package byj.concurrent;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.CountDownLatch;

import byj.util.U;

//���̵߳ȴ��������߳̽������ټ���ִ�е�ʾ������  20161023
//��ʽ1�� ��ѯ��ÿ100ms ����� Thread1��2��State��
//��ʽ2��ͨ��join()����ʵ�֡�
//��ʽ3��ͨ�� CountDownLatch ��ʵ��  - 
//1. CountDownLatch ��count ��ʼ��Ϊ2��
//2. ���߳��ȵ���CountDownLatch.await() ��������״̬�� 
//3. �������߳���ɺ󣬵��� CountDownLatch.countDown() ���� count ��Ϊ0ʱ�����̴߳�����״̬�ָ�������ִ�С�
//
public class TwoThreadDemo {
	public static class Operate implements Comparable<Operate> {
		// ����ִ��ʱ�䣨��Ϊ��λ��
		public int mDuration = 0;
		public int mId = 0;
		public long mExecutedThreadId = -1;
		public long mEndSystemTime = 0;

		public Operate(int id, int duration) {
			mId = id;
			mDuration = duration;
		}

		@Override
		public int compareTo(Operate o) {
			// 1. min -> max
			return mDuration > o.mDuration ? 1 : (mDuration == o.mDuration ? 0 : -1);
		}

		@Override
		public String toString() {
			return "[mId:" + mId + ",mDuration:" + mDuration + "]";
		}
	}

	public static class Thread1 extends Thread {
		public List<Operate> mOperateList = new ArrayList<Operate>();

		@Override
		public void run() {
			for (Operate o : mOperateList) {
				try {
					// ģ����������
					Thread.sleep(o.mDuration * 100);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
					TwoThreadDemo.err("Thread1->run()->InterruptedException");
				}
				TwoThreadDemo.err("Thread1->run()->prepare to synchronized block", true);
				synchronized (sLock) {
					TwoThreadDemo.err("Thread1->run()->enter to synchronized block", true);
					o.mExecutedThreadId = Thread.currentThread().getId();
					o.mEndSystemTime = System.currentTimeMillis();
				}
				TwoThreadDemo.err("Thread1->run()->left to synchronized block", true);
			}

			err("Thread1-> start call sLatch.countDown()");
			sLatch.countDown();
			err("Thread1-> end call sLatch.countDown()");
		}
	}

	public static class Thread2 extends Thread {
		public List<Operate> mOperateList = new ArrayList<Operate>();

		@Override
		public void run() {
			for (Operate o : mOperateList) {
				try {
					// ģ����������
					Thread.sleep(o.mDuration * 100);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
					TwoThreadDemo.err("Thread2->run()->InterruptedException");
				}
				TwoThreadDemo.err("Thread2->run()->prepare to synchronized block", true);
				synchronized (sLock) {
					TwoThreadDemo.err("Thread2->run()->enter to synchronized block", true);
					o.mExecutedThreadId = Thread.currentThread().getId();
					o.mEndSystemTime = System.currentTimeMillis();
				}
				TwoThreadDemo.err("Thread2->run()->left to synchronized block", true);
			}

			err("Thread2-> start call sLatch.countDown()");
			sLatch.countDown();
			err("Thread2-> end call sLatch.countDown()");
		}
	}

	private static Object sLock = new Object();

	private static CountDownLatch sLatch = new CountDownLatch(2);

