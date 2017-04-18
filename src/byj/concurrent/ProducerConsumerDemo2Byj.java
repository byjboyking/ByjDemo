package byj.concurrent;


import java.util.ArrayList;
import java.util.List;

import byj.util.U;

//���Լ�д������ 20161229
//ע�⣬����Ҫ�������ߺ���������Ŀ��ͬ�� δ���ǲ�ͬ�� �˳��ݴ���

//�ο��ԣ�
//java���߳�֮������������ģʽ .
//http://blog.csdn.net/shijinupc/article/details/7250407
//
//Java�߳�(��)���߳�Э��-������/���������� 
//http://blog.csdn.net/ghsau/article/details/7433673#
//
//
//ʵ�ֵĹؼ���
//�����ڴ��е�����ͬ����������ͬ��������wait()�����ĵ��ã�ͬ����֤�˶���ֻ�ܱ�һ���߳�ռ�ã�
//wait��֤�˵��߳��ڵȴ��������ͷ�����ʹ�����������л���������
//��һ�������У���synchonized�����ķ���Ϊͬ��������
//Java����һ��ͬ��ģ��-����������������̶߳Զ����е�ͬ�������ķ��ʣ�����ԭ���ǣ�����ö���Ψһһ��'Կ��'��
//������߳̽������ֻ��ȡ�øö���Կ�׵��̲߳ſ��Է���ͬ�������������߳��ڸö����еȴ���
//ֱ�����߳���wait()�����������Կ�ף������ȴ����߳���ռ��Կ�ף�
//��ռ��Կ�׵��̺߳�ſɵ���ִ�У���û��ȡ��Կ�׵��߳��Ա������ڸö����еȴ��� 
//�ܶ���֮��synchonizedʹ��ֻ��һ���߳��ܽ����ٽ��������
//
//
//ע��:�ڵ���wait����ʱ��������while�ж������ģ�������if����wait����˵���У�Ҳ�Ƽ�ʹ��while��
//��Ϊ��ĳЩ�ض�������£��߳��п��ܱ��ٻ��ѣ�
//ʹ��while��ѭ���������ס�wait��notify�������빤����synchronized�ڲ���������������ֻ���������������á�

public class ProducerConsumerDemo2Byj {

	public static void main(String[] args) {
		Workshop ws = new Workshop();
		Thread tProducer = new Thread(new Producer(ws));
		Thread tConsumer = new Thread(new Consumer(ws));
		tProducer.start();
		tConsumer.start();
	}

	public final static int PRODUCE_NUM = 50;
	public final static int CONSUME_NUM = 50;
	
	private static class Workshop {

		private final static int MAX_SIZE = 3;

		private List<Hamburg> list = new ArrayList<Hamburg>();

		public void produce(Hamburg h) {
			synchronized (list) {
				while (list.size() == MAX_SIZE) {
					try {
						U.info("Workshop->produce-> before  wait", true);
						list.wait();

						U.info("Workshop->produce-> after wait", true);
					} catch (InterruptedException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}

				U.info("Workshop->produce-> h:"+h, true);
				list.add(h);
				//U.info("Workshop->produce-> before  notify", true);
				list.notify();
				//U.info("Workshop->produce-> after  notify", true);
			}
		}

		public Hamburg consume() {
			Hamburg h = null;
			synchronized (list) {
				while (list.size() == 0) {
					try {
						U.info("Workshop->consume-> before  wait", true);
						list.wait();
						U.info("Workshop->consume-> after  wait", true);
					} catch (InterruptedException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}

				if (list.size() > 0) {
					h = list.remove(0);
					U.info("Workshop->consume->h:"+h,true);
				}

				//U.info("Workshop->consume-> before  notify", true);
				list.notify();
				//U.info("Workshop->consume-> after  notify", true);
			}

			return h;
		}
	}

	private static class Producer implements Runnable {

		private Workshop ws;

		public Producer(Workshop ws) {
			this.ws = ws;
		}

		@Override
		public void run() {
			for (int i = 0; i < PRODUCE_NUM; i++) {
				long sleepTime = U.generateIntRandom(50);
				//U.info("Producer->run - i:" + i + " sleepTime:" + sleepTime + " start sleep", true);
				try {
					Thread.sleep(sleepTime);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}

				//U.info("Producer->run - i:" + i + " sleepTime:" + sleepTime + " end sleep", true);
				String name = "hamburg" + i;

				//U.info("Producer->run - i:" + i + " produce start", true);
				ws.produce(new Hamburg(name, i));
				//U.info("Producer->run - i:" + i + " produce end", true);
			}
			
			U.info("Producer->run->������ "+PRODUCE_NUM+"������",true);
		}
	}

	private static class Consumer implements Runnable {
		private Workshop ws;

		public Consumer(Workshop ws) {
			this.ws = ws;
		}

		@Override
		public void run() {
			for (int i = 0; i < CONSUME_NUM; i++) {
				long sleepTime = U.generateIntRandom(50);
				//U.info("Consumer->run - i:" + i + " sleepTime:" + sleepTime + " start sleep", true);
				try {
					Thread.sleep(sleepTime);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				//U.info("Consumer->run - i:" + i + " sleepTime:" + sleepTime + " end sleep", true);
				//U.info("Consumer->run - i:" + i + " consume start", true);
				Hamburg h = ws.consume();
				//U.info("Consumer->run - i:" + i + " consume end, h: ��" + h + "��", true);
			}
			
			U.info("Consumer->run->������"+CONSUME_NUM+"������",true);
		}
	}

	private static class Hamburg {
		public Hamburg(String name, int id) {
			this.name = name;
			this.id = id;
		}

		public String name;
		public int id;

		@Override
		public String toString() {
			return "name:" + name + " ;id:" + id;
		}
	}
}
