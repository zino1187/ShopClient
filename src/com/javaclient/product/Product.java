/*상품 하나를 표현하는 객체정의 = 컴포넌트*/
package com.javaclient.product;
import java.awt.Canvas;
import java.awt.Checkbox;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Image;

import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;
public class Product extends JPanel{
	Canvas can;
	ImageIcon icon;
	Image img;
	JLabel la_name;
	JLabel la_price;
	Checkbox ch;
	String path;
	String product_id;
	String name;
	String price;
	
	public Product(String path,String product_id,String name,String price) {
		this.path=path;
		this.product_id=product_id;
		this.name=name;
		this.price=price;
		
		//이미지 생성...
		icon=new ImageIcon(path);
		img=icon.getImage();
		
		can = new Canvas(){
			public void paint(Graphics g) {
				g.drawImage(img, 0, 0, 146, 85, this);
			}
		};
		//켄버스 사이즈 지정 
		can.setPreferredSize(new Dimension(130, 100));
		la_name = new JLabel(name);
		la_price = new JLabel(price);
		ch = new  Checkbox();
		
		la_name.setPreferredSize(new Dimension(150, 15));
		la_price.setPreferredSize(new Dimension(95, 15));
		
		add(can);
		add(la_name);
		add(la_price);
		add(ch);
		this.setBackground(Color.WHITE);
		this.setPreferredSize(new Dimension(150, 150));
	}
	
}








