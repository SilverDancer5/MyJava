package test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;





@SuppressWarnings("rawtypes")
public class Puzzle implements Comparable{
	private int col;
	private int row;
    private int[] num = new int[col*row];
    private int depth;                    //��ǰ����ȼ��ߵ���ǰ״̬�Ĳ���
    private int evaluation;                //����ʼ״̬��Ŀ�����С����ֵ
    private int misposition;            //��Ŀ�����С����
    private Puzzle parent;            //��ǰ״̬�ĸ�״̬

    
    
    public int getCol() {
		return col;
	}
	public void setCol(int col) {
		this.col = col;
	}
	public int getRow() {
		return row;
	}
	public void setRow(int row) {
		this.row = row;
	}
	public int[] getNum() {
        return num;
    }
    public void setNum(int[] num) {
        this.num = num;
    }
    public int getDepth() {
        return depth;
    }
    public void setDepth(int depth) {
        this.depth = depth;
    }
    public int getEvaluation() {
        return evaluation;
    }
    public void setEvaluation(int evaluation) {
        this.evaluation = evaluation;
    }
    public int getMisposition() {
        return misposition;
    }
    public void setMisposition(int misposition) {
        this.misposition = misposition;
    }
    public Puzzle getParent() {
        return parent;
    }
    public void setParent(Puzzle parent) {
        this.parent = parent;
    }
    
    Puzzle(int col, int row) {
    	this.col = col;
    	this.row = row;
    }
    
    /**
     * �жϵ�ǰ״̬�Ƿ�ΪĿ��״̬
     * @param target
     * @return
     */
    public boolean isTarget(Puzzle target){
        return Arrays.equals(getNum(), target.getNum());
    }
    
    /**
     * ��f(n) = g(n)+h(n);
     * ��ʼ��״̬��Ϣ
     * @param target
     */
    public void init(Puzzle target){
        int temp = 0;
        for(int i=0;i<col*row;i++){
            if(num[i]!=target.getNum()[i])
                temp++;
        }
        this.setMisposition(temp);
        if(this.getParent()==null){
            this.setDepth(0);
        }else{
            this.depth = this.parent.getDepth()+1;
        }
        this.setEvaluation(this.getDepth()+this.getMisposition());
    }
    
    /**
     * ������ֵ���ж��Ƿ��н�
     * @param target
     * @return �н⣺true �޽⣺false
     */
    public boolean isSolvable(Puzzle target){
        int reverse = 0;
        for(int i=0;i<col*row;i++){
            for(int j=0;j<i;j++){
                if(num[j]>num[i])
                    reverse++;
                if(target.getNum()[j]>target.getNum()[i])
                    reverse++;
            }
        }
        if(reverse % 2 == 0)
            return true;
        return false;
    }
    @Override
    public int compareTo(Object o) {
        Puzzle c = (Puzzle) o;
        return this.evaluation-c.getEvaluation();//Ĭ������Ϊf(n)��С��������
    }
    /**
     * @return ����0�ڰ������е�λ��
     */
    public int getZeroPosition(){
        int position = -1;
        for(int i=0;i<col*row;i++){
            if(this.num[i] == 0){
                position = i;
            }
        }
        return position;
    }
    /**
     * 
     * @param open    ״̬����
     * @return �жϵ�ǰ״̬�Ƿ������open����
     */
    public int isContains(ArrayList<Puzzle> open){
        for(int i=0;i<open.size();i++){
            if(Arrays.equals(open.get(i).getNum(), getNum())){
                return i;
            }
        }
        return -1;
    }
    /**
     * 
     * @return С��3�Ĳ������Ʒ���false
     */
    public boolean isMoveUp() {
        int position = getZeroPosition();
        if(position/col == 0){
            return false;
        }
        return true;
    }
    /**
     * 
     * @return ����6����false
     */
    public boolean isMoveDown() {
        int position = getZeroPosition();
        if(position/col == row-1){
            return false;
        }
        return true;
    }
    /**
     * 
     * @return 0��3��6����false
     */
    public boolean isMoveLeft() {
        int position = getZeroPosition();
        if(position%col == 0){
            return false;
        }
        return true;
    }
    /**
     * 
     * @return 2��5��8�������Ʒ���false
     */
    public boolean isMoveRight() {
        int position = getZeroPosition();
        if(position%col == col-1){
            return false;
        }
        return true;
    }
    /**
     * 
     * @param move 0���ϣ�1���£�2����3����
     * @return �����ƶ����״̬
     */
    public Puzzle moveUp(int move){
        Puzzle temp = new Puzzle(row, col);
        int[] tempnum = (int[])num.clone();
        temp.setNum(tempnum);
        int position = getZeroPosition();    //0��λ��
        int p=0;                            //��0��λ�õ�λ��
        switch(move){
            case 0:
                p = position-col;
                temp.getNum()[position] = num[p];
                break;
            case 1:
                p = position+col;
                temp.getNum()[position] = num[p];
                break;
            case 2:
                p = position-1;
                temp.getNum()[position] = num[p];
                break;
            case 3:
                p = position+1;
                temp.getNum()[position] = num[p];
                break;
        }
        temp.getNum()[p] = 0;
        return temp;
    }
    /**
     * ���հ�����ĸ�ʽ���
     */
    public void print(){
        for(int i=0;i<row*col;i++){
            if(i%col == col-1){
                System.out.println(this.num[i]);
            }else{
                System.out.print(this.num[i]+"  ");
            }
        }
    }
    /**
     * �����е����״̬
     */
    public void printRoute(){
        Puzzle temp = null;
        int count = 0;
        temp = this;
        while(temp!=null){
            temp.print();
            System.out.println("----------�ָ���----------");
            temp = temp.getParent();
            count++;
        }
        System.out.println("��������"+(count-1));
    }
    /**
     * 
     * @param open open��
     * @param close close��
     * @param parent ��״̬
     * @param target Ŀ��״̬
     */
    public void operation(ArrayList<Puzzle> open,ArrayList<Puzzle> close,Puzzle parent,Puzzle target){
        if(this.isContains(close) == -1){
            int position = this.isContains(open);
            if(position == -1){
                this.parent = parent;
                this.init(target);
                open.add(this);
            }else{
                if(this.getDepth() < open.get(position).getDepth()){
                    open.remove(position);
                    this.parent = parent;
                    this.init(target);
                    open.add(this);
                }
            }
        }
    }
    
    
    
}