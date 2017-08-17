import com.hisen.entity.User;
import com.hisen.jwt.EncodeDecodeJWT;

/**
 * 生成token，以及解析token
 * Created by hisenyuan on 2017/8/17 at 14:18.
 */
public class TestToken {

  public static void main(String[] args) {
    String sign = sign();
    System.out.printf("jwt sign:%s\n", sign);
    User user = unsign(sign);
    System.out.printf("jwt unsign:%s\n", user);

  }

  /**
   * jwt - 生成token
   *
   * @return 返回token
   */
  public static String sign() {
    User user = new User();
    user.setUserId(6);
    user.setUserName("阿星");
    user.setUserPassword("123456");
    // 第一个参数可以是任何类型，解析的时候必须也是这个类型
    String token = EncodeDecodeJWT.sign(user, 60L * 1000L * 30L);
    return token;
  }

  /**
   * jwt - 解密token
   *
   * @param token jwt-token
   * @return 解密后还原的内容
   */
  public static User unsign(String token) {
    // 第二个参数是 EncodeDecodeJWT.sign 入参的类型学
    User user = EncodeDecodeJWT.unsign(token, User.class);
    return user;
  }
}
