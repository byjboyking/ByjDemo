package jnjd.antMoving;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import byj.util.U;

/**
 * ��������
 * ��һ��27���׵�ϸľ�ˣ��ڵ�3���ס�7���ס�11���ס�17���ס�23������5��λ���ϸ���һֻ���ϡ�ľ�˺�ϸ������ͬʱͨ����ֻ���ϡ�
 * ��ʼʱ�����ϵ�ͷ�����ǳ���������ģ�����ֻ�ᳯǰ�߻��ǵ�ͷ����������ˡ���������ֻ������ͷʱ����ֻ���ϻ�ͬʱ��ͷ���෴�����ߡ�
 * ����������ÿ���ӿ�����һ���׵ľ��롣
 * ���������뿪ľ�˵�ƽ��ʱ��Ϊ���������뿪ľ������ʱ���ƽ��ֵ��
 * ��д��������������⣺
 * �������϶��뿪ľ�˵����ƽ��ʱ����ƽ��ʱ�䣻
 * �����������뿪ľ������ƽ��ʱ���������£���iֻ�����߳�ľ��������ʱ���Ƕ��٣�������û��������ľ�����м䣩
 * ����֪ʶ�㣺
 * 1�������㷨
 * 2�������㷨
 * 3����ѧ��ģ
 */
public class AntMoving {
	private final static int IS_ON = 1;
	private final static int IS_OFF = -1;
	private final static int DIRECTION_LEFT = -1;
	private final static int DIRECTION_RIGHT = 1;
	private static double minAverageTime=Double.MAX_VALUE;
	private static double maxAverageTime = Double.MIN_VALUE;
	private static int[][] minArr;
	private static int[][] maxArr;
	
	/**
	 * 
	 * @param length ϸľ�˵ĳ��ȣ�����
	 * @param pos ϸľ�������ϵ�λ��
	 * @param speed ������������������ٶ�
	 * @return ���� new double[]{minAverage,maxAverage}�� �����ƽ��ʱ�䡢�ƽ��ʱ��
	 * ��ע�����ÿ������벻�Ϸ������
	 */
	public static double[] calcAvergeTime(double length, double [] pos, double speed){
		double[] rtnArray =calcAvergeTimeEx(length,  pos,  speed);
		resetVars();
		return rtnArray;
	}

	/**
	 * 
	 * @param length ϸľ�˵ĳ��ȣ�����
	 * @param pos ϸľ�������ϵ�λ��
	 * @param speed ������������������ٶ�
	 * @param antIndex ��antIndex������
	 * @return ���ص�antIndex���������ƽ��ʱ�������£��뿪ľ�������ʱ��
	 * ��ע�����ÿ������벻�Ϸ������
	 */
	public static double leaveTime(double length,double[] pos, double speed, int antIndex){
		calcAvergeTimeEx(length,  pos,  speed);
		double leaveTime =maxArr[antIndex][3];
		resetVars();
		return leaveTime;
	}
	
	private static void resetVars() {
		// reset
		minAverageTime = Double.MAX_VALUE;
		maxAverageTime = Double.MIN_VALUE;
		minArr = null;
		maxArr = null;
	}
	
	private static double[] calcAvergeTimeEx(double length, double[] pos,
			double speed) {
		//��speed=1 ����ģ�ͣ� ���Ľ������ϵ������

		int antNum = pos.length;
		int[][] antArray = new int[antNum][4];
		for (int i = 0; i < pos.length; i++) {
			//ÿһ�����ϼ�¼��4������,���£�
			antArray[i][0] = (int) (pos[i]);  	//λ�� pos >0 && pos<length������͵���ȥ
			antArray[i][1] = 0; 				//���������� (-1������ 1������)
			antArray[i][2] = 1; 				//�Ƿ���ϸľ���� (-1������ϸľ���ϣ�1������ϸľ����)
			antArray[i][3] = 0; 				//����ʱ��
		}

		int index = 0;
		int iLength = (int) length;
		calcAntArray(antArray, index, new int[] { DIRECTION_LEFT,
				DIRECTION_RIGHT }, iLength);

		double[] rtnArray = new double[2];

		rtnArray[0] = minAverageTime/speed;
		rtnArray[1] = maxAverageTime/speed;

		U.err("calcAvergeTime->minArr:" + U.array2Str(minArr));
		U.err("calcAvergeTime->maxArr:" + U.array2Str(maxArr));
		return rtnArray;
	}
	
