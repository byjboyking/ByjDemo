package jnjd.wordGame;

public class WordGameBest1 {

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
		if(in==null || in.length()==0 || in.length()>15 || !isLowerChar(in) 
				|| isStrictIncrease(in)){
			return 0;
		}
		
		//递归实现
		return whoWin(in)?1:0;
	}
	
	public static boolean isLowerChar(String in){
		for(int i=0;i<in.length();i++){
			char c = in.charAt(i);
			if(c<'a' || c>'z'){
				return false;
			}
		}
		
		return true;
	}
	
	
	public static boolean isStrictIncrease(String in){
		if(in.length()==1){
			return true;
		}
		
		for(int i=1;i<in.length();i++){
			char c1 = in.charAt(i-1);
			char c2 = in.charAt(i);
			if(c1 >=c2){
				return false;
			}
		}
		
		return true;
	}
	
	private static boolean whoWin(String word){
		if(word.length()==2){
			return true;
		}
		
		for(int charIndex=0;charIndex<word.length();charIndex++){
			String subInput = new StringBuilder(word).deleteCharAt(charIndex).toString();
			
			//注意好好理解这种思路...
			//当前人获胜有两种情况：
			//1、如果删除某个字符后，严格递增，则当前人获胜
			//2、如果删除某个字符后，对手没法获胜， 则当前人获胜。 （博弈，A获胜的前提是B失败； A失败的前提是B获胜），其实是一个递归算法
			// 再反思一下自己以前的思路，总想着当前人 吧每一个字符都删除一下 验证是否单调递增， 思路走歪了。
			if(isStrictIncrease(subInput)){
				return true;
			}else if(!whoWin(subInput)){
				return true;
			}
		}
		
		//当前人获胜只有上面2种情况，所以循环删除每个位置字符，仍然无法获胜，就是输了。
		return false;
	}
}
