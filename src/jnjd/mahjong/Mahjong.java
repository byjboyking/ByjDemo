package jnjd.mahjong;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import byj.util.U;

public class Mahjong {

	/**
	 * 说起麻将，那可是川渝市民的最爱，无论亲朋好友聚会，还是业务谈判，总是少不了麻将 的声音。
	 * 成都麻将只能包括3种类型：条、筒、万。 没有“门、东南西北、红中”。
	 * 每种牌都是数字从1到9，每个数字有4张，共36张。条、筒、万均一样。
	 * 胡牌规则如下：
	 * 1、手里面的牌最多只有两种类型，即必须打缺一种，不能出现：条、筒、万都存在的情况。
	 * 2、必须有一个对子，即两张相同 的牌，比如：两个2筒，两个4条等。
	 * 3、剩余的牌，每3张需要凑成一个有效牌，比如：3个 一样的牌（3个2筒）、或者3个顺子（1条2条3条），
	 * 如果所有的牌都能够凑好，再满足规则2和1，有一个对子，并且所有的牌只有两种类型，那么就可以胡牌了。
	 * 4、有一种特殊牌，叫七对，即全部的牌都是成对出现，比如：2筒2筒5筒5筒
	 * 3筒3筒4条4条8条8条1条1条2条2条，一共7对，再满足条件1，也可以胡牌。
	 * 5、假设牌不会出现碰的情况，即手里的牌肯定是14张。输入数据肯定都是麻将牌，不用考虑异常输入；
	 * 也不用考虑会输入“门”、“红中”等成都麻将中不会出现的牌。
	 * 
	 * @param cards 输入麻将序列。
	 * 比如：”1T2T2T2T3T7T8T9T5D4D7D8D8D8D”
	 * 条子为：T。比如2条输入为：2T
	 * 筒子为：D。比如3筒输入为：3D
	 * 万子为：W。比如4万输入为: 4W
	 * @return 如果能够胡牌，返回true；如果不能胡牌，返回false。
	 * 比如：”7T1T8T2T2T2T3T9T5D4D3D8D8D8D” 可以转换成
	 * “1T2T3T2T2T7T8T9T3D4D5D8D8D8D”,可以胡牌。
	 * 
	 * 注意：不用考虑输入的合法性。这个由函数的使用者保证。
	 * 输入的牌为字符串，字母为大写的”TDW”,请编码的时候注意。
	 */
	public boolean isMahjongWin(String cards){
		
		List<Item> tList = new ArrayList<Item>();
		List<Item> dList = new ArrayList<Item>();
		List<Item> wList = new ArrayList<Item>();
		boolean parseRtn =parseCards( cards, tList,dList,wList);
		if(!parseRtn){
			//解析失败
			U.err("isMahjongWin->parse failed, return false;");
			return  false;
		}
		
		// 从小到大排序：
		Collections.sort(tList);
		Collections.sort(dList);
		Collections.sort(wList);
		 
		//save id list
		List<Twins> twinsList = new ArrayList<Twins>();
		 
		
		//检查总共有几对相同的牌
		calcuateTwins( tList,  twinsList);
		calcuateTwins( dList,  twinsList);
		calcuateTwins( wList,  twinsList);
		
		
		//判断是否为七对
		if(twinsList.size() == TWINS_7 ){
			U.info("isMahjongWin->7对，返回true");
			return true;
		}
		
		if(twinsList.size() == 0 ){
			U.err("isMahjongWin->1对都没有，返回false");
			return false;
		}
		
		for(int i=0;i<twinsList.size();i++){
			Twins twins = twinsList.get(i);
			
			
			if(isWin(twins,tList,dList,wList)){
				return true;
			}
		}
		
		
		
		U.err("isMahjongWin->没有找到 符合isWin的条件，返回false");
		return false;
	}
	
	
	private boolean isWin(Twins twins, List<Item> tList,List<Item> dList,List<Item> wList){
		
		
		return isWin(twins,tList) && isWin(twins,dList) && isWin(twins,wList);
	}
	
	private boolean isWin(Twins twins, List<Item> list){
		boolean rtn=false;
		
		if(list.size()==0){
			return true;
		}
		
		List<Item> newList = new ArrayList<Item>();
		for(int i=0;i<list.size();i++){
			Item item  = list.get(i);
			//排除twins
			if(item.id != twins.id1 && item.id != twins.id2){
				newList.add(item.clone());
			}
		}
		
		//如果不能被3整除， 则不能胡牌
		if(newList.size()%3 != 0 ){
			U.err("isWin->不能被3整除， 则不能胡牌, return false;");
			return false;
		}
		
		
		List<Item> group = new ArrayList<Item>();
		for(int i=0;i<newList.size();i++){
			if(group.size()<3){
				Item item = newList.get(i);
				if(!item.isMark){
					group.add(item);
					if(group.size()==3){
						//1,3,4,  1,1,3
						if(isJump(group)){
							U.err("isWin->isJump true, 数字之间有跳跃， 则不能胡牌, return false;");
							return false;
						}
						
						//1,2,3     1,1,1
						if(isLianxu(group) || isAllSame(group)){
							setMark( group, true);
							group.clear();
							continue;
						}
						
						
						//1,1,2   1,2,2
						if(isTwosame(group)){
							if(!findMatchItemAndProcess(group, newList)){
								U.err("isWin->找不到匹配的顺子， 不能胡牌, return false;");
								return false;
							}else{
								continue;
							}
						}
						
						U.err("isWin->走入异常场景，不能胡牌，return false;");
						return false;
						
					}
				}
				
				
			}
			
		}
		
		
		//检查 newList 是否都已经被标记
		boolean isAllMark = isAllMark(newList);
		
		if(!isAllMark){
			U.err("isWin-> isAllMark==false, 不能胡牌，return false ");
		}
		
		return isAllMark;
		
	}
	
