package test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;





@SuppressWarnings("rawtypes")
public class Puzzle implements Comparable{
	private int col;
	private int row;
    private int[] num = new int[col*row];
    private int depth;                    //当前的深度即走到当前状态的步骤
    private int evaluation;                //从起始状态到目标的最小估计值
    private int misposition;            //到目标的最小估计
    private Puzzle parent;            //当前状态的父状态

    
    
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
     * 判断当前状态是否为目标状态
     * @param target
     * @return
     */
    public boolean isTarget(Puzzle target){
        return Arrays.equals(getNum(), target.getNum());
    }
    
    /**
     * 求f(n) = g(n)+h(n);
     * 初始化状态信息
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
     * 求逆序值并判断是否有解
     * @param target
     * @return 有解：true 无解：false
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
        return this.evaluation-c.getEvaluation();//默认排序为f(n)由小到大排序
    }
    /**
     * @return 返回0在八数码中的位置
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
     * @param open    状态集合
     * @return 判断当前状态是否存在于open表中
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
     * @return 小于3的不能上移返回false
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
     * @return 大于6返回false
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
     * @return 0，3，6返回false
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
     * @return 2，5，8不能右移返回false
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
     * @param move 0：上，1：下，2：左，3：右
     * @return 返回移动后的状态
     */
    public Puzzle moveUp(int move){
        Puzzle temp = new Puzzle(row, col);
        int[] tempnum = (int[])num.clone();
        temp.setNum(tempnum);
        int position = getZeroPosition();    //0的位置
        int p=0;                            //与0换位置的位置
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
     * 按照八数码的格式输出
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
     * 反序列的输出状态
     */
    public void printRoute(){
        Puzzle temp = null;
        int count = 0;
        temp = this;
        while(temp!=null){
            temp.print();
            System.out.println("----------分割线----------");
            temp = temp.getParent();
            count++;
        }
        System.out.println("步骤数："+(count-1));
    }
    /**
     * 
     * @param open open表
     * @param close close表
     * @param parent 父状态
     * @param target 目标状态
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