package top.ftas.dunit.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Inherited;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import top.ftas.dunit.group.DUnitGroupInterface;
import top.ftas.dunit.group.DUnitRootGroup;
import top.ftas.dunit.util.DUnitConstant;
import top.ftas.dunit.util.ThreadModel;

import static top.ftas.dunit.util.DUnitConstant.Sys.DEFAULT_VALUE_THREAD_MODEL;


/**
 * Created by tik on 17/6/27.
 * 单元展示注解类
 * Inherited、Retention CLASS以上、Target TYPE三者结合方能实现注解的继承
 */

@Inherited //允许接口继承
@Retention(RetentionPolicy.CLASS) //注解保留到类文件中
@Target(ElementType.TYPE)  //类类型的注解
public @interface DUnit{
	String value() default DUnitConstant.Sys.DEFAULT_VALUE_STRING;
	String name() default DUnitConstant.Sys.DEFAULT_VALUE_STRING;
	int priority() default DUnitConstant.Sys.DEFAULT_VALUE_INT;
	int threadMode() default DEFAULT_VALUE_THREAD_MODEL;
	Class<? extends DUnitGroupInterface> group() default DUnitConstant.Sys.DEFAULT_VALUE_GROUP.class;
}
