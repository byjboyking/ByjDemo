package byj.concurrent;

import byj.util.U;

//【notify， notifyAll 区别详解  20150402】
//参考文档：
//再谈notify和notifyAll的区别和相同
//http://www.iflym.com/index.php/code/201208190001.html
//
//
// //nofify()
// * 通知那些可能等待该对象的其他线程，如果有多个线程等待该对象，那么线程规划器会挑选出其中一个来发出通知，而其他线程继续保持等待的状态。
// * 如果没有任何对象等待该对象，那么notify() 就不会起作用了。
// 
// * 在调用notify()方法之前，线程必须获得该对象的对象级别锁，这样就具有排他性了。
// * 与wait() 不一样的是，调用notify()不会有临时释放锁，
// * 如果调用notify()时，没有持有合适的对象锁，那么 就抛出非法的监视器状态的锁，这个异常是运行时的异常。因此不需要try catch的结构。
// * 如果没有对象的wait（）那么就是不起作用的
//
// notifyAll()
// * 这个方法与notify()的工作方式是一样的，但是通知的是等待该对象的所有线程，而不仅仅通知一个线程
// * 不用去关心通知的哪个等待的线程，而是简单的通知全部就可以了。
// * 如果实际上只有一个线程能够实际器作用，那么这样的通知就是一种浪费。浪费了处理器的资源了。
// * 如果不知道该用notifyALL还是用notify的方法，那么用notifyAll方法还保证用性能来保证程序的安全秩序
// * 虽然所有的线程都被通知了。但是这些线程都会进行竞争，且只会有一个线程成功获取到锁，在这个线程没有执行完毕之前，
//   其他的线程就必须等待了（只是这里不需要再notifyAll通知了，因为已经notifyAll了，只差获取锁了）-- 详细常见下面的demo。
//
////【总结】： 被wait的线程，想要继续运行的话，它必须满足2个条件：
//1.由其他线程notify或notifyAll了，并且当前线程被通知到了
//2.经过和其他线程进行锁竞争，成功获取到锁了
//2个条件，缺一不可。其实在实现层面，notify和notifyAll都达到相同的效果，都只会有一个线程继续运行。
//但notifyAll免去了，线程运行完了通知其他线程的必要，因为已经通知过了。
//
//
////以餐厅上菜的场景来解释 （虽然不是完全对应）：
//用餐厅做菜和端盘子的来表达，如果厨师一次只做出来一个菜，并且马上在窗口放一盘菜，然后按铃（ notify）通知 一个服务员就可以了。
//如果厨师做好了菜都先放到自己的灶台上，然后一下子端了很多盘菜到窗口，那么就需要使劲按铃（notifyAll），
//让所有的服务员都过来看看能不能碰碰运气拿到一盘菜。


//【wait&notify 总结 20161229】
//notify 后， 某个线程的wait 不一定会立即执行。 只有对应的 其他同步块都没有执行了，才会被调度。
//如以下例子， 只有Thread2 退出同步块后， Thread1的 wait 才会 恢复执行。
//
//Thread1：
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
		
		U.info("主线程中 启动子线程  开始...",true);
		for (Thread r : rs) {
			r.start();
		}
		U.info("主线程中 启动子线程  结束...",true);
		
		try {
			Thread.sleep(200);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		synchronized (obj) {
			U.info("主线程中 notifyAll 开始",true);
			obj.notifyAll();
			U.info("主线程中 notifyAll 结束",true);
			
			/*U.info("主线程中 notify 开始");
			obj.notify();
			U.info("主线程中 notify 结束");*/
		}
		
		

		//notifyAll 输出示例：
//		07:11:02.595 - [1,main,Alive,RUNNABLE]-主线程中 启动子线程  开始...
//		07:11:02.596 - [1,main,Alive,RUNNABLE]-主线程中 启动子线程  结束...
//		07:11:02.596 - [9,Thread-0,Alive,RUNNABLE]-子线程->  0 等待中
//		07:11:02.596 - [13,Thread-4,Alive,RUNNABLE]-子线程->  4 等待中
//		07:11:02.596 - [11,Thread-2,Alive,RUNNABLE]-子线程->  2 等待中
//		07:11:02.596 - [12,Thread-3,Alive,RUNNABLE]-子线程->  3 等待中
//		07:11:02.597 - [10,Thread-1,Alive,RUNNABLE]-子线程->  1 等待中
//		07:11:02.797 - [1,main,Alive,RUNNABLE]-主线程中 notifyAll 开始
//		07:11:02.797 - [1,main,Alive,RUNNABLE]-主线程中 notifyAll 结束
//		07:11:02.798 - [10,Thread-1,Alive,RUNNABLE]-子线程->  1 在运行了
//		07:11:03.000 - [12,Thread-3,Alive,RUNNABLE]-子线程->  3 在运行了
//		07:11:03.202 - [11,Thread-2,Alive,RUNNABLE]-子线程->  2 在运行了
//		07:11:03.402 - [13,Thread-4,Alive,RUNNABLE]-子线程->  4 在运行了
//		07:11:03.603 - [9,Thread-0,Alive,RUNNABLE]-子线程->  0 在运行了

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
					U.info("子线程->  " + i + " 等待中",true);
					obj.wait();
					U.info("子线程->  " + i + " 在运行了",true);
					Thread.sleep(200);
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}
}
