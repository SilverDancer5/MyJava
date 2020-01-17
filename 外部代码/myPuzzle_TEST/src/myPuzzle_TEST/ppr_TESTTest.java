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
public class ppr_TESTTest {
	private int[] input1; 
	private int[] inputTarget;
    private int expected;  
	
    @Parameters  
    @SuppressWarnings("unchecked")  
    public static Collection prepareData(){  
    	int[] inputArray1 = {0, 1, 2, 3, 4, 5, 6, 7, 8};
        int expected1 = 0;
        
        int[] inputArray2 = {1, 0, 2, 3, 4, 5, 6, 7, 8};
        int expected2 = 1;
        
        return Arrays.asList(new Object[][]{
                {inputArray1, expected1},
                {inputArray2, expected2}
        });
    }  
      
    public ppr_TESTTest(int[] input1, int expected){  
        this.input1 = input1;   
        this.expected = expected;  
    }  
    
    
   @Test  
    public void testAdd(){  
    	
        ppr_TEST p = new ppr_TEST();  
        assertEquals(expected, p.getZeroPosition(input1));   
        
	}

}
