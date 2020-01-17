package service;

import dao.UserDao;

public class UserService {
	public boolean register(String username, String password, int age, String sex) {
		UserDao userDao = new UserDao();
		boolean isExist = userDao.isExist(username);
		if(isExist)
			return false;
		else {
			userDao.addUser(username, password, age, sex);
			return true;
		}
	}

}
