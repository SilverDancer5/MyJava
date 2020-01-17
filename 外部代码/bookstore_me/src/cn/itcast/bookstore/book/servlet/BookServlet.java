package cn.itcast.bookstore.book.servlet;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import cn.itcast.bookstore.book.domain.Book;
import cn.itcast.bookstore.book.service.BookService;
import cn.itcast.servlet.BaseServlet;

public class BookServlet extends BaseServlet {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private BookService bookService=new BookService();
	public String  findAll(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
	     List<Book> list=bookService.findAll();
	     request.setAttribute("bookList", list);
	     return "f:/jsps/book/list.jsp";
	}
	public String  findByCid(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String cid=request.getParameter("cid");
		List<Book> list=bookService.findByCid(cid);
	     request.setAttribute("bookList", list);
	     return "f:/jsps/book/list.jsp";
	}
	public String  findByBid(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String bid=request.getParameter("bid");
		Book book=bookService.findByBid(bid);
	     request.setAttribute("book", book);
	     return "f:/jsps/book/desc.jsp";
	}
}
