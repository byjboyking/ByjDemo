package jnjd.mahjong;

import static org.junit.Assert.*;

import org.junit.Test;

public class TestMahjong {

//	@Test
//	public void test01() {
//		System.out.println("******test01-��������");
//		Mahjong instance = new Mahjong();
//		assertEquals(true, instance.isMahjongWin("7T1T8T2T2T2T3T9T5D4D3D8D8D8D"));
//	}
//	
//	@Test
//	public void test02() {
//		System.out.println("******test02-�쳣��3�����Ͷ�����");
//		Mahjong instance = new Mahjong();
//		assertEquals(false, instance.isMahjongWin("7T1T8T2T2T2T3T2W5D4D3D8D8D8D"));
//	}
//	
//	@Test
//	public void test03() {
//		System.out.println("******test03-7��");
//		Mahjong instance = new Mahjong();
//		assertEquals(true, instance.isMahjongWin("7T7T7T7T2T2T3T3T5D5D3D3D8D8D"));
//	}
//	
//	@Test
//	public void test04() {
//		System.out.println("******test04-�쳣��1�Զ�û��");
//		Mahjong instance = new Mahjong();
//		assertEquals(false, instance.isMahjongWin("1T2T3T4T5T6T7T8T1D2D3D4D5D6D"));
//	}
//	
//	@Test
//	public void test05() {
//		System.out.println("******test05-124�� ������");
//		Mahjong instance = new Mahjong();
//		assertEquals(false, instance.isMahjongWin("1T2T4T5T5T6T7T8T1D2D3D4D5D6D"));
//	}
//
//	@Test
//	public void test06() {
//		System.out.println("******test06-���ܱ�3����");
//		Mahjong instance = new Mahjong();
//		assertEquals(false, instance.isMahjongWin("1T2T3T5T5T6T7T1D2D3D4D5D6D7D"));
//	}
//	
//	@Test
//	public void test07() {
//		System.out.println("******test07-ȫ����");
//		Mahjong instance = new Mahjong();
//		assertEquals(true, instance.isMahjongWin("1T2T3T5T5T6T7T8T1D2D3D4D5D6D"));
//	}
	
	@Test
	public void test08() {
		System.out.println("******test08-�쳣");
		Mahjong instance = new Mahjong();
		assertEquals(false, instance.isMahjongWin("1T1T5T5T5T8T8T8T2D2D2D5D5D6D"));
	}
	
	@Test
	public void test09() {
		System.out.println("******test09-����Ԫ");
		Mahjong instance = new Mahjong();
		assertEquals(true, instance.isMahjongWin("1T1T5T5T5T8T8T8T2D2D2D5D5D5D"));
	}
	
	@Test
	public void test10() {
		System.out.println("******test10-����Ԫ");
		Mahjong instance = new Mahjong();
		assertEquals(true, instance.isMahjongWin("5T5T5T1T1T8T8T8T2D2D2D5D5D5D"));
	}
	
	@Test
	public void test11() {
		System.out.println("******test11-�쳣");
		Mahjong instance = new Mahjong();
		assertEquals(false, instance.isMahjongWin("1T1T3T4T4T8T8T8T2D2D2D5D5D5D"));
	}
}
