/**
 * 
 */
package br.com.hotel.helper;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import com.google.common.base.Strings;

/**
 * @author anonymous
 *
 */
public class DataValidator implements ConstraintValidator<DataInvalida, String> {

	private String value;
	 
    @Override
    public void initialize(DataInvalida constraintAnnotation) {
        this.value = constraintAnnotation.value();
    }
 
    @Override
    public boolean isValid(String value, ConstraintValidatorContext constraintValidatorContext) {
        try {
        	if(!Strings.isNullOrEmpty(value) && !Geral.isValidDate(value))
	            return false;
        } catch (Exception e) {
            return false;
        }
        
        return true;
    }

}
