package jnjd.middlefix2postfix;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

/**
 * �������׺���ʽ��
 * 1��	��$��ʾ���ʽ�Ľ�β��
 * 2��	�ܱ��Ǹ��������������㡣������ķ��Ű�������0123456789.�����+- * /
 * �Լ��������ı����������������ַ�����Ϊ�ո�
 * 
 * ���Ϊ�沨�����ʽ��
 * 1��	�Կո�ָ���ͬ����������,������Ϊ��(1 + 2) * 3$��,  �����Ϊ��1 2+3*��
 * 1��	����ȷ����򵥵ı��ʽ��40�֣��� �罫1+2+3$ ת��Ϊ 1 2 + 3 +
 * 2��	����ȷ����ͬ�����������ȼ���30�֣������ܽ� 1+2*3$  ת��Ϊ  1 2 3 * +
 * 3��	����ȷ��������ƥ�估���ŵ��µļ������仯��20�֣������ܽ� (1+2)*3$  ת��Ϊ 1 2 + 3 *
 * 4��	����ȷʶ���������׺���ʽ�е��﷨����10�֣���������1++2$ ���׳� SyntaxErrorException�쳣
 */
public class ReversePolishNotation {

	public final static String Digitals = new String("0123456789");
	public final static String operators = new String("+-*/");
	public final static String Seperators =new String("()$");

	/**
	 * �������׺���ʽ�д����﷨����
	 *
	 */
	public static class SyntaxErrorException extends Exception{
		private static final long serialVersionUID = 5551252903635916672L;
		public SyntaxErrorException() {
		}
	}

	/**
	 * ��ʵ��convert����������׺���ʽ�ַ���infixNotationת��Ϊ�沨�����ʽ�ַ�����
	 * ���Ը�����Ҫ���ӱ����ͺ���������ע�ⲻҪ�ı�convert������ԭ��
	 * @param infixNotation ��׺���ʽ�ַ���
	 * @return �沨�����ʽ�ַ���
	 * @throws SyntaxErrorException
	 */
	public String convert(String infixNotation) throws SyntaxErrorException {
		if(infixNotation==null || infixNotation.length()==0 || !infixNotation.endsWith("$")){
			throw new SyntaxErrorException();
		}
		
		//remove end $
		String input = infixNotation.substring(0,infixNotation.length()-1);
		
		StringBuffer sb = new StringBuffer(input);
		//���Ƿ��ַ����滻Ϊ�ո�
		for(int i=0;i<sb.length();i++){
			char c  = sb.charAt(i);
			if(!isNumber(c) && !isOp(c) && !isBracket(c) && !isBlank(c)){
				//replace with �ո�
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
						//�쳣���֣����磺  034
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
				//�쳣���֣����磺  034
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
			
			//���������Ƚ�
			
			if(op.priority < priority){
				//�²����������ȼ����ߣ��ϲ���������ջ�� ֱ��break�� ����: 1-2*3
				break;
			}
			
			//�ϵķ��ų�ջ������������
			//��1�� 1*2+3,  *��ջ
			//��2��1+3+5,   ��һ��+��ջ��  ��ʹ������1+3+5*6,  �˺�Ӱ���Ҳ�ǽ��ڵ�+�ţ���һ��+��Ӱ�졣 
			Operator opOld = opStack.pop();
			resultList.add(new Item(opOld.op,TYPE_OP));
		}
		//�� ��������Ҫ��ջ
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
