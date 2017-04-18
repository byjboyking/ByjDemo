package jnjd.wordGame;

import java.util.Arrays;

public class WordGameBest2 {

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
		//reset n
		n= in.length();
		//reset state
		Arrays.fill(state, -1);
		
		int a2 = 1<<3;
		
		for(int i=0;i<(1<<n);i++){
			state[i] = 0;
		}
		
		int[] a = new int[20];
		for(int i=0;i<(1<<n);i++){
			int num=0;
			for(int j=0;j<n;j++){
				if( (i&(1<<j)) !=0 ){
					a[num++] = j;
				}
			}
			
			state[i] =1;
			
			for(int j=1;j<num;j++){
				int u= a[j];
				int v = a[j-1];
				
				if(in.charAt(u) <= in.charAt(v)  ){
					state[i]=0;
				}
			}
		}
		
		int ans = dfs( (1<<n)-1  );
		return ans;
		
	}
	
	static int [] state = new int[400000];
	static int n;
	static int dfs(int s){
		if(state[s]==1)	return 0;
		
		for(int i=0;i<n;i++){
			if( (s&(1<<i))!=0  ){
				if(dfs(s-(1<<i)   ) ==0  ){
					return 1;
				}
			}
		}
		
		return 0;
	}
	
	
}
