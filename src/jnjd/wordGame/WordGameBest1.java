package jnjd.wordGame;

public class WordGameBest1 {

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
		if(in==null || in.length()==0 || in.length()>15 || !isLowerChar(in) 
				|| isStrictIncrease(in)){
			return 0;
		}
		
		//�ݹ�ʵ��
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
			
			//ע��ú��������˼·...
			//��ǰ�˻�ʤ�����������
			//1�����ɾ��ĳ���ַ����ϸ��������ǰ�˻�ʤ
			//2�����ɾ��ĳ���ַ��󣬶���û����ʤ�� ��ǰ�˻�ʤ�� �����ģ�A��ʤ��ǰ����Bʧ�ܣ� Aʧ�ܵ�ǰ����B��ʤ������ʵ��һ���ݹ��㷨
			// �ٷ�˼һ���Լ���ǰ��˼·�������ŵ�ǰ�� ��ÿһ���ַ���ɾ��һ�� ��֤�Ƿ񵥵������� ˼·�����ˡ�
			if(isStrictIncrease(subInput)){
				return true;
			}else if(!whoWin(subInput)){
				return true;
			}
		}
		
		//��ǰ�˻�ʤֻ������2�����������ѭ��ɾ��ÿ��λ���ַ�����Ȼ�޷���ʤ���������ˡ�
		return false;
	}
}
