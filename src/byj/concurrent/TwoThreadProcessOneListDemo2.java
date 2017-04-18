package byj.concurrent;

import java.util.ArrayList;
import java.util.List;

import byj.util.U;

//�����߳���������һ��list -����ʾ�� 20161231
//�����߳���ѯ����һ�����ϣ�
//��ʽ2��ͨ��wait/notifyAllʵ�֣�����ʵ���߼�����Щ����Ҫ����һ���߳���ɺ�֪ͨ����һ���߳�Ҳ����������
public class TwoThreadProcessOneListDemo2 {

	private static class Item {
		private int id;
		private String name;

		public Item(int id, String name) {
			this.id = id;
			this.name = name;
		}

		@Override
		public String toString() {
			return " id:" + id + " ;name:" + name;
		}
	}
	
	private static class WrapperList{
		public List<Item> list;
		public boolean isFirstThread = true;
		public boolean anotherThreadRunning = true;
		
		public WrapperList(List<Item> list,boolean isFirstThread ) {
			this.list=list;
			this.isFirstThread=isFirstThread;
		}
	}

	private static class WorkThread implements Runnable {
		private String threadName;
		private WrapperList wrapperList ;
		private boolean isFirstThread = false;

		public WorkThread(String name, WrapperList wrapperList , boolean isFirstThread) {
			this.threadName = name;
			this.wrapperList = wrapperList;
			this.isFirstThread=isFirstThread;
		}

		@Override
		public void run() {
			synchronized (wrapperList) {
				while (wrapperList.list.size() > 0) {
					if(isFirstThread != wrapperList.isFirstThread && wrapperList.anotherThreadRunning){
						try {
							wrapperList.wait();
						} catch (InterruptedException e) {
							U.info("ThreadName:" + threadName + "->run()-InterruptedException:"+e,true);
						}
					}
					
					if(wrapperList.list.size() == 0){
						U.info("ThreadName:" + threadName + "->run()-wrapperList.list.size() == 0, break",true);
						break;
					}
					
					Item item = wrapperList.list.remove(0);
					//do some job here...
					
					//�л�������һ���߳�ȥ����
					wrapperList.isFirstThread = !wrapperList.isFirstThread;
					U.info("ThreadName:" + threadName + "->run()->remove item:" + item, true);
					wrapperList.notifyAll();
				}
				
				//���ñ��λ��ȷ�� ���� һ���߳�Ҳ�������� ��ֹ
				wrapperList.anotherThreadRunning = false;
				wrapperList.notifyAll();
			}
			
			U.info("ThreadName:" + threadName + "->run()-> finish", true);
		}
	}

	public static void main(String[] args) {
		List<Item> list = new ArrayList<Item>();
		for (int i = 0; i < 10; i++) {
			int id = i + 1;
			String name = "item" + id;
			list.add(new Item(id, name));
		}
		
		//��ִ�е�һ���߳�
		WrapperList wrapperList = new WrapperList(list,true);
		Thread t1 = new Thread(new WorkThread("Thread1", wrapperList, true));
		Thread t2 = new Thread(new WorkThread("Thread2", wrapperList,false));
		t1.start();
		t2.start();

		try {
			//�ȴ�t1��t2ִ����ɺ��ټ���ִ�����߳�
			t1.join();
			t2.join();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		U.info("main->end", true);
	}
}