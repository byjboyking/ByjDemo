package byj.concurrent;

import byj.util.U;

//【一种由于 wait ，notify 使用不当引起的死锁 20150402】

//说明一下notify与notifyAll的区别：
//
//以上sub和main方法都是用了锁，所以说多个调用sub方法的线程和多个调用main方法的都会处于阻塞状态，
//都会等待一个正在运行的其他线程来唤醒他们，以上代码使用了notify进行唤醒，
//notify只能唤醒一个线程，其他等待的线程仍然处于wait状态，【如果调用sub方法的线程执行完后，所有的线程都处于等待状态】，
//isSub=false了，这时唤醒的是一个sub方法调度线程，
//那么while循环等于true，则该线程也会处于等待状态，之后所有的线程处于等待状态，没有运行的线程来唤醒他们，这时就产生了死锁。
//如果使用notifyAll()来唤醒所有正在等待该锁的线程，
//那么所有的线程都会处于运行前的准备状态，就是sub方法执行完后，唤醒了所有等待该锁的状态，那么即使再次唤醒一个sub方法调度线程，
//那么该线程再次处于等待状态后，
//还有其他的线程可以获得该锁，进入运行状态。所以notify方法很容易引起死锁，除非你根据自己的程序设计，确定不会引起死锁，
//notifyAll则是线程的安全唤醒方法。
//
//言归正传，以上代码 只需要将sub和main方法中的参数改成this.notifyAll()即可。

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
		
		//死锁时的输出

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
