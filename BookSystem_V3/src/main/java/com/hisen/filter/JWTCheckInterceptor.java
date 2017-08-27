package com.hisen.filter;

import com.alibaba.fastjson.JSON;
import com.hisen.bean.contains.CommonEnum;
import com.hisen.bean.entity.JWTInfo;
import com.hisen.bean.response.CommonResponse;
import com.hisen.common.JWTUtil;
import java.io.PrintWriter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

/**
 * Created by hisen on 17-8-27.
 * E-mail: hisenyuan@gmail.com
 */
public class JWTCheckInterceptor implements HandlerInterceptor {

  private Logger log = LoggerFactory.getLogger(this.getClass());

  public boolean preHandle(HttpServletRequest request,
      HttpServletResponse response, Object o) throws Exception {
    response.setCharacterEncoding("utf-8");
    String jwt = request.getHeader("Authorization");
    String name = request.getHeader("name");
    log.info("JWTCheckInterceptor - jwt:{},name{}",jwt,name);
    if ("".equals(jwt) || "".equals(name) || null == jwt || null == name) {
      CommonResponse commonResponse = new CommonResponse();
      commonResponse.setResCode(CommonEnum.REQUEST_FAILED.getCode());
      commonResponse.setResMsg("非法操作");
      responseMessage(response, response.getWriter(), commonResponse);
      return false;
    }
    // 解密信息
    JWTInfo jwtInfo = JWTUtil.unsign(jwt, JWTInfo.class);
    log.info("jwt解密之后:{}",JSON.toJSONString(jwtInfo));
    if (jwtInfo == null) {
      CommonResponse commonResponse = new CommonResponse();
      commonResponse.setResCode(CommonEnum.REQUEST_FAILED.getCode());
      commonResponse.setResMsg("token超时,请重新登录");
      responseMessage(response, response.getWriter(), commonResponse);
      return false;
    }
    if (name.equals(jwtInfo.getUsername())) {
      return true;
    } else {
      CommonResponse commonResponse = new CommonResponse();
      commonResponse.setResCode(CommonEnum.REQUEST_FAILED.getCode());
      commonResponse.setResMsg("token校验不通过,请重新登录");
      responseMessage(response, response.getWriter(), commonResponse);
      return false;
    }
  }

  public void postHandle(HttpServletRequest request,
      HttpServletResponse response, Object o, ModelAndView modelAndView)
      throws Exception {
  }

  public void afterCompletion(HttpServletRequest httpServletRequest,
      HttpServletResponse httpServletResponse, Object o, Exception e) throws Exception {

  }

  //请求不通过，返回错误信息给客户端
  private void responseMessage(HttpServletResponse response, PrintWriter out,
      CommonResponse commonResponse) {
    response.setContentType("application/json; charset=utf-8");
    String json = JSON.toJSONString(commonResponse);
    out.print(json);
    out.flush();
    out.close();
  }
}
