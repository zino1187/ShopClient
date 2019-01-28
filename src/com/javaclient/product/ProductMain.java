package com.javaclient.product;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

import com.javaclient.main.ClientMain;
public class ProductMain extends JPanel{
	ClientMain clientMain;
	String path="C:/Users/itbank510/java_developer/data/";

	JPanel container;//��ǰ�� �ٰԵ� �г�
	JPanel p_south; //���� ��ư���� ��ġ�� ���� �г� 
	JButton bt_cart;
	
	//��ٱ��� ������ �� ����Ʈ ����!!!
	List cartList=new ArrayList(); //�÷��� �����ӿ� �� ������ �ִ� ��ü���� �ֻ��� ��ü(�������̽�)
	
	public ProductMain(ClientMain clientMain) {
		this.clientMain=clientMain;
		setPreferredSize(new Dimension(1300, 800));
		this.setLayout(new BorderLayout());
		container = new JPanel();
		p_south = new JPanel();
		p_south.setBackground(Color.YELLOW);
		add(container);
		add(p_south, BorderLayout.SOUTH);
		bt_cart = new JButton("��ٱ���");
		p_south.add(bt_cart);
		selectAll();
		
		//��ٱ��� ��ư�� ������ ���� 
		bt_cart.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int result=JOptionPane.showConfirmDialog(clientMain, "���� ������ ��ǰ�� ��ٱ��Ͽ� �������?");
				if(result ==JOptionPane.OK_OPTION){
					goCart();
				}
			}
		});
	}
	
	//�����ͺ��̽��� �����ϴ� ��ǰ��ŭ Product ��ü ����!!
	public void selectAll() {
		Connection con=clientMain.getCon();
		PreparedStatement pstmt=null;
		ResultSet rs=null;
		
		String sql="select * from product order by product_id asc";
		try {
			pstmt=con.prepareStatement(sql);
			rs=pstmt.executeQuery();
			
			while(rs.next()) {
				String filename=path+rs.getString("img");
				String product_id=rs.getString("product_id");
				String product_name=rs.getString("product_name");
				String price=rs.getString("price");
				
				Product product = new Product(this,filename,product_id,product_name,price);
				container.add(product);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			if(rs!=null) {
				try {
					rs.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
			if(pstmt!=null) {
				try {
					pstmt.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
		}
	}
	
	//��ٱ��� �г��� ȭ�鿡 ������!!
	public void goCart() {
		clientMain.showPage(1);
	}
}




















