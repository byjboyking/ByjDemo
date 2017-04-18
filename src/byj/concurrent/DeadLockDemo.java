package byj.concurrent;

import byj.util.U;

//��һ������ wait ��notify ʹ�ò������������ 20150402��

//˵��һ��notify��notifyAll������
//
//����sub��main��������������������˵�������sub�������̺߳Ͷ������main�����Ķ��ᴦ������״̬��
//����ȴ�һ���������е������߳����������ǣ����ϴ���ʹ����notify���л��ѣ�
//notifyֻ�ܻ���һ���̣߳������ȴ����߳���Ȼ����wait״̬�����������sub�������߳�ִ��������е��̶߳����ڵȴ�״̬����
//isSub=false�ˣ���ʱ���ѵ���һ��sub���������̣߳�
//��ôwhileѭ������true������߳�Ҳ�ᴦ�ڵȴ�״̬��֮�����е��̴߳��ڵȴ�״̬��û�����е��߳����������ǣ���ʱ�Ͳ�����������
//���ʹ��notifyAll()�������������ڵȴ��������̣߳�
//��ô���е��̶߳��ᴦ������ǰ��׼��״̬������sub����ִ����󣬻��������еȴ�������״̬����ô��ʹ�ٴλ���һ��sub���������̣߳�
//��ô���߳��ٴδ��ڵȴ�״̬��
//�����������߳̿��Ի�ø�������������״̬������notify������������������������������Լ��ĳ�����ƣ�ȷ����������������
//notifyAll�����̵߳İ�ȫ���ѷ�����
//
//�Թ����������ϴ��� ֻ��Ҫ��sub��main�����еĲ����ĳ�this.notifyAll()���ɡ�

public class DeadLockDemo {
	
	public static void main(String[] args) {
		// U.info("lock");

		final OutTurn ot = new OutTurn();
		for (int j = 0; j < 100; j++) {
			new Thread(new Runnable() {
				public void run() {
					// try {
					// Thread.sleep(10);
					// } catch (InterruptedException e) {
					// e.printStackTrace();
					// }
					for (int i = 0; i < 5; i++) {
						ot.sub();
					}
				}
			}).start();

			new Thread(new Runnable() {
				public void run() {
					// try {
					// Thread.sleep(10);
					// } catch (InterruptedException e) {
					// e.printStackTrace();
					// }
					for (int i = 0; i < 5; i++) {
						ot.main();
					}
				}
			}).start();
		}

	}
}

class OutTurn {
	private boolean isSub = true;
	private int count = 0;

	public synchronized void sub() {
		try {
			while (!isSub) {
				U.info("sub in while. before wait " + count + " ;isSub:" + isSub,true);
				this.wait();

				U.info("sub in while. after wait " + count + " ;isSub:" + isSub,true);
			}
			U.info("sub ---- " + count,true);
			isSub = false;
			this.notify();
			// this.notifyAll();
		} catch (Exception e) {
			e.printStackTrace();
		}
		count++;
	}

	public synchronized void main() {
		try {
			while (isSub) {
				U.info("main in while. before wait. " + count + " ;isSub:" + isSub,true);
				this.wait();

				U.info("main in while. after wait. " + count + " ;isSub:" + isSub,true);
			}
			U.info("main (((((((((((( " + count,true);
			isSub = true;
			this.notify();
			// this.notifyAll();
		} catch (Exception e) {
			e.printStackTrace();
		}
		count++;
		
		//����ʱ�����

//sub in while. before wait 127 ;isSub:false
//sub in while. after wait 127 ;isSub:false
//sub in while. before wait 127 ;isSub:false
//main in while. after wait. 127 ;isSub:false
//main (((((((((((( 127
//main in while. before wait. 128 ;isSub:true
//sub in while. after wait 128 ;isSub:true
//sub ---- 128
//sub in while. before wait 129 ;isSub:false
//sub in while. after wait 129 ;isSub:false
//sub in while. before wait 129 ;isSub:false
//sub in while. after wait 129 ;isSub:false
//sub in while. before wait 129 ;isSub:false
//sub in while. after wait 129 ;isSub:false
//sub in while. before wait 129 ;isSub:false
//sub in while. after wait 129 ;isSub:false
//sub in while. before wait 129 ;isSub:false

	}
}
