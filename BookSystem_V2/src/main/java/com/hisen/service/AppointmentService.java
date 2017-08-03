package com.hisen.service;

import com.hisen.dao.form.AppointmentForm;
import com.hisen.entity.Appointment;

/**
 * Created by hisenyuan on 2017/8/2 at 10:36.
 */
public interface AppointmentService {
  int appoint(AppointmentForm record);
  int returnBook(Appointment record);
}
