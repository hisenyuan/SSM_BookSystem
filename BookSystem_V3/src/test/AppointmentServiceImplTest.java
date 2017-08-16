import com.hisen.dao.form.AppointmentForm;
import com.hisen.service.AppointmentService;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * Created by hisenyuan on 2017/8/2 at 11:03.
 */
public class AppointmentServiceImplTest extends BaseTest {

  @Autowired
  private AppointmentService appointmentService;

  /**
   * 测试预约功能
   */
  @Test
  public void insertApponit() {
    AppointmentForm form = new AppointmentForm();
    form.setHoldDay("10");
    form.setBookId(103);
    form.setUserNumber(20080808);
    int appoint = appointmentService.appoint(form);
    System.out.println(appoint);
  }
}
