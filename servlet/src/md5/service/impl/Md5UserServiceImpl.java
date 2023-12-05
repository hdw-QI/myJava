package md5.service.impl;

import checkcode.CreateVerificationCode;
import md5.dao.Md5UserDao;
import md5.dao.impl.Md5UserDaoImpl;
import md5.domain.dto.LoginDto;
import md5.domain.entity.Md5User;
import md5.domain.vo.RestResponse;
import md5.service.Md5UserService;
import utils.md5.GetRandomSaltUtils;
import utils.md5.MD5Utils;

/**
 * @projectName: git
 * @package: md5.service.impl
 * @className: Md5UserServiceImpl
 * @author: 胡代伟
 * @description: Md5UserServiceImpl
 * @date: 2023/12/5 13:10
 * @version: 1.0
 */
public class Md5UserServiceImpl implements Md5UserService {
    private final Md5UserDao md5UserDao = new Md5UserDaoImpl();
    @Override
    public RestResponse<Boolean> register(Md5User md5User) {
        // 密码二次加密加盐
        String salt = GetRandomSaltUtils.getSecurityCode(10, CreateVerificationCode.SecurityCodeLevel.Hard, false);
        md5User.setSalt(salt);
        String dbPwd = MD5Utils.md5(md5User.getPassword() + salt);
        md5User.setPassword(dbPwd);
        int i = md5UserDao.addUser(md5User);
        if (i>0){
            return RestResponse.success("注册成功", true);
        }else {
            return RestResponse.fail("注册失败", false);
        }
    }

    @Override
    public RestResponse<Boolean> login(LoginDto loginDto) {
        // 密码二次加密加盐。因为要与数据库中的一样
        String userSalt = md5UserDao.getUserSalt(loginDto.getUsername());
        String dbPwd = MD5Utils.md5(loginDto.getPassword() + userSalt);
        loginDto.setPassword(dbPwd);
        Md5User loginUser = md5UserDao.getLoginUser(loginDto);
        if (loginUser == null) {
            return RestResponse.fail("登录失败", false);
        }
        return RestResponse.fail("登录成功", true);
    }
}
