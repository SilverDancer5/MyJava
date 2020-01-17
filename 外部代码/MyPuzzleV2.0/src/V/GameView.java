package V;

import java.awt.Graphics;
import java.awt.Image;
import java.awt.Rectangle;
import java.awt.event.MouseListener;
import java.awt.image.BufferedImage;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.HashSet;
import java.util.Random;
import java.util.Set;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JOptionPane;
import javax.swing.JPanel;



import M.Model;
import M.Puzzle;



public class GameView extends JPanel implements MouseListener{
	
	
	private static final long serialVersionUID = 1L;
	Cell[] cell = new Cell[25];
	private Cell nullCell = cell[0];
	int[] startNums;
	private int WIDTH = 450 / col;
	private int HEIGHT = 450 / row;
	
	public static int row = 3;
	public static int col = 3;
	public boolean hasActionListener = false;


	//��ʼ��
	public GameView() {		
		reloadGameView();
	}
	
	//��ʼ��
	public void reloadGameView() {
		this.removeAll();
		this.setLayout(null);
		
		startNums = new int[row*col];
		for(int i=0; i<row*col; i++) {
			startNums[i] = i;
		}
		
		WIDTH = 450 / col;
		HEIGHT = 450 / row;
		String name = "Picture\\" + Model.pictureId +".jpg";
		try {
			BufferedImage image = ImageIO.read(new FileInputStream(name));
			image = zoomImage(image);
			for(int i=0; i<row*col;i++) {
				String n = String.valueOf(i);
				Image piece = image.getSubimage(i%col*WIDTH, i/col*WIDTH, WIDTH, HEIGHT);
				ImageIcon icon = new ImageIcon();
				icon.setImage(piece);
				if(i == 0) {
					cell[i] = new Cell(n);
					cell[i].setBounds(i%col*WIDTH, i/col*WIDTH, WIDTH, HEIGHT);
					this.add(cell[i]);
					continue;
				}
				cell[i] = new Cell(n, icon);
				cell[i].setBounds(i%col*WIDTH, i/col*WIDTH, WIDTH, HEIGHT);
				this.add(cell[i]);
			}
			
			nullCell = cell[0];
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	//ͼƬ����
	public BufferedImage zoomImage(BufferedImage prevImage) {
		int w = 450, h = 450;
		BufferedImage image = new BufferedImage(w, h, BufferedImage.TYPE_INT_BGR);
        Graphics graphics = image.createGraphics();
        graphics.drawImage(prevImage, 0, 0, w, h, null);
        return image;
	}

	//��Ϸ����
	public void stat() {
		int count = row*col;
		if(hasActionListener == false) {
			for(int i=0; i<count; i++) {
				if(cell[i] != null)
					cell[i].addMouseListener(this);
				else
					System.out.println("null" + i);
			
			}
			hasActionListener = true;
		}
		
		startNums = creatSolvabelPuzzleNums(count);
		
		disorder(startNums);
		
	}
	
	//��ȡ����
	public void readPrevStatus(int[] nums) {
		int count = row*col;
		if(hasActionListener == false) {
			for(int i=0; i<count; i++) {
				if(cell[i] != null)
					cell[i].addMouseListener(this);
				else
					System.out.println("null" + i);
			
			}
			hasActionListener = true;
		}
		disorder(nums);
	}

	
	//�ܷ��ƶ�
	public boolean canMove(Cell clickCell) {
		int w = 450 / col;
		int h = 450 / row; 
		int clickCellX = clickCell.getBounds().x;
		int clickCellY = clickCell.getBounds().y;
		int nullCellX = nullCell.getBounds().x;
		int nullCellY = nullCell.getBounds().y;
		
		if(((clickCellX-h) == nullCellX && clickCellY == nullCellY) ||
		   ((clickCellX+h) == nullCellX && clickCellY == nullCellY) ||
		   (clickCellX == nullCellX && (clickCellY-w) == nullCellY) ||
		   (clickCellX == nullCellX && (clickCellY+w) == nullCellY))
			return true;
		else
			return false;
	}
	
	//�н��������
	public void disorder(int[] startNums) {	
		for(int i = 0; i<startNums.length; i++) {
			int x = i%col*WIDTH;
			int y = i/col*HEIGHT;
 			cell[startNums[i]].setBounds(x, y, WIDTH, HEIGHT);				
		}
	}

	//�Զ���ԭ
	public void autoMove() {
		String path = getSolution();
		System.out.println(path);
		
		for(int i=1; i<path.length(); i++) {
			int beforePosition = path.charAt(i-1)-48;
			int afterPosition = path.charAt(i) - 48;
			if(afterPosition - beforePosition == row)
				System.out.print("��");
			if(afterPosition - beforePosition == -row)
				System.out.print("��");
			if(afterPosition - beforePosition == -1)
				System.out.print("��");
			if(afterPosition - beforePosition == 1)
				System.out.print("��");
			
		}
		
//		int[] curNums = startNums;
//		for(int i=0; i<path.length(); i++) {
//			repaint();
//			System.out.println("");
//			int position = path.charAt(i) - 48;
//			//Ҫ������cell����
//			int x = position%col*WIDTH;
//			int y = position/col*HEIGHT;
//			
//			//�ո������
//			Rectangle zeroP = cell[0].getBounds();
//			
//			cell[0].setBounds(x, y, WIDTH, HEIGHT);
//			cell[startNums[position]].setBounds(zeroP);
//			
//			for(int j=0; j<row*col; j++) {
//				if(startNums[j] == 0) {
//					int temp = startNums[j];
//					startNums[j] = startNums[position];
//					startNums[position] = temp;
//					break;
//				}
//			}
//		}

    }

	//�ƶ�
	public void move(Cell clickCell) {

		Rectangle clickCellPosition = clickCell.getBounds();
		Rectangle nullCellPosition = cell[0].getBounds();
		Rectangle tempPosition = cell[0].getBounds();
		
		nullCellPosition = clickCellPosition;
		clickCellPosition = tempPosition;
		
		clickCell.setBounds(clickCellPosition);
		nullCell.setBounds(nullCellPosition);
		repaint();
		
	}
	
	//�жϿɽ�
	public boolean isSolvable(int[] startNums, int[] targetNums) {
		int sum = 0;
		for(int i = 0; i<(col*row); i++) {
			for(int j=0; j<i; j++) {
				if(startNums[j] > startNums[i])
					sum++;
				if(targetNums[j] > targetNums[i])
					sum++;
			}
		}
		if(sum%2 == 0)
			return true;
		
		return false;
	}
	
	//���ɿɽ�ƴͼ
	public int[] creatSolvabelPuzzleNums(int length) {	
		
		int[] targetNums = new int[length];
		int[] startNums = new int[length];
		for(int i=0; i<(length); i++) {
			targetNums[i] = i;
		}
		do {
			int k = length-1;
			Random random = new Random();
			Set<Integer> set = new HashSet<Integer>();
			while(set.size() < (length-1)) {
				int n = random.nextInt((length-1)) + 1;
				if(!set.contains(n)) {
					set.add(n);
					startNums[k] = n;
					k--;
				}
			}
			startNums[k] = 0;
		}while(isSolvable(startNums, targetNums) == false);
		
		return startNums;
	}
	
	//�ж����
	private boolean isFinish() {
		for (int i = 0; i < row*col; i++) {
			int x = cell[i].getBounds().x;
			int y = cell[i].getBounds().y;
			if (x/WIDTH + y/HEIGHT*row != i) {
				return false;
			}
		}
		return true;
	}

	//��ý�
	public String getSolution() {
		ArrayList<Puzzle> open = new ArrayList<Puzzle>();
        ArrayList<Puzzle> close = new ArrayList<Puzzle>();
        Puzzle start = new Puzzle(row, col);
        Puzzle target = new Puzzle(row, col);
        Puzzle best = new Puzzle(row, col);
        String shortestPath = "";
        
        int[] targetNums = new int[col*row];
        for(int i=0; i<row*col; i++)
        	targetNums[i] = i;
        
        start.setNum(startNums);
        target.setNum(targetNums);
        long startTime=System.currentTimeMillis();   //��ȡ��ʼʱ��
        if(start.isSolvable(target)){
            //��ʼ����ʼ״̬
            start.init(target);            
            open.add(start);
            while(open.isEmpty() == false){
                Collections.sort(open);            //����evaluation��ֵ����
                best = open.get(0);    //��open����ȡ����С��ֵ��״̬���Ƴ�open��
                open.remove(0);
                close.add(best);
                if(best.isTarget(target)){            
                    //���
                    best.printRoute();
                    shortestPath = best.shortestPath();
                    long end=System.currentTimeMillis(); //��ȡ����ʱ��  
                    System.out.println("��������ʱ�䣺 "+(end-startTime)+"ms");
                    break;
                }
                int move;
                if(best.isMoveUp()){
                    move = 0;
                    Puzzle up = best.moveUp(move);
                    up.operation(open, close, best, target);
                }
                if(best.isMoveDown()){
                    move = 1;
                    Puzzle up = best.moveUp(move);
                    up.operation(open, close, best, target);
                }
                if(best.isMoveLeft()){
                    move = 2;
                    Puzzle up = best.moveUp(move);
                    up.operation(open, close, best, target);
                }
                if(best.isMoveRight()){
                    move = 3;
                    Puzzle up = best.moveUp(move);
                    up.operation(open, close, best, target);
                }
                
            }
        }else 
            System.out.println("û�н⣬���������롣");
        return shortestPath;
	}
	
	//���
	@Override
	public void mousePressed(java.awt.event.MouseEvent e) {
		//��ȡ��ǰ����ķ���
		Cell clickCell = (Cell) e.getSource();
		if(canMove(clickCell))
		{
			move(clickCell);
		}
		else
			System.out.println("���ɽ���");
		
		if(isFinish()){
			JOptionPane.showMessageDialog(this, "��ϲ�����");
			//����С����ļ����¼�
			for (int i = 0; i < row*col; i++) {
				cell[i].removeMouseListener(this);
			}
			hasActionListener = false;
		}
	}
	
	@Override
	public void mouseClicked(java.awt.event.MouseEvent e) {

	}

	@Override
	public void mouseEntered(java.awt.event.MouseEvent e) {
		
		
	}

	@Override
	public void mouseExited(java.awt.event.MouseEvent e) {
		
	}

	@Override
	public void mouseReleased(java.awt.event.MouseEvent e) {
		
	}


}
