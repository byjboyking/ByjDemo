package jnjd.smartDial;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import byj.util.U;

/**
 * ����ʵ���ֻ����ּ������ܲ�����ϵ�˵�������
 * ��׼���ֻ����ּ�����2-9���ֱ��Ӧ26����ĸ��
 * ͨ��������ϵ���ջ�������ǰ������ĸ����ϵ�˵绰����Ĳ�����ϵ���֣�
 * ��ϵ���պ���������ĸ��ƥ����ϵ�˼�¼��
 * �û��������ִ������ȱ�����ڵ���2�������¹���ƥ����ϵ�˼�¼��
 * 1��	��ϵ�˺���ƥ�䣺������ϵ�˺���������������һ�����ֿ�ƥ�����ϵ�ˡ�
 * ����ĳ����ϵ�˵ĺ�����13951905919���û�����������������һ��ƥ�䣬
 * ������ ��139��, ��951��,��5919���ȡ�
 * 2��	��ϵ���ջ���ƥ�䣺������ϵ���ջ�����Ӣ�ĵ��ʵ�����ĸ����Ӧ�����ּ���
 * ��ƥ�����ϵ�ˡ�����ĳ����ϵ������Ϊ ��Brian Yang��, �û���������9(wxyz) 2(abc)
 * ����2(abc)7(pqrs)4(ghi)��ƥ�����ϵ�ˡ�
 * 3��	��ϵ���պ���������ĸƥ�䣺������ϵ���պ�����Ӣ�ĵ�������ĸ��ƥ�����ϵ�ˣ�
 * ���պ�����˳����Եߵ�������ĳ����ϵ������Ϊ��Brian Yang��, 
 * �û���������2(abc)9(wxyz) ���� 9(wxyz)2(abc) ��ƥ�����ϵ�ˡ�
 * 
 * �漰֪ʶ�㣺���򡢼��ϡ��㷨
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
		//RIN   746  ok(�Ҳ���)
	}

	/*�û������������ϵ�˼���*/
	ArrayList contacts = new ArrayList();

	/**
	 * �����ϵ��
	 * @param firstName ����ֻ����Ӣ��26����ĸ�Ĵ�д��Сд��ֵ��Ϊ��
	 * @param lastName �գ�ֻ����Ӣ��26����ĸ�Ĵ�д��Сд��ֵ��Ϊ��
	 * @param number ���룬ֻ����������ֵ��Ϊ��
	 */
	public void addContact(String firstName, String lastName, String number){
		addContact(new Contact( firstName,  lastName,  number));
	}
	
	/**
	 * �����ϵ��
	 * @param c
	 */
	public void addContact(Contact c){
		contacts.add(c);
	}
	
	/**
	 * ���ط�����������ϵ�˼��ϡ� 
	 * @param digitalSequence ���ִ��� ���ȴ��ڵ���2�����Դ����ֺ������У��ջ��������׸���ĸ�����ȵ���2ʱ�����Դ�������������ĸ
	 * @return ������������ϵ�� ���ϣ������е�Ԫ������Ϊ Contact��
	 * digitalSequence�ĳ��� С��2����null��û�з��� ���� �ļ�¼���ؿ�ArrayList
	 */
	public ArrayList findContacts(String digitalSequence){
		
		//TODO: �����ַ����Ϸ��Լ�飡
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
