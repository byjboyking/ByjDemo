package jnjd.RunAndCalories;

import static org.junit.Assert.*;

import org.junit.Test;

public class TestRunAndCalories {

//	@Test
//	public void test01() {
//		System.out.println("******test01-��������");
//		int S=3;
//		int N=2;
//		int[] D = {1,2};
//		int[] C = {3,2};
//		RunAndCalories instance = new RunAndCalories();
//		assertEquals(9, instance.maxCalorie(S, N, D, C));
//	}
//
//	@Test
//	public void test02() {
//		System.out.println("******test02-��������");
//		int S=3;
//		int N=2;
//		int[] D = {1,3};
//		int[] C = {3,10};
//		RunAndCalories instance = new RunAndCalories();
//		assertEquals(10, instance.maxCalorie(S, N, D, C));
//	}
//	
//	@Test
//	public void test03() {
//		System.out.println("******test03-�����쳣-S �� ��С��D ��ҪС");
//		int S=2;
//		int N=2;
//		int[] D = {4,3};
//		int[] C = {3,10};
//		RunAndCalories instance = new RunAndCalories();
//		assertEquals(-1, instance.maxCalorie(S, N, D, C));
//	}
//	
//	@Test
//	public void test04() {
//		System.out.println("******test04-�����쳣-S ����������ʹ��");
//		int S=5;
//		int N=2;
//		int[] D = {4,3};
//		int[] C = {3,10};
//		RunAndCalories instance = new RunAndCalories();
//		assertEquals(-1, instance.maxCalorie(S, N, D, C));
//	}
//	
//	@Test
//	public void test05() {
//		System.out.println("******test05-��������-�������ʽʹ��");
//		int S=5;
//		int N=2;
//		int[] D = {2,3};
//		int[] C = {3,10};
//		RunAndCalories instance = new RunAndCalories();
//		assertEquals(13, instance.maxCalorie(S, N, D, C));
//	}
	
//	@Test
//	public void test06() {
//		System.out.println("******test06-��������-�������ʽʹ��,ÿ����������");
//		int S=10;
//		int N=2;
//		int[] D = {2,3};
//		int[] C = {3,10};
//		RunAndCalories instance = new RunAndCalories();
//		assertEquals(26, instance.maxCalorie(S, N, D, C));
//	}
	
//	@Test
//	public void test07() {
//		System.out.println("******test07-��������-�������ʽʹ��,ÿ����������");
//		int S=13;
//		int N=2;
//		int[] D = {2,3};
//		int[] C = {3,10};
//		RunAndCalories instance = new RunAndCalories();
//		assertEquals(36, instance.maxCalorie(S, N, D, C));
//	}
//	
//	
//	@Test
//	public void test08() {
//		System.out.println("******test08-��ڲ������");
//		int S=13;
//		int N=2;
//		int[] D = null;
//		int[] C = {3,10};
//		RunAndCalories instance = new RunAndCalories();
//		assertEquals(-1, instance.maxCalorie(S, N, D, C));
//	}
//	
//	@Test
//	public void test09() {
//		System.out.println("******test09-��ڲ������");
//		int S=13;
//		int N=0;
//		int[] D ={2,3};
//		int[] C = {3,10};
//		RunAndCalories instance = new RunAndCalories();
//		assertEquals(-1, instance.maxCalorie(S, N, D, C));
//	}
//	
//	@Test
//	public void test10() {
//		System.out.println("******test10-��ڲ������");
//		int S=13;
//		int N=21;
//		int[] D ={2,3};
//		int[] C = {3,10};
//		RunAndCalories instance = new RunAndCalories();
//		assertEquals(-1, instance.maxCalorie(S, N, D, C));
//	}
//	
//	
//	@Test
//	public void test11() {
//		System.out.println("******test11-��ڲ������");
//		int S=0;
//		int N=2;
//		int[] D ={2,3};
//		int[] C = {3,10};
//		RunAndCalories instance = new RunAndCalories();
//		assertEquals(-1, instance.maxCalorie(S, N, D, C));
//	}
//	
//	@Test
//	public void test12() {
//		System.out.println("******test12-��ڲ������");
//		int S=101;
//		int N=2;
//		int[] D ={2,3};
//		int[] C = {3,10};
//		RunAndCalories instance = new RunAndCalories();
//		assertEquals(-1, instance.maxCalorie(S, N, D, C));
//	}
//	
//	
//	@Test
//	public void test13() {
//		System.out.println("******test13-��ڲ������");
//		int S=15;
//		int N=3;
//		int[] D ={2,3};
//		int[] C = {3,10};
//		RunAndCalories instance = new RunAndCalories();
//		assertEquals(-1, instance.maxCalorie(S, N, D, C));
//	}
//	
//
//	@Test
//	public void test14() {
//		System.out.println("******test14-ѹ������");
//		int S=10;
//		int N=20;
//		int[] D ={1,2,3,4,5,6,7,8,9,10,11,12,13,14,15,16,17,18,19,20};
//		int[] C = {1,2,3,4,5,6,7,8,9,13,11,12,13,14,15,16,17,18,19,20};
//		RunAndCalories instance = new RunAndCalories();
//		assertEquals(13, instance.maxCalorie(S, N, D, C));
//	}
//	
//	@Test
//	public void test15() {
//		System.out.println("******test15-ѹ������");
//		int S=10;
//		int N=20;
//		int[] D ={1,2,3,4,5,6,7,8,9,10,11,12,13,14,15,16,17,18,19,20};
//		int[] C = {1,3,3,4,5,6,7,8,9,13,11,12,13,14,15,16,17,18,19,20};
//		RunAndCalories instance = new RunAndCalories();
//		assertEquals(15, instance.maxCalorie(S, N, D, C));
//	}
	
	@Test
	public void test16() {
		System.out.println("******test16-ѹ������");
		int S=100;
		int N=5;
		int[] D ={11,22,33,4,5};
		int[] C = {11,32,33,4,5};
		RunAndCalories instance = new RunAndCalories();
		assertEquals(140, instance.maxCalorie(S, N, D, C));
	}
	
}
