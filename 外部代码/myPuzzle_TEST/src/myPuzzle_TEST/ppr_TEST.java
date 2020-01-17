package myPuzzle_TEST;

import java.lang.reflect.Array;
import java.util.Arrays;


public class ppr_TEST {
	
	
	
	 int add(int input1, int input2) {
		 return (input1 + input2);
	 }
	
	
	int getZeroPosition(int[] resultNum) {
		int position = -1;
			for(int j=0; j<9; j++) {
				if(resultNum[j] == 0) {
					position = j;
					break;
				}
			}
		return position;
	}
	
	public boolean isTarget(int[] num) {
		int[] target = {0, 1, 2, 3, 4, 5, 6, 7, 8};
		return Arrays.equals(num, target);
	}
	
	public boolean isSolvable(int[] num){
		int[] target = {0, 1, 2, 3, 4, 5, 6, 7, 8};
        int reverse = 0;
        int col = 3, row = 3;
        for(int i=0;i<col*row;i++){
            for(int j=0;j<i;j++){
                if(num[j]>num[i])
                    reverse++;
                if(num[j]>target[i])
                    reverse++;
            }
        }
        if(reverse % 2 == 0)
            return true;
        return false;
    }
	
	
	public boolean isMoveUp(int[] num) {
        int position = -1;
        for(int i=0; i<num.length; i++) {
        	if(num[i] == 0) {
        		position = i;
        		break;
        	}
        }
        if(position != -1 && position/3 == 0){
            return false;
        }
        return true;
    }
	
	
	 public boolean isMoveDown(int[] num) {
		 int position = -1;
	        for(int i=0; i<num.length; i++) {
	        	if(num[i] == 0) {
	        		position = i;
	        		break;
	        	}
	        }
	        if(position != -1 && position/3 == 3-1){
	            return false;
	        }
	        return true;
	    }
	 
	 
	 public boolean isMoveLeft(int[] num) {
		 int position = -1;
	        for(int i=0; i<num.length; i++) {
	        	if(num[i] == 0) {
	        		position = i;
	        		break;
	        	}
	        }
	        if(position != 3 && position%3 == 0){
	            return false;
	        }
	        return true;
	    }
	 
	 
	 public boolean isMoveRight(int[] num) {
		 int position = -1;
	        for(int i=0; i<num.length; i++) {
	        	if(num[i] == 0) {
	        		position = i;
	        		break;
	        	}
	        }
	        if(position%3 == 3-1){
	            return false;
	        }
	        return true;
	    }
	
}
