package top.ftas.dunit.core;

/**
 * Created by tik on 17/7/6.
 */

public abstract class AbstractResultMessageHelperWrapper implements ResultMessageHelper{
	protected ResultMessageHelper mOriginalMessageHelper;
	public AbstractResultMessageHelperWrapper(ResultMessageHelper messageHelper){
		mOriginalMessageHelper = messageHelper;
	}

	public String processMessage(String resultMessage){
		return resultMessage;
	}

	@Override
	public void print(String resultMessage) {
		mOriginalMessageHelper.print(processMessage(resultMessage));
	}

	@Override
	public void printLine(String resultMessage) {
		mOriginalMessageHelper.printLine(processMessage(resultMessage));
	}

	@Override
	public void append(String resultMessage) {
		mOriginalMessageHelper.append(processMessage(resultMessage));
	}

	@Override
	public void appendLine(String resultMessage) {
		mOriginalMessageHelper.appendLine(processMessage(resultMessage));
	}

	@Override
	public void clean() {
		mOriginalMessageHelper.clean();
	}

	@Override
	public void hidden() {
		mOriginalMessageHelper.hidden();
	}

	@Override
	public void hiddenAndClean() {
		mOriginalMessageHelper.hiddenAndClean();
	}

	@Override
	public void show() {
		mOriginalMessageHelper.show();
	}
}
