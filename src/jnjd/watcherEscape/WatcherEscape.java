package jnjd.watcherEscape;

/**
 * 【逃出恶魔岛】
 * 恶魔猎手尤迪安野心勃勃，他背叛了暗夜精灵，率深藏在海底的那加企图叛变：
 * 守望者在与尤迪安的交锋中遭遇了围杀，被困在一个荒芜的大岛上。
 * 为了杀死守望者，尤迪安开始对这个荒岛施咒，这座岛很快就会沉下去。
 * 到那时，岛上的所有人都会遇难：守望者的跑步速度为17m/s,
 * 以这样的速度是无法逃离荒岛的，庆幸的是守望者拥有闪烁法术，可在1s内移动60m，
 * 不过每次使用闪烁法术都会消耗魔法值10点。守望者的魔法值恢复的速度为4点/s,
 * 只有处在原地休息状态时才能恢复。
 * 现在已知守望者 的魔法初值M，他所在的初始位置与岛的出口之间的距离S，岛沉没的时间T. 
 * 你的任务是写一个程序帮助守望者计算如何在最短的时间内逃离荒岛，若不能逃离，则输出守望者在剩下的时间内能走的最远距离。
 * 注意：守望者跑步、闪烁或休息活动均以秒（s）为单位。
 * 且每次活动的持续时间为整数秒。距离的单位为米（m）
 * 
 * 提示：
 * 30%的数据满足： 1<=t<=10,  1<=s<=100
 * 50%的数据满足：  1<=t<=1000, 1<=s<=10000
 * 100%的数据满足：1<=t<=300000, 0<=m<=1000   1<=s<=10^8
 *
 */
public class WatcherEscape {

	//17 m/s
	private  final static int SPEED_RUN = 17;
	
	//60 m/s, 同时 法术消耗10点
	private  final static int SPEED_FLASH = 60;
	
	//每次使用闪烁法术消耗魔法值10点
	private  final static int MAGIC_FLASH_CONSUMING = 10;
	
	//4 点/s
	private  final static int MAGIC_RECOVER = 4;
	
	// 综合计算出的 使用魔法的平均速度, 17.14
	//private final static double SPEED_MAGIC_FLASH = (double)(((4*10)/10*60.0)/(10+4));
	
	private final static String YES = "Yes";
	private final static String NO = "No";
	
	/**
	 * 功能：判断守望者是否能够逃出荒岛
	 * @param uiMagic 整型，守望者的初始魔法值
	 * @param uiDistance 型，守望者所在的初始位置与岛出口之间的距离
	 * @param uiSec 整型，岛沉没需要的时间，单位为秒
	 * @param pRstOut 保证传入的是空的StringBuilder对象，要求把结果加入，
	 * 输出守望者能否逃出荒岛，若能逃出输出”Yes”, 不能输出”No”, 注意大小写。
	 * @return 返回值：若守望者能逃出荒岛，输出逃出荒岛所用的最短时间，不能逃出则输出守望者能逃出的最大距离。
	 */
	public int helpWatcherEscape(int uiMagic,int uiDistance,int uiSec,StringBuilder pRstOut){
		
		return helpWatcherEscape_greedy( uiMagic, uiDistance, uiSec, pRstOut);
		//return helpWatcherEscape_byj( uiMagic, uiDistance, uiSec, pRstOut);
	}
	
	//贪心算法实现
	public int helpWatcherEscape_greedy(int uiMagic,int uiDistance,int uiSec,StringBuilder pRstOut){
		if(uiMagic<0 || uiDistance<=0 || uiSec<=0){
			pRstOut.append(NO);
			return 0;
		}
		
		int runDistance = 0;
		int blinkDistance =0;
		int passedSec = 0;
		
		//把 可用时间 用完，计算出最大距离和 uiDistance 进行比较即可
		while(passedSec<uiSec){
			passedSec++;
			
			if(uiMagic<10){
				//恢复魔法
				uiMagic+=4;
			}else{
				//使用魔法进行移动
				uiMagic-=10;
				blinkDistance+=60;
			}
			
			runDistance+=17;
			
			if(blinkDistance>runDistance){
				//如果在某一个时间点，全部使用魔法的运动距离超过 全部跑步，则以魔法距离为主（取最大值）
				runDistance = blinkDistance;
			}
			
			//此时 runDistance  即为 实时的最大距离
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
			//初始魔法值就足以逃出
			int time = distance/SPEED_FLASH;
			if(distance%SPEED_FLASH !=0){
				time++;
			}
			if(time<=uiSec){
				//场景1:时间足够的情况下， 全部使用魔法，可以逃出
				pRstOut.append(YES);
				return time;
			}else{
				//场景2:时间不足够的情况下， 全部使用魔法，无法逃出
				pRstOut.append(NO);
				return uiSec*SPEED_FLASH;
			}
		}else{
			//初始魔法值不足以逃出
			if(initMagicNum>=uiSec){
				//场景3：使用初始魔法点数耗费时间 超过了 可用时间，无法逃出
				pRstOut.append(NO);
				return uiSec*SPEED_FLASH;
			}else{
				//先把魔法值全部消耗掉
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
						//【必须要使用两次移动魔法】
						//0 ->  4,8,12,16,20  5次补充魔法值， 2次使用 魔法移动
						//1 ->  5,9,13,17,21  5次补充魔法值， 2次使用 魔法移动
						//需要使用两次魔法才划算, 4,10 最小公倍数 20
						useMagicTime = 20/MAGIC_RECOVER+  20/MAGIC_FLASH_CONSUMING; //7秒
						magicMoveTime = 2;
					}else{
						//【只需要使用一次移动魔法】
						int lackMagic = 10-remainMagic;
						useMagicTime = lackMagic%4==0?lackMagic/4:lackMagic/4+1;
						//增加使用魔法 进行移动的耗时
						useMagicTime++;
						magicMoveTime = 1;
					}
					
					if(remainTime<useMagicTime){
						//时间不够，只能使用步行
						if(remainTime*SPEED_RUN>=remainDistance){
							//可以直接跑出去
							int tempTime = remainDistance%SPEED_RUN==0?remainDistance/SPEED_RUN:remainDistance/SPEED_RUN+1;
							sumConsumingTime +=tempTime;
							pRstOut.append(YES);
							return sumConsumingTime;
						}else{
							//跑不出去
							sumConsumingDistance+= remainTime*SPEED_RUN;
							pRstOut.append(NO);
							return sumConsumingDistance;
						}
					}else{
						//时间足够，使用魔法移动
						if(magicMoveTime*SPEED_FLASH>=remainDistance){
							//可以直接魔法移动出去
							sumConsumingTime+=useMagicTime;
							pRstOut.append(YES);
							return sumConsumingTime;
						}else{
							//魔法也移动不出去
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
