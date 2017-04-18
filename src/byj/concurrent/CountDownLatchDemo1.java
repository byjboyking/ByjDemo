package byj.concurrent;

import java.util.concurrent.*;

import byj.util.U;

import java.util.*;

//CountDownLatch - 闭锁 （倒计数门栓）: （JDK1.5 引入）
//一个同步辅助类，可以让一个或多个线程处于等待状态，而在其他若干线程全部执行完毕后 再继续执行。

//【典型场景和使用步骤】：
//典型场景: 第一组线程（m个） 等待第二组线程（n个） 全部执行完成后再继续执行。
//
//步骤：
//1. 初始化一个 CountDownLatch， counter 为 n。
//2. 第一组线程启动， 调用 CountDownLatch 的await(),处于阻塞状态
//3. 第二组线程开始执行， 每执行完一个， 调用 CountDownLatch 的countDown()
//4. 当第二组线程 全部执行完成，即 调用了 n次 countDown()后  (此时count==0)， 第一组线程全部开始继续执行


//典型场景1：将count设置为1，作为一个 on/off的门闩。 
//若干线程 调用await(),处于阻塞状态，
//当调用 countDown()后，所有线程都开始继续执行。

//典型场景2:1个线程 等待其他若干线程执行完毕后再继续执行。
//主线程启动 若干子线程后， 等所有子线程都执行完毕后， 再接着往下执行。

//count down 倒计时
//Latch 闭锁，门闩

//CountDownLatch java doc 全部完整看过了！！

//void java.util.concurrent.CountDownLatch.await() throws InterruptedException
//Causes the current thread to wait until the latch has counted down to zero, unless the thread is interrupted. 
//If the current count is zero then this method returns immediately. 
//If the current count is greater than zero then the current thread becomes disabled for thread scheduling purposes
//and lies dormant until one of two things happen: 
//The count reaches zero due to invocations of the countDown method; or 
//Some other thread interrupts the current thread. 
//
////void java.util.concurrent.CountDownLatch.countDown()
//Decrements the count of the latch, releasing all waiting threads if the count reaches zero. 
//If the current count is greater than zero then it is decremented. 
//If the new count is zero then all waiting threads are re-enabled for thread scheduling purposes. 
//If the current count equals zero then nothing happens.
//

//实例1-  100个工作线程（TaskPortion）都执行完成后，再让若干个 WaitingTask 执行（来源java编程思想）


// Performs some portion of a task:
class TaskPortion implements Runnable {
	private static int counter = 0;
	private final int id = counter++;
	private static Random rand = new Random(47);
	private final CountDownLatch latch;

	TaskPortion(CountDownLatch latch) {
		this.latch = latch;
	}

	public void run() {
		try {
			doWork();
			latch.countDown();
		} catch (InterruptedException ex) {
			// Acceptable way to exit
		}
	}

	public void doWork() throws InterruptedException {
		TimeUnit.MILLISECONDS.sleep(rand.nextInt(2000));
		U.info("TaskPortion->doWork()->"+this + "completed",true);
	}

	public String toString() {
		return String.format("%1$-3d ", id);
	}
}

// Waits on the CountDownLatch:
class WaitingTask implements Runnable {
	private static int counter = 0;
	private final int id = counter++;
	private final CountDownLatch latch;

	WaitingTask(CountDownLatch latch) {
		this.latch = latch;
	}

	public void run() {
		try {
			U.info("start call latch.await() in WaitingTask " + this, true);
			latch.await();
			U.info("WaitingTask->run()->阻塞线程 继续执行-> Latch barrier passed for " + this, true);
		} catch (InterruptedException ex) {
			System.out.println(this + " interrupted");
		}
	}

	public String toString() {
		return String.format("WaitingTask %1$-3d ", id);
	}
}

public class CountDownLatchDemo1 {
	static final int SIZE = 10;

	public static void main(String[] args) throws Exception {
		ExecutorService exec = Executors.newCachedThreadPool();
		// All must share a single CountDownLatch object:
		CountDownLatch latch = new CountDownLatch(SIZE);
		for (int i = 0; i < SIZE; i++){
			//调用 CountDownLatch.await(), 让10个WaitingTask 都处于 阻塞状态。
			exec.execute(new WaitingTask(latch));
		}
		for (int i = 0; i < SIZE; i++){
			//每个 TaskPortion 的run 要执行完时， 调用 CountDownLatch.countDown();
			//当count 降为0时， 前面10个 处于阻塞状态的WaitingTask 会继续执行。
			exec.execute(new TaskPortion(latch));
		}
		System.out.println("Launched all tasks");
		exec.shutdown(); // Quit when all tasks complete
	}
} /* (Execute to see output) */// :~
