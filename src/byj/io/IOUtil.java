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
				// 文件夹不过滤
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
				// System.out.println("文件夹:" + file2.getAbsolutePath());
				getAllFilesRecursiveInternal(file2.getAbsolutePath(), fileList, postfix);
			} else {
				// System.out.println("文件:" + file2.getAbsolutePath());
				//fileList.add(file2.getAbsolutePath());
				fileList.add(file2);
			}
		}
	}

	/**
	 * 拷贝文件夹 
	 * 1、如果des 不存在会自动创建 
	 * 2、将 src 里面的 所有文件夹、文件 （包括子文件夹和子文件） 都拷贝到 des 里
	 * （递归调用） 
	 * 3、如果des 有相同文件会被覆盖
	 * 
	 * @param src
	 *            源文件夹
	 * @param des
	 *            目标文件夹
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
				copyFile(f.getPath(), des + "\\" + f.getName()); // 调用文件拷贝的方法
			} else if (f.isDirectory()) {
				copyFolderInternal(f.getPath(), des + "\\" + f.getName());
			} else {
				// do nothing
			}
		}
	}

	/**
	 * 拷贝文件(自动创建目标文件的 父文件夹)
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
			
			//如果有必要，创建父文件夹
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
	 * 拷贝文件（支持把 原始文件的几级文件夹 都拷贝过去）
	 * 举例如下：
	 * 把  d:\src 中的  ab\cd\a.jar 拷贝到 d:\target
	 * 最终路径是： d:\target\ab\cd\a.jar
	 * 则相应参数如下：
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
	
	//创建父文件夹（如果有必要的）
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
	 * 将字符串信息写入到指定文件
	 * 1、如果文件不存在会创建
	 * 2、文件存在时， append 为true 为追加写， false 为覆盖
	 * 3、文件对应的文件夹 不存在时，会创建
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
	 * 关闭一个或多个流对象
	 * @param closeables 可关闭的流对象列表
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
	 * 关闭一个或多个流对象
	 */
	public static void closeQuietly(Closeable... closeables) {
		try {
			close(closeables);
		} catch (IOException e) {
			// do nothing
		}
	}

	/**
	 * 同时支持zip和rar
	 * @param zipFileName 需要解压缩的zip和rar文件
	 * @param targetFolder 解压缩的目标文件夹
	 * @return
	 */
	public static boolean unZip(String zipFileName, String targetFolder) {
		if (zipFileName.toLowerCase().endsWith(".zip")) {
			return unZipFiles(zipFileName, targetFolder);
		} else if (zipFileName.toLowerCase().endsWith(".rar")) {
			unRarFile(zipFileName, targetFolder);
			return true;
		}else{
			U.err("unZip->不是zip或rar，无法解压缩！");
			return false;
		}
	}

	/**
	 * 解压zip格式的压缩文件到指定位置
	 * @param zipFileName 压缩文件
	 * @param extPlace 解压目录
	 */
	@SuppressWarnings("unchecked")
	private static boolean unZipFiles(String zipFileName, String extPlace) {
		System.setProperty("sun.zip.encoding", System.getProperty("sun.jnu.encoding"));
		try {
			(new File(extPlace)).mkdirs();
			File f = new File(zipFileName);
			ZipFile zipFile = new ZipFile(zipFileName,"GBK");  //处理中文文件名乱码的问题
			if((!f.exists()) && (f.length() <= 0)) {
				throw new Exception("要解压的文件不存在!");
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
					//读写文件
					InputStream is = zipFile.getInputStream(zipEnt);
					BufferedInputStream bis = new BufferedInputStream(is);
					gbkPath=zipEnt.getName();
					strtemp = strPath + File.separator + gbkPath;

					//建目录
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
	 * 根据原始rar路径，解压到指定文件夹下.
	 * @param srcRarPath 原始rar路径
	 * @param dstDirectoryPath 解压到的文件夹
	 */
	public static void unRarFile(String srcRarPath, String dstDirectoryPath) {
		if (!srcRarPath.toLowerCase().endsWith(".rar")) {
			System.out.println("非rar文件！");
			return;
		}
		File dstDiretory = new File(dstDirectoryPath);
		if (!dstDiretory.exists()) {// 目标目录不存在时，创建该文件夹
			dstDiretory.mkdirs();
		}
		Archive a = null;
		try {
			a = new Archive(new File(srcRarPath));
			if (a != null) {
				//a.getMainHeader().print(); // 打印文件信息.
				FileHeader fh = a.nextFileHeader();
				while (fh != null) {
					//防止文件名中文乱码问题的处理

					String fileName = fh.getFileNameW().isEmpty()?fh.getFileNameString():fh.getFileNameW();
					if (fh.isDirectory()) { // 文件夹
						File fol = new File(dstDirectoryPath + File.separator + fileName);
						fol.mkdirs();
					} else { // 文件
						File out = new File(dstDirectoryPath + File.separator + fileName.trim());
						try {
							if (!out.exists()) {
								if (!out.getParentFile().exists()) {// 相对路径可能多级，可能需要创建父目录.
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
