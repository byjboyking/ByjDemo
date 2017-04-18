package jnjd.longNumMultiply;

import java.math.BigDecimal;

import byj.algorithm.LongNumber;

/**
 * ����ʵ�֣��������ⳤ�ȵ�����ˣ�����������
 * ��ϸҪ���Լ�ϵͳԼ����
 * 1����������������С����������������������
 * 2�������������Ϊ�ַ�����ʽ��������ַ����ԡ�\0������������Ľ���ַ���Ҳ�����ԡ�\0��������
 * 3����������ַ��������ǿ��ַ�����Ƿ��ַ���������null��
 * 4��������Ľ���ַ�����Ҫ���˵�����λǰ�Լ�С��λ����Ч��0��С��λΪȫ0�ģ�ֱ���������λ��
 * ��1����˽��Ϊ11.345������ֵǰ��������Դ�0����011.345�����ߡ�0011.34500���ȵ�ǰ�����Ч0�ľ���Ϊ���������
 * ��2��080x0.125 ����ǡ�10��
 * 5��������Ľ�������������0��Ҫ���˵�ǰ���+�š�
 * ��1����˽��Ϊ+121����+121��Ϊ�����������121��Ϊ��ȷ�����
 *
 */
public class LongNumMultiply {
	/**
	 * �ӿڣ�
	 * �������ⳤ�ȵĳ�����ˣ�������
	 * @param multipleA ����A
	 * @param multipleB ����B
	 * @return �˷��������������쳣������null
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
		
		//1���Ƴ���ͷ��+
		if(result.charAt(0)=='+'){
			//remove it
			result = result.substring(0+1,result.length());
		}
		
		//2��ȥ��С���ж����0�����С����ֻ��0����С����Ҳȥ����
		int indexDot = result.indexOf(".");
		if(indexDot!=-1){
			int noZeroPos =-1;
			for(int i=result.length()-1;i>=indexDot;i--){
				if(result.charAt(i) == '0'){
					continue;
				}else{
					//��0
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
		
		//3��ȥ�������ж����0
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
				// û��С����, ��ȥ��
				result = result.substring(0 + zeroCounter, result.length());
			} else {
				//��С����
				if(noZeroPos1==indexDot){
					//0�������С���㣬���һ��0����
					if(zeroCounter>1){
						result = result.substring(0+zeroCounter-1,result.length());
					}
				}else{
					//0���治��С���㣬0ȫ��ȥ��
					result = result.substring(0+zeroCounter,result.length());
				}
			}
		}
		
		return result;
	}
	
	
	
	
	
}
