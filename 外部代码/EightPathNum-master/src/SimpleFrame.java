import java.awt.BorderLayout;
import java.awt.Button;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.Menu;
import java.awt.MenuBar;
import java.awt.MenuItem;
import java.awt.Panel;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.UIManager;





public class SimpleFrame extends JFrame implements ActionListener,KeyListener{

//	private static final int DEFAULT_WIDTH = 800;
//	private static final int DEFAULT_HEIGHT = 800;

	MenuBar menubar=new MenuBar();
	Menu menu_file = new Menu("帮助");
	MenuItem restart = new MenuItem("新的拼图");
	MenuItem printPath = new MenuItem("还原");
	MenuItem exit = new MenuItem("退出");
	
	Button[] button;
	Panel panel;
	int row,col;
	private static int position,cellNum;
	StringBuffer bestPath = new StringBuffer();
	int pathLength=-1;
	int a[] = new int[9];
	
	final int dr[] = { 0,-1, 0, 1};
	final int dc[] = {-1, 0, 1, 0};
	
	public SimpleFrame(int row,int col) {
		super.setMenuBar(menubar);
		//Windows风格 
		try {
			UIManager.setLookAndFeel("com.sun.java.swing.plaf.windows.WindowsLookAndFeel");
		} catch (Exception e) {
			e.printStackTrace();
		}
		this.row = row;
		this.col = col;
		cellNum = row*col;
		
		position = cellNum - 1;
		
		restart.addActionListener(this);
		exit.addActionListener(this);
		printPath.addActionListener(this);
		menu_file.add(restart);
		menu_file.add(printPath);
		menu_file.add(exit);
		menubar.add(menu_file);
		
		panel = new Panel(new GridLayout(row,col)) ;
		
		//初始化button
		button = new Button[cellNum];
		for(int i = 0; i < cellNum; i++) {
			if(i == cellNum - 1) {
				button[i] = new Button(" ");
			}else {
				button[i] = new Button(String.valueOf(i + 1));
			}
			button[i].setFont(new Font("Courier", 1, 20));
			button[i].addActionListener(this);
			button[i].addKeyListener(this);
			panel.add(button[i]);
		}
		
		this.add(BorderLayout.CENTER,panel);
		this.setTitle("拼图");
		this.setVisible(true);
		this.setSize(400,400);
		
		Toolkit kit = Toolkit.getDefaultToolkit();
		Dimension screenSize = kit.getScreenSize();
		int screenWidth = screenSize.width/2;
		int screenHeight = screenSize.height/2;
		int height = this.getHeight();
		int width = this.getWidth();
		this.setLocation(screenWidth-width/2, screenHeight-height/2);
		this.addWindowListener(new WindowAdapter() {
			public void windowClosing(WindowEvent e) {
				System.exit(0);
			}
		});

	}
	
	void start() {


		
		Puzzle target = new Puzzle();
		Puzzle start = new Puzzle();
		target.setNum(Model.targetState);
		start.setNum(a);
		
		System.out.println("----------生成随机状态----------");	
		Puzzle best = new Puzzle();
		if(start.isSolvable(target)) {
			System.out.println("----------开始复原----------");
			best = DoPuzzle.doRestore(start, target);
			
		}else {
			JOptionPane.showMessageDialog(this,"此状态无解请生成新的拼图");
		}

		//存储最优解
		while(best != null) {
			DoPuzzle.result[++pathLength] = best.getNum();
			best = best.getParent();
		}
		//存储最优路径
		bestPath = DoPuzzle.getbestPath(pathLength);
		
		//打印拼图
		DoPuzzle.printPuzzleRoute(DoPuzzle.result, pathLength);
		//打印路径
		System.out.println(bestPath);		
		System.out.println("结束");	
	}
	
	boolean win() {
		for(int i = 0; i < cellNum - 1; i++) {
			if(button[i].getLabel().equals(" ")) {
				return false;
			}else if(Integer.valueOf(button[i].getLabel()) != i+1) {
				return false;
			}
		}
		return true;
	}

	private boolean judge(Button a, Button b) {
		for(int i = 0; i < 4; i++) {
			if( (a.getX() == b.getX() + dr[i]*a.getWidth()) 
					&& (a.getY() == b.getY() + dc[i]*a.getHeight())) {
				return true;
			}
		}
		return false;
	}
	
	public void actionPerformed(ActionEvent e) {
		if(e.getSource() == restart) {
			Model.creatRandomState();
			a = Model.startState;
			for(int i = 0; i < 9; i++) {
				button[i].setLabel(String.valueOf(a[i]));
				if(a[i] == 0) {
					position = i;
					button[i].setLabel(" ");
				}
			}
			
			return;
		}else if(e.getSource() == exit) {
			System.exit(0);
			return;
		}else if(e.getSource() == printPath) {
			long time1 = System.currentTimeMillis();
			start();
			long time2 = System.currentTimeMillis();
			System.out.println((time2-time1)/ 1000 + "s");
			StringBuffer path = bestPath;
			for(int i = 0; i < path.length(); i++) {
				switch(path.charAt(i)) {
				case 'U':
					go(KeyEvent.VK_UP);
					break;
				case 'D':
					go(KeyEvent.VK_DOWN);
					break;
				case 'L':
					go(KeyEvent.VK_LEFT);
					break;
				case 'R':
					go(KeyEvent.VK_RIGHT);
					break;
				}
				try {
					Thread.sleep(1000);
				} catch (InterruptedException e1) {
					e1.printStackTrace();
				}
			}
		}
		
		for(int i = 0; i < cellNum; i++) {
			if(e.getSource() == button[i]) {
				if(!button[i].getLabel().equals(" ") && judge(button[i],button[position])) {
					button[position].setLabel(button[i].getLabel());
					button[i].setLabel(" ");
					position = i;
				}
			}
		}
		
		if(win()) {
			JOptionPane.showMessageDialog(this,"成功还原");
		}
	}
	
	void go(int dir) {
		int x = position / col;
		int y = position % col;
		switch(dir) {
		case KeyEvent.VK_UP:
			if(x != 0) {
				button[position].setLabel(button[position-col].getLabel());
				button[position-col].setLabel(" ");
				position -= col;
			}
			break;
		case KeyEvent.VK_DOWN:
			if(x != row-1) {
				button[position].setLabel(button[position+col].getLabel());
				button[position+col].setLabel(" ");
				position += col;
			}
			break;
		case KeyEvent.VK_LEFT:
			if(y != 0) {
				button[position].setLabel(button[position-1].getLabel());
				button[position-1].setLabel(" ");
				position -= 1;
			}
			break;
		case KeyEvent.VK_RIGHT:
			if(y != col-1) {
				button[position].setLabel(button[position+1].getLabel());
				button[position+1].setLabel(" ");
				position += 1;
			}
			break;
		}
	}

	@Override
	public void keyPressed(KeyEvent e) {
		go(e.getKeyCode());
		if(win()) {
			JOptionPane.showMessageDialog(this,"Congratulations");
		}
	}

	@Override
	public void keyReleased(KeyEvent e) {}

	@Override
	public void keyTyped(KeyEvent e) {}


	
}
