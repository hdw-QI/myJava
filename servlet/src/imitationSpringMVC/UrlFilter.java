package imitationSpringMVC;

import imitationSpringMVC.annotation.apt.UrlMappingAPT;
import utils.annotationScanner.ScannerManager;
import utils.annotationScanner.annotation.Scanner;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.annotation.WebServlet;
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
 * @className: UrlFilter
 * @author: 胡代伟
 * @description: 获取请求的url
 * @date: 2023/11/30 15:28
 * @version: 1.0
 */
@WebFilter("/uf/*") //uf:user filter
@Scanner("employee.controller")
public class UrlFilter implements Filter {
    // 扫描器
    ScannerManager<WebServlet> scannerManager=new ScannerManager<>();
    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        // 先要将 ServletRequest对象强转成HttpRequestServlet。HttpServletRequest extends ServletRequest
        HttpServletRequest httpServletRequest=(HttpServletRequest) servletRequest;
        HttpServletResponse httpServletResponse = (HttpServletResponse)servletResponse;
        // 可以在这样统一编码格式
        httpServletRequest.setCharacterEncoding("UTF-8");
        httpServletResponse.setCharacterEncoding("UTF-8");
//        响应类型不在这里进行统一处理，不同的方法有不同的响应内容
//        httpServletResponse.setContentType("text/html");

        // 获取的是url中符合webServlet中value的值
        String servletPath = httpServletRequest.getServletPath();
        System.out.println(servletPath);
        executeServletMethod(httpServletRequest,httpServletResponse,servletPath);
//        filterChain.doFilter(servletRequest,servletResponse);
    }

    /**
     * @param servletPath:httpServletRequest.getServletPath()的值
     * @return String
     * @author 胡代伟
     * @description 获取url中要执行的方法
     * @date 2023/11/30 15:55
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

    /**
     * @param servletPath: httpServletRequest.getServletPath()的值
     * @return String
     * @author 胡代伟
     * @description 获取访问servlet的路径。@WebServlet中的值
     * @date 2023/11/30 16:48
     */
    public String getWebServletValue(String servletPath){
//        httpServletRequest.getServletPath()获取的值不会包含web上下文路径
        String substring;
        int end = servletPath.lastIndexOf("/");
        substring = servletPath.substring(0, end);

        System.out.println("getWebServletValue:"+substring);
        return substring;
    }

    /**
     * @param packages: 所有指定包下包含@WebServlet的类的全包名
     * @param webServletValue:客户端访问servlet的路径。@WebServlet中的值
     * @return String
     * @author 胡代伟
     * @description 获取客户端要访问的servlet的全类名
     * @date 2023/12/1 9:50
     */
    public String getServletPackage(List<String> packages,String webServletValue) {
        for (String pack : packages) {
            try {
                Class<?> aClass = Class.forName(pack);
                WebServlet annotation = aClass.getAnnotation(WebServlet.class);
                String s = annotation.value()[0];
                if (s.equals(webServletValue)){
                    return pack;
                }
            } catch (ClassNotFoundException e) {
                throw new RuntimeException(e);
            }
        }
        return null;
    }
    /**
     * @param httpServletRequest:
     * @param httpServletResponse:
     * @param servletPath:
     * @return void
     * @author 胡代伟
     * @description 执行servlet中的方法
     * @date 2023/12/1 9:53
     */
    public void executeServletMethod(HttpServletRequest httpServletRequest,HttpServletResponse httpServletResponse,String servletPath){
        String methodName = getMethodName(servletPath);
        String webServletValue = getWebServletValue(servletPath);
        List<String> strings = scannerManager.scanner(WebServlet.class);
        String servletPackage = getServletPackage(strings, webServletValue);
        Class<?> aClass = null;
        Object method=null;
        try {
            aClass = Class.forName(servletPackage);
            Constructor<?> declaredConstructor = aClass.getDeclaredConstructor();
            method = declaredConstructor.newInstance();
            Method declaredMethod = aClass.getDeclaredMethod(methodName, HttpServletRequest.class, HttpServletResponse.class);
            declaredMethod.setAccessible(true);
            declaredMethod.invoke(method,httpServletRequest,httpServletResponse);
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        } catch (NoSuchMethodException e) {
            UrlMappingAPT.isMapping(aClass,method,methodName,httpServletRequest,httpServletResponse);
        } catch (InvocationTargetException e) {
            throw new RuntimeException(e);
        } catch (InstantiationException e) {
            throw new RuntimeException(e);
        } catch (IllegalAccessException e) {
            throw new RuntimeException(e);
        }
    }





}
