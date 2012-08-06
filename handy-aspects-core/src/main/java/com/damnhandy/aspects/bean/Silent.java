package com.damnhandy.aspects.bean;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * Advised classed with fields marked with this annotation 
 * will not fire change events
 * 
 * @author Ryan J. McDonough
 * @since 1.0
 * @version $Revision: 1.1 $
 */
@Target({ElementType.FIELD,ElementType.METHOD})
@Retention(value=RetentionPolicy.RUNTIME)
public @interface Silent {}
