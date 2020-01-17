package dao;



import model.User;
import util.DBUtil;

public class UserDao {

	public boolean isExist(String username) {
		return DBUtil.userMap.containsKey(username);
	}
	
	public boolean addUser(String username, String password, int age, String sex) {
		if(isExist(username))
			return false;
		User u = new User(username, password, age, sex, false);
		DBUtil.userMap.put(u.getUsername(), u);
		return true;
	
	}
	
	public User getUserByUP(String username, String password) {
		if(isExist(username) == false)
			return null;
		User u = DBUtil.userMap.get(username);
		if(u.getPassword().equalsIgnoreCase(password))
			return u;
		else
			return null;
	}
}
