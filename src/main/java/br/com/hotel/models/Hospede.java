/**
 * 
 */
package br.com.hotel.models;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

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
@NoArgsConstructor @AllArgsConstructor
@EqualsAndHashCode
public class Hospede implements Serializable{

	private static final long serialVersionUID = 1L;
	
	@Id @GeneratedValue(strategy = GenerationType.AUTO)
    @Getter private Long id;
	
	@Getter @Setter private String nome;
	
	@Getter @Setter private String documento;
	
	@Getter @Setter private String telefone;
	
}
