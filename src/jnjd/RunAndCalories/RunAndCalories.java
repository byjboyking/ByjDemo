package jnjd.RunAndCalories;

import java.util.Arrays;
import byj.util.U;

public class RunAndCalories {
	/**
	 * ��N�ֽ׶��ܣ����루��λ�ǹ���ֱ���D1��D2��Dn, ������������λ��ǧ�����ֱ���C1��C2��Dn�� 
	 * ����ĳ��Ҫ��S�������ѡ����ֽ׶λ���ܣ���ÿ�ֽ׶ο����ܶ�Ρ���Ҫ����׶ξ����ܺ�ǡ����S,
	 * ��ѡ��һ���ܷ�ʹ���ۻ������������ۻ���������Ƕ��٣�
	 * ����ע������������֤������һ����ϣ�ʹ�ø��׶ξ����ܺ�ΪS��
	 * @param S  1<=S<=100
	 * @param N  1<=N<=20
	 * @param D
	 * @param C
	 * @return �ۻ������������λ��ǧ�����������쳣����-1
	 */
	public int maxCalorie(int S,int N, int[] D, int[] C){
		//��ڲ����Ϸ��Լ��
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
		
		//��ͳ�������� ����� maxNum��
		int maxNum = S/minDistance; 
		
		//�����鱣�� ÿһ���ܵ�  �׶��������±ֵ꣨Ϊ: 0~ D.length-1��
		int[] runArray = new int[maxNum];
		Arrays.fill(runArray, -1);
		
		int idx = 0;
		getMaxC(S, idx,runArray, D,C);
		U.info("maxCalorie-> maxC:"+maxC);
		
		if(maxC>0){
			U.info("maxCalorie->maxC:"+maxC+"; maxCIndex:"+U.array2Str(maxCIndex));
			return maxC;
		}else{
			//�쳣������-1
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
				//���þ��벻��
				continue;
			}else if(remainDistance == distanceItem){
				//�պÿ������ģ� ����Ҫ����
				runArray[idx] = i;
				//�� idx+1�Ժ�Ķ�����
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
