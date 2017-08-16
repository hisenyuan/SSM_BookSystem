package com.hisen.service;

import com.hisen.entity.Book;
import java.util.List;

/**
 * Created by hisen on 17-4-24.
 */
public interface BookService {

  /**
   * 根据图书id查询图书信息
   * @param bookId 图书id
   * @return 图书信息
   */
  Book getById(long bookId);

  /**
   * 查询图书列表
   * @param start 开始的位置
   * @param pageNum 每次查询的次数
   * @return 图书信息列表
   */
  List<Book> getList(int start, int pageNum);

  /**
   * 添加图书信息
   * @param book 图书对象
   * @return
   */
  int addBook(Book book);

  /**
   * 修改图书信息
   * @param book 图书对象
   * @return
   */
  int updateBook(Book book);

  /**
   * 删除图书 - 根据图书id
   * @param id 图书id
   * @return
   */
  int deleteBookById(long id);

  /**
   * 通过分页插件查询
   * @return
   */
  List<Book> getListPlug();
}
