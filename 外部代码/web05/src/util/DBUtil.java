package util;

import java.util.HashMap;
import java.util.Map;

import model.User;

public class DBUtil {

	public static Map<String, User> userMap = new HashMap<String, User>();
	
	static {
		addUser(new User("hym", "123", 10, "ÄÐ", true));
		addUser(new User("hc", "123", 10, "ÄÐ", true));
	}
	
	private static void addUser(User user) {
		userMap.put(user.getUsername(), user);
	}
	
}
