package com.hisen.bean.dto;

/**
 * Created by hisen on 17-8-26.
 * E-mail: hisenyuan@gmail.com
 */
public class UserInfoLoginDto {

  private String name;
  private String pwd;
  private Integer userState;
  private Integer userType;

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

  public Integer getUserState() {
    return userState;
  }

  public void setUserState(Integer userState) {
    this.userState = userState;
  }

  public Integer getUserType() {
    return userType;
  }

  public void setUserType(Integer userType) {
    this.userType = userType;
  }
}
