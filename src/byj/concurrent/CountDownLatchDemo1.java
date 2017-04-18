package byj.concurrent;

import java.util.concurrent.*;

import byj.util.U;

import java.util.*;

//CountDownLatch - ���� ����������˨��: ��JDK1.5 ���룩
//һ��ͬ�������࣬������һ�������̴߳��ڵȴ�״̬���������������߳�ȫ��ִ����Ϻ� �ټ���ִ�С�

//�����ͳ�����ʹ�ò��衿��
//���ͳ���: ��һ���̣߳�m���� �ȴ��ڶ����̣߳�n���� ȫ��ִ����ɺ��ټ���ִ�С�
//
//���裺
//1. ��ʼ��һ�� CountDownLatch�� counter Ϊ n��
//2. ��һ���߳������� ���� CountDownLatch ��await(),��������״̬
//3. �ڶ����߳̿�ʼִ�У� ÿִ����һ���� ���� CountDownLatch ��countDown()
//4. ���ڶ����߳� ȫ��ִ����ɣ��� ������ n�� countDown()��  (��ʱcount==0)�� ��һ���߳�ȫ����ʼ����ִ��


//���ͳ���1����count����Ϊ1����Ϊһ�� on/off�����š� 
//�����߳� ����await(),��������״̬��
//������ countDown()�������̶߳���ʼ����ִ�С�

//���ͳ���2:1���߳� �ȴ����������߳�ִ����Ϻ��ټ���ִ�С�
//���߳����� �������̺߳� ���������̶߳�ִ����Ϻ� �ٽ�������ִ�С�

//count down ����ʱ
//Latch ����������

//CountDownLatch java doc ȫ�����������ˣ���

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

//ʵ��1-  100�������̣߳�TaskPortion����ִ����ɺ��������ɸ� WaitingTask ִ�У���Դjava���˼�룩


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
			U.info("WaitingTask->run()->�����߳� ����ִ��-> Latch barrier passed for " + this, true);
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
			//���� CountDownLatch.await(), ��10��WaitingTask ������ ����״̬��
			exec.execute(new WaitingTask(latch));
		}
		for (int i = 0; i < SIZE; i++){
			//ÿ�� TaskPortion ��run Ҫִ����ʱ�� ���� CountDownLatch.countDown();
			//��count ��Ϊ0ʱ�� ǰ��10�� ��������״̬��WaitingTask �����ִ�С�
			exec.execute(new TaskPortion(latch));
		}
		System.out.println("Launched all tasks");
		exec.shutdown(); // Quit when all tasks complete
	}
} /* (Execute to see output) */// :~
