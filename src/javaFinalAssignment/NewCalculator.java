package javaFinalAssignment;
import java.util.*;
import java.math.*;
import java.text.NumberFormat;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.lang.String;

public class NewCalculator extends JFrame implements ActionListener{

	private final String[] inputC = {
			"%",   "sin", "cos", "tan", 
			"��x",  "x^y", "(",   ")", 
			"exp", "1/x", "ln",  "/", 
			"7",   "8",   "9",   "*", 
			"4",   "5",   "6",   "-", 
			"1",   "2",   "3",   "+", 
			"",    "0",   ".",   "="}; //���������ַ������ֺ��������
	private final String[] commandC = {"Backspace", "CE", "C",
			"M+", "MR", "MS", "MC"};//���������������
	JButton inputBtn[] = new JButton[inputC.length];//Ϊ�����ַ����ð�ť
	JButton commandBtn[] = new JButton[commandC.length];//Ϊ��������������ð�ť
	JTextField textBox = new JTextField("0"),
				textBox1 = new JTextField();//����������������ʽ������������ֿ���
	private int leftBkt = 0, rightBkt = 0;//�������ż���
	double mem = 0.0;//�������ļĴ���
	
	public static void main(String[] args) throws Exception{
		new NewCalculator();
	}
	
	public NewCalculator()  throws Exception{//���췽��
		initializePanel();
	}
	
	public void initializePanel() {
		this.setTitle("������");
		this.setSize(600, 450);// ���ô��ڵĿ�Ⱥ͸߶�
        this.setLocationRelativeTo(null);//���ô��ڴ�ʱ��λ�ã�nullΪĬ����Ļ����
        this.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);//���ô��ڵ���رպ��Ĭ�ϲ������˴���Ϊ�˳�����
        
        //��ʼ�������ı���textBox1Ϊ����ʽ�����textBoxΪ�����������
        textBox.setHorizontalAlignment(JTextField.RIGHT);//���Ҷ���
        textBox.setEditable(false);//��Ϊ����ֱ�ӱ༭
        textBox.setBackground(Color.white);//��ɫ����
        textBox.setFont(new Font(null, Font.PLAIN, 30));//��������
        textBox1.setHorizontalAlignment(JTextField.RIGHT);
        textBox1.setEditable(false);
        textBox1.setBackground(Color.white);
        textBox1.setFont(new Font(null, Font.PLAIN, 30));
        
        //��ʼ������������壨7��4�а�ť��28����������������壨7��1�а�ť��7��������ť���Ϊ3
        JPanel inputPanel = new JPanel(new GridLayout(7, 4, 3, 3)), 
        	commandPanel = new JPanel(new GridLayout(7, 1, 3, 3));
   
        for(int i = 0; i < inputC.length; i++) {//��ʼ�����ݰ����������
        	inputBtn[i] = new JButton(inputC[i]);
        	inputBtn[i].setForeground(Color.black);
        	inputBtn[i].setBackground(Color.white);
        	inputBtn[i].setFont(new Font(null, Font.PLAIN, 15));
        	inputBtn[i].addActionListener(this);
        	inputPanel.add(inputBtn[i]);
        }
        for(int i = 0; i < commandC.length; i++) {
        	commandBtn[i] = new JButton(commandC[i]);
        	commandBtn[i].setForeground(Color.black);
        	commandBtn[i].setBackground(Color.LIGHT_GRAY);
        	commandBtn[i].addActionListener(this);
        	commandPanel.add(commandBtn[i]);
        }
        
