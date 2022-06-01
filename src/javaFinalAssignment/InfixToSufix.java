package javaFinalAssignment;


public class InfixToSufix {
    private StringStack s1;//���������ջ
    private StringStack s2;//����洢���ջ
    private String input = "";
    private int firstDigital = 1;//��ʶ������ǰ��Ϊ����������λ
     
    //Ĭ�Ϲ��췽��������Ϊ�������׺���ʽ
    public InfixToSufix(){
    }
    
    //�ع�
    public InfixToSufix(String in){
        input = in;
        s1 = new StringStack(input.length());
        s2 = new StringStack(input.length());
    }
    
    //��׺���ʽת��Ϊ��׺���ʽ��������洢��ջ�з��أ�������ʾ����׺���ʽ
    public StringStack transition(){
        for(int j = 0 ; j < input.length() ; j++){
            String str = input.substring(j, j+1);
            System.out.println("s1ջԪ��Ϊ��");s1.displayStack();
            System.out.println("s2ջԪ��Ϊ��");s2.displayStack();
            System.out.println("��ǰ�ַ�Ϊ��" + str);
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
            if(opTop.equals("(")){//���ջ����'(',ֱ�ӽ�������ѹ��s1
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
                if(priorTop < priorCur){//�����ǰ�������s1ջ����������ȼ��ߣ��������ѹ��s1
                    s1.push(opTop, 2);
                    break;
                }else{//�����ǰ�������ջ���������ͬ����С�����ȼ�����ô��S1ջ���������������ѹ�뵽S2��
                    //����Ҫ�ٴ��ٴ�ת��whileѭ������ s1 ���µ�ջ���������Ƚϣ�
                    s2.push(opTop, 2);
                }
            }
             
        }
        //���s1Ϊ�գ���ֱ�ӽ���ǰ�����������ѹ��s1
        s1.push(opThis, 2);
    }
     
    //��ǰ�ַ��� ')' ʱ�����ջ����'(',����һ�����Ŷ������������ε���s1ջ�����ַ���ѹ��s2��ֱ������'('
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
