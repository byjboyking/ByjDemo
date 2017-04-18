package jnjd.wordGame;

import java.util.Arrays;

public class WordGameExx {

	/**
	 * 题目如下：
	 * 甲乙两个人用一个英语单词玩游戏。两个人轮流进行，每个人每次从中删除任意一个字母，
	 * 如果剩余的字母序列是严格单调递增的（按字典序a<b<c<…z）,则这个人胜利。
	 * 两个人都足够聪明（即如果有赢的方案，都不会选输的方案），
	 * 甲先开始，问他能赢么？
	 * 
	 * 输入：一连串英文小写字母，长度不超过15，保证最开始的状态不是一个严格单调的序列。
	 * 输出：1表示甲可以赢，0表示甲不能赢。
	 * 例如：输入bad，则甲可以删除b或者a，剩余的是ad或者bd，他就赢了，输出1.
	 * 又如：输入aaa，则甲只能删除1个a，乙删除一个a，剩余1个a，乙获胜，输出0.
	 * 
	 * 考察知识点：递归，动态规划
	 * @param in
	 * @return
	 */
	public static int who(String in){
		//TODO: 小写字母， <=15个,  不是严格单调序列
		
		return isWin(in) ? 1:0;
	}
	
	private static boolean isWin(String in){
		int length = in.length();
		
		int[] removeIdxArray = new int[length];
		Arrays.fill(removeIdxArray, -1);
		char[] removeOwnerArray = new char[length];
		//初始值U,  甲乙分别用A,B 表示
		Arrays.fill(removeOwnerArray, 'U');
		
		//将 String in  保存到 char[] 保存
		char[] inCharArray = new char[length];
		for(int i=0;i<in.length();i++){
			inCharArray[i] = in.charAt(i);
		}
		
		String[] strArray = new String[length];
		
		
		//第几轮移除
		int order = 0;
		
		
//		for(int i=0;i<in.length();i++){
//			if(isWin(in, i)){
//				return true;
//			}
//		}
		return false;
	}
	
	private static boolean isAWin(String in,char[] inCharArray, int[] removeIdxArray,
			char[] removeOwnerArray,int order){
		if(order>inCharArray.length){
			//TODO:??
			return false;
		}
		
		boolean isA = (order+2)%2 ==0 ;
		
		for(int i=0;i<inCharArray.length;i++){
			
			
			StringBuilder  sb = new  StringBuilder(in);
			sb.deleteCharAt(i);
			
			//场景1：删除字符后 变为升序序列， 可以赢
			if(isIncreasing(sb)){
				return true;
			}
			
			
			
			removeIdxArray[order]=i;
			
			removeOwnerArray[order] = isA?CHAR_A:CHAR_B;

			
			
			
		}
		
		
		return false;
	}
	
	

	
	private static final char CHAR_A = 'A';
	private static final char CHAR_B = 'B';
	
	
	
	//ok
	public static boolean isIncreasing(StringBuilder sb){
		if(sb.length()==1){
			return true;
		}

		for(int i=0;i<sb.length()-1;i++){
			char c1 = sb.charAt(i);
			char c2 = sb.charAt(i+1);
			if(c1>=c2){
				return false;
			}
		}
		
		return true;
	}

}
