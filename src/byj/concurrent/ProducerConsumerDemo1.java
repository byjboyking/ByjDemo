package byj.concurrent;

import byj.util.U;

//�����̻߳���������ߡ�������ʾ�� 20150401
//�����ϵĵ������ӡ�
/* ��������������ģ���У�Ҫ��֤���¼��㣺
* 1 ͬһʱ����ֻ����һ������������		������������sychronized
* 2 ͬһʱ����ֻ����һ������������		���ѷ�������sychronized
* 3 ������������ͬʱ�����߲�������		������������sychronized
* 4 ���������ѵ�ͬʱ�����߲�������		���ѷ�������sychronized
* 5 ����ռ��ʱ�����߲��ܼ�������		����ǰѭ���ж��Ƿ�Ϊ�գ��յĻ������߳�wait���ͷ�����������ͬ������ִ��
* 6 ����ռ���ʱ�����߲��ܼ�������		����ǰѭ���ж��Ƿ�Ϊ�������Ļ������߳�wait���ͷ�����������ͬ������ִ��   
*/

//����
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

//����ջ�ռ�
class StackBasket
{
	private final static int MAX_PRODUCE_NUM = 2;
	
	Mantou sm[] = new Mantou[MAX_PRODUCE_NUM];
	int index = 0;
	
	/** 
	* ��������.
	* �÷���Ϊͬ�����������з�������
	* ����ѭ���ж��������Ļ�ʹ���̵߳ȴ����ͷ�ͬ�����������������ѣ�
	* ������ʱ���Ȼ������ڵȴ������ѷ���������Ҳֻ������������״̬��
	* �����������ͷ�ͬ�������������Ѳ��ܳ��и�����������
	* @param m Ԫ��
	* @return û�з���ֵ 
	*/ 
	public synchronized void push(Mantou m){
		try{
			while(index == MAX_PRODUCE_NUM){
				U.info("!!!!!!!!!��������!!!!!!!!!",true);
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
		U.info("�����ˣ�" + m + " ��" + index + "����ͷ",true);
	}

	/** 
	* ���ѷ���
	* �÷���Ϊͬ�����������з�����
	* ����ѭ���жϿշ񣬿յĻ�ʹ���̵߳ȴ����ͷ�ͬ��������������������
	* ������ʱ���Ȼ������ڵȴ�����������������Ҳֻ������������״̬
	* �����ѽ����ͷ�ͬ�����������������ܳ��и�����������
	* @param b true ��ʾ��ʾ��false ��ʾ���� 
	* @return û�з���ֵ 
	*/ 
	public synchronized Mantou pop(){
		try{
			while(index == 0){
				U.info("!!!!!!!!!���ѹ���!!!!!!!!!",true);
				this.wait();
			}
			this.notify();
		}catch(InterruptedException e){
			e.printStackTrace();
		}catch(IllegalMonitorStateException e){
			e.printStackTrace();
		}
		index--;
		U.info("�����ˣ�---------" + sm[index] + " ��" + index + "����ͷ",true);
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
	* ��������. 
	*/ 
	public void run(){
		for(int i = 0;i < ProducerConsumerDemo1.MANTOU_NUM;i++){
			Mantou m = new Mantou(i);
			ss.push(m);
//			U.info("�����ˣ�" + m + " ��" + ss.index + "����ͷ");
			try{
				Thread.sleep((int)(Math.random()*100));
			}catch(InterruptedException e){
				e.printStackTrace();
			}
		}
		
		U.info("Producer->run->����ȫ����ɣ�һ��������  "+ProducerConsumerDemo1.MANTOU_NUM+"����ͷ");
	}
}

class Consumer implements Runnable
{
	StackBasket ss = new StackBasket();
	Consumer(StackBasket ss){
		this.ss = ss;
	}

	/** 
	* ���ѽ���.
	*/ 
	public void run(){
		for(int i = 0;i < ProducerConsumerDemo1.MANTOU_NUM;i++){
			Mantou m = ss.pop();
//			U.info("�����ˣ�---------" + m + " ��" + ss.index + "����ͷ");
			try{
				Thread.sleep((int)(Math.random()*100));
			}catch(InterruptedException e){
				e.printStackTrace();
			}
		}
		
		U.info("Consumer->run->����ȫ����ɣ�һ��������  "+ProducerConsumerDemo1.MANTOU_NUM+"����ͷ");
	}
}