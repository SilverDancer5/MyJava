
public class Date {
	private int year;
	private int month;
	private int day;
	
	public int getYear() {
		return year;
	}
	public void setYear(int year) {
		this.year = year;
	}
	public int getMonth() {
		return month;
	}
	public void setMonth(int month) {
		this.month = month;
	}
	public int getDay() {
		return day;
	}
	public void setDay(int day) {
		this.day = day;
	}
	
	
	public boolean isLeapYear() {
		if(this.year%4 == 0 && this.year%100 != 0 || this.year%400 == 0) {
			return true;
		}
		
		return false;	
	}
	
	public int daysOfMonth() {
		int count = 0;
		switch(this.month) {
		case 1: 
			count = 31;break;
		case 2: 
			if(this.isLeapYear() == true) {
				count = 29;
				break;
			}
			else {
				count = 28;
				break;
			}
		case 3:
			count = 31;break;
		case 4:
			count = 30;break;
		case 5:
			count = 31;break;
		case 6:
			count = 30;break;
		case 7:
			count = 31;break;
		case 8:
			count = 31;break;
		case 9:
			count = 30;break;
		case 10:
			count = 31;break;
		case 11:
			count = 30;break;
		case 12:
			count = 31;break;
		
		}
		return count;
	}
	

}
