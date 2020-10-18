/**
 * 
 */
package br.com.hotel.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.hotel.models.Hospede;

/**
 * @author anonymous
 *
 */
@Repository
public interface HospedeRepository extends JpaRepository<Hospede, Long> {

}
