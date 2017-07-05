package top.ftas.dunit.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Inherited;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import top.ftas.dunit.group.DUnitGroupInterface;
import top.ftas.dunit.util.DUnitConstant;
import top.ftas.dunit.util.ThreadModel;

/**
 * Created by tik on 17/6/27.
 * 隐藏当前分组或单元
 */

@Retention(RetentionPolicy.CLASS) //注解保留到类文件中
@Target(ElementType.TYPE)  //类类型的注解
public @interface DUnitHidden {
}
