package byj.concurrent;

import byj.util.U;

//��notify�� notifyAll �������  20150402��
//�ο��ĵ���
//��̸notify��notifyAll���������ͬ
//http://www.iflym.com/index.php/code/201208190001.html
//
//
// //nofify()
// * ֪ͨ��Щ���ܵȴ��ö���������̣߳�����ж���̵߳ȴ��ö�����ô�̹߳滮������ѡ������һ��������֪ͨ���������̼߳������ֵȴ���״̬��
// * ���û���κζ���ȴ��ö�����ônotify() �Ͳ����������ˡ�
// 
// * �ڵ���notify()����֮ǰ���̱߳����øö���Ķ��󼶱����������;����������ˡ�
// * ��wait() ��һ�����ǣ�����notify()��������ʱ�ͷ�����
// * �������notify()ʱ��û�г��к��ʵĶ���������ô ���׳��Ƿ��ļ�����״̬����������쳣������ʱ���쳣����˲���Ҫtry catch�Ľṹ��
// * ���û�ж����wait������ô���ǲ������õ�
//
// notifyAll()
// * ���������notify()�Ĺ�����ʽ��һ���ģ�����֪ͨ���ǵȴ��ö���������̣߳���������֪ͨһ���߳�
// * ����ȥ����֪ͨ���ĸ��ȴ����̣߳����Ǽ򵥵�֪ͨȫ���Ϳ����ˡ�
// * ���ʵ����ֻ��һ���߳��ܹ�ʵ�������ã���ô������֪ͨ����һ���˷ѡ��˷��˴���������Դ�ˡ�
// * �����֪������notifyALL������notify�ķ�������ô��notifyAll��������֤����������֤����İ�ȫ����
// * ��Ȼ���е��̶߳���֪ͨ�ˡ�������Щ�̶߳�����о�������ֻ����һ���̳߳ɹ���ȡ������������߳�û��ִ�����֮ǰ��
//   �������߳̾ͱ���ȴ��ˣ�ֻ�����ﲻ��Ҫ��notifyAll֪ͨ�ˣ���Ϊ�Ѿ�notifyAll�ˣ�ֻ���ȡ���ˣ�-- ��ϸ���������demo��
//
////���ܽ᡿�� ��wait���̣߳���Ҫ�������еĻ�������������2��������
//1.�������߳�notify��notifyAll�ˣ����ҵ�ǰ�̱߳�֪ͨ����
//2.�����������߳̽������������ɹ���ȡ������
//2��������ȱһ���ɡ���ʵ��ʵ�ֲ��棬notify��notifyAll���ﵽ��ͬ��Ч������ֻ����һ���̼߳������С�
//��notifyAll��ȥ�ˣ��߳���������֪ͨ�����̵߳ı�Ҫ����Ϊ�Ѿ�֪ͨ���ˡ�
//
//
////�Բ����ϲ˵ĳ��������� ����Ȼ������ȫ��Ӧ����
//�ò������˺Ͷ����ӵ����������ʦһ��ֻ������һ���ˣ����������ڴ��ڷ�һ�̲ˣ�Ȼ���壨 notify��֪ͨ һ������Ա�Ϳ����ˡ�
//�����ʦ�����˲˶��ȷŵ��Լ�����̨�ϣ�Ȼ��һ���Ӷ��˺ܶ��̲˵����ڣ���ô����Ҫʹ�����壨notifyAll����
//�����еķ���Ա�����������ܲ������������õ�һ�̲ˡ�


//��wait&notify �ܽ� 20161229��
//notify �� ĳ���̵߳�wait ��һ��������ִ�С� ֻ�ж�Ӧ�� ����ͬ���鶼û��ִ���ˣ��Żᱻ���ȡ�
//���������ӣ� ֻ��Thread2 �˳�ͬ����� Thread1�� wait �Ż� �ָ�ִ�С�
//
//Thread1��
//wait
//
//Thread2:
//notify
//sleep

public class NotifyNotifyAllDemo {

	public static void main(String[] args) {
		Thread[] rs = new Thread[5];
		for (int i = 0; i < 5; i++) {
			rs[i] = new Thread(new R(i));
		}
		
		U.info("���߳��� �������߳�  ��ʼ...",true);
		for (Thread r : rs) {
			r.start();
		}
		U.info("���߳��� �������߳�  ����...",true);
		
		try {
			Thread.sleep(200);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		synchronized (obj) {
			U.info("���߳��� notifyAll ��ʼ",true);
			obj.notifyAll();
			U.info("���߳��� notifyAll ����",true);
			
			/*U.info("���߳��� notify ��ʼ");
			obj.notify();
			U.info("���߳��� notify ����");*/
		}
		
		

		//notifyAll ���ʾ����
//		07:11:02.595 - [1,main,Alive,RUNNABLE]-���߳��� �������߳�  ��ʼ...
//		07:11:02.596 - [1,main,Alive,RUNNABLE]-���߳��� �������߳�  ����...
//		07:11:02.596 - [9,Thread-0,Alive,RUNNABLE]-���߳�->  0 �ȴ���
//		07:11:02.596 - [13,Thread-4,Alive,RUNNABLE]-���߳�->  4 �ȴ���
//		07:11:02.596 - [11,Thread-2,Alive,RUNNABLE]-���߳�->  2 �ȴ���
//		07:11:02.596 - [12,Thread-3,Alive,RUNNABLE]-���߳�->  3 �ȴ���
//		07:11:02.597 - [10,Thread-1,Alive,RUNNABLE]-���߳�->  1 �ȴ���
//		07:11:02.797 - [1,main,Alive,RUNNABLE]-���߳��� notifyAll ��ʼ
//		07:11:02.797 - [1,main,Alive,RUNNABLE]-���߳��� notifyAll ����
//		07:11:02.798 - [10,Thread-1,Alive,RUNNABLE]-���߳�->  1 ��������
//		07:11:03.000 - [12,Thread-3,Alive,RUNNABLE]-���߳�->  3 ��������
//		07:11:03.202 - [11,Thread-2,Alive,RUNNABLE]-���߳�->  2 ��������
//		07:11:03.402 - [13,Thread-4,Alive,RUNNABLE]-���߳�->  4 ��������
//		07:11:03.603 - [9,Thread-0,Alive,RUNNABLE]-���߳�->  0 ��������

	}

	private static final Object obj = new Object();

	static class R implements Runnable {
		int i;

		R(int i) {
			this.i = i;
		}

		public void run() {
			try {
				synchronized (obj) {
					U.info("���߳�->  " + i + " �ȴ���",true);
					obj.wait();
					U.info("���߳�->  " + i + " ��������",true);
					Thread.sleep(200);
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}
}
