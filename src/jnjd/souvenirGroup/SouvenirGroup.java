package jnjd.souvenirGroup;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.Arrays;

import byj.util.U;

/**
 * Ԫ���쵽�ˣ�Уѧ���������ָ����������ļ���Ʒ���Ź����� Ϊʹ�òμ�����ͯЬ����õļ���Ʒ��ֵ��Ծ��⣬��Ҫ��
 * �����ļ���Ʒ���ݼ۸���з��飬��ÿ�����ֻ�ܰ�����������Ʒ�� ����ÿ�����Ʒ�ļ۸�֮�Ͳ��ܳ���һ��������������
 * Ϊ�˱�֤�ھ����̵�ʱ���ڷ������м���Ʒ������ϣ���������Ŀ���١�
 * 
 * ���������дһ�������ҳ����з��鷽���з��������ٵ�һ�֣�������ٵķ�����Ŀ��
 * 
 */
public class SouvenirGroup {

	/**
	 * 
	 * @param input
	 *            �������ŵ�һ������Ϊÿ�����Ʒ�۸�֮�͵����ޣ� �ڶ�������Ϊ��������Ʒ������Ŀ�����µ���Ϊÿ������Ʒ�ļ۸�
	 *            ��β������жϣ��������Ա�֤��
	 * @return ���ٵķ�����Ŀ
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
	 * ���ļ��ж�ȡ����ת�������顣
	 * 
	 * @param inputFile
	 *            ������ֵ��ļ������ּ��Զ��ŷָ�ļ��п����пո�ͻ��У����һ�����ֺ���û�ж��š�
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
			//�����ǽ������ֲ��Ϸ������
			
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
