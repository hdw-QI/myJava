package md5.dao;

import md5.domain.dto.LoginDto;
import md5.domain.entity.Md5User;

/**
 * @projectName: git
 * @package: md5.dao
 * @className: Md5UserDao
 * @author: 胡代伟
 * @description: dao
 * @date: 2023/12/5 13:09
 * @version: 1.0
 */
public interface Md5UserDao {
    /**
     * @param md5User:
     * @return int
     * @author 胡代伟
     * @description 添加用户
     * @date 2023/12/5 13:13
     */
    public int addUser(Md5User md5User);

    /**
     * @param loginDto:
     * @return Md5User
     * @author 胡代伟
     * @description 获取用户
     * @date 2023/12/5 13:13
     */
    public Md5User getLoginUser(LoginDto loginDto);


    /**
     * @param username:
     * @return String
     * @author 胡代伟
     * @description 获取盐值
     * @date 2023/12/5 14:30
     */
    public String getUserSalt(String username);
}
