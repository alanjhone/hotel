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
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import br.com.hotel.dtos.CheckInDTO;
import br.com.hotel.dtos.CheckInRespostaDTO;
import br.com.hotel.exceptions.CustomNotFoundException;
import br.com.hotel.models.CheckIn;
import br.com.hotel.service.CheckInService;
import br.com.hotel.utils.Utils;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

/**
 * @author anonymous
 *
 */

@RestController
@RequestMapping("/api")
@Api(value = "API REST CHECK IN")
@CrossOrigin(origins = "*")
public class CheckInController {

	@Autowired
	private CheckInService checkInService;
	
	@GetMapping(value="/checkins")
	@ApiOperation(value = "Retorna uma lista de check ins")
    public List<CheckIn> findAll(){
        return checkInService.findAll();
    }
	
	@GetMapping(value="/checkins/{id}")
	@ApiOperation(value = "Retorna um check in através da chave Id")
    ResponseEntity<CheckIn> buscarPorId(@PathVariable("id") int id) {
		CheckIn checkIn = checkInService.findById(id)
						                .orElseThrow(
						                		()-> new CustomNotFoundException(Utils.MSG_REGISTO_NAO_ENCONTRADO + id)
						        		);
		
        return ResponseEntity.ok().body(checkIn);
	}
	
	@PostMapping(value="/checkin")
	@ApiOperation(value = "Faz um check in")
	ResponseEntity<CheckInRespostaDTO> salvar(@RequestBody @Valid CheckInDTO checkInDTO) {
		CheckIn checkIn = checkInService.salvarOuAtualizar(checkInDTO, null);
		return ResponseEntity.ok().body(CheckInRespostaDTO.transformaEmDTO(checkIn)); 
	}
	
    @PutMapping(value="/checkin/{id}")
    @ApiOperation(value = "Atualiza um check in")
    ResponseEntity<CheckInRespostaDTO> atualizar(@PathVariable("id")  int id, @RequestBody @Valid CheckInDTO checkInDTO) {
    	CheckIn c = checkInService.findById(id)
                                    .orElseThrow(()->new CustomNotFoundException(Utils.MSG_REGISTO_NAO_ENCONTRADO + id));
        
    	c = checkInService.salvarOuAtualizar(checkInDTO, c.getId());
        return ResponseEntity.ok().body(CheckInRespostaDTO.transformaEmDTO(c));    
    }
    
    @DeleteMapping(value="/checkin/{id}")
    @ApiOperation(value = "Deleta um check in")
    ResponseEntity deletar( @PathVariable("id") int id) {
    	CheckIn checkIn = checkInService.findById(id)
                                    .orElseThrow(()->new CustomNotFoundException(Utils.MSG_REGISTO_NAO_ENCONTRADO + id));
    	checkInService.delete(checkIn.getId());
        return ResponseEntity.ok().body(Utils.MSG_REGISTO_REMOVIDO);  
    }
	
	/* --------------- CRUD ------------------ */
    
    @RequestMapping(method = RequestMethod.GET, value = "/checkin/hospedes/nome")
    @ApiOperation(value = "Buscar checkins por nome do hóspede")
    public List<CheckIn> findByHospedeNome(@RequestParam("nome") String nome) {
    	return checkInService.findByHospedeNome(nome);
    }
    
    @RequestMapping(method = RequestMethod.GET, value = "/checkin/hospedes/documento")
    @ApiOperation(value = "Buscar checkin por documento do hóspede")
    public List<CheckIn> findByHospedeDocumento(@RequestParam("documento") String documento) {
    	return checkInService.findByHospedeDocumento(documento);
    }
    
    @RequestMapping(method = RequestMethod.GET, value = "/checkin/hospedes/telefone")
    @ApiOperation(value = "Buscar checkin por telefone do hóspede")
    public List<CheckIn> findByDescricao(@RequestParam("telefone") String telefone) {
    	return checkInService.findByHospedeTelefone(telefone);
    }
    
    @GetMapping(value="/checkin/hospedes/foraDoHotel")
    @ApiOperation(value = "Consultar hóspedes que já realizaram o check in e não estão mais no hotel")
    public List<CheckIn> verificarHospedesForaDoHotel() {
    	return checkInService.verificarHospedesForaDoHotel();
    }
    
    @GetMapping(value="/checkin/hospedes/noHotel")
    @ApiOperation(value = "Consultar hóspedes que ainda estão no hotel")
    public List<CheckIn> verificarHospedesNoHotel() {
    	return checkInService.verificarHospedesNoHotel();
    }

    @GetMapping(value="/checkin/hospedes/foraDoHotel/{id}")
    @ApiOperation(value = "Consultar hóspedes que já realizaram o check in e não estão mais no hotel pela chave ID")
    public CheckIn verificarHospedesForaDoHotelPorId(@PathVariable("id") int id) {
    	return checkInService.verificarHospedesForaDoHotelPorId(id);
    }
    
    @GetMapping(value="/checkin/hospedes/noHotel/{id}")
    @ApiOperation(value = "Consultar hóspedes que ainda estão no hotel pela chave ID")
    public CheckIn verificarHospedeNoHotelPorId(@PathVariable("id") int id) {
    	return checkInService.verificarHospedeNoHotelPorId(id);
    }
    
    /* --------------- BUSCAS -----------------*/
	
}
