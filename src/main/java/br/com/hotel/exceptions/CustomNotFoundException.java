/**
 * 
 */
package br.com.hotel.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

/**
 * @author anonymous
 *
 */

@AllArgsConstructor
@ResponseStatus(value = HttpStatus.NOT_FOUND)
public class CustomNotFoundException extends RuntimeException{

	@Getter @Setter private String mensagem;
	

	
}