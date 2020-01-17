import java.util.ArrayList;
import java.util.Arrays;

public class Puzzle implements Comparable{
	
	private int[] state = new int[9]; 	
	private int depth; 					
    private int guessWeight;            
    private int actualWeight;             
    private Puzzle parent;				
    
    public int[] getNum() {
        return state;
    }
    public void setNum(int[] num) {
        this.state = num;
    }
    public int getDepth() {
        return depth;
    }
    public void setDepth(int depth) {
        this.depth = depth;
    }
    public int getEvaluation() {
        return actualWeight;
    }
    public void setEvaluation(int evaluation) {
        this.actualWeight = evaluation;
    }
    public int getMisposition() {
        return guessWeight;
    }
    public void setMisposition(int misposition) {
        this.guessWeight = misposition;
    }
    public Puzzle getParent() {
        return parent;
    }
    public void setParent(Puzzle parent) {
        this.parent = parent;
    }
    
    //MARK �ж��Ƿ�ΪĿ��״̬
    public boolean isTarget(Puzzle target) {
    	return Arrays.equals(this.getNum(), target.getNum());
    }
    
    //MARK ��ʼȨ��
    public void weight(Puzzle target) {
    	int tempMisposition = 0;
    	for(int i=0; i<9; i++) {
    		if(state[i] != target.getNum()[i])
    			tempMisposition++;
    	} 
    	this.setMisposition(tempMisposition);
    	if(this.getParent() == null) {
    		this.setDepth(0);
    	}else {
    		this.depth = this.parent.getDepth()+1;
    	}
    	this.setEvaluation(this.getDepth() + this.getMisposition());
    }
    
    //MARK �жϸ�״̬�Ƿ�ɽ�
    public boolean isSolvable(Puzzle target) {
    	int sum = 0;
    	for(int i=0; i<9; i++) {
    		for(int j=0; j<i; j++) {
    			if(state[j] > state[i])
    				sum++;
    			if(target.getNum()[j] > target.getNum()[i])
    				sum++;
    		}
    	}
    	if(sum%2 == 0)
    		return true;
    	return false;
    }
    
    //MARK Ȩ������
    public int compareTo(Object o) {
    	Puzzle state = (Puzzle) o;
    	return (this.actualWeight - state.getEvaluation()); // ����Ȩ�ش�С��������
    }
    
    //MARK ��ȡ��ǰ״̬0��λ��
    public int getZeroPosition() {
    	int position = -1;
    	for(int i=0; i<9; i++) {
    		if(this.getNum()[i] == 0) {
    			position = i;
    			break;
    		}
    	}
    	return position;
    }
    
     //MARK �жϵ�ǰ״̬�Ƿ��ڿ�������
    public int isContains(ArrayList<Puzzle> open) {
    	for(int i=0; i < open.size(); i++) {
    		if(Arrays.equals(open.get(i).getNum(), this.getNum())) {
    			return i;
    		}
    	}
    	return -1;
    }
    
    //MARK �ж����ƶ�
    public boolean canUp() {
    	int curPosition = getZeroPosition();
    	if(curPosition <= 2)
    		return false;
    	return true;
    }
    
    //MARK �ж����ƶ�
    public boolean canDown() {
    	int curPosition = getZeroPosition();
    	if(curPosition >= 6)
    		return false;
    	return true;
    }
    
    //MARK �ж����ƶ�
    public boolean canLeft() {
    	int curPosition = getZeroPosition();
    	if(curPosition%3 == 0)
    		return false;
    	return true;
    }
    
    //MARK �ж����ƶ�
    public boolean canRight() {
    	int curPosition = getZeroPosition();
    	if(curPosition%3 == 2)
    		return false;
    	return true;
    }
    
    //MARK �����ƶ�0123����������
    public Puzzle doMove(int moveDir) {
    	Puzzle tempState = new Puzzle();
    	int[] tempNum = this.getNum().clone();
    	tempState.setNum(tempNum);
    	int zeroPosition = getZeroPosition();
    	int swapToPosition = 0;
    	switch(moveDir) {
    		case 0:
    			swapToPosition = zeroPosition - 3;
    			tempState.getNum()[zeroPosition] = this.getNum()[swapToPosition];
    			break;
    		case 1:
    			swapToPosition = zeroPosition + 3;
    			tempState.getNum()[zeroPosition] = this.getNum()[swapToPosition];
    			break;
    		case 2:
    			swapToPosition = zeroPosition - 1;
    			tempState.getNum()[zeroPosition] = this.getNum()[swapToPosition];
    			break;
    		case 3:
    			swapToPosition = zeroPosition + 1;
    			tempState.getNum()[zeroPosition] = this.getNum()[swapToPosition];
    			break;
    	}
    	tempState.getNum()[swapToPosition] = this.getNum()[zeroPosition];
    	return tempState;
    }
    
    //MARK ��ӡƴͼ
    public void print() {
    	for(int i=0; i<9; i++) {
    		if(i%3 == 2)
    			System.out.println(this.state[i]);
    		else
    			System.out.print(this.state[i] + " ");
    	}
    }
    
    //MARK ��ӡ·��
    public void printPuzzle() {
    	Puzzle tempState = this;
    	int count = 0;
    	while(tempState != null) {
    		tempState.print();
    		count++;
    		tempState = tempState.getParent();
    		System.out.println("---------------------------------------------");
    		int pos = tempState.getZeroPosition();
    		System.out.println(pos);
    		System.out.println("----------" + "��"+ count + "��" + "----------");
    	}
    	System.out.println("��������Ϊ��" + count);
    }
    
    //MARK ��Ӳ���
    public void bfs(ArrayList<Puzzle> open, ArrayList<Puzzle> close, Puzzle parent, Puzzle target) {
//    	System.out.println("��ʼ����");
    	if(this.isContains(close) == -1) {
    		int position = this.isContains(open);
    		if(position == -1) {
    			this.setParent(parent);
    			this.weight(target);
    			open.add(this);
    		}else {
    			if(this.getDepth() < open.get(position).getDepth()) {
    				open.remove(position);
    				this.setParent(parent);
    				this.weight(target);
    				open.add(this);
    			}
    		}
    	}
    	
    }

    

}
