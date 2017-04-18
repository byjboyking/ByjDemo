package byj.util;


import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.Random;
import java.util.regex.Matcher;
import java.util.regex.Pattern;



public class U {
	private static final boolean PRINT_INFO_FLAG = true;
	private static final boolean PRINT_KEY_INFO_FLAG = true;
	private static final boolean PRINT_ERR_FLAG = true;
	
	private static final boolean PRINT_FLAG = true;
	
	

	public static void info(String msg) {
		info(msg, false);
	}
	
	public static void print(String msg){
		if(PRINT_FLAG)
			System.out.println(msg);
	}

	// 打印格式如下：
	// 带线程打印： 18:02:53.337 - [1,main,Alive,RUNNABLE]-msg
	// 不带线程打印： 18:02:53.337 - msg
	public static void info(String msg, boolean isShowThreadInfo) {
		if (PRINT_INFO_FLAG)
			System.out.println(collectInfo(msg, isShowThreadInfo));
	}

	public static void keyInfo(String msg) {
		keyInfo(msg, false);
	}

	public static void keyInfo(String msg, boolean isShowThreadInfo) {
		if (PRINT_KEY_INFO_FLAG)
			System.out.println(collectInfo(msg, isShowThreadInfo) + "-[关键日志]");
	}

	public static void err(String msg) {
		err(msg, false);
	}

	public static void err(String msg, boolean isShowThreadInfo) {
		if (PRINT_ERR_FLAG)
			System.err.println(collectInfo(msg, isShowThreadInfo));
	}
	
	public static void infoOrErr(String msg, boolean isShowThreadInfo, boolean isInfo){
		if(isInfo)
			info(msg, isShowThreadInfo);
		else
			err(msg,isShowThreadInfo);
	}

	private static String getCurrentDatetime() {
		// 格式：23:36:50.333
		SimpleDateFormat df = new SimpleDateFormat("HH:mm:ss.SSS");
		String formatDataTime = df.format(new Date(System.currentTimeMillis()));
		return formatDataTime;
	}

	private static String collectInfo(String msg, boolean showThread) {
		String threadInfo = "";
		if (showThread) {
			threadInfo = collectionThreadInfo();
			return getCurrentDatetime() + " - " + threadInfo + "-" + msg;
		} else {
			return getCurrentDatetime() + " - " + msg;
		}
	}

	private static String collectionThreadInfo() {
		Thread t = Thread.currentThread();
		// final String id = fillBlankChars(String.valueOf(t.getId()), 2);
		final String id = String.valueOf(t.getId());

		// max: Thread-0
		// final String name = fillBlankChars(t.getName(),9);
		final String name = t.getName();

		// max: false;
		// final String isAlive = fillBlankChars(String.valueOf(t.isAlive()),
		// 5);
		final String isAlive = t.isAlive() ? "Alive" : "NOT Alive";
		// max: TIMED_WAITING
		// final String state = fillBlankChars(t.getState().toString(),10);
		final String state = t.getState().toString();

		/*
		 * return "[tid:"+id+" ;name:"+name+" ;isAlive:"+isAlive+
		 * " ;state:"+state+"]";
		 */

		return "[" + id + "," + name + "," + isAlive + "," + state + "]";
	}

	public static void readTxtToList(final String srcTxtFile, List<String> lineList) {
		// 示例代码
		/*
		  String srcTxtFile = "d:\\aa.txt"; List<String> list = new
		  ArrayList<String>(); readTxtToList(srcTxtFile, list); for (String
		  string : list) { System.out.println(string); }
		 */

		lineList.clear();
		BufferedReader reader = null;
		try {
			reader = new BufferedReader(new FileReader(srcTxtFile));
			String tempString = null;
			while ((tempString = reader.readLine()) != null) {
				lineList.add(tempString);
			}
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			if (reader != null) {
				try {
					reader.close();
				} catch (IOException e1) {
					e1.printStackTrace();
				}
			}
		}
	}

	public static void printArray(int[][] a){

		System.out.println("printArray-> a.length:"+a.length);
		for(int i=0;i<a.length;i++){
			String formatStr = "|--";
			String spaceStr = "";
			int size = a[i].length;
			for(int j=1;j<size;j++){
				spaceStr += "      ";
			}
			System.out.println(spaceStr+formatStr+array2Str(a[i]));
		}
	}
	

