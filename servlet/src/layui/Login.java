package layui;

import com.alibaba.fastjson.JSON;
import imitationSpringMVC.BaseServlet;
import imitationSpringMVC.annotation.UrlMapping;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @projectName: git
 * @package: layui
 * @className: Login
 * @author: 胡代伟
 * @description: 使用layUI的登录页面
 * @date: 2023/12/2 14:43
 * @version: 1.0
 */
@WebServlet("/layUILogin/*")
public class Login extends BaseServlet {
    @UrlMapping("login")
    public void login(HttpServletRequest request, HttpServletResponse response) {
        String username = request.getParameter("username");
        String password = request.getParameter("password");
        String captcha = request.getParameter("captcha");
        String generatedCode= (String) request.getSession().getAttribute("verityCode");
        if (username.equals("qq") & password.equals("hu") & captcha.equalsIgnoreCase(generatedCode)){
//            验证码只能用一次
            request.getSession().removeAttribute("verityCode");
            Result<Boolean> result = new Result<>();
            result.setMsg("登陆成功");
            result.setResp(true);
            String jsonString = JSON.toJSONString(result);
            response.setContentType("application/json");
            try {
                response.getWriter().write(jsonString);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
    }
    static class Result<T>{
        private String msg;
        private T resp;

        public String getMsg() {
            return msg;
        }

        public void setMsg(String msg) {
            this.msg = msg;
        }

        public T getResp() {
            return resp;
        }

        public void setResp(T resp) {
            this.resp = resp;
        }
    }
}

