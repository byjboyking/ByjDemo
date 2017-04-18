package jnjd.wordReverse;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import byj.util.U;

/**
 * 
 * ���ʵ���
 * ���ַ����е����е��ʽ��е��š�
 * ˵����ÿ����������26����д��СдӢ����ĸ���ɣ���-����Ϊ�������ӷ�ʹ��ʱ��
 * ��Ϊ���ʵ�һ���֣�����һ��������ֻ������һ�Σ���������2����-������ʱ
 * ��Ϊ�ָ������硱aa-bb���еġ�-����Ϊ�ָ����2������Ϊ��aa���͡�bb����
 * �ǹ��ɵ��ʵ��ַ�����Ϊ���ʼ������
 * Ҫ���ź�ĵ��ʼ�����Կո��ʾ��ԭ�ַ��������ڵ��ʼ��ж�������ʱ��
 * ����ת����ֻ�������һ���ո�������ÿ�������20����ĸ��
 * 
 * ʾ����
 * ����ԭʼ������䣺 I am a �C student
 * ���ź�ĵ�����䣺studuent a am I
 * 
 */
public class WordReverse {

	/**
	 * ���ܣ����ַ����е����е��ʽ��е��š�
	 * @param pInput �����ĵ��ʴ�
	 * @param pOutput ���ź�ĵ��ʴ�
	 * @return �ɹ�0��ʧ��-1
	 */
	public int converse(String pInput,StringBuffer pOutput){
		if(pInput==null){
			return -1;
		}
		
		//����ĸ�и�  �� �и�2��������  ��Ϊ�ָ���
		String[] arr = pInput.split("([^a-zA-Z\\-])|([\\-]{2,})");
		List<String> list = new ArrayList<String>();
		
		if(arr ==null || arr.length==0){
			return -1;
		}
		
		for(int i=0;i<arr.length;i++){
			String item = arr[i];
			if(item.isEmpty()){
				continue;
			}
			if(item.length()>20){
				U.err("converse->item.length()>20, return -1;");
				return -1;
			}
			if(item.equals("-")){
				continue;
			}
			list.add(item);
		}
		
		if(list.size()==0){
			return -1;
		}
		
		Collections.reverse(list);
		for(int i=0;i<list.size();i++){
			if(i!=0){
				pOutput.append(" ");
			}
			pOutput.append(list.get(i));
		}
		return 0;
	}
}
