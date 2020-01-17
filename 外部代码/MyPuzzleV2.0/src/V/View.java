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
	private String[] pictures = {"ͼƬһ","ͼƬ��"};
	
	//�ؼ�����
	JToolBar toolBar = new JToolBar("������");
	JComboBox<String> levelBox = new JComboBox<>(levels);
	JComboBox<String> picturesBox = new JComboBox<>(pictures);
	JButton startBtn = new JButton("��ʼ/����");
	JButton autoBtn = new JButton("��ʾ��");
	JButton saveBtn = new JButton("�������");
	JButton readBtn = new JButton("��ȡ����");
	JPanel leftPanel = new JPanel();
	JPanel rightPanel = new JPanel();
	PicturePreview picturePreview = new PicturePreview();
	GameView gameView = new GameView();
	
	//��ʼ��
	private void init() {
		//���ñ��� 
		this.setTitle("Hym's Puzzle Game");
		//���ô��ڴ�С
		this.setSize(1000, 700);
		//������ʾλ��
		this.setLocation(150, 10);
		//����Ϊ�̶���С
		this.setResizable(false);
		//���ô��ڵ�Ĭ�ϲ���
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}
	
	//�ؼ�����
	private void addComponent() {

		this.setLayout(null);
		
		toolBar.setFloatable(false);
		toolBar.add(startBtn);
		toolBar.add(autoBtn);
		toolBar.add(new JLabel("             ѡ��ͼƬ"));
		toolBar.add(picturesBox);
		toolBar.add(new JLabel("             ѡ���Ѷ�"));
		toolBar.add(levelBox);
		toolBar.add(saveBtn);
		toolBar.add(readBtn);
		toolBar.setBounds(0, 0, 990, 30);;
		
		
		//��Ϸ�����
		
		leftPanel.setBorder(new TitledBorder("��Ϸ��"));
		leftPanel.setBackground(Color.lightGray);//����ɫ
		leftPanel.setBounds(0, 50, 490, 490);
		leftPanel.setLayout(new GridLayout(1,1));
		leftPanel.add(gameView);
		
		


		
		
		//��ϷԤ�������
		
		rightPanel.setBorder(new TitledBorder("Ԥ����"));
		rightPanel.setBackground(Color.lightGray);//����ɫ
		rightPanel.setLayout(new GridLayout(1,1));
		rightPanel.setBounds(500, 50, 490, 490);
		rightPanel.add(picturePreview);
		
		
		
		this.add(toolBar);
		this.add(leftPanel);
		this.add(rightPanel);


	}

	//�ؼ�Action
	private void addActionListeer() {
		
		//Ԥ����
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
		
		//��Ϸ��
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
				//�ػ�
				leftPanel.removeAll();
				gameView.reloadGameView();
				leftPanel.add(gameView);
				leftPanel.repaint();
				gameView.hasActionListener = false;
			}
		});

		//��ʼ���Ұ�ť
		this.startBtn.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				gameView.stat();
			}
		});
	
		//�Զ���ԭ
		this.autoBtn.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				gameView.autoMove();
				
			}
		});
		
		//�������
		this.saveBtn.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				for(int i=0; i<gameView.startNums.length; i++) {
					System.out.print(gameView.startNums[i]);
				}
				System.out.println("�ѱ���");
				
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

		//��ȡ����
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
				System.out.println("�Ѷ�ȡ");
				
				gameView.readPrevStatus(readNums);
			}
		});
		
	}
	
	//��ͼ
	public View() {
		//��ʼ��
		init();
		//��ӿؼ�
		addComponent();
		//��Ӷ���
		addActionListeer();
		
	}

}
