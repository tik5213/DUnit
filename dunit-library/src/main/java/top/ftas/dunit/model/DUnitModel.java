package top.ftas.dunit.model;

import top.ftas.dunit.util.ThreadModel;

/**
 * Created by tik on 17/6/28.
 */

public class DUnitModel extends DUnitBaseModel{
	private ThreadModel threadMode;

	public ThreadModel getThreadMode() {
		return threadMode;
	}

	public void setThreadMode(ThreadModel threadMode) {
		this.threadMode = threadMode;
	}

	@Override
	public ModelType getModelType() {
		return ModelType.MODEL_TYPE_UNIT;
	}
}
