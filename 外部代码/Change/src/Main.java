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
		
		System.out.print("������۸�");
		price = in.nextInt();
		System.out.print("�����븶��");
		money = in.nextInt();
		in.close();
		
		if(isLeagl(price, money) == true) {
			System.out.println("��������ֺϷ����������");
		} else {
			System.out.println("��������ַǷ����������");
			System.exit(0);
		}

		change = money - price;
		System.out.println("�ܼ���Ǯ��"+ change +"Ԫ");
		
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
		
		System.out.println("50Ԫ��"+ cut50 + "��");
		System.out.println("20Ԫ��"+ cut20 + "��");
		System.out.println("10Ԫ��"+ cut10 + "��");
		System.out.println("5Ԫ��"+ cut5 + "��");
		System.out.println("1Ԫ��"+ cut1 + "��");
	}

}
