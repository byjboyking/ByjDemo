package byj.concurrent;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;

import byj.util.U;


//CyclicBarrier
//【典型场景和使用步骤】：
//典型场景：若干个线程（m个）协同完成一件任务。 每个子线程完成子任务后，处于等待状态。 等所有子线程都处理完子任务后，
//触发一个action 来收集各个线程的完成信息进行 处理和汇总。
//步骤：
//1. 初始化 CyclicBarrier对象 （参数 - parties ：子线程个数 m；barrierAction: 所有子线程完成后触发的动作 ）
//2. 启动所有的子线程， 子线程要执行完时，调用 CyclicBarrier.await(), 此时子线程处于阻塞状态
//3. 当最后一个子线程调用 CyclicBarrier.await(),  此时 在最后一个子线程中触发 barrierAction.
//4. barrierAction 执行完成后， 所有子线程 继续执行。

/**
 * java.util.concurrent.CyclicBarrier


A synchronization aid that allows a set of threads to all wait for each other to reach a common barrier point. 
CyclicBarriers are useful in programs involving a fixed sized party of threads that must occasionally
 wait for each other. The barrier is called cyclic because it can be re-used after the waiting threads are released. 

公共屏障点 (common barrier point)

A CyclicBarrier supports an optional Runnable command that is run once per barrier point, after the last thread
 in the party arrives, but before any threads are released. This barrier action is useful for updating 
 shared-state before any of the parties continue. 

Sample usage: Here is an example of using a barrier in a parallel decomposition design: 
 ...
 
Here, each worker thread processes a row of the matrix then waits at the barrier until all rows have been processed. 
When all rows are processed the supplied Runnable barrier action is executed and merges the rows. 
If the merger determines that a solution has been found then done() will return true and each worker will terminate. 
If the barrier action does not rely on the parties being suspended when it is executed, then any of the threads 
in the party could execute that action when it is released. To facilitate this, each invocation of await 
returns the arrival index of that thread at the barrier. You can then choose which thread should execute
 the barrier action, for example: 
 
 //if (barrier.await() == 0) {
   // log the completion of this iteration
 //  }

The CyclicBarrier uses an all-or-none breakage model for failed synchronization attempts: If a thread leaves
 a barrier point prematurely because of interruption, failure, or timeout, all other threads waiting at that
  barrier point will also leave abnormally via BrokenBarrierException (or InterruptedException if they too
   were interrupted at about the same time). 

Memory consistency effects: Actions in a thread prior to calling await() happen-before actions that are part of
 the barrier action, which in turn happen-before actions following a successful return from the corresponding 
 await() in other threads.
Since:



//apihome.cn
一个同步辅助类，它允许一组线程互相等待，直到到达某个公共屏障点 (common barrier point)。在涉及一组固定大小的线程的程序中，
这些线程必须不时地互相等待，此时 CyclicBarrier 很有用。因为该 barrier 在释放等待线程后可以重用，所以称它为循环 的 barrier。

CyclicBarrier 支持一个可选的 Runnable 命令，在一组线程中的最后一个线程到达之后（但在释放所有线程之前），
该命令只在每个屏障点运行一次。若在继续所有参与线程之前更新共享状态，此屏障操作 很有用。

示例用法：下面是一个在并行分解设计中使用 barrier 的例子：

 
在这个例子中，每个 worker 线程处理矩阵的一行，在处理完所有的行之前，该线程将一直在屏障处等待。处理完所有的行之后，
将执行所提供的 Runnable 屏障操作，并合并这些行。如果合并者确定已经找到了一个解决方案，那么 done() 将返回 true，
所有的 worker 线程都将终止。
如果屏障操作在执行时不依赖于正挂起的线程，则线程组中的任何线程在获得释放时都能执行该操作。为方便此操作，
每次调用 await() 都将返回能到达屏障处的线程的索引。然后，您可以选择哪个线程应该执行屏障操作，例如：

  //if (barrier.await() == 0) {
     // log the completion of this iteration
  // }
对于失败的同步尝试，CyclicBarrier 使用了一种要么全部要么全不 (all-or-none) 的破坏模式：如果因为中断、失败或者超时等原因，
导致线程过早地离开了屏障点，那么在该屏障点等待的其他所有线程也将通过 BrokenBarrierException（如果它们几乎同时被中断，
则用 InterruptedException）以反常的方式离开。

内存一致性效果：线程中调用 await() 之前的操作 happen-before 那些是屏障操作的一部份的操作，后者依次 happen-before 
紧跟在从另一个线程中对应 await() 成功返回的操作。

//await()
int java.util.concurrent.CyclicBarrier.await() throws InterruptedException, BrokenBarrierException


Waits until all parties have invoked await on this barrier. 

If the current thread is not the last to arrive then it is disabled for thread scheduling purposes and lies dormant until one of the following things happens: 
?The last thread arrives; or 
?Some other thread interrupts the current thread; or 
?Some other thread interrupts one of the other waiting threads; or 
?Some other thread times out while waiting for barrier; or 
?Some other thread invokes reset on this barrier. 

If the current thread: 
?has its interrupted status set on entry to this method; or 
?is interrupted while waiting 
then InterruptedException is thrown and the current thread's interrupted status is cleared. 
If the barrier is reset while any thread is waiting, or if the barrier is broken when await is invoked, or while any thread is waiting, then BrokenBarrierException is thrown. 

If any thread is interrupted while waiting, then all other waiting threads will throw BrokenBarrierException and the barrier is placed in the broken state. 

If the current thread is the last thread to arrive, and a non-null barrier action was supplied in the constructor, then the current thread runs the action before allowing the other threads to continue. If an exception occurs during the barrier action then that exception will be propagated in the current thread and the barrier is placed in the broken state.

Returns:the arrival index of the current thread, where index getParties() - 1 indicates the first to arrive and zero indicates the last to arrive

Throws:
	InterruptedException - if the current thread was interrupted while waiting

	BrokenBarrierException - if another thread was interrupted or timed out while the current thread was waiting, or the barrier was reset, or the barrier was broken when await was called, or the barrier action (if present) failed due to an exception

 * 
 * 
 * 循环屏障类
 * 【本例说明】：CyclicBarrier 类似一个 起跑线。 先给定一个 parties值（例如：3）。
 * 然后启动3个线程， 每个线程启动时 join到主线程。 调用一下 CyclicBarrier.await(), 会进入阻塞状态， 一旦3个线程都调用了 CyclicBarrier.await();
 * 则会触发CyclicBarrier 的 barrierAction（即构造函数的第二个参数）， 然后所有的线程 都从阻塞状态恢复，开始执行。
 * 
 * 现实场景对照：有点类似起跑线，每个运动员走到起跑线前， 按下按钮，表示已经准备好。 
 * 一旦最后一个运动员按下按钮， 即所有运动员都准备好了， 此时所有运动员就开跑了。。。
 * 
 */
