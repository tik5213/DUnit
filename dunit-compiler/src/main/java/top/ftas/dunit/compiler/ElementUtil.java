package top.ftas.dunit.compiler;

import javax.lang.model.element.Element;
import javax.lang.model.element.TypeElement;
import javax.lang.model.util.Types;

/**
 * Created by tik on 17/7/6.
 */

public class ElementUtil {
	public static TypeElement getSuperElement(Types types,Element element){
		if (element instanceof TypeElement){
			TypeElement typeElement = (TypeElement) element;
			Element superElement = types.asElement(typeElement.getSuperclass());
			return (TypeElement) superElement;
		}
		return null;
	}
}
