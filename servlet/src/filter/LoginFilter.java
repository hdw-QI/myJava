package filter;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @projectName: git
 * @package: filter
 * @className: LoginFilter
 * @author: 胡代伟
 * @description: 检查登录
 * @date: 2023/12/1 11:47
 * @version: 1.0
 */
@WebFilter("/*")
public class LoginFilter implements Filter {
    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        // 放行登陆界面和登录认证方法,静态资源,验证码
        HttpServletRequest request=(HttpServletRequest)servletRequest;
        HttpServletResponse response=(HttpServletResponse)servletResponse;
        String requestURI = request.getRequestURI();
        if (requestURI.contains("/loginFilter.jsp") || requestURI.contains("/login/fl") || requestURI.contains("/login/filterLogin" ) || requestURI.contains("/js") || requestURI.contains("/css") || requestURI.contains("/imageCode")){
            filterChain.doFilter(servletRequest,servletResponse);
        }else {
            Object account = request.getSession().getAttribute("account");
            if (account!=null){
                filterChain.doFilter(servletRequest,servletResponse);
            }else {
                RequestDispatcher requestDispatcher = request.getRequestDispatcher("loginFilter.jsp");
                requestDispatcher.forward(request,response);
            }
        }
    }
}
