package LazySingleton;

public class LazySingleton {
 
	//设立静态变量
	private static LazySingleton mySingleton = null;
 
	private LazySingleton(){
		//私有化构造函数
		System.out.println("-->懒汉式单例模式开始调用构造函数");
	}
	
	//开放一个公有方法，判断是否已经存在实例，有返回，没有新建一个在返回
	public static LazySingleton getInstance(){
		System.out.println("-->懒汉式单例模式开始调用公有方法返回实例");
		if(mySingleton == null){
			System.out.println("-->懒汉式构造函数的实例当前并没有被创建");
			mySingleton = new LazySingleton();
		}else{
			System.out.println("-->懒汉式构造函数的实例已经被创建");
		}
		System.out.println("-->方法调用结束，返回单例");
		return mySingleton;
	}
}


