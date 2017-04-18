package byj.concurrent;


import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;

import byj.util.U;

//【基于阻塞队列(BlockingQueue)的实现（我自己的例子） 20161231】

//BlockingQueue-阻塞队列,  可以设置一个队列的最大值。
//1、当队列已满，继续put，会处于阻塞状态。
//2、当队列为空，继续take， 也会处于阻塞状态。
//阻塞队列 就是为了 生产者-消费者的典型场景而设计的。


//public interface BlockingQueue<E> extends Queue<E>
//
//A Queue that additionally supports operations that wait for the queue to become non-empty when 
//retrieving an element, and wait for space to become available in the queue when storing an element. 
//
//BlockingQueue implementations are designed to be used primarily for producer-consumer queues
//
//BlockingQueue implementations are thread-safe
//
//Note that a BlockingQueue can safely be used with multiple producers and multiple consumers. 
//
////Summary of BlockingQueue methods
//       【Throws exception】 【Special value】 【Blocks】     【Times out】 
//Insert  add(e)               offer(e)          put(e)         offer(e, time, unit) 
//Remove  remove()             poll()            take()         poll(time, unit) 
//Examine element()            peek()            not applicable not applicable 

public class ProducerConsumerUsingBlockingQuene {
	public final static int PRODUCE_NUM = 50;
	public final static int CONSUME_NUM = 50;
	private final static int MAX_SIZE = 3;
	
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
	
	private static class Consumer implements Runnable {
		BlockingQueue<Hamburg> queue;
		public Consumer(BlockingQueue<Hamburg> queue) {
			this.queue = queue;
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

				try {
					long start = System.currentTimeMillis();
					U.info("Consumer->run - i:" + i + " queue.take() start", true);
					Hamburg h = queue.take();
					long consuming = System.currentTimeMillis()-start;
					
					U.infoOrErr("Consumer->run - i:" + i + " queue.take() end, consuming:"+consuming, true, consuming==0);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					U.err("run->InterruptedException e:"+e, true);
				}
				//U.info("Consumer->run - i:" + i + " consume end, h: 【" + h + "】", true);
			}
		}
	}
	
	private static class Producer implements Runnable {
		BlockingQueue<Hamburg> queue;
		public Producer(BlockingQueue<Hamburg> queue) {
			this.queue = queue;
		}

		@Override
		public void run() {
			for (int i = 0; i < PRODUCE_NUM; i++) {
				long sleepTime = U.generateIntRandom(50);
				//U.info("Producer->run - i:" + i + " sleepTime:" + sleepTime + " start sleep", true);
				try {
					Thread.sleep(sleepTime);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}

				//U.info("Producer->run - i:" + i + " sleepTime:" + sleepTime + " end sleep", true);
				String name = "hamburg" + i;
				try {
					Hamburg h = new Hamburg(name, i);
					long start = System.currentTimeMillis();
					U.info("Producer->run - i:" + i + " queue.put() start, Hamburg:"+h, true);
					queue.put(h);
					long consuming = System.currentTimeMillis()-start;
					U.infoOrErr("Producer->run - i:" + i + " queue.put() end. consuming:"+consuming, true, consuming==0);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				//U.info("Producer->run - i:" + i + " produce end", true);
			}
		}
	}

	public static void main(String[] args) {
		//BlockingQueue<Hamburg> queue = new LinkedBlockingQueue<Hamburg>(MAX_SIZE);
		BlockingQueue<Hamburg> queue = new ArrayBlockingQueue<Hamburg>(MAX_SIZE);
		Thread tProducer = new Thread(new Producer(queue));
		Thread tConsumer = new Thread(new Consumer(queue));
		tProducer.start();
		tConsumer.start();
	}
}