	private static  void  calcAntArray(int[][] antArray,int index,int[] directList,int length){
		if(index>=antArray.length){
			//U.err("fillAntArray->index>=antArray.length�� return");
			return;
		}
		
		for(int i=0;i<directList.length;i++){
			antArray[index][1] = directList[i];
			if(index == antArray.length-1){
				//System.out.println("fillAntArray->"+U.array2Str(antArray));
				int[][] cloneArray = U.clone2DimensionalArray(antArray);
				double averageTime = calculateAverageTime(cloneArray,length);
				if(minAverageTime>averageTime){
					minAverageTime = averageTime;
					minArr = U.clone2DimensionalArray(antArray);
					//����time
					for(int j=0;j<minArr.length;j++){
						minArr[j][3] = cloneArray[j][3];
					}
				}
				if(maxAverageTime<averageTime){
					maxAverageTime=averageTime;
					maxArr = U.clone2DimensionalArray(antArray);
					//����time
					for(int j=0;j<maxArr.length;j++){
						maxArr[j][3] = cloneArray[j][3];
					}
				}
			}
			
			calcAntArray(antArray,index+1,directList,length );
		}
	}
	
	private static double calculateAverageTime(int[][] array,int length){
		int time = 0;
		long maxTime = length*array.length;
		
		while(true){
			time++;
			for(int i=0;i<array.length;i++){
				int[] item = array[i];
				if(item[2] == IS_OFF){
					continue;
				}
				//�ƶ����ϵ�λ��
				item[0] += item[1];
				if(item[0]<=0 || item[0]>=length){
					//�����Ѿ�����ϸľ��
					item[2] = IS_OFF;
					item[3] = time;
				}
			}
			
			//����pos ��ͬ��Ԫ�أ��ı䷽��
			processDirection(array);
			
			if(isAllOff(array)){
				double avgTime = calAverageTime(array);
				//U.info("calculateTotalTime->isAllOff(antArray), return; maxtime:"+time+"; avgTime:"+avgTime);
				return avgTime;
			}
			
			if(time>=maxTime){
				//�����˳�ѭ������
				U.err("calculateTotalTime->time>=maxTime, return; time:"+time+"; maxTime:"+maxTime);
				return 0.0;
			}
		}
	}
	
	private static double calAverageTime(int[][] antArray){
		double averageTime=0.0;
		
		double sum =0;
		for(int i=0;i<antArray.length;i++){
			int[] item = antArray[i];
			sum+= item[3];	
		}
		
		averageTime = sum/antArray.length;
		return averageTime;
	}
	
	private static boolean isAllOff(int[][] antArray){
		for(int i=0;i<antArray.length;i++){
			int[] item = antArray[i];
			if(item[2] == IS_ON){
				return false;
			}
		}
		return true;
	}
	
	private static void processDirection(int[][] antArray){
		//key : pos;  value: index list which have same pos
		Map<Integer, ArrayList<Integer>> map  = new HashMap<Integer,  ArrayList<Integer>>();
		
		for(int i=0;i<antArray.length;i++){
			int[] item = antArray[i];
			if(item[2] == IS_OFF){
				continue;
			}
			
			int pos = item[0];
			ArrayList<Integer> list = map.get(pos);
			if(list == null){
				list = new ArrayList<Integer>();
			}
			
			list.add(i);
			map.put(pos, list);
		}
		
		for (int key : map.keySet()) {
			ArrayList<Integer> list  = map.get(key);
			if(list.size()>1){
				for(int element: list){
					//����ת��
					antArray[element][1] = (antArray[element][1]==DIRECTION_LEFT)?DIRECTION_RIGHT:DIRECTION_LEFT;
				}
			}
		}
	}
}
