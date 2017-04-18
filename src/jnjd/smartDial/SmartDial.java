package jnjd.smartDial;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import byj.util.U;

/**
 * 本类实现手机数字键盘智能查找联系人的能力。
 * 标准的手机数字键盘中2-9键分别对应26个字母。
 * 通过输入联系人姓或者名的前几个字母，联系人电话号码的部分联系数字，
 * 联系人姓和名的首字母来匹配联系人记录。
 * 用户输入数字串（长度必须大于等于2）按如下规则匹配联系人记录：
 * 1）	联系人号码匹配：输入联系人号码所包含的任意一段数字可匹配该联系人。
 * 比如某个联系人的号码是13951905919，用户可以输入号码的任意一段匹配，
 * 如输入 “139”, “951”,”5919”等。
 * 2）	联系人姓或名匹配：输入联系人姓或名的英文单词的首字母（对应的数字键）
 * 可匹配该联系人。比如某个联系人姓名为 “Brian Yang”, 用户可以输入9(wxyz) 2(abc)
 * 或者2(abc)7(pqrs)4(ghi)来匹配该联系人。
 * 3）	联系人姓和名的首字母匹配：输入联系人姓和名的英文单词首字母可匹配该联系人，
 * 且姓和名的顺序可以颠倒。比如某个联系人姓名为”Brian Yang”, 
 * 用户可以输入2(abc)9(wxyz) 或者 9(wxyz)2(abc) 来匹配该联系人。
 * 
 * 涉及知识点：排序、集合、算法
 *
 */
public class SmartDial {
	
	private Map<Integer, CharacterList> map = new HashMap<Integer, CharacterList>();
	
	public SmartDial() {
		
		map.put(Integer.valueOf(2), new CharacterList(new String[]{"A","B","C"}));
		map.put(Integer.valueOf(3), new CharacterList(new String[]{"D","E","F"}));
		map.put(Integer.valueOf(4), new CharacterList(new String[]{"G","H","I"}));
		map.put(Integer.valueOf(5), new CharacterList(new String[]{"J","K","L"}));
		map.put(Integer.valueOf(6), new CharacterList(new String[]{"M","N","O"}));
		map.put(Integer.valueOf(7), new CharacterList(new String[]{"P","Q","R","S"}));
		map.put(Integer.valueOf(8), new CharacterList(new String[]{"T","U","V"}));
		map.put(Integer.valueOf(9), new CharacterList(new String[]{"W","X","Y","Z"}));
		
//		private Contact contact1 = new Contact("Brian", "Yang", "13951905919"); 
//		private Contact contact2 = new Contact("Jiali", "Chen", "13851958680");
		//ang   264 ok
		//ial   425 OK
		//HEN    436  OK
		//RIA   742 OK
		//RIN   746  ok(找不到)
	}

	/*用户保存的所有联系人集合*/
	ArrayList contacts = new ArrayList();

	/**
	 * 添加联系人
	 * @param firstName 名，只包含英文26个字母的大写或小写且值不为空
	 * @param lastName 姓，只包含英文26个字母的大写或小写且值不为空
	 * @param number 号码，只包含数字且值不为空
	 */
	public void addContact(String firstName, String lastName, String number){
		addContact(new Contact( firstName,  lastName,  number));
	}
	
	/**
	 * 添加联系人
	 * @param c
	 */
	public void addContact(Contact c){
		contacts.add(c);
	}
	
	/**
	 * 返回符合条件的联系人集合。 
	 * @param digitalSequence 数字串， 长度大于等于2，可以代表部分号码序列，姓或者名的首个字母，长度等于2时还可以代表姓名的首字母
	 * @return 符合条件的联系人 集合（集合中的元素类型为 Contact）
	 * digitalSequence的长度 小于2返回null，没有符合 条件 的记录返回空ArrayList
	 */
	public ArrayList findContacts(String digitalSequence){
		
		//TODO: 输入字符串合法性检查！
		if(digitalSequence.length()<2){
			return null;
		}
		
		ArrayList matchContacts  = new ArrayList();
		matchContacts=find(digitalSequence);
		return matchContacts;
	}
	
