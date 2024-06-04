package com.example.springbootlearn.config.jwt;

import com.example.springbootlearn.utils.enumType.Constant;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.JwtBuilder;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.apache.tomcat.util.codec.binary.Base64;

import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;
import java.util.Date;

/**
 * @Project springboot-learn
 * @Description
 * @Author xuzhenshan
 * @Date 2023/12/28 09:50:24
 * @Version 1.0
 */
public class JwtUtil {

    /**
     * 生成签名,15分钟过期
     *
     * @param objectId
     * @param subject
     * @return
     */
    public static String createJwt(int objectId,String subject) {
        SignatureAlgorithm signatureAlgorithm = SignatureAlgorithm.HS256;
        //设置过期时间
        long presentTime = System.currentTimeMillis();
        Date issuedAtTime = new Date(presentTime);
        Date expirationTime = new Date(presentTime + Constant.TOKEN_EXPIRE_TIME);

        SecretKey secretKey = generalKey();
        JwtBuilder builder = Jwts.builder()
                .setId(String.valueOf(objectId))
                // 主题
                .setSubject(subject)
                // 签发者
                .setIssuer(Constant.JWT_ID)
                // 签发时间
                .setIssuedAt(issuedAtTime)
                // 签名算法以及密匙
                .signWith(signatureAlgorithm, secretKey)
                // 过期时间
                .setExpiration(expirationTime);
        return builder.compact();
    }

    /**
     * 验证JWT
     *
     * @param jwtStr
     * @return
     */
    public Claims validateJWT(String jwtStr) {
        //签名秘钥，和生成的签名的秘钥一模一样
        SecretKey key = generalKey();
        return Jwts.parser()
                .setSigningKey(key)
                .parseClaimsJws(jwtStr).getBody();
    }

    /**
     * 设置key值
     *
     * @return
     */
    public static SecretKey generalKey() {
        String stringKey = Constant.TOKEN_SECRET;
        byte[] encodedKey = Base64.decodeBase64(stringKey);
        return new SecretKeySpec(encodedKey, 0, encodedKey.length, "AES");
    }

    public static void main(String[] args) {
        String JWT = createJwt(1, "username");
        System.out.println("测试token:" + JWT);
//        String jwt = "" +
//                "eyJhbGciOiJIUzI1NiJ9.eyJqdGkiOiIxIiwic3ViIjoidXNlcm5hbWUiLCJpc3MiOiJlMzBmN2U4Yy0wODY5LTQ5NDItYjk5Yy01ZDBlYWQwN2ZjMGEiLCJpYXQiOjE1OTUzMjA0MzIsImV4cCI6MTU5NTkyNTIzMn0.f_v1dN3YPoH9rez-Tb2i0w4BuP1yIul_FA7rWYam-So";
//        JwtUtil jwtUtil = new JwtUtil();
//        Claims c = jwtUtil.validateJWT(jwt);
//        System.out.println(c.getId());
//        System.out.println(c.getIssuedAt());
//        System.out.println(c.getSubject());

    }

}
