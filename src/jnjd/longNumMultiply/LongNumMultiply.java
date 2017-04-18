package jnjd.longNumMultiply;

import java.math.BigDecimal;

import byj.algorithm.LongNumber;

/**
 * 请编程实现：两个任意长度的数相乘，请输出结果。
 * 详细要求以及系统约束：
 * 1）、两个数可能是小数、整数、正数、负数；
 * 2）、输入输出均为字符串形式，输入的字符串以“\0”结束，输出的结果字符串也必须以“\0”结束；
 * 3）、输入的字符串不能是空字符串或非法字符串，否则null；
 * 4）、输出的结果字符串需要过滤掉整数位前以及小数位后无效的0，小数位为全0的，直接输出整数位；
 * 例1：相乘结果为11.345，此数值前后均不可以带0，“011.345”或者“0011.34500”等等前后带无效0的均视为错误输出。
 * 例2：080x0.125 结果是“10”
 * 5）、输出的结果如果是正数或0需要过滤掉前面的+号。
 * 例1：相乘结果为+121，则“+121”为错误输出，“121”为正确输出。
 *
 */
public class LongNumMultiply {
	/**
	 * 接口：
	 * 两个任意长度的长数相乘，输出结果
	 * @param multipleA 乘数A
	 * @param multipleB 乘数B
	 * @return 乘法结果；如果数据异常，返回null
	 */ 
	public static String multiply(String multipleA, String multipleB) {
		//return multiplyByBigDecimal(multipleA,multipleB);
		return LongNumber.bigNumberMultiple(multipleA, multipleB);
	}
	
	private static String multiplyByBigDecimal(String multipleA, String multipleB){
		try{
			BigDecimal bd1 = new BigDecimal(multipleA);
			BigDecimal bd2 = new BigDecimal(multipleB);
			BigDecimal resultBd = bd1.multiply(bd2);
			String result = resultBd.toString();
			return convert(result);
		}catch(Exception e){
			return null;
		}
	}
	
	public static String convert(String num){
		String result = num;
		
		//1、移除开头的+
		if(result.charAt(0)=='+'){
			//remove it
			result = result.substring(0+1,result.length());
		}
		
		//2、去掉小数中多余的0（如果小数中只有0，则小数点也去掉）
		int indexDot = result.indexOf(".");
		if(indexDot!=-1){
			int noZeroPos =-1;
			for(int i=result.length()-1;i>=indexDot;i--){
				if(result.charAt(i) == '0'){
					continue;
				}else{
					//非0
					noZeroPos = i;
					break;
				}
			}
			if(noZeroPos!=-1){
				if(indexDot == noZeroPos){
					//"333.000"
					result = result.substring(0, indexDot);
				}else{
					//333.300
					result = result.substring(0,noZeroPos+1);
				}
			}
		}
		
		//3、去掉整数中多余的0
		int noZeroPos1 = -1;
		int zeroCounter = 0;
		for(int i=0;i<result.length();i++){
			if(result.charAt(i)=='0'){
				zeroCounter++;
				continue;
			}else{
				noZeroPos1 = i;
				break;
			}
		}
		if (zeroCounter > 0) {
			if (indexDot == -1) {
				// 没有小数点, 都去掉
				result = result.substring(0 + zeroCounter, result.length());
			} else {
				//有小数点
				if(noZeroPos1==indexDot){
					//0后面跟着小数点，最后一个0保留
					if(zeroCounter>1){
						result = result.substring(0+zeroCounter-1,result.length());
					}
				}else{
					//0后面不跟小数点，0全部去掉
					result = result.substring(0+zeroCounter,result.length());
				}
			}
		}
		
		return result;
	}
	
	
	
	
	
}