public class CyclicBarrierDemo {
	final int N;
	final float[][] data;
	final CyclicBarrier barrier;
	private static Random sRand = new Random(System.currentTimeMillis());

	// 生成一个x以内的随机数
	public static int generateIntRandom() {
		return sRand.nextInt(200);
	}

	class Worker implements Runnable {
		int myRow;

		Worker(int row) {
			myRow = row;
		}

		// 模拟处理行数据（睡眠 一个随机毫秒数）
		void processRow(int row) {
			int sleepMillis = generateIntRandom();
			U.info("processRow()->myRow=" + myRow + " sleepMillis=" + sleepMillis + " start sleep", true);
			try {
				Thread.sleep(sleepMillis);
			} catch (InterruptedException e) {
				U.err("processRow()->myRow=" + myRow + " InterruptedException, " + e, true);
				e.printStackTrace();
			}
		}

		public void run() {
			processRow(myRow);

			try {
				U.info("run()->myRow=" + myRow + " start barrier.await()", true);
				int arriveIndex = barrier.await();
				U.info("run()->myRow=" + myRow + " end barrier.await() arriveIndex:" + arriveIndex, true);
			} catch (InterruptedException ex) {
				U.err("run()->myRow=" + myRow + " InterruptedException in barrier.await()", true);
				return;
			} catch (BrokenBarrierException ex) {
				U.err("run()->myRow=" + myRow + " BrokenBarrierException in barrier.await()", true);
				return;
			}
		}
	}

	public void mergeRows() {
		U.info("mergeRows start",true);
		try {
			Thread.sleep(50);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		U.info("mergeRows end",true);
	}

	public CyclicBarrierDemo(float[][] matrix) {
		data = matrix;
		N = matrix.length;
		Runnable barrierAction = new Runnable() {
			public void run() {
				mergeRows();
			}
		};
		barrier = new CyclicBarrier(N, barrierAction);
	}

	public void calculateMatrix() {
		List<Thread> threads = new ArrayList<Thread>(N);
		for (int i = 0; i < N; i++) {
			Thread thread = new Thread(new Worker(i));
			threads.add(thread);
			thread.start();
		}

		// wait until done
		for (int i = 0; i < N; i++) {
			Thread thread = threads.get(i);
			try {
				U.info("calculateMatrix start thread.join(), index=" + i, true);
				thread.join();
				U.info("calculateMatrix end thread.join(), index=" + i, true);
			} catch (InterruptedException e) {
				U.err("calculateMatrix InterruptedException in  thread.join(), index=" + i, true);
				e.printStackTrace();
			}
		}
		
		U.info("calculateMatrix end", true);
	}

	public static void main(String[] args) {
		float[][] matrix = new float[][] { { 5.0f, 3.0f }, { 4.5f, 2.5f }, { 3.2f, 4.4f } };

		CyclicBarrierDemo s = new CyclicBarrierDemo(matrix);
		s.calculateMatrix();
	}
}