package cookie.service;

/**
 * @projectName: git
 * @package: cookie.service
 * @className: ServletUserService
 * @author: 胡代伟
 * @description: TODO
 * @date: 2023/11/28 18:49
 * @version: 1.0
 */
public interface ServletUserService {
    /**
     * @param username:
     * @param password:
     * @return boolean
     * @author 胡代伟
     * @description 登录
     * @date 2023/11/28 19:01
     */
    public boolean login(String username, String password);
}
