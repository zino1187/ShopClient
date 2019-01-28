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

	JPanel container;//상품이 붙게될 패널
	JPanel p_south; //각종 버튼들이 위치할 남쪽 패널 
	JButton bt_cart;
	
	//장바구니 역할을 할 리스트 선언!!!
	List cartList=new ArrayList(); //컬렉션 프레임웍 중 순서가 있는 객체들의 최상위 객체(인터페이스)
	
	public ProductMain(ClientMain clientMain) {
		this.clientMain=clientMain;
		setPreferredSize(new Dimension(1300, 800));
		this.setLayout(new BorderLayout());
		container = new JPanel();
		p_south = new JPanel();
		p_south.setBackground(Color.YELLOW);
		add(container);
		add(p_south, BorderLayout.SOUTH);
		bt_cart = new JButton("장바구니");
		p_south.add(bt_cart);
		selectAll();
		
		//장바구니 버튼과 리스너 연결 
		bt_cart.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int result=JOptionPane.showConfirmDialog(clientMain, "개의 선택한 상품을 장바구니에 담을까요?");
				if(result ==JOptionPane.OK_OPTION){
					goCart();
				}
			}
		});
	}
	
	//데이터베이스에 존재하는 상품만큼 Product 객체 생성!!
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
	
	//장바구니 패널을 화면에 보여줌!!
	public void goCart() {
		clientMain.showPage(1);
	}
}




















