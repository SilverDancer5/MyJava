package com.zy.java;

import java.util.Scanner;

public class Method {
	static Scanner sc=new Scanner(System.in);
	static TelBook[] tb=new TelBook[100];
	static int maxindex=-1;
	public static void enquireByName() {
		System.out.println("请输入你要查询的姓名：");
		String name=sc.next();
		int index=getIndexByName(name);
		if(index!=-1){
			tb[index].display();
			return;
		}
		System.out.println(" 无此人！");
		
	}

	public static void enquireAll() {
		if(maxindex<0){
			System.out.println("没有数据！");
		}else{
		    for(int i=0;i<=maxindex;i++){
			    TelBook telbook=tb[i];
			    telbook.display();
		    }
		}
		
	}

	public static void updateTel() {
		System.out.println("请输入你要更新的名字：");
		String name=sc.next();
		int index=getIndexByName(name);
		if(index!=-1){
			tb[index].display();
			System.out.println("请开始更新！");
			System.out.println("姓名：");
			String name1=sc.next();
			System.out.println("性别：");
			String sex=sc.next();
			System.out.println("年龄：");
			int age=sc.nextInt();
			System.out.println("电话：");
			int tel=sc.nextInt();
			System.out.println("QQ：");
			int QQ=sc.nextInt();
			System.out.println("地址：");
			String address=sc.next();
			TelBook telbook=null;
			telbook=new TelBook(name1, sex, age, tel, QQ, address);
			TelBook telb=telbook;
			tb[index]=telbook;
			telbook.display();
			System.out.println("更新成功！");
			
			
		}
		if(index==-1){
			System.out.println("无此人！");
		}
	}

	public static void delTel() {
		System.out.println("请输入删除的姓名：");
		String name=sc.next();
		int index=getIndexByName(name);
		if(index!=-1){
			tb[maxindex].display();
			System.out.println("确定删除吗？1(是)0(否)");
			int x=sc.nextInt();
			if(x==1){
				for(int i=index;i<maxindex;i++){
					tb[index]=tb[index+1];
				}
				maxindex--;
				System.out.println("删除成功！");
			}
			if(x==0){
				System.out.println("删除失败！");
			}
			
		}else{
			System.out.println("此人不存在！");
		}
		
	}
	public static int getIndexByName(String name){
		for(int i=0;i<=maxindex;i++){
			if(name.equals(tb[i].getName())){
				//找到了
				return i;
			}
		}
		return -1;
	}
	public static void addTel() {
		System.out.println("--------添加电话本--------");
		System.out.println("姓名：");
		String name=sc.next();
		System.out.println("性别：");
		String sex=sc.next();
		System.out.println("年龄：");
		int age=sc.nextInt();
		System.out.println("电话：");
		int tel=sc.nextInt();
		System.out.println("QQ：");
		int QQ=sc.nextInt();
		System.out.println("地址：");
		String address=sc.next();
		TelBook telbook=null;
		telbook=new TelBook(name, sex, age, tel, QQ, address);
		tb[++maxindex]=telbook;
		telbook.display();
		System.out.println("添加成功！");
	}

}
