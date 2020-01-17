package cn.itcast.bookstore.cart.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import cn.itcast.bookstore.book.dao.BookDao;
import cn.itcast.bookstore.book.domain.Book;
import cn.itcast.bookstore.cart.domain.Cart;
import cn.itcast.bookstore.cart.domain.CartItem;
import cn.itcast.servlet.BaseServlet;

public class CartServlet extends BaseServlet {
	public String  addItem(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		CartItem item=new CartItem();
		String bid=request.getParameter("bid");
		Book book=new BookDao().findByBid(bid);
	    item.setBook(book);
	    item.setCount(Integer.parseInt(request.getParameter("count")));
	    Cart userCart=(Cart)request.getSession().getAttribute("cart");
	    userCart.add(item);
	    request.setAttribute("book",book);
	    request.getSession().setAttribute("cart", userCart);
	    return "f:/jsps/book/desc.jsp";
	}
    public String deleteItem(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
    	CartItem item=new CartItem();
		String bid=request.getParameter("bid");
		Book book=new Book();
		book.setBid(bid);
	    item.setBook(book);//这里的item只需要bid信息，因为是删除，而且也不必重定向到desc.jsp,就不用保存整个book
	    Cart userCart=(Cart)request.getSession().getAttribute("cart");
	    userCart.delete(item);
	    request.getSession().setAttribute("cart", userCart);
       return "f:/jsps/cart/list.jsp";
    }
    public String clear(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
    	 Cart userCart=(Cart)request.getSession().getAttribute("cart");
    	 userCart.clear();
    	return "f:/jsps/cart/list.jsp";
    }
}
