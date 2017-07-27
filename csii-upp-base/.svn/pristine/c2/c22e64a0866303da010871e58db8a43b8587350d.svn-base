package com.csii.upp.annotation;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target(ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)
public @interface BaseObjClass {

	/**
	 * 如果该对象描述的是一个List，则指定类名
	 */
	@SuppressWarnings("rawtypes")
	Class getBaseObjClass();
}
