package com.hisen.service.impl;

import com.hisen.dao.UserMapper;
import com.hisen.entity.User;
import com.hisen.entity.UserExample;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * Created by hisen on 17-8-21.
 * E-mail: hisenyuan@gmail.com
 */
public class CommonServiceImpl implements CommonService {
  @Autowired
  private UserMapper userMapper;

  public int login(User user) {
    // 一般来说，去除空格和替换引号可以避免sql注入(虽然本项目druid提供了防sql注入)
    String name = user.getUserName().trim().replace("'", "");
    String pwd = user.getUserPassword().trim().replace("'", "");
    UserExample example = new UserExample();
    example.createCriteria().andUserNameEqualTo(name).andUserPasswordEqualTo(pwd);
    return userMapper.countByExample(example);
  }
}
