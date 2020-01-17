

import java.awt.EventQueue;
import java.awt.List;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.border.TitledBorder;
import javax.swing.table.DefaultTableModel;
import javax.swing.JTable;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;

import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.beans.Statement;
import java.awt.event.ActionEvent;
import java.sql.*;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Vector;

public class View {

	private JFrame frame;
	private JTable list_table;
	private JTextField name_textField;
	private JTextField tel_textField;
	private JTextField address_textField;
	DefaultTableModel tableModel;
	

	static ResultSet rs = null;
	static Connection con = null;
	static PreparedStatement ps=null;
	static Vector rowData, columnNames;
	static int totalRow = 1;
	static Object[][] obRowData = new Object[26][5];
	
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		
		connectToDB();
		
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					//addBook a=new addBook();
					View window = new View();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public View() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setTitle("\u901A\u8BAF\u5F55");
		frame.setBounds(100, 100, 799, 535);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		JPanel man_panel = new JPanel(new BorderLayout());
		man_panel.setBorder(new TitledBorder(null, "\u8054\u7CFB\u4EBA\u5217\u8868", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		man_panel.setBounds(14, 13, 753, 198);
		frame.getContentPane().add(man_panel);
		//��ͷ
		Object[] columnNames = {"���", "����", "�绰", "��ַ"};
		//obRowData = new Object[totalRow][5];
		tableModel = new DefaultTableModel(obRowData, columnNames);

        man_panel.setLayout(new BorderLayout());
        list_table = new JTable(tableModel) {
        	public boolean isCellEditable(int row, int column)
            {
                return false;
            }
        };
        man_panel.add(list_table.getTableHeader(), BorderLayout.NORTH);
        // �� ������� ��ӵ���������
        man_panel.add(list_table, BorderLayout.CENTER);
        list_table.setBounds(72, 29, 404, 80);
        list_table.setFillsViewportHeight(true);
        list_table.getTableHeader().setResizingAllowed(false);               // ���ò������ֶ��ı��п�
        list_table.getTableHeader().setReorderingAllowed(false); 
        // ���ù�������ӿڴ�С�������ô�С�������ݣ���Ҫ�϶����������ܿ�����
        list_table.setPreferredScrollableViewportSize(new Dimension(400, 300));
        // �� ��� �ŵ� ������� �У���ͷ���Զ���ӵ�������嶥����
        JScrollPane scrollPane = new JScrollPane(list_table);

        man_panel.add(scrollPane);

		
		JPanel message_panel = new JPanel();
		message_panel.setBorder(new TitledBorder(null, "\u4FE1\u606F\u7A97\u53E3", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		message_panel.setBounds(24, 224, 365, 251);
		frame.getContentPane().add(message_panel);
		message_panel.setLayout(null);
		
		JLabel name_label = new JLabel("\u59D3\u540D");
		name_label.setBounds(43, 41, 41, 18);
		message_panel.add(name_label);
		
		
		JLabel telLabel = new JLabel("\u7535\u8BDD");
		telLabel.setBounds(43, 94, 41, 18);
		message_panel.add(telLabel);
		
		JLabel address_label = new JLabel("\u5730\u5740");
		address_label.setBounds(43, 142, 41, 18);
		message_panel.add(address_label);
		
		name_textField = new JTextField();
		name_textField.setBounds(144, 38, 101, 24);
		message_panel.add(name_textField);
		name_textField.setColumns(10);
		
		
		tel_textField = new JTextField();
		tel_textField.setBounds(144, 91, 101, 24);
		message_panel.add(tel_textField);
		tel_textField.setColumns(10);
		
		address_textField = new JTextField();
		address_textField.setBounds(144, 139, 172, 99);
		message_panel.add(address_textField);
		address_textField.setColumns(10);
		
		JButton confirm_Button = new JButton("\u786E\u5B9A");
		confirm_Button.setBounds(275, 37, 76, 27);
		message_panel.add(confirm_Button);
		confirm_Button.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent arg0) {
				String newName = name_textField.getText();
				if(newName.isEmpty()) {
					JOptionPane.showMessageDialog(message_panel, "��������Ϊ��", "����",JOptionPane.WARNING_MESSAGE);
				} else {
					System.out.println("����½���ϵ������" + newName);
				}
				
			}
		});
		
		JButton cancel_Button = new JButton("\u53D6\u6D88");
		cancel_Button.setBounds(275, 85, 76, 27);
		message_panel.add(cancel_Button);
		cancel_Button.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent arg0) {
				String newTel = tel_textField.getText();
				if(newTel.isEmpty()) {
					JOptionPane.showMessageDialog(message_panel, "�绰����Ϊ��", "����",JOptionPane.WARNING_MESSAGE);
				} else {
					System.out.println("����½���ϵ������" + newTel);
				}
				
				
			}
		});
		
		JPanel control_panel = new JPanel();
		control_panel.setBorder(new TitledBorder(null, "\u64CD\u4F5C\u7A97\u53E3", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		control_panel.setBounds(393, 224, 374, 251);
		frame.getContentPane().add(control_panel);
		control_panel.setLayout(null);
		
		/*
		 * �����Ϣ
		 */
		JButton addMes_Btn = new JButton("\u6DFB\u52A0\u8054\u7CFB\u4EBA\u4FE1\u606F");
		addMes_Btn.setBounds(14, 25, 346, 27);
		control_panel.add(addMes_Btn);
		addMes_Btn.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				String nameToDB = name_textField.getText();
				String phoneToDB = tel_textField.getText();
				String addressToDB = address_textField.getText();
				if(nameToDB != null && phoneToDB != null) {
					String values = "'" + nameToDB +"','" + phoneToDB + "','" + addressToDB +"'";
					insertToDB(values);
					Vector addRowData = new Vector();
					addRowData.add(nameToDB);
					addRowData.add(phoneToDB);
					addRowData.add(addressToDB);
					tableModel.addRow(addRowData);
					System.out.println("�������ݣ�" + values);
				} else {
					JOptionPane.showMessageDialog(control_panel, "����Ϊ��", "����",JOptionPane.WARNING_MESSAGE);
				}
			}
		});
		
		/*
		 * �޸���Ϣ
		 */
		JButton changemes_btn = new JButton("\u4FEE\u6539\u9009\u4E2D\u8054\u7CFB\u4EBA\u4FE1\u606F");
		changemes_btn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				int curRow = list_table.getSelectedRow();
				String Iname = name_textField.getText();
				String Iphone = tel_textField.getText();
				String Iaddress = address_textField.getText();
				if(curRow != -1) {
					if(!Iname.isEmpty() && !Iphone.isEmpty()) {
						Object id = list_table.getValueAt(curRow, 0);
						
						//System.out.println(id);
						updateToDB(id, Iname, Iphone, Iaddress);
						tableModel.setValueAt(Iname, curRow, 1);
						tableModel.setValueAt(Iphone, curRow, 2);
						tableModel.setValueAt(Iaddress, curRow, 3);
						
					} else {
						JOptionPane.showMessageDialog(control_panel, "������޸�����", "����",JOptionPane.WARNING_MESSAGE);
					}
				} else {
					JOptionPane.showMessageDialog(control_panel, "��ѡ���޸�����", "����",JOptionPane.WARNING_MESSAGE);
				}
				
			}
		});
		changemes_btn.setBounds(14, 78, 346, 27);
		control_panel.add(changemes_btn);
		
		
		/*
		 * ɾ��
		 */
		JButton delete_btn = new JButton("\u5220\u9664\u9009\u4E2D\u8054\u7CFB\u4EBA\u4FE1\u606F");
		delete_btn.setBounds(14, 129, 346, 27);
		control_panel.add(delete_btn);
		delete_btn.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				int curRow = list_table.getSelectedRow();

				if(curRow != -1) {
					Object id = list_table.getValueAt(curRow, 0);
					deleteToDB(id);
					tableModel.removeRow(curRow);
				} else {
					JOptionPane.showMessageDialog(control_panel, "��ѡ��ɾ������", "����",JOptionPane.WARNING_MESSAGE);
				}
				
			}
		});
		
		JButton about_btn = new JButton("\u5173\u4E8E\u8F6F\u4EF6");
		about_btn.setBounds(14, 185, 346, 27);
		control_panel.add(about_btn);
	}
	
	
	
	
	
	/*
	 * �������ݿ�
	 */
	private static void connectToDB() {
		int n = 0;
		
		try {
			Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
			String url = "jdbc:sqlserver://localhost:1433;databaseName=AddressBook";
			con = DriverManager.getConnection(url,"sa","123456");
			System.out.println("���ݿ����ӳɹ�");

	        String sql;
	        sql = "SELECT Fid, Fname, Fphone, Faddress FROM bookTable";

	        ps = con.prepareStatement(sql);
	        rs = ps.executeQuery();
	        
	        // չ����������ݿ�
	        while(rs.next()){
            	obRowData[n][0] = rs.getString("Fid");
            	obRowData[n][1] = rs.getString("Fname");
            	obRowData[n][2] = rs.getString("Fphone");
            	obRowData[n][3] = rs.getString("Faddress");
            	n++;
            }
	        totalRow = n;
	        // ��ɺ�ر�
	        rs.close();
	        ps.close();
			con.close();
		}
		catch(Exception e) {
			System.out.println("���ݿ�����ʧ��\n" + e.toString());
		}
	}
	
	/*
	 * ��������
	 */
	private static void insertToDB(String values) {
		java.sql.Statement st = null;
		try {
			Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
			String url = "jdbc:sqlserver://localhost:1433;databaseName=AddressBook";
			con = DriverManager.getConnection(url,"sa","123456");
			con.setAutoCommit(false);
			st = con.createStatement();
			System.out.println("���ݿ����ӳɹ���׼�����");
	        values = "INSERT INTO bookTable VALUES(" + values+")";//�����ݿ��в������� 
	        st.executeUpdate(values);
	        con.commit();
	        
	        // ��ɺ�ر�
	        st.close();
	        con.close();
	        System.out.println("�������ݳɹ�");
		}
		catch(Exception e) {
			System.out.println("�������ݿ�����ʧ��\n" + e.toString());
		}
	}

	/*
	 * �޸�����
	 */
	private static void updateToDB(Object id, String name, String phone, String address) {
		String sID = (String)id;
		String sql = "update bookTable set ";
		sql += "Fname = '" + name + "'," ;
		sql += "Fphone = '" + phone + "',";
		sql += "Faddress = '" + address + "'";
		sql += "where Fid = " + sID;
		//System.out.println(sql);
		
		java.sql.Statement st = null;
		try {
			Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
			String url = "jdbc:sqlserver://localhost:1433;databaseName=AddressBook";
			con = DriverManager.getConnection(url,"sa","123456");
			con.setAutoCommit(false);
			st = con.createStatement();
			System.out.println("���ݿ����ӳɹ���׼���޸�");
	        st.execute(sql);
	        con.commit();
	        
	        // ��ɺ�ر�
	        st.close();
	        con.close();
	        System.out.println("�޸����ݳɹ�");
		}
		catch(Exception e) {
			System.out.println("�޸����ݿ�����ʧ��\n" + e.toString());
		}
	}

	/*
	 * ɾ������
	 */
	private static void deleteToDB(Object id) {
		String sql = "delete from bookTable where Fid=";
		String sID = (String) id;
		sql += sID;
		
		
		java.sql.Statement st = null;
		try {
			Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
			String url = "jdbc:sqlserver://localhost:1433;databaseName=AddressBook";
			con = DriverManager.getConnection(url,"sa","123456");
			con.setAutoCommit(false);
			st = con.createStatement();
			System.out.println("���ݿ����ӳɹ���׼��ɾ��");
	        st.execute(sql);
	        con.commit();
	        
	        
	        // ��ɺ�ر�
	        st.close();
	        con.close();
	        System.out.println("ɾ�����ݳɹ�");
		}
		catch(Exception e) {
			System.out.println("�޸����ݿ�ʧ��\n" + e.toString());
		}
	}

	
	

}
