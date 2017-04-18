package jnjd.hannoitaRobot;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

public class HannoitaRobot {

	public void manager(IControlable controller) {
		// TODO: 编写自己的代码
		operRecursive(controller, controller.getSrcIndex(),
				controller.getTargetIndex(), controller.getTowerSize());
	}

	private int getTempIndex(int srcIndex, int targetIndex) {
		int tempIndex = -1;
		for (int i = 0; i <= 2; i++) {
			if (i != srcIndex && i != targetIndex) {
				tempIndex = i;
				break;
			}
		}

		return tempIndex;
	}

	// 汉诺塔的递归算法实现
	private void operRecursive(IControlable controller, int srcIndex,
			int targetIndex, int n) {
		int tempIndex = getTempIndex(srcIndex, targetIndex);

		if (n == 1) {
			operDetail(controller, srcIndex, targetIndex);
		} else {
			// 步骤1、把 最上面的 n-1 个 从 src 移到 临时下标中
			operRecursive(controller, srcIndex, tempIndex, n - 1);

			// 步骤2、把最下面的 第n个 从 src 移到 目标下标
			operDetail(controller, srcIndex, targetIndex);

			// 步骤3、把 n-1个 从 临时下标 移到 目标下标
			operRecursive(controller, tempIndex, targetIndex, n - 1);
		}
	}

	private void operDetail(IControlable controller, int srcIndex,
			int targetIndex) {
		// 步骤1、移动到 srcIndex
		moveToIndex(controller, srcIndex);

		// 步骤2、取出
		controller.takeUp();
		int currentObj = controller.getCurrentObj();

		// 步骤3、移动到 targetIndex
		moveToIndex(controller, targetIndex);

		// 步骤4、放下
		controller.dropDown();

//		System.out.println("operDetail->将 [" + currentObj + "] 从  [" + srcIndex
//				+ "] 移动到 [" + targetIndex + "]");
	}

	private void moveToIndex(IControlable controller, int index) {
		// 移动机器人到 srcIndex
		if (controller.getCurrentIndex() > index) {
			int steps = controller.getCurrentIndex() - index;
			for (int i = 0; i < steps; i++) {
				controller.backwards();
			}
		} else if (controller.getCurrentIndex() < index) {
			int steps = index - controller.getCurrentIndex();
			for (int i = 0; i < steps; i++) {
				controller.onwards();
			}
		} else {
			// do nothing
		}
	}
}

/**
 * 
 * 搬运机器人控制接口：提供机器人运动即操作控制函数
 */
interface IControlable {
	/**
	 * 向前移动一格：（currentIndex++）
	 */
	void onwards();

	/**
	 * 向后移动一格；（currentIndex--）
	 */
	void backwards();

	/**
	 * 拿起牌
	 */
	void takeUp();

	/**
	 * 放下牌
	 */
	void dropDown();

	/**
	 * 获取机器人当前所在塔的标号
	 * 
	 * @return 塔的下标，取值为0,1,2
	 */
	int getCurrentIndex();

	/**
	 * 获取塔的规模
	 * 
	 * @return
	 */
	int getTowerSize();

	/**
	 * 获取塔初始位置标号
	 * 
	 * @return
	 */
	int getSrcIndex();

	/**
	 * 获取塔目标位置标号
	 * 
	 * @return
	 */
	int getTargetIndex();

	int getCurrentObj();
}

/**
 * 结果获取接口，供testcase使用，不需要关注
 * 
 * @author byj
 * 
 */
interface IGetResult {
	int popResult();
}

class Cargador implements IControlable, IGetResult {

	private final int MAX_INDEX = 2;
	private final int MIN_INDEX = 0;
	private List<Stack<Integer>> source = null;

	private int towerSize = 0;
	private int currentIndex;
	private int src;
	private int dest;
	private Integer currentObj = null;

	public Cargador(int size, int currentIndex, int srcIndex, int destIndex) {

		this.towerSize = size;
		this.currentIndex = currentIndex;
		this.src = srcIndex;
		this.dest = destIndex;

		source = new ArrayList<Stack<Integer>>();

		Stack<Integer> tower = null;
		for (int i = MIN_INDEX; i <= MAX_INDEX; i++) {
			if (i == src) {
				tower = createOrigTower();
				source.add(tower);
				continue;
			}

			tower = new Stack<Integer>();
			source.add(tower);
		}
	}

	private Stack<Integer> createOrigTower() {
		Stack<Integer> stack = new Stack<Integer>();
		for (int i = towerSize; i > 0; i--) {
			stack.push(Integer.valueOf(i));
		}

		return stack;
	}

	@Override
	public void onwards() {

		if (currentIndex == MAX_INDEX) {
			return;
		}
		currentIndex++;
	}

	@Override
	public void backwards() {
		if (currentIndex == MIN_INDEX) {
			return;
		}
		currentIndex--;
	}

	@Override
	public void takeUp() {

		Stack<Integer> tower = source.get(currentIndex);
		currentObj = tower.pop();

	}

	@Override
	public void dropDown() {

		Stack<Integer> tower = source.get(currentIndex);
		tower.push(currentObj);

	}

	@Override
	public int getCurrentIndex() {

		return currentIndex;
	}

	@Override
	public int getTowerSize() {
		return towerSize;
	}

	@Override
	public int getSrcIndex() {
		// TODO Auto-generated method stub
		return src;
	}

	@Override
	public int getTargetIndex() {
		// TODO Auto-generated method stub
		return dest;
	}

	@Override
	public int popResult() {
		Stack<Integer> tower = source.get(dest);
		return tower.pop().intValue();
	}

	@Override
	public int getCurrentObj() {

		if (currentObj == null) {
			return -1;
		} else {
			return currentObj.intValue();
		}

	}

}
