package jnjd.HappyXiaoming;

import byj.util.U;

public class HappyXiaoming {
	/**
	 * 
	 * @param a
	 * {   
	 * {availableMoney,availableThings},
	 * {item1_Price, item1_Importance},
	 * {item2_Price, item2_Importance},
	 * ...
	 *    }
	 *    
	 *  0<availableMoney<3000
	 *  0<availableThings<25
	 *  Weight = Price*Importance
	 *  �� ����Monkey �����������µ� ��Weight�����ֵ 
	 *    
	 * @return
	 */
	public int getMaxWeight(int[][] a){
		
		//TODO: ��ڲ������
		
		
		availableMoney = a[0][0];
		availableThings = a[0][1];
		
		//��ʼ��һ����ά���� int[][4], {    {id(>=1),price,importance,weight} ,{...}       }
		things = new int[availableThings][4];
		for(int i=1;i<a.length;i++){
			things[i-1][0] = i; // id
			things[i-1][1] = a[i][0];  // price
			things[i-1][2] = a[i][1];  // importance
			things[i-1][3] = a[i][0]*a[i][1];  // weight (price*importance) 
		}
		
		
		for(int i=0;i<things.length;i++){
			int[][] tempThings = new int[availableThings][4];
			tempThings[0] =  things[i].clone();
			
			//��һ��Ԫ����Ϊ��㣬�ݹ�����Ԫ�أ��ж��Ƿ񳬳� weight��Χ��
			calcuateMaxWeight(1, tempThings);
			
			System.out.println(i+" ;cloneCounter:"+cloneCounter);
		}
		
		return currentMaxWeight;
	}
	
	

	private int cloneCounter=0;
	
	private void calcuateMaxWeight(int step, int[][] array){
		
		if(step>availableThings){
			U.info("calcuateMaxWeight-> step>availableThings,  return directly.  step:"+step+"; availableThings:"+availableThings);
			return;
		}
		
		//���� array ���ۻ� weight
		int sumWeight=0;
		int sumMoney =0;
		for(int i=0;i<array.length;i++){
			if(array[i][0] ==0){
				//δ����ʼ��
				break;
			}
			
			sumMoney+= array[i][1];
			sumWeight+= array[i][3];
			
			if(sumMoney>availableMoney){
				U.info("calcuateMaxWeight->sumMoney>availableMoney, return directly. sumMoney:"+sumMoney+";availableMoney:"+availableMoney); 
				return;
			}
			if(sumWeight>MAX_WEIGHT){
				U.info("calcuateMaxWeight->sumWeight>MAX_WEIGHT, return directly. sumWeight:"+sumWeight+";MAX_WEIGHT:"+MAX_WEIGHT); 
				return;
			}
		}
		
		if(currentMaxWeight<sumWeight){
			U.err("calcuateMaxWeight->currentMaxWeight<sumWeight, ����currentMaxWeight�� old:"+currentMaxWeight+"; new:"+sumWeight+" array:"+getArrayStr(array));
			currentMaxWeight = sumWeight;	
		}
		
		//�ݹ����
		
		for(int i=0;i<things.length;i++){
			int id=  things[i][0];
			
			if(isExist(id,array)){
				continue;
			}
			
			 int[][] newArray = array.clone();
			 newArray[step] = things[i].clone();
			 
			 cloneCounter++;
			 
			calcuateMaxWeight(step+1, newArray);
		}
		
		
	}
	
	
	private boolean isExist(int id, int[][] array){
		for(int i=0;i<array.length;i++){
			int itemId = array[i][0];
			if(itemId ==0){
				return false;
			}
			if(itemId == id){
				return true;
			}
		}
		return false;
	}
	
	private int availableMoney;
	private int availableThings;
	private int[][] things;
	
	private int currentMaxWeight=0;
	
	private final static int MAX_WEIGHT = 100000000;
	
	// array��ʽ�� int[][4]
	private String getArrayStr(int[][] array){
		StringBuilder sb=new StringBuilder();
		sb.append(" { ");
		
		for(int i=0;i<array.length;i++){
			int[] item = array[i];
			if(item[0] == 0){
				break;
			}
			
			sb.append(" { ");
			sb.append(item[0]+" "+item[1]+" "+item[2]+" "+item[3]);
			sb.append(" } ");
		}
		
		
		sb.append(" } ");
		return		sb.toString();
	}
}
