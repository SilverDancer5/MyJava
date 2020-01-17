package util;

import com.alibaba.fastjson.JSON;

import model.User;

public class JsonTest {

	public static void main(String[] args) {
		
		//���л��־û�
//		User user = new User("username", "password", 20, "sex", true);
//		String json = JSON.toJSONString(user);
//		System.out.println(json);
		
		//�����л� json����
		String s = "{\"admin\":true,\"age\":20,\"password\":\"password\",\"sex\":\"sex\",\"username\":\"username\"}";
		User user = JSON.parseObject(s, User.class);
		System.out.println(user.getUsername());
	}
}
