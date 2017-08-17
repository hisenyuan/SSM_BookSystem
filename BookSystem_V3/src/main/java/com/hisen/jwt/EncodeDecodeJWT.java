package com.hisen.jwt;

import com.auth0.jwt.JWTSigner;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.internal.com.fasterxml.jackson.databind.ObjectMapper;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by hisenyuan on 2017/8/17 at 11:09.
 */
public class EncodeDecodeJWT {
  private static final String SECRET = "XX#$%()(#*!()!KL<><MQLMNQNQJQK sdfkjsdrow32234545fdf>?N<:{LWPW";
  private static final String EXP = "exp";
  private static final String PAYLOAD = "payload";

  // 加密，传入一个对象和有效期
  public static <T> String sign(T object,long maxAge){
    try {
      JWTSigner signer = new JWTSigner(SECRET);
      final Map<String, Object> claims = new HashMap<String, Object>();
      ObjectMapper mapper = new ObjectMapper();
      String jsonString = mapper.writeValueAsString(object);
      claims.put(PAYLOAD,jsonString);
      claims.put(EXP,System.currentTimeMillis()+maxAge);
      return signer.sign(claims);
    }catch (Exception e){
      System.out.println(e);
      return null;
    }
  }

  // 解密，传入一个加密后的token字符串和解密后的类型
  public static<T> T unsign(String jwt,Class<T> classT){
    final JWTVerifier verifier = new JWTVerifier(SECRET);
    try {
      final Map<String, Object> claims = verifier.verify(jwt);
      if (claims.containsKey(EXP) && claims.containsKey(PAYLOAD)){
        long exp = (Long) claims.get(EXP);
        long currentTimeMillis = System.currentTimeMillis();
        if (exp>currentTimeMillis){
          String json = (String) claims.get(PAYLOAD);
          ObjectMapper mapper = new ObjectMapper();
          return mapper.readValue(json,classT);
        }
      }
      return null;
    }catch (Exception e){
      return null;
    }
  }
}
