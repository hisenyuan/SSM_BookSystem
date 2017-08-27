package com.hisen.controller;

import static com.hisen.common.MD5Util.toMd5;

import com.alibaba.fastjson.JSON;
import com.hisen.bean.request.UserLoginRequest;
import com.hisen.bean.response.UserLoginResponse;
import com.hisen.common.CookieUtil;
import com.hisen.service.UserInfoService;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * Created by hisen on 17-8-26.
 * E-mail: hisenyuan@gmail.com
 */
@Controller
public class UserInfoController {
  private Logger log = LoggerFactory.getLogger(this.getClass());
  @Autowired
  private UserInfoService userInfoService;

  @RequestMapping(value = "/login", method = RequestMethod.POST, produces = "text/plain;charset=UTF-8")
  @ResponseBody
  public String login(HttpServletResponse res,HttpServletRequest req,UserLoginRequest request) {
    request.setPwd(toMd5(request.getPwd()));
    log.info("[UserInfoController] - [login] 入参-> "+request.toString());
    UserLoginResponse login = userInfoService.login(request);
    log.info("[UserInfoController] - [login] 出参-> "+login.toString());
//    // 放置cookies
//    Map<String,String> map = new HashMap<String, String>();
//    map.put("jwt",login.getJwt());
//    map.put("name",login.getName());
//    CookieUtil.addCookieMap(res,map);
    return JSON.toJSONString(login);
  }
  @RequestMapping(value = "/test", method = RequestMethod.GET, produces = "text/plain;charset=UTF-8")
  @ResponseBody
  public String test(HttpServletResponse response){
    // 放置cookies
    Map<String,String> map = new HashMap<String, String>();
    map.put("md5","test");
    map.put("pwd","hisen");
    CookieUtil.addCookieMap(response,map);
    return "hello";
  }
  @RequestMapping(value = "/add", method = RequestMethod.GET, produces = "text/plain;charset=UTF-8")
  @ResponseBody
  public String testJWT(HttpServletResponse res,HttpServletRequest req){
    Boolean checkJWT = userInfoService.checkJWT(req);
    if (checkJWT) {
      return "成功";
    }
    return "失败";
  }
  @RequestMapping(value = "/register", method = RequestMethod.POST, produces = "text/plain;charset=UTF-8")
  @ResponseBody
  public String register(HttpServletRequest req,HttpServletResponse res,String name){
    // 打印请求头
    Enumeration<?> enum1 = req.getHeaderNames();
    while (enum1.hasMoreElements()) {
      String key = (String) enum1.nextElement();
      String value = req.getHeader(key);
      System.out.println(key + "\t" + value);
    }
    // 取出jwt中的加密信息,与数据库比对,看是否正确
    Boolean aBoolean = userInfoService.checkJWT(req);
    return aBoolean ? "成功" : "失败";
  }
}
