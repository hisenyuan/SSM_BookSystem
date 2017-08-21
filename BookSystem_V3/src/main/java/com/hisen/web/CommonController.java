package com.hisen.web;

import com.hisen.entity.User;
import com.hisen.service.impl.CommonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * Created by hisen on 17-8-21.
 * E-mail: hisenyuan@gmail.com
 */
@Controller
@RequestMapping("/common")
public class CommonController {
  @Autowired
  CommonService commonService;

  @RequestMapping(value = "/login",method = RequestMethod.POST,produces = "text/plain;charset=UTF-8")
  @ResponseBody
  public int login(User user){
    if (user != null) {
      int login = commonService.login(user);
      if (login>0){
        return 1;
        //TODO 生成token、返回commonData对象
      }
    }
    return 0;
  }
}
