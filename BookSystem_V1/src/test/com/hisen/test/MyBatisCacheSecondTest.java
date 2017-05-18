package com.hisen.test;

import com.hisen.entity.Book;
import com.hisen.service.BookService;
import java.util.List;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * redis缓存测试类
 * Created by hisenyuan on 2017/5/18 at 16:10.
 */
public class MyBatisCacheSecondTest extends BookDaoTest {

  @Autowired
  private BookService bookService;

  @Test
  public void testCache() {
    //查询两次，第二次就直接在redis取出
    List<Book> list = bookService.getList(0, 10);
    System.out.println(list);
    List<Book> list1 = bookService.getList(0, 10);
    System.out.println(list1);
  }
  /**
   * 提醒：在控制台搜索：Cache Hit Ratio 即可看到数据
   * 第一次查询
   * 17:00:38.946 [main] DEBUG com.hisen.dao.BookDao - Cache Hit Ratio [com.hisen.dao.BookDao]: 0.0
   * 第二次查询
   * 17:02:06.453 [main] DEBUG com.hisen.dao.BookDao - Cache Hit Ratio [com.hisen.dao.BookDao]: 1.0
   */
}
