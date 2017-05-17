package com.hisen.test;

import com.hisen.entity.Book;
import com.hisen.service.BookService;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * Created by hisenyuan on 2017/5/3 at 20:16.
 */
public class BookServiceImplTest extends BookDaoTest {
  @Autowired
  private BookService bookService;

  @Test
  public void getById() {
    //在service里面改了一下名字，其实就是dao里面的queryById
    Book book = bookService.getById(101);
    System.out.println(book);
  }
  @Test
  public void countNum(){
    int i = bookService.countNum();
    System.out.println(i);
  }
}
