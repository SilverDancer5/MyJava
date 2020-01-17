package cn.itcast.bookstore.order.service;

import java.sql.SQLException;
import java.util.List;

import cn.itcast.bookstore.order.dao.OrderDao;
import cn.itcast.bookstore.order.domain.Order;
import cn.itcast.jdbc.JdbcUtils;
public class OrderService {
 private OrderDao orderDao=new OrderDao();
 public void add(Order order){
	 
	 try{
		 JdbcUtils.beginTransaction();
		 orderDao.addOrder(order);
		 orderDao.addOrderItems(order.getOrderItemList());
		 JdbcUtils.commitTransaction();
	 }catch(Exception e){
		 try {
			JdbcUtils.rollbackTransaction();
		} catch (SQLException e1) {
			e1.printStackTrace();
			throw new RuntimeException(e);
		}
	 }
 }
public List<Order> myOrders(String uid) {
	return orderDao.myOrders(uid);
}
public Order load(String oid) {
	return orderDao.load(oid);
}
public void confirm(String oid) throws OrderException{
	if(orderDao.getStateByOid(oid)!=3) throw new OrderException("订单确认失败，你不是好人");
	orderDao.update(oid, 4);
}
public void zhiFu(String oid) {
	int state=orderDao.getStateByOid(oid);
	if(state==1){
		//可在这里进行积分添加操作，避免有人恶意刷积分
		orderDao.update(oid, 2);
	}
}
}
