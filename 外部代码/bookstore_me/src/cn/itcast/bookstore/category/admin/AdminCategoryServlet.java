package cn.itcast.bookstore.category.admin;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import cn.itcast.bookstore.category.domain.Category;
import cn.itcast.bookstore.category.service.CategoryException;
import cn.itcast.bookstore.category.service.CategoryService;
import cn.itcast.commons.CommonUtils;
import cn.itcast.servlet.BaseServlet;

public class AdminCategoryServlet extends BaseServlet {
   private static final String String = null;
private	CategoryService categoryService=new CategoryService();
public String findAll(HttpServletRequest request, HttpServletResponse response)
		throws ServletException, IOException {
	List<Category> list=categoryService.findAll();
	request.setAttribute("categoryList", list);
	return "f:/adminjsps/admin/category/list.jsp";
}
public String add(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
	String cname=request.getParameter("cname");
	if(cname==null||cname.trim().length()==0){
		request.setAttribute("msg","类名不能为空");
		return "f:/adminjsps/admin/category/add.jsp";
	}else if(cname.length()<3||cname.length()>10){
		request.setAttribute("msg","类名长度不对");
		return "f:/adminjsps/admin/category/add.jsp";
	}
	try {
		categoryService.add(cname);
	} catch (CategoryException e) {
	    request.setAttribute("msg", "添加失败，该类名已经存在");
	    return "f:/adminjsps/admin/category/add.jsp";
	}
	return findAll(request,response);
}
public String delete(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
	String cid=request.getParameter("cid");
	try {
		categoryService.delete(cid);
	} catch (CategoryException e) {
		request.setAttribute("msg", e.getMessage());
		return "f:/adminjsps/msg.jsp";
	}
	return findAll(request,response);
}
public String toEdit(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
	String cid=request.getParameter("cid");
	try {
		Category category=categoryService.findByCid(cid);
		request.setAttribute("category",category);
	} catch (CategoryException e) {
		throw new RuntimeException(e);
	}
	return "f:/adminjsps/admin/category/mod.jsp";
}
public String edit(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
	Category category=CommonUtils.toBean(request.getParameterMap(),Category.class);
	String cname=category.getCname();
	if(cname==null||cname.trim().length()==0){
		request.setAttribute("msg", "类别名不能为空");
		return "f:/adminjsps/admin/category/mod.jsp";
	}else if(cname.length()<3||cname.length()>10){
		request.setAttribute("msg", "类别名长度格式不对");
		return "f:/adminjsps/admin/category/mod.jsp";
	}
	categoryService.edit(category);
	return findAll(request,response);
}
}
