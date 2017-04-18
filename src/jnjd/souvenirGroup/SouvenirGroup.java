package jnjd.souvenirGroup;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.Arrays;

import byj.util.U;

/**
 * 元旦快到了，校学生会让乐乐负责新年晚会的纪念品发放工作。 为使得参加晚会的童鞋所获得的纪念品价值相对均衡，他要把
 * 购来的纪念品根据价格进行分组，但每组最多只能包括两件纪念品。 并且每组纪念品的价格之和不能超过一个给定的整数。
 * 为了保证在尽量短的时间内发完所有纪念品，乐乐希望分组的数目最少。
 * 
 * 你的任务是写一个程序，找出所有分组方案中分组数最少的一种，输出最少的分组数目。
 * 
 */
public class SouvenirGroup {

	/**
	 * 
	 * @param input
	 *            该数组存放的一个数据为每组纪念品价格之和的上限， 第二个数据为购来纪念品的总数目。余下的数为每个纪念品的价格。
	 *            入参不用做判断，用例可以保证。
	 * @return 最少的分组数目
	 */
	public int getResult(int[] input) {
		if(input==null || input.length<3){
			return 0;
		}
	
		final int maxPrize = input[0];
		final int num = input[1];
		int[] prizeArray = Arrays.copyOfRange(input, 2, input.length);
		if(num != prizeArray.length){
			return 0;
		}
		
		for(int i=0;i<prizeArray.length;i++){
			if(prizeArray[i] >maxPrize){
				return 0;
			}
		}
		
		U.info("before sort prizeArray:"+Arrays.toString(prizeArray));
		Arrays.sort(prizeArray);
		U.info("after sort prizeArray:"+Arrays.toString(prizeArray));
		
		int groupNum  =0;
		for(int maxIndex=prizeArray.length-1,minIndex =0;maxIndex>=minIndex; ){
			if(maxIndex!=minIndex && prizeArray[maxIndex]+prizeArray[minIndex]<=maxPrize){
				minIndex++;
			}
			maxIndex--;
			groupNum++;
		}
		
		return groupNum;
	}

	/**
	 * 从文件中读取数据转换成数组。
	 * 
	 * @param inputFile
	 *            存放数字的文件，数字间以逗号分割，文件中可能有空格和换行，最后一个数字后面没有逗号。
	 * @return
	 */
	public int[] read(File inputFile) {
		
		StringBuilder sb = new StringBuilder();
		BufferedReader reader = null;
		try {
			reader = new BufferedReader(new FileReader(inputFile));
			String tempString = null;
			while ((tempString = reader.readLine()) != null) {
				sb.append(tempString);
			}
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			if (reader != null) {
				try {
					reader.close();
				} catch (IOException e1) {
					e1.printStackTrace();
				}
			}
		}
		
		String[] arr = sb.toString().trim().split(",");
		if(arr==null ||  arr.length<3){
			return null;
		}
		
		int[] rtnArray = new int[arr.length];
		for(int i=0;i<arr.length;i++){
			//不考虑解析数字不合法的情况
			
			try {
				rtnArray[i] =  Integer.parseInt(arr[i].trim());
			} catch (NumberFormatException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				return null;
			}
		}
		
		U.info("rtnArray:"+Arrays.toString(rtnArray));
		
		return rtnArray;
	}

}
