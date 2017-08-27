package com.hisen.bean.request;

import com.hisen.bean.entity.Appointment;

/**
 * Created by hisen on 17-8-27.
 * E-mail: hisenyuan@gmail.com
 */
public class AppointmentRequest extends Appointment{
  private String holdDay;

  public String getHoldDay() {
    return holdDay;
  }

  public void setHoldDay(String holdDay) {
    this.holdDay = holdDay;
  }

  @Override
  public String toString() {
    return "AppointmentRequest{" +
        "holdDay='" + holdDay + '\'' +
        '}';
  }
}
