package br.com.guedes.sistemacomercial.util;

import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

/**
 * Classe com método staticos uteis.
 * 
 * @author Guedes
 *
 */
public class Util {
	
	private static final SimpleDateFormat simpleDateFormatDataHora = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
	private static final SimpleDateFormat simpleDateFormatData     = new SimpleDateFormat("dd/MM/yyyy");

	/**
	 * Obter a data atual no formato dd/MM/yyyy
	 * 
	 * @return String
	 */
	public static String obterDataAtual() {
		return simpleDateFormatData.format(new Date());
	}
	
	/**
	 * Obter a data atual no formato dd/MM/yyyy HH:mm:ss
	 * 
	 * @return String
	 */
	public static String obterDataHoraAtual() {
		return simpleDateFormatDataHora.format(new Date());
	}	
	
	/**
	 * Campo foi preenchido.
	 * 
	 * @param valor String
	 * @return boolean
	 */
	public static boolean isCampoPreenchido(String valor) {
		return (valor != null && !"".equals(valor.trim()));
	}
	
	/**
	 * Campo foi preenchido.
	 * 
	 * @param valor Long
	 * @return boolean
	 */
	public static boolean isCampoPreenchido(Long valor) {
		return (valor != null && !"".equals(valor) && valor == 0);
	}	
	
	/**
	 * Campo foi preenchido.
	 * 
	 * @param valor Long
	 * @return boolean
	 */
	public static boolean isCampoPreenchido(Integer valor) {
		return (valor != null && !"".equals(valor) && valor == 0);
	}
	
	/**
	 * Campo foi preenchido.
	 * 
	 * @param valor Long
	 * @return boolean
	 */
	public static boolean isCampoPreenchido(Double valor) {
		return (valor != null && !"".equals(valor) && valor == 0);
	}	
	
	/**
	 * Formatar data no formato "dd/mm/yyyy 00:00:00"
	 * 
	 * @param data Calendar
	 * @return String
	 */
	public static String converterCalendarParaString(final Calendar data) {
		if (data != null) {
			return simpleDateFormatDataHora.format(data.getTime());
		}
		return "";
	}	
	
	/**
	 * Formatar data no formato "yyyy-MM-dd"
	 * 
	 * @param data String
	 * @return String
	 */
	public static String formatarDataString(String data) {
		return data.substring(6, 10) + "-" + data.substring(3, 5) + "-" + data.substring(0, 2);
	}
	
	/**
	 * Converter data tipo String para data tipo Calendar.
	 * 
	 * @param valorData String
	 * @return Calendar
	 * @throws Exception
	 */
	public static Calendar converterStringParaCalendar(final String valorData) throws Exception {
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(simpleDateFormatDataHora.parse(valorData)); 
		return calendar;
	}
	
	/**
	 * Converter decimal String em BigDecimal.
	 * @param valorDecimal String
	 * @return BigDecimal
	 */
	public static BigDecimal converterDecimalStringParaBigDecimal(String valorDecimal) {
		valorDecimal = valorDecimal.replace("R$ ", "") ;
		valorDecimal = valorDecimal.replace(".", "") ;
		return new BigDecimal(valorDecimal.replace(",", "."));
	}
	
	/**
	 * Converter BigDecimal para String.
	 * @param valor BigDecimal
	 * @return String
	 */
	public static String converterBigDecimalParaStringDecimal(BigDecimal valor) {
		DecimalFormat decimalFormat = new DecimalFormat("R$ #,##0.00"); 
		return decimalFormat.format(valor);
	}	
}
