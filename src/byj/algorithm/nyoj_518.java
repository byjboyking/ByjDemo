package byj.algorithm;



import java.util.Scanner;

/**
 * 
 *取球游戏
时间限制：1000 ms  |  内存限制：65535 KB
难度：2
描述
    今盒子里有n个小球，A、B两人轮流从盒中取球，每个人都可以看到另一个人取了多少个，也可以看到盒中还剩下多少个，并且两人都很聪明，不会做出错误的判断。

    我们约定：
    每个人从盒子中取出的球的数目必须是：1，3，7或者8个。

    轮到某一方取球时不能弃权！

    A先取球，然后双方交替取球，直到取完。

    被迫拿到最后一个球的一方为负方（输方）
   

    请编程确定出在双方都不判断失误的情况下，对于特定的初始球数，A是否能赢？

输入
先是一个整数n(n<100)，表示接下来有n个整数。然后是n个整数，每个占一行（整数<10000），表示初始球数。
输出
程序则输出n行，表示A的输赢情况（输为0，赢为1）。
样例输入
4
1
2
10
18
样例输出
0
1
1
0
 */
public class nyoj_518 {

	
	
	public static void main(String[] args) {
		
		game();
		
		
//		Scanner sc = new Scanner(System.in);
//		int T = sc.nextInt();
//		int[] a = { 1, 3, 7, 8 };
//		while (T-- > 0) {
//			int N = sc.nextInt();
//			boolean[] b = new boolean[N + 10];
//			for (int i = 1; i < N; i++)
//				if (!b[i]) {
//					for (int j = 0; j < 4; j++)
//						b[i + a[j]] = true;
//				}
//			System.out.println(b[N] ? 1 : 0);
//		}
   }
	
	
	public static void game(){
		int [] a = {1,2,3};
		int n = 5;
		
		boolean [] b = new boolean[n+10];
		for(int i=1;i<n;i++){
			if(!b[i]){
				for(int j=0;j<a.length;j++){
					b[i+a[j]] = true;
				}
			}
		}
		
		System.out.println(b[n]?1:0);
		
		
		
		
		
	}
	
	
	
}