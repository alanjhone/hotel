/**
 * 
 */
package br.com.hotel.exceptions;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.ToString;

/**
 * @author anonymous
 *
 */

@ToString
@AllArgsConstructor
public class ErrorResponse {
    private final String message;
    private final int code;
    private final String status;
    private final String objectName;
    private final List<ObjectError> errors;
}