        Box vBox = Box.createVerticalBox();//�������ı���װ�����������
        vBox.add(textBox1);
        vBox.add(textBox);
        this.getContentPane().setLayout(new BorderLayout(5,5));
        getContentPane().add("North",vBox);//�������ͺ��ӵĲ���
		getContentPane().add("Center", inputPanel);
		getContentPane().add("West", commandPanel);
        this.setVisible(true);
	}
	
	public void actionPerformed(ActionEvent e){//���ð�������
		String label = e.getActionCommand();
		
		if(label.matches("[0-9]|\\.")) {//�������ֻ�С���㰴��
			this.dealWithNum(label);
		}
		else if(label.matches("\\+|-|\\*|/|%|exp|x\\^y")) {//���������ͨ���������һ�����������������
			this.dealWithOperator1(label);
		}
		else if(label.matches("\\(|\\)")) {//�����Ϊ����
			this.dealWithBracket(label);
		}
		else if(label.matches("ln|��x|1/x|sin|cos|tan")) {//��������
			this.dealWithFunction(label);
		}
		else if(label.matches("C|CE")) {//�������
			this.dealWithCleaner(label);
		}
		else if(label.equals("Backspace")) {//�˸�����
			this.dealWithBackspace(label);
		}
		else if(label.matches("M\\+|MS|MR|MC")) {//�Ĵ���
			this.dealWithMem(label);
		}
		else if(label.equals("=")) {//���ں�
			this.dealWithEquals(label);
		}
	}
	
	private void dealWithNum(String label) {
		if(label.equals(".")) {//������С���㰴�������ж����Ƿ��ǵ�һ��С���㣬��������������е����λ��
			if(textBox.getText().indexOf(".") < 0) 	textBox.setText(textBox.getText() + label);
		}
		else if(label.matches("[0-9]")){//�����������
			if(textBox.getText().equals("0"))	textBox.setText(label);
			else	textBox.setText(textBox.getText() + label);
		}
		//else if(label.equals("-"))
	}
	
	private void dealWithOperator1(String label) {
		if(!textBox.getText().equals("0")) {//��������������������ݣ������������ʽ��������
			textBox1.setText(textBox1.getText() + textBox.getText());
		}
		if("".equals(textBox1.getText())) {//����ʽ�����Ϊ��ʱ������������������ǰ�����0
			textBox1.setText("0"+textBox1.getText());
		}
		textBox.setText("0");
		if(label.equals("exp"))		textBox1.setText(textBox1.getText() + "e");
		else if(label.equals("x^y"))	textBox1.setText(textBox1.getText() + "^");
		else 	textBox1.setText(textBox1.getText() + label);
	}
	
	private void dealWithBracket(String label) {
		if(label.equals("(")) {//������
			if(textBox1.getText().length() > 0 && 
				textBox1.getText().lastIndexOf(")") == textBox1.getText().length() - 1) {
				textBox1.setText(textBox1.getText().substring(0, textBox1.getText().lastIndexOf("(") + 1));
				rightBkt--;
			}//������ʽ����������ַ�������һ�������Ϊ������ʱ�����������Ż�������������е�����
			
			else{
				textBox1.setText(textBox1.getText() + ""+ label);
				leftBkt++;
			}
		}
		else if(label.equals(")") && leftBkt > rightBkt) {//������
			if(textBox1.getText().length() > 0 && 
				textBox1.getText().lastIndexOf(")") == textBox1.getText().length() - 1) {
				textBox1.setText(textBox1.getText() + ")");
			}
			else	textBox1.setText(textBox1.getText() + textBox.getText() + ")");
			textBox.setText("0");
			rightBkt++;
		}
	}
	
	private void dealWithFunction(String label) {
		double temResult = 0.0;
		NumberFormat doubleFormat = NumberFormat.getNumberInstance() ; 
		doubleFormat.setMaximumFractionDigits(4); 
		try {
			temResult = Double.valueOf(textBox.getText()).doubleValue();
		} catch (NumberFormatException e1) {
			textBox.setText("Wrong Input!");
		}finally {//�������������Ϊֱ�ӶԵ�ǰ��������ֵ��������λС����������ʽ�����
			if(label.equals("ln") && temResult != 0) 		temResult = Math.log(temResult);
			else if(label.equals("��x")) 	temResult = Math.sqrt(temResult);
			else if(label.equals("1/x") && temResult != 0) temResult = 1/temResult;			
			else if(label.equals("sin")) 	temResult = Math.sin(temResult);
			else if(label.equals("cos")) 	temResult = Math.cos(temResult);
			else if(label.equals("tan")) 	temResult = Math.tan(temResult);
			textBox1.setText(textBox1.getText() + doubleFormat.format(temResult));
			textBox.setText("0");
				
			if(temResult == 0 && (label.equals("ln") || label.equals("1/x")) ) {
				textBox.setText("Invalid input!");
			}
		}
	}
	
	private void dealWithCleaner(String label) {//C��ť��CE��ť��ʵ��
		textBox.setText("0");
		if(label.equals("C"))	textBox1.setText("");
	}
	
	private void dealWithMem(String label) {
		double temNum = 0.0;
		if(label.matches("M\\+|MS")) {//�Ĵ����ӷ��뱣��
			try {
				temNum = Double.valueOf(textBox.getText()).doubleValue();
			} catch (NumberFormatException e1) {
				textBox.setText("Wrong Input!");
			}finally {
				if(label.equals("M+"))	mem += temNum;
				else if(label.equals("MS")) mem = temNum;
				textBox.setText("0");
			}
		}
		else if(label.matches("MR|MC")) {//�Ĵ�������Ͷ�д
			if(label.equals("MR")) {
				textBox.setText(""+mem);
			}
			else if(label.equals("MC")) {//�Ĵ����Ķ���������
				mem = 0.0;
			}
		}
	}
	
	private void dealWithEquals(String label) {//������ں�
		double result = 0.0;
		NumberFormat doubleFormat = NumberFormat.getNumberInstance() ; 
		doubleFormat.setMaximumFractionDigits(4); 
		if(!textBox.getText().equals("0")) {//�������������Ϊ0���������ݴ��ڣ�����뵽����ʽ��������
			textBox1.setText(textBox1.getText() + textBox.getText());
		}
		
		//��׺���ʽת��׺���ʽ
		InfixToSufix E = new InfixToSufix(textBox1.getText());
		StringStack S = E.transition();
		
		//�����׺���ʽ�����������ı���
		try {
			result = new SuffixExpressionSolver(S).expSolver();
			System.out.println(result);
		}catch(Exception e1) {
			result = 0.0;
			textBox.setText("Something went wrong!");
		}finally {
			textBox.setText("" + doubleFormat.format(result));
		}
	}
	
	private void dealWithBackspace(String label) {//�˸�����Ĵ���
		textBox.setText(textBox.getText().substring(0, textBox.getText().length() - 1));
		if(textBox.getText().length() == 0) 	textBox.setText("0");
	}
	
	
	
}
