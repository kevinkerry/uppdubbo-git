package com.csii.upp.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;


/**
 * 
 * @author xujin
 *
 */
@Target(ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)
public @interface AttributeProperties{
	/**
	 * 是否为必须值
	 * @return
	 */
	boolean required() default false;
	/**
	 * 如果有值就验证域唯一值
	 * @return
	 */
	String [] checkConstraint() default {};
	
	/**
	 * Param 1 is precision of BigDecimal default 20. <br/>
	 * Param 2 is scale of BigDecimal default 2.
	 */
	int[] decimal() default {13,2};
	
	/**
	 * 日期格式化
	 * @return
	 */
	String dateFormat() default "";
}
