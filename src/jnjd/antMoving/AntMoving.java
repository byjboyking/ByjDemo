package jnjd.antMoving;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import byj.util.U;

/**
 * 蚂蚁爬杠
 * 有一个27厘米的细木杆，在第3厘米、7厘米、11厘米、17厘米、23厘米这5个位置上各有一只蚂蚁。木杆很细，不能同时通过两只蚂蚁。
 * 开始时，蚂蚁的头朝左还是朝右是任意的，他们只会朝前走或是调头，但不会后退。当任意两只蚂蚁碰头时，两只蚂蚁会同时调头朝相反方向走。
 * 假如蚂蚁们每秒钟可以走一厘米的距离。
 * 所有蚂蚁离开木杆的平均时间为所有蚂蚁离开木杆所需时间的平均值。
 * 编写程序，求解如下问题：
 * 所有蚂蚁都离开木杆的最短平均时间和最长平均时间；
 * 在所有蚂蚁离开木杆所需平均时间最长的情况下，第i只蚂蚁走出木杆所花的时间是多少；（假设没有蚂蚁在木杆正中间）
 * 考察知识点：
 * 1、排序算法
 * 2、查找算法
 * 3、数学建模
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
	 * @param length 细木杆的长度：厘米
	 * @param pos 细木杆上蚂蚁的位置
	 * @param speed 蚂蚁在麻杆上爬动的速度
	 * @return 返回 new double[]{minAverage,maxAverage}。 即最短平均时间、最长平均时间
	 * 备注：不用考虑输入不合法的情况
	 */
	public static double[] calcAvergeTime(double length, double [] pos, double speed){
		double[] rtnArray =calcAvergeTimeEx(length,  pos,  speed);
		resetVars();
		return rtnArray;
	}

	/**
	 * 
	 * @param length 细木杆的长度：厘米
	 * @param pos 细木杆上蚂蚁的位置
	 * @param speed 蚂蚁在麻杆上爬动的速度
	 * @param antIndex 第antIndex个蚂蚁
	 * @return 返回第antIndex个蚂蚁在最长平均时间条件下，离开木杆所需的时间
	 * 备注：不用考虑输入不合法的情况
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
		//以speed=1 建立模型， 最后的结果乘以系数即可

		int antNum = pos.length;
		int[][] antArray = new int[antNum][4];
		for (int i = 0; i < pos.length; i++) {
			//每一个蚂蚁记录其4个数据,如下：
			antArray[i][0] = (int) (pos[i]);  	//位置 pos >0 && pos<length，否则就掉下去
			antArray[i][1] = 0; 				//标记左或者右 (-1代表左， 1代表右)
			antArray[i][2] = 1; 				//是否在细木杆上 (-1代表不在细木杆上，1代表在细木杆上)
			antArray[i][3] = 0; 				//爬行时间
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
			//U.err("fillAntArray->index>=antArray.length， return");
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
					//保存time
					for(int j=0;j<minArr.length;j++){
						minArr[j][3] = cloneArray[j][3];
					}
				}
				if(maxAverageTime<averageTime){
					maxAverageTime=averageTime;
					maxArr = U.clone2DimensionalArray(antArray);
					//保存time
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
				//移动蚂蚁的位置
				item[0] += item[1];
				if(item[0]<=0 || item[0]>=length){
					//蚂蚁已经掉下细木杆
					item[2] = IS_OFF;
					item[3] = time;
				}
			}
			
			//查找pos 相同的元素，改变方向
			processDirection(array);
			
			if(isAllOff(array)){
				double avgTime = calAverageTime(array);
				//U.info("calculateTotalTime->isAllOff(antArray), return; maxtime:"+time+"; avgTime:"+avgTime);
				return avgTime;
			}
			
			if(time>=maxTime){
				//设置退出循环条件
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
					//蚂蚁转向
					antArray[element][1] = (antArray[element][1]==DIRECTION_LEFT)?DIRECTION_RIGHT:DIRECTION_LEFT;
				}
			}
		}
	}
}
