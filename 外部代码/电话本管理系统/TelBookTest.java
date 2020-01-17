package com.zy.java;

import java.util.Scanner;

public class TelBookTest {
	static Scanner sc=new Scanner(System.in);
	public static void main(String[] args) {
		while(true){
		System.out.println("-----------------电话本管理系统----------------");
        System.out.println("1.添加  2.删除  3.修改  4.根据姓名查询  5.查询所有  0.退出");
        System.out.println("请选择业务：");
        int x=sc.nextInt();
        switch (x) {
		case 1:
			Method.addTel();
			break;
        case 2:
			Method.delTel();
			break;
        case 3:
	        Method.updateTel();
	        break;
       case 4:
    	    Method.enquireByName();
    	    break;
       case 5:
    	    Method.enquireAll();
			break;
       case 0:
    	    System.out.println("已退出程序！");
			System.exit(0);
		default:
			break;
		}
	 }

	}

}
