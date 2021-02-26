package com.xxxx.server.config.security.component;


import io.jsonwebtoken.*;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * JwtToken工具类
 */
@Component
public class JwtTokenUtil {

    //用户名的key
    private static final String CLAIM_KEY_USERNAME = "sub";
    //Jwt的创建时间
    private static final String CLAIM_KEY_CREATED = "create";

    //Jwt秘钥，失效时间
    @Value("${jwt.secret}")
    private String secret;
    @Value("${jwt.expiration}")
    private Long expiration;


    /**
     * 根据用户信息生成 token
     * @param userDetails
     * @return
     */
    public String generateToken(UserDetails userDetails){
        Map<String, Object> claims = new HashMap<>();
        claims.put(CLAIM_KEY_USERNAME,userDetails.getUsername());
        claims.put(CLAIM_KEY_CREATED,new Date());
        return generateToken(claims);
    }

    /**
     * 从token中获取用户名
     * @param token
     * @return
     */
    public String getUserNameFromToken(String token){
        String username = null;
        try {
            Claims claims = getClaimsFromToken(token);
            username = claims.getSubject();
        } catch (Exception e) {
            username = null;
            e.printStackTrace();
        }
        return username;
    }

    /**
     * 验证token是否有效
     * @param token
     * @param userDetails
     * @return
     */
    public boolean validateToken(String token,UserDetails userDetails){
        String username = getUserNameFromToken(token);
        return username.equals(userDetails.getUsername()) && !isTokenExpired(token);
    }

    /**
     * 验证token是否可以被刷新
     * @param token
     * @return
     */
    public boolean canRefresh(String token){
        return !isTokenExpired(token);
    }

    /**
     * 刷新token
     * @param token
     * @return
     */
    public String refreshToken(String token){
        Claims claims = getClaimsFromToken(token);//从旧token中获取负载
        claims.put(CLAIM_KEY_CREATED, new Date());//设置新的创建时间
        return generateToken(claims);//返回负载中的新token
    }



/****************************************************************************/

    /**
     * 根据荷载（负载）生成token
     * @param claims
     * @return
     */
    private String generateToken(Map<String, Object> claims) {
        return Jwts.builder()
                .setClaims(claims)
                .setExpiration(generateExpriationDate())
                .signWith(SignatureAlgorithm.HS512,secret)
                .compact();
    }

    /**
     * 生成token失效时间
     * @return
     */
    private Date generateExpriationDate() {
        return new Date(System.currentTimeMillis() + expiration * 1000);
    }

    /**
     * 从token中获取荷载
     * @param token
     * @return
     */
    private Claims getClaimsFromToken(String token) {
        Claims claims = null;
        try {
            claims = Jwts.parser()
                    .setSigningKey(secret)
                    .parseClaimsJws(token)
                    .getBody();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return claims;
    }

    /**
     * 判断token时间是否失效
     * @param token
     * @return
     */
    private boolean isTokenExpired(String token) {
        Date exprieDate =  getExpiredDateFromToken(token);
        return exprieDate.before(new Date());
    }

    /**
     * 从token中获取过期时间
     * @param token
     * @return
     */
    private Date getExpiredDateFromToken(String token) {
        Claims claims = getClaimsFromToken(token);
        return claims.getExpiration();
    }
}
