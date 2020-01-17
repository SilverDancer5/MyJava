package util;

import com.alibaba.fastjson.JSON;

import model.User;

public class JsonTest {

	public static void main(String[] args) {
		
		//序列化持久化
//		User user = new User("username", "password", 20, "sex", true);
//		String json = JSON.toJSONString(user);
//		System.out.println(json);
		
		//反序列化 json解析
		String s = "{\"admin\":true,\"age\":20,\"password\":\"password\",\"sex\":\"sex\",\"username\":\"username\"}";
		User user = JSON.parseObject(s, User.class);
		System.out.println(user.getUsername());
	}
}
