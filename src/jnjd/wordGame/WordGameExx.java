package jnjd.wordGame;

import java.util.Arrays;

public class WordGameExx {

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
		//TODO: Сд��ĸ�� <=15��,  �����ϸ񵥵�����
		
		return isWin(in) ? 1:0;
	}
	
	private static boolean isWin(String in){
		int length = in.length();
		
		int[] removeIdxArray = new int[length];
		Arrays.fill(removeIdxArray, -1);
		char[] removeOwnerArray = new char[length];
		//��ʼֵU,  ���ҷֱ���A,B ��ʾ
		Arrays.fill(removeOwnerArray, 'U');
		
		//�� String in  ���浽 char[] ����
		char[] inCharArray = new char[length];
		for(int i=0;i<in.length();i++){
			inCharArray[i] = in.charAt(i);
		}
		
		String[] strArray = new String[length];
		
		
		//�ڼ����Ƴ�
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
			
			//����1��ɾ���ַ��� ��Ϊ�������У� ����Ӯ
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
