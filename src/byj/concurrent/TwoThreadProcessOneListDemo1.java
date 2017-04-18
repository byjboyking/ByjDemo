package byj.concurrent;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Semaphore;

import byj.util.U;

//方式1：通过Semaphore信号量实现， 需要每次切换需要睡眠5-10ms。 （设置5ms还是有部分无法轮流执行）
//对于压力测试可能无法通过， 仅供参考。
public class TwoThreadProcessOneListDemo1 {

	private static class Item{
		private int id;
		private String name;
		public Item(int id, String name) {
			this.id=id;
			this.name=name;
		}
		
		@Override
		public String toString() {
			return " id:"+id+" ;name:"+name;
		}
	}
	
	private static class WorkThread implements Runnable{
		private String threadName;
		private Semaphore sem ;
		List<Item> list ;
		
		private static final long SLEEP_TIME = 5;
		
		public WorkThread(String name, Semaphore sem, List<Item> list ) {
			this.threadName = name;
			this.sem =sem;
			this.list=list;
		}
		
		@Override
		public void run() {
			int counter = 0;
			while(list.size()>0){
				try {
					sem.acquire();
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				
				if(list.size()>0){
					Item item = list.remove(0);
					//U.info("ThreadName:"+threadName+"->run()->remove item:"+item,true);
					counter++;
				}
				
				sem.release();
//				try {
//					Thread.sleep(SLEEP_TIME);
//				} catch (InterruptedException e) {
//					// TODO Auto-generated catch block
//					e.printStackTrace();
//				}
			}
			
			U.info("ThreadName:"+threadName+"->run()->counter:"+counter,true);
		}
	}
	
	public static void main(String[] args) {
		Semaphore sem = new Semaphore(1,true);
		List<Item> list = new ArrayList<Item>();
		for(int i=0;i<10000;i++){
			int id = i+1;
			String name = "item"+id;
			list.add(new Item(id,name));
		}
		
		Thread t1 = new Thread(new WorkThread("Thread1",sem,list));
		Thread t2 = new Thread(new WorkThread("Thread2",sem,list));
		
		t1.start();
		t2.start();
		try {
			t1.join();
			t2.join();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		U.info("main->end",true);
		
		//1、睡眠时间为5ms时 ，仍然会出现 不是轮询执行的情况。
//		17:46:02.484 - [12,Thread-1,Alive,RUNNABLE]-ThreadName:Thread2->run()->remove item: id:1 ;name:item1
//		17:46:02.485 - [11,Thread-0,Alive,RUNNABLE]-ThreadName:Thread1->run()->remove item: id:2 ;name:item2
//		17:46:02.489 - [12,Thread-1,Alive,RUNNABLE]-ThreadName:Thread2->run()->remove item: id:3 ;name:item3
//		17:46:02.490 - [11,Thread-0,Alive,RUNNABLE]-ThreadName:Thread1->run()->remove item: id:4 ;name:item4
//		17:46:02.496 - [12,Thread-1,Alive,RUNNABLE]-ThreadName:Thread2->run()->remove item: id:5 ;name:item5
//		17:46:02.496 - [11,Thread-0,Alive,RUNNABLE]-ThreadName:Thread1->run()->remove item: id:6 ;name:item6
//		17:46:02.502 - [11,Thread-0,Alive,RUNNABLE]-ThreadName:Thread1->run()->remove item: id:7 ;name:item7
//		17:46:02.502 - [12,Thread-1,Alive,RUNNABLE]-ThreadName:Thread2->run()->remove item: id:8 ;name:item8
//		17:46:02.507 - [11,Thread-0,Alive,RUNNABLE]-ThreadName:Thread1->run()->remove item: id:9 ;name:item9
//		17:46:02.507 - [12,Thread-1,Alive,RUNNABLE]-ThreadName:Thread2->run()->remove item: id:10 ;name:item10
//		17:46:02.512 - [12,Thread-1,Alive,RUNNABLE]-ThreadName:Thread2->run()->remove item: id:11 ;name:item11
//		17:46:02.512 - [11,Thread-0,Alive,RUNNABLE]-ThreadName:Thread1->run()->remove item: id:12 ;name:item12
//		17:46:02.517 - [11,Thread-0,Alive,RUNNABLE]-ThreadName:Thread1->run()->remove item: id:13 ;name:item13
//		17:46:02.517 - [12,Thread-1,Alive,RUNNABLE]-ThreadName:Thread2->run()->remove item: id:14 ;name:item14
//		17:46:02.522 - [12,Thread-1,Alive,RUNNABLE]-ThreadName:Thread2->run()->remove item: id:15 ;name:item15
		
		//2、不睡眠， 每次都会出现 不轮询执行的情况
//		22:09:37.294 - [10,Thread-1,Alive,RUNNABLE]-ThreadName:Thread2->run()->counter:4993
//		22:09:37.294 - [9,Thread-0,Alive,RUNNABLE]-ThreadName:Thread1->run()->counter:5007
//
//		22:10:02.039 - [9,Thread-0,Alive,RUNNABLE]-ThreadName:Thread1->run()->counter:5014
//		22:10:02.039 - [10,Thread-1,Alive,RUNNABLE]-ThreadName:Thread2->run()->counter:4986
//
//		22:10:08.806 - [10,Thread-1,Alive,RUNNABLE]-ThreadName:Thread2->run()->counter:4999
//		22:10:08.806 - [9,Thread-0,Alive,RUNNABLE]-ThreadName:Thread1->run()->counter:5001
//
//		22:10:15.667 - [9,Thread-0,Alive,RUNNABLE]-ThreadName:Thread1->run()->counter:5004
//		22:10:15.667 - [10,Thread-1,Alive,RUNNABLE]-ThreadName:Thread2->run()->counter:4996
//
//		22:10:23.012 - [10,Thread-1,Alive,RUNNABLE]-ThreadName:Thread2->run()->counter:4978
//		22:10:23.012 - [9,Thread-0,Alive,RUNNABLE]-ThreadName:Thread1->run()->counter:5022
		
	}
}
