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
			"√x",  "x^y", "(",   ")", 
			"exp", "1/x", "ln",  "/", 
			"7",   "8",   "9",   "*", 
			"4",   "5",   "6",   "-", 
			"1",   "2",   "3",   "+", 
			"",    "0",   ".",   "="}; //定义输入字符（数字和运算符）
	private final String[] commandC = {"Backspace", "CE", "C",
			"M+", "MR", "MS", "MC"};//定义输入控制命令
	JButton inputBtn[] = new JButton[inputC.length];//为输入字符设置按钮
	JButton commandBtn[] = new JButton[commandC.length];//为输入控制命令设置按钮
	JTextField textBox = new JTextField("0"),
				textBox1 = new JTextField();//设置数字与总运算式的输入框（两个分开）
	private int leftBkt = 0, rightBkt = 0;//左右括号计数
	double mem = 0.0;//计算器的寄存器
	
	public static void main(String[] args) throws Exception{
		new NewCalculator();
	}
	
	public NewCalculator()  throws Exception{//构造方法
		initializePanel();
	}
	
	public void initializePanel() {
		this.setTitle("计算器");
		this.setSize(600, 450);// 设置窗口的宽度和高度
        this.setLocationRelativeTo(null);//设置窗口打开时的位置，null为默认屏幕中心
        this.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);//设置窗口点击关闭后的默认操作，此处设为退出进程
        
        //初始化两个文本框，textBox1为运算式输入框，textBox为操作数输入框
        textBox.setHorizontalAlignment(JTextField.RIGHT);//向右对齐
        textBox.setEditable(false);//设为不可直接编辑
        textBox.setBackground(Color.white);//白色背景
        textBox.setFont(new Font(null, Font.PLAIN, 30));//设置字体
        textBox1.setHorizontalAlignment(JTextField.RIGHT);
        textBox1.setEditable(false);
        textBox1.setBackground(Color.white);
        textBox1.setFont(new Font(null, Font.PLAIN, 30));
        
        //初始化数据输入面板（7行4列按钮共28个）和命令输入面板（7行1列按钮共7个），按钮间距为3
        JPanel inputPanel = new JPanel(new GridLayout(7, 4, 3, 3)), 
        	commandPanel = new JPanel(new GridLayout(7, 1, 3, 3));
   
        for(int i = 0; i < inputC.length; i++) {//初始化数据按键和命令按键
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
        
        Box vBox = Box.createVerticalBox();//把两个文本框装入盒子容器中
        vBox.add(textBox1);
        vBox.add(textBox);
        this.getContentPane().setLayout(new BorderLayout(5,5));
        getContentPane().add("North",vBox);//设置面板和盒子的布局
		getContentPane().add("Center", inputPanel);
		getContentPane().add("West", commandPanel);
        this.setVisible(true);
	}
	
	public void actionPerformed(ActionEvent e){//设置按键输入活动
		String label = e.getActionCommand();
		
		if(label.matches("[0-9]|\\.")) {//按下数字或小数点按键
			this.dealWithNum(label);
		}
		else if(label.matches("\\+|-|\\*|/|%|exp|x\\^y")) {//输入的是普通运算符，则按一定规则加入上输入行
			this.dealWithOperator1(label);
		}
		else if(label.matches("\\(|\\)")) {//输入的为括号
			this.dealWithBracket(label);
		}
		else if(label.matches("ln|√x|1/x|sin|cos|tan")) {//函数符号
			this.dealWithFunction(label);
		}
		else if(label.matches("C|CE")) {//清空命令
			this.dealWithCleaner(label);
		}
		else if(label.equals("Backspace")) {//退格命令
			this.dealWithBackspace(label);
		}
		else if(label.matches("M\\+|MS|MR|MC")) {//寄存器
			this.dealWithMem(label);
		}
		else if(label.equals("=")) {//等于号
			this.dealWithEquals(label);
		}
	}
	
	private void dealWithNum(String label) {
		if(label.equals(".")) {//按下了小数点按键，则判断其是否是第一个小数点，是则加入下输入行的最后位置
			if(textBox.getText().indexOf(".") < 0) 	textBox.setText(textBox.getText() + label);
		}
		else if(label.matches("[0-9]")){//输入的是数字
			if(textBox.getText().equals("0"))	textBox.setText(label);
			else	textBox.setText(textBox.getText() + label);
		}
		//else if(label.equals("-"))
	}
	
	private void dealWithOperator1(String label) {
		if(!textBox.getText().equals("0")) {//如果操作数输入框存在数据，则将其放入运算式输入框最后
			textBox1.setText(textBox1.getText() + textBox.getText());
		}
		if("".equals(textBox1.getText())) {//运算式输入框为空时输入运算符，在运算符前面加上0
			textBox1.setText("0"+textBox1.getText());
		}
		textBox.setText("0");
		if(label.equals("exp"))		textBox1.setText(textBox1.getText() + "e");
		else if(label.equals("x^y"))	textBox1.setText(textBox1.getText() + "^");
		else 	textBox1.setText(textBox1.getText() + label);
	}
	
	private void dealWithBracket(String label) {
		if(label.equals("(")) {//左括号
			if(textBox1.getText().length() > 0 && 
				textBox1.getText().lastIndexOf(")") == textBox1.getText().length() - 1) {
				textBox1.setText(textBox1.getText().substring(0, textBox1.getText().lastIndexOf("(") + 1));
				rightBkt--;
			}//当运算式输入框中有字符，且上一个输入符为右括号时，输入左括号会重置最后括号中的内容
			
			else{
				textBox1.setText(textBox1.getText() + ""+ label);
				leftBkt++;
			}
		}
		else if(label.equals(")") && leftBkt > rightBkt) {//右括号
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
		}finally {//将运算机制设置为直接对当前操作数求值，保留四位小数放至运算式输入框
			if(label.equals("ln") && temResult != 0) 		temResult = Math.log(temResult);
			else if(label.equals("√x")) 	temResult = Math.sqrt(temResult);
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
	
	private void dealWithCleaner(String label) {//C按钮和CE按钮的实现
		textBox.setText("0");
		if(label.equals("C"))	textBox1.setText("");
	}
	
	private void dealWithMem(String label) {
		double temNum = 0.0;
		if(label.matches("M\\+|MS")) {//寄存器加法与保存
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
		else if(label.matches("MR|MC")) {//寄存器清零和读写
			if(label.equals("MR")) {
				textBox.setText(""+mem);
			}
			else if(label.equals("MC")) {//寄存器的读出和清零
				mem = 0.0;
			}
		}
	}
	
	private void dealWithEquals(String label) {//处理等于号
		double result = 0.0;
		NumberFormat doubleFormat = NumberFormat.getNumberInstance() ; 
		doubleFormat.setMaximumFractionDigits(4); 
		if(!textBox.getText().equals("0")) {//若操作数输入框不为0（即有数据存在）则加入到运算式输入框最后
			textBox1.setText(textBox1.getText() + textBox.getText());
		}
		
		//中缀表达式转后缀表达式
		InfixToSufix E = new InfixToSufix(textBox1.getText());
		StringStack S = E.transition();
		
		//计算后缀表达式后输出结果到文本框
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
	
	private void dealWithBackspace(String label) {//退格命令的处理
		textBox.setText(textBox.getText().substring(0, textBox.getText().length() - 1));
		if(textBox.getText().length() == 0) 	textBox.setText("0");
	}
	
	
	
}
