package jnjd.watcherEscape;

/**
 * ���ӳ���ħ����
 * ��ħ�����ȵϰ�Ұ�Ĳ������������˰�ҹ���飬������ں��׵��Ǽ���ͼ�ѱ䣺
 * �����������ȵϰ��Ľ�����������Χɱ��������һ�����ߵĴ��ϡ�
 * Ϊ��ɱ�������ߣ��ȵϰ���ʼ������ĵ�ʩ�䣬�������ܿ�ͻ����ȥ��
 * ����ʱ�����ϵ������˶������ѣ������ߵ��ܲ��ٶ�Ϊ17m/s,
 * ���������ٶ����޷�����ĵ��ģ����ҵ���������ӵ����˸����������1s���ƶ�60m��
 * ����ÿ��ʹ����˸������������ħ��ֵ10�㡣�����ߵ�ħ��ֵ�ָ����ٶ�Ϊ4��/s,
 * ֻ�д���ԭ����Ϣ״̬ʱ���ָܻ���
 * ������֪������ ��ħ����ֵM�������ڵĳ�ʼλ���뵺�ĳ���֮��ľ���S������û��ʱ��T. 
 * ���������дһ��������������߼����������̵�ʱ��������ĵ������������룬�������������ʣ�µ�ʱ�������ߵ���Զ���롣
 * ע�⣺�������ܲ�����˸����Ϣ������루s��Ϊ��λ��
 * ��ÿ�λ�ĳ���ʱ��Ϊ�����롣����ĵ�λΪ�ף�m��
 * 
 * ��ʾ��
 * 30%���������㣺 1<=t<=10,  1<=s<=100
 * 50%���������㣺  1<=t<=1000, 1<=s<=10000
 * 100%���������㣺1<=t<=300000, 0<=m<=1000   1<=s<=10^8
 *
 */
public class WatcherEscape {

	//17 m/s
	private  final static int SPEED_RUN = 17;
	
	//60 m/s, ͬʱ ��������10��
	private  final static int SPEED_FLASH = 60;
	
	//ÿ��ʹ����˸��������ħ��ֵ10��
	private  final static int MAGIC_FLASH_CONSUMING = 10;
	
	//4 ��/s
	private  final static int MAGIC_RECOVER = 4;
	
	// �ۺϼ������ ʹ��ħ����ƽ���ٶ�, 17.14
	//private final static double SPEED_MAGIC_FLASH = (double)(((4*10)/10*60.0)/(10+4));
	
	private final static String YES = "Yes";
	private final static String NO = "No";
	
	/**
	 * ���ܣ��ж��������Ƿ��ܹ��ӳ��ĵ�
	 * @param uiMagic ���ͣ������ߵĳ�ʼħ��ֵ
	 * @param uiDistance �ͣ����������ڵĳ�ʼλ���뵺����֮��ľ���
	 * @param uiSec ���ͣ�����û��Ҫ��ʱ�䣬��λΪ��
	 * @param pRstOut ��֤������ǿյ�StringBuilder����Ҫ��ѽ�����룬
	 * ����������ܷ��ӳ��ĵ��������ӳ������Yes��, ���������No��, ע���Сд��
	 * @return ����ֵ�������������ӳ��ĵ�������ӳ��ĵ����õ����ʱ�䣬�����ӳ���������������ӳ��������롣
	 */
	public int helpWatcherEscape(int uiMagic,int uiDistance,int uiSec,StringBuilder pRstOut){
		
		return helpWatcherEscape_greedy( uiMagic, uiDistance, uiSec, pRstOut);
		//return helpWatcherEscape_byj( uiMagic, uiDistance, uiSec, pRstOut);
	}
	
	//̰���㷨ʵ��
	public int helpWatcherEscape_greedy(int uiMagic,int uiDistance,int uiSec,StringBuilder pRstOut){
		if(uiMagic<0 || uiDistance<=0 || uiSec<=0){
			pRstOut.append(NO);
			return 0;
		}
		
		int runDistance = 0;
		int blinkDistance =0;
		int passedSec = 0;
		
		//�� ����ʱ�� ���꣬������������ uiDistance ���бȽϼ���
		while(passedSec<uiSec){
			passedSec++;
			
			if(uiMagic<10){
				//�ָ�ħ��
				uiMagic+=4;
			}else{
				//ʹ��ħ�������ƶ�
				uiMagic-=10;
				blinkDistance+=60;
			}
			
			runDistance+=17;
			
			if(blinkDistance>runDistance){
				//�����ĳһ��ʱ��㣬ȫ��ʹ��ħ�����˶����볬�� ȫ���ܲ�������ħ������Ϊ����ȡ���ֵ��
				runDistance = blinkDistance;
			}
			
			//��ʱ runDistance  ��Ϊ ʵʱ��������
			if(runDistance>=uiDistance){
				break;
			}
		}
		
		
		if(runDistance>=uiDistance){
			pRstOut.append(YES);
			return passedSec;
		}else{
			pRstOut.append(NO);
			return runDistance;
		}
	}
	
