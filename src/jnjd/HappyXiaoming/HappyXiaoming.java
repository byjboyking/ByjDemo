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
	 *  求 在总Monkey 不超标的情况下的 总Weight的最大值 
	 *    
	 * @return
	 */
	public int getMaxWeight(int[][] a){
		
		//TODO: 入口参数检查
		
		
		availableMoney = a[0][0];
		availableThings = a[0][1];
		
		//初始化一个二维数组 int[][4], {    {id(>=1),price,importance,weight} ,{...}       }
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
			
			//以一个元素作为起点，递归增加元素，判断是否超出 weight范围。
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
		
		//计算 array 的累积 weight
		int sumWeight=0;
		int sumMoney =0;
		for(int i=0;i<array.length;i++){
			if(array[i][0] ==0){
				//未被初始化
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
			U.err("calcuateMaxWeight->currentMaxWeight<sumWeight, 更新currentMaxWeight， old:"+currentMaxWeight+"; new:"+sumWeight+" array:"+getArrayStr(array));
			currentMaxWeight = sumWeight;	
		}
		
		//递归调用
		
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
	
	// array格式： int[][4]
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
