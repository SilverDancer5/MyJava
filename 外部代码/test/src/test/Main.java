package test;

import java.util.ArrayList;
import java.util.Collections;

public class Main {
	public static void main(String args[]){
        int row = 4;
        int col = 4;
		//����open��
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
        long startTime=System.currentTimeMillis();   //��ȡ��ʼʱ��
        if(start.isSolvable(target)){
            //��ʼ����ʼ״̬
            start.init(target);            
            open.add(start);
            while(open.isEmpty() == false){
                Collections.sort(open);            //����evaluation��ֵ����
                Puzzle best = open.get(0);    //��open����ȡ����С��ֵ��״̬���Ƴ�open��
                open.remove(0);
                close.add(best);
                if(best.isTarget(target)){            
                    //���
                    best.printRoute();
                    long end=System.currentTimeMillis(); //��ȡ����ʱ��  
                    System.out.println("��������ʱ�䣺 "+(end-startTime)+"ms");
                    System.exit(0);
                }
                int move;
                //��best״̬������չ�����뵽open����
                //0��λ������֮��״̬����close��open���趨bestΪ�丸״̬������ʼ��f(n)��ֵ����
                if(best.isMoveUp()){
                    move = 0;
                    Puzzle up = best.moveUp(move);
                    up.operation(open, close, best, target);
                }
                //0��λ������֮��״̬����close��open���趨bestΪ�丸״̬������ʼ��f(n)��ֵ����
                if(best.isMoveDown()){
                    move = 1;
                    Puzzle up = best.moveUp(move);
                    up.operation(open, close, best, target);
                }
                //0��λ������֮��״̬����close��open���趨bestΪ�丸״̬������ʼ��f(n)��ֵ����
                if(best.isMoveLeft()){
                    move = 2;
                    Puzzle up = best.moveUp(move);
                    up.operation(open, close, best, target);
                }
                //0��λ������֮��״̬����close��open���趨bestΪ�丸״̬������ʼ��f(n)��ֵ����
                if(best.isMoveRight()){
                    move = 3;
                    Puzzle up = best.moveUp(move);
                    up.operation(open, close, best, target);
                }
                
            }
        }else 
            System.out.println("û�н⣬���������롣");
    }

}
