package LazySingleton;

public class Client {
	
	/**
	 * ����ʽ����ģʽ
	 * MySingleton
	 */
	public static void myprint(){
		System.out.println("-----------------����ʽ����ģʽ----------------");
		System.out.println("��һ��ȡ��ʵ��������ʽ��");
		LazySingleton s1 = LazySingleton.getInstance();
		System.out.println("�ڶ���ȡ��ʵ��������ʽ��");
		LazySingleton s2 = LazySingleton.getInstance();
		if(s1==s2){
			System.out.println(">>>>>s1,s2Ϊͬһʵ��������ʽ��<<<<<");
		}
		System.out.println();
	}
        /**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		//����ʽ
		myprint();
		//����ʽ
		//myprint2();
		//����ʽ�Ľ�
		//myprint2a();
		//�Ǽ�ʽ
		//myprint3();
 
	}
 
}
