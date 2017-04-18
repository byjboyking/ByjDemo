package jnjd.RunAndCalories;

import java.util.Arrays;
import byj.util.U;

public class RunAndCalories {
	/**
	 * 有N种阶段跑，距离（单位是公里）分别是D1、D2…Dn, 消耗热量（单位是千卡）分别是C1、C2…Dn。 
	 * 现在某人要跑S公里，可以选择多种阶段混合跑，【每种阶段可以跑多次】，要求各阶段距离总和恰好是S,
	 * 请选择一种跑法使得累积热量最大，这个累积最大热量是多少？
	 * （备注：测试用例保证至少有一种组合，使得各阶段距离总和为S）
	 * @param S  1<=S<=100
	 * @param N  1<=N<=20
	 * @param D
	 * @param C
	 * @return 累积最大热量（单位是千卡），输入异常返回-1
	 */
	public int maxCalorie(int S,int N, int[] D, int[] C){
		//入口参数合法性检查
		if(D==null || C==null){
			U.err("maxCalorie->  D==null || C==null, return -1;");
			return -1;
		}
		
		if(N<1 || N>20){
			U.err("maxCalorie->  N<1 || N>20, return -1;");
			return -1;
		}
		
		if(S<1 || S>100){
			U.err("maxCalorie->  S<1 || S>100, return -1;");
			return -1;
		}
		
		if(D.length !=N || C.length!=N){
			U.err("maxCalorie->  D.length !=N || C.length!=N, return -1;");
			return -1;
		}
		int minDistance = getMinDistance(D);
		if(S<minDistance){
			U.err("maxCalorie->  S<minDistance, return -1;");
			return -1;
		}
		
		//不统计余数， 最多跑 maxNum次
		int maxNum = S/minDistance; 
		
		//此数组保存 每一次跑的  阶段跑类型下标（值为: 0~ D.length-1）
		int[] runArray = new int[maxNum];
		Arrays.fill(runArray, -1);
		
		int idx = 0;
		getMaxC(S, idx,runArray, D,C);
		U.info("maxCalorie-> maxC:"+maxC);
		
		if(maxC>0){
			U.info("maxCalorie->maxC:"+maxC+"; maxCIndex:"+U.array2Str(maxCIndex));
			return maxC;
		}else{
			//异常，返回-1
			return -1;
		}
	}
	
	private int maxC = Integer.MIN_VALUE;
	private int[] maxCIndex;
	private int calculateC( int[] runArray,int[] D,int[] C){
		int sum=0;
		for(int i=0;i<runArray.length;i++){
			if(runArray[i]==-1)
				continue;
			sum+= C[runArray[i]];
		}
		return sum;
	}
	
	private void getMaxC(int remainDistance,int idx, int[] runArray,int[] D,int[] C){
		if(idx==runArray.length){
			return;
		}
		
		for(int i=0;i<D.length;i++){
			int distanceItem = D[i];
			if(remainDistance<distanceItem){
				//可用距离不够
				continue;
			}else if(remainDistance == distanceItem){
				//刚好可以消耗， 必须要消耗
				runArray[idx] = i;
				//把 idx+1以后的都重置
				for(int j=idx+1;j<runArray.length;j++){
					runArray[j]=-1;
				}
				int currentSum = calculateC(  runArray, D, C);
				if(maxC<currentSum){
					maxC = currentSum;
					maxCIndex = runArray.clone();
				}
				break;
			}
			runArray[idx] = i;
			getMaxC(remainDistance-distanceItem, idx+1, runArray,D,C);
		}
	}
	
	private int getMinDistance(int[] D){
		int min = Integer.MAX_VALUE;
		for(int i=0;i<D.length;i++){
			if(D[i]<min){
				min  =D[i];
			}
		}
		return min;
	}
}
