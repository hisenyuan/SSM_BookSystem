package com.hisen.service.impl;

import static com.google.common.base.Preconditions.checkNotNull;

import com.hisen.dao.AppointmentMapper;
import com.hisen.dao.BookDao;
import com.hisen.dao.form.AppointmentForm;
import com.hisen.entity.Book;
import com.hisen.service.AppointmentService;
import org.joda.time.DateTime;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Created by hisenyuan on 2017/8/2 at 10:36.
 */
@Service
public class AppointmentServiceImpl implements AppointmentService {

  @Autowired
  private AppointmentMapper appointmentMapper;
  @Autowired
  private BookDao bookDao;

  /**
   * 预约图书
   */
  //如果发生了异常，就进行回滚
  @Transactional(rollbackFor = {Exception.class})
  public int appoint(AppointmentForm record) {
    //利用google guava判空
    checkNotNull(record.getUserNumber(), "用户号不能为空");
    checkNotNull(record.getBookId(), "图书编号不能为空");
    //利用joda-time生成时间，并且计算时间
    DateTime dt = new DateTime();
    record.setAppointmentTime(dt.toDate());
    //预计持有时间
    int holdDay = Integer.valueOf(record.getHoldDay());
    //利用持有时间计算预计归还时间
    record.setExpectReturnTime(dt.plusDays(holdDay).toDate());
    Book book = bookDao.queryById(record.getBookId());
    int num = book.getNumber();
    int insert = 0;
    if (num >= 1) {
      insert = appointmentMapper.insert(record);
      book.setNumber(num - 1);
      bookDao.updateBook(book);
//      checkNotNull(null, "出现异常，事物回滚。用来测试事物控制");
    }
    return insert;
  }
}
