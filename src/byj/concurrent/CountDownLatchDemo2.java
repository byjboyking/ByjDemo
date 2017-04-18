package byj.concurrent;


import java.util.Random;
import java.util.concurrent.CountDownLatch;

import byj.util.U;


//典型场景2的实现  （主线程启动 若干子线程后， 等所有子线程都执行完毕后， 再接着往下执行） 20161227
public class CountDownLatchDemo2 {

	public static class SubThread extends Thread {
		private int id;
		private CountDownLatch latch;
		private long random;

		public SubThread(int id, CountDownLatch latch, long random) {
			this.id = id;
			this.latch = latch;
			this.random = random;
		}

		@Override
		public void run() {
			super.run();

			U.err("SubThread->run ,id:" + id + " start sleep", true);
			try {
				Thread.sleep(random);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}

			U.err("SubThread->run ,id:" + id + " end sleep, start countDown", true);

			latch.countDown();
			U.err("SubThread->run ,id:" + id + " end countDown", true);
		}
	}

	public static void main(String[] args) {
		int size = 5;

		CountDownLatch latch = new CountDownLatch(size);

		Random random = new Random(55);

		for (int i = 0; i < size; i++) {
			new SubThread(i, latch, random.nextInt(1000)).start();
		}

		U.err("main-> start await", true);
		try {
			latch.await();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}

		U.err("main-> end await, continue doing something...", true);
	}

}