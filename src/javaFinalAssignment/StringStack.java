package javaFinalAssignment;

public class StringStack {
    public String[] array;
    public int maxSize;
    public int top;
     
    public StringStack(int size){
        this.maxSize = size;
        array = new String[size];
        top = -1;
    }
     
    //ѹ������
    public void push(String value, int flag){
        if(top < maxSize-1){
            if(flag == 0 || flag == 2 || flag == 3) {
            	array[++top] = value;
        	}//�����Ϊ��һ�����֣������������/���Ʒ�����������������
        	else if(flag == 1) {//�����Ϊ�ڶ������Ժ������/С����
        		array[top] += value;
        	}
        }
    }
     
    //����ջ������
    public String pop(){
        return array[top--];
    }
     
    //����ջ������
    public String peek(){
        return array[top];
    }
     
    //�鿴ָ��λ�õ�Ԫ��
    public String peekN(int n){
        return array[n];
    }
     
    //Ϊ�˱��ں���ֽ�չʾջ�е����ݣ�����������һ������ջ�ķ���(ʵ����ջֻ�ܷ���ջ��Ԫ�ص�)
    public void displayStack(){
        System.out.print("Stack(bottom-->top):");
        for(int i = 0 ; i < top+1; i++){
            System.out.print(peekN(i));
            System.out.print(' ');
        }
        System.out.println("");
    }
     
    //�ж�ջ�Ƿ�Ϊ��
    public boolean isEmpty(){
        return (top == -1);
    }
     
    //�ж�ջ�Ƿ�����
    public boolean isFull(){
        return (top == maxSize-1);
    }
 
}