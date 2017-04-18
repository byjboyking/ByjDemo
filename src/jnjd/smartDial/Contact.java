package jnjd.smartDial;


//2011033技能鉴定实战考试JAVA 三级第二题


/**
 * 联系人实体类
 *
 */
public class Contact {
	
	/*名*/
	private String firstName;
	
	/*姓*/
	private String lastName;
	
	/*号码*/
	private String number;
	

	/**
	 * 构造函数
	 * @param firstName 名，只包含英文26个字母的大写或小写且值不为空
	 * @param lastName 姓，只包含英文26个字母的大写或小写且值不为空
	 * @param number 号码，只包含数字且值不为空
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
