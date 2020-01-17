package V;


import java.awt.Color;
import java.awt.GridLayout;
import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

import javax.swing.JToolBar;
import javax.swing.border.TitledBorder;




import M.Model;
import M.PicturePreview;


public class View extends JFrame {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String[] levels = {"3x3", "4x4", "5x5"};
	private String[] pictures = {"图片一","图片二"};
	
	//控件创建
	JToolBar toolBar = new JToolBar("控制栏");
	JComboBox<String> levelBox = new JComboBox<>(levels);
	JComboBox<String> picturesBox = new JComboBox<>(pictures);
	JButton startBtn = new JButton("开始/打乱");
	JButton autoBtn = new JButton("显示答案");
	JButton saveBtn = new JButton("保存进度");
	JButton readBtn = new JButton("读取进度");
	JPanel leftPanel = new JPanel();
	JPanel rightPanel = new JPanel();
	PicturePreview picturePreview = new PicturePreview();
	GameView gameView = new GameView();
	
	//初始化
	private void init() {
		//设置标题 
		this.setTitle("Hym's Puzzle Game");
		//设置窗口大小
		this.setSize(1000, 700);
		//设置显示位置
		this.setLocation(150, 10);
		//设置为固定大小
		this.setResizable(false);
		//设置窗口的默认操作
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}
	
	//控件设置
	private void addComponent() {

		this.setLayout(null);
		
		toolBar.setFloatable(false);
		toolBar.add(startBtn);
		toolBar.add(autoBtn);
		toolBar.add(new JLabel("             选择图片"));
		toolBar.add(picturesBox);
		toolBar.add(new JLabel("             选择难度"));
		toolBar.add(levelBox);
		toolBar.add(saveBtn);
		toolBar.add(readBtn);
		toolBar.setBounds(0, 0, 990, 30);;
		
		
		//游戏区面板
		
		leftPanel.setBorder(new TitledBorder("游戏区"));
		leftPanel.setBackground(Color.lightGray);//背景色
		leftPanel.setBounds(0, 50, 490, 490);
		leftPanel.setLayout(new GridLayout(1,1));
		leftPanel.add(gameView);
		
		


		
		
		//游戏预览区面板
		
		rightPanel.setBorder(new TitledBorder("预览区"));
		rightPanel.setBackground(Color.lightGray);//背景色
		rightPanel.setLayout(new GridLayout(1,1));
		rightPanel.setBounds(500, 50, 490, 490);
		rightPanel.add(picturePreview);
		
		
		
		this.add(toolBar);
		this.add(leftPanel);
		this.add(rightPanel);


	}

	//控件Action
	private void addActionListeer() {
		
		//预览区
		this.picturesBox.addItemListener(new ItemListener() {
			
			@Override
			public void itemStateChanged(ItemEvent e) {
				int pictureNum = picturesBox.getSelectedIndex();
				Model.pictureId = pictureNum + 1;
				picturePreview.repaint();
				
				leftPanel.removeAll();
				gameView.reloadGameView();
				leftPanel.add(gameView);
				leftPanel.repaint();
			}
		});
		
		//游戏区
		this.levelBox.addItemListener(new ItemListener() {
			
			@Override
			public void itemStateChanged(ItemEvent e) {
				int curLevel = levelBox.getSelectedIndex();
				switch(curLevel) {
					case 0: 
						GameView.col = 3;
						GameView.row = 3;
						break;
					case 1: 
						GameView.col = 4;
						GameView.row = 4;
						break;
					case 2: 
						GameView.col = 5;
						GameView.row = 5;
						break;
				}
				//重绘
				leftPanel.removeAll();
				gameView.reloadGameView();
				leftPanel.add(gameView);
				leftPanel.repaint();
				gameView.hasActionListener = false;
			}
		});

		//开始打乱按钮
		this.startBtn.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				gameView.stat();
			}
		});
	
		//自动还原
		this.autoBtn.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				gameView.autoMove();
				
			}
		});
		
		//保存进度
		this.saveBtn.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				for(int i=0; i<gameView.startNums.length; i++) {
					System.out.print(gameView.startNums[i]);
				}
				System.out.println("已保存");
				
				File file = new File("Picture\\saveFile.txt");
				try {
					FileWriter out = new FileWriter(file);
					for(int i=0; i<gameView.startNums.length; i++) {
						out.write(gameView.startNums[i]+"\n");
					}
					out.close();
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				
				
			}
		});

		//读取进度
		this.readBtn.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent arg0) {
				File file = new File("Picture\\saveFile.txt");
				int[] readNums = new int[gameView.row*gameView.col];
				try {
					BufferedReader in = new BufferedReader(new FileReader(file));
					String line;
					int i=0;
					while((line = in.readLine()) != null) {
						String[] temp = line.split("\n");
						for(int j=0; j<temp.length; j++) {
							readNums[i++] = Integer.parseInt(temp[j]);
						}
					}
					in.close();
					

				} catch (IOException e) {
					e.printStackTrace();
				}
				
				for(int i=0; i<readNums.length; i++)
					System.out.print(readNums[i]);
				System.out.println("已读取");
				
				gameView.readPrevStatus(readNums);
			}
		});
		
	}
	
	//视图
	public View() {
		//初始化
		init();
		//添加控件
		addComponent();
		//添加动作
		addActionListeer();
		
	}

}
