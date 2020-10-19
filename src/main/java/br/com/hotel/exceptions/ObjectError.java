/**
 * 
 */
package br.com.hotel.exceptions;

import lombok.AllArgsConstructor;
import lombok.ToString;

/**
 * @author anonymous
 *
 */

@ToString
@AllArgsConstructor
public class ObjectError {
    private final String message;
    private final String field;
    private final Object parameter;
}
