package byj.io;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.BufferedWriter;
import java.io.Closeable;
import java.io.File;
import java.io.FileFilter;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.zip.ZipEntry;
import java.util.zip.ZipInputStream;
import java.util.zip.ZipOutputStream;

import byj.util.U;

public class IOOperDemo {

	public static void main(String[] args) {

		// 1. ����ָ���ļ��У� ��ȡָ����׺��(java)���ļ���
//		 List<String> fileList = new ArrayList<String>();
//		 getAllFilesRecursive("D:\\jnjdtest\\TestCase", fileList, "java");
//		 U.info("fileList:" + fileList.toString());
		 

		// 2. �����ļ��У��ļ�
		//copyFolder("D:\\testCopy\\src", "D:\\testCopy\\target");

		
		//3. �����ļ�
		//copyFile("D:\\testCopy\\src\\bb\\cc\\aa.txt", "D:\\testCopy\\target2\\bb1\\cc1\\aa1.txt");
		//copyFileWithHierarchy("D:\\testCopy\\src","bb\\cc\\aa.txt", "D:\\testCopy\\target2");
		
		
		//4. ɾ���ļ����е� ��������
		//deleteAllInFolder("D:\\testCopy\\testdelete");
		
		
		//5. ��ʽ��path
//		String path1 = "D:\\\\testCopy12\\\\testdelete33";
//		String path2 = formatPath(path1);
//		U.info("path1:"+path1);
//		U.info("path2:"+path2);
		
		//6. ��string д�� �ļ�
//		String path = "D:\\testCopy\\cc1\\cc2\\a1.txt";
//		writeToFile(path, "overwrite", false);
		
		
		//7. ѹ���ͽ�ѹ��
		boolean zipRtn = IOUtil.zip("D:\\testCopy\\�½��ļ���\\src-����","D:\\testCopy\\�½��ļ���\\src-����-target1.rar") ;
		U.info("ѹ������״̬��zipRtn:"+zipRtn);
	    //ע�⣬�ļ��������пո񣬷����ѹ���������⣡���������
		boolean unzipRtn = IOUtil.unzip("D:\\testCopy\\�½��ļ���\\src-����-target1.rar","D:\\testCopy\\�½��ļ��� (6)");
		U.info("��ѹ������״̬��unzipRtn:"+unzipRtn);
	}


    
	
	
	
}
