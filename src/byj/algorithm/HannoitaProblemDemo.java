package byj.algorithm;

import java.math.BigDecimal;

/**
 * 【汉诺塔问题】
 * 法国数学家爱德华·卢卡斯曾编写过一个印度的古老传说：在世界中心贝拿勒斯（在印度北部）的圣庙里，一块黄铜板上插着三根宝石针。
 * 印度教的主神梵天在创造世界的时候，在其中一根针上从下到上地穿好了由大到小的64片金片，这就是所谓的汉诺塔。不论白天黑夜，
 * 总有一个僧侣在按照下面的法则移动这些金片：
 * 一次只移动一片，不管在哪根针上，小片必须在大片上面。僧侣们预言，当所有的金片都从梵天穿好的那根针上移到另外一根针上时，世界就将在一声霹雳中消灭，
 * 而梵塔、庙宇和众生也都将同归于尽。
 * 
 * 不管这个传说的可信度有多大，如果考虑一下把64片金片，由一根针上移到另一根针上，并且始终保持上小下大的顺序。
 * 这需要多少次移动呢?这里需要递归的方法。假设有n片，移动次数是f(n).显然f(1)=1,f(2)=3,f(3)=7，且f(k+1)=2*f(k)+1。此后不难证明f(n)=2^n-1。n=64时，
 * 假如每秒钟一次，共需多长时间呢？一个平年365天有31536000 秒，闰年366天有31622400秒，平均每年31556952秒，计算一下：
 * 18446744073709551615秒
 * 这表明移完这些金片需要5845.54亿年以上，而地球存在至今不过45亿年，太阳系的预期寿命据说也就是数百亿年。
 * 真的过了5845.54亿年，不说太阳系和银河系，至少地球上的一切生命，连同梵塔、庙宇等，都早已经灰飞烟灭。
 * 
 * f(n) = 2*f(n-1)+1;
 * f(n) = 2^n-1;
 * 
 * f(1) =1           = 2^1-1;
 * f(2) =2*1+1=3     = 2^2-1;
 * f(3) =2*(2^2-1)+1 = 2^3-1;
 * 
 * 
 * 备注：计数单位：
 * 10^4=万
 * 10^8=亿
 * 10^16=兆
 * 10^32=京
 * 万万为亿，亿亿为兆，兆兆为京。
 * 
 */
public class HannoitaProblemDemo {
	public static void main(String[] args) {
		hannoitaProblem(4,TOWER_A,TOWER_B,TOWER_C);
		
		//通过BigDecimal计算 2^64-1
		long times = (long)Math.pow(2, 62); 
		BigDecimal bd1 = new BigDecimal(times);
		BigDecimal result = bd1.multiply(new BigDecimal(4)); 
		result = result.subtract(new BigDecimal(1));  // 2^64-1 = 18446744073709551615 = 1844兆次
		
		long secondsInYear = 60*60*24*365;
		result = result.divideToIntegralValue(new BigDecimal(secondsInYear)); //584,942,417,355 年, 5849亿年
	}
	
	private final static String TOWER_A= "A"; //初始都在这个地方, 从下到上依次为 第n ，n-1， ... 1个盘子
	private final static String TOWER_B= "B";
	private final static String TOWER_C= "C"; //目标塔
	
	//A - 原始塔（移动前盘子的位置）
	//B - 临时塔
	//C - 目标搭（移动后盘子的位置）
	public static void hannoitaProblem(int n,String a,String b,String c){
		if(n==1){
			System.out.println("n:"+n+" ; "+a+"  -->  "+c);
			return; 
		}
		
		//1、上面的n-1个 从A移到 B
		hannoitaProblem(n-1,a,c,b);
		//2、最下面的第n个从A移到C
		System.out.println("n:"+n+" ; "+a+"  -->  "+c);
		//3、n-1个             从B移到C
		hannoitaProblem(n-1,b,a,c);
	}
}
