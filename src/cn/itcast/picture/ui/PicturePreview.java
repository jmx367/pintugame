package cn.itcast.picture.ui;

import java.awt.Graphics;
import java.awt.Image;

import javax.swing.ImageIcon;
import javax.swing.JPanel;
/*
 * 图片预览类//图片能在面板中显示的本质是java的一个绘图的功能，将图片绘制在指定的面板中
 * 需要覆写面板绘制的方法
 */
@SuppressWarnings("serial")
public class PicturePreview extends JPanel {
	//覆写绘制组件方法，实现图片的显示
	//1：找到图片 2：绘制图片
	@Override
	protected void paintComponent(Graphics g) {
		
		super.paintComponent(g);
		//指定当前图片的路径
		String filename = "picture\\"+PictureCanvas.pictureID+".jpg";
		//通过图片的路径，获取到对应图片中的图像
		ImageIcon icon = new ImageIcon(filename);
		Image image = icon.getImage();		
		//
		//把图像 绘制在 预览区的面板中
		g.drawImage(image, 0, 20, 490, 610, this);//图片大小应为490*610
	}	
}
