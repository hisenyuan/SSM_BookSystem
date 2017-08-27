package com.hisen.common;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 * Created by hisen on 17-8-26.
 * E-mail: hisenyuan@gmail.com
 */
public class MD5Util {

  public static String toMd5(String str) {
    StringBuffer md5Code = new StringBuffer();
    try {
      //获取加密方式为md5的算法对象
      MessageDigest instance = MessageDigest.getInstance("MD5");
      byte[] digest = instance.digest(str.getBytes());
      for (byte b : digest) {
        String hexString = Integer.toHexString(b & 0xff);
        if (hexString.length() < 2) {
          hexString = "0" + hexString;
        }
        md5Code = md5Code.append(b);
      }
    } catch (NoSuchAlgorithmException e) {
      e.printStackTrace();
    }
    return md5Code.toString();
  }

  public static void main(String[] args) {
    System.out.println(toMd5("hisen"));
  }
}
