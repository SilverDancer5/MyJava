package test;

import java.util.ArrayList;
import java.util.Collections;

public class Main {
	public static void main(String args[]){
        int row = 4;
        int col = 4;
		//定义open表
        ArrayList<Puzzle> open = new ArrayList<Puzzle>();
        ArrayList<Puzzle> close = new ArrayList<Puzzle>();
        Puzzle start = new Puzzle(row, col);
        Puzzle target = new Puzzle(row, col);
        

//        int stnum[] = {0,1,2,3,4,8,7,6,5};
//        int tanum[] = {0,1,2,3,4,5,6,7,8};
        
        int stnum[] = {1,2,3,4,5,6,7,8,9,10,11,12,13,14,0,15};
        int tanum[] = {0,1,2,3,4,5,6,7,8,9,10,11,12,13,14,15};
        


        start.setNum(stnum);
        target.setNum(tanum);
        long startTime=System.currentTimeMillis();   //获取开始时间
        if(start.isSolvable(target)){
            //初始化初始状态
            start.init(target);            
            open.add(start);
            while(open.isEmpty() == false){
                Collections.sort(open);            //按照evaluation的值排序
                Puzzle best = open.get(0);    //从open表中取出最小估值的状态并移除open表
                open.remove(0);
                close.add(best);
                if(best.isTarget(target)){            
                    //输出
                    best.printRoute();
                    long end=System.currentTimeMillis(); //获取结束时间  
                    System.out.println("程序运行时间： "+(end-startTime)+"ms");
                    System.exit(0);
                }
                int move;
                //由best状态进行扩展并加入到open表中
                //0的位置上移之后状态不在close和open中设定best为其父状态，并初始化f(n)估值函数
                if(best.isMoveUp()){
                    move = 0;
                    Puzzle up = best.moveUp(move);
                    up.operation(open, close, best, target);
                }
                //0的位置下移之后状态不在close和open中设定best为其父状态，并初始化f(n)估值函数
                if(best.isMoveDown()){
                    move = 1;
                    Puzzle up = best.moveUp(move);
                    up.operation(open, close, best, target);
                }
                //0的位置左移之后状态不在close和open中设定best为其父状态，并初始化f(n)估值函数
                if(best.isMoveLeft()){
                    move = 2;
                    Puzzle up = best.moveUp(move);
                    up.operation(open, close, best, target);
                }
                //0的位置右移之后状态不在close和open中设定best为其父状态，并初始化f(n)估值函数
                if(best.isMoveRight()){
                    move = 3;
                    Puzzle up = best.moveUp(move);
                    up.operation(open, close, best, target);
                }
                
            }
        }else 
            System.out.println("没有解，请重新输入。");
    }

}
