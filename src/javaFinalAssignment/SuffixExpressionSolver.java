package javaFinalAssignment;

public class SuffixExpressionSolver {
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
					result = num1 + num2;
				}
				else if(s.array[i].equals("-")) {
					result = num1 - num2;
				}
				else if(s.array[i].equals("*")) {
					result = num1 * num2;
					
				}
				else if(s.array[i].equals("/")) {
					result = num1 / num2;
				}
				else if(s.array[i].equals("%")) {
					result = num1 % num2;
				}
				else if(s.array[i].equals("^")) {
					result = Math.pow(num1, num2);
				}
				else if(s.array[i].equals("e")) {
					result = num1 * Math.pow(10, num2);
				}
				s1.push(result + "", 3);
			}
			
		}
		
		return result;
	}
}
