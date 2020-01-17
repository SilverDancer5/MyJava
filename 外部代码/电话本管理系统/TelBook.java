package com.zy.java;

public class TelBook {

	   private String name;
	   private String sex;
	   private int age;
	   private int tel;
	   private int QQ;
	   private String address;
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getSex() {
		return sex;
	}
	public void setSex(String sex) {
		this.sex = sex;
	}
	public int getAge() {
		return age;
	}
	public void setAge(int age) {
		this.age = age;
	}
	public int getTel() {
		return tel;
	}
	public void setTel(int tel) {
		this.tel = tel;
	}
	public int getQQ() {
		return QQ;
	}
	public void setQQ(int qQ) {
		QQ = qQ;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String adress) {
		this.address = adress;
	}
	public TelBook(String name, String sex, int age, int tel, int qQ, String address) {
		super();
		this.name = name;
		this.sex = sex;
		this.age = age;
		this.tel = tel;
		QQ = qQ;
		this.address = address;
	}
	public TelBook() {
		super();
	}
public void display() {
		
		System.out.println("姓名："+getName()+
				",性别："+getSex()+
				",年龄："+getAge()+
				",电话："+getTel()+
				",QQ："+getQQ()+
				",地址："+getAddress()
				);
	}
	 
}
