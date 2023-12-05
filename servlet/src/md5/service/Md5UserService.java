package md5.service;

import md5.domain.dto.LoginDto;
import md5.domain.entity.Md5User;
import md5.domain.vo.RestResponse;

/**
 * @projectName: git
 * @package: md5.service
 * @className: Md5UserService
 * @author: 胡代伟
 * @description: Md5UserService
 * @date: 2023/12/5 13:09
 * @version: 1.0
 */
public interface Md5UserService {
    /**
     * @param md5User:
     * @return RestResponse<Boolean>
     * @author 胡代伟
     * @description 注册
     * @date 2023/12/5 14:01
     */
    public RestResponse<Boolean> register(Md5User md5User);

    /**
     * @param loginDto:
     * @return RestResponse<Boolean>
     * @author 胡代伟
     * @description 登录
     * @date 2023/12/5 14:03
     */
    public RestResponse<Boolean> login(LoginDto loginDto);
}
