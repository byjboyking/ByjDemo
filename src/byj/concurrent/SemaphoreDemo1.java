package byj.concurrent;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Semaphore;

import byj.util.U;

/**
 * Semaphore是用来管理一个资源池的工具，可以看成是个通行证， 线程要想从资源池拿到资源必须先拿到通行证，
 * 如果线程暂时拿不到通行证，线程就会被阻断进入等待状态。
 * 
 * Semaphore 基本示例 （ExecutorService 和 Semaphore 结合使用） 20161016
 */
public class SemaphoreDemo1 extends Thread {

	public static void main(String[] args) {
		
		int i2 = (int) Math.random() * 10000; //0
		int i1 = (int) (Math.random() * 10000); //4436,  0~10000
		U.info("i1:"+i1+"; i2:"+i2);
		
		
		ExecutorServiceAndSemaphoreDemo();
	}

	private int i;
	private Semaphore semaphore;
	private List<Integer> list;

	public SemaphoreDemo1(int i, Semaphore semaphore,List<Integer> list) {
		this.i = i;
		this.semaphore = semaphore;
		this.list=list;
	}

	public void run() {

		final boolean isAlive = this.isAlive();
		State state = this.getState();
		// 注意： isAlive() 和 getState()都是 Thread类的函数，
		// 如果是通过 ExecutorService.execute()调用的，Thread类的状态都不会变化
		U.info("" + i + " in run method: isAlive:" + isAlive + " ; state:" + state, true);
		if (semaphore.availablePermits() > 0) {
			U.info("" + i + "有空位 ： ", true);
		} else {
			U.info("" + i + "等待，没有空位 ", true);
		}
		try {
			
			synchronized (list) {
				list.add(i);
			}
			
			semaphore.acquire();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		U.info("" + i + "获得空位", true);
		try {
			Thread.sleep((int) (Math.random() * 1000));
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		U.info("" + i + "使用完毕, list:"+list, true);
		semaphore.release();
	}

	private static void ExecutorServiceAndSemaphoreDemo() {

		// 等同于调用 new Semaphore(2,false); Semaphore(int permits, boolean fair),
		//如果希望 First-in, first-out, 则初始化为公平信号量   new Semaphore(2,true)
		Semaphore semaphore = new Semaphore(2);
		List<Integer> list =new ArrayList<Integer>();
		ExecutorService service = Executors.newCachedThreadPool();
		for (int i = 0; i < 10; i++) {
			// 注意：execute(java.lang.Runnable)， 所以任何 Thread类的状态都不会有变化！
			service.execute(new SemaphoreDemo1(i, semaphore,list));
		}
		service.shutdown();

		// 已经验证过， new Semaphore(2,true) 场景下，是一个公平信号量，
		//多个线程的调度顺序是 根据 First-in, first-out 来的。
		//注意：日志打印在同样一个毫秒时间点，先后顺序不是固定的。 所以上面的例子通过对list 加锁来进行验证
		
	
	}
}