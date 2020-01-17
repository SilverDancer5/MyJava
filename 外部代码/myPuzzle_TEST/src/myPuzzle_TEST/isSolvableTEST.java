package myPuzzle_TEST;

import static org.junit.jupiter.api.Assertions.assertEquals;


import java.util.Arrays;  
import java.util.Collection;  
  
import org.junit.Assert;  
import org.junit.Test;  
import org.junit.runner.RunWith;  
import org.junit.runners.Parameterized;  
import org.junit.runners.Parameterized.Parameters;  


@RunWith(Parameterized.class) 
public class isSolvableTEST {

	private int[] input1; 
    private boolean expected;  
	
    @Parameters  
    @SuppressWarnings("unchecked")  
    public static Collection prepareData(){  
    	int[] inputArray1_1 = {0, 1, 2, 3, 4, 5, 6, 7, 8};
        boolean expected1_1 = true;
        int[] inputArray1_2 = {0, 1, 2, 3, 4, 5, 6, 7, 8};
        boolean expected1_2 = false;
        
        int[] inputArray2_1 = {1, 0, 2, 3, 4, 5, 6, 7, 8};
        boolean expected2_1 = true;
        int[] inputArray2_2 = {1, 0, 2, 3, 4, 5, 6, 7, 8};
        boolean expected2_2 = false;
        
        int[] inputArray3_1 = {1, 2, 0, 3, 4, 5, 6, 7, 8};
        boolean expected3_1 = true;
        int[] inputArray3_2 = {1, 2, 0, 3, 4, 5, 6, 7, 8};
        boolean expected3_2 = false;
        
        int[] inputArray4_1 = {1, 2, 0, 3, 4, 5, 6, 7, 8};
        boolean expected4_1 = true;
        int[] inputArray4_2 = {1, 2, 0, 3, 4, 5, 6, 7, 8};
        boolean expected4_2 = false;
        
        int[] inputArray5_1 = {1, 2, 3, 0, 4, 5, 6, 7, 8};
        boolean expected5_1 = true;
        int[] inputArray5_2 = {1, 2, 3, 0, 4, 5, 6, 7, 8};
        boolean expected5_2 = false;
        
        
        return Arrays.asList(new Object[][]{
                {inputArray1_1, expected1_1},
                {inputArray1_2, expected1_2},
                {inputArray2_1, expected2_1},
                {inputArray2_2, expected2_2},
                {inputArray3_1, expected3_1},
                {inputArray3_2, expected3_2},
                {inputArray4_1, expected4_1},
                {inputArray4_2, expected4_2},
                {inputArray5_1, expected5_1},
                {inputArray5_2, expected5_2}
        });
    }  
      
    public isSolvableTEST(int[] input1, boolean expected){  
        this.input1 = input1;   
        this.expected = expected;  
    }  
    
    
   @Test  
    public void testAdd(){  
    	
        ppr_TEST p = new ppr_TEST();  
        assertEquals(expected, p.isSolvable(input1));   
 
        
	}


}
