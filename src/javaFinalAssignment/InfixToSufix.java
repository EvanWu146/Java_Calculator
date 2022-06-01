package javaFinalAssignment;


public class InfixToSufix {
    private StringStack s1;//定义运算符栈
    private StringStack s2;//定义存储结果栈
    private String input = "";
    private int firstDigital = 1;//标识符：当前符为操作数的首位
     
    //默认构造方法，参数为输入的中缀表达式
    public InfixToSufix(){
    }
    
    //重构
    public InfixToSufix(String in){
        input = in;
        s1 = new StringStack(input.length());
        s2 = new StringStack(input.length());
    }
    
    //中缀表达式转换为后缀表达式，将结果存储在栈中返回，逆序显示即后缀表达式
    public StringStack transition(){
        for(int j = 0 ; j < input.length() ; j++){
            String str = input.substring(j, j+1);
            System.out.println("s1栈元素为：");s1.displayStack();
            System.out.println("s2栈元素为：");s2.displayStack();
            System.out.println("当前字符为：" + str);
            if(str.matches("[0-9]|\\.")) {
            	if(firstDigital == 1) {
            		firstDigital = 0;
            		s2.push(str, 0);
            	}
            	else 	s2.push(str, 1);
            }
            else {
            	firstDigital = 1;
            	if(str.matches("\\+|-")) 	gotOperator(str, 1);
            	else if(str.matches("\\*|/|%"))		gotOperator(str, 2);
            	else if(str.matches("e|\\^"))		gotOperator(str, 3);
            	else if(str.equals("(")) {
            		s1.push(str, 2);
            	}
            	else if(str.equals(")")) {
            		gotParen();
            	}
            }
        }//end for
         
        while(!s1.isEmpty()){
            s2.push(s1.pop(), 2);
        }
        return s2;
    }
     
    public void gotOperator(String opThis,int priorCur){
        while(!s1.isEmpty()){
            String opTop = s1.pop();
            if(opTop.equals("(")){//如果栈顶是'(',直接将操作符压入s1
                s1.push(opTop, 2);
                break;
            }else{
                int priorTop;
                if(opTop.equals("+")|| opTop.equals("-")){
                    priorTop = 1;
                }else if(opTop.matches("\\\\*|/|%")){
                    priorTop = 2;
                }
                else priorTop = 3;
                if(priorTop < priorCur){//如果当前运算符比s1栈顶运算符优先级高，则将运算符压入s1
                    s1.push(opTop, 2);
                    break;
                }else{//如果当前运算符与栈顶运算符相同或者小于优先级别，那么将S1栈顶的运算符弹出并压入到S2中
                    //并且要再次再次转到while循环中与 s1 中新的栈顶运算符相比较；
                    s2.push(opTop, 2);
                }
            }
             
        }
        //如果s1为空，则直接将当前解析的运算符压入s1
        s1.push(opThis, 2);
    }
     
    //当前字符是 ')' 时，如果栈顶是'(',则将这一对括号丢弃，否则依次弹出s1栈顶的字符，压入s2，直到遇到'('
    public void gotParen(){
        while(!s1.isEmpty()){
            String s = s1.pop();
            if(s.equals("(")){
                break;
            }else{
                s2.push(s, 2);
            }
        }
    }
 
}
