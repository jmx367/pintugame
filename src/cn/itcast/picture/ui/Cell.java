package cn.itcast.picture.ui;

import javax.swing.Icon;
import javax.swing.JButton;
/*
 * ͼƬС������
 */
@SuppressWarnings("serial")
public class Cell extends JButton {
	//����ͼƬ��С����
	public Cell(Icon icon) {
		super(icon);
		//����С�����С
		this.setSize(163,152 );//490*610
		
		
	}
	//����ͼƬ���Ҵ������ֵ�С����
	public Cell(String text, Icon icon) {
		super(text, icon);
		//����С�����С
		this.setSize(163,152 );//490*610
		this.setHorizontalTextPosition(CENTER);//����ˮƽ������ʾ
		this.setVerticalTextPosition(CENTER);//���ִ�ֱ������ʾ
	}
	//��ǰ������ƶ�
	public void move(String direction){//�ϡ��¡�����
	switch(direction){
	case "UP":
		this.setLocation(this.getBounds().x, this.getBounds().y-152);
		break;
	case "DOWN":
		this.setLocation(this.getBounds().x, this.getBounds().y+152);
		break;
	case "LEFT":
		this.setLocation(this.getBounds().x-163, this.getBounds().y);
		break;
	case "RIGHT":
		this.setLocation(this.getBounds().x+163, this.getBounds().y);
		break;
	default://�����������������
		break;
	}
		
	}
	

}
