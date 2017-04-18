package byj.algorithm;

/**
 * ŷ������㷨�ֳ�շת����������ڼ�������������a��b�����Լ����
 * �����������������Լ���������н�С���Ǹ���������������������Լ�������Լ����greatest common divisor����дΪgcd��
 * gcd(a,b) = gcd(b,a mod b) (������a>b ��r=a mod b ,r��Ϊ0)
 *
 */
public class GCDDemo {
	// a>b
	private static long gcd1(long a, long b){
		if(b==0){
			return a;
		}
		return gcd1(b, a%b);
	}
	
	public static long gcd(long a, long b){
		if(a>b){
			return gcd1(a,b);
		}else{
			return gcd1(b,a);
		}
	}
	
	public static void main(String[] args){
		long gcd = gcd(15,12);
		System.out.println("gcd:"+gcd);
	}

}
