package LazySingleton;

public class LazySingleton {
 
	//������̬����
	private static LazySingleton mySingleton = null;
 
	private LazySingleton(){
		//˽�л����캯��
		System.out.println("-->����ʽ����ģʽ��ʼ���ù��캯��");
	}
	
	//����һ�����з������ж��Ƿ��Ѿ�����ʵ�����з��أ�û���½�һ���ڷ���
	public static LazySingleton getInstance(){
		System.out.println("-->����ʽ����ģʽ��ʼ���ù��з�������ʵ��");
		if(mySingleton == null){
			System.out.println("-->����ʽ���캯����ʵ����ǰ��û�б�����");
			mySingleton = new LazySingleton();
		}else{
			System.out.println("-->����ʽ���캯����ʵ���Ѿ�������");
		}
		System.out.println("-->�������ý��������ص���");
		return mySingleton;
	}
}


