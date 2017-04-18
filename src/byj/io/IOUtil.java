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
	
	
	
	
	
	
	//第二版
	
	/**
	 * 压缩文件
	 * 已经验证。 压缩支持 txt， python，java，xlsx
	 * @param filePath 待压缩的文件夹路径
	 * @param targetPath 压缩后的目标文件（需要以rar和 zip 结尾， 如果存在会先删除）
	 * @return
	 */
    public static boolean zip(String filePath,String targetPath) {
        File target = null;
        File source = new File(filePath);
        if (!source.exists()) {
        	U.err("zip->filePath not exist, filePath:"+filePath);
        	return false;
        }
        
        target = new File(targetPath);
        if( !(target.getName().toLowerCase().endsWith("rar") || target.getName().toLowerCase().endsWith("zip")) ){
        	//不是 rar或 zip
        	U.err("zip->目标文件后缀名异常，无法压缩,targetPath:"+targetPath);
        	return false;
        }
        
        createParentFolderIfNeeded(targetPath);
        if (target.exists()) {
            boolean delete = target.delete();//删除旧的压缩包
            U.info("zip->targetPath exist,  delete it,  deleteFlag :"+delete);
        }
        
        FileOutputStream fos = null;
        ZipOutputStream zos = null;
        try {
            fos = new FileOutputStream(target);
            zos = new ZipOutputStream(new BufferedOutputStream(fos));

            addEntry(null, source, zos);  //添加对应的文件Entry
        } catch (IOException e) {
            throw new RuntimeException(e);
        } finally {
            closeQuietly(zos, fos);
        }
        
        return true;
    }

    /**
     * 扫描添加文件Entry
     *
     * @param base   基路径
     * @param source 源文件
     * @param zos    Zip文件输出流
     * @throws IOException
     */
    private static void addEntry(String base, File source, ZipOutputStream zos) throws IOException {
        String entry = (base != null) ? base + source.getName() : source.getName(); //按目录分级，形如：aaa/bbb.txt
        if (source.isDirectory()) {
            File[] files = source.listFiles();
            if (files != null && files.length > 0) {
                for (File file : files) {
                    addEntry(entry + "/", file, zos);// 递归列出目录下的所有文件，添加文件 Entry
                }
            }
        } else {
            FileInputStream fis = null;
            BufferedInputStream bis = null;
            try {
                byte[] buffer = new byte[1024 * 10];
                fis = new FileInputStream(source);
                bis = new BufferedInputStream(fis, buffer.length);
                int read;
                zos.putNextEntry(new ZipEntry(entry)); //如果只是想将文件夹下的所有文件压缩，不需名要压缩父目录,约定文件名长度 entry.substring(length)
                while ((read = bis.read(buffer, 0, buffer.length)) != -1) {
                    zos.write(buffer, 0, read);
                }
                zos.closeEntry();
            } finally {
                closeQuietly(bis, fis);
            }
        }
    }

    /**
     * 解压文件
     *
     * @param filePath 压缩文件路径
     */
    public static boolean unzip(String filePath, String targetFolder) {
        File source = new File(filePath);
        if (!source.exists()) {
        	U.err("unzip->filePath not exist: filePath:"+filePath);
        	return false;
        }
        
        createFolderIfNeeded(targetFolder);

        ZipInputStream zis = null;
        BufferedOutputStream bos = null;
        try {
            zis = new ZipInputStream(new FileInputStream(source));
            ZipEntry entry;
            while ((entry = zis.getNextEntry()) != null && !entry.isDirectory()) {
                File target = new File(targetFolder, entry.getName());
                if (!target.getParentFile().exists()) {  
                    target.getParentFile().mkdirs();
                }
                bos = new BufferedOutputStream(new FileOutputStream(target));
                int read;
                byte[] buffer = new byte[1024 * 10];
                while ((read = zis.read(buffer, 0, buffer.length)) != -1) {
                    bos.write(buffer, 0, read);
                }
                bos.flush();
            }
            zis.closeEntry();
        } catch (IOException e) {
            throw new RuntimeException(e);
        } finally {
            closeQuietly(zis, bos);
        }
        
        return true;
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
}
