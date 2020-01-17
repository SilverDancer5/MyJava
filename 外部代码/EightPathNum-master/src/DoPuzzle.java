import java.awt.EventQueue;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.Random;
import java.util.Set;

import javax.swing.JFrame;


public class DoPuzzle {

	
	static ArrayList<Puzzle> open = new ArrayList<Puzzle>();
	static ArrayList<Puzzle> close = new ArrayList<Puzzle>();
	static int[][] result = new int[400000][9];
	
	static void changeTo(int[] state) {
		int count=1;
		int a = -1, b = -1;
		for(int i=0; i<9; i++) {
			if(state[i] != 0) {
				if(count == 2) {
					b = i;
					break;
				}
				a = i;
				count++;
			}
		}
		int temp = state[a];
		state[a] = state[b];
		state[b] = temp;
	}
		
	static void printPuzzleRoute(int[][] result, int last) {

		for(int i=last; i>=0; i--) {
			for(int j=0; j<9; j++) {

				if(j%3 == 2)
	    			System.out.println(result[i][j]);
	    		else
	    			System.out.print(result[i][j] + " ");
			}
			System.out.println("------------" + (last-i) +"-------------");
		}
		
	}

	static int getZeroPosition(int[] resultNum, int last) {
		int position = -1;
			for(int j=0; j<9; j++) {
				if(resultNum[j] == 0) {
					position = j;
					break;
				}
			}
		return position;
	}
	
	@SuppressWarnings("unchecked")
	static Puzzle doRestore(Puzzle start, Puzzle target) {
		long startTime = System.currentTimeMillis();
		
		//设置权重
		start.weight(target);
		open.add(start);

		Puzzle retBest = new Puzzle();
		while(open.isEmpty() == false) {

			Collections.sort(open);
			
			Puzzle best = open.get(0);
			retBest = best;
			open.remove(0);
			close.add(best);
			
			if(best.isTarget(target)) {
				long end=System.currentTimeMillis(); 
                System.out.println("程序运行时间： "+(end-startTime)/1000+"s");
//                System.exit(0);
        		break;
			}

			int moveDir;
			if(best.canUp()) {

				moveDir = 0;
				Puzzle up = best.doMove(moveDir);
				up.bfs(open, close, best, target);
			}
			
			if(best.canDown()) {

				moveDir = 1;
				Puzzle down = best.doMove(moveDir);
				down.bfs(open, close, best, target);
			}
			
			if(best.canLeft()) {

				moveDir = 2;
				Puzzle left = best.doMove(moveDir);
				left.bfs(open, close, best, target);
			}
			
			if(best.canRight()) {

				moveDir = 3;
				Puzzle right = best.doMove(moveDir);
				right.bfs(open, close, best, target);
			}

		}
		return retBest;
	}

	static StringBuffer getbestPath(int last) {
		//存储最优路径
		StringBuffer bestPath = new StringBuffer();
		for(int i=last-1; i>=0; i--) {
			int j = i+1;
			int beforePos = DoPuzzle.getZeroPosition(result[j], last);
			int afterPos = DoPuzzle.getZeroPosition(result[i], last);
			switch((afterPos - beforePos)) {
				case 1:
					bestPath.append("R");
					break;
				case -1:
					bestPath.append("L");
					break;
				case 3:
					bestPath.append("D");
					break;
				case -3:
					bestPath.append("U");
					break;
				}
		}
		return bestPath;
	}
	
	
//	public static void main(String[] args) {
//			
//	}
    
}
