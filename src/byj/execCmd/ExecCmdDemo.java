package byj.execCmd;

import byj.io.IOUtil;
import byj.util.U;
import org.apache.commons.exec.CommandLine;
import org.apache.commons.exec.DefaultExecutor;
import org.apache.commons.exec.ExecuteWatchdog;
import org.apache.commons.exec.PumpStreamHandler;

import java.awt.*;
import java.io.*;

//功能1. 执行 cmd start 打开一个文本文件
//功能2. Java调用命令行并获取执行结果 （执行一个ping 命令 并获取返回的信息） 
public class ExecCmdDemo {

	public static void main(String[] args) {
		//execCmd();
		
		//execCmdAndGetResponse( "ping www.taobao.com");
		
		//a.bat   里面， 就是一行 ping www.taobao.com
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
			//正常场景，返回值为0；
			//timeout, 返回值为1；
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
			//注意，此调用和 开始-运行 调用 以下语句是等价的。
//			cmd /c dir 是执行完dir命令后关闭命令窗口。 
//			cmd /k dir 是执行完dir命令后不关闭命令窗口。 
//			cmd /c start dir 会打开一个新窗口后执行dir指令，原窗口会关闭。 
//			cmd /k start dir 会打开一个新窗口后执行dir指令，原窗口不会关闭。 

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
	 * 通过 org.apache.commons.exec 来进行调用命令行操作。
	 * 1、支持  cmd1 && cmd2 的方式
	 * 2、对正常输出和异常输出都进行了处理 （注：单独使用Process， 需要对正常和异常输出额外逻辑处理，成本较高）
     *
     * 参考：http://blog.csdn.net/ligaoyang/article/details/8029020
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
	 * 打开exe， 会新开一个窗口。
	 * 注意：如果是通过 Runtime.getRuntime().exec("cmd /k "+path);  打开，只能在任务管理器看到exe， 没有看到exe的界面。
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

