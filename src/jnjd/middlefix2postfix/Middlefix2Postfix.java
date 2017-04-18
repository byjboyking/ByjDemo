package jnjd.middlefix2postfix;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

/**
 * 人们的日常习惯是吧算术表达式写成中缀式，但对于机器来说更“习惯于”后缀式（即逆波兰表达式,好处是不需要使用括号），
 * 关于算术表达式的中缀式和后缀式的论述一般的数据结构书都有相关内容可供参考，
 * 这里不再赘述，现在你的任务是将中缀式变为后缀式。
 * 输入:
 * 每组测试数据只有一行，是一个长度不超过1000的字符串，
 * 表示这个运算式的中缀式。
 * 这个表达式里只包含+- *  /与小括号这几种符号。其中小括号可以嵌套使用。
 * 数据保证输入的操作数中不会出现负数。
 * 数据保证除数不会为0
 * 操作数保证都是整数
 * 输出：
 * 每组都输出该中缀式相应的 后缀式，要求相邻的操作数操作符用空格隔开。
 *
 */
public class Middlefix2Postfix {
	private final static int PRIORITY_PLUS_MINUS = 1;
	private final static int PRIORITY_MULTIPLY =2;
	
	/**
	 * 功能：中缀表达式转换为后缀表达式
	 * @param strInExp 中缀表达式字符串
	 * @return 输出的后缀表达式
	 * 
	 * 约束：
	 * 输入字符串长度不超过1000.
	 * 中缀字符串都以\0结束，表达式的合法性由调用者保证。
	 * 操作数都是整数，整数的有效性由调用者保证。
	 * 举例：
	 * 输入： 8+9
	 * 输出： 8 9 +
	 * 输入： (1+2)*3
	 * 输出： 1 2 + 3 *
	 */
	public static String expChange(String strInExp){
		//TODO: 输入字符串长度不超过1000.
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
				//数字，直接加到结果数列
				result.add(item);
			}
		}
		
		//逐一出栈
		while(!stack.isEmpty()){
			String topItem = stack.pop();
			result.add(topItem);
		}
		
		//添加空格，转换为结果字符串
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
			//取出栈顶的元素
			String topItem = stack.pop();
			if(topItem.equals("(")){
				//左括号不做处理，重新push进去
				stack.push(topItem);
				break;
			}
			int topItemPriority = PRIORITY_PLUS_MINUS;
			if(topItem.equals("*") || topItem.equals("/")){
				topItemPriority = PRIORITY_MULTIPLY;
			}
			//比较运算符的优先级
			if(topItemPriority<newPriority){
				//不出栈（需要在新元素后面执行，所以不能 出栈）
				stack.push(topItem);
				break;
			}else{
				//出栈
				result.add(topItem);
			}
		}
		stack.push(item);
	}
	
	//一直出栈，直到看到左括号
	private static void processLeftBracket(Stack<String> stack,List<String> result){
		while(!stack.isEmpty()){
			//取出栈顶的元素
			String topItem = stack.pop();
			if(topItem.equals("(")){
				break;
			}else{
				result.add(topItem);
			}
		}
	}
}
