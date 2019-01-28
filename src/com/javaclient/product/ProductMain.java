package com.javaclient.product;

import java.awt.Dimension;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.swing.JPanel;

import com.javaclient.main.ClientMain;
public class ProductMain extends JPanel{
	ClientMain clientMain;
	String path="C:/Users/itbank510/java_developer/data/";
	
	public ProductMain(ClientMain clientMain) {
		this.clientMain=clientMain;
		setPreferredSize(new Dimension(1300, 850));
		
		selectAll();
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
				
				Product product = new Product(filename,product_id,product_name,price);
				add(product);
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
	
}




















