package byj.concurrent;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;

import byj.util.U;


//CyclicBarrier
//�����ͳ�����ʹ�ò��衿��
//���ͳ��������ɸ��̣߳�m����Эͬ���һ������ ÿ�����߳����������󣬴��ڵȴ�״̬�� ���������̶߳��������������
//����һ��action ���ռ������̵߳������Ϣ���� ����ͻ��ܡ�
//���裺
//1. ��ʼ�� CyclicBarrier���� ������ - parties �����̸߳��� m��barrierAction: �������߳���ɺ󴥷��Ķ��� ��
//2. �������е����̣߳� ���߳�Ҫִ����ʱ������ CyclicBarrier.await(), ��ʱ���̴߳�������״̬
//3. �����һ�����̵߳��� CyclicBarrier.await(),  ��ʱ �����һ�����߳��д��� barrierAction.
//4. barrierAction ִ����ɺ� �������߳� ����ִ�С�

/**
 * java.util.concurrent.CyclicBarrier


A synchronization aid that allows a set of threads to all wait for each other to reach a common barrier point. 
CyclicBarriers are useful in programs involving a fixed sized party of threads that must occasionally
 wait for each other. The barrier is called cyclic because it can be re-used after the waiting threads are released. 

�������ϵ� (common barrier point)

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
һ��ͬ�������࣬������һ���̻߳���ȴ���ֱ������ĳ���������ϵ� (common barrier point)�����漰һ��̶���С���̵߳ĳ����У�
��Щ�̱߳��벻ʱ�ػ���ȴ�����ʱ CyclicBarrier �����á���Ϊ�� barrier ���ͷŵȴ��̺߳�������ã����Գ���Ϊѭ�� �� barrier��

CyclicBarrier ֧��һ����ѡ�� Runnable �����һ���߳��е����һ���̵߳���֮�󣨵����ͷ������߳�֮ǰ����
������ֻ��ÿ�����ϵ�����һ�Ρ����ڼ������в����߳�֮ǰ���¹���״̬�������ϲ��� �����á�

ʾ���÷���������һ���ڲ��зֽ������ʹ�� barrier �����ӣ�

 
����������У�ÿ�� worker �̴߳�������һ�У��ڴ��������е���֮ǰ�����߳̽�һֱ�����ϴ��ȴ������������е���֮��
��ִ�����ṩ�� Runnable ���ϲ��������ϲ���Щ�С�����ϲ���ȷ���Ѿ��ҵ���һ�������������ô done() ������ true��
���е� worker �̶߳�����ֹ��
������ϲ�����ִ��ʱ����������������̣߳����߳����е��κ��߳��ڻ���ͷ�ʱ����ִ�иò�����Ϊ����˲�����
ÿ�ε��� await() ���������ܵ������ϴ����̵߳�������Ȼ��������ѡ���ĸ��߳�Ӧ��ִ�����ϲ��������磺

  //if (barrier.await() == 0) {
     // log the completion of this iteration
  // }
����ʧ�ܵ�ͬ�����ԣ�CyclicBarrier ʹ����һ��Ҫôȫ��Ҫôȫ�� (all-or-none) ���ƻ�ģʽ�������Ϊ�жϡ�ʧ�ܻ��߳�ʱ��ԭ��
�����̹߳�����뿪�����ϵ㣬��ô�ڸ����ϵ�ȴ������������߳�Ҳ��ͨ�� BrokenBarrierException��������Ǽ���ͬʱ���жϣ�
���� InterruptedException���Է����ķ�ʽ�뿪��

�ڴ�һ����Ч�����߳��е��� await() ֮ǰ�Ĳ��� happen-before ��Щ�����ϲ�����һ���ݵĲ������������� happen-before 
�����ڴ���һ���߳��ж�Ӧ await() �ɹ����صĲ�����

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
 * ѭ��������
 * ������˵������CyclicBarrier ����һ�� �����ߡ� �ȸ���һ�� partiesֵ�����磺3����
 * Ȼ������3���̣߳� ÿ���߳�����ʱ join�����̡߳� ����һ�� CyclicBarrier.await(), ���������״̬�� һ��3���̶߳������� CyclicBarrier.await();
 * ��ᴥ��CyclicBarrier �� barrierAction�������캯���ĵڶ����������� Ȼ�����е��߳� ��������״̬�ָ�����ʼִ�С�
 * 
 * ��ʵ�������գ��е����������ߣ�ÿ���˶�Ա�ߵ�������ǰ�� ���°�ť����ʾ�Ѿ�׼���á� 
 * һ�����һ���˶�Ա���°�ť�� �������˶�Ա��׼�����ˣ� ��ʱ�����˶�Ա�Ϳ����ˡ�����
 * 
 */
public class CyclicBarrierDemo {
	final int N;
	final float[][] data;
	final CyclicBarrier barrier;
	private static Random sRand = new Random(System.currentTimeMillis());

	// ����һ��x���ڵ������
	public static int generateIntRandom() {
		return sRand.nextInt(200);
	}

	class Worker implements Runnable {
		int myRow;

		Worker(int row) {
			myRow = row;
		}

		// ģ�⴦�������ݣ�˯�� һ�������������
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