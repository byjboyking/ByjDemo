package jnjd.middlefix2postfix;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

/**
 * 输入的中缀表达式，
 * 1、	以$表示表达式的结尾；
 * 2、	能表达非负整数的四则运算。即输入的符号包括数字0123456789.运算符+- * /
 * 以及括号来改变计算次序；所有其他字符均视为空格。
 * 
 * 输出为逆波兰表达式。
 * 1、	以空格分隔不同的运算数据,如输入为”(1 + 2) * 3$”,  则输出为”1 2+3*”
 * 1、	能正确处理简单的表达式（40分）， 如将1+2+3$ 转换为 1 2 + 3 +
 * 2、	能正确处理不同操作符的优先级（30分），如能将 1+2*3$  转换为  1 2 3 * +
 * 3、	能正确处理阔汗匹配及括号导致的计算次序变化（20分），如能将 (1+2)*3$  转换为 1 2 + 3 *
 * 4、	能正确识别输入的中缀表达式中的语法错误（10分），如输入1++2$ 则抛出 SyntaxErrorException异常
 */
public class ReversePolishNotation {

	public final static String Digitals = new String("0123456789");
	public final static String operators = new String("+-*/");
	public final static String Seperators =new String("()$");

	/**
	 * 输入的中缀表达式中存在语法错误
	 *
	 */
	public static class SyntaxErrorException extends Exception{
		private static final long serialVersionUID = 5551252903635916672L;
		public SyntaxErrorException() {
		}
	}

	/**
	 * 请实现convert函数，将中缀表达式字符串infixNotation转换为逆波兰表达式字符串，
	 * 可以根据需要增加变量和函数，但是注意不要改变convert函数的原型
	 * @param infixNotation 中缀表达式字符串
	 * @return 逆波兰表达式字符串
	 * @throws SyntaxErrorException
	 */
	public String convert(String infixNotation) throws SyntaxErrorException {
		if(infixNotation==null || infixNotation.length()==0 || !infixNotation.endsWith("$")){
			throw new SyntaxErrorException();
		}
		
		//remove end $
		String input = infixNotation.substring(0,infixNotation.length()-1);
		
		StringBuffer sb = new StringBuffer(input);
		//将非法字符都替换为空格
		for(int i=0;i<sb.length();i++){
			char c  = sb.charAt(i);
			if(!isNumber(c) && !isOp(c) && !isBracket(c) && !isBlank(c)){
				//replace with 空格
				sb.setCharAt(i, ' ');
			}
		}
		
		List<String> inputList = new ArrayList<String>();
		
		String tempNum= "";
		int leftBracketNum =0;
		int rightBracketNum=0;
		int opNum=0;
		int numNum=0;
		for(int i=0;i<sb.length();i++){
			char c  = sb.charAt(i);
			String item = String.valueOf(c);
			
			if(isNumber(c)){
				tempNum+=item;
				
			}else{
				if(!tempNum.isEmpty()){
					
					if(tempNum.length()>1 && tempNum.startsWith("0")){
						//异常数字，例如：  034
						throw new SyntaxErrorException();
					}
					
					inputList.add(tempNum);
					tempNum = "";
					numNum++;
				}
				
				if(c=='('){
					leftBracketNum++;
					inputList.add(item);
				}else if(c==')'){
					rightBracketNum++;
					inputList.add(item);
				}else if(isOp(c)){
					inputList.add(item);
					opNum++;
				}
			}
		}
		
		if(!tempNum.isEmpty()){
			if(tempNum.length()>1 && tempNum.startsWith("0")){
				//异常数字，例如：  034
				throw new SyntaxErrorException();
			}
			
			inputList.add(tempNum);
			tempNum = "";
			numNum++;
		}
		
		if(leftBracketNum!=rightBracketNum){
			throw new SyntaxErrorException();
		}
		
		if(numNum<2){
			throw new SyntaxErrorException();
		}
		
		if(numNum != opNum+1){
			throw new SyntaxErrorException();
		}
		
		List<Item> resultList =  new ArrayList<Item>();
		Stack<Operator> opStack = new Stack<Operator>();
		
		for(int i=0;i<inputList.size();i++){
			String item = inputList.get(i);
			
			if(item.equals("+") || item.equals("-")){
				process(item,PRIORITY_PLUS,opStack,resultList);
			}else if(item.equals("*") || item.equals("/")){
				process(item,PRIORITY_MULTIPLY,opStack,resultList);
			}else if(isNumber(item)){
				resultList.add(new Item(item,TYPE_NUMBER));
			}else if(item.equals("(")){
				opStack.push(new Operator("(", PRIORITY_BRACKET));
			}else if(item.equals(")")){
				processRightBracket( opStack,resultList);
			}else{
				//blank, do nothing
			}
		}
		
		while(!opStack.isEmpty()){
			Operator opOld = opStack.pop();
			resultList.add(new Item(opOld.op,TYPE_OP));
		}
		
		StringBuffer result = new StringBuffer();
		int previousType = -1;
		for(int i=0;i<resultList.size();i++){
			Item item = resultList.get(i);
			
			if(previousType == TYPE_NUMBER && item.type==TYPE_NUMBER){
				result.append(" ");
			}
			
			result.append(item.v);
			
			//save type
			previousType = item.type;
		}

		return result.toString();
	}
	
