package byj.algorithm;



import java.util.Scanner;

/**
 * 
 *ȡ����Ϸ
ʱ�����ƣ�1000 ms  |  �ڴ����ƣ�65535 KB
�Ѷȣ�2
����
    ���������n��С��A��B���������Ӻ���ȡ��ÿ���˶����Կ�����һ����ȡ�˶��ٸ���Ҳ���Կ������л�ʣ�¶��ٸ����������˶��ܴ�������������������жϡ�

    ����Լ����
    ÿ���˴Ӻ�����ȡ���������Ŀ�����ǣ�1��3��7����8����

    �ֵ�ĳһ��ȡ��ʱ������Ȩ��

    A��ȡ��Ȼ��˫������ȡ��ֱ��ȡ�ꡣ

    �����õ����һ�����һ��Ϊ�������䷽��
   

    ����ȷ������˫�������ж�ʧ�������£������ض��ĳ�ʼ������A�Ƿ���Ӯ��

����
����һ������n(n<100)����ʾ��������n��������Ȼ����n��������ÿ��ռһ�У�����<10000������ʾ��ʼ������
���
���������n�У���ʾA����Ӯ�������Ϊ0��ӮΪ1����
��������
4
1
2
10
18
�������
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