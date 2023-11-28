package cookie.dao;

import register.bean.ServletUser;

/**
 * @projectName: git
 * @package: cookie.dao
 * @className: ServletUserDao
 * @author: 胡代伟
 * @description: TODO
 * @date: 2023/11/28 18:50
 * @version: 1.0
 */
public interface ServletUserDao {
    /**
     * @param username: 
     * @return ServletUser
     * @author 胡代伟
     * @description 根据用户名获取信息
     * @date 2023/11/28 18:52
     */
    public ServletUser getServletUser(String username,String password);
}
