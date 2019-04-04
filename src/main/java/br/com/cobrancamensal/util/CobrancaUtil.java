package br.com.cobrancamensal.util;

import java.time.LocalDate;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.Objects;

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

}
