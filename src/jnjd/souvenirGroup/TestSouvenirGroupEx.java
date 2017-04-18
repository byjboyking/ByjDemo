package jnjd.souvenirGroup;

import java.io.File;

import junit.framework.TestCase;

import org.junit.Assert;

public class TestSouvenirGroupEx extends TestCase {
	
	private final static String TEST_PATH = System.getProperty("user.dir")
			+ File.separator + "src" + File.separator + "jnjd\\souvenirGroup"
			+ File.separator;

	private SouvenirGroupEx demo;

	protected void setUp() throws Exception {
		demo = new SouvenirGroupEx();
	}

	public void testcase11() {
		File intputFile = new File(TEST_PATH + "testcase_11.txt");
		int[] input = demo.read(intputFile);
		Assert.assertEquals(6, demo.getResult(input));
	}

//	public void testcase02() {
//		File intputFile = new File(TEST_PATH + "testcase_02.txt");
//		int[] input = demo.read(intputFile);
//		Assert.assertEquals(9, demo.getResult(input));
//	}
//	
//	public void testcase03() {
//		File intputFile = new File(TEST_PATH + "testcase_03.txt");
//		int[] input = demo.read(intputFile);
//		Assert.assertEquals(9, demo.getResult(input));
//	}
	
}
