/**
 * 
 */
package br.com.hotel.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.hotel.dtos.HospedeDTO;
import br.com.hotel.models.Hospede;
import br.com.hotel.repository.HospedeRepository;
import br.com.hotel.repository.IGenericCrud;

/**
 * @author anonymous
 *
 */

@Service
public class HospedeService implements IGenericCrud<Hospede>{

	@Autowired
	private HospedeRepository hospedeRepository;
	
	@Override
	public List<Hospede> findAll() {
		return hospedeRepository.findAll();
	}

	@Override
	public Optional<Hospede> findById(long id) {
		return hospedeRepository.findById(id);
	}

	@Override
	public Hospede save(Hospede hospede) {
		return hospedeRepository.save(hospede);
	}

	public Hospede save(HospedeDTO dto) {
		return hospedeRepository.save(dto.transformaParaObjeto());
	}
	
	@Override
	public void delete(long id) {
		hospedeRepository.deleteById(id);
	}

}
