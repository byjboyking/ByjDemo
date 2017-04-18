package jnjd.hannoitaRobot;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

public class HannoitaRobot {

	public void manager(IControlable controller) {
		// TODO: ��д�Լ��Ĵ���
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

	// ��ŵ���ĵݹ��㷨ʵ��
	private void operRecursive(IControlable controller, int srcIndex,
			int targetIndex, int n) {
		int tempIndex = getTempIndex(srcIndex, targetIndex);

		if (n == 1) {
			operDetail(controller, srcIndex, targetIndex);
		} else {
			// ����1���� ������� n-1 �� �� src �Ƶ� ��ʱ�±���
			operRecursive(controller, srcIndex, tempIndex, n - 1);

			// ����2����������� ��n�� �� src �Ƶ� Ŀ���±�
			operDetail(controller, srcIndex, targetIndex);

			// ����3���� n-1�� �� ��ʱ�±� �Ƶ� Ŀ���±�
			operRecursive(controller, tempIndex, targetIndex, n - 1);
		}
	}

	private void operDetail(IControlable controller, int srcIndex,
			int targetIndex) {
		// ����1���ƶ��� srcIndex
		moveToIndex(controller, srcIndex);

		// ����2��ȡ��
		controller.takeUp();
		int currentObj = controller.getCurrentObj();

		// ����3���ƶ��� targetIndex
		moveToIndex(controller, targetIndex);

		// ����4������
		controller.dropDown();

//		System.out.println("operDetail->�� [" + currentObj + "] ��  [" + srcIndex
//				+ "] �ƶ��� [" + targetIndex + "]");
	}

	private void moveToIndex(IControlable controller, int index) {
		// �ƶ������˵� srcIndex
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
 * ���˻����˿��ƽӿڣ��ṩ�������˶����������ƺ���
 */
interface IControlable {
	/**
	 * ��ǰ�ƶ�һ�񣺣�currentIndex++��
	 */
	void onwards();

	/**
	 * ����ƶ�һ�񣻣�currentIndex--��
	 */
	void backwards();

	/**
	 * ������
	 */
	void takeUp();

	/**
	 * ������
	 */
	void dropDown();

	/**
	 * ��ȡ�����˵�ǰ�������ı��
	 * 
	 * @return �����±꣬ȡֵΪ0,1,2
	 */
	int getCurrentIndex();

	/**
	 * ��ȡ���Ĺ�ģ
	 * 
	 * @return
	 */
	int getTowerSize();

	/**
	 * ��ȡ����ʼλ�ñ��
	 * 
	 * @return
	 */
	int getSrcIndex();

	/**
	 * ��ȡ��Ŀ��λ�ñ��
	 * 
	 * @return
	 */
	int getTargetIndex();

	int getCurrentObj();
}

/**
 * �����ȡ�ӿڣ���testcaseʹ�ã�����Ҫ��ע
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
