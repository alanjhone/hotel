/**
 * 
 */
package br.com.hotel.dtos;

import br.com.hotel.models.Hospede;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * @author anonymous
 *
 */

@Getter
@Setter
@NoArgsConstructor @AllArgsConstructor
public class HospedeRespostaDTO {

	private Long id;
	
	private String nome;
	
	private String documento;
	
	private String telefone;
	
	public static HospedeRespostaDTO transformaEmDTO(Hospede hospede) {
		return new HospedeRespostaDTO(hospede.getId(), hospede.getNome(), hospede.getDocumento(), hospede.getTelefone());
	}
	
}
