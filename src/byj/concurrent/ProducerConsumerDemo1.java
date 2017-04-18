package byj.concurrent;

import byj.util.U;

//两个线程互斥的生产者、消费者示例 20150401
//【网上的典型例子】
/* 生产者与消费者模型中，要保证以下几点：
* 1 同一时间内只能有一个生产者生产		生产方法加锁sychronized
* 2 同一时间内只能有一个消费者消费		消费方法加锁sychronized
* 3 生产者生产的同时消费者不能消费		生产方法加锁sychronized
* 4 消费者消费的同时生产者不能生产		消费方法加锁sychronized
* 5 共享空间空时消费者不能继续消费		消费前循环判断是否为空，空的话将该线程wait，释放锁允许其他同步方法执行
* 6 共享空间满时生产者不能继续生产		生产前循环判断是否为满，满的话将该线程wait，释放锁允许其他同步方法执行   
*/

//主类
public class  ProducerConsumerDemo1
{
	public static int MANTOU_NUM =20;
	
	public static void main(String[] args) 
	{
		StackBasket s = new StackBasket();
		Producer p = new Producer(s);
		Consumer c = new Consumer(s);
		Thread tp = new Thread(p);
		Thread tc = new Thread(c);
		tp.start();
		tc.start();
	}
}

//
class Mantou
{
	private int id;
	
	Mantou(int id){
		this.id = id;
	}

	public String toString(){
		return "[Mantou :id=" + id+" ]";
	}
}

//共享栈空间
class StackBasket
{
	private final static int MAX_PRODUCE_NUM = 2;
	
	Mantou sm[] = new Mantou[MAX_PRODUCE_NUM];
	int index = 0;
	
	/** 
	* 生产方法.
	* 该方法为同步方法，持有方法锁；
	* 首先循环判断满否，满的话使该线程等待，释放同步方法锁，允许消费；
	* 当不满时首先唤醒正在等待的消费方法，但是也只能让其进入就绪状态，
	* 等生产结束释放同步方法锁后消费才能持有该锁进行消费
	* @param m 元素
	* @return 没有返回值 
	*/ 
	public synchronized void push(Mantou m){
		try{
			while(index == MAX_PRODUCE_NUM){
				U.info("!!!!!!!!!生产满了!!!!!!!!!",true);
				this.wait();
			}
			this.notify();
		}catch(InterruptedException e){
			e.printStackTrace();
		}catch(IllegalMonitorStateException e){
			e.printStackTrace();
		}
		
		sm[index] = m;
		index++;
		U.info("生产了：" + m + " 共" + index + "个馒头",true);
	}

	/** 
	* 消费方法
	* 该方法为同步方法，持有方法锁
	* 首先循环判断空否，空的话使该线程等待，释放同步方法锁，允许生产；
	* 当不空时首先唤醒正在等待的生产方法，但是也只能让其进入就绪状态
	* 等消费结束释放同步方法锁后生产才能持有该锁进行生产
	* @param b true 表示显示，false 表示隐藏 
	* @return 没有返回值 
	*/ 
	public synchronized Mantou pop(){
		try{
			while(index == 0){
				U.info("!!!!!!!!!消费光了!!!!!!!!!",true);
				this.wait();
			}
			this.notify();
		}catch(InterruptedException e){
			e.printStackTrace();
		}catch(IllegalMonitorStateException e){
			e.printStackTrace();
		}
		index--;
		U.info("消费了：---------" + sm[index] + " 共" + index + "个馒头",true);
		return sm[index];
	}
}




class Producer implements Runnable
{
	StackBasket ss = new StackBasket();
	Producer(StackBasket ss){
		this.ss = ss;
	}

	/** 
	* 生产进程. 
	*/ 
	public void run(){
		for(int i = 0;i < ProducerConsumerDemo1.MANTOU_NUM;i++){
			Mantou m = new Mantou(i);
			ss.push(m);
//			U.info("生产了：" + m + " 共" + ss.index + "个馒头");
			try{
				Thread.sleep((int)(Math.random()*100));
			}catch(InterruptedException e){
				e.printStackTrace();
			}
		}
		
		U.info("Producer->run->生产全部完成，一共生产了  "+ProducerConsumerDemo1.MANTOU_NUM+"个馒头");
	}
}

class Consumer implements Runnable
{
	StackBasket ss = new StackBasket();
	Consumer(StackBasket ss){
		this.ss = ss;
	}

	/** 
	* 消费进程.
	*/ 
	public void run(){
		for(int i = 0;i < ProducerConsumerDemo1.MANTOU_NUM;i++){
			Mantou m = ss.pop();
//			U.info("消费了：---------" + m + " 共" + ss.index + "个馒头");
			try{
				Thread.sleep((int)(Math.random()*100));
			}catch(InterruptedException e){
				e.printStackTrace();
			}
		}
		
		U.info("Consumer->run->消费全部完成，一共消费了  "+ProducerConsumerDemo1.MANTOU_NUM+"个馒头");
	}
}