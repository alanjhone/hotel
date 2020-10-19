/**
 * 
 */
package br.com.hotel.helper;

import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import javax.validation.Constraint;
import javax.validation.Payload;

import static java.lang.annotation.ElementType.FIELD;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

/**
 * @author anonymous
 *
 */

@Target({FIELD})
@Retention(RUNTIME)
@Constraint(validatedBy = DataValidator.class)
public @interface DataInvalida {
	String message() default "Valor inválido";
	 
    Class<?>[] groups() default {};
 
    Class<? extends Payload>[] payload() default {};
 
    String value() default "";
}
