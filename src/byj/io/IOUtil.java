package byj.io;

import byj.util.U;
import com.github.junrar.Archive;
import com.github.junrar.rarfile.FileHeader;
import org.apache.tools.zip.ZipEntry;
import org.apache.tools.zip.ZipFile;

import java.io.*;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.List;

public class IOUtil {

	public static void getAllFilesRecursiveEx(String path, List<File> fileList, final String postfix) {
		getAllFilesRecursiveInternal( path,  fileList,  postfix);
	}
	
	public static void getAllFilesRecursive(String path, List<String> stringList, final String postfix) {
		List<File> fileList = new ArrayList<File>();
		getAllFilesRecursiveEx(path, fileList,postfix);
		fileList2StringList(fileList,stringList);
	}
	
	private static void fileList2StringList(List<File> fileList,List<String> list){
		if(fileList==null){
			return;
		}
		
		for(File file:fileList){
			list.add(file.getAbsolutePath());
		}
	}
	
	private static void getAllFilesRecursiveInternal(String path, List<File> fileList, final String postfix) {
		File file = new File(path);
		if (!file.exists()) {
			return;
		}

		File[] files = file.listFiles(new FileFilter() {
			@Override
			public boolean accept(File pathname) {
				// �ļ��в�����
				if (pathname.isDirectory()) {
					return true;
				}

				if (postfix == null || postfix.isEmpty()) {
					return true;
				}
				return pathname.getName().toLowerCase().endsWith(postfix.toLowerCase());
			}
		});

		if (files == null) {
			return;
		}

		for (File file2 : files) {
			if (file2.isDirectory()) {
				// System.out.println("�ļ���:" + file2.getAbsolutePath());
				getAllFilesRecursiveInternal(file2.getAbsolutePath(), fileList, postfix);
			} else {
				// System.out.println("�ļ�:" + file2.getAbsolutePath());
				//fileList.add(file2.getAbsolutePath());
				fileList.add(file2);
			}
		}
	}

	/**
	 * �����ļ��� 
	 * 1�����des �����ڻ��Զ����� 
	 * 2���� src ����� �����ļ��С��ļ� ���������ļ��к����ļ��� �������� des ��
	 * ���ݹ���ã� 
	 * 3�����des ����ͬ�ļ��ᱻ����
	 * 
	 * @param src
	 *            Դ�ļ���
	 * @param des
	 *            Ŀ���ļ���
	 */
	public static void copyFolder(String src, String des) {
		try {
			copyFolderInternal(src, des);
		} catch (Exception e) {
			U.err("copyFolder-> e:" + e);
		}
	}

	private static void copyFolderInternal(String src, String des) {
		File file1 = new File(src);
		File[] fs = file1.listFiles();
		File file2 = new File(des);
		if (!file2.exists()) {
			file2.mkdirs();
		}

		for (File f : fs) {
			if (f.isFile()) {
				copyFile(f.getPath(), des + "\\" + f.getName()); // �����ļ������ķ���
			} else if (f.isDirectory()) {
				copyFolderInternal(f.getPath(), des + "\\" + f.getName());
			} else {
				// do nothing
			}
		}
	}

	/**
	 * �����ļ�(�Զ�����Ŀ���ļ��� ���ļ���)
	 * @param src
	 * @param des
	 */
	public static boolean copyFile(String src, String des) {
		FileInputStream fis = null;
		FileOutputStream fos = null;

		try {
			File srcFile = new File(src);
			if(!srcFile.exists()){
				U.err("copyFile->srcFile NOT exist. src:"+src);
				return false;
			}
			
			//����б�Ҫ���������ļ���
			File desFile = new File(des);
			createParentFolderIfNeeded(des);
			
			if (!desFile.exists()) {
				desFile.createNewFile();
			}

			fis = new FileInputStream(src);
			fos = new FileOutputStream(desFile);
			byte[] buffer = new byte[1024];
			int length;
			while ((length = fis.read(buffer)) != -1) {
				fos.write(buffer, 0, length);
			}
			
			return true;
		} catch (FileNotFoundException e) {
			U.err("copyFile->FileNotFoundException e:"+e);
		} catch (IOException e) {
			U.err("copyFile->IOException e:"+e);
		} finally {
			try {
				if (fis != null)
					fis.close();
				if (fos != null)
					fos.close();
			} catch (IOException e) {
				U.err("copyFile->close, IOException e:"+e);
			}
		}
		
		return false;
	}
	
