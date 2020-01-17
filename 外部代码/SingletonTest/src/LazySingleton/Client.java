package LazySingleton;

public class Client {
	
	/**
	 * 懒汉式单例模式
	 * MySingleton
	 */
	public static void myprint(){
		System.out.println("-----------------懒汉式单例模式----------------");
		System.out.println("第一次取得实例（懒汉式）");
		LazySingleton s1 = LazySingleton.getInstance();
		System.out.println("第二次取得实例（懒汉式）");
		LazySingleton s2 = LazySingleton.getInstance();
		if(s1==s2){
			System.out.println(">>>>>s1,s2为同一实例（懒汉式）<<<<<");
		}
		System.out.println();
	}
        /**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		//懒汉式
		myprint();
		//饿汉式
		//myprint2();
		//懒汉式改进
		//myprint2a();
		//登记式
		//myprint3();
 
	}
 
}
