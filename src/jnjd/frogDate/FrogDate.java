package jnjd.frogDate;

import byj.util.U;

/**
 * 【青蛙约会】
 * 
 * 两只青蛙在网上相识了，他们聊得很开心，于是觉得很有必要见上一面。
 * 他们很高兴地发现他们住在同一条纬度线上，于是它们约定各自朝西跳，
 * 直到碰面为止。可是它们出发之前忘记了一件很重要的事情，
 * 既没有问清楚对方的特征，也没有约定见面的具体位置。
 * 不过青蛙们都是很乐观的，它们觉得只要一直朝着某个方向跳下去，总能碰到对方的。
 * 但是除非这两只青蛙在同一时间跳到同一点上，不然是永远都不可能碰面的。
 * 为了帮忙这两只乐观的青蛙，你被要求写一个程序来判断这两只青蛙是否能够碰面，
 * 会在什么时候碰面。
 * 我们把这 两只青蛙分别叫着青蛙A和青蛙B,并且规定纬度线上东经0度处为原点，
 * 由东往西为正方向，单位长度 1米，这样我们就得到了一条首尾相接的数轴。
 * 设青蛙A的出发点作为是x，青蛙B的出发点坐标是y。
 * 青蛙A一次能跳m米，青蛙B 一次能跳n米，两只青蛙跳一次所花费的时间相同。
 * 纬度线总长L米。现在要你求出它们跳几次以后才会碰面。
 * 
 */
public class FrogDate {
	private static final long MAX_INT = 2000000000;
	private static final String IMPOSSIBLE = "Impossible";
	
	/**
	 * 计算青蛙是否能够碰面。如果永远不能碰面返回Impossible。如果能够碰面，输出碰面所需要的跳跃次数。
	 * 相关函数，对于重要的函数建议注释	
	 * Input:
	 * 输入 只包括一行5个整数x,y,m,n,L, 其中 x !=y <2000000000, 0<m,n<2000000000,
	 * Output:
	 * 输出碰面 所需要的跳跃次数，如果永远不可能碰面则输出一行 “Impossible”
	 * Sample Input:
	 * 1 2 3 4 5
	 * Sample Output:
	 * 4
	 * @param p 参数数组，依次是x,y,m,n,L
	 * @return
	 */
	public  static String judgeMeet(long[] p){
		if(p==null || p.length!=5){
			U.err("judgeMeet->p==null || p.length!=5， impossible");
			return IMPOSSIBLE;
		}
		
		long x = p[0];
		long y = p[1];
		long m = p[2];
		long n = p[3];
		long L = p[4];
		
		if(x==y){
			U.err("judgeMeet->x==y， impossible");
			return IMPOSSIBLE;
		}
		
		if(x>=MAX_INT || y>=MAX_INT || x<0 || y<0){
			U.err("judgeMeet->x>=MAX_INT || y>=MAX_INT || x<0 || y<0， impossible");
			return IMPOSSIBLE;
		}
		
		if(m<=0 || m>=MAX_INT || n<=0 || n>=MAX_INT){
			U.err("judgeMeet->m<=0 || m>=MAX_LONG || n<=0 || n>=MAX_LONG， impossible");
			return IMPOSSIBLE;
		}
		
		//x<2000000000  -> L<=2000000000
		if(L<=0 || L>MAX_INT){
			U.err("judgeMeet->L<=0 || L>MAX_INT， impossible");
			return IMPOSSIBLE;
		}
		
		//检查步长，转换为1~L-1 的数
		m = m%L;
		n = n%L;
		
		if(m==n){
			U.err("judgeMeet->步长相同 ， impossible");
			return IMPOSSIBLE;
		}
		
		//追赶问题 
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
			U.err("judgeMeet->pos1==pos2， impossible");
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
			
			//下一次碰到或超过 需要的次数
			long times = distance/diffPerOnce;
			if(distance%diffPerOnce==0){
				counter += times;
				U.err("judgeMeetEx->碰到 ,counter:"+counter);
				return String.valueOf(counter);
			}else{
				times ++;
				pos1 = (pos1+times*step1)%L;
				pos2 = (pos2+times*step2)%L;
				
				counter+=times;
			}
			
			if(pos1==initPos1 && pos2==initPos2){
				U.err("judgeMeetEx->回到初始位置，不可能碰到，return IMPOSSIBLE ,counter:"+counter);
				return IMPOSSIBLE;
			}
			
			//注意：循环的最长次数为L次， 因为L次时，两个青蛙一定都会回到初始位置。
			if(counter>L){
				U.err("judgeMeetEx->counter>L, return IMPOSSIBLE ,counter:"+counter);
				return IMPOSSIBLE;
			}
		}
	}
}
