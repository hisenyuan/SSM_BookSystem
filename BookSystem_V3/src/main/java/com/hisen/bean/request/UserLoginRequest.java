package com.hisen.bean.request;

/**
 * Created by hisen on 17-8-26.
 * E-mail: hisenyuan@gmail.com
 */
public class UserLoginRequest {
  private String name;
  private String pwd;

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public String getPwd() {
    return pwd;
  }

  public void setPwd(String pwd) {
    this.pwd = pwd;
  }

  @Override
  public String toString() {
    return "UserLoginRequest{" +
        "name='" + name + '\'' +
        ", pwd='" + pwd + '\'' +
        '}';
  }
}
