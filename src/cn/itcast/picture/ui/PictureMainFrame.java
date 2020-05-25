package cn.itcast.picture.ui;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;//引入一个界面包
import javax.swing.JLabel;
import javax.swing.JPanel;//引入一个面板包
import javax.swing.JRadioButton;
import javax.swing.JTextField;
import javax.swing.border.TitledBorder;
/*
 * 主界面类
 */
@SuppressWarnings("serial")
public class PictureMainFrame extends JFrame {//继承界面类	
	private String[] items = {"小女孩","女明星"};
	private JRadioButton addNumInfo;//数字提示
	private JRadioButton clearNumInfo;//清除提示Ctrl+1:快速将局部变量转换为成员变量
	private PictureCanvas canvas;//拼图区
	private PicturePreview preview;//预览区
	private JComboBox<String> box;//下拉框
	private JTextField name;//图片名称
	public static JTextField step;//步数
	private JButton start;//开始按钮	
	//空参数构造方法
	public PictureMainFrame(){
	//super();
	init();//界面初始化操作  		
	addcomponent();	
	addpreviewimage();//添加预览图片与拼图图片
	addActionListener();//为组件添加事件监听	
	}
	//为组件添加事件监听
	private void addActionListener() {
	//数字提示
		addNumInfo.addActionListener(new ActionListener() {
			//点击按钮时激活下面的方法
			@Override
			public void actionPerformed(ActionEvent e) {
			//完成数字提示的显示	
			canvas.reLoadPictureAddNumber();				
			}
		});
		//清除提示
		clearNumInfo.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				//完成数字提示的清除
			canvas.reLoadPictureClearNumber();	
			}
		});
		
		//下拉框
		box.addItemListener(new ItemListener() {//ItemListener:每一项的内容			
			@Override
			public void itemStateChanged(ItemEvent e) {
			//获取到选择的图片序号
			int num = box.getSelectedIndex();//默认从零开始
			//更新当前图片ID
			//更新预览区
			PictureCanvas.pictureID = num+1;
			preview.repaint();//重新绘制预览区界面
			//更新拼图区
			canvas.reLoadPictureClearNumber();
			//更新游戏状态区
			name.setText("图片名称:"+box.getSelectedItem());	//设置图片名称
			//游戏步数清零
			int stepNum = PictureCanvas.stepNum = 0;//游戏步数清零
			step.setText("步数："+stepNum);//设置当前的步数
		    //按钮区
			//把选择按钮设成清除提示按钮选中状态
			clearNumInfo.setSelected(true);//			
			}
		});
		//开始按钮
		start.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
			//已移动的步数清零
			PictureCanvas.stepNum=0;
			//游戏状态区进行步数更新显示
			step.setText("步数："+PictureCanvas.stepNum);
			//对小方格进行重新位置排序，打乱顺序
			canvas.start();//*************
			}
		});
		
	}
	private void addpreviewimage() {
		//创建一个面板，包含拼图区与预览区
		JPanel panel = new JPanel();
			
		panel.setLayout(new GridLayout(1, 2)); //设置为表格布局，为面板panel设置一行两列
		canvas = new PictureCanvas();
		canvas.setBorder(new TitledBorder("拼图区"));//为区域添加边框		
		preview = new PicturePreview();
		preview.setBorder(new TitledBorder("图片预览区"));//为区域添加边框
		//把拼图区与图片预览区添加到中间的面板中
		panel.add(canvas,BorderLayout.WEST);//左边
		panel.add(preview,BorderLayout.EAST);
		//把面板显示在主界面中  居中显示
		this.add(panel,BorderLayout.CENTER);	
	}
	//添加组件
	private void addcomponent() {
	//创建一个用来在主界面上方显示的面板，在面板中要包含 按钮区 与 游戏状态区
	JPanel	panel = new JPanel();//new出一个面板
	panel.setBackground(Color.PINK);//设置当前面板背景色为粉色
	//----------以上代码没问题-------------------
	panel.setLayout(new GridLayout(1, 2));//mgr:布局方式  GridLayout:表格布局方式  rows：代表行数 cols:代表列数 	
	//创建左边按钮区面板
	JPanel leftPanel = new JPanel();
	leftPanel.setBorder(new TitledBorder("按钮区"));//添加边框
	panel.add(leftPanel, BorderLayout.WEST);//将左边按钮区面板放在左侧
	leftPanel.setBackground(Color.PINK);//将左边按钮区面板背景色设置成粉色
	addNumInfo = new JRadioButton("数字提示",false);
	clearNumInfo = new JRadioButton("清除提示",true);
	//添加按钮组
	ButtonGroup  buttonGroup = new  ButtonGroup();       //buttongroup:按钮组
	//添加下拉选择框	
	box = new JComboBox<String>(items);	
	start = new JButton("start");	
	//添加单选按钮到按钮组中
	buttonGroup.add(addNumInfo);
	buttonGroup.add(clearNumInfo);
	//设置背景色
	addNumInfo.setBackground(Color.PINK);
	clearNumInfo.setBackground(Color.PINK);
	start.setBackground(Color.PINK);	
	//添加组件到左边面板中\\面板中不予许添加按钮组，只能添加按钮
	leftPanel.add(addNumInfo);
	leftPanel.add(clearNumInfo);
	leftPanel.add(new JLabel("            选择图片"));
	leftPanel.add(box);
	leftPanel.add(start);	
	//------------------------------------
	//创建右边游戏状态区面板
	JPanel rightPanel = new JPanel();
	rightPanel.setBorder(new TitledBorder("游戏状态区"));//添加边框
	rightPanel.setBackground(Color.PINK);//将右边游戏状态区面板背景色设置成粉色
	rightPanel.setLayout(new GridLayout(1, 2));
	name = new JTextField("图片名称：小女孩儿");
	step = new JTextField("步数：0");	
	//把组件添加到游戏状态面板中name,BorderLayout.WEST
	rightPanel.add(name,BorderLayout.WEST);//左边
	rightPanel.add(step,BorderLayout.EAST);//右边	
	//设置文本框不能编辑
	name.setEditable(false);
	step.setEditable(false);	
	panel.add(rightPanel, BorderLayout.EAST);//将右边游戏状态面板放在右侧
	//------------------以下代码没问题--------------------------
	//设置panel 在主界面的上方 NORTH是北方，即上方
	this.add(panel,BorderLayout.NORTH);	
}
	/*
	 * 界面初始化方法
	 */
	private void init() {
	 //1：设置当前窗口的标题
		this.setTitle("拼图游戏");
	 //2：设置窗口的大小
		this.setSize(1000, 720);
	 //3：设置窗口的显示位置
		this.setLocation(460, 180);
	 //4：设置窗口的大小为固定大小
		this.setResizable(false);
	//设置窗口的默认关闭操作,当关闭窗体界面时，关闭java程序
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);		
	}
}
