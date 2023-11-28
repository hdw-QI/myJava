package register.dao;

import register.bean.ServletUser;

/**
 * @projectName: git
 * @package: register.dao
 * @className: ServletUserDao
 * @author: 胡代伟
 * @description: TODO
 * @date: 2023/11/28 18:05
 * @version: 1.0
 */
public interface ServletUserDao {
    /**
     * @param servletUser:
     * @return int
     * @author 胡代伟
     * @description 添加用户
     * @date 2023/11/28 18:08
     */
    public int insertServletUser(ServletUser servletUser);
}
