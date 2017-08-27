package com.hisen.service;

import com.hisen.bean.request.UserLoginRequest;
import com.hisen.bean.response.UserLoginResponse;
import javax.servlet.http.HttpServletRequest;

/**
 * Created by hisen on 17-8-26.
 * E-mail: hisenyuan@gmail.com
 */
public interface UserInfoService {
  public UserLoginResponse login(UserLoginRequest request);
  public Boolean checkJWT(HttpServletRequest req);
}