	private boolean isNumber(String item){
		if(item==null || item.length()==0){
			return false;
		}
		
		for(int i=0;i<item.length();i++){
			char c = item.charAt(i);
			if(!isNumber(c)){
				return false;
			}
		}
		
		return true;
	}
	
	private void process(String item, int priority,Stack<Operator> opStack,
			List<Item> resultList ){
		
		while(!opStack.isEmpty()){
			Operator op = opStack.peek();
			
			if(op.op.equals("(")){
				break;
			}
			
			//进行两两比较
			
			if(op.priority < priority){
				//新操作符的优先级更高，老操作符不出栈， 直接break； 例如: 1-2*3
				break;
			}
			
			//老的符号出栈并加入结果集合
			//例1： 1*2+3,  *出栈
			//例2：1+3+5,   第一个+出栈，  即使后面是1+3+5*6,  乘号影响的也是紧邻的+号，第一个+不影响。 
			Operator opOld = opStack.pop();
			resultList.add(new Item(opOld.op,TYPE_OP));
		}
		//新 符号总是要进栈
		opStack.push(new Operator(item, priority));
	}

	private void processRightBracket(Stack<Operator> opStack,
			List<Item> resultList){
		while(!opStack.isEmpty()){
			Operator op = opStack.peek();
			if(op.op.equals("(")){
				opStack.pop();
				break;
			}
			
			Operator opOld = opStack.pop();
			resultList.add(new Item(opOld.op,TYPE_OP));
		}
	}
	
	private boolean isOp(char c){
		return "+-*/".indexOf(c)!=-1;
	}
	
	private boolean isBracket(char c){
		return "()".indexOf(c)!=-1;
	}
	
	private boolean isBlank(char c){
		return " ".indexOf(c)!=-1;
	}
	
	private boolean isNumber(char c){
		return "0123456789".indexOf(c)!=-1;
	}
	
	//+-
	private final static int PRIORITY_PLUS = 1;
	//*/
	private final static int PRIORITY_MULTIPLY = 2;
	//()
	private final static int PRIORITY_BRACKET = 3;
	
	private final static int TYPE_NUMBER = 1;
	private final static int TYPE_OP = 2;
	
	public static class Operator{
		
		public String op;
		public int priority;
		
		public Operator(String op, int priority) {
			this.op = op;
			this.priority = priority;
		}
	}
	
	public static class Item{
		public String v;
		public int type;
		
		public Item(String v,int type) {
			this.v=v;
			this.type=type;
		}
		
		@Override
		public String toString() {
			// TODO Auto-generated method stub
			return v;
		}
	}
}
