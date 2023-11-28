package register.service;

import register.bean.ServletUser;

/**
 * @projectName: git
 * @package: register.service
 * @className: ServletUserService
 * @author: 胡代伟
 * @description: TODO
 * @date: 2023/11/28 18:06
 * @version: 1.0
 */
public interface ServletUserService {
    /**
     * @param servletUser:
     * @return boolean
     * @author 胡代伟
     * @description 注册
     * @date 2023/11/28 18:16
     */
    public boolean register(ServletUser servletUser);
}
