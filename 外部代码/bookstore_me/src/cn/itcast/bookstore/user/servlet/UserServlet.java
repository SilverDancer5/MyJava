package cn.itcast.bookstore.user.servlet;


import java.io.IOException;
import java.text.MessageFormat;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

import javax.mail.MessagingException;
import javax.mail.Session;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import cn.itcast.bookstore.cart.domain.Cart;
import cn.itcast.bookstore.user.domain.User;
import cn.itcast.bookstore.user.service.UserException;
import cn.itcast.bookstore.user.service.UserService;
import cn.itcast.commons.CommonUtils;
import cn.itcast.mail.Mail;
import cn.itcast.mail.MailUtils;
import cn.itcast.servlet.BaseServlet;

public class UserServlet extends BaseServlet {
 private UserService userService=new UserService();

public String regist(HttpServletRequest request, HttpServletResponse response)
		throws ServletException, IOException, MessagingException {
	User form=CommonUtils.toBean(request.getParameterMap(), User.class);
	String username=form.getUsername();
	Map<String,String> map=new HashMap<String,String>();
	if(username==null||username.trim().length()==0){
		map.put("username", "用户名不能为空");
	}else if(username.length()<3||username.length()>10){
		map.put("username", "用户名长度最大为10最小为3");
	}
	String password=form.getPassword();
	if(password==null||password.trim().length()==0){
		map.put("password", "密码不能为空");
	}else if(password.length()<3||password.length()>10){
		map.put("password", "密码长度最大为10最小为3");
	}
	String email=form.getEmail();
	if(email==null||email.trim().length()==0){
		map.put("email", "邮箱不能为空");
	}else if(!email.matches("\\w+@\\w+\\.\\w+")){
		map.put("email", "邮箱格式不对");
	}
	if(map.size()!=0){
		request.setAttribute("errors", map);
		request.setAttribute("form", form);
		return "f:/jsps/user/regist.jsp";
	}

	form.setUid(CommonUtils.uuid());
    form.setCode(CommonUtils.uuid()+CommonUtils.uuid());
	try {
		userService.regist(form);
		Properties props=new Properties();
		props.load(this.getClass().getClassLoader().getResourceAsStream("email_template.properties"));
		String user=props.getProperty("username");
		String pwd=props.getProperty("password");
		String host=props.getProperty("host");
		String subject=props.getProperty("subject");
		String content=props.getProperty("content");
		String from=props.getProperty("from");
		String to=form.getEmail();
		content=MessageFormat.format(content,form.getCode() );
		Session session=MailUtils.createSession(host, user, pwd);
		Mail mail=new Mail(from, to, subject, content);
		MailUtils.send(session, mail);
		request.setAttribute("msg", "恭喜你，注册成功！请去邮箱激活用户身份");
		return "f:/jsps/msg.jsp";
	} catch (UserException e) {
		request.setAttribute("msg", e.getMessage());
		return "f:/jsps/user/regist.jsp";
	}
}
	public String active(HttpServletRequest request, HttpServletResponse response) throws IOException{
		String code=request.getParameter("code");
		try {
			userService.active(code);
		} catch (UserException e) {
		   request.setAttribute("msg", e.getMessage());
		   return "f:/jsps/msg.jsp";
		}
		request.setAttribute("msg", "恭喜你，激活成功");
		return "f:/jsps/msg.jsp";
	}
	public String login(HttpServletRequest request, HttpServletResponse response) throws IOException{
		User form=CommonUtils.toBean(request.getParameterMap(), User.class);
		String username= form.getUsername();
		Map<String,String> errors=new HashMap<String, String>();
		if(username==null||username.trim().length()==0){
			errors.put("username","用户名不能为空" );
		}else if(username.length()<3||username.length()>10){
			errors.put("username", "用户名长度最大为10，最小为3");
		}
		String password= form.getPassword();
		if(password==null||password.trim().length()==0){
			errors.put("password","密码不能为空" );
		}else if(password.length()<3||password.length()>10){
			errors.put("password", "密码长度最大为10，最小为3");
		}
		if(errors.size()!=0){
			request.setAttribute("user",  form);
			request.setAttribute("errors", errors);
			return "f:/jsps/user/login.jsp";
		}
		try {
			User newUser=userService.login( form);
			request.getSession().setAttribute("user",newUser);
			request.getSession().setAttribute("cart", new Cart());
			return "r:/jsps/main.jsp";	
		} catch (UserException e) {
			request.setAttribute("msg",e.getMessage());
		    request.setAttribute("user", form);
			return "f:/jsps/user/login.jsp";
		}
		//request.getSession().invalidate();安全
	}
	public String quit(HttpServletRequest request, HttpServletResponse response) throws IOException{
		request.getSession().invalidate();
		return "r:/jsps/user/login.jsp";
	}
}
 

