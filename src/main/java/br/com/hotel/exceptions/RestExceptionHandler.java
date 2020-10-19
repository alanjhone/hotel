/**
 * 
 */
package br.com.hotel.exceptions;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

/**
 * @author anonymous
 *
 */

@RestControllerAdvice
public class RestExceptionHandler extends ResponseEntityExceptionHandler{
	
	@ExceptionHandler(CustomNotFoundException.class)
	public final ResponseEntity<Object> handleCustomNotFoundException(final Exception ex, final WebRequest request) {
		CustomNotFoundException e = (CustomNotFoundException) ex;
		
        return new ResponseEntity<>(e.getMensagem(), HttpStatus.BAD_REQUEST);
	}
	
    @ExceptionHandler(Exception.class)
    public final ResponseEntity<Object> handleAllExceptions(final Exception ex, final WebRequest request) {
        final List<String> details = new ArrayList<>();
        details.add(ex.getLocalizedMessage());
        return new ResponseEntity("Erro de processamento. Por favor, tente novamente!", HttpStatus.INTERNAL_SERVER_ERROR);
    }
	
	@Override
	protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex, HttpHeaders headers, HttpStatus status, WebRequest request) {
	    List<ObjectError> errors = getErrors(ex);
	    ErrorResponse errorResponse = getErrorResponse(ex, status, errors);
        return new ResponseEntity<>(errorResponse, status);
	}
	
	
    private ErrorResponse getErrorResponse(MethodArgumentNotValidException ex, HttpStatus status, List<ObjectError> errors) {
        return new ErrorResponse("A requisição possui campos inválidos", status.value(),
                status.getReasonPhrase(), ex.getBindingResult().getObjectName(), errors);
    }

	private List<ObjectError> getErrors(MethodArgumentNotValidException ex) {
	    return ex.getBindingResult().getFieldErrors().stream()
	            .map(error -> new ObjectError(error.getDefaultMessage(), error.getField(), error.getRejectedValue()))
	            .collect(Collectors.toList());
	}
	
}
