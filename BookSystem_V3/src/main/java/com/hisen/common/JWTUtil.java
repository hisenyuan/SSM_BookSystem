package com.hisen.common;

import com.alibaba.fastjson.JSON;
import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.Claim;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.hisen.bean.contains.CommonEnum;
import java.io.UnsupportedEncodingException;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import org.joda.time.DateTime;

/**
 * Created by hisenyuan on 2017/8/17 at 11:09.
 */
public class JWTUtil {



  private static final String SECRET = CommonEnum.JWT_SECRET.getMsg();
  private static final String EXP = "exp";
  private static final String PAYLOAD = CommonEnum.JWT_PAYLOAD.getMsg();

  /**
   * 生成Token:jwt
   * @param object 传入的加密对象 - 放入PAYLOAD
   * @param maxAge 过期事件,单位毫秒
   * @param <T>
   * @return
   */
  public static <T> String sign(T object, long maxAge) {
    Map<String, Object> map = new HashMap<String, Object>();
    String jsonString = JSON.toJSONString(object);
    map.put("alg", "HS256");
    map.put("typ", "JWT");
    long exp = System.currentTimeMillis() + maxAge;
    System.out.println("JWTUtil 当前时间:"+new DateTime().toString("yyyy-MM-dd HH:mm:ss EE"));
    System.out.println("JWTUtil 过期时间:"+new DateTime(exp).toString("yyyy-MM-dd HH:mm:ss EE"));
    String token = null;
    try {
      token = JWT.create()
          .withHeader(map)//header
          .withClaim(PAYLOAD, jsonString)//存放的内容 json
          .withClaim(EXP, new DateTime(exp).toDate())//超时时间
          .sign(Algorithm.HMAC256(SECRET));//密钥
    } catch (UnsupportedEncodingException e) {
      e.printStackTrace();
    }
    return token;
  }

  /**
   * 解密token
   * @param token jwt类型的token
   * @param classT 加密时的类型
   * @param <T>
   * @return 返回解密后的对象 - 如果token过期返回空对象
   */
  public static <T> T unsign(String token, Class<T> classT)  {
    if (token == null) {
      return null;
    }
    DecodedJWT decode = JWT.decode(token);
    Map<String, Claim> claims = decode.getClaims();
    if (claims.containsKey(EXP) && claims.containsKey(PAYLOAD)){
      long tokenTime = claims.get(EXP).asDate().getTime();
      long nowTime = new Date().getTime();
      // 判断令牌是否超时
      if (tokenTime > nowTime){
        String json = claims.get(PAYLOAD).asString();
        return JSON.parseObject(json, classT);
      }
    }

//    JWTVerifier verifier = null;
//    try {
//      verifier = JWT.require(Algorithm.HMAC256(SECRET)).build();
//      DecodedJWT jwt = verifier.verify(token); // 如果超时,直接抛出运行时异常
//      Map<String, Claim> claims = jwt.getClaims();
//      if (claims.containsKey(EXP) && claims.containsKey(PAYLOAD)) {
//        long tokenTime = claims.get(EXP).asDate().getTime();
//        long now = new Date().getTime();
//        // 判断令牌是否已经超时
//        if (tokenTime > now) {
//          String json = claims.get(PAYLOAD).asString();
//          // 把json转回对象，返回
//          return JSON.parseObject(json, classT);
//        }
//      }
//    } catch (UnsupportedEncodingException e) {
//      e.printStackTrace();
//    } catch (TokenExpiredException e){
//      e.printStackTrace();
//    }
    return null;
  }
}
