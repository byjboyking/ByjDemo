package jnjd.frogDate;

import byj.util.U;

/**
 * ������Լ�᡿
 * 
 * ��ֻ������������ʶ�ˣ������ĵúܿ��ģ����Ǿ��ú��б�Ҫ����һ�档
 * ���Ǻܸ��˵ط�������ס��ͬһ��γ�����ϣ���������Լ�����Գ�������
 * ֱ������Ϊֹ���������ǳ���֮ǰ������һ������Ҫ�����飬
 * ��û��������Է���������Ҳû��Լ������ľ���λ�á�
 * ���������Ƕ��Ǻ��ֹ۵ģ����Ǿ���ֻҪһֱ����ĳ����������ȥ�����������Է��ġ�
 * ���ǳ�������ֻ������ͬһʱ������ͬһ���ϣ���Ȼ����Զ������������ġ�
 * Ϊ�˰�æ����ֻ�ֹ۵����ܣ��㱻Ҫ��дһ���������ж�����ֻ�����Ƿ��ܹ����棬
 * ����ʲôʱ�����档
 * ���ǰ��� ��ֻ���ֱܷ��������A������B,���ҹ涨γ�����϶���0�ȴ�Ϊԭ�㣬
 * �ɶ�����Ϊ�����򣬵�λ���� 1�ף��������Ǿ͵õ���һ����β��ӵ����ᡣ
 * ������A�ĳ�������Ϊ��x������B�ĳ�����������y��
 * ����Aһ������m�ף�����B һ������n�ף���ֻ������һ�������ѵ�ʱ����ͬ��
 * γ�����ܳ�L�ס�����Ҫ����������������Ժ�Ż����档
 * 
 */
public class FrogDate {
	private static final long MAX_INT = 2000000000;
	private static final String IMPOSSIBLE = "Impossible";
	
	/**
	 * ���������Ƿ��ܹ����档�����Զ�������淵��Impossible������ܹ����棬�����������Ҫ����Ծ������
	 * ��غ�����������Ҫ�ĺ�������ע��	
	 * Input:
	 * ���� ֻ����һ��5������x,y,m,n,L, ���� x !=y <2000000000, 0<m,n<2000000000,
	 * Output:
	 * ������� ����Ҫ����Ծ�����������Զ���������������һ�� ��Impossible��
	 * Sample Input:
	 * 1 2 3 4 5
	 * Sample Output:
	 * 4
	 * @param p �������飬������x,y,m,n,L
	 * @return
	 */
	public  static String judgeMeet(long[] p){
		if(p==null || p.length!=5){
			U.err("judgeMeet->p==null || p.length!=5�� impossible");
			return IMPOSSIBLE;
		}
		
		long x = p[0];
		long y = p[1];
		long m = p[2];
		long n = p[3];
		long L = p[4];
		
		if(x==y){
			U.err("judgeMeet->x==y�� impossible");
			return IMPOSSIBLE;
		}
		
		if(x>=MAX_INT || y>=MAX_INT || x<0 || y<0){
			U.err("judgeMeet->x>=MAX_INT || y>=MAX_INT || x<0 || y<0�� impossible");
			return IMPOSSIBLE;
		}
		
		if(m<=0 || m>=MAX_INT || n<=0 || n>=MAX_INT){
			U.err("judgeMeet->m<=0 || m>=MAX_LONG || n<=0 || n>=MAX_LONG�� impossible");
			return IMPOSSIBLE;
		}
		
		//x<2000000000  -> L<=2000000000
		if(L<=0 || L>MAX_INT){
			U.err("judgeMeet->L<=0 || L>MAX_INT�� impossible");
			return IMPOSSIBLE;
		}
		
		//��鲽����ת��Ϊ1~L-1 ����
		m = m%L;
		n = n%L;
		
		if(m==n){
			U.err("judgeMeet->������ͬ �� impossible");
			return IMPOSSIBLE;
		}
		
		//׷������ 
		long step1=0;
		long step2=0;
		long pos1 =0;
		long pos2 = 0;
		
		//make sure: step2 >step1
		if (m < n) {
			step2 = n;
			step1 = m;
			pos2 = y;
			pos1 = x;
		} else {
			step2 = m;
			step1 = n;
			pos2 = x;
			pos1 = y;
		}
		
		//make sure pos>=0 && pos<=L-1
		if(pos1>=L){
			pos1 = pos1%L;
		}
		if(pos2>=L){
			pos2 = pos2%L;
		}
		
		if(pos1==pos2){
			U.err("judgeMeet->pos1==pos2�� impossible");
			return IMPOSSIBLE;
		}

		return judgeMeetEx( step1,  step2,  pos1,  pos2,  L);
	}
	
	//step2 >step1
	private static String judgeMeetEx(long step1, long step2, long pos1, long pos2, long L){
		final long diffPerOnce = step2-step1;
		final long initPos1 = pos1;
		final long initPos2 = pos2;
		
		long counter=0;
		while(true){
			long distance = pos1-pos2;
			if(distance<0){
				distance+=L;
			}
			
			//��һ�������򳬹� ��Ҫ�Ĵ���
			long times = distance/diffPerOnce;
			if(distance%diffPerOnce==0){
				counter += times;
				U.err("judgeMeetEx->���� ,counter:"+counter);
				return String.valueOf(counter);
			}else{
				times ++;
				pos1 = (pos1+times*step1)%L;
				pos2 = (pos2+times*step2)%L;
				
				counter+=times;
			}
			
			if(pos1==initPos1 && pos2==initPos2){
				U.err("judgeMeetEx->�ص���ʼλ�ã�������������return IMPOSSIBLE ,counter:"+counter);
				return IMPOSSIBLE;
			}
			
			//ע�⣺ѭ���������ΪL�Σ� ��ΪL��ʱ����������һ������ص���ʼλ�á�
			if(counter>L){
				U.err("judgeMeetEx->counter>L, return IMPOSSIBLE ,counter:"+counter);
				return IMPOSSIBLE;
			}
		}
	}
}