	public static String array2Str(int[] a){
		if(a==null){
			return "null";
					
		}
		StringBuilder sb=new StringBuilder();
		sb.append("{");
		for(int i=0;i<a.length;i++){
			sb.append(a[i]);
			
			if(i!= a.length-1){
				sb.append(",");
			}
		}
		
		sb.append("}");
		return sb.toString();
	}
	
	public static String constructSpace(int num){
		StringBuilder sb= new StringBuilder();
		for(int i=0;i<num;i++){
			sb.append(" ");
		}
		return sb.toString();
	}
	
	public static int[][] clone2DimensionalArray(int[][] arr){
		int[][] cloneArr = new int[arr.length][];
		
		for(int i=0;i<arr.length;i++){
			cloneArr[i] = arr[i].clone();
		}
		
		return cloneArr;
	}
	
	
	public static String array2Str(int[][] arr){
		if (arr == null)
			return "null";

		if (arr.length == 0)
			return "[]";
		
		StringBuilder sb =new StringBuilder();
		sb.append("{");
		for(int i=0;i<arr.length;i++){
			sb.append(Arrays.toString(arr[i]));
		}
		
		sb.append("}");
		
		return sb.toString();
	}
	
	public static String array2Str(String[] a){
		if(a==null){
			return "null";
					
		}
		StringBuilder sb=new StringBuilder();
		sb.append("{");
		for(int i=0;i<a.length;i++){
			sb.append(a[i]);
			
			if(i!= a.length-1){
				sb.append(",");
			}
		}
		
		sb.append("}");
		return sb.toString();
	}
	
	
	
	private final static String REGEX_SPACE = "[\\s]*";
	private final static String REGEX_NUMBER = "[-]?[0-9]+";  // 包括负数符号
	private final static String REGEX_COMMA = "[\\,]{1}";
	private final static String REGEX_LEFT_BRACKET = "[\\(]{1}";
	private final static String REGEX_RIGHT_BRACKET = "[\\)]{1}";
	
	//  ) , (
	private final static String REGEX_SPLITTER = REGEX_RIGHT_BRACKET
			+ REGEX_SPACE + REGEX_COMMA + REGEX_SPACE + REGEX_LEFT_BRACKET;
	
	// ( 1 , 1, 1 )
	private final static  String REGEX = REGEX_LEFT_BRACKET+
			REGEX_SPACE+"("+REGEX_NUMBER+")"+REGEX_SPACE +
			REGEX_COMMA+
			REGEX_SPACE+"("+REGEX_NUMBER+")"+REGEX_SPACE+
			REGEX_COMMA+
			REGEX_SPACE+"("+REGEX_NUMBER+")"+REGEX_SPACE+
			REGEX_RIGHT_BRACKET;
	
	public static  class Item{
		public int index;
		public int dependsOn;
		public int position;
		public int delta;
		public int depth;
		
		public Item(int index ,int dependsOn, int position, int delta){
			this.index = index;
			this.dependsOn = dependsOn;
			this.position = position;
			this.delta =delta;
		}
		
		@Override
		public String toString() {
			return "[index:"+index+" ;dependsOn:"+dependsOn+" ; position:"+position+ " ;delta:"+delta+" ;depth:"+depth+" ]";
		}
	}
	
	// 解析  (2,3,22),(-1,5,15),(-1,4,10)   
	public static List<Item> readItems(String input){
		//sample code
		//List<Item> list1 = U.readItems(" (2,3,22),(-1,5,15),(-1,4,10)");
		//U.err(list1.toString());
		
		List<Item> itemList = new ArrayList<Item>();
		
		Pattern p = Pattern.compile(REGEX);
		Matcher m = p.matcher(input);
		
		int index=0;
		while(m.find()){
			String group = m.group();
			U.err("readOps->group:  "+ group);
			
			try {
				int dependsOn = Integer.parseInt(m.group(1).trim());
				int position =  Integer.parseInt(m.group(2).trim());
				int delta = Integer.parseInt(m.group(3).trim());
				
				Item op = new Item(index, dependsOn, position, delta);
				
				itemList.add(op);
				
				index++;
			} catch (NumberFormatException e) {
				// TODO Auto-generated catch block
				//e.printStackTrace();
				
				U.err("readOps->解析异常，直接返回");
				//解析异常，直接返回
			
				//throw new InvalidParamException("操作字符解析异常");
			}
		}
		
		return itemList;
	}
	
	//  (2,3,22),(-1,5,15),(-1,4,10)  ->    2 
	public static int getSpliterCounter(String itemsStr){
		//sample code
		//int counter = U.getSpliterCounter(" (2,3,22),(-1,5,15),(-1,4,10)");
		//U.err("counter:"+counter);
		
		
		Pattern p = Pattern.compile(REGEX_SPLITTER);
		Matcher m = p.matcher(itemsStr);
		
		int counter=0;
		while(m.find()){
			counter++;
		}
		
		return counter;
	}
	
	private final static String charset = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz";
	
	public static boolean isValidInput(String src, String validCharSet){
		//sample code
		//boolean b1 = isValidInput( "Hell!ddd", charset);
		
		
		
		for(int i=0;i<src.length();i++){
			int index = validCharSet.indexOf(src.charAt(i));
			if(index == -1){
				return false;
			}
		}
		
		return true;
		
	}
	
	
	//
	public static void main(String[] args){
		//demofindFamilyTree();
		
		//huawei.device.TestCase001.java
		//U.err(replaceAllSlashWithDot("huawei/device/TestCase001.java"));
	}
	
	private static void demofindFamilyTree() {
		// 1. 正常场景
		// List<Item> list =
		// readItems("(-1,0,1),(0,0,1),(1,0,1),(2,0,1),(1,0,1)");
		// 2.循环依赖
		// List<Item> list = readItems("(1,0,1),(2,0,1),(0,0,1)");
		// 3. 找不到父节点
		List<Item> list = readItems("(-1,0,1),(4,0,1),(0,0,1)");

		U.info("查找家族树前：" + list.toString());
		try {
			for (Item item : list) {
				List<Item> familyList = new ArrayList<Item>();
				findFamilyTree(item, list, familyList);
				item.depth = familyList.size();
			}
		} catch (InvalidParamException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		U.info("查找家族树后：" + list.toString());
	}
	
	public static void findFamilyTree(Item op,List<Item> rawOpsList, List<Item> familyList) throws InvalidParamException{
		if(op.dependsOn==-1){
			familyList.add(op);
			return;
		}
		
		if(isExist(op,familyList)){
			//循环依赖
			U.err("findFamilyTree->isExist(op,familyList)， 循环依赖，throw new InvalidParamException ");
			throw new InvalidParamException("isExist(op,familyList)， 循环依赖，");
		}
		
		//步骤1：把 自己加进去：
		familyList.add(op);
		
		//步骤2：查找父节点
		Item parentOp = findOpById(op.dependsOn, rawOpsList);
		if(parentOp==null){
			//找不到父节点
			U.err("findFamilyTree->parentOp==null，throw new InvalidParamException ");
			throw new InvalidParamException("parentOp==null");
		}
		
		//步骤3：递归调用
		findFamilyTree(parentOp,rawOpsList,familyList);
	}
	
	private static Item findOpById(int index,List<Item> rawOpsList){
		for (Item item : rawOpsList) {
			if(index==item.index)
				return item;
		}
		
		return null;
	}
	
	
	private static boolean isExist(Item op,List<Item> rawOpsList){
		for (Item item : rawOpsList) {
			if(op.index==item.index)
				return true;
		}
		
		return false;
	}
	
	private static class InvalidParamException extends Exception{
		public InvalidParamException(String str){
			super(str);
		}
	}
	
	private static Random sRand = new Random(System.currentTimeMillis());
	// 生成一个x以内的随机数
	public static int generateIntRandom(int n){
		return sRand.nextInt(n);
	}
	
	public static long generateIntRandom2(int n){
		return (long) (Math.random() * n);
	}
	
	//将字符串中的所有反斜杠\ 替换为  正斜杠/
	public static String replaceAllBackSlashWithSlash(String str){
		 return str.replaceAll("\\\\","/");
	}
	
	public static String replaceAllSlashWithDot(String str){
		return str.replaceAll("/","\\.");
	}
}