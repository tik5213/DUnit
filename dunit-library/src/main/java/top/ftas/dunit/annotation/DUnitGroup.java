package top.ftas.dunit.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Inherited;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import top.ftas.dunit.util.DUnitConstant;

/**
 * Created by tik on 17/6/29.
 */

@Inherited
@Retention(RetentionPolicy.CLASS)
@Target(ElementType.TYPE)
public @interface DUnitGroup {
	String value() default DUnitConstant.Sys.DEFAULT_VALUE_STRING;
	String name() default DUnitConstant.Sys.DEFAULT_VALUE_STRING;
	int priority() default DUnitConstant.Sys.DEFAULT_VALUE_INT;
	Class group() default DUnitConstant.Sys.DEFAULT_VALUE_GROUP.class;
}
