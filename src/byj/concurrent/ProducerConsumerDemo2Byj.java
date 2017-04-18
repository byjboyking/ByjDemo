package byj.concurrent;


import java.util.ArrayList;
import java.util.List;

import byj.util.U;

//我自己写的例子 20161229
//注意，必须要是生产者和消费者数目相同。 未考虑不同的 退出容错处理。

//参考自：
//java多线程之消费者生产者模式 .
//http://blog.csdn.net/shijinupc/article/details/7250407
//
//Java线程(三)：线程协作-生产者/消费者问题 
//http://blog.csdn.net/ghsau/article/details/7433673#
//
//
//实现的关键：
//共享内存中的两个同步方法，及同步方法中wait()方法的调用，同步保证了对象只能被一个线程占用，
//wait保证了当线程在等待过程中释放锁，使得其他对象有机会获得锁。
//在一个对象中，用synchonized声明的方法为同步方法。
//Java中有一个同步模型-监视器，负责管理线程对对象中的同步方法的访问，它的原理是：赋予该对象唯一一把'钥匙'，
//当多个线程进入对象，只有取得该对象钥匙的线程才可以访问同步方法，其它线程在该对象中等待，
//直到该线程用wait()方法放弃这把钥匙，其它等待的线程抢占该钥匙，
//抢占到钥匙的线程后才可得以执行，而没有取得钥匙的线程仍被阻塞在该对象中等待。 
//总而言之，synchonized使得只有一个线程能进入临界代码区。
//
//
//注意:在调用wait方法时，都是用while判断条件的，而不是if，在wait方法说明中，也推荐使用while，
//因为在某些特定的情况下，线程有可能被假唤醒，
//使用while会循环检测更稳妥。wait和notify方法必须工作于synchronized内部，且这两个方法只能由锁对象来调用。

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
			
			U.info("Producer->run->共生产 "+PRODUCE_NUM+"个汉堡",true);
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
				//U.info("Consumer->run - i:" + i + " consume end, h: 【" + h + "】", true);
			}
			
			U.info("Consumer->run->共消费"+CONSUME_NUM+"个汉堡",true);
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
