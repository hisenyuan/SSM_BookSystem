package com.hisen.common;

import java.util.HashMap;
import java.util.Map;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Created by hisen on 17-8-26.
 * E-mail: hisenyuan@gmail.com
 */
public class CookieUtil {

  /**
   * 把cookie装入map,然后设置到response中
   */
  public static void addCookieMap(HttpServletResponse response, Map<String, String> map) {
    for (String key : map.keySet()) {
      String value = map.get(key);
      Cookie cookie = new Cookie(key.trim(), value.trim());
      cookie.setMaxAge(30 * 60);// 设置为30min
      cookie.setPath("/");
      response.addCookie(cookie);
    }
  }

  /**
   * 添加单个cookie
   * @param response
   * @param name
   * @param value
   */
  public static void addCookie(HttpServletResponse response, String name, String value) {
    Cookie cookie = new Cookie(name.trim(), value.trim());
    cookie.setMaxAge(30 * 60);// 设置为30min
    cookie.setPath("/");
    response.addCookie(cookie);
  }

  /**
   * 对cookie进行封装
   */
  public static Map<String, Cookie> ReadCookieMap(HttpServletRequest request) {
    Map<String, Cookie> cookieMap = new HashMap<String, Cookie>();
    Cookie[] cookies = request.getCookies();
    if (null != cookies) {
      for (Cookie cookie : cookies) {
        cookieMap.put(cookie.getName(), cookie);
      }
    }
    return cookieMap;
  }
}
