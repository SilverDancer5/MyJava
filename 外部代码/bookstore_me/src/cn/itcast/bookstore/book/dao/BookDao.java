package cn.itcast.bookstore.book.dao;

import java.sql.SQLException;
import java.util.List;

import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;
import org.apache.commons.dbutils.handlers.BeanListHandler;

import cn.itcast.bookstore.book.domain.Book;
import cn.itcast.jdbc.TxQueryRunner;

public class BookDao {
 private QueryRunner qr=new TxQueryRunner();
 public List<Book> findAll() {
	try{ 
		String sql="select * from book";
		return qr.query(sql, new BeanListHandler<Book>(Book.class));
	}catch(SQLException e){
		e.printStackTrace();
		throw new RuntimeException(e);
	}
}
 public List<Book> findByCid(String cid) {
		try{
			String sql="select * from book where cid=?";
			return qr.query(sql, new BeanListHandler<Book>(Book.class),cid);
		}catch(SQLException e){
			e.printStackTrace();
			throw new RuntimeException(e);
		}
	}
 public Book findByBid(String bid) {
		try{
			String sql="select * from book where bid=?";
			return qr.query(sql, new BeanHandler<Book>(Book.class),bid);
		}catch(SQLException e){
			e.printStackTrace();
			throw new RuntimeException(e);
		}
	}
}
