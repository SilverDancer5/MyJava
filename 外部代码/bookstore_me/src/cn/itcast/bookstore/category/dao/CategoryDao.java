package cn.itcast.bookstore.category.dao;

import java.sql.SQLException;
import java.util.List;

import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;
import org.apache.commons.dbutils.handlers.BeanListHandler;
import org.apache.commons.dbutils.handlers.ScalarHandler;

import cn.itcast.bookstore.category.domain.Category;
import cn.itcast.jdbc.TxQueryRunner;

public class CategoryDao {
  private QueryRunner qr=new TxQueryRunner();
  public List<Category> findAll(){
	  String sql="select * from category";
	  try {
		return qr.query(sql, new BeanListHandler<Category>(Category.class));
	} catch (SQLException e) {
		e.printStackTrace();
		throw new RuntimeException(e);
	}
  }
  public Category findByCname(String cname){
	  try{
		  String sql="select * from category where cname=?";
		  return qr.query(sql, new BeanHandler<Category>(Category.class),cname);
	  }catch(SQLException e){
		  e.printStackTrace();
		  throw new RuntimeException(e);
	  }
  }
  public int getTotal(){
	  try{
		  String sql="select count(*) from category";
		   Number number= (Number)qr.query(sql, new ScalarHandler());
		   return number.intValue();
	  }catch(SQLException e){
		  e.printStackTrace();
		  throw new RuntimeException(e);
	  }
  }
  public void add(Category category){
	  try{
		  String sql="insert  into category values(?,?)";
	      qr.update(sql, category.getCid(),category.getCname());
	  }catch(SQLException e){
		  e.printStackTrace();
		  throw new RuntimeException(e);
	  }
  }
  public int findBooksByCid(String cid){
	  try{
		  String sql="select count(*) from book where cid=?";
		   Number number=(Number) qr.query(sql, new ScalarHandler(),cid);
		   return number.intValue();
	  }catch(SQLException e){
		  e.printStackTrace();
		  throw new RuntimeException(e);
	  }
  }
  public void delete(String cid){
	  try{
		  String sql="delete from category where cid=?";
	      qr.update(sql, cid);
	  }catch(SQLException e){
		  e.printStackTrace();
		  throw new RuntimeException(e);
	  } 
  }
public Category findByCid(String cid) {
	try{
		  String sql="select * from category where cid=?";
		  return qr.query(sql, new BeanHandler<Category>(Category.class),cid);
	  }catch(SQLException e){
		  e.printStackTrace();
		  throw new RuntimeException(e);
	  }
}
public void edit(String cname, java.lang.String cid) {
	try{
		  String sql="update category set cname=? where cid=?";
	      qr.update(sql, cname,cid);
	  }catch(SQLException e){
		  e.printStackTrace();
		  throw new RuntimeException(e);
	  } 
}
}
