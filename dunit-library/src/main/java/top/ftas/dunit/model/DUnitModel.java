package top.ftas.dunit.model;

import top.ftas.dunit.util.ThreadModel;

/**
 * Created by tik on 17/6/28.
 */

public class DUnitModel extends DUnitBaseModel{
	private int threadMode;

	public int getThreadMode() {
		return threadMode;
	}

	public void setThreadMode(int threadMode) {
		this.threadMode = threadMode;
	}

	@Override
	public ModelType getModelType() {
		return ModelType.MODEL_TYPE_UNIT;
	}
}
