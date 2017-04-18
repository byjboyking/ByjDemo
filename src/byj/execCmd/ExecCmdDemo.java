package byj.execCmd;

import java.io.BufferedReader;
import java.io.InputStreamReader;

//功能1. 执行 cmd start 打开一个文本文件
//功能2. Java调用命令行并获取执行结果 （执行一个ping 命令 并获取返回的信息） 
public class ExecCmdDemo {

	public static void main(String[] args) {
		
	
		
		
		execCmd();
		
		//execCmdAndGetResponse( "ping www.taobao.com");
		
		//a.bat   里面， 就是一行 ping www.taobao.com
		//execCmdAndGetResponse( "D:\\a.bat");
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
	            if (br != null)
	            {
	                try {
	                    br.close();
	                } catch (Exception e) {
	                    e.printStackTrace();
	                }
	            }
	        }
	    }

}
