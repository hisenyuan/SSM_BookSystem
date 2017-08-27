package com.hisen.filter;

import java.io.IOException;
import java.util.Collection;
import java.util.Enumeration;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

/**
 * CorsFileter 功能描述：CORS过滤器
 * 允许跨域访问
 * Created by hisen on 17-8-27.
 * E-mail: hisenyuan@gmail.com
 */
@Component
public class CorsFilter implements Filter {

  private Logger log = LoggerFactory.getLogger(this.getClass());

  public void init(FilterConfig filterConfig) throws ServletException {

  }

  public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse,
      FilterChain filterChain) throws IOException, ServletException {
    HttpServletRequest request = (HttpServletRequest) servletRequest;
    HttpServletResponse response = (HttpServletResponse) servletResponse;
    Enumeration<String> enum1 = request.getHeaderNames();
    log.info("before Filter Header is:");
    while (enum1.hasMoreElements()) {
      String key = (String) enum1.nextElement();
      String value = request.getHeader(key);
      log.info("{},{}",key,value);
    }
    // 设置允许访问的域名
    response.setHeader("Access-Control-Allow-Origin", "*");
    // 设置允许的方式
    response.setHeader("Access-Control-Allow-Methods", "POST, GET, OPTIONS, DELETE");
    // 设置preflight请求的结果能够被缓存多久
    response.setHeader("Access-Control-Max-Age", "3600");
    // 设置允许的header类型
    response.setHeader("Access-Control-Allow-Headers", "x-requested-with,Authorization,name");
    // 设置为true时允许浏览器读取response的内容
    response.setHeader("Access-Control-Allow-Credentials", "true");

    Collection<String> names = response.getHeaderNames();
    log.info("after Filter Header is:");
    for (String name:names) {
      String value = response.getHeader(name);
      log.info("{},{}",name,value);
    }
    filterChain.doFilter(servletRequest, servletResponse);
  }

  public void destroy() {

  }
}
