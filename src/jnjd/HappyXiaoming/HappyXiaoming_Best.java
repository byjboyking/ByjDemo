package jnjd.HappyXiaoming;

public class HappyXiaoming_Best {
	/**
	 * 
	 * @param a
	 * {   
	 * {availableMoney,availableThings},
	 * {item1_Price, item1_Importance},
	 * {item2_Price, item2_Importance},
	 * ...
	 *    }
	 *    availableThings = a.length-1
	 *  0<availableMoney<3000
	 *  0<availableThings<25
	 *  Weight = Price*Importance
	 *  �� ����Monkey �����������µ� ��Weight�����ֵ 
	 *    
	 * @return
	 */
	public int getMaxWeight(int[][] a){
		int reMny = a[0][0];
		int idx=1;
		if(a.length<=1){
			return 0;
		}
		
		int maxValue= getMaxValue(reMny,idx,a);
		System.out.println("getMaxWeight->counter:"+counter);
		return maxValue;
	}
	
	int counter=0;
	
	//�����idx ��ʼ��weightȨ�ػ��ܵ����ֵ
	//���磺���������5�У�ʵ������Ϊ4����һ��ΪMoney��Ŀ����
	//idx=2ʱ�� ����ľ��� idx={2,3,4} ��weightȨ�ػ��ܵ����ֵ
	//�ݹ鷽ʽ���ã� ��ʵ���ȼ��� idx={4},  Ȼ����� idx={3,4} ... idx={1,2,3,4}
	private int getMaxValue(int remainMoney, int idx, int[][] a){
		if(idx>=a.length){
			return 0;
		}
		counter++;
		if(remainMoney<a[idx][0]){
			//����1�����𣬲�����һ��Ԫ��
			return getMaxValue(remainMoney,idx+1,a);
		}
		
		//����2������𣬲��򣬲�����һ��Ԫ��
		int notBuy = getMaxValue(remainMoney,idx+1,a);
		//����3��������򣬲�����һ��Ԫ��
		int toBuy = a[idx][0]*a[idx][1]+getMaxValue(remainMoney-a[idx][0],idx+1,a);

		//���� weight�����
		return notBuy>toBuy?notBuy:toBuy;
	}
}
