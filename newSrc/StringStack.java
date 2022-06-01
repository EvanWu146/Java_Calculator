package assignment;

 class StringStack {
    String[] array;
    int maxSize;
    int top;

    StringStack(int size){
        this.maxSize = size;
        array = new String[size];
        top = -1;
    }

    //压入数据
    void push(String value, int flag){
        if(top < maxSize-1){
            if(flag == 0 || flag == 2 || flag == 3) {
                array[++top] = value;
            }//传入的为第一个数字，或其他运算符/控制符，或者是整个数字
            else if(flag == 1) {//传入的为第二个或以后的数字/小数点
                array[top] += value;
            }
        }
    }

    //弹出栈顶数据
    String pop(){
        return array[top--];
    }

    //访问栈顶数据
    String peek(){
        return array[top];
    }

    //查看指定位置的元素
    String peekN(int n){
        return array[n];
    }

    //为了便于后面分解展示栈中的内容，我们增加了一个遍历栈的方法(实际上栈只能访问栈顶元素的)
    void displayStack(){
        System.out.print("Stack(bottom-->top):");
        for(int i = 0 ; i < top+1; i++){
            System.out.print(peekN(i));
            System.out.print(' ');
        }
        System.out.println("");
    }

    //判断栈是否为空
    boolean isEmpty(){
        return (top == -1);
    }

    //判断栈是否满了
    boolean isFull(){
        return (top == maxSize-1);
    }

}