	public static void main(String[] args) {
		List<Operate> rawList = new ArrayList<Operate>();
		rawList.add(new Operate(1, 1));
		rawList.add(new Operate(2, 3));
		rawList.add(new Operate(3, 5));
		rawList.add(new Operate(4, 2));
		rawList.add(new Operate(5, 6));
		rawList.add(new Operate(6, 2));
		rawList.add(new Operate(7, 4));
		rawList.add(new Operate(8, 8));
		rawList.add(new Operate(9, 7));

		List<List<Operate>> result = divideIntoTwoListByDuration(rawList);
		Thread1 thread1 = new Thread1();
		thread1.mOperateList = result.get(0);

		Thread2 thread2 = new Thread2();
		thread2.mOperateList = result.get(1);

		thread1.start();
		thread2.start();

		// ��ʽ1�� ��ѯ��ÿ100ms ����� Thread1��2��State��
		/*
		 * while(true){ try { Thread.sleep(100); } catch (InterruptedException
		 * e) { // TODO Auto-generated catch block e.printStackTrace();
		 * err("main thread, InterruptedException:"+e); }
		 * 
		 * if(thread1.getState()== State.TERMINATED ){
		 * //err("thread1.getState()== State.TERMINATED"); }
		 * 
		 * if(thread2.getState()== State.TERMINATED ){
		 * //err("thread2.getState()== State.TERMINATED"); }
		 * if(thread1.getState()== State.TERMINATED && thread2.getState()==
		 * State.TERMINATED ){
		 * err("thread1, thread2, are all terminated, break;"); break; } }
		 */

		// ��ʽ2��ͨ��join()����ʵ�֡�
		// ���ε��� thread1.join(); thread2.join(); ����Ҫ���ֵ���˳��
		// ���߳�Ҫ�ȴ� thread1��thread2����ɺ�Ž�������ִ��
		/*
		 * err("main-> thread2.join start"); try { thread2.join(); } catch
		 * (InterruptedException e) { // TODO Auto-generated catch block
		 * e.printStackTrace(); err("main-> thread2.join InterruptedException");
		 * } err("main-> thread2.join end");
		 * 
		 * err("main-> thread1.join start"); try { thread1.join(); } catch
		 * (InterruptedException e) { // TODO Auto-generated catch block
		 * e.printStackTrace(); err("main-> thread1.join InterruptedException");
		 * } err("main-> thread1.join end");
		 */

		// ��ʽ3��ͨ�� CountDownLatch ��ʵ��
		err("main-> start call sLatch.await()");
		try {
			sLatch.await();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			err("main->sLatch.await InterruptedException " + e);
		}
		err("main-> end call sLatch.await(), continue to execute");
		
		//��ʽ3-��ӡ��־
//		06:51:02.544 - main-> start call sLatch.await()
//		06:51:02.626 - [9,Thread-0,Alive,RUNNABLE]-Thread1->run()->prepare to synchronized block
//		06:51:02.627 - [9,Thread-0,Alive,RUNNABLE]-Thread1->run()->enter to synchronized block
//		06:51:02.627 - [9,Thread-0,Alive,RUNNABLE]-Thread1->run()->left to synchronized block
//		06:51:02.726 - [10,Thread-1,Alive,RUNNABLE]-Thread2->run()->prepare to synchronized block
//		06:51:02.726 - [10,Thread-1,Alive,RUNNABLE]-Thread2->run()->enter to synchronized block
//		06:51:02.726 - [10,Thread-1,Alive,RUNNABLE]-Thread2->run()->left to synchronized block
//		06:51:02.827 - [9,Thread-0,Alive,RUNNABLE]-Thread1->run()->prepare to synchronized block
//		06:51:02.827 - [9,Thread-0,Alive,RUNNABLE]-Thread1->run()->enter to synchronized block
//		06:51:02.827 - [9,Thread-0,Alive,RUNNABLE]-Thread1->run()->left to synchronized block
//		06:51:03.027 - [10,Thread-1,Alive,RUNNABLE]-Thread2->run()->prepare to synchronized block
//		06:51:03.027 - [10,Thread-1,Alive,RUNNABLE]-Thread2->run()->enter to synchronized block
//		06:51:03.027 - [10,Thread-1,Alive,RUNNABLE]-Thread2->run()->left to synchronized block
//		06:51:03.229 - [9,Thread-0,Alive,RUNNABLE]-Thread1->run()->prepare to synchronized block
//		06:51:03.229 - [9,Thread-0,Alive,RUNNABLE]-Thread1->run()->enter to synchronized block
//		06:51:03.229 - [9,Thread-0,Alive,RUNNABLE]-Thread1->run()->left to synchronized block
//		06:51:03.527 - [10,Thread-1,Alive,RUNNABLE]-Thread2->run()->prepare to synchronized block
//		06:51:03.527 - [10,Thread-1,Alive,RUNNABLE]-Thread2->run()->enter to synchronized block
//		06:51:03.527 - [10,Thread-1,Alive,RUNNABLE]-Thread2->run()->left to synchronized block
//		06:51:03.830 - [9,Thread-0,Alive,RUNNABLE]-Thread1->run()->prepare to synchronized block
//		06:51:03.830 - [9,Thread-0,Alive,RUNNABLE]-Thread1->run()->enter to synchronized block
//		06:51:03.830 - [9,Thread-0,Alive,RUNNABLE]-Thread1->run()->left to synchronized block
//		06:51:04.227 - [10,Thread-1,Alive,RUNNABLE]-Thread2->run()->prepare to synchronized block
//		06:51:04.227 - [10,Thread-1,Alive,RUNNABLE]-Thread2->run()->enter to synchronized block
//		06:51:04.227 - [10,Thread-1,Alive,RUNNABLE]-Thread2->run()->left to synchronized block
//		06:51:04.228 - Thread2-> start call sLatch.countDown()
//		06:51:04.228 - Thread2-> end call sLatch.countDown()
//		06:51:04.630 - [9,Thread-0,Alive,RUNNABLE]-Thread1->run()->prepare to synchronized block
//		06:51:04.630 - [9,Thread-0,Alive,RUNNABLE]-Thread1->run()->enter to synchronized block
//		06:51:04.631 - [9,Thread-0,Alive,RUNNABLE]-Thread1->run()->left to synchronized block
//		06:51:04.631 - Thread1-> start call sLatch.countDown()
//		06:51:04.631 - Thread1-> end call sLatch.countDown()
//		06:51:04.632 - main-> end call sLatch.await(), continue to execute
	}

	// �� rawList�е�item ��ַŵ� ����list�С� ȷ������ list ��Ԫ�ص� ��duration ������ͬ��
	// TODO: ������ֵ��ʼ���䣬����Ӿ�׼������
	private static List<List<Operate>> divideIntoTwoListByDuration(List<Operate> rawList) {
		List<List<Operate>> result = new ArrayList<List<Operate>>();

		List<Operate> sortList = new ArrayList<Operate>(rawList);
		// err(copyList.toString());
		Collections.sort(sortList);
		// err(copyList.toString());

		List<Operate> list1 = new ArrayList<Operate>();
		List<Operate> list2 = new ArrayList<Operate>();

		int sumDuration1 = 0;
		int sumDuration2 = 0;
		for (Operate o : sortList) {
			if (sumDuration1 > sumDuration2) {
				list2.add(o);
				sumDuration2 += o.mDuration;
			} else {
				list1.add(o);
				sumDuration1 += o.mDuration;
			}
		}

		result.add(list1);
		result.add(list2);

		return result;
	}

	private static void err(String msg) {
		U.info(msg);
	}

	private static void err(String msg, boolean b) {
		U.info(msg, b);
	}
}