package jnjd.wordGame;

import java.util.Arrays;

public class WordGameBest2 {

	/**
	 * ��Ŀ���£�
	 * ������������һ��Ӣ�ﵥ������Ϸ���������������У�ÿ����ÿ�δ���ɾ������һ����ĸ��
	 * ���ʣ�����ĸ�������ϸ񵥵������ģ����ֵ���a<b<c<��z��,�������ʤ����
	 * �����˶��㹻�������������Ӯ�ķ�����������ѡ��ķ�������
	 * ���ȿ�ʼ��������Ӯô��
	 * 
	 * ���룺һ����Ӣ��Сд��ĸ�����Ȳ�����15����֤�ʼ��״̬����һ���ϸ񵥵������С�
	 * �����1��ʾ�׿���Ӯ��0��ʾ�ײ���Ӯ��
	 * ���磺����bad����׿���ɾ��b����a��ʣ�����ad����bd������Ӯ�ˣ����1.
	 * ���磺����aaa�����ֻ��ɾ��1��a����ɾ��һ��a��ʣ��1��a���һ�ʤ�����0.
	 * 
	 * ����֪ʶ�㣺�ݹ飬��̬�滮
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
