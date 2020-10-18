/**
 * 
 */
package br.com.hotel.service;

import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.hotel.dtos.CheckInDTO;
import br.com.hotel.helper.Geral;
import br.com.hotel.models.CheckIn;
import br.com.hotel.models.Hospede;
import br.com.hotel.repository.CheckInRepository;
import br.com.hotel.repository.IGenericCrud;
import br.com.hotel.utils.Utils;

/**
 * @author anonymous
 *
 */

@Service
public class CheckInService implements IGenericCrud<CheckIn>{

	public double valorDiariaExtra(Calendar dataInicio, Calendar dataFim) {
		if(dataFim.getTime().getHours() >= Utils.HORARIO_LIMITE_DIARIA_FINAL_SEMANA && dataFim.getTime().getMinutes() > Utils.MINUTOS_LIMITE_DIARIA_FINAL_SEMANA) {
			if(Geral.isUltrapassarLimiteDeSemana(dataFim.DAY_OF_WEEK)) {
				return Utils.VALOR_DIARIA_FINAL_SEMANA;
			}
			return Utils.VALOR_DIARIA_SEMANA;
		}
		return 0.0;
	}
	
	public double calcularValorDiarias(CheckIn checkin, double valorDiaria, double valorAcrescimo, boolean semana, boolean fds) {
	    int dias = Geral.computarQtdDias(checkin.getDataEntrada(), checkin.getDataSaida(), semana, fds);
	    double valorAdicional = 0.0;
	    if(dias > 0 && checkin.isAdicionalVeiculo()) {
	    	valorAdicional = valorAcrescimo;
	    }
	    
	    return (dias * (valorDiaria + valorAdicional));
	    
	}
	
	public double calcularValorTotalDiaria(CheckIn checkin) {
		
		if(checkin.getDataSaida().before(Calendar.getInstance())) {
			double valorSemana = calcularValorDiarias(checkin, Utils.VALOR_DIARIA_SEMANA, Utils.VALOR_ACRESCIMO_DIARIA_SEMANA, true, false);
			double valorFinalSemana = calcularValorDiarias(checkin, Utils.VALOR_DIARIA_FINAL_SEMANA, Utils.VALOR_ACRESCIMO_DIARIA_FINAL_SEMANA, false, true);
			double valorDiariaExtra = valorDiariaExtra(checkin.getDataEntrada(), checkin.getDataSaida());
			return valorSemana + valorFinalSemana + valorDiariaExtra;
		}
		return 0.0;
	}
	
	/* ------------- COMPUTACAO --------------- */
	
	@Autowired
	private CheckInRepository checkInRepository;

	@Override
	public List<CheckIn> findAll() {
		return checkInRepository.findAll();
	}

	@Override
	public Optional<CheckIn> findById(long id) {
		return checkInRepository.findById(id);
	}
	
	@Override
	public CheckIn save(CheckIn checkIn) {
		return checkInRepository.save(checkIn);
	}
	
	public CheckIn salvarOuAtualizar(CheckInDTO dto, Long id) {
		CheckIn checkin = dto.transformaParaObjeto();
		
		if(id != null) {
			checkin.setId(id);
		}
			
		checkin.setValorTotal(calcularValorTotalDiaria(checkin));
		
		CheckIn in = checkInRepository.valorUltimaHospedagemPorId(checkin.getHospede().getId(), checkin.getDataEntrada().getTime());
		double ultimaHospedagem = (in != null ? in.getValorTotal() : 0.0);
		
		checkin.setValorUltimaHospedagem(ultimaHospedagem);
		
		return checkInRepository.save(checkin);
	}

	@Override
	public void delete(long id) {
		checkInRepository.deleteById(id);
	}
	
	/* ---------------- Buscas -------------- */
	
	public List<CheckIn> findByHospedeNome(String nome) {
		return checkInRepository.findByHospedeNomeLike(nome);
	}
	
	public List<CheckIn> findByHospedeDocumento(String documento) {
		return checkInRepository.findByHospedeDocumento(documento);
	}
	
	public List<CheckIn> findByHospedeTelefone(String telefone) {
		return checkInRepository.findByHospedeTelefone(telefone);
	}

	public List<CheckIn> verificarHospedesNoHotel() {
		return checkInRepository.verificarHospedeNoHotel();
	}
	
	public List<CheckIn> verificarHospedesForaDoHotel() {
		return checkInRepository.verificarHospedesForaDoHotel();
	}
	
	public CheckIn verificarHospedeNoHotelPorId(long id) {
		return checkInRepository.verificarHospedeNoHotelPorId(id);
	}
	
	public CheckIn verificarHospedesForaDoHotelPorId(long id) {
		return checkInRepository.verificarHospedesForaDoHotelPorId(id);
	}


	
}
