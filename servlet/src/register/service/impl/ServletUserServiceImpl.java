package register.service.impl;

import register.bean.ServletUser;
import register.dao.ServletUserDao;
import register.dao.impl.ServletUserDaoImpl;
import register.service.ServletUserService;

/**
 * @projectName: git
 * @package: register.service.impl
 * @className: ServletUserServiceImpl
 * @author: 胡代伟
 * @description: TODO
 * @date: 2023/11/28 18:06
 * @version: 1.0
 */
public class ServletUserServiceImpl implements ServletUserService {
    ServletUserDao servletUserDao = new ServletUserDaoImpl();
    @Override
    public boolean register(ServletUser servletUser) {
        int i = servletUserDao.insertServletUser(servletUser);
        return i > 0;
    }
}
