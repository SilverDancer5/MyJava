import java.util.Scanner;

public class Main {
	
	public static boolean isLeagl(int price, int money) {
		if(money>=0 && money<=100 && price>=0 && price<=100)
			return true;
		else 
			return false; 
	}

	public static void main(String[] args) {
		
		int price, money, change;
		Scanner in = new Scanner(System.in);
		
		System.out.print("请输入价格");
		price = in.nextInt();
		System.out.print("请输入付款");
		money = in.nextInt();
		in.close();
		
		if(isLeagl(price, money) == true) {
			System.out.println("输入的数字合法，程序继续");
		} else {
			System.out.println("输入的数字非法，程序结束");
			System.exit(0);
		}

		change = money - price;
		System.out.println("总计找钱："+ change +"元");
		
		int cut50=0, cut20=0, cut10=0, cut5=0, cut1=0;
		cut50 = change/50;
		change = change%50;
		cut20 = change/20;
		change = change%20;
		cut10 = change/10;
		change = change%10;
		cut5 = change/5;
		change = change%5;
		cut1 = change;
		
		System.out.println("50元："+ cut50 + "张");
		System.out.println("20元："+ cut20 + "张");
		System.out.println("10元："+ cut10 + "张");
		System.out.println("5元："+ cut5 + "张");
		System.out.println("1元："+ cut1 + "张");
	}

}
