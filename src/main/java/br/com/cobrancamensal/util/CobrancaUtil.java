package br.com.cobrancamensal.util;

import java.security.SecureRandom;
import java.time.LocalDate;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.Objects;
import java.util.Random;

import org.apache.commons.lang3.StringUtils;

public class CobrancaUtil {

	private CobrancaUtil() {
	}

	public static LocalDate stringToLocalDate(String value) {
		if (StringUtils.isBlank(value)) {
			return null;
		}
		return LocalDate.parse(value, DateTimeFormatter.ofPattern(Constantes.DATE_PATTERN_PT_BR));
	}

	public static Date localDateToDate(LocalDate localDate) {
		if (Objects.isNull(localDate)) {
			return null;
		}
		return Date.from(localDate.atStartOfDay(ZoneId.systemDefault()).toInstant());
	}

	public static String localDateToString(LocalDate localDate) {
		if (Objects.isNull(localDate)) {
			return null;
		}
		return localDate.format(DateTimeFormatter.ofPattern(Constantes.DATE_PATTERN_PT_BR));
	}

	public static LocalDate dateToLocalDate(Date date) {
		if (Objects.isNull(date)) {
			return null;
		}
		return new Date(date.getTime()).toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
	}

	public static Long generateLongValue() {
		Random r = new SecureRandom();
		return r.longs(0, (Long.MAX_VALUE)).limit(1).findFirst().getAsLong();
	}

}
