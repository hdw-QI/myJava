package cookie.service.impl;

import cookie.service.ServletUserService;
import cookie.dao.ServletUserDao;
import cookie.dao.impl.ServletUserDaoImpl;
import register.bean.ServletUser;

/**
 * @projectName: git
 * @package: cookie.service.impl
 * @className: ServletUserServiceImpl
 * @author: 胡代伟
 * @description: TODO
 * @date: 2023/11/28 18:51
 * @version: 1.0
 */
public class ServletUserServiceImpl implements ServletUserService {
    ServletUserDao servletUserDao = new ServletUserDaoImpl();
    @Override
    public boolean login(String username, String password) {
        ServletUser servletUser = servletUserDao.getServletUser(username, password);
        return servletUser != null;
    }
}
