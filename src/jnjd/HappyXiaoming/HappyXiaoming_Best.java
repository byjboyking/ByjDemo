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
	 *  求 在总Monkey 不超标的情况下的 总Weight的最大值 
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
	
	//计算从idx 开始，weight权重汇总的最大值
	//例如：如果数组有5行（实际行数为4，第一行为Money数目），
	//idx=2时， 计算的就是 idx={2,3,4} 的weight权重汇总的最大值
	//递归方式调用， 其实是先计算 idx={4},  然后计算 idx={3,4} ... idx={1,2,3,4}
	private int getMaxValue(int remainMoney, int idx, int[][] a){
		if(idx>=a.length){
			return 0;
		}
		counter++;
		if(remainMoney<a[idx][0]){
			//场景1：买不起，查找下一个元素
			return getMaxValue(remainMoney,idx+1,a);
		}
		
		//场景2：买得起，不买，查找下一个元素
		int notBuy = getMaxValue(remainMoney,idx+1,a);
		//场景3：买得起，买，查找下一个元素
		int toBuy = a[idx][0]*a[idx][1]+getMaxValue(remainMoney-a[idx][0],idx+1,a);

		//返回 weight更大的
		return notBuy>toBuy?notBuy:toBuy;
	}
}
