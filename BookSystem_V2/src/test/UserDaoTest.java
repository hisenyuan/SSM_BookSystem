import com.hisen.dao.UserExample;
import com.hisen.dao.UserExample.Criteria;
import com.hisen.dao.UserMapper;
import com.hisen.entity.User;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * Created by hisenyuan on 2017/8/2 at 11:18.
 */
public class UserDaoTest extends BaseTest {
  @Autowired
  private UserMapper userMapper;

  /**
   * 测试添加用户
   */
  @Test
  public void insert(){
    User u = new User();
    u.setUserName("阿星");
    u.setUserAddress("北京市朝阳区");
    u.setUserPhone("13820080001");
    u.setUserNumber("20080001");
    int insert = userMapper.insert(u);
    System.out.println(insert);
  }
}
