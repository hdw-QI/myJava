package utils.json;

import com.alibaba.fastjson.JSONObject;

import javax.servlet.http.HttpServletRequest;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * @projectName: git
 * @package: utils.json
 * @className: HttpGetJson
 * @author: 胡代伟
 * @description: 封装一个处理http请求获得json参数的工具类
 * @date: 2023/12/4 14:14
 * @version: 1.0
 */
public class HttpGetJson {
    /**
     * @param request:
     * @return JSONObject
     * @author 胡代伟
     * @description 获取json数据
     * @date 2023/12/4 14:15
     */
    public static JSONObject getJson(HttpServletRequest request){
        String result = "";
        BufferedReader in = null;
        try {
            in= new BufferedReader(new InputStreamReader(
                    request.getInputStream(),"utf-8"));
            String line;
            while ((line = in.readLine()) != null) {
                result += line;
            }
        } catch (IOException e) {
            e.printStackTrace();
        }finally {
            try {
                in.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        return (JSONObject) JSONObject.parse(result);
    }
}
