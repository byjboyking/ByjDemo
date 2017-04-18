package jnjd.middlefix2postfix;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

/**
 * ���ǵ��ճ�ϰ���ǰ��������ʽд����׺ʽ�������ڻ�����˵����ϰ���ڡ���׺ʽ�����沨�����ʽ,�ô��ǲ���Ҫʹ�����ţ���
 * �����������ʽ����׺ʽ�ͺ�׺ʽ������һ������ݽṹ�鶼��������ݿɹ��ο���
 * ���ﲻ��׸����������������ǽ���׺ʽ��Ϊ��׺ʽ��
 * ����:
 * ÿ���������ֻ��һ�У���һ�����Ȳ�����1000���ַ�����
 * ��ʾ�������ʽ����׺ʽ��
 * ������ʽ��ֻ����+- *  /��С�����⼸�ַ��š�����С���ſ���Ƕ��ʹ�á�
 * ���ݱ�֤����Ĳ������в�����ָ�����
 * ���ݱ�֤��������Ϊ0
 * ��������֤��������
 * �����
 * ÿ�鶼�������׺ʽ��Ӧ�� ��׺ʽ��Ҫ�����ڵĲ������������ÿո������
 *
 */
public class Middlefix2Postfix {
	private final static int PRIORITY_PLUS_MINUS = 1;
	private final static int PRIORITY_MULTIPLY =2;
	
	/**
	 * ���ܣ���׺���ʽת��Ϊ��׺���ʽ
	 * @param strInExp ��׺���ʽ�ַ���
	 * @return ����ĺ�׺���ʽ
	 * 
	 * Լ����
	 * �����ַ������Ȳ�����1000.
	 * ��׺�ַ�������\0���������ʽ�ĺϷ����ɵ����߱�֤��
	 * ������������������������Ч���ɵ����߱�֤��
	 * ������
	 * ���룺 8+9
	 * ����� 8 9 +
	 * ���룺 (1+2)*3
	 * ����� 1 2 + 3 *
	 */
	public static String expChange(String strInExp){
		//TODO: �����ַ������Ȳ�����1000.
		Stack<String> stack = new Stack<String>();
		List<String> result = new ArrayList<String>();
		List<String> inputList = processInput(strInExp);
		
		for(int i=0;i<inputList.size();i++){
			final String item = inputList.get(i);
			if(item.equals("+") || item.equals("-")){
				processChar(stack,result,item,PRIORITY_PLUS_MINUS);
			}else if(item.equals("*") || item.equals("/")){
				processChar(stack,result,item,PRIORITY_MULTIPLY);
			}else if(item.equals("(") ){
				stack.push(item);
			}else if(item.equals(")") ){
				processLeftBracket(stack,result);
			}else{
				//���֣�ֱ�Ӽӵ��������
				result.add(item);
			}
		}
		
		//��һ��ջ
		while(!stack.isEmpty()){
			String topItem = stack.pop();
			result.add(topItem);
		}
		
		//��ӿո�ת��Ϊ����ַ���
		StringBuilder sb = new StringBuilder();
		for(int i=0;i<result.size();i++){
			sb.append(result.get(i));
			if(i!= result.size()-1){
				sb.append(" ");
			}
		}
		return sb.toString();
	}
	
	//"12+33*44"  ->  {"12", "+", "33", "*", "44"}
	public static List<String> processInput(String input){
		List<String> inputList =new ArrayList<String>();
		String temp="";
		for(int i=0;i<input.length();i++){
			char c = input.charAt(i);
			if(c=='+' || c=='-' || c=='*' || c=='/' || c=='(' || c==')'){
				if(!temp.isEmpty()){
					inputList.add(temp);
					temp = "";
				}
				inputList.add(String.valueOf(c));
			}else{
				temp+= String.valueOf(c);
			}
		}
		
		if(!temp.isEmpty()){
			inputList.add(temp);
			temp = "";
		}
		
		return inputList;
	}
	
	private static void processChar(Stack<String> stack,List<String> result,String item, int newPriority){
		while(!stack.isEmpty()){
			//ȡ��ջ����Ԫ��
			String topItem = stack.pop();
			if(topItem.equals("(")){
				//�����Ų�����������push��ȥ
				stack.push(topItem);
				break;
			}
			int topItemPriority = PRIORITY_PLUS_MINUS;
			if(topItem.equals("*") || topItem.equals("/")){
				topItemPriority = PRIORITY_MULTIPLY;
			}
			//�Ƚ�����������ȼ�
			if(topItemPriority<newPriority){
				//����ջ����Ҫ����Ԫ�غ���ִ�У����Բ��� ��ջ��
				stack.push(topItem);
				break;
			}else{
				//��ջ
				result.add(topItem);
			}
		}
		stack.push(item);
	}
	
	//һֱ��ջ��ֱ������������
	private static void processLeftBracket(Stack<String> stack,List<String> result){
		while(!stack.isEmpty()){
			//ȡ��ջ����Ԫ��
			String topItem = stack.pop();
			if(topItem.equals("(")){
				break;
			}else{
				result.add(topItem);
			}
		}
	}
}