	/**
	 * �����ļ���֧�ְ� ԭʼ�ļ��ļ����ļ��� ��������ȥ��
	 * �������£�
	 * ��  d:\src �е�  ab\cd\a.jar ������ d:\target
	 * ����·���ǣ� d:\target\ab\cd\a.jar
	 * ����Ӧ�������£�
	 * srcBasePath = d:\src
	 * srcSubPath = ab\cd\a.jar
	 * targetPath = d:\target
	 * @param srcBasePath
	 * @param srcSubPath
	 * @param targetPath
	 * @return
	 */
	public static boolean copyFileWithHierarchy(String srcBasePath, String srcSubPath, String targetPath) {
		String srcAbsolutePath = srcBasePath+"\\"+srcSubPath;
		String targetAbsolutePath = targetPath+"\\"+srcSubPath;
		return copyFile(srcAbsolutePath,targetAbsolutePath);
	}
	
	
	public static boolean deleteAllInFolder(String path){
		return deleteAllInFolderInternal(path);
	}
	
	private static boolean deleteAllInFolderInternal(String path){
		File file = new File(path);
		if(!file.isDirectory()){
			U.err("deleteAllInFolderInternal-> file is not a dir. path:"+path);
			return false;
		}
		
		File[] files = file.listFiles();
		if (files == null) {
			return false;
		}

		for (File file2 : files) {
			if(file2.isDirectory()){
				boolean deleteAllFlag = deleteAllInFolderInternal(file2.getAbsolutePath());
				if(!deleteAllFlag){
					U.err("deleteAllInFolderInternal->deleteAllFlag:"+deleteAllFlag);
					return false;
				}
			}
			
			boolean deleteFlag = file2.delete();
			if(!deleteFlag){
				U.err("deleteAllInFolderInternal->delteFlag:"+deleteFlag);
				return false;
			}
		}
		
		return true;
	}
	
	
	public static String formatPath(String path){
		File file = new File(path);
		return file.getPath();
	}
	
	//�������ļ��У�����б�Ҫ�ģ�
	private static void createParentFolderIfNeeded(String path) {
		File desFile = new File(path);
		String parentPath = desFile.getParent();
		createFolderIfNeeded(parentPath);
	}
	
