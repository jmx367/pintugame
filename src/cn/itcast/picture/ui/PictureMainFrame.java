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
import javax.swing.JFrame;//����һ�������
import javax.swing.JLabel;
import javax.swing.JPanel;//����һ������
import javax.swing.JRadioButton;
import javax.swing.JTextField;
import javax.swing.border.TitledBorder;
/*
 * ��������
 */
@SuppressWarnings("serial")
public class PictureMainFrame extends JFrame {//�̳н�����	
	private String[] items = {"СŮ��","Ů����"};
	private JRadioButton addNumInfo;//������ʾ
	private JRadioButton clearNumInfo;//�����ʾCtrl+1:���ٽ��ֲ�����ת��Ϊ��Ա����
	private PictureCanvas canvas;//ƴͼ��
	private PicturePreview preview;//Ԥ����
	private JComboBox<String> box;//������
	private JTextField name;//ͼƬ����
	public static JTextField step;//����
	private JButton start;//��ʼ��ť	
	//�ղ������췽��
	public PictureMainFrame(){
	//super();
	init();//�����ʼ������  		
	addcomponent();	
	addpreviewimage();//���Ԥ��ͼƬ��ƴͼͼƬ
	addActionListener();//Ϊ�������¼�����	
	}
	//Ϊ�������¼�����
	private void addActionListener() {
	//������ʾ
		addNumInfo.addActionListener(new ActionListener() {
			//�����ťʱ��������ķ���
			@Override
			public void actionPerformed(ActionEvent e) {
			//���������ʾ����ʾ	
			canvas.reLoadPictureAddNumber();				
			}
		});
		//�����ʾ
		clearNumInfo.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				//���������ʾ�����
			canvas.reLoadPictureClearNumber();	
			}
		});
		
		//������
		box.addItemListener(new ItemListener() {//ItemListener:ÿһ�������			
			@Override
			public void itemStateChanged(ItemEvent e) {
			//��ȡ��ѡ���ͼƬ���
			int num = box.getSelectedIndex();//Ĭ�ϴ��㿪ʼ
			//���µ�ǰͼƬID
			//����Ԥ����
			PictureCanvas.pictureID = num+1;
			preview.repaint();//���»���Ԥ��������
			//����ƴͼ��
			canvas.reLoadPictureClearNumber();
			//������Ϸ״̬��
			name.setText("ͼƬ����:"+box.getSelectedItem());	//����ͼƬ����
			//��Ϸ��������
			int stepNum = PictureCanvas.stepNum = 0;//��Ϸ��������
			step.setText("������"+stepNum);//���õ�ǰ�Ĳ���
		    //��ť��
			//��ѡ��ť��������ʾ��ťѡ��״̬
			clearNumInfo.setSelected(true);//			
			}
		});
		//��ʼ��ť
		start.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
			//���ƶ��Ĳ�������
			PictureCanvas.stepNum=0;
			//��Ϸ״̬�����в���������ʾ
			step.setText("������"+PictureCanvas.stepNum);
			//��С�����������λ�����򣬴���˳��
			canvas.start();//*************
			}
		});
		
	}
	private void addpreviewimage() {
		//����һ����壬����ƴͼ����Ԥ����
		JPanel panel = new JPanel();
			
		panel.setLayout(new GridLayout(1, 2)); //����Ϊ��񲼾֣�Ϊ���panel����һ������
		canvas = new PictureCanvas();
		canvas.setBorder(new TitledBorder("ƴͼ��"));//Ϊ������ӱ߿�		
		preview = new PicturePreview();
		preview.setBorder(new TitledBorder("ͼƬԤ����"));//Ϊ������ӱ߿�
		//��ƴͼ����ͼƬԤ������ӵ��м�������
		panel.add(canvas,BorderLayout.WEST);//���
		panel.add(preview,BorderLayout.EAST);
		//�������ʾ����������  ������ʾ
		this.add(panel,BorderLayout.CENTER);	
	}
	//������
	private void addcomponent() {
	//����һ���������������Ϸ���ʾ����壬�������Ҫ���� ��ť�� �� ��Ϸ״̬��
	JPanel	panel = new JPanel();//new��һ�����
	panel.setBackground(Color.PINK);//���õ�ǰ��屳��ɫΪ��ɫ
	//----------���ϴ���û����-------------------
	panel.setLayout(new GridLayout(1, 2));//mgr:���ַ�ʽ  GridLayout:��񲼾ַ�ʽ  rows���������� cols:�������� 	
	//������߰�ť�����
	JPanel leftPanel = new JPanel();
	leftPanel.setBorder(new TitledBorder("��ť��"));//��ӱ߿�
	panel.add(leftPanel, BorderLayout.WEST);//����߰�ť�����������
	leftPanel.setBackground(Color.PINK);//����߰�ť����屳��ɫ���óɷ�ɫ
	addNumInfo = new JRadioButton("������ʾ",false);
	clearNumInfo = new JRadioButton("�����ʾ",true);
	//��Ӱ�ť��
	ButtonGroup  buttonGroup = new  ButtonGroup();       //buttongroup:��ť��
	//�������ѡ���	
	box = new JComboBox<String>(items);	
	start = new JButton("start");	
	//��ӵ�ѡ��ť����ť����
	buttonGroup.add(addNumInfo);
	buttonGroup.add(clearNumInfo);
	//���ñ���ɫ
	addNumInfo.setBackground(Color.PINK);
	clearNumInfo.setBackground(Color.PINK);
	start.setBackground(Color.PINK);	
	//����������������\\����в�������Ӱ�ť�飬ֻ����Ӱ�ť
	leftPanel.add(addNumInfo);
	leftPanel.add(clearNumInfo);
	leftPanel.add(new JLabel("            ѡ��ͼƬ"));
	leftPanel.add(box);
	leftPanel.add(start);	
	//------------------------------------
	//�����ұ���Ϸ״̬�����
	JPanel rightPanel = new JPanel();
	rightPanel.setBorder(new TitledBorder("��Ϸ״̬��"));//��ӱ߿�
	rightPanel.setBackground(Color.PINK);//���ұ���Ϸ״̬����屳��ɫ���óɷ�ɫ
	rightPanel.setLayout(new GridLayout(1, 2));
	name = new JTextField("ͼƬ���ƣ�СŮ����");
	step = new JTextField("������0");	
	//�������ӵ���Ϸ״̬�����name,BorderLayout.WEST
	rightPanel.add(name,BorderLayout.WEST);//���
	rightPanel.add(step,BorderLayout.EAST);//�ұ�	
	//�����ı����ܱ༭
	name.setEditable(false);
	step.setEditable(false);	
	panel.add(rightPanel, BorderLayout.EAST);//���ұ���Ϸ״̬�������Ҳ�
	//------------------���´���û����--------------------------
	//����panel ����������Ϸ� NORTH�Ǳ��������Ϸ�
	this.add(panel,BorderLayout.NORTH);	
}
	/*
	 * �����ʼ������
	 */
	private void init() {
	 //1�����õ�ǰ���ڵı���
		this.setTitle("ƴͼ��Ϸ");
	 //2�����ô��ڵĴ�С
		this.setSize(1000, 720);
	 //3�����ô��ڵ���ʾλ��
		this.setLocation(460, 180);
	 //4�����ô��ڵĴ�СΪ�̶���С
		this.setResizable(false);
	//���ô��ڵ�Ĭ�Ϲرղ���,���رմ������ʱ���ر�java����
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);		
	}
}
