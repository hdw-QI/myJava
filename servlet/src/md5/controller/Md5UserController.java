package md5.controller;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import imitationSpringMVC.BaseServlet;
import imitationSpringMVC.annotation.UrlMapping;
import md5.domain.dto.LoginDto;
import md5.domain.entity.Md5User;
import md5.domain.vo.RestResponse;
import md5.service.Md5UserService;
import md5.service.impl.Md5UserServiceImpl;
import utils.json.HttpGetJson;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @projectName: git
 * @package: md5.controller
 * @className: Md5UserController
 * @author: 胡代伟
 * @description: Md5UserController
 * @date: 2023/12/5 13:10
 * @version: 1.0
 */
@WebServlet("/md5/*")
public class Md5UserController extends BaseServlet {
    Md5UserService md5UserService = new Md5UserServiceImpl();
    @UrlMapping("userRegister")
    public void register(HttpServletRequest request, HttpServletResponse response) throws IOException {
        JSONObject json = HttpGetJson.getJson(request);
        Object nickname = json.get("nickname");
        Object password = json.get("password");
        System.out.println(nickname);
        System.out.println(password);
        Md5User md5User = new Md5User();
        md5User.setUserName(nickname.toString());
        md5User.setPassword(password.toString());
        RestResponse<Boolean> register = md5UserService.register(md5User);
        response.setContentType("application/json");
        response.getWriter().write(JSON.toJSONString(register));
    }

    @UrlMapping("userLogin")
    public void login(HttpServletRequest request, HttpServletResponse response) throws IOException {
        JSONObject json = HttpGetJson.getJson(request);
        Object username = json.get("username");
        Object password = json.get("password");
        Object captcha = json.get("captcha");
        String generatedCode= (String) request.getSession().getAttribute("verityCode");
        response.setContentType("application/json");
        if (captcha.toString().equalsIgnoreCase(generatedCode)){
            LoginDto loginDto = new LoginDto();
            loginDto.setUsername(username.toString());
            loginDto.setPassword(password.toString());
            RestResponse<Boolean> login = md5UserService.login(loginDto);
            response.getWriter().write(JSON.toJSONString(login));
        }else {
            RestResponse<Boolean> fail = RestResponse.fail("验证码错误", false);
            response.getWriter().write(JSON.toJSONString(fail));
        }


    }
}
