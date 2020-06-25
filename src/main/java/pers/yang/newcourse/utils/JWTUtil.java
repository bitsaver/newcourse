package pers.yang.newcourse.utils;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTDecodeException;
import com.auth0.jwt.interfaces.DecodedJWT;

import java.util.Date;

public class JWTUtil {

    // 过期时间5分钟
    private static final long EXPIRE_TIME = 60*60*1000;

    public static boolean verify(String token, Long id, String secret) {
        try {
            Algorithm algorithm = Algorithm.HMAC256(secret);
            JWTVerifier verifier = JWT.require(algorithm)
                    .withClaim("id",id)
                    .build();
            DecodedJWT jwt = verifier.verify(token);
            return true;
        } catch (Exception exception) {
            return false;
        }
    }


    public static Long getUserId(String token) {
        try {
            DecodedJWT jwt = JWT.decode(token);
            return jwt.getClaim("id").asLong();
        } catch (JWTDecodeException e) {
            return null;
        } catch (NullPointerException e){
            return null;
        }
    }


    public static String getPassword(String token) {
        try {
            DecodedJWT jwt = JWT.decode(token);
            return jwt.getClaim("password").asString();
        } catch (JWTDecodeException e) {
            return null;
        }
    }

    /**
     * 生成签名,5min后过期
     * @param secret 用户的密码
     * @return 加密的token
     */
    public static String sign(Long id, String secret) {
        try {
            Date date = new Date(System.currentTimeMillis()+EXPIRE_TIME);
            Algorithm algorithm = Algorithm.HMAC256(secret);
            // 附带username信息
            return JWT.create()
                    .withClaim("id", id)
                    .withExpiresAt(date)
                    .sign(algorithm);
        } catch (Exception e) {
            return null;
        }
    }

    public static void main(String[] args) {
        /**
         * 测试生成一个token
         */
        String sign = sign(123456L, "123456");
        System.out.println(sign);
        String username = getPassword("eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJleHAiOjE1OTI5MDM1OTQsInVzZXJuYW1lIjoieWFuZyJ9.GJrE5z_bq1KZVmAD2fHidThw3zL_UYGXPgNfmuxDhO0");
        System.out.println(username);

    }
}