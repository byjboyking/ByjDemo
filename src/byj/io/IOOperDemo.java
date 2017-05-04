package byj.io;

import byj.util.U;

public class IOOperDemo {

	public static void main(String[] args) {

		// 1. 遍历指定文件夹， 获取指定后缀名(java)的文件。
//		 List<String> fileList = new ArrayList<String>();
//		 getAllFilesRecursive("D:\\jnjdtest\\TestCase", fileList, "java");
//		 U.info("fileList:" + fileList.toString());
		 

		// 2. 拷贝文件夹，文件
		//copyFolder("D:\\testCopy\\src", "D:\\testCopy\\target");

		
		//3. 拷贝文件
		//copyFile("D:\\testCopy\\src\\bb\\cc\\aa.txt", "D:\\testCopy\\target2\\bb1\\cc1\\aa1.txt");
		//copyFileWithHierarchy("D:\\testCopy\\src","bb\\cc\\aa.txt", "D:\\testCopy\\target2");
		
		
		//4. 删除文件夹中的 所有内容
		//deleteAllInFolder("D:\\testCopy\\testdelete");
		
		
		//5. 格式化path
//		String path1 = "D:\\\\testCopy12\\\\testdelete33";
//		String path2 = formatPath(path1);
//		U.info("path1:"+path1);
//		U.info("path2:"+path2);
		
		//6. 将string 写入 文件
//		String path = "D:\\testCopy\\cc1\\cc2\\a1.txt";
//		writeToFile(path, "overwrite", false);
		
		
		//7. 压缩和解压缩
//		boolean zipRtn = IOUtil.zip("D:\\testCopy\\新建文件夹\\src-副本","D:\\testCopy\\新建文件夹\\src-副本-target1.rar") ;
//		U.info("压缩操作状态，zipRtn:"+zipRtn);
//	    //注意，文件名不能有空格，否则解压缩会有问题！！待解决。
		boolean unzipRtn = IOUtil.unZip("C:\\Users\\byj\\Desktop\\ddd\\test01.rar","C:\\Users\\byj\\Desktop\\ddd\\test02");
		U.info("解压缩操作状态，unzipRtn:"+unzipRtn);



	}


    
	
	
	
}
