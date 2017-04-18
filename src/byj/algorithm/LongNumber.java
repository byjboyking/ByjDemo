package byj.algorithm;

import java.util.Arrays;
import java.util.regex.Pattern;

import byj.util.U;

public class LongNumber {

	// num1, num2 都是数字，没有正负号，没有小数点，最前面无0
	//参数异常，返回 null;
	public static String bigNumberMultiple(String num1, String num2) {
		
		if(!isLegalBigNumber(num1) || !isLegalBigNumber(num2)){
			U.err("bigNumberMultiple->!isLegalBigNumber(num1) || !isLegalBigNumber(num2)， return -1;");
			return null;
		}
		if(num1.equals("0") || num2.equals("0")){
			U.err("bigNumberMultiple->num1.equals(0) || num2.equals(0), return 0;");
			return "0";
		}

		String newNum1 = num1;
		String newNum2 = num2;
	
		final boolean isPositive1 = newNum1.charAt(0)!='-';
		final boolean isPositive2 = newNum2.charAt(0)!='-';
		final boolean isPositive = (isPositive1 && isPositive2)
								|| ((!isPositive1) && (!isPositive2));
		
		if(newNum1.charAt(0)=='+' || newNum1.charAt(0)=='-'){
			//去掉正负号
			newNum1  = newNum1.substring(1,newNum1.length());
		}
		if(newNum2.charAt(0)=='+' || newNum2.charAt(0)=='-'){
			//去掉正负号
			newNum2  =newNum2.substring(1,newNum2.length());
		}
		
		int decimalNum1 = 0;
		int decimalNum2 =0;
		int indexDot1 = newNum1.indexOf('.');
		if(indexDot1!=-1){
			// 0  1  2  3  4
		    // a  b  .  e  f
			// a  b  e  f  .
			// .  a  b  c  d
			decimalNum1 = newNum1.length()-1-indexDot1;
			StringBuilder sb = new StringBuilder(newNum1);
			sb.deleteCharAt(indexDot1);
			newNum1 = sb.toString();
		}
		int indexDot2 = newNum2.indexOf('.');
		if(indexDot2!=-1){
			decimalNum2 = newNum2.length()-1-indexDot2;
			StringBuilder sb = new StringBuilder(newNum2);
			sb.deleteCharAt(indexDot2);
			newNum2 = sb.toString();
		}
		
		final int decimalNum=decimalNum1+decimalNum2;
		String rtn= bigIntegerMultiply(newNum1,newNum2);
		rtn = insertDot(rtn,decimalNum);
		rtn = removeEndZeroAfterDot(rtn);
		
		if(!isPositive){
			rtn = "-"+rtn;
		}
		return rtn;
	}
	
	public static String removeEndZeroAfterDot(String input){
		int indexDot = input.indexOf('.');
		if(indexDot == -1){
			return input;
		}
		
		String rtn = input;
		
		//从尾部开始计算
		int noZeroIndex=-1;
		for(int i=input.length()-1;i>=0;i--){
			if(input.charAt(i)=='0'){
				continue;
			}
			
			noZeroIndex = i;
			break;
		}
		
		if(indexDot == noZeroIndex){
			//小数点后全部是0
			if(indexDot==0){
				rtn = "0";
			}else{
				//小数点也去掉
				rtn = rtn.substring(0,indexDot);
			}
		}else{
			rtn = rtn.substring(0,noZeroIndex+1);
		}
		
		return rtn;
		
	}
	
	public static String insertDot(String input, int decimalNum){
		if(decimalNum<=0){
			return input;
		}
		
		String rtn = input;
		if(rtn.length()<=decimalNum){
			//补充0
			int prefixZeroNum = decimalNum-rtn.length()+1;
			StringBuilder sb = new StringBuilder(rtn);
			for(int i=0;i<prefixZeroNum;i++){
				sb.insert(0, '0');
			}
			rtn = sb.toString();
		}
		
		StringBuilder sb1 = new StringBuilder(rtn);
		sb1.insert(rtn.length()-decimalNum, '.');
		rtn = sb1.toString();
		return rtn;
	}
	
	//
	public static boolean isLegalBigNumber(String num){
		if(num==null || num.length()==0 ){
			U.err("isLegalBigNumber->num==null || num.length()==0 , return false;");
			return false;
		}
		
		if(num.length()==1 ){
			if(num.charAt(0)>='0' && num.charAt(0)<='9'){
				return true;
			}else{
				U.err("isLegalBigNumber->! (num.charAt(0)>='0' && num.charAt(0)<='9'), return false");
				return false;
			}
		}
		
		if(num.length()==2 && (num.equals("+.")|| num.equals("-.") )){
			U.err("isLegalBigNumber->num.length()==2 && (num.equals(\"+.\")|| num.equals(\"-.\") ), return false;");
			return false;
		}
		
		// ? 0次或1次 验证过！！
		// * 0次或多次   验证过！！  （0次，1次，n次）
		boolean isMatch = Pattern.matches("[\\+\\-]?[0-9]*[\\.]?[0-9]*", num);
		return isMatch;
	}
	
	public static String bigIntegerMultiply(String int1, String int2){
		int len1 = int1.length();
		int len2 = int2.length();
		int len = len1+len2;
		char[] num1CharArray = new StringBuffer(int1).toString().toCharArray();
		char[] num2CharArray = new StringBuffer(int2).toString().toCharArray();
		int[] result = new int[len];
		
		for(int i=0;i<len1;i++){
			for(int j=0;j<len2;j++){
				//最后一个字符才是个位数
				int e1 = num1CharArray[len1-1-i]-'0';
				int e2 = num2CharArray[len2-1-j]-'0';
				result[i+j] += (e1*e2);
				if(result[i+j]>=10){
					result[i+j+1] += result[i+j]/10;
					result[i+j] = result[i+j]%10;
				}
			}
		}
		
		//此时个位数在最前面
		StringBuffer sb = new StringBuffer();
		for(int i=0;i<result.length;i++){
			sb.append(result[i]);
		}
		
		//反转字符串
		String rtn = sb.reverse().toString();
		
		//删除最前面多余的0
		rtn = processPrefixZero(rtn);
		return rtn;
	}
	
	public static String processPrefixZero(String num){
		String rtn = num;
		int firstNoZeroIndex =-1;
		for(int i=0;i<rtn.length();i++){
			if(rtn.charAt(i) == '0')
				continue;
			
			firstNoZeroIndex = i;
			break;
		}
		
		if(firstNoZeroIndex==-1){
			//全部是0，则保留最后一个0
			if(rtn.length()>1){
				rtn = rtn.substring(rtn.length()-1,rtn.length());
			}
		}else{
			//部分是0
			rtn = rtn.substring(firstNoZeroIndex,rtn.length());
		}
		
		return rtn;
		
	}
}
