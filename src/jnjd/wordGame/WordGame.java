package jnjd.wordGame;

public class WordGame {

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
		
		return isWinInternal(in) ? 1:0;
	}
	
	
	private static boolean isWinInternal(String in){
//		int order = 0;
//		
//		for(int i=0;i<in.length();i++){
//			if(isWinEx(in, i)){
//				return true;
//			}
//		}
		
		return false;
		
		
	}
	
//	private static boolean isWin(String in){
//		
//		return false;
//	}
	
	private static boolean isWinEx(String in, int index){
		boolean rtn =false;

		
		StringBuilder  sb = new  StringBuilder(in);
		sb.deleteCharAt(index);
		
		//����1��ɾ���ַ��� ��Ϊ�������У� ����Ӯ
		if(isIncreasing(sb)){
			return true;
		}
		
//		//����2������Ӯ�� �׾���
//		if(isWin(sb.toString())){
//			return false;
//		}
//		
//		//����3�� ��Ҳ����Ӯ��ѡ��һ�����ü�Ӯ�ķ�ʽ
//		
		
		
		
		return rtn;
		
	}
	
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
