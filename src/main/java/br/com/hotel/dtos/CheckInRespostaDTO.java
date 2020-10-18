/**
 * 
 */
package br.com.hotel.dtos;

import javax.persistence.Column;

import br.com.hotel.helper.Geral;
import br.com.hotel.models.CheckIn;
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
public class CheckInRespostaDTO {

	private Long id;
	
	private Hospede hospede;
	
	private String dataEntrada;
							
	private String dataSaida;
					
	private boolean adicionalVeiculo;
	
	private double valorTotal;
	
	private double valorUltimaHospedagem;
	
	public static CheckInRespostaDTO transformaEmDTO(CheckIn checkIn) {
		return new CheckInRespostaDTO(checkIn.getId(), 
										checkIn.getHospede(), 
										Geral.converToString(checkIn.getDataEntrada()), 
										Geral.converToString(checkIn.getDataSaida()), 
										checkIn.isAdicionalVeiculo(), 
										checkIn.getValorTotal(), 
										checkIn.getValorUltimaHospedagem()
									);
	}
}
