package byj.algorithm;

import java.math.BigDecimal;

/**
 * ����ŵ�����⡿
 * ������ѧ�Ұ��»���¬��˹����д��һ��ӡ�ȵĹ��ϴ�˵�����������ı�����˹����ӡ�ȱ�������ʥ���һ���ͭ���ϲ���������ʯ�롣
 * ӡ�Ƚ̵����������ڴ��������ʱ��������һ�����ϴ��µ��ϵش������ɴ�С��64Ƭ��Ƭ���������ν�ĺ�ŵ�������۰����ҹ��
 * ����һ��ɮ���ڰ�������ķ����ƶ���Щ��Ƭ��
 * һ��ֻ�ƶ�һƬ���������ĸ����ϣ�СƬ�����ڴ�Ƭ���档ɮ����Ԥ�ԣ������еĽ�Ƭ�������촩�õ��Ǹ������Ƶ�����һ������ʱ������ͽ���һ������������
 * �����������������Ҳ����ͬ���ھ���
 * 
 * ���������˵�Ŀ��Ŷ��ж���������һ�°�64Ƭ��Ƭ����һ�������Ƶ���һ�����ϣ�����ʼ�ձ�����С�´��˳��
 * ����Ҫ���ٴ��ƶ���?������Ҫ�ݹ�ķ�����������nƬ���ƶ�������f(n).��Ȼf(1)=1,f(2)=3,f(3)=7����f(k+1)=2*f(k)+1���˺���֤��f(n)=2^n-1��n=64ʱ��
 * ����ÿ����һ�Σ�����೤ʱ���أ�һ��ƽ��365����31536000 �룬����366����31622400�룬ƽ��ÿ��31556952�룬����һ�£�
 * 18446744073709551615��
 * �����������Щ��Ƭ��Ҫ5845.54�������ϣ�������������񲻹�45���̫꣬��ϵ��Ԥ��������˵Ҳ�����������ꡣ
 * ��Ĺ���5845.54���꣬��˵̫��ϵ������ϵ�����ٵ����ϵ�һ����������ͬ����������ȣ������Ѿ��ҷ�����
 * 
 * f(n) = 2*f(n-1)+1;
 * f(n) = 2^n-1;
 * 
 * f(1) =1           = 2^1-1;
 * f(2) =2*1+1=3     = 2^2-1;
 * f(3) =2*(2^2-1)+1 = 2^3-1;
 * 
 * 
 * ��ע��������λ��
 * 10^4=��
 * 10^8=��
 * 10^16=��
 * 10^32=��
 * ����Ϊ�ڣ�����Ϊ�ף�����Ϊ����
 * 
 */
public class HannoitaProblemDemo {
	public static void main(String[] args) {
		hannoitaProblem(4,TOWER_A,TOWER_B,TOWER_C);
		
		//ͨ��BigDecimal���� 2^64-1
		long times = (long)Math.pow(2, 62); 
		BigDecimal bd1 = new BigDecimal(times);
		BigDecimal result = bd1.multiply(new BigDecimal(4)); 
		result = result.subtract(new BigDecimal(1));  // 2^64-1 = 18446744073709551615 = 1844�״�
		
		long secondsInYear = 60*60*24*365;
		result = result.divideToIntegralValue(new BigDecimal(secondsInYear)); //584,942,417,355 ��, 5849����
	}
	
	private final static String TOWER_A= "A"; //��ʼ��������ط�, ���µ�������Ϊ ��n ��n-1�� ... 1������
	private final static String TOWER_B= "B";
	private final static String TOWER_C= "C"; //Ŀ����
	
	//A - ԭʼ�����ƶ�ǰ���ӵ�λ�ã�
	//B - ��ʱ��
	//C - Ŀ���ƶ������ӵ�λ�ã�
	public static void hannoitaProblem(int n,String a,String b,String c){
		if(n==1){
			System.out.println("n:"+n+" ; "+a+"  -->  "+c);
			return; 
		}
		
		//1�������n-1�� ��A�Ƶ� B
		hannoitaProblem(n-1,a,c,b);
		//2��������ĵ�n����A�Ƶ�C
		System.out.println("n:"+n+" ; "+a+"  -->  "+c);
		//3��n-1��             ��B�Ƶ�C
		hannoitaProblem(n-1,b,a,c);
	}
}
