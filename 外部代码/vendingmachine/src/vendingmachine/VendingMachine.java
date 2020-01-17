package vendingmachine;

public class VendingMachine {
	int price = 200;
	int balance;
	int total;
	
	void showPromapt( ) {
		System.out.println("Welcome");
	}
	
	void inserMoney(int amount) {
		balance = balance + amount;
	}
	
	void showBalance() {
		System.out.println(balance);
	}
	
	void getFood() {
		if (balance >= price) {
			System.out.println("Here you are");
			balance -= price;
			total += price;
		}
	}
	
	public static void main(String[] args) {
		VendingMachine vm = new VendingMachine();
		vm.showPromapt();
		vm.showBalance();
		vm.inserMoney(100);
		vm.getFood();
		
	}

}
