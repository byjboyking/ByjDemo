package jnjd.smartDial;


//2011033���ܼ���ʵս����JAVA �����ڶ���


/**
 * ��ϵ��ʵ����
 *
 */
public class Contact {
	
	/*��*/
	private String firstName;
	
	/*��*/
	private String lastName;
	
	/*����*/
	private String number;
	

	/**
	 * ���캯��
	 * @param firstName ����ֻ����Ӣ��26����ĸ�Ĵ�д��Сд��ֵ��Ϊ��
	 * @param lastName �գ�ֻ����Ӣ��26����ĸ�Ĵ�д��Сд��ֵ��Ϊ��
	 * @param number ���룬ֻ����������ֵ��Ϊ��
	 */
	public Contact(String firstName, String lastName, String number) {
		this.firstName = firstName;
		this.lastName = lastName;
		this.number = number;
	}
	
	public String getLastName(){
		return this.lastName;
	}
	
	public void setLastName(String lastName){
		this.lastName = lastName;
	}
	
	public String getFirstName(){
		return this.firstName;
	}
	
	public void setFirstName(String firstName){
		this.firstName =firstName;
	}
	
	public String getNumber(){
		return  this.number;
	}
	
	public void setNumber(String number){
		this.number = number;
	}
	
	@Override
	public boolean equals(Object o) {
		if(this == o){
			return true;
		}
		
		if(o==null || !(o instanceof Contact)){
			return false;
		}
		
		Contact c = (Contact)o;
		if(this.getFirstName().equals(c.getFirstName()) &&
				this.getLastName().equals(c.getLastName()) &&
				this.getNumber().equals(c.getNumber()) ){
			return true;
		}else{
			return false;
		}
	}
}
