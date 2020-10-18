/**
 * 
 */
package br.com.hotel.dtos;

import javax.validation.constraints.NotBlank;

import br.com.hotel.models.Hospede;
import lombok.Getter;
import lombok.Setter;

/**
 * @author anonymous
 *
 */

@Getter
public class HospedeDTO {

	@NotBlank
	private String nome;
	
	@NotBlank
	private String documento;
	
	@NotBlank
	private String telefone;
	
	public Hospede transformaParaObjeto() {
		return new Hospede(null, nome, documento, telefone);
	}
	
}
