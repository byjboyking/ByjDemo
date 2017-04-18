package jnjd.mahjong;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import byj.util.U;

public class Mahjong {

	/**
	 * ˵���齫���ǿ��Ǵ���������������������Ѿۻᣬ����ҵ��̸�У������ٲ����齫 ��������
	 * �ɶ��齫ֻ�ܰ���3�����ͣ�����Ͳ���� û�С��š��������������С���
	 * ÿ���ƶ������ִ�1��9��ÿ��������4�ţ���36�š�����Ͳ�����һ����
	 * ���ƹ������£�
	 * 1��������������ֻ���������ͣ��������ȱһ�֣����ܳ��֣�����Ͳ���򶼴��ڵ������
	 * 2��������һ�����ӣ���������ͬ ���ƣ����磺����2Ͳ������4���ȡ�
	 * 3��ʣ����ƣ�ÿ3����Ҫ�ճ�һ����Ч�ƣ����磺3�� һ�����ƣ�3��2Ͳ��������3��˳�ӣ�1��2��3������
	 * ������е��ƶ��ܹ��պã����������2��1����һ�����ӣ��������е���ֻ���������ͣ���ô�Ϳ��Ժ����ˡ�
	 * 4����һ�������ƣ����߶ԣ���ȫ�����ƶ��ǳɶԳ��֣����磺2Ͳ2Ͳ5Ͳ5Ͳ
	 * 3Ͳ3Ͳ4��4��8��8��1��1��2��2����һ��7�ԣ�����������1��Ҳ���Ժ��ơ�
	 * 5�������Ʋ�����������������������ƿ϶���14�š��������ݿ϶������齫�ƣ����ÿ����쳣���룻
	 * Ҳ���ÿ��ǻ����롰�š��������С��ȳɶ��齫�в�����ֵ��ơ�
	 * 
	 * @param cards �����齫���С�
	 * ���磺��1T2T2T2T3T7T8T9T5D4D7D8D8D8D��
	 * ����Ϊ��T������2������Ϊ��2T
	 * Ͳ��Ϊ��D������3Ͳ����Ϊ��3D
	 * ����Ϊ��W������4������Ϊ: 4W
	 * @return ����ܹ����ƣ�����true��������ܺ��ƣ�����false��
	 * ���磺��7T1T8T2T2T2T3T9T5D4D3D8D8D8D�� ����ת����
	 * ��1T2T3T2T2T7T8T9T3D4D5D8D8D8D��,���Ժ��ơ�
	 * 
	 * ע�⣺���ÿ�������ĺϷ��ԡ�����ɺ�����ʹ���߱�֤��
	 * �������Ϊ�ַ�������ĸΪ��д�ġ�TDW��,������ʱ��ע�⡣
	 */
	public boolean isMahjongWin(String cards){
		
		List<Item> tList = new ArrayList<Item>();
		List<Item> dList = new ArrayList<Item>();
		List<Item> wList = new ArrayList<Item>();
		boolean parseRtn =parseCards( cards, tList,dList,wList);
		if(!parseRtn){
			//����ʧ��
			U.err("isMahjongWin->parse failed, return false;");
			return  false;
		}
		
		// ��С��������
		Collections.sort(tList);
		Collections.sort(dList);
		Collections.sort(wList);
		 
		//save id list
		List<Twins> twinsList = new ArrayList<Twins>();
		 
		
		//����ܹ��м�����ͬ����
		calcuateTwins( tList,  twinsList);
		calcuateTwins( dList,  twinsList);
		calcuateTwins( wList,  twinsList);
		
		
		//�ж��Ƿ�Ϊ�߶�
		if(twinsList.size() == TWINS_7 ){
			U.info("isMahjongWin->7�ԣ�����true");
			return true;
		}
		
		if(twinsList.size() == 0 ){
			U.err("isMahjongWin->1�Զ�û�У�����false");
			return false;
		}
		
		for(int i=0;i<twinsList.size();i++){
			Twins twins = twinsList.get(i);
			
			
			if(isWin(twins,tList,dList,wList)){
				return true;
			}
		}
		
		
		
		U.err("isMahjongWin->û���ҵ� ����isWin������������false");
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
			//�ų�twins
			if(item.id != twins.id1 && item.id != twins.id2){
				newList.add(item.clone());
			}
		}
		
		//������ܱ�3������ ���ܺ���
		if(newList.size()%3 != 0 ){
			U.err("isWin->���ܱ�3������ ���ܺ���, return false;");
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
							U.err("isWin->isJump true, ����֮������Ծ�� ���ܺ���, return false;");
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
								U.err("isWin->�Ҳ���ƥ���˳�ӣ� ���ܺ���, return false;");
								return false;
							}else{
								continue;
							}
						}
						
						U.err("isWin->�����쳣���������ܺ��ƣ�return false;");
						return false;
						
					}
				}
				
				
			}
			
		}
		
		
		//��� newList �Ƿ��Ѿ������
		boolean isAllMark = isAllMark(newList);
		
		if(!isAllMark){
			U.err("isWin-> isAllMark==false, ���ܺ��ƣ�return false ");
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
	
	//3������֮������Ծ
	//���磺1,3,4    1,2,4
	private boolean isJump(List<Item> group){
		if(group.get(1).n-group.get(0).n >1 ||
				group.get(2).n-group.get(1).n >1	){
			return true;
		}else{
			return false;
		}
	}
	
	//3�������Ƿ�����
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
				U.err("parseCards->NumberFormatException ��set parseRtn to false; e:"+e);
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
		
		public boolean isMarkTwins = false; // �Ƿ���Ϊһ��
		
		
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
