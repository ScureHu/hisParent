package units;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.JwtBuilder;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.boot.context.properties.ConfigurationProperties;

import javax.servlet.http.HttpServletRequest;
import java.util.Date;

@ConfigurationProperties("jwt.config")
public class JwtUtil {
    //key为盐，必须用这个来加密，同时也得引用这个来解密
    private String key;
    //一个小时
    private long ttl;

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public long getTtl() {
        return ttl;
    }

    public void setTtl(long ttl) {
        this.ttl = ttl;
    }

    /**
     * 生成JWT
     * @return
     */
    public String createJWT(String id,String subject,String wardCode){
        long nowMillis = System.currentTimeMillis();
        //当前时间
        Date now = new Date(nowMillis);
        JwtBuilder builder = Jwts.builder().setId(id)
                .setSubject(subject)
                .setIssuedAt(now)
                .signWith(SignatureAlgorithm.HS256,key)
                .claim("wardCode",wardCode);
        if(ttl>0){
            builder.setExpiration(new Date(nowMillis + ttl));
        }
        return builder.compact();
    }

    /**
     * 解析jwt
     * 得到的是key和value
     * @param jwtStr
     * @return
     */
    public Claims parseJWT(String jwtStr){
        return Jwts.parser()
                .setSigningKey(key)
                .parseClaimsJws(jwtStr)
                .getBody();
    }

    /**
     * 根据HttpServletRequest获取wardCode
     * @param request
     * @return
     */
    public String getJwtWardCode(HttpServletRequest request){
        String authorization = request.getHeader("Authorization");
        String token = authorization.substring(7);
        Claims claims = parseJWT(token);
        return (String) claims.get("wardCode");
    }
}
