package top.ftas.dunit.model;

/**
 * Created by tik on 17/6/28.
 */

public class DUnitModel extends DUnitBaseModel{
	private int threadMode;
	private String unitType;
	private String paramJson;

	public String getParamJson() {
		return paramJson;
	}

	public void setParamJson(String paramJson) {
		this.paramJson = paramJson;
	}

	public String getUnitType() {
		return unitType;
	}

	public void setUnitType(String unitType) {
		this.unitType = unitType;
	}

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
