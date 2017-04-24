package com.hisen.dao;

import com.hisen.entity.Book;
import java.util.List;
import org.apache.ibatis.annotations.Param;

/**
 * Created by hisen on 17-4-24.
 */
public interface BookDao {
  Book queryById(long id);
  List<Book> queryAll(@Param("offset") int offset, @Param("limit") int limit);
  int addBook(Book book);
  int updateBook(Book book);
  int deleteBookById(long id);
}
