package top.ftas.dunit.core;

/**
 * Created by tik on 17/7/6.
 */

public interface ResultMessageHelper {
	void print(String resultMessage);
	void printLine(String resultMessage);
	void append(String resultMessage);
	void appendLine(String resultMessage);
	void clean();
	void hidden();
	void hiddenAndClean();
	void show();
}
