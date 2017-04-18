package byj.algorithm;

/**
 * 欧几里德算法又称辗转相除法，用于计算两个正整数a，b的最大公约数。
 * 定理：两个整数的最大公约数等于其中较小的那个数和两数相除余数的最大公约数。最大公约数（greatest common divisor）缩写为gcd。
 * gcd(a,b) = gcd(b,a mod b) (不妨设a>b 且r=a mod b ,r不为0)
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
