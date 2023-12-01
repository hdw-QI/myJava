package imitationSpringMVC;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.List;

/**
 * @projectName: git
 * @package: imitationSpringMVC
 * @className: BaseServlet
 * @author: 胡代伟
 * @description: 查询service，判断要执行的方法
 * @date: 2023/12/1 10:11
 * @version: 1.0
 */
public class BaseServlet extends HttpServlet {
    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        // http://localhost:8080/test/delete 获取/test/delete
        String servletPath = req.getRequestURI();
        executeServletMethod(req,resp,servletPath);
    }
    /**
     * @param servletPath:
     * @return String
     * @author 胡代伟
     * @description 获取url中要执行的方法
     * @date 2023/12/1 10:17
     */
    public String getMethodName(String servletPath){
        int begin = servletPath.lastIndexOf("/");
        String substring;
        if (servletPath.contains(".")){
            int end = servletPath.lastIndexOf(".");
            substring = servletPath.substring(begin+1,end);
        }else {
            substring = servletPath.substring(begin+1);
        }

        System.out.println("getMethodName:"+substring);
        return substring;
    }
    public void executeServletMethod(HttpServletRequest httpServletRequest,HttpServletResponse httpServletResponse,String servletPath){
        String methodName = getMethodName(servletPath);
        try {
            Class<? extends BaseServlet> aClass = this.getClass();
            Method declaredMethod = aClass.getDeclaredMethod(methodName, HttpServletRequest.class, HttpServletResponse.class);
            declaredMethod.setAccessible(true);
            declaredMethod.invoke(this,httpServletRequest,httpServletResponse);
        } catch (NoSuchMethodException e) {
            throw new RuntimeException(e);
        } catch (InvocationTargetException e) {
            throw new RuntimeException(e);
        } catch (IllegalAccessException e) {
            throw new RuntimeException(e);
        }
    }
}
