package byj.algorithm;

import java.util.ArrayList;
import java.util.List;

import byj.util.U;

//环形跳跃问题。
//环上有L个节点:  0->1->2...->L-1 ->0...
//步长step:为 1~L-1 (0和L步是一样的，又会回到初始位置)
//初始位置假设为0；
//计算是否可以覆盖到所有的节点。

public class RingStepDemo {
	public static void main(String[] args) {
		
		//L = 3 , STEP=1;
		for(int l=3;l<=20;l++){
			String msg = "## l="+l;
			System.out.println(msg);
			for(int step=1;step<=l-1;step++){
				System.out.println(U.constructSpace(msg.length())+"step:"+step+" result:"+isCover(l,step));
			}	
		}
		
		
		/*
		 * 
		 【质数】：质数（prime number）又称素数，有无限个。质数定义为在大于1的自然数中，除了1和它本身以外不再有其他因数的数称为质数。
		 【合数】：Composite number，指自然数中除了能被1和本身整除外，还能被其他数（0除外）整除的数。
		 【因数】：a*b=c（a、b、c都是整数)，那么我们称a和b就是c的因数。英文名Factor，也称为约数。
		 1既不属于质数也不属于合数
		 
		 1、【step=1， 可以全覆盖】
		 2、step 和 l-step是相同的，只是顺序是反向的
		 3、
		  如果l为质数：则其一定可以全覆盖
		  如果l为合数： step 为某个因数（不包括1和他本身）的倍数，则一定不能全覆盖。   【l被step整除，不能全覆盖， 是其中一种特殊场景】
		 
		
		## l=3
		      step:1 result:isCover:true ; num:4 list:[0, 1, 2, 0]
		      step:2 result:isCover:true ; num:4 list:[0, 2, 1, 0]
		## l=4
		      step:1 result:isCover:true ; num:5 list:[0, 1, 2, 3, 0] 
		      step:2 result:isCover:false ; num:3 list:[0, 2, 0]        【l被step整除，不能全覆盖】
		      step:3 result:isCover:true ; num:5 list:[0, 3, 2, 1, 0]
		## l=5
		      step:1 result:isCover:true ; num:6 list:[0, 1, 2, 3, 4, 0]
		      step:2 result:isCover:true ; num:6 list:[0, 2, 4, 1, 3, 0]
		      step:3 result:isCover:true ; num:6 list:[0, 3, 1, 4, 2, 0]
		      step:4 result:isCover:true ; num:6 list:[0, 4, 3, 2, 1, 0]
		## l=6
		      step:1 result:isCover:true ; num:7 list:[0, 1, 2, 3, 4, 5, 0]
		      step:2 result:isCover:false ; num:4 list:[0, 2, 4, 0]   【l被step整除，不能全覆盖】
		      step:3 result:isCover:false ; num:3 list:[0, 3, 0]       【l被step整除，不能全覆盖】
		      step:4 result:isCover:false ; num:4 list:[0, 4, 2, 0]    step 和 l-step是相同的，只是顺序是反向的
		      step:5 result:isCover:true ; num:7 list:[0, 5, 4, 3, 2, 1, 0]
		## l=7
		      step:1 result:isCover:true ; num:8 list:[0, 1, 2, 3, 4, 5, 6, 0]
		      step:2 result:isCover:true ; num:8 list:[0, 2, 4, 6, 1, 3, 5, 0]
		      step:3 result:isCover:true ; num:8 list:[0, 3, 6, 2, 5, 1, 4, 0]
		      step:4 result:isCover:true ; num:8 list:[0, 4, 1, 5, 2, 6, 3, 0]
		      step:5 result:isCover:true ; num:8 list:[0, 5, 3, 1, 6, 4, 2, 0]
		      step:6 result:isCover:true ; num:8 list:[0, 6, 5, 4, 3, 2, 1, 0]
		## l=8
		      step:1 result:isCover:true ; num:9 list:[0, 1, 2, 3, 4, 5, 6, 7, 0]
		      step:2 result:isCover:false ; num:5 list:[0, 2, 4, 6, 0]
		      step:3 result:isCover:true ; num:9 list:[0, 3, 6, 1, 4, 7, 2, 5, 0]
		      step:4 result:isCover:false ; num:3 list:[0, 4, 0]
		      step:5 result:isCover:true ; num:9 list:[0, 5, 2, 7, 4, 1, 6, 3, 0]
		      step:6 result:isCover:false ; num:5 list:[0, 6, 4, 2, 0]
		      step:7 result:isCover:true ; num:9 list:[0, 7, 6, 5, 4, 3, 2, 1, 0]
		      
		      
		## l=9
		      step:1 result:isCover:true ; num:10 list:[0, 1, 2, 3, 4, 5, 6, 7, 8, 0]
		      step:2 result:isCover:true ; num:10 list:[0, 2, 4, 6, 8, 1, 3, 5, 7, 0]
		      step:3 result:isCover:false ; num:4 list:[0, 3, 6, 0]
		      step:4 result:isCover:true ; num:10 list:[0, 4, 8, 3, 7, 2, 6, 1, 5, 0]
		      step:5 result:isCover:true ; num:10 list:[0, 5, 1, 6, 2, 7, 3, 8, 4, 0]
		      step:6 result:isCover:false ; num:4 list:[0, 6, 3, 0]
		      step:7 result:isCover:true ; num:10 list:[0, 7, 5, 3, 1, 8, 6, 4, 2, 0]
		      step:8 result:isCover:true ; num:10 list:[0, 8, 7, 6, 5, 4, 3, 2, 1, 0]
		## l=10
		       step:1 result:isCover:true ; num:11 list:[0, 1, 2, 3, 4, 5, 6, 7, 8, 9, 0]
		       step:2 result:isCover:false ; num:6 list:[0, 2, 4, 6, 8, 0]
		       step:3 result:isCover:true ; num:11 list:[0, 3, 6, 9, 2, 5, 8, 1, 4, 7, 0]
		       step:4 result:isCover:false ; num:6 list:[0, 4, 8, 2, 6, 0]  
		       step:5 result:isCover:false ; num:3 list:[0, 5, 0]
		       step:6 result:isCover:false ; num:6 list:[0, 6, 2, 8, 4, 0]
		       step:7 result:isCover:true ; num:11 list:[0, 7, 4, 1, 8, 5, 2, 9, 6, 3, 0]
		       step:8 result:isCover:false ; num:6 list:[0, 8, 6, 4, 2, 0]
		       step:9 result:isCover:true ; num:11 list:[0, 9, 8, 7, 6, 5, 4, 3, 2, 1, 0]
		## l=11
		       step:1 result:isCover:true ; num:12 list:[0, 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 0]
		       step:2 result:isCover:true ; num:12 list:[0, 2, 4, 6, 8, 10, 1, 3, 5, 7, 9, 0]
		       step:3 result:isCover:true ; num:12 list:[0, 3, 6, 9, 1, 4, 7, 10, 2, 5, 8, 0]
		       step:4 result:isCover:true ; num:12 list:[0, 4, 8, 1, 5, 9, 2, 6, 10, 3, 7, 0]
		       step:5 result:isCover:true ; num:12 list:[0, 5, 10, 4, 9, 3, 8, 2, 7, 1, 6, 0]
		       step:6 result:isCover:true ; num:12 list:[0, 6, 1, 7, 2, 8, 3, 9, 4, 10, 5, 0]
		       step:7 result:isCover:true ; num:12 list:[0, 7, 3, 10, 6, 2, 9, 5, 1, 8, 4, 0]
		       step:8 result:isCover:true ; num:12 list:[0, 8, 5, 2, 10, 7, 4, 1, 9, 6, 3, 0]
		       step:9 result:isCover:true ; num:12 list:[0, 9, 7, 5, 3, 1, 10, 8, 6, 4, 2, 0]
		       step:10 result:isCover:true ; num:12 list:[0, 10, 9, 8, 7, 6, 5, 4, 3, 2, 1, 0]
		## l=12
		因数为2和3。 所有2和3的倍数都是 不能全覆盖的。
		       step:1 result:isCover:true ; num:13 list:[0, 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 0]
		       step:2 result:isCover:false ; num:7 list:[0, 2, 4, 6, 8, 10, 0]
		       step:3 result:isCover:false ; num:5 list:[0, 3, 6, 9, 0]
		       step:4 result:isCover:false ; num:4 list:[0, 4, 8, 0]
		       step:5 result:isCover:true ; num:13 list:[0, 5, 10, 3, 8, 1, 6, 11, 4, 9, 2, 7, 0]
		       step:6 result:isCover:false ; num:3 list:[0, 6, 0]
		       step:7 result:isCover:true ; num:13 list:[0, 7, 2, 9, 4, 11, 6, 1, 8, 3, 10, 5, 0]
		       step:8 result:isCover:false ; num:4 list:[0, 8, 4, 0]
		       step:9 result:isCover:false ; num:5 list:[0, 9, 6, 3, 0]
		       step:10 result:isCover:false ; num:7 list:[0, 10, 8, 6, 4, 2, 0]
		       step:11 result:isCover:true ; num:13 list:[0, 11, 10, 9, 8, 7, 6, 5, 4, 3, 2, 1, 0]
		## l=13
		       step:1 result:isCover:true ; num:14 list:[0, 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 0]
		       step:2 result:isCover:true ; num:14 list:[0, 2, 4, 6, 8, 10, 12, 1, 3, 5, 7, 9, 11, 0]
		       step:3 result:isCover:true ; num:14 list:[0, 3, 6, 9, 12, 2, 5, 8, 11, 1, 4, 7, 10, 0]
		       step:4 result:isCover:true ; num:14 list:[0, 4, 8, 12, 3, 7, 11, 2, 6, 10, 1, 5, 9, 0]
		       step:5 result:isCover:true ; num:14 list:[0, 5, 10, 2, 7, 12, 4, 9, 1, 6, 11, 3, 8, 0]
		       step:6 result:isCover:true ; num:14 list:[0, 6, 12, 5, 11, 4, 10, 3, 9, 2, 8, 1, 7, 0]
		       step:7 result:isCover:true ; num:14 list:[0, 7, 1, 8, 2, 9, 3, 10, 4, 11, 5, 12, 6, 0]
		       step:8 result:isCover:true ; num:14 list:[0, 8, 3, 11, 6, 1, 9, 4, 12, 7, 2, 10, 5, 0]
		       step:9 result:isCover:true ; num:14 list:[0, 9, 5, 1, 10, 6, 2, 11, 7, 3, 12, 8, 4, 0]
		       step:10 result:isCover:true ; num:14 list:[0, 10, 7, 4, 1, 11, 8, 5, 2, 12, 9, 6, 3, 0]
		       step:11 result:isCover:true ; num:14 list:[0, 11, 9, 7, 5, 3, 1, 12, 10, 8, 6, 4, 2, 0]
		       step:12 result:isCover:true ; num:14 list:[0, 12, 11, 10, 9, 8, 7, 6, 5, 4, 3, 2, 1, 0]
		## l=14
		       step:1 result:isCover:true ; num:15 list:[0, 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 0]
		       step:2 result:isCover:false ; num:8 list:[0, 2, 4, 6, 8, 10, 12, 0]
		       step:3 result:isCover:true ; num:15 list:[0, 3, 6, 9, 12, 1, 4, 7, 10, 13, 2, 5, 8, 11, 0]
		       step:4 result:isCover:false ; num:8 list:[0, 4, 8, 12, 2, 6, 10, 0]
		       step:5 result:isCover:true ; num:15 list:[0, 5, 10, 1, 6, 11, 2, 7, 12, 3, 8, 13, 4, 9, 0]
		       step:6 result:isCover:false ; num:8 list:[0, 6, 12, 4, 10, 2, 8, 0]
		       step:7 result:isCover:false ; num:3 list:[0, 7, 0]
		       step:8 result:isCover:false ; num:8 list:[0, 8, 2, 10, 4, 12, 6, 0]
		       step:9 result:isCover:true ; num:15 list:[0, 9, 4, 13, 8, 3, 12, 7, 2, 11, 6, 1, 10, 5, 0]
		       step:10 result:isCover:false ; num:8 list:[0, 10, 6, 2, 12, 8, 4, 0]
		       step:11 result:isCover:true ; num:15 list:[0, 11, 8, 5, 2, 13, 10, 7, 4, 1, 12, 9, 6, 3, 0]
		       step:12 result:isCover:false ; num:8 list:[0, 12, 10, 8, 6, 4, 2, 0]
		       step:13 result:isCover:true ; num:15 list:[0, 13, 12, 11, 10, 9, 8, 7, 6, 5, 4, 3, 2, 1, 0]
		## l=15
		       step:1 result:isCover:true ; num:16 list:[0, 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 0]
		       step:2 result:isCover:true ; num:16 list:[0, 2, 4, 6, 8, 10, 12, 14, 1, 3, 5, 7, 9, 11, 13, 0]
		       step:3 result:isCover:false ; num:6 list:[0, 3, 6, 9, 12, 0]
		       step:4 result:isCover:true ; num:16 list:[0, 4, 8, 12, 1, 5, 9, 13, 2, 6, 10, 14, 3, 7, 11, 0]
		       step:5 result:isCover:false ; num:4 list:[0, 5, 10, 0]
		       step:6 result:isCover:false ; num:6 list:[0, 6, 12, 3, 9, 0]
		       step:7 result:isCover:true ; num:16 list:[0, 7, 14, 6, 13, 5, 12, 4, 11, 3, 10, 2, 9, 1, 8, 0]
		       step:8 result:isCover:true ; num:16 list:[0, 8, 1, 9, 2, 10, 3, 11, 4, 12, 5, 13, 6, 14, 7, 0]
		       step:9 result:isCover:false ; num:6 list:[0, 9, 3, 12, 6, 0]
		       step:10 result:isCover:false ; num:4 list:[0, 10, 5, 0]
		       step:11 result:isCover:true ; num:16 list:[0, 11, 7, 3, 14, 10, 6, 2, 13, 9, 5, 1, 12, 8, 4, 0]
		       step:12 result:isCover:false ; num:6 list:[0, 12, 9, 6, 3, 0]
		       step:13 result:isCover:true ; num:16 list:[0, 13, 11, 9, 7, 5, 3, 1, 14, 12, 10, 8, 6, 4, 2, 0]
		       step:14 result:isCover:true ; num:16 list:[0, 14, 13, 12, 11, 10, 9, 8, 7, 6, 5, 4, 3, 2, 1, 0]
		       
		       
		       
		       
		## l=16
		       step:1 result:isCover:true ; num:17 list:[0, 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15, 0]
		       step:2 result:isCover:false ; num:9 list:[0, 2, 4, 6, 8, 10, 12, 14, 0]
		       step:3 result:isCover:true ; num:17 list:[0, 3, 6, 9, 12, 15, 2, 5, 8, 11, 14, 1, 4, 7, 10, 13, 0]
		       step:4 result:isCover:false ; num:5 list:[0, 4, 8, 12, 0]
		       step:5 result:isCover:true ; num:17 list:[0, 5, 10, 15, 4, 9, 14, 3, 8, 13, 2, 7, 12, 1, 6, 11, 0]
		       step:6 result:isCover:false ; num:9 list:[0, 6, 12, 2, 8, 14, 4, 10, 0]
		       step:7 result:isCover:true ; num:17 list:[0, 7, 14, 5, 12, 3, 10, 1, 8, 15, 6, 13, 4, 11, 2, 9, 0]
		       step:8 result:isCover:false ; num:3 list:[0, 8, 0]
		       step:9 result:isCover:true ; num:17 list:[0, 9, 2, 11, 4, 13, 6, 15, 8, 1, 10, 3, 12, 5, 14, 7, 0]
		       step:10 result:isCover:false ; num:9 list:[0, 10, 4, 14, 8, 2, 12, 6, 0]
		       step:11 result:isCover:true ; num:17 list:[0, 11, 6, 1, 12, 7, 2, 13, 8, 3, 14, 9, 4, 15, 10, 5, 0]
		       step:12 result:isCover:false ; num:5 list:[0, 12, 8, 4, 0]
		       step:13 result:isCover:true ; num:17 list:[0, 13, 10, 7, 4, 1, 14, 11, 8, 5, 2, 15, 12, 9, 6, 3, 0]
		       step:14 result:isCover:false ; num:9 list:[0, 14, 12, 10, 8, 6, 4, 2, 0]
		       step:15 result:isCover:true ; num:17 list:[0, 15, 14, 13, 12, 11, 10, 9, 8, 7, 6, 5, 4, 3, 2, 1, 0]
		## l=17
		       step:1 result:isCover:true ; num:18 list:[0, 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15, 16, 0]
		       step:2 result:isCover:true ; num:18 list:[0, 2, 4, 6, 8, 10, 12, 14, 16, 1, 3, 5, 7, 9, 11, 13, 15, 0]
		       step:3 result:isCover:true ; num:18 list:[0, 3, 6, 9, 12, 15, 1, 4, 7, 10, 13, 16, 2, 5, 8, 11, 14, 0]
		       step:4 result:isCover:true ; num:18 list:[0, 4, 8, 12, 16, 3, 7, 11, 15, 2, 6, 10, 14, 1, 5, 9, 13, 0]
		       step:5 result:isCover:true ; num:18 list:[0, 5, 10, 15, 3, 8, 13, 1, 6, 11, 16, 4, 9, 14, 2, 7, 12, 0]
		       step:6 result:isCover:true ; num:18 list:[0, 6, 12, 1, 7, 13, 2, 8, 14, 3, 9, 15, 4, 10, 16, 5, 11, 0]
		       step:7 result:isCover:true ; num:18 list:[0, 7, 14, 4, 11, 1, 8, 15, 5, 12, 2, 9, 16, 6, 13, 3, 10, 0]
		       step:8 result:isCover:true ; num:18 list:[0, 8, 16, 7, 15, 6, 14, 5, 13, 4, 12, 3, 11, 2, 10, 1, 9, 0]
		       step:9 result:isCover:true ; num:18 list:[0, 9, 1, 10, 2, 11, 3, 12, 4, 13, 5, 14, 6, 15, 7, 16, 8, 0]
		       step:10 result:isCover:true ; num:18 list:[0, 10, 3, 13, 6, 16, 9, 2, 12, 5, 15, 8, 1, 11, 4, 14, 7, 0]
		       step:11 result:isCover:true ; num:18 list:[0, 11, 5, 16, 10, 4, 15, 9, 3, 14, 8, 2, 13, 7, 1, 12, 6, 0]
		       step:12 result:isCover:true ; num:18 list:[0, 12, 7, 2, 14, 9, 4, 16, 11, 6, 1, 13, 8, 3, 15, 10, 5, 0]
		       step:13 result:isCover:true ; num:18 list:[0, 13, 9, 5, 1, 14, 10, 6, 2, 15, 11, 7, 3, 16, 12, 8, 4, 0]
		       step:14 result:isCover:true ; num:18 list:[0, 14, 11, 8, 5, 2, 16, 13, 10, 7, 4, 1, 15, 12, 9, 6, 3, 0]
		       step:15 result:isCover:true ; num:18 list:[0, 15, 13, 11, 9, 7, 5, 3, 1, 16, 14, 12, 10, 8, 6, 4, 2, 0]
		       step:16 result:isCover:true ; num:18 list:[0, 16, 15, 14, 13, 12, 11, 10, 9, 8, 7, 6, 5, 4, 3, 2, 1, 0]
		## l=18
		       step:1 result:isCover:true ; num:19 list:[0, 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15, 16, 17, 0]
		       step:2 result:isCover:false ; num:10 list:[0, 2, 4, 6, 8, 10, 12, 14, 16, 0]
		       step:3 result:isCover:false ; num:7 list:[0, 3, 6, 9, 12, 15, 0]
		       step:4 result:isCover:false ; num:10 list:[0, 4, 8, 12, 16, 2, 6, 10, 14, 0]
		       step:5 result:isCover:true ; num:19 list:[0, 5, 10, 15, 2, 7, 12, 17, 4, 9, 14, 1, 6, 11, 16, 3, 8, 13, 0]
		       step:6 result:isCover:false ; num:4 list:[0, 6, 12, 0]
		       step:7 result:isCover:true ; num:19 list:[0, 7, 14, 3, 10, 17, 6, 13, 2, 9, 16, 5, 12, 1, 8, 15, 4, 11, 0]
		       step:8 result:isCover:false ; num:10 list:[0, 8, 16, 6, 14, 4, 12, 2, 10, 0]
		       step:9 result:isCover:false ; num:3 list:[0, 9, 0]
		       step:10 result:isCover:false ; num:10 list:[0, 10, 2, 12, 4, 14, 6, 16, 8, 0]
		       step:11 result:isCover:true ; num:19 list:[0, 11, 4, 15, 8, 1, 12, 5, 16, 9, 2, 13, 6, 17, 10, 3, 14, 7, 0]
		       step:12 result:isCover:false ; num:4 list:[0, 12, 6, 0]
		       step:13 result:isCover:true ; num:19 list:[0, 13, 8, 3, 16, 11, 6, 1, 14, 9, 4, 17, 12, 7, 2, 15, 10, 5, 0]
		       step:14 result:isCover:false ; num:10 list:[0, 14, 10, 6, 2, 16, 12, 8, 4, 0]
		       step:15 result:isCover:false ; num:7 list:[0, 15, 12, 9, 6, 3, 0]
		       step:16 result:isCover:false ; num:10 list:[0, 16, 14, 12, 10, 8, 6, 4, 2, 0]
		       step:17 result:isCover:true ; num:19 list:[0, 17, 16, 15, 14, 13, 12, 11, 10, 9, 8, 7, 6, 5, 4, 3, 2, 1, 0]
		## l=19
		       step:1 result:isCover:true ; num:20 list:[0, 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15, 16, 17, 18, 0]
		       step:2 result:isCover:true ; num:20 list:[0, 2, 4, 6, 8, 10, 12, 14, 16, 18, 1, 3, 5, 7, 9, 11, 13, 15, 17, 0]
		       step:3 result:isCover:true ; num:20 list:[0, 3, 6, 9, 12, 15, 18, 2, 5, 8, 11, 14, 17, 1, 4, 7, 10, 13, 16, 0]
		       step:4 result:isCover:true ; num:20 list:[0, 4, 8, 12, 16, 1, 5, 9, 13, 17, 2, 6, 10, 14, 18, 3, 7, 11, 15, 0]
		       step:5 result:isCover:true ; num:20 list:[0, 5, 10, 15, 1, 6, 11, 16, 2, 7, 12, 17, 3, 8, 13, 18, 4, 9, 14, 0]
		       step:6 result:isCover:true ; num:20 list:[0, 6, 12, 18, 5, 11, 17, 4, 10, 16, 3, 9, 15, 2, 8, 14, 1, 7, 13, 0]
		       step:7 result:isCover:true ; num:20 list:[0, 7, 14, 2, 9, 16, 4, 11, 18, 6, 13, 1, 8, 15, 3, 10, 17, 5, 12, 0]
		       step:8 result:isCover:true ; num:20 list:[0, 8, 16, 5, 13, 2, 10, 18, 7, 15, 4, 12, 1, 9, 17, 6, 14, 3, 11, 0]
		       step:9 result:isCover:true ; num:20 list:[0, 9, 18, 8, 17, 7, 16, 6, 15, 5, 14, 4, 13, 3, 12, 2, 11, 1, 10, 0]
		       step:10 result:isCover:true ; num:20 list:[0, 10, 1, 11, 2, 12, 3, 13, 4, 14, 5, 15, 6, 16, 7, 17, 8, 18, 9, 0]
		       step:11 result:isCover:true ; num:20 list:[0, 11, 3, 14, 6, 17, 9, 1, 12, 4, 15, 7, 18, 10, 2, 13, 5, 16, 8, 0]
		       step:12 result:isCover:true ; num:20 list:[0, 12, 5, 17, 10, 3, 15, 8, 1, 13, 6, 18, 11, 4, 16, 9, 2, 14, 7, 0]
		       step:13 result:isCover:true ; num:20 list:[0, 13, 7, 1, 14, 8, 2, 15, 9, 3, 16, 10, 4, 17, 11, 5, 18, 12, 6, 0]
		       step:14 result:isCover:true ; num:20 list:[0, 14, 9, 4, 18, 13, 8, 3, 17, 12, 7, 2, 16, 11, 6, 1, 15, 10, 5, 0]
		       step:15 result:isCover:true ; num:20 list:[0, 15, 11, 7, 3, 18, 14, 10, 6, 2, 17, 13, 9, 5, 1, 16, 12, 8, 4, 0]
		       step:16 result:isCover:true ; num:20 list:[0, 16, 13, 10, 7, 4, 1, 17, 14, 11, 8, 5, 2, 18, 15, 12, 9, 6, 3, 0]
		       step:17 result:isCover:true ; num:20 list:[0, 17, 15, 13, 11, 9, 7, 5, 3, 1, 18, 16, 14, 12, 10, 8, 6, 4, 2, 0]
		       step:18 result:isCover:true ; num:20 list:[0, 18, 17, 16, 15, 14, 13, 12, 11, 10, 9, 8, 7, 6, 5, 4, 3, 2, 1, 0]
		## l=20
		       step:1 result:isCover:true ; num:21 list:[0, 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15, 16, 17, 18, 19, 0]
		       step:2 result:isCover:false ; num:11 list:[0, 2, 4, 6, 8, 10, 12, 14, 16, 18, 0]
		       step:3 result:isCover:true ; num:21 list:[0, 3, 6, 9, 12, 15, 18, 1, 4, 7, 10, 13, 16, 19, 2, 5, 8, 11, 14, 17, 0]
		       step:4 result:isCover:false ; num:6 list:[0, 4, 8, 12, 16, 0]
		       step:5 result:isCover:false ; num:5 list:[0, 5, 10, 15, 0]
		       step:6 result:isCover:false ; num:11 list:[0, 6, 12, 18, 4, 10, 16, 2, 8, 14, 0]
		       step:7 result:isCover:true ; num:21 list:[0, 7, 14, 1, 8, 15, 2, 9, 16, 3, 10, 17, 4, 11, 18, 5, 12, 19, 6, 13, 0]
		       step:8 result:isCover:false ; num:6 list:[0, 8, 16, 4, 12, 0]
		       step:9 result:isCover:true ; num:21 list:[0, 9, 18, 7, 16, 5, 14, 3, 12, 1, 10, 19, 8, 17, 6, 15, 4, 13, 2, 11, 0]
		       step:10 result:isCover:false ; num:3 list:[0, 10, 0]
		       step:11 result:isCover:true ; num:21 list:[0, 11, 2, 13, 4, 15, 6, 17, 8, 19, 10, 1, 12, 3, 14, 5, 16, 7, 18, 9, 0]
		       step:12 result:isCover:false ; num:6 list:[0, 12, 4, 16, 8, 0]
		       step:13 result:isCover:true ; num:21 list:[0, 13, 6, 19, 12, 5, 18, 11, 4, 17, 10, 3, 16, 9, 2, 15, 8, 1, 14, 7, 0]
		       step:14 result:isCover:false ; num:11 list:[0, 14, 8, 2, 16, 10, 4, 18, 12, 6, 0]
		       step:15 result:isCover:false ; num:5 list:[0, 15, 10, 5, 0]
		       step:16 result:isCover:false ; num:6 list:[0, 16, 12, 8, 4, 0]
		       step:17 result:isCover:true ; num:21 list:[0, 17, 14, 11, 8, 5, 2, 19, 16, 13, 10, 7, 4, 1, 18, 15, 12, 9, 6, 3, 0]
		       step:18 result:isCover:false ; num:11 list:[0, 18, 16, 14, 12, 10, 8, 6, 4, 2, 0]
		       step:19 result:isCover:true ; num:21 list:[0, 19, 18, 17, 16, 15, 14, 13, 12, 11, 10, 9, 8, 7, 6, 5, 4, 3, 2, 1, 0]


		       
		       
		*/
		
	}
	
	
	public static Result isCover(int l,int step){
		final int initPos = 0;
		int currentPos = 0;
		List<Integer> list =new ArrayList<Integer>();
		list.add(currentPos);
		
		while(true){
			
			currentPos = (currentPos+step)%l;
			list.add(currentPos);
			
			if(currentPos==initPos){
				break;
			}
		}
		
		boolean isCover = (list.size()-1==l);
		return new Result(isCover,list);
	}
	
	public static class Result{
		public boolean isCover = false;
		
		public List<Integer> list;
		
		public Result(boolean isCover,List<Integer> list) {
			this.isCover = isCover;
			this.list=list;
		}
		
		@Override
		public String toString() {
			
			return "isCover:"+isCover+" ; num:"+list.size()+" list:"+list;
		}
	}

}
