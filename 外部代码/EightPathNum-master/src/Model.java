import java.util.HashSet;
import java.util.Random;
import java.util.Set;

public class Model {
	
	static int[] targetState = {1,2,3,4,5,6,7,8,0};
//	static int[] startState = new int[9];
	static int[] startState = {0,2,3,1,4,5,6,7,8};
	
	public static boolean isSolvable(int[] state) {
		int sum = 0;
    	for(int i=0; i<9; i++) {
    		for(int j=0; j<i; j++) {
    			if(startState[j] > startState[i])
    				sum++;
    			if(targetState[j] > targetState[i])
    				sum++;
    		}
    	}
    	if(sum%2 == 0)
    		return true;
    	return false;
    }
	
	public static  void creatRandomState() {
		do {
			int k = 0;
			Random random=new Random();
			Set<Integer> set=new HashSet<Integer>();
			while(set.size() < 8) {
				int n=random.nextInt(8)+1;
				if(!set.contains(n)) {
				set.add(n);
				startState[k++] = n;
				}
			}
			startState[k] = 0;
			}while(isSolvable(startState) == false);
	}
	 
}
