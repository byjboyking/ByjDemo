package byj.concurrent;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Semaphore;

import byj.util.U;

/**
 * Semaphore����������һ����Դ�صĹ��ߣ����Կ����Ǹ�ͨ��֤�� �߳�Ҫ�����Դ���õ���Դ�������õ�ͨ��֤��
 * ����߳���ʱ�ò���ͨ��֤���߳̾ͻᱻ��Ͻ���ȴ�״̬��
 * 
 * Semaphore ����ʾ�� ��ExecutorService �� Semaphore ���ʹ�ã� 20161016
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
		// ע�⣺ isAlive() �� getState()���� Thread��ĺ�����
		// �����ͨ�� ExecutorService.execute()���õģ�Thread���״̬������仯
		U.info("" + i + " in run method: isAlive:" + isAlive + " ; state:" + state, true);
		if (semaphore.availablePermits() > 0) {
			U.info("" + i + "�п�λ �� ", true);
		} else {
			U.info("" + i + "�ȴ���û�п�λ ", true);
		}
		try {
			
			synchronized (list) {
				list.add(i);
			}
			
			semaphore.acquire();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		U.info("" + i + "��ÿ�λ", true);
		try {
			Thread.sleep((int) (Math.random() * 1000));
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		U.info("" + i + "ʹ�����, list:"+list, true);
		semaphore.release();
	}

	private static void ExecutorServiceAndSemaphoreDemo() {

		// ��ͬ�ڵ��� new Semaphore(2,false); Semaphore(int permits, boolean fair),
		//���ϣ�� First-in, first-out, ���ʼ��Ϊ��ƽ�ź���   new Semaphore(2,true)
		Semaphore semaphore = new Semaphore(2);
		List<Integer> list =new ArrayList<Integer>();
		ExecutorService service = Executors.newCachedThreadPool();
		for (int i = 0; i < 10; i++) {
			// ע�⣺execute(java.lang.Runnable)�� �����κ� Thread���״̬�������б仯��
			service.execute(new SemaphoreDemo1(i, semaphore,list));
		}
		service.shutdown();

		// �Ѿ���֤���� new Semaphore(2,true) �����£���һ����ƽ�ź�����
		//����̵߳ĵ���˳���� ���� First-in, first-out ���ġ�
		//ע�⣺��־��ӡ��ͬ��һ������ʱ��㣬�Ⱥ�˳���ǹ̶��ġ� �������������ͨ����list ������������֤
		
	
	}
}