package cn.itcast.picture.ui;

import java.awt.Rectangle;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
//import java.util.Iterator;


import javax.swing.ImageIcon;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
/*
 * 拼图类
 */
@SuppressWarnings("serial")
public class PictureCanvas extends JPanel implements MouseListener{
	//静态变量
	public static int pictureID = 1;//图片ID
	public static int stepNum = 0;//移动步数
	
	//成员变量
	private Cell[] cell;//小方格
	private boolean hasAddActionListener = false;//用来表示是否为方格添加了点击监听，添加了值为真，未添加值为false
	private Rectangle nullCell;
	//构造方法
	public PictureCanvas(){
	//设置拼图区的布局
	this.setLayout(null);//帧布局	
	//创建12个图片小方格，添加到拼图区
	 cell = new Cell[12];
	for(int i=0;i<4;i++){//代表的是行数
		for(int j=0;j<3;j++){//代表的是列数
			//加载图片
			//int num = i*3+j;//一个很好的设计思路！！！！！！！！！！！！			
			ImageIcon icon = new ImageIcon("picture\\"+pictureID+"_"+(i*3+j+1)+".gif");
			//创建图片小方格
			cell[i*3+j] = new Cell(icon);
			//指定图片小方格显示的位置
			cell[i*3+j].setLocation(j*163+0,i*152+20);//setSize(163,152 );//490*610
			//把图片小方格添加到拼图区显示
			this.add(cell[i*3+j]);
		}
	}
	//删除第12个图片小方格
	this.remove(cell[11]);//remove删除组件
	nullCell = new Rectangle(326, 476, 163, 152);
	
	}
	//重新加载图片，并添加数字提示
	public void reLoadPictureAddNumber(){
	//获取到每一个图片小方格
		for(int i=0;i<4;i++){//行数
			for(int j=0;j<3;j++){//列数
				//获取小方格cell[i*3+j]						
				//设置小方格显示的图片
				ImageIcon icon = new ImageIcon("picture\\"+pictureID+"_"+(i*3+j+1)+".gif");
				cell[i*3+j].setIcon(icon);	
				//设置小方格显示数字提示
				cell[i*3+j].setText(""+(i*3+j+1));
				cell[i*3+j].setVerticalTextPosition(this.getY()/2);
				cell[i*3+j].setHorizontalTextPosition(this.getX()/2);
			}
		}
	}
	//重新加载图片，并去除数字提示
	public void reLoadPictureClearNumber(){
		for(int i=0;i<4;i++){//行数
			for(int j=0;j<3;j++){//列数
				//获取小方格cell[i*3+j]						
				//设置小方格显示的图片
				ImageIcon icon = new ImageIcon("picture\\"+pictureID+"_"+(i*3+j+1)+".gif");
				cell[i*3+j].setIcon(icon);	
				//设置小方格显示数字提示
				cell[i*3+j].setText("");
				cell[i*3+j].setVerticalTextPosition(this.getY()/2);
				cell[i*3+j].setHorizontalTextPosition(this.getX()/2);
			}
		}
		
	}
	//对小方格进行重新位置排序
	public void start(){
		//如果要是没有给小方格添加鼠标监听的话，则添加监听
		if( !hasAddActionListener ){
			//添加监听
			for(int i=0;i<11;i++){
				cell[i].addMouseListener(this);				
			}
			//更新鼠标点击监听的状态
			hasAddActionListener = true;	
		}
		//判断当前第一个小方格距离左上角较近时，进行方格与空方格的互换//
		//如果第一个方格在左上角的四个方格位置内，就不断循环，进行方格与空方格的位置互换
		while(cell[0].getBounds().x<=163&&cell[0].getBounds().y<=172){
		//获取到空格的位置
		int nullX = nullCell.getBounds().x;	
		int nullY = nullCell.getBounds().y;		
		//随机产生一个方向，进行空方格与普通小方格的互换
		//产生0-3之间的随机数，对应空方格的上下左右移动
		int direction = (int)(Math.random()*4);//0.1.2.3
		switch(direction){ //163***152
		case 0://空方格向左移动，与左边的方格进行互换位置，左侧方格要向右移动
			//nullX = nullX -150
			nullX -=163;
			cellMove(nullX,nullY,"RIGHT");
			break;
		case 1://空方格向右移动，与右边的方格进行互换位置，右侧方格要向左移动
			nullX +=163;
			cellMove(nullX,nullY,"LEFT");
			break;
		case 2://空方格向上移动，与上边的方格进行互换位置，上侧方格要向下移动
			nullY -=152;
			cellMove(nullX,nullY,"DOWN");
			break;
		case 3://空方格向下移动，与下边的方格进行互换位置，下侧方格要向上移动
			nullY +=152;
			cellMove(nullX,nullY,"UP");
			break;
			
		default://
			break;
				
		}
		
		}
		
	}
	
	/*
	 * 方格与空方格的移动
	 * nullX 空方格的X轴坐标
	 * 
	 * int nullY 空方格的Y轴坐标
	 * 
	 * direction 方格将要移动的方向
	 */
	
	private void cellMove(int nullX, int nullY, String direction) {
	for(int i =0;i<11;i++){
		//获取到与空方格位置相同的小方格
		if(cell[i].getBounds().x == nullX && cell[i].getBounds().y == nullY){
		//当前方格的移动
		cell[i].move(direction);
		//空方格的移动
		nullCell.setLocation(nullX, nullY);	
		//交换位置后，结束循环	
		break;	
		}
		
	}
		
	}
	@Override
	public void mouseClicked(MouseEvent e) {
	}
	@Override
	public void mousePressed(MouseEvent e) {//鼠标按下
	//获取到当前所点击的小方格163*152
	Cell button =(Cell)e.getSource();
	//获取所点击方格的X，Y坐标
	int clickX = button.getBounds().x;
	int clickY = button.getBounds().y;	
	//获取当前空方格的X，Y坐标
	int nullX = nullCell.getBounds().x;
	int nullY = nullCell.getBounds().y;
	//进行比较，如果满足条件进行位置的交换	
	if(clickX == nullX && clickY - nullY == 152){//点击的为空方格下面的方格
	button.move("UP");//所点击的方格向上移动	
	} else if(clickX == nullX && clickY - nullY == -152){
	button.move("DOWN");//所点击的方格向上移动	
	} else if(clickY == nullY && clickX -nullX == 163){
	button.move("LEFT");	
	} else if(clickY == nullY && clickX -nullX == -163){
	button.move("RIGHT");
	} else{
		return ;
	}
	//更新空方格的位置
	nullCell.setLocation(clickX, clickY);
	//拼图区界面重新绘制
	this.repaint();
	//更新步数，将游戏状态区的步数更新
	stepNum++;
	PictureMainFrame.step.setText("步数："+stepNum);
	
	//判断当前游戏是否完成，若完成，给玩家一个友好的提示
	if(this.isFinish()){
		//弹出一个窗口提示
		JOptionPane.showMessageDialog(this, "恭喜你完成拼图！！！\n所用步"+ stepNum);
		//撤销每一个小方格上的鼠标点击监听，让鼠标点击小方格不再起作用
		for(int i=0;i<11;i++){
			cell[i].removeMouseListener(this);
		}
		
		//更新方格的动作监听器的状态
		hasAddActionListener = false;		
	}
	
	}
	//判断当前游戏是否完成,根据坐标判断拼图是否完成
	private boolean isFinish(){
		for(int i=0 ;i<11; i++){
			//获取每一个方格的位置
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










