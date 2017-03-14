package com.oschina.learn.lib.apt.route;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * Annotated with a field to indicate this field can be injected between <b>bundle</b> and a entity
 */

@Target({ElementType.FIELD})
@Retention(RetentionPolicy.CLASS)
public @interface Arg {
    /**
     * rename key of field.if not see,field name should be used
     * @return key of field
     */
    String value() default "";
}