	private static void createFolderIfNeeded(String path) {
		File folder = new File(path);
		if (!folder.exists()) {
			boolean mkDirRtn = folder.mkdirs();
			if (!mkDirRtn) {
				U.err("createFolderIfNeeded->mkDirRtn==false,  path:" + path);
			}
		}
	}
	
		
	/**
	 * ���ַ�����Ϣд�뵽ָ���ļ�
	 * 1������ļ������ڻᴴ��
	 * 2���ļ�����ʱ�� append Ϊtrue Ϊ׷��д�� false Ϊ����
	 * 3���ļ���Ӧ���ļ��� ������ʱ���ᴴ��
	 * @param file
	 * @param content
	 * @param append
	 */
	public static void writeToFile(String file, String content,boolean append) {
		BufferedWriter out = null;
		try {
			createParentFolderIfNeeded(file);
			out = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(file, append)));
			out.write(content);
		} catch (Exception e) {
			U.err("writeToFile->e:"+e);
		} finally {
			try {
				if (out != null) {
					out.close();
				}
			} catch (IOException e) {
				U.err("writeToFile->out.close, e:"+e);
			}
		}
	}

	/**
	 * �ر�һ������������
	 * @param closeables �ɹرյ��������б�
	 * @throws IOException
	 */
	public static void close(Closeable... closeables) throws IOException {
		if (closeables != null) {
			for (Closeable closeable : closeables) {
				if (closeable != null) {
					closeable.close();
				}
			}
		}
	}

	/**
	 * �ر�һ������������
	 */
	public static void closeQuietly(Closeable... closeables) {
		try {
			close(closeables);
		} catch (IOException e) {
			// do nothing
		}
	}

	/**
	 * ͬʱ֧��zip��rar
	 * @param zipFileName ��Ҫ��ѹ����zip��rar�ļ�
	 * @param targetFolder ��ѹ����Ŀ���ļ���
	 * @return
	 */
	public static boolean unZip(String zipFileName, String targetFolder) {
		if (zipFileName.toLowerCase().endsWith(".zip")) {
			return unZipFiles(zipFileName, targetFolder);
		} else if (zipFileName.toLowerCase().endsWith(".rar")) {
			unRarFile(zipFileName, targetFolder);
			return true;
		}else{
			U.err("unZip->����zip��rar���޷���ѹ����");
			return false;
		}
	}

	/**
	 * ��ѹzip��ʽ��ѹ���ļ���ָ��λ��
	 * @param zipFileName ѹ���ļ�
	 * @param extPlace ��ѹĿ¼
	 */
	@SuppressWarnings("unchecked")
	private static boolean unZipFiles(String zipFileName, String extPlace) {
		System.setProperty("sun.zip.encoding", System.getProperty("sun.jnu.encoding"));
		try {
			(new File(extPlace)).mkdirs();
			File f = new File(zipFileName);
			ZipFile zipFile = new ZipFile(zipFileName,"GBK");  //���������ļ������������
			if((!f.exists()) && (f.length() <= 0)) {
				throw new Exception("Ҫ��ѹ���ļ�������!");
			}
			String strPath, gbkPath, strtemp;
			File tempFile = new File(extPlace);
			strPath = tempFile.getAbsolutePath();
			Enumeration<?> e = zipFile.getEntries();
			while(e.hasMoreElements()){
				ZipEntry zipEnt = (ZipEntry) e.nextElement();
				gbkPath=zipEnt.getName();
				if(zipEnt.isDirectory()){
					strtemp = strPath + File.separator + gbkPath;
					File dir = new File(strtemp);
					dir.mkdirs();
					continue;
				} else {
					//��д�ļ�
					InputStream is = zipFile.getInputStream(zipEnt);
					BufferedInputStream bis = new BufferedInputStream(is);
					gbkPath=zipEnt.getName();
					strtemp = strPath + File.separator + gbkPath;

					//��Ŀ¼
					String strsubdir = gbkPath;
					for(int i = 0; i < strsubdir.length(); i++) {
						if(strsubdir.substring(i, i + 1).equalsIgnoreCase("/")) {
							String temp = strPath + File.separator + strsubdir.substring(0, i);
							File subdir = new File(temp);
							if(!subdir.exists())
								subdir.mkdir();
						}
					}
					FileOutputStream fos = new FileOutputStream(strtemp);
					BufferedOutputStream bos = new BufferedOutputStream(fos);
					int c;
					while((c = bis.read()) != -1) {
						bos.write((byte) c);
					}
					bos.close();
					fos.close();
				}
			}
			return true;
		} catch(Exception e) {
			e.printStackTrace();
			return false;
		}
	}
	/**
	 * ����ԭʼrar·������ѹ��ָ���ļ�����.
	 * @param srcRarPath ԭʼrar·��
	 * @param dstDirectoryPath ��ѹ�����ļ���
	 */
	public static void unRarFile(String srcRarPath, String dstDirectoryPath) {
		if (!srcRarPath.toLowerCase().endsWith(".rar")) {
			System.out.println("��rar�ļ���");
			return;
		}
		File dstDiretory = new File(dstDirectoryPath);
		if (!dstDiretory.exists()) {// Ŀ��Ŀ¼������ʱ���������ļ���
			dstDiretory.mkdirs();
		}
		Archive a = null;
		try {
			a = new Archive(new File(srcRarPath));
			if (a != null) {
				//a.getMainHeader().print(); // ��ӡ�ļ���Ϣ.
				FileHeader fh = a.nextFileHeader();
				while (fh != null) {
					//��ֹ�ļ���������������Ĵ���

					String fileName = fh.getFileNameW().isEmpty()?fh.getFileNameString():fh.getFileNameW();
					if (fh.isDirectory()) { // �ļ���
						File fol = new File(dstDirectoryPath + File.separator + fileName);
						fol.mkdirs();
					} else { // �ļ�
						File out = new File(dstDirectoryPath + File.separator + fileName.trim());
						try {
							if (!out.exists()) {
								if (!out.getParentFile().exists()) {// ���·�����ܶ༶��������Ҫ������Ŀ¼.
									out.getParentFile().mkdirs();
								}
								out.createNewFile();
							}
							FileOutputStream os = new FileOutputStream(out);
							a.extractFile(fh, os);
							os.close();
						} catch (Exception ex) {
							ex.printStackTrace();
						}
					}
					fh = a.nextFileHeader();
				}
				a.close();
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}


}
