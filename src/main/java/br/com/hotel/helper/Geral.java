/**
 * 
 */
package br.com.hotel.helper;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.format.DateTimeFormatter;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

/**
 * @author anonymous
 *
 */
public class Geral {

	public static boolean isFinalDeSemana(int dia) {
		return (dia == Calendar.SATURDAY || dia == Calendar.SUNDAY);
	}
	
	public static boolean isUltrapassarLimiteDeSemana(int dia) {
		return (dia == Calendar.FRIDAY || dia == Calendar.SATURDAY);
	}
	
	public static boolean isUltrapassarLimiteFinalSemana(int dia) {
		return (dia == Calendar.SUNDAY);
	}
	
	public static boolean isValidDate(String data) {
		if(convertToCalendar(data) != null) {
			return true;
		}
		return false;
	}
	
	public static Calendar convertToCalendar(String data) {
		SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss");
        Date date;
		Calendar calendar = new GregorianCalendar();
		try {
			date = formatter.parse(data);
	        calendar.setTime(date);
		} catch (ParseException e) {
			e.printStackTrace();
			return null;
		}
		return calendar;
	}
	
	public static String converToString(Calendar calendar) {
		SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss");
		String date = formatter.format(calendar.getTime());
	    return date;
	}
	
	public static int computarQtdDias(Calendar entrada, Calendar saida, boolean semana, boolean fds) {
	    int qtd = 0;
	    Calendar inicioTmp = new GregorianCalendar();
	    Calendar fimTmp = new GregorianCalendar();
	    
	    inicioTmp.setTime(entrada.getTime());
	    fimTmp.setTime(saida.getTime());
	    
	    while (inicioTmp.before(fimTmp)) {
	    	int dia = inicioTmp.get(Calendar.DAY_OF_WEEK);
		  
			if(semana) {
				if (!isFinalDeSemana(dia)) {
					qtd++;
				}
		  	}else {
		  		if (isFinalDeSemana(dia)) {
		  			qtd++;
		  		}
			}
			inicioTmp.add(Calendar.DAY_OF_MONTH, 1);
	    }
		
		return qtd;
	}


	
}
