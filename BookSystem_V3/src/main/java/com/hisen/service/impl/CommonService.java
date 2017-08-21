package com.hisen.service.impl;

import com.hisen.entity.User;

/**
 * Created by hisen on 17-8-21.
 * E-mail: hisenyuan@gmail.com
 */
public interface CommonService {

  /**
   * 登录
   * @param user
   * @return 返回符合条件的记录条数，为1的时候登录成功
   */
  public int login(User user);
}
