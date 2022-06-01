package assignment;

    class SuffixExpressionSolver {
        private StringStack s, s1;

        public SuffixExpressionSolver(StringStack inputStack) throws Exception{
            this.s = inputStack;
            s1 = new StringStack(s.maxSize);
        }

        public double expSolver() {
            double num1 = 0.0, num2 = 0.0, result = 0.0;
            s.displayStack();
            for(int i =  0; i <= s.top ; i++) {
                if(s.array[i].matches("([0-9]|\\.)+")) {//如果当前遍历元素为数字
                    s1.push(s.array[i], 3);//将数字压入栈中
                }else {
                    num2 = Double.parseDouble(s1.pop());
                    num1 = Double.parseDouble(s1.pop());
                    if(s.array[i].equals("+")) {
                        add_factory f=new add_factory();
                        result = f.calculation().operation(num1,num2);
                    }
                    else if(s.array[i].equals("-")) {
                        subtract_factory f=new subtract_factory();
                        result = f.calculation().operation(num1,num2);
                    }
                    else if(s.array[i].equals("*")) {
                        multiply_factory f=new multiply_factory();
                        result = f.calculation().operation(num1,num2);

                    }
                    else if(s.array[i].equals("/")) {
                        divide_factory f=new divide_factory();
                        result = f.calculation().operation(num1,num2);
                    }
                    else if(s.array[i].equals("%")) {
                        modular_factory f=new modular_factory();
                        result = f.calculation().operation(num1,num2);
                    }
                    else if(s.array[i].equals("^")) {
                        pow_factory f=new pow_factory();
                        result = f.calculation().operation(num1,num2);
                    }
                    else if(s.array[i].equals("e")) {
                        pow_10_factory f=new pow_10_factory();
                        result = f.calculation().operation(num1,num2);
                    }
                    s1.push(result + "", 3);
                }

            }

            return result;
        }
}