	private boolean isAllMark(List<Item> list){
		for(int i=0;i<list.size();i++){
			if(!list.get(i).isMark){
				return false;
			}
		}
		
		return true;
		
	}
	
	private boolean findMatchItemAndProcess(List<Item> group,List<Item> newList){
		boolean  isFind = false;
		//1,1,2   1,2,2
		//we need find 3
		int targetN = group.get(2).n+1;
		
		for(int i=0;i<newList.size();i++){
			Item item = newList.get(i);
			if(!item.isMark && item.n==targetN){
				isFind = true;
				
				item.isMark=true;
				//process first and last item in group
				group.get(group.size()-1).isMark = true;
				group.get(0).isMark = true;
				group.remove(group.size()-1);
				group.remove(0);
				
				break;
			}
		}
		
		return isFind;
	}
	
	private boolean isTwosame(List<Item> group){
		if(group.get(1).n-group.get(0).n ==0 ||
				group.get(2).n-group.get(1).n ==0	){
			return true;
		}else{
			return false;
		}
	}
	
	private boolean  isAllSame(List<Item> group){
		if(group.get(1).n-group.get(0).n ==0 &&
				group.get(2).n-group.get(1).n ==0	){
			return true;
		}else{
			return false;
		}
	}
	
	//3个数字之间有跳跃
	//例如：1,3,4    1,2,4
	private boolean isJump(List<Item> group){
		if(group.get(1).n-group.get(0).n >1 ||
				group.get(2).n-group.get(1).n >1	){
			return true;
		}else{
			return false;
		}
	}
	
	//3个数字是否连续
	private boolean isLianxu(List<Item> group){
		if(group.get(1).n-group.get(0).n ==1 &&
				group.get(2).n-group.get(1).n ==1	){
			return true;
		}else{
			return false;
		}
	}
	
	private void setMark(List<Item> group, boolean flag){
		for(int i=0;i<group.size();i++){
			group.get(i).isMark = flag;
		}
	}
	
	public static class Twins{
		public char type;
		public int n;
		public int id1;
		public int id2;
		
		public Twins(int n, char type, int id1,int id2) {
			this.n=n;
			this.type=type;
			this.id1=id1;
			this.id2=id2;
		}
		@Override
		public String toString() {
			return "Twins n:"+n+" ;type:"+type+" id1:"+id1+" id2:"+id2;
		}
	}
	
	
	private void calcuateTwins(List<Item> sortList, List<Twins> twinsList){
		for(int i=0;i<sortList.size()-1;i++){
			Item item1 = sortList.get(i);
			Item item2 = sortList.get(i+1);
			if(!item1.isMarkTwins && !item2.isMarkTwins && item1.n==item2.n){
				item1.isMarkTwins = true;
				item2.isMarkTwins = true;
				twinsList.add(new Twins(item1.n, item1.type, item1.id, item2.id));
			}
		}
		
		//reset isMarkTwins
		for(int i=0;i<sortList.size();i++){
			Item item1 = sortList.get(i);
			if(item1.isMarkTwins){
				item1.isMarkTwins=false;
			}
		}
	}
	
	private final static char T = 'T';
	private final static char D = 'D';
	private final static char W = 'W';
	private final static int TWINS_7 = 7;
	
	private boolean parseCards(String cards, List<Item> tList,List<Item> dList,List<Item> wList){
		boolean parseRtn = true;
		int length = cards.length()/2;
		
		for(int i=0;i<length;i++){
			char c1 = cards.charAt(i*2);
			char c2 = cards.charAt(i*2+1);
			try {
				int n = Integer.parseInt(String.valueOf(c1));
				
				int id = i+1;
				if(c2 == T){
					tList.add(new Item(n,c2,id));
				}else if(c2 == D){
					dList.add(new Item(n,c2,id));
				}else if(c2 == W){
					wList.add(new Item(n,c2,id));
				}
			} catch (NumberFormatException e) {
				U.err("parseCards->NumberFormatException ，set parseRtn to false; e:"+e);
				parseRtn = false;
				break;
			}
		}
		
		
		if(tList.size()>0 && dList.size()>0 && wList.size()>0){
			U.err("parseCards->tList.size()>0 && dList.size()>0 && wList.size()>0, set parseRtn to false;");
			parseRtn = false;
		}
		
		return parseRtn;
	}
	
	public static class Item implements Comparable{
		public int n;
		public char type;
		
		public int id;
		
		public boolean isMarkTwins = false; // 是否标记为一对
		
		
		public boolean isMark = false;
		
		public Item(int n, char type,int id) {
			this.n =n;
			this.type=type;
			this.id=id;
		}
		
		@Override
		public String toString() {
			return "Item n:"+n+" type:"+type;
		}
		
		public int compareTo(Object o) {
			Item s = (Item) o;
			// 1. min -> max
			return n > s.n ? 1 : (n == s.n ? 0 : -1);
		}
		
		public Item clone(){
			return new Item(this.n, this.type,this.id);
		}
	}
	
}
