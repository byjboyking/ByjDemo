package byj.concurrent;


import java.util.concurrent.Semaphore;

import byj.util.U;

/**
 * Semaphore����������һ����Դ�صĹ��ߣ����Կ����Ǹ�ͨ��֤�� �߳�Ҫ�����Դ���õ���Դ�������õ�ͨ��֤��
 * ����߳���ʱ�ò���ͨ��֤���߳̾ͻᱻ��Ͻ���ȴ�״̬��
 * 
 * //���� ��ÿ���߳� timeout���Ƶ�ʵ�֡�
 * ����run��������һ���̣߳�˯��xx ������� ԭ�߳��Ƿ�isAlive, Ȼ��ͨ�� interrupt()���жϣ�
 *    ��run ��������Ҫcatch InterruptedException��
 */
public class SemaphoreDemo2 extends Thread {

	public static void main(String[] args) {
		ThreadAndSemaphoreDemo();
	}
	
	private final static int THREAD_TIMEOUT_MS  = 350;
	private final static int THREAD_BUSINESS_PROCESS_MS  = 100;
	
	private int i;
	private Semaphore semaphore;

	public SemaphoreDemo2(int i, Semaphore semaphore) {
		this.i = i;
		this.semaphore = semaphore;
	}

	public void run() {
		
		new Thread(new Runnable() {
			public void run() {
				println(" new thread  in run start " + i + " ");
				
				try {
					Thread.sleep(THREAD_TIMEOUT_MS);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
				
				println(" new thread  in run "+i+" ,after sleep "+THREAD_TIMEOUT_MS+" MS" + i + " "+
						getThreadKeyInfo(SemaphoreDemo2.this));
				
				if(SemaphoreDemo2.this.isAlive()){
					println(" new thread  in run "+i+" ,before call interrupt " + i + " "+
							getThreadKeyInfo(SemaphoreDemo2.this));
					SemaphoreDemo2.this.interrupt();
					println(" new thread  in run "+i+" ,after call interrupt " + i + " "+
							getThreadKeyInfo(SemaphoreDemo2.this));
				}
				
			}
		}).start();
		
		boolean hasAcquire= false;
		try {
			// ��ע�⡿�� isAlive() �� getState()���� Thread��ĺ�����
			//�����ͨ�� ExecutorService.execute()���õģ�Thread���״̬������仯
			println("" + i + " in run method: "+getThreadKeyInfo(this));
			if (semaphore.availablePermits() > 0) {
				println("" + i + "�п�λ �� before call semaphore.acquire()");
			} else {
				println("" + i + "�ȴ���û�п�λ  before call semaphore.acquire()");
			}
			
			semaphore.acquire();
			hasAcquire = true;
			println("" + i + "after call semaphore.acquire(), start sleep");
			Thread.sleep(THREAD_BUSINESS_PROCESS_MS);
			println("" + i + "end sleep");
			
		} catch (InterruptedException e) {
			//e.printStackTrace();
			println("" + i + " catch InterruptedException in semaphore.acquire() e:"+e.getMessage());
		}finally{
			if(hasAcquire){
				println("" + i + " hasAcquire==true, call semaphore.release()");
				semaphore.release();
			}
		}
	}
	
	public static String getThreadKeyInfo(Thread t){
		final boolean isAlive = t.isAlive();
		State state = t.getState();
		return " ;isAlive:"+isAlive+" ;state:"+state+" ;";
	}
	
	
	
	private static void ThreadAndSemaphoreDemo() {
		//��ƽ��� ʾ��
		Semaphore semaphore = new Semaphore(2,true);
		for (int i = 0; i < 10; i++) {
			println("thread " + i + " start");
			new SemaphoreDemo2(i, semaphore).start();
		}
	
		////�����־1:
		//����ע������ Semaphore �ٷ��ĵ��ķ��룡��
		//ע�⣬FIFO �����ȻӦ�õ���Щ�����ڵ�ָ���ڲ�ִ�е㡣���ԣ�����ĳ���߳�������һ���̵߳�����
		//acquire������ȴ�ڸ��߳�֮�󵽴�����㣬���Ҵӷ�������ʱҲ���ơ�
		/*Line 1: tid:1 2016-10-15 23:40:48.972 - thread 0 start
		Line 2: tid:1 2016-10-15 23:40:48.973 - thread 1 start
		Line 3: tid:1 2016-10-15 23:40:48.973 - thread 2 start
		Line 4: tid:1 2016-10-15 23:40:48.973 - thread 3 start
		Line 9: tid:1 2016-10-15 23:40:48.974 - thread 4 start
		Line 13: tid:1 2016-10-15 23:40:48.974 - thread 5 start
		Line 22: tid:1 2016-10-15 23:40:48.975 - thread 6 start
		Line 26: tid:1 2016-10-15 23:40:48.977 - thread 7 start
		Line 35: tid:1 2016-10-15 23:40:48.977 - thread 8 start
		Line 40: tid:1 2016-10-15 23:40:48.982 - thread 9 start
		
		Line 5: tid:10 2016-10-15 23:40:48.974 - 2 in run method: isAlive:true ; state:RUNNABLE
		Line 6: tid:8 2016-10-15 23:40:48.974 - 0 in run method: isAlive:true ; state:RUNNABLE
		Line 7: tid:9 2016-10-15 23:40:48.974 - 1 in run method: isAlive:true ; state:RUNNABLE
		Line 14: tid:11 2016-10-15 23:40:48.975 - 3 in run method: isAlive:true ; state:RUNNABLE
		Line 21: tid:13 2016-10-15 23:40:48.976 - 5 in run method: isAlive:true ; state:RUNNABLE
		Line 25: tid:12 2016-10-15 23:40:48.976 - 4 in run method: isAlive:true ; state:RUNNABLE
		Line 30: tid:14 2016-10-15 23:40:48.977 - 6 in run method: isAlive:true ; state:RUNNABLE
		Line 31: tid:15 2016-10-15 23:40:48.977 - 7 in run method: isAlive:true ; state:RUNNABLE
		Line 43: tid:16 2016-10-15 23:40:48.982 - 8 in run method: isAlive:true ; state:RUNNABLE
		Line 45: tid:17 2016-10-15 23:40:48.982 - 9 in run method: isAlive:true ; state:RUNNABLE
		
		Line 11: tid:10 2016-10-15 23:40:48.974 - 2��ÿ�λ
		Line 12: tid:9 2016-10-15 23:40:48.974 - 1��ÿ�λ
		Line 18: tid:11 2016-10-15 23:40:48.976 - 3��ÿ�λ
		Line 20: tid:8 2016-10-15 23:40:48.976 - 0��ÿ�λ
		Line 29: tid:12 2016-10-15 23:40:48.977 - 4��ÿ�λ
		Line 36: tid:14 2016-10-15 23:40:48.981 - 6��ÿ�λ
		Line 37: tid:13 2016-10-15 23:40:48.977 - 5��ÿ�λ
		Line 41: tid:15 2016-10-15 23:40:48.982 - 7��ÿ�λ
		Line 46: tid:16 2016-10-15 23:40:48.983 - 8��ÿ�λ
		Line 49: tid:17 2016-10-15 23:40:48.983 - 9��ÿ�λ*/
		
		
		//����ʾ��2�� ÿ���߳��趨һ����ʱʱ�䣬
		/*Line 19: tid:12 2016-10-16 06:56:51.187 -  new thread  in run start 1 
		Line 26: tid:20 2016-10-16 06:56:51.188 -  new thread  in run start 4 
		Line 27: tid:19 2016-10-16 06:56:51.188 -  new thread  in run start 6 
		Line 32: tid:25 2016-10-16 06:56:51.189 -  new thread  in run start 5 
		Line 33: tid:10 2016-10-16 06:56:51.190 -  new thread  in run start 0 
		Line 34: tid:14 2016-10-16 06:56:51.190 -  new thread  in run start 3 
		Line 35: tid:18 2016-10-16 06:56:51.190 -  new thread  in run start 2 
		Line 38: tid:27 2016-10-16 06:56:51.190 -  new thread  in run start 7 
		Line 40: tid:28 2016-10-16 06:56:51.191 -  new thread  in run start 8 
		Line 42: tid:26 2016-10-16 06:56:51.191 -  new thread  in run start 9 
		Line 51: tid:12 2016-10-16 06:56:51.387 -  new thread  in run 1 ,after sleep 200 MS1  ;isAlive:false ;state:TERMINATED ;
		Line 52: tid:19 2016-10-16 06:56:51.388 -  new thread  in run 6 ,after sleep 200 MS6  ;isAlive:true ;state:TIMED_WAITING ;
		Line 53: tid:20 2016-10-16 06:56:51.388 -  new thread  in run 4 ,after sleep 200 MS4  ;isAlive:true ;state:TIMED_WAITING ;
		Line 54: tid:25 2016-10-16 06:56:51.389 -  new thread  in run 5 ,after sleep 200 MS5  ;isAlive:true ;state:WAITING ;
		Line 55: tid:14 2016-10-16 06:56:51.390 -  new thread  in run 3 ,after sleep 200 MS3  ;isAlive:false ;state:TERMINATED ;
		Line 56: tid:10 2016-10-16 06:56:51.390 -  new thread  in run 0 ,after sleep 200 MS0  ;isAlive:false ;state:TERMINATED ;
		Line 57: tid:28 2016-10-16 06:56:51.391 -  new thread  in run 8 ,after sleep 200 MS8  ;isAlive:true ;state:WAITING ;
		Line 58: tid:27 2016-10-16 06:56:51.391 -  new thread  in run 7 ,after sleep 200 MS7  ;isAlive:true ;state:WAITING ;
		Line 59: tid:18 2016-10-16 06:56:51.392 -  new thread  in run 2 ,after sleep 200 MS2  ;isAlive:false ;state:TERMINATED ;
		Line 60: tid:26 2016-10-16 06:56:51.392 -  new thread  in run 9 ,after sleep 200 MS9  ;isAlive:true ;state:WAITING ;
		*/
		
	}
	
	private static void println(String msg) {
		U.info(msg,true);
	}

}