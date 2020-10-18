/**
 * 
 */
package br.com.hotel.controllers;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import br.com.hotel.dtos.HospedeDTO;
import br.com.hotel.dtos.HospedeRespostaDTO;
import br.com.hotel.exceptions.CustomNotFoundException;
import br.com.hotel.models.Hospede;
import br.com.hotel.service.HospedeService;
import br.com.hotel.utils.Utils;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

/**
 * @author anonymous
 *
 */

@RestController
@RequestMapping("/api")
@Api(value = "API REST HÓSPEDES")
@CrossOrigin(origins = "*")
public class HospedeController {

	@Autowired
	private HospedeService hospedeService;
	
	@GetMapping(value="/hospedes")
	@ApiOperation(value = "Retorna uma lista de hóspedes")
    public List<Hospede> findAll(){
        return hospedeService.findAll();
    }
	
	@GetMapping(value="/hospedes/{id}")
	@ApiOperation(value = "Retorna um hóspede através da chave Id")
    ResponseEntity<Hospede> buscarPorId(@PathVariable("id") int id) {
		Hospede hospede = hospedeService.findById(id)
						                .orElseThrow(
						                		()-> new CustomNotFoundException(Utils.MSG_REGISTO_NAO_ENCONTRADO + id)
						        		);
		
        return ResponseEntity.ok().body(hospede);
	}
	
	@PostMapping(value="/hospede")
	@ApiOperation(value = "Salva um hóspede")
	ResponseEntity<HospedeRespostaDTO> salvarUsuario(@RequestBody @Valid HospedeDTO dto) {
		Hospede hospede = hospedeService.save(dto);
		
		return ResponseEntity.ok().body(HospedeRespostaDTO.transformaEmDTO(hospede)); 
	}
	
    @PutMapping(value="/hospede/{id}")
    @ApiOperation(value = "Atualiza um hóspede")
    ResponseEntity<HospedeRespostaDTO> atualizarUsuario(@PathVariable("id")  int id, @RequestBody @Valid HospedeDTO dto) {
    	Hospede h = hospedeService.findById(id)
                                    .orElseThrow(()->new CustomNotFoundException(Utils.MSG_REGISTO_NAO_ENCONTRADO + id));
        
        h = hospedeService.save(dto);
        return ResponseEntity.ok().body(HospedeRespostaDTO.transformaEmDTO(h));
    }
    
    @DeleteMapping(value="/hospede/{id}")
    @ApiOperation(value = "Deleta um hóspede")
    ResponseEntity deletarUsuario( @PathVariable("id") int id) {
    	Hospede hospede = hospedeService.findById(id)
                                    .orElseThrow(()->new CustomNotFoundException(Utils.MSG_REGISTO_NAO_ENCONTRADO + id));
    	hospedeService.delete(hospede.getId());
        return ResponseEntity.ok().body(Utils.MSG_REGISTO_REMOVIDO);  
    }
	
	/* --------------- CRUD ------------------ */
    
    
    
    
    
    /* --------------- BUSCAS -----------------*/
}
