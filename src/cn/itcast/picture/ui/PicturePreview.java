package cn.itcast.picture.ui;

import java.awt.Graphics;
import java.awt.Image;

import javax.swing.ImageIcon;
import javax.swing.JPanel;
/*
 * ͼƬԤ����//ͼƬ�����������ʾ�ı�����java��һ����ͼ�Ĺ��ܣ���ͼƬ������ָ���������
 * ��Ҫ��д�����Ƶķ���
 */
@SuppressWarnings("serial")
public class PicturePreview extends JPanel {
	//��д�������������ʵ��ͼƬ����ʾ
	//1���ҵ�ͼƬ 2������ͼƬ
	@Override
	protected void paintComponent(Graphics g) {
		
		super.paintComponent(g);
		//ָ����ǰͼƬ��·��
		String filename = "picture\\"+PictureCanvas.pictureID+".jpg";
		//ͨ��ͼƬ��·������ȡ����ӦͼƬ�е�ͼ��
		ImageIcon icon = new ImageIcon(filename);
		Image image = icon.getImage();		
		//
		//��ͼ�� ������ Ԥ�����������
		g.drawImage(image, 0, 20, 490, 610, this);//ͼƬ��СӦΪ490*610
	}	
}
