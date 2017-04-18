package byj.execCmd;

import java.io.BufferedReader;
import java.io.InputStreamReader;

//����1. ִ�� cmd start ��һ���ı��ļ�
//����2. Java���������в���ȡִ�н�� ��ִ��һ��ping ���� ����ȡ���ص���Ϣ�� 
public class ExecCmdDemo {

	public static void main(String[] args) {
		
	
		
		
		execCmd();
		
		//execCmdAndGetResponse( "ping www.taobao.com");
		
		//a.bat   ���棬 ����һ�� ping www.taobao.com
		//execCmdAndGetResponse( "D:\\a.bat");
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
