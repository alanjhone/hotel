/**
 * 
 */
package br.com.hotel.models;

import java.io.Serializable;
import java.util.Calendar;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * @author anonymous
 *
 */

@Entity
@Table(name = "check_in")
@NoArgsConstructor @AllArgsConstructor
@EqualsAndHashCode
public class CheckIn implements Serializable {

	private static final long serialVersionUID = 1L;
	
	@Setter @Id @GeneratedValue(strategy = GenerationType.AUTO)
    @Getter private Long id;
	
	@OneToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "hospede_id")
	@Getter @Setter private Hospede hospede;
	
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "data_entrada")
	@Getter @Setter private Calendar dataEntrada;
				
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "data_saida")
	@Getter @Setter private Calendar dataSaida;
					
	@Column(name = "adicional_veiculo")
	@Getter @Setter private boolean adicionalVeiculo;
	
	@Column(name = "valor_total")
	@Getter @Setter private double valorTotal;
	
	@Column(name = "valor_ultima_hospedagem")
	@Getter @Setter private double valorUltimaHospedagem;
	
}
