package cn.itcast.bookstore.cart.domain;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.Collection;
import java.util.LinkedHashMap;
import java.util.Map;

public class Cart {
  Map<String,CartItem> map=new LinkedHashMap<String,CartItem>();//遍历时是有顺序的
  public double getTotal(){
	  BigDecimal total=new BigDecimal(""+0);
	  for (CartItem item : map.values()) {
		BigDecimal subTotal=new BigDecimal(item.getSubTotal()+"");
		total=total.add(subTotal);
	}
	  return total.doubleValue();
  }
  public void add(CartItem item){
	  CartItem oldItem=map.get(item.getBook().getBid());
	  if(oldItem==null){
		  map.put(item.getBook().getBid(),item);
	  }else{
		  oldItem.setCount(item.getCount()+oldItem.getCount());//引用，所以不用再 往里面put
	  }
  }
  public void clear(){
	  map.clear();
  }
  public void delete(CartItem item){
	  map.remove(item.getBook().getBid());
  }
  public Collection<CartItem> getCartItems(){
	  return map.values();
  }
}
