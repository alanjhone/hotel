/**
 * 
 */
package br.com.hotel.repository;

import java.util.Date;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import br.com.hotel.models.CheckIn;

/**
 * @author anonymous
 *
 */

@Repository
public interface CheckInRepository extends JpaRepository<CheckIn, Long> {
	
	@Query(value = "select c.* from check_in c INNER JOIN hospede h ON h.id = c.hospede_id  where lower(h.nome) like lower(concat('%', :nome,'%'))",  nativeQuery = true)
	public List<CheckIn> findByHospedeNomeLike(@Param("nome") String nome);

	public List<CheckIn> findByHospedeDocumento(@Param("documento") String documento);

	public List<CheckIn> findByHospedeTelefone(@Param("telefone") String telefone);
	
	@Query(value = "select c.* from check_in c INNER JOIN hospede h ON h.id = c.hospede_id WHERE data_saida < NOW()", nativeQuery = true)
	public List<CheckIn> verificarHospedesForaDoHotel();
	
	@Query(value = "select c.* from check_in c INNER JOIN hospede h ON h.id = c.hospede_id WHERE data_saida >= NOW()", nativeQuery = true)
	public List<CheckIn> verificarHospedeNoHotel();
	
	@Query(value = "select c.* from check_in c INNER JOIN hospede h ON h.id = c.hospede_id WHERE h.id = :id AND data_saida < NOW()", nativeQuery = true)
	public CheckIn verificarHospedesForaDoHotelPorId(@Param("id") Long id);
	
	@Query(value = "select c.* from check_in c INNER JOIN hospede h ON h.id = c.hospede_id WHERE h.id = :id AND data_saida >= NOW()", nativeQuery = true)
	public CheckIn verificarHospedeNoHotelPorId(@Param("id") Long id);
	
	@Query(value = "select c.* from check_in c INNER JOIN hospede h ON h.id = c.hospede_id \n"
			+ "WHERE h.id = :id AND data_saida <= :data ORDER BY data_saida DESC limit 1", nativeQuery = true)
	public CheckIn valorUltimaHospedagemPorId(@Param("id") Long id, @Param("data") Date data);
	
}
