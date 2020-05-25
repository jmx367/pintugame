package cn.itcast.picture.ui;

import java.awt.Rectangle;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
//import java.util.Iterator;


import javax.swing.ImageIcon;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
/*
 * ƴͼ��
 */
@SuppressWarnings("serial")
public class PictureCanvas extends JPanel implements MouseListener{
	//��̬����
	public static int pictureID = 1;//ͼƬID
	public static int stepNum = 0;//�ƶ�����
	
	//��Ա����
	private Cell[] cell;//С����
	private boolean hasAddActionListener = false;//������ʾ�Ƿ�Ϊ��������˵�������������ֵΪ�棬δ���ֵΪfalse
	private Rectangle nullCell;
	//���췽��
	public PictureCanvas(){
	//����ƴͼ���Ĳ���
	this.setLayout(null);//֡����	
	//����12��ͼƬС������ӵ�ƴͼ��
	 cell = new Cell[12];
	for(int i=0;i<4;i++){//�����������
		for(int j=0;j<3;j++){//�����������
			//����ͼƬ
			//int num = i*3+j;//һ���ܺõ����˼·������������������������			
			ImageIcon icon = new ImageIcon("picture\\"+pictureID+"_"+(i*3+j+1)+".gif");
			//����ͼƬС����
			cell[i*3+j] = new Cell(icon);
			//ָ��ͼƬС������ʾ��λ��
			cell[i*3+j].setLocation(j*163+0,i*152+20);//setSize(163,152 );//490*610
			//��ͼƬС������ӵ�ƴͼ����ʾ
			this.add(cell[i*3+j]);
		}
	}
	//ɾ����12��ͼƬС����
	this.remove(cell[11]);//removeɾ�����
	nullCell = new Rectangle(326, 476, 163, 152);
	
	}
	//���¼���ͼƬ�������������ʾ
	public void reLoadPictureAddNumber(){
	//��ȡ��ÿһ��ͼƬС����
		for(int i=0;i<4;i++){//����
			for(int j=0;j<3;j++){//����
				//��ȡС����cell[i*3+j]						
				//����С������ʾ��ͼƬ
				ImageIcon icon = new ImageIcon("picture\\"+pictureID+"_"+(i*3+j+1)+".gif");
				cell[i*3+j].setIcon(icon);	
				//����С������ʾ������ʾ
				cell[i*3+j].setText(""+(i*3+j+1));
				cell[i*3+j].setVerticalTextPosition(this.getY()/2);
				cell[i*3+j].setHorizontalTextPosition(this.getX()/2);
			}
		}
	}
	//���¼���ͼƬ����ȥ��������ʾ
	public void reLoadPictureClearNumber(){
		for(int i=0;i<4;i++){//����
			for(int j=0;j<3;j++){//����
				//��ȡС����cell[i*3+j]						
				//����С������ʾ��ͼƬ
				ImageIcon icon = new ImageIcon("picture\\"+pictureID+"_"+(i*3+j+1)+".gif");
				cell[i*3+j].setIcon(icon);	
				//����С������ʾ������ʾ
				cell[i*3+j].setText("");
				cell[i*3+j].setVerticalTextPosition(this.getY()/2);
				cell[i*3+j].setHorizontalTextPosition(this.getX()/2);
			}
		}
		
	}
	//��С�����������λ������
	public void start(){
		//���Ҫ��û�и�С��������������Ļ�������Ӽ���
		if( !hasAddActionListener ){
			//��Ӽ���
			for(int i=0;i<11;i++){
				cell[i].addMouseListener(this);				
			}
			//���������������״̬
			hasAddActionListener = true;	
		}
		//�жϵ�ǰ��һ��С����������ϽǽϽ�ʱ�����з�����շ���Ļ���//
		//�����һ�����������Ͻǵ��ĸ�����λ���ڣ��Ͳ���ѭ�������з�����շ����λ�û���
		while(cell[0].getBounds().x<=163&&cell[0].getBounds().y<=172){
		//��ȡ���ո��λ��
		int nullX = nullCell.getBounds().x;	
		int nullY = nullCell.getBounds().y;		
		//�������һ�����򣬽��пշ�������ͨС����Ļ���
		//����0-3֮������������Ӧ�շ�������������ƶ�
		int direction = (int)(Math.random()*4);//0.1.2.3
		switch(direction){ //163***152
		case 0://�շ��������ƶ�������ߵķ�����л���λ�ã���෽��Ҫ�����ƶ�
			//nullX = nullX -150
			nullX -=163;
			cellMove(nullX,nullY,"RIGHT");
			break;
		case 1://�շ��������ƶ������ұߵķ�����л���λ�ã��Ҳ෽��Ҫ�����ƶ�
			nullX +=163;
			cellMove(nullX,nullY,"LEFT");
			break;
		case 2://�շ��������ƶ������ϱߵķ�����л���λ�ã��ϲ෽��Ҫ�����ƶ�
			nullY -=152;
			cellMove(nullX,nullY,"DOWN");
			break;
		case 3://�շ��������ƶ������±ߵķ�����л���λ�ã��²෽��Ҫ�����ƶ�
			nullY +=152;
			cellMove(nullX,nullY,"UP");
			break;
			
		default://
			break;
				
		}
		
		}
		
	}
	
