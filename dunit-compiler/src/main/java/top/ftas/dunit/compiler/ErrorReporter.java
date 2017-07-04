package top.ftas.dunit.compiler;

import javax.annotation.processing.Messager;
import javax.annotation.processing.ProcessingEnvironment;
import javax.lang.model.element.Element;
import javax.tools.Diagnostic;

/**
 * Created by tik on 17/6/26.
 */

public class ErrorReporter {
	private final Messager mMessager;

	ErrorReporter(ProcessingEnvironment processingEnvironment){
		mMessager = processingEnvironment.getMessager();
	}

	void print(String msg){
		reportNote(msg);
	}

	void reportNote(String msg){
		mMessager.printMessage(Diagnostic.Kind.NOTE,msg);
	}

	void reportNote(String msg, Element e){
		mMessager.printMessage(Diagnostic.Kind.NOTE,msg,e);
	}

	void reportWaring(String msg){
		mMessager.printMessage(Diagnostic.Kind.WARNING,msg);
	}

	void reportWaring(String msg,Element e){
		mMessager.printMessage(Diagnostic.Kind.WARNING,msg,e);
	}

	void reportError(String msg){
		mMessager.printMessage(Diagnostic.Kind.ERROR,msg);
	}

	void reportError(String msg,Element e){
		mMessager.printMessage(Diagnostic.Kind.ERROR,msg,e);
	}

	void abortWithError(String msg){
		reportError(msg);
		throw new ProcessorAbortException();
	}

	void abortWithError(String msg,Element e){
		reportError(msg,e);
		throw new ProcessorAbortException();
	}
}
