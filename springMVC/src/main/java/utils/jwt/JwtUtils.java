package utils.jwt;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTCreator;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTDecodeException;
import com.auth0.jwt.interfaces.DecodedJWT;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * @author 胡代伟
 * @description jwt工具类
 * @date 2024-01-03 14:55
 */
public class JwtUtils {
    /**
     * 过期时间一天，
     * 1000 * 24 * 60 * 60 一天
     * 1000 * 60 * 60 一小时
     * 120 * 60 * 1000 120分钟
     */
    private static final long EXPIRE_TIME = 120 * 60 * 1000;
    /**
     * token私钥
     */
    private static final String TOKEN_SECRET = "wqq";

    /**
     * 校验token是否正确
     *
     * @param token 密钥
     * @return 是否正确
     */
    public static boolean verify(String token) {
        try {
            Algorithm algorithm = Algorithm.HMAC256(TOKEN_SECRET);
            JWTVerifier verifier = JWT.require(algorithm)
                    .build();
            DecodedJWT jwt = verifier.verify(token);
            return true;
        } catch (Exception exception) {
            return false;
        }
    }

    /**
     * 获得token中的信息无需secret解密也能获得
     *
     * @return token中包含的用户名
     */
    public static String getTokenStr(String token, String data) {
        try {
            DecodedJWT jwt = JWT.decode(token);
            return jwt.getClaim(data).asString();
        } catch (JWTDecodeException e) {
            return null;
        }
    }


    /**
     * 生成签名,120min后过期
     *
     * @return 加密的token
     */
    public static String getToken(Map<String, String> map) {
        //            过期时间
        Date date = new Date(System.currentTimeMillis() + EXPIRE_TIME);

        //新建一个JWT的Builder对象
        JWTCreator.Builder builder = JWT.create();
//            设置头部信息
        Map<String, Object> header = new HashMap<>(2);
        header.put("typ", "JWT");
        header.put("alg", "HS256");
        map.forEach(builder::withClaim);
        //设置过期时间和签名
        String token = builder.withHeader(header).withExpiresAt(date).sign(Algorithm.HMAC256(TOKEN_SECRET));
        // 附带用户信息，生成签名
        return token;
    }

    public static void main(String[] args) {
        Map<String, String> map = new HashMap<>();
        map.put("z2b", "1243");
        map.put("x1b", "e342");
        map.put("c3b", "5544");

        String signs = getToken(map);
        System.out.println(signs);
        boolean s = verify(signs);
        System.out.println(s);
        String str = getTokenStr(signs, "c3b");
        System.out.println(str);


    }
}
