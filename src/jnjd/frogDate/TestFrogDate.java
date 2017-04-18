package jnjd.frogDate;

import junit.framework.TestCase;

public class TestFrogDate extends TestCase {

	@Override
	protected void setUp() throws Exception {
		// TODO Auto-generated method stub
		super.setUp();
	}
	
	public void test001() {
		System.out.println("*********test001-��������************");
		assertEquals("4", FrogDate.judgeMeet(new long[]{1,2,3,4,5}));
	}
	
	public void test002() {
		System.out.println("*********test002-��������************");
		assertEquals("3", FrogDate.judgeMeet(new long[]{18,4,3,1,10}));
	}
	
	//******************�����ķֽ���**********************
	
	public void test003() {
		//pos1,pos2,step1,step2,L
		System.out.println("*********test003-��������-һ�ξ�����************");
		//   0______1_______2_______3_______4_______5
		//        pos2(4)        pos1(2)
		//        
		assertEquals("1", FrogDate.judgeMeet(new long[]{3,1,2,4,5}));
	}
	
	public void test004() {
		//pos1,pos2,step1,step2,L
		System.out.println("*********test004-��������-һ�ξ�����************");
		//   0______1_______2_______3_______4_______5
		//        pos1(1)        pos2(4)
		//        
		assertEquals("1", FrogDate.judgeMeet(new long[]{1,3,1,4,5}));
	}
	
	public void test005() {
		//pos1,pos2,step1,step2,L
		System.out.println("*********test005-��������-��������L************");
		//   0______1_______2_______3_______4_______5
		//        pos1(1)        pos2(9)
		//        
		assertEquals("1", FrogDate.judgeMeet(new long[]{1,3,1,9,5}));
	}
	
	public void test006() {
		//pos1,pos2,step1,step2,L
		System.out.println("*********test006-������ͬ-Impossible************");
		//   0______1_______2_______3_______4_______5
		//        pos1(14)        pos2(9)
		//        
		assertEquals("Impossible", FrogDate.judgeMeet(new long[]{1,3,14,9,5}));
	}
	
	public void test007() {
		//pos1,pos2,step1,step2,L
		System.out.println("*********test007-��������쳣-Impossible************");
		//   0______1_______2_______3_______4_______5
		//        pos1(14)        pos2(9)
		//        
		assertEquals("Impossible", FrogDate.judgeMeet(null));
	}
	
	public void test008() {
		//pos1,pos2,step1,step2,L
		System.out.println("*********test008-��������쳣-Impossible************");
		//   0______1_______2_______3_______4_______5
		//        pos1(14)        pos2(9)
		//        
		assertEquals("Impossible", FrogDate.judgeMeet(new long[]{1,3,9,5}));
	}
	
	public void test009() {
		//pos1,pos2,step1,step2,L
		System.out.println("*********test009-��������쳣-x==y, Impossible************");
		//   0______1_______2_______3_______4_______5
		//        pos1(14)        pos2(9)
		//        
		assertEquals("Impossible", FrogDate.judgeMeet(new long[]{1,1,3,9,5}));
	}
	
	public void test010() {
		//pos1,pos2,step1,step2,L
		System.out.println("*********test010-��������-��n�κ��ֻص������λ��,Impossible************");
		//   0______1_______2_______3_______4_______5________6
		//        pos1(3)                 pos2(1)
		//                                pos1     pos2
		//        pos1                                      pos2
		//        pos2                    pos1
		//        pos1     pos2
		//                         pos2   pos1
		//        pos1                    pos2  (�ص���ʼλ��)
		assertEquals("Impossible", FrogDate.judgeMeet(new long[]{1,4,3,1,6}));
	}

	public void test011() {
		//pos1,pos2,step1,step2,L
		System.out.println("*********test011-��������-L�㹻���ߺܳ�ʱ���׷��************");
		assertEquals("Impossible", FrogDate.judgeMeet(new long[]{1,4,3,1,2000000000}));
	}
	
	public void test012() {
		//pos1,pos2,step1,step2,L
		System.out.println("*********test012-��������-pos==L************");
		assertEquals("Impossible", FrogDate.judgeMeet(new long[]{10,0,3,1,10}));
	}
	
	public void test013() {
		//pos1,pos2,step1,step2,L
		System.out.println("*********test013-��������-************");
		assertEquals("Impossible", FrogDate.judgeMeet(new long[]{1,0,3,1,10}));
	}
	
}
