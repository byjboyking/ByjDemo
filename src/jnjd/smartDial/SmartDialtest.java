package jnjd.smartDial;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import junit.framework.TestCase;

public class SmartDialtest extends TestCase {

	private SmartDial smartDial = new SmartDial();
	private Contact contact1 = new Contact("Brian", "Yang", "13951905919"); 
	private Contact contact2 = new Contact("Jiali", "Chen", "13851958680"); 
	
	@Override
	protected void setUp() throws Exception {
	
		smartDial.addContact(contact1);
		smartDial.addContact(contact2);
	}
	
	
//	public void test001(){
//	
//		
//		ArrayList matchContacts = smartDial.findContacts("519");
//		Collections.sort(matchContacts,new ContactComparator());
//		
//		ArrayList expectContacts = new ArrayList();
//		expectContacts.add(contact1);
//		expectContacts.add(contact2);
//		Collections.sort(expectContacts,new ContactComparator());
//		
//		if(expectContacts.size() != matchContacts.size() ){
//			fail();
//		}
//		
//		for(int i=0;i<expectContacts.size();i++){
//			if(!expectContacts.get(i).equals(matchContacts.get(i))){
//				fail();
//			}
//		}
//	}	
//	
//	
//	
//	
//	public void test002(){
//		ArrayList matchContacts = smartDial.findContacts("926");
//		Collections.sort(matchContacts,new ContactComparator());
//		
//		ArrayList expectContacts = new ArrayList();
//		expectContacts.add(contact1);
//		Collections.sort(expectContacts,new ContactComparator());
//		if(expectContacts.size() != matchContacts.size() ){
//			fail();
//		}
//		for(int i=0;i<expectContacts.size();i++){
//			if(!expectContacts.get(i).equals(matchContacts.get(i))){
//				fail();
//			}
//		}
//	}
//	
//	public void test003(){
//		ArrayList matchContacts = smartDial.findContacts("52");
//		Collections.sort(matchContacts,new ContactComparator());
//		
//		ArrayList expectContacts = new ArrayList();
//		expectContacts.add(contact2);
//		Collections.sort(expectContacts,new ContactComparator());
//		if(expectContacts.size() != matchContacts.size() ){
//			fail();
//		}
//		for(int i=0;i<expectContacts.size();i++){
//			if(!expectContacts.get(i).equals(matchContacts.get(i))){
//				fail();
//			}
//		}
//	}
//	
//	
//	public void test004(){
//		System.out.println("*********test004-Á½¸öÊ××ÖÄ¸ËÑË÷*************");
//		ArrayList matchContacts = smartDial.findContacts("92");
//		Collections.sort(matchContacts,new ContactComparator());
//		
//		ArrayList expectContacts = new ArrayList();
//		expectContacts.add(contact1);
//		Collections.sort(expectContacts,new ContactComparator());
//		if(expectContacts.size() != matchContacts.size() ){
//			fail();
//		}
//		for(int i=0;i<expectContacts.size();i++){
//			if(!expectContacts.get(i).equals(matchContacts.get(i))){
//				fail();
//			}
//		}
//	}
//	
//	public void test005() {
//		System.out.println("*********test005-3¸ö×ÖÄ¸ËÑË÷*************");
//		ArrayList matchContacts = smartDial.findContacts("264");
//		Collections.sort(matchContacts, new ContactComparator());
//
//		ArrayList expectContacts = new ArrayList();
//		expectContacts.add(contact1);
//		Collections.sort(expectContacts, new ContactComparator());
//		if (expectContacts.size() != matchContacts.size()) {
//			fail();
//		}
//		for (int i = 0; i < expectContacts.size(); i++) {
//			if (!expectContacts.get(i).equals(matchContacts.get(i))) {
//				fail();
//			}
//		}
//	}
//	
//	public void test006() {
//		System.out.println("*********test006-3¸ö×ÖÄ¸ËÑË÷,  IAL 425*************");
//		ArrayList matchContacts = smartDial.findContacts("425");
//		Collections.sort(matchContacts, new ContactComparator());
//
//		ArrayList expectContacts = new ArrayList();
//		expectContacts.add(contact2);
//		Collections.sort(expectContacts, new ContactComparator());
//		if (expectContacts.size() != matchContacts.size()) {
//			fail();
//		}
//		for (int i = 0; i < expectContacts.size(); i++) {
//			if (!expectContacts.get(i).equals(matchContacts.get(i))) {
//				fail();
//			}
//		}
//	}
//	
//	
//	public void test007() {
//		System.out.println("*********test007-3¸ö×ÖÄ¸ËÑË÷,  HEN    436*************");
//		ArrayList matchContacts = smartDial.findContacts("436");
//		Collections.sort(matchContacts, new ContactComparator());
//
//		ArrayList expectContacts = new ArrayList();
//		expectContacts.add(contact2);
//		Collections.sort(expectContacts, new ContactComparator());
//		if (expectContacts.size() != matchContacts.size()) {
//			fail();
//		}
//		for (int i = 0; i < expectContacts.size(); i++) {
//			if (!expectContacts.get(i).equals(matchContacts.get(i))) {
//				fail();
//			}
//		}
//	}
//	
//	
//	//
//	public void test008() {
//		System.out.println("*********test008-3¸ö×ÖÄ¸ËÑË÷,  RIA   742*************");
//		ArrayList matchContacts = smartDial.findContacts("742");
//		Collections.sort(matchContacts, new ContactComparator());
//
//		ArrayList expectContacts = new ArrayList();
//		expectContacts.add(contact1);
//		Collections.sort(expectContacts, new ContactComparator());
//		if (expectContacts.size() != matchContacts.size()) {
//			fail();
//		}
//		for (int i = 0; i < expectContacts.size(); i++) {
//			if (!expectContacts.get(i).equals(matchContacts.get(i))) {
//				fail();
//			}
//		}
//	}
//	
//	
//	///
//	public void test009() {
//		System.out.println("*********test009-3¸ö×ÖÄ¸ËÑË÷, RIN   746*************");
//		ArrayList matchContacts = smartDial.findContacts("746");
//		Collections.sort(matchContacts, new ContactComparator());
//		System.out.println("matchContacts size:"+matchContacts.size());
//
//		ArrayList expectContacts = new ArrayList();
//		//expectContacts.add(contact1);
//		Collections.sort(expectContacts, new ContactComparator());
//		if (expectContacts.size() != matchContacts.size()) {
//			fail();
//		}
//		for (int i = 0; i < expectContacts.size(); i++) {
//			if (!expectContacts.get(i).equals(matchContacts.get(i))) {
//				fail();
//			}
//		}
//	}
	
	
	public void test010() {
	System.out.println("*********test010-3¸ö×ÖÄ¸ËÑË÷,  RIA   742*************");
	ArrayList matchContacts = smartDial.findContacts("742");
	Collections.sort(matchContacts, new ContactComparator());

	ArrayList expectContacts = new ArrayList();
	expectContacts.add(contact1);
	Collections.sort(expectContacts, new ContactComparator());
	if (expectContacts.size() != matchContacts.size()) {
		fail();
	}
	for (int i = 0; i < expectContacts.size(); i++) {
		if (!expectContacts.get(i).equals(matchContacts.get(i))) {
			fail();
		}
	}
}
	
	private class ContactComparator implements Comparator{

		@Override
		public int compare(Object o1, Object o2) {
			String c1 = ((Contact)o1).getFirstName()+"+"+
					((Contact)o1).getLastName()+"+"+
					((Contact)o1).getNumber();
			String c2 = ((Contact)o2).getFirstName()+"+"+
					((Contact)o2).getLastName()+"+"+
					((Contact)o2).getNumber();
			
			return c1.compareTo(c2);
		}
		
		
		
	}
	
}
