package cn.itcast.bookstore.user.service;

import cn.itcast.bookstore.user.dao.UserDao;
import cn.itcast.bookstore.user.domain.User;



public class UserService {
   private	UserDao userDao=new UserDao();
   public void regist(User form) throws UserException{
	   User user=userDao.findByUsername(form.getUsername());
	   if(user!=null) throw  new UserException("用户名已被注册！");
	   user=userDao.findByEmail(form.getEmail());
	   if(user!=null) throw new UserException("Email 已被注册！");
	   userDao.addUser(form);
   }
   public void active(String code) throws UserException{
	   User user=userDao.findByCode(code);
	   if(user==null) throw new UserException("激活码无效");
	   else if(user.isState()) throw new UserException("您已经激活了，请不要再激活，除非你想死");
	   userDao.updateState(user.getUid(), true);
   }
   public User login(User  form) throws UserException{
	   User newUser=userDao.findByUsername( form.getUsername());
	   if(newUser==null) throw new UserException("用户不存在");
	   else if(!newUser.isState()) throw new UserException("您尚未激活");
	   else if(!newUser.getPassword().equals( form.getPassword()))
		   throw new UserException("密码错误");
	   return newUser;
   }
}
