package byj.execCmd;

import byj.io.IOUtil;
import byj.util.U;
import org.apache.commons.exec.CommandLine;
import org.apache.commons.exec.DefaultExecutor;
import org.apache.commons.exec.ExecuteWatchdog;
import org.apache.commons.exec.PumpStreamHandler;

import java.awt.*;
import java.io.*;

//����1. ִ�� cmd start ��һ���ı��ļ�
//����2. Java���������в���ȡִ�н�� ��ִ��һ��ping ���� ����ȡ���ص���Ϣ�� 
public class ExecCmdDemo {

	public static void main(String[] args) {
		//execCmd();
		
		//execCmdAndGetResponse( "ping www.taobao.com");
		
		//a.bat   ���棬 ����һ�� ping www.taobao.com
		//execCmdAndGetResponse( "D:\\a.bat");

//		String response = execCmdAndGetResponseEx("cmd /c d: && cd D:\\jnjdtest && dir");
//		U.info("response:"+response);

//		String path="C:\\Program Files (x86)\\Youdao\\YoudaoNote\\YoudaoNote.exe";
//		startExe(path);

		//String response = execCmdAndGetResponseEx_watchdog("cmd /c d: && ping www.taobao.com");



		StringBuilder sb =new StringBuilder();
		sb.append("cmd /c d: ");
		for(int i=0;i<2;i++){
			sb.append(" && ");
			sb.append("ping www.taobao.com");
		}
		long start =  System.currentTimeMillis();
		String response = execCmdAndGetResponseWithWatchdog(sb.toString(),3000);
		long consuming = System.currentTimeMillis()-start;
		U.info("consuming:"+consuming+" ;response:"+response);
	}

	public static String execCmdAndGetResponseWithWatchdog(String command,long timeout) {
		ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
		ByteArrayOutputStream errorStream = new ByteArrayOutputStream();

		try {
			ExecuteWatchdog watchdog = new ExecuteWatchdog(timeout);
			CommandLine commandline = CommandLine.parse(command);
			DefaultExecutor exec = new DefaultExecutor();
			exec.setExitValues(null);
			exec.setWatchdog(watchdog);
			PumpStreamHandler streamHandler = new PumpStreamHandler(outputStream,errorStream);
			exec.setStreamHandler(streamHandler);
			int execStatus = exec.execute(commandline);
			U.info("execStatus:"+execStatus);
			//��������������ֵΪ0��
			//timeout, ����ֵΪ1��
			String out = outputStream.toString("gbk");
			String error = errorStream.toString("gbk");
			return out+error;
		} catch (Exception e) {
			U.err("ping task failed."+e);
			return e.toString();
		}finally {
			IOUtil.closeQuietly(outputStream,errorStream);
		}

	}


	private static void execCmd() {
		Runtime runtime = Runtime.getRuntime();
		try {
			//ע�⣬�˵��ú� ��ʼ-���� ���� ��������ǵȼ۵ġ�
//			cmd /c dir ��ִ����dir�����ر�����ڡ� 
//			cmd /k dir ��ִ����dir����󲻹ر�����ڡ� 
//			cmd /c start dir ���һ���´��ں�ִ��dirָ�ԭ���ڻ�رա� 
//			cmd /k start dir ���һ���´��ں�ִ��dirָ�ԭ���ڲ���رա� 

			//runtime.exec("cmd /c start d://a.txt");
			
			runtime.exec("cmd /c start D:\\jnjdtest\\run\\javac_cmd.bat");
		} catch (Exception e) {
			System.out.println("Error!");
		}
	}

	 public static void execCmdAndGetResponse(String commandStr) {
	        BufferedReader br = null;
	        try {
	            Process p = Runtime.getRuntime().exec(commandStr);
	            br = new BufferedReader(new InputStreamReader(p.getInputStream(), "gbk"));

	            String line = null;
	            StringBuilder sb = new StringBuilder();

	            while ((line = br.readLine()) != null) {
	                sb.append(line + "\n");
	            }
	            System.out.println(sb.toString());
	        } catch (Exception e) {
	            e.printStackTrace();
	        }
	        finally
	        {
				IOUtil.closeQuietly(br);
	        }
	    }

	/**
	 * ͨ�� org.apache.commons.exec �����е��������в�����
	 * 1��֧��  cmd1 && cmd2 �ķ�ʽ
	 * 2��������������쳣����������˴��� ��ע������ʹ��Process�� ��Ҫ���������쳣��������߼������ɱ��ϸߣ�
     *
     * �ο���http://blog.csdn.net/ligaoyang/article/details/8029020
	 * @param command
	 * @return
	 */
	public static String execCmdAndGetResponseEx(String command) {
		ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
		ByteArrayOutputStream errorStream = new ByteArrayOutputStream();

		try {
			CommandLine commandline = CommandLine.parse(command);
			DefaultExecutor exec = new DefaultExecutor();
			exec.setExitValues(null);
			PumpStreamHandler streamHandler = new PumpStreamHandler(outputStream,errorStream);
			exec.setStreamHandler(streamHandler);
			exec.execute(commandline);
			String out = outputStream.toString("gbk");
			String error = errorStream.toString("gbk");
			return out+error;
		} catch (Exception e) {
			U.err("ping task failed."+e);
			return e.toString();
		}finally {
			if (outputStream != null)
			{
				try {
					outputStream.close();
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
			if (errorStream != null)
			{
				try {
					errorStream.close();
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		}

	}



	/**
	 * ��exe�� ���¿�һ�����ڡ�
	 * ע�⣺�����ͨ�� Runtime.getRuntime().exec("cmd /k "+path);  �򿪣�ֻ�����������������exe�� û�п���exe�Ľ��档
	 * @param path
	 */
	public static void startExe(String path){

		try {
			Desktop.getDesktop().open(new File(path));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

}

