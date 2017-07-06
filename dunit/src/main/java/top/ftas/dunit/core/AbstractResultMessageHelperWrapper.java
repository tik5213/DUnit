package top.ftas.dunit.core;

/**
 * Created by tik on 17/7/6.
 */

public abstract class AbstractResultMessageHelperWrapper implements ResultMessageHelper{
	protected ResultMessageHelper mOriginalMessageHelper;
	public AbstractResultMessageHelperWrapper(ResultMessageHelper messageHelper){
		mOriginalMessageHelper = messageHelper;
	}
	@Override
	public void print(String resultMessage) {
		mOriginalMessageHelper.print(resultMessage);
	}

	@Override
	public void printLine(String resultMessage) {
		mOriginalMessageHelper.printLine(resultMessage);
	}

	@Override
	public void append(String resultMessage) {
		mOriginalMessageHelper.append(resultMessage);
	}

	@Override
	public void appendLine(String resultMessage) {
		mOriginalMessageHelper.appendLine(resultMessage);
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
