package cn.itcast.bookstore.order.dao;

import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;
import org.apache.commons.dbutils.handlers.BeanListHandler;
import org.apache.commons.dbutils.handlers.MapListHandler;
import org.apache.commons.dbutils.handlers.ScalarHandler;

import cn.itcast.bookstore.book.domain.Book;
import cn.itcast.bookstore.order.domain.Order;
import cn.itcast.bookstore.order.domain.OrderItem;
import cn.itcast.commons.CommonUtils;
import cn.itcast.jdbc.TxQueryRunner;

public class OrderDao {
 private QueryRunner qr=new TxQueryRunner();
 public void addOrder(Order order){
	 try{
		 System.out.println("dao.........");
		 Timestamp time=new Timestamp(order.getOrdertime().getTime());
		 String sql="insert  into orders values(?,?,?,?,?,?)";
		 Object[] params={order.getOid(),time,order.getTotal()
				 ,order.getState(),order.getOwner().getUid(),order.getAddress()};
		 qr.update(sql, params);
	 }catch(SQLException e){
		 e.printStackTrace();
		 throw new RuntimeException(e);
	 }
 }
 public void addOrderItems(List<OrderItem> items){
	 try{
		 String sql="insert  into orderItem values(?,?,?,?,?)";
		 Object[][] params=new Object[items.size()][];
		 for(int i=0;i<items.size();i++){
			 OrderItem item=items.get(i);
			 params[i]=new Object[]{item.getIid(),item.getCount(),item.getSubtotal(),
					                item.getOrder().getOid(),item.getBook().getBid()};
		 }
		 qr.batch(sql, params);
	 }catch(SQLException e){
		 e.printStackTrace();
		 throw new RuntimeException(e);
	 }
 }
public List<Order> myOrders(String uid) {
	try{
		String sql="select * from orders where uid=?";
		List<Order> orders=qr.query(sql, new BeanListHandler<Order>(Order.class), uid);
		for(Order order:orders){
			addOrderItems(order);
		}
		return orders;
	}catch(SQLException e){
		e.printStackTrace();
		throw new RuntimeException(e);
	}
}
public void addOrderItems(Order order){
	try{
		String sql="select * from orderItem o,book b where o.bid=b.bid and o.oid=?";
		List<Map<String,Object>> mapList=qr.query(sql, new MapListHandler(),order.getOid());
		List<OrderItem> items=toOrderItemList(mapList);
		order.setOrderItemList(items);
	}catch(SQLException e){
		e.printStackTrace();
		throw new RuntimeException(e);
	}
}
private List<OrderItem> toOrderItemList(List<Map<String, Object>> mapList) {
	List<OrderItem> items=new ArrayList<OrderItem>();
	for (Map<String, Object> map : mapList) {
		Book book=CommonUtils.toBean(map, Book.class);
		OrderItem item=CommonUtils.toBean(map, OrderItem.class);
		item.setBook(book);
		items.add(item);
	}
	return items;
}
public Order load(String oid) {//由myOrders方法改过来
	try{
		String sql="select * from orders where oid=?";
		Order order=qr.query(sql, new BeanHandler<Order>(Order.class), oid);
		addOrderItems(order);
		return order;
	}catch(SQLException e){
		e.printStackTrace();
		throw new RuntimeException(e);
	}
}
public int getStateByOid(String oid){
	try{
		String sql="select state from orders where oid=?";
		Number number=(Number) qr.query(sql, new ScalarHandler(), oid);
		return number.intValue();
	}catch(Exception e){
		e.printStackTrace();
		throw new RuntimeException(e);
	}
}
public void update(String oid,int state){
	try{
		String sql="update orders set state=? where oid=?";
		qr.update(sql, state,oid);
	}catch(SQLException e){
		e.printStackTrace();
		throw new RuntimeException(e);
	}
}
}
