package cn.itcast.bookstore.category.servlet;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import cn.itcast.bookstore.category.domain.Category;
import cn.itcast.bookstore.category.service.CategoryService;
import cn.itcast.servlet.BaseServlet;

public class CategoryServlet extends BaseServlet {
  private CategoryService categoryService=new CategoryService();

public String findAll(HttpServletRequest request, HttpServletResponse response)
		throws ServletException, IOException {
	  List<Category> categoryList=categoryService.findAll();
	  request.setAttribute("categoryList", categoryList);
      return "f:/jsps/left.jsp";	
}
}
