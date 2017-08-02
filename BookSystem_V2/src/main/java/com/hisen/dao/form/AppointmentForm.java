package com.hisen.dao.form;

import com.hisen.entity.Appointment;

/**
 * Created by hisenyuan on 2017/8/2 at 10:54.
 */
public class AppointmentForm extends Appointment {
  private String holdDay;

  public String getHoldDay() {
    return holdDay;
  }

  public void setHoldDay(String holdDay) {
    this.holdDay = holdDay;
  }
}
