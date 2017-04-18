package jnjd.souvenirGroup;

import java.io.File;

import junit.framework.TestCase;

import org.junit.Assert;

public class TestSouvenirGroup extends TestCase {

//	private final static String TEST_PATH = System.getProperty("user.dir")
//			+ File.separator + "src" + File.separator + "testcase"
//			+ File.separator;
	//java.io.FileNotFoundException: E:\jason.bai.new\mycode\mycode\Java\ByjDemo\ByjDemo
	// \src\testcase\testcase_01.txt (系统找不到指定的路径。)
	
	private final static String TEST_PATH = System.getProperty("user.dir")
			+ File.separator + "src" + File.separator + "jnjd\\souvenirGroup"
			+ File.separator;

	private SouvenirGroup demo;

	protected void setUp() throws Exception {
		demo = new SouvenirGroup();
	}

	public void testcase01() {
		File intputFile = new File(TEST_PATH + "testcase_01.txt");
		int[] input = demo.read(intputFile);
		Assert.assertEquals(6, demo.getResult(input));
	}

	public void testcase02() {
		File intputFile = new File(TEST_PATH + "testcase_02.txt");
		int[] input = demo.read(intputFile);
		Assert.assertEquals(9, demo.getResult(input));
	}
	
	public void testcase03() {
		File intputFile = new File(TEST_PATH + "testcase_03.txt");
		int[] input = demo.read(intputFile);
		Assert.assertEquals(9, demo.getResult(input));
	}
	
}
