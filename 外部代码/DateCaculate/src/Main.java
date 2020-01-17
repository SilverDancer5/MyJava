import java.util.Scanner;

public class Main {
	
	public static boolean isLegal(Date curDate) {
		int day = curDate.getDay();
		int month = curDate.getMonth();
		int year = curDate.getYear();
		
//		if(year>0) {
//			if(month>0 && month <13) {
//				
//				if(curDate.getDay() <= curDate.daysOfMonth() && curDate.getDay() > 0)
//					return true;
//				else
//					return false;
//			}
//			else
//				return false;	
//		}else
//			return false;
		
		if(year>0 && month>0 && month <13 && day>=0 && day <=curDate.daysOfMonth())
			return true;
		else
			return false;
		
	}
	
	public static Date caculate(Date curDate) {
		Date date = new Date();
		
		date.setYear(curDate.getYear());
		date.setMonth(curDate.getMonth());
		date.setDay(curDate.getDay() + 2);
		
		if(date.getDay() <= curDate.daysOfMonth()) {
			return date;
		}else {
			int day=0;
			day = date.getDay() - curDate.daysOfMonth();
			date.setDay(day);
			
			if(date.getMonth() < 12) {
				int month = date.getMonth() + 1;
				date.setMonth(month);
			}else {
				int month = 1;
				int year = date.getYear() + 1;
				date.setMonth(month);
				date.setYear(year);
			}
		}
		return date;
	}
	
	public static void main(String[] args) {
		
		Date curDate = new Date();
		int year, month, day;
		Scanner in = new Scanner(System.in);
		
		System.out.print("请输入年:");
		year = in.nextInt();
		curDate.setYear(year);
		
		System.out.print("请输入月:");
		month = in.nextInt();
		curDate.setMonth(month);
		
		System.out.print("请输入日:");
		day = in.nextInt();
		curDate.setDay(day);
		
		in.close();
		
		Date afterDate = new Date();
		if(isLegal(curDate) == true) {
			afterDate = caculate(curDate);
		}else {
			System.out.println("你的输入不合法,程序结束");
			System.exit(0);
		}
		
		System.out.print(afterDate.getYear() + " ");
		System.out.print(afterDate.getMonth() + " ");
		System.out.print(afterDate.getDay());
	}
}