	/*
	 * ������շ�����ƶ�
	 * nullX �շ����X������
	 * 
	 * int nullY �շ����Y������
	 * 
	 * direction ����Ҫ�ƶ��ķ���
	 */
	
	private void cellMove(int nullX, int nullY, String direction) {
	for(int i =0;i<11;i++){
		//��ȡ����շ���λ����ͬ��С����
		if(cell[i].getBounds().x == nullX && cell[i].getBounds().y == nullY){
		//��ǰ������ƶ�
		cell[i].move(direction);
		//�շ�����ƶ�
		nullCell.setLocation(nullX, nullY);	
		//����λ�ú󣬽���ѭ��	
		break;	
		}
		
	}
		
	}
	@Override
	public void mouseClicked(MouseEvent e) {
	}
	@Override
	public void mousePressed(MouseEvent e) {//��갴��
	//��ȡ����ǰ�������С����163*152
	Cell button =(Cell)e.getSource();
	//��ȡ����������X��Y����
	int clickX = button.getBounds().x;
	int clickY = button.getBounds().y;	
	//��ȡ��ǰ�շ����X��Y����
	int nullX = nullCell.getBounds().x;
	int nullY = nullCell.getBounds().y;
	//���бȽϣ����������������λ�õĽ���	
	if(clickX == nullX && clickY - nullY == 152){//�����Ϊ�շ�������ķ���
	button.move("UP");//������ķ��������ƶ�	
	} else if(clickX == nullX && clickY - nullY == -152){
	button.move("DOWN");//������ķ��������ƶ�	
	} else if(clickY == nullY && clickX -nullX == 163){
	button.move("LEFT");	
	} else if(clickY == nullY && clickX -nullX == -163){
	button.move("RIGHT");
	} else{
		return ;
	}
	//���¿շ����λ��
	nullCell.setLocation(clickX, clickY);
	//ƴͼ���������»���
	this.repaint();
	//���²���������Ϸ״̬���Ĳ�������
	stepNum++;
	PictureMainFrame.step.setText("������"+stepNum);
	
	//�жϵ�ǰ��Ϸ�Ƿ���ɣ�����ɣ������һ���Ѻõ���ʾ
	if(this.isFinish()){
		//����һ��������ʾ
		JOptionPane.showMessageDialog(this, "��ϲ�����ƴͼ������\n���ò�"+ stepNum);
		//����ÿһ��С�����ϵ�������������������С������������
		for(int i=0;i<11;i++){
			cell[i].removeMouseListener(this);
		}
		
		//���·���Ķ�����������״̬
		hasAddActionListener = false;		
	}
	
	}
	//�жϵ�ǰ��Ϸ�Ƿ����,���������ж�ƴͼ�Ƿ����
	private boolean isFinish(){
		for(int i=0 ;i<11; i++){
			//��ȡÿһ�������λ��
			int x = cell[i].getBounds().x;
			int y = cell[i].getBounds().y;
			if((y-20)/152*3+(x/163)!=i){
			return false;	
			}
			
			
		}
		
		
		return true;
	}
	@Override
	public void mouseReleased(MouseEvent e) {
		
		
	}
	@Override
	public void mouseEntered(MouseEvent e) {

		
	}
	@Override
	public void mouseExited(MouseEvent e) {		
	}		
}