	private ArrayList find(String digitalSequence){
		
		boolean isOnlySearchNumber = digitalSequence.contains("1");
		
		boolean isSearchCapitalChar = digitalSequence.length()==2;
		
		ArrayList matchContacts  = new ArrayList();
		
		for(int i=0;i<contacts.size();i++){
			Contact c = (Contact) contacts.get(i);
			
			if(c.getNumber().contains(digitalSequence)){
				matchContacts.add(c);
				continue;
			}
			
			if(isOnlySearchNumber){
				continue;
			}
			
			if(isSearchCapitalChar){
				String firstNameChar = c.getFirstName().substring(0, 1);
				String lastNameChar = c.getLastName().substring(0, 1);
				if(findCapitalChar(firstNameChar,  lastNameChar,  digitalSequence)){
					matchContacts.add(c);
					continue;
				}
			}
			
			if(findName(c.getFirstName(),c.getLastName(),digitalSequence)){
				matchContacts.add(c);
				continue;
			}
		}
		return matchContacts;
	}
	
	private boolean findName(String firstName,String lastName, String digitalSequence){
		String str1 = firstName.toUpperCase();
		String str2 = lastName.toUpperCase();
		List<CharacterList> list = new ArrayList<CharacterList>();
		for(int i=0;i<digitalSequence.length();i++){
			String item = String.valueOf(digitalSequence.charAt(i));
			try {
				Integer i1 = Integer.valueOf(item);
				
				CharacterList c = map.get(i1);
				if(c==null){
					U.err("findName->c==null, ");
					continue;
				}
				
				list.add(c);
			} catch (NumberFormatException e) {
				U.err("findName->parse int fail, ");
				continue;
			}
		}
		
		if(list.size()==0){
			U.err("findName->list.size()==0, ");
			return false;
		}
		
		int[] indexArray = new int[list.size()];
		Arrays.fill(indexArray, -1);
		String[] strArray =new String[list.size()];
		
		return findName(str1,str2, list,indexArray,strArray,0);
	}
	
	private boolean findName(String firstName,String lastName,List<CharacterList> list,
			int[] indexArray,String[] strArray,int order){
		
		if(order == list.size()){
			return false;
		}
		
		CharacterList c = list.get(order);
		for(int i=0;i<c.list.size();i++){
			
			String item = c.list.get(i);
			
			indexArray[order] = i;
			strArray[order] = item;
			
			if(order == list.size()-1){
				
				StringBuilder sb =new StringBuilder();
				for(int j=0;j<strArray.length;j++){
					sb.append(strArray[j]);
				}
				
				if(firstName.contains(sb.toString()) || lastName.contains(sb.toString())){
					U.info("findName-> find name. ");
					return true;
				}
			}
			
			boolean isFind = findName( firstName, lastName, list,
					 indexArray, strArray, order+1);
			
			if(isFind){
				return true;
			}
		}
		
		return false;
	}
	
	// digitalSequence.length ==2
	private boolean findCapitalChar(String firstNameChar, String lastNameChar, String digitalSequence){
		String str1 = firstNameChar.toUpperCase();
		String str2 = lastNameChar.toUpperCase();
		
		Integer i1 = Integer.valueOf(String.valueOf(digitalSequence.charAt(0)));
		Integer i2 = Integer.valueOf(String.valueOf(digitalSequence.charAt(1)));
		
		CharacterList c1 = map.get(i1);
		CharacterList c2 = map.get(i2);
		
		if((c1.contains(str1) && c2.contains(str2))  ||
				(c1.contains(str2) && c2.contains(str1))	){
			return true;
		}
		
		return false;
	}

	public static class CharacterList{
		public List<String> list = new ArrayList<String>();
		
		public CharacterList(String[] arr) {
			for(int i=0;i<arr.length;i++){
				list.add(arr[i]);
			}
		}
		
		public boolean contains(String str){
			return list.contains(str);
		}
		
	}
	
}
