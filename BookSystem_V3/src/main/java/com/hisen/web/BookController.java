package com.hisen.web;

import com.alibaba.fastjson.JSON;
import com.github.pagehelper.PageHelper;
import com.hisen.entity.Book;
import com.hisen.service.BookService;
import java.util.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * Created by hisen on 17-4-24.
 */
@Controller
@RequestMapping("/book")
public class BookController {
  private Logger logger = LoggerFactory.getLogger(this.getClass());

  @Autowired
  private BookService bookService;

  /**
   * 查询列表 - 默认查询 0-10 的数据
   * @return
   */
  @RequestMapping(value = "/list", method = RequestMethod.GET)
  @ResponseBody //以json的形式返回对象
  private List<Book> list() {
    List<Book> list = bookService.getList(0, 10);
    return list;
  }

  /**
   * 图书的详细信息
   * @param bookId 图书id
   * @return
   */
  @RequestMapping(value = "/detail/{bookId}", method = RequestMethod.GET)
  @ResponseBody
  private Book detail(@PathVariable("bookId") Long bookId) {
    Book book = bookService.getById(bookId);
    return book;
  }

  /**
   * 添加图书的方法
   * @param book
   * @return
   */
  @RequestMapping(value = "/add", method = RequestMethod.POST, produces = "text/plain;charset=UTF-8")
  @ResponseBody
  private int add(Book book) {
    Book hasBook = bookService.getById(book.getBookId());
    int i = -2;
    if (hasBook == null) {
      i = bookService.addBook(book);
    }
    return i;
  }

  /**
   * 删除图书
   * @param id 图书编号
   * @return
   */
  @RequestMapping(value = "/del/{bookId}", method = RequestMethod.GET)
  @ResponseBody
  private String deleteBookById(@PathVariable("bookId") Long id) {
    int i = bookService.deleteBookById(id);
    return i > 0 ? "success" : "error";
  }

  /**
   * 利用分页插件查询
   * @param start
   * @return
   */
  @RequestMapping(value = "/list/{start}", method = RequestMethod.GET)
  @ResponseBody
  private List<Book> listPagePlug(@PathVariable("start") String start) {
    PageHelper.startPage(Integer.valueOf(start), 20);
    //默认一页10条
    List<Book> readingList = bookService.getListPlug();
    return readingList;
  }
}
