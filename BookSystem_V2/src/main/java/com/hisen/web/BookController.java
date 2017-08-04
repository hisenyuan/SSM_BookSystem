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

  @RequestMapping(value = "/list", method = RequestMethod.GET)
  private String list(Model model) {
    List<Book> list = bookService.getList(0, 10);
    model.addAttribute("books", list);
    return "list";// WEB-INF/jsp/"list".jsp
  }

  @RequestMapping(value = "/detail/{bookId}", method = RequestMethod.GET)
  private String detail(@PathVariable("bookId") Long bookId, Model model) {
    Book book = bookService.getById(bookId);
    model.addAttribute("book", book);
    return "detail";
  }

  @RequestMapping(value = "/add", method = RequestMethod.POST, produces = "text/plain;charset=UTF-8")
  @ResponseBody
  private String add(Book book) {
    Book hasBook = bookService.getById(book.getBookId());
    int i = -2;
    if (hasBook == null) {
      i = bookService.addBook(book);
    }
    return i > 0 ? "success" : "error";
  }

  @RequestMapping(value = "/del/{bookId}", method = RequestMethod.GET)
  @ResponseBody
  private String deleteBookById(@PathVariable("bookId") Long id) {
    int i = bookService.deleteBookById(id);
    return i > 0 ? "success" : "error";
  }

  /**
   * 查询总页数
   * @return
   */
  @RequestMapping(value = "/countNum", method = RequestMethod.POST, produces = {
      "application/json; charset=utf-8"})
  @ResponseBody
  private int countNum() {
    int num = bookService.countNum();
    //计算页数，如果除以10有余数，得加上一页
    int countNum = num / 10 + ((num % 10) > 0 ? 1 : 0);
    return countNum;
  }

  /**
   * 分页查询方法
   * @param start
   * @return
   */
  @RequestMapping(value = "/listpage", method = RequestMethod.POST)
  @ResponseBody
  private String listPage(@RequestParam("start") int start) {
    //默认一页10条
    List<Book> list = bookService.getList(start, 10);
    //阿里fastjson把数组转换为json
    String s = JSON.toJSONString(list);
    //System.out.println(s);
    return s;
  }

  @RequestMapping(value = "/listpageplug/{start}", method = RequestMethod.GET)
//  @ResponseBody
  private String listPagePlug(@PathVariable("start") String start, Model model) {
    PageHelper.startPage(Integer.valueOf(start), 20);
    //默认一页10条
    List<Book> readingList = bookService.getListPlug();
    model.addAttribute("books", readingList);
    //System.out.println(s);
    return "readingList";
  }
}
