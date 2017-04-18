package byj.util;

import java.math.BigDecimal;
import java.util.Arrays;

public class Factorial1 {
	
public static void main(String[] arguments) {
		
		//int, ���Լ��� �� <=16 ���ڵĽ׳�
		//long, ���Լ��㵽 <=20 ���ڵĽ׳�
		System.out.println("**********�׳˼���*************");
		for(int n=1;n<=30;n++){
			//System.out.println(n + "!  =" + factorial(n));
		}
		
		
		//int , ���Լ��㵽 <=12���ڵ�����
		//long, ���Լ��㵽 <=20���ڵ�����
		System.out.println("**********���м���*************");
		for(int n=1;n<=24;n++){
			for(int m=1;m<=n;m++){
				//System.out.println("A("+n+", "+m+") =" + arrange(n,m) );
			}
		}
		
		//long, ���Լ��㵽 <=28���ڵ����
		System.out.println("**********��ϼ���*************");
		for(int n=1;n<=28;n++){
			for(int m=1;m<=n;m++){
				//System.out.println("C("+n+", "+m+") =" + composition(n,m) );
			}
		}
		
		int x= Integer.MAX_VALUE;  //2,147,483,647   21��4748��3647   10λ���� pow(2,31)-1
		long x2 = Long.MAX_VALUE;  //9,223,372,036,854,775,807  19λ����  pow(2,63)-1
		
		Factorial1 instance1 = new Factorial1();
		//instance1.calculateAllComposition(5);
		
		Factorial1 instance2 = new Factorial1();
		//instance2.calculateAllArrangement(4);
		
		System.out.println("**********m��n�η�-���м���*************");
		Factorial1 instance3 = new Factorial1();
		instance3.calculateMPowerN(2,3);
	}

	public static BigDecimal factorial1(int n) {
		BigDecimal result = new BigDecimal(1);
		BigDecimal a;
		for (int i = 2; i <= n; i++) {
			a = new BigDecimal(i);// ��iת��ΪBigDecimal����
			result = result.multiply(a);// ����result*a����ΪBigDecimal����û�ж���*����
		}
		return result;
	}

	//���м��㹫ʽ�� n>=m A(n,m) = n*(n-1)*...*(n-m+1)  = n!/(n-m)!
	public static long arrange(int n ,int m){
		long sum=1;
		for(int i=0;i<m;i++){
			sum *= (n-i);
		}
		
		return sum;
	}
	
	//��� ���㹫ʽ�� n>=m   C(n,m) = A(n,m)/m! 
	public static long composition(int n, int m){
		
		//C(n,m) = C(n, n-m)
		//C(10,8)  ->  C(10,2) , ȷ����������С
		int newM = n/2>m?m:n-m;
		
		long a= arrange(n,newM);
		long f = factorial(newM);
		
		long result = a/f;
		return result;
	}
	
	public static long factorial(int n){
		long sum=1;
		
		for(int i=1;i<=n;i++){
			sum *= i;
		}
		
		return sum;
	}
	
	public static int factorial2(int n){
		int sum=1;
		
		for(int i=1;i<=n;i++){
			sum *= i;
		}
		
		return sum;
	}
	
	
	
	
	public static void printArray(int[][] a){

		System.out.println("printArray-> a.length:"+a.length);
		for(int i=0;i<a.length;i++){
			String formatStr = "|--";
			String spaceStr = "";
			int size = a[i].length;
			for(int j=1;j<size;j++){
				spaceStr += "      ";
			}
			System.out.println(spaceStr+formatStr+array2Str(a[i]));
		}
	}
	

	private static String array2Str(int[] a){
		StringBuilder sb=new StringBuilder();
		sb.append("{");
		for(int i=0;i<a.length;i++){
			sb.append(a[i]);
			
			if(i!= a.length-1){
				sb.append(",");
			}
		}
		
		sb.append("}");
		return sb.toString();
	}
	
	
	private int[][] array;
	private int n;
	private int arrayIndex=0;
	
	/*����ȫ��ϣ� n=4;  C(4,1)+C(4,2)+C(4,3)+C(4,4)
	�������Ϊ��
	|--{1}
	    |--{1,2}
	          |--{1,2,3}
	                |--{1,2,3,4}
	          |--{1,2,4}
	    |--{1,3}
	          |--{1,3,4}
	    |--{1,4}
	|--{2}
	    |--{2,3}
	          |--{2,3,4}
	    |--{2,4}
	|--{3}
	    |--{3,4}
	|--{4}
	*/
	public  void calculateAllComposition(int n){
		int sum = 0;
		for(int m=1;m<=n;m++){
			sum+= composition(n,m);
		}
		
		this.array = new int[sum][n];
		this.n = n;
		
		for(int i=1;i<=n;i++){
			int[] current = new int[]{i};
			calculateComposition(   current);
		}
		
		printArray(array);
	}
	
	// m ���� current ���м���Ԫ���ǺϷ���
	private  void calculateComposition(int[] current){
		if(current.length>n){
			return;
		}
		
		this.array[arrayIndex] = current.clone();
		arrayIndex++;
		int max = current[current.length-1];
		for(int i=max+1;i<=n;i++){
			int[] newCurrent = Arrays.copyOf(current, current.length+1);
			newCurrent[newCurrent.length-1] = i;
			calculateComposition(newCurrent);
		}
	}
	
	//����ȫ����
	public  void calculateAllArrangement(int n){
		int sum = 0;
		for(int m=1;m<=n;m++){
			sum+= arrange(n,m);
		}
		
		this.array = new int[sum][n];
		this.n = n;
		
		for(int i=1;i<=n;i++){
			int[] current = new int[]{i};
			calculateArrangement(current);
		}
		printArray(array);
	}
	
	private  void calculateArrangement(int[] current){
		if(current.length>n){
			return;
		}
		
		this.array[arrayIndex] = current.clone();
		arrayIndex++;
		
		for(int i=1;i<=n;i++){
			if(isExist(i,current)){
				continue;
			}
			
			int[] newCurrent = Arrays.copyOf(current, current.length+1);
			newCurrent[newCurrent.length-1] = i;
			calculateArrangement(newCurrent);
		}
	}
	
	private boolean isExist(int item, int[] a){
		for(int i=0;i<a.length;i++){
			if(a[i]==item){
				return true;
			}
		}
		return false;
	}
	
	
	/*����m��n�η������з�ʽ
	���磺m=2 (1,2),n=3(1,2,3),  2��3�η�= 2*2*2 =8
	[1, 1, 1]
	[1, 1, 2]
	[1, 2, 1]
	[1, 2, 2]
	[2, 1, 1]
	[2, 1, 2]
	[2, 2, 1]
	[2, 2, 2]
	*/
	public void calculateMPowerN(int m, int n){
		int num = (int)Math.pow(m, n);
		
		int[][] arr = new int[num][3];
		int index =0;
		int[] item = new int[3];
		calculateMPowerN( m, n,  arr , index,  item);
		
		for(int i=0;i<arr.length;i++){
			System.out.println(Arrays.toString(arr[i]));
		}
	}
	
	private int counter =0;
	
	private void calculateMPowerN(int m,int n, int[][] arr ,int index,int[] item){
		if(index >=n){
			return;
		}
		
		for(int i=1;i<=m;i++){
			item[index] = i;
			if(index == n-1){
				//item������Ԫ�ؾ��Ѿ���䣬��ʱ����һ�ݷŵ�arr��
				arr[counter] = item.clone();
				counter++;
			}
			calculateMPowerN(m,n,arr,index+1,item);
		}
	}
}
