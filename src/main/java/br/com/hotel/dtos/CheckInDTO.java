/**
 * 
 */
package br.com.hotel.dtos;

import java.time.format.DateTimeFormatter;
import java.util.Calendar;

import javax.persistence.Column;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.validation.constraints.NotBlank;

import br.com.hotel.helper.Geral;
import br.com.hotel.models.CheckIn;
import br.com.hotel.models.Hospede;
import lombok.Getter;
import lombok.Setter;

/**
 * @author anonymous
 *
 */

@Getter
public class CheckInDTO {

	private Hospede hospede;
	
	@NotBlank(message = "Informar data de entrada")
	private String dataEntrada;
		
	private String dataSaida;
	
	private boolean adicionalVeiculo;
	
	public CheckIn transformaParaObjeto(){
	    return new CheckIn(null, hospede, Geral.convertToCalendar(dataEntrada), Geral.convertToCalendar(dataSaida), adicionalVeiculo, 0.0, 0.0);
	}
	
}