	//
	public int helpWatcherEscape_byj(int uiMagic,int uiDistance,int uiSec,StringBuilder pRstOut){
		if(uiMagic<0 || uiDistance<=0 || uiSec<=0){
			pRstOut.append(NO);
			return 0;
		}
		
		int magic = uiMagic;
		int initMagicNum = magic/MAGIC_FLASH_CONSUMING;
		
		int distance = uiDistance;
		if(SPEED_FLASH*initMagicNum>=distance){
			//��ʼħ��ֵ�������ӳ�
			int time = distance/SPEED_FLASH;
			if(distance%SPEED_FLASH !=0){
				time++;
			}
			if(time<=uiSec){
				//����1:ʱ���㹻������£� ȫ��ʹ��ħ���������ӳ�
				pRstOut.append(YES);
				return time;
			}else{
				//����2:ʱ�䲻�㹻������£� ȫ��ʹ��ħ�����޷��ӳ�
				pRstOut.append(NO);
				return uiSec*SPEED_FLASH;
			}
		}else{
			//��ʼħ��ֵ�������ӳ�
			if(initMagicNum>=uiSec){
				//����3��ʹ�ó�ʼħ�������ķ�ʱ�� ������ ����ʱ�䣬�޷��ӳ�
				pRstOut.append(NO);
				return uiSec*SPEED_FLASH;
			}else{
				//�Ȱ�ħ��ֵȫ�����ĵ�
				int remainDistance = distance-SPEED_FLASH*initMagicNum;
				int remainMagic = magic%MAGIC_FLASH_CONSUMING;
				int remainTime = uiSec-initMagicNum;
				
				int sumConsumingTime = initMagicNum;
				int sumConsumingDistance = SPEED_FLASH*initMagicNum;
				
				int counter=0;
				int maxCounter= uiSec;
				
				while(true){ 
					if(counter>=maxCounter){
						pRstOut.append(NO);
						return 0;
					}
					
					int useMagicTime=0;
					int magicMoveTime=0;
					
					if(remainMagic==0 || remainMagic==1){
						//������Ҫʹ�������ƶ�ħ����
						//0 ->  4,8,12,16,20  5�β���ħ��ֵ�� 2��ʹ�� ħ���ƶ�
						//1 ->  5,9,13,17,21  5�β���ħ��ֵ�� 2��ʹ�� ħ���ƶ�
						//��Ҫʹ������ħ���Ż���, 4,10 ��С������ 20
						useMagicTime = 20/MAGIC_RECOVER+  20/MAGIC_FLASH_CONSUMING; //7��
						magicMoveTime = 2;
					}else{
						//��ֻ��Ҫʹ��һ���ƶ�ħ����
						int lackMagic = 10-remainMagic;
						useMagicTime = lackMagic%4==0?lackMagic/4:lackMagic/4+1;
						//����ʹ��ħ�� �����ƶ��ĺ�ʱ
						useMagicTime++;
						magicMoveTime = 1;
					}
					
					if(remainTime<useMagicTime){
						//ʱ�䲻����ֻ��ʹ�ò���
						if(remainTime*SPEED_RUN>=remainDistance){
							//����ֱ���ܳ�ȥ
							int tempTime = remainDistance%SPEED_RUN==0?remainDistance/SPEED_RUN:remainDistance/SPEED_RUN+1;
							sumConsumingTime +=tempTime;
							pRstOut.append(YES);
							return sumConsumingTime;
						}else{
							//�ܲ���ȥ
							sumConsumingDistance+= remainTime*SPEED_RUN;
							pRstOut.append(NO);
							return sumConsumingDistance;
						}
					}else{
						//ʱ���㹻��ʹ��ħ���ƶ�
						if(magicMoveTime*SPEED_FLASH>=remainDistance){
							//����ֱ��ħ���ƶ���ȥ
							sumConsumingTime+=useMagicTime;
							pRstOut.append(YES);
							return sumConsumingTime;
						}else{
							//ħ��Ҳ�ƶ�����ȥ
							sumConsumingDistance+= magicMoveTime*SPEED_FLASH;
							sumConsumingTime+=useMagicTime;
							remainDistance -= magicMoveTime*SPEED_FLASH;
							remainTime -= useMagicTime;
							
							if(magicMoveTime==1){
								int lackMagic = 10-remainMagic;
								int recoverTime = lackMagic%4==0?lackMagic/4:lackMagic/4+1;
								remainMagic = (remainMagic+recoverTime*4)/10;
							}
						}
					}
				}
			}
		}
	}
}
