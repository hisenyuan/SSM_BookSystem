package com.hisen.controller;

import com.alibaba.fastjson.JSON;
import com.hisen.bean.request.AppointmentRequest;
import com.hisen.bean.entity.Appointment;
import com.hisen.service.AppointmentService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * Created by hisenyuan on 2017/8/2 at 10:27.
 */
@Controller
@RequestMapping("/appointment")
public class AppointmengtController {

  private Logger logger = LoggerFactory.getLogger(this.getClass());
  @Autowired
  private AppointmentService appointmentService;

  /**
   * 借书的一个小功能
   * 本地测试成功，暂时不想写页面
   * localhost:8080/V2/appointment/appoint/103/20080808/20
   */
  @RequestMapping(value = "/appoint/{bookId}/{userNumber}/{holdDay}", method = RequestMethod.GET, produces = "text/plain;charset=UTF-8")
  @ResponseBody
  public String appoint(@PathVariable("bookId") int bookId,
      @PathVariable("userNumber") int userNumber, @PathVariable("holdDay") String holdDay) {
    AppointmentRequest form = new AppointmentRequest();
    form.setBookId(bookId);
    form.setUserNumber(userNumber);
    form.setHoldDay(holdDay);
    logger.info("借书入参 AppointmengtController >>>>> " + form.toString());
    int appoint = appointmentService.appoint(form);
    String s = JSON.toJSONString(appoint > 0 ? "借书成功" : "借书失败");
    return s;
  }

  @RequestMapping(value = "/return/{bookId}/{userNumber}", method = RequestMethod.GET, produces = "text/plain;charset=UTF-8")
  @ResponseBody
  public String returnBook(@PathVariable("bookId") int bookId,
      @PathVariable("userNumber") int userNumber) {
    Appointment form = new Appointment();
    form.setBookId(bookId);
    form.setUserNumber(userNumber);
    logger.info("还书书入参 AppointmengtController >>>>> " + form.toString());
    int i = appointmentService.returnBook(form);
    String s = JSON.toJSONString(i > 0 ? "还书成功" : "还书失败");
    return s;
  }
}
