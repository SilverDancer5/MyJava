package cn.itcast.bookstore.book.service;

import java.util.List;

import cn.itcast.bookstore.book.dao.BookDao;
import cn.itcast.bookstore.book.domain.Book;

public class BookService {
private BookDao bookDao=new BookDao();
 public List<Book> findAll(){
	 return bookDao.findAll();
 }
 public List<Book> findByCid(String cid){
	 return bookDao.findByCid(cid);
 }
 public Book findByBid(String bid){
	 return bookDao.findByBid(bid);
 }
}
