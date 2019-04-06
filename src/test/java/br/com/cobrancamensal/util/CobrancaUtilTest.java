package br.com.cobrancamensal.util;

import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.greaterThanOrEqualTo;
import static org.hamcrest.Matchers.lessThanOrEqualTo;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertThat;

import java.time.LocalDate;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.Date;

import org.hamcrest.Matchers;
import org.junit.Test;

public class CobrancaUtilTest {

	@Test
	public void deveConverterStringParaLocalDate() {

		LocalDate localDate = CobrancaUtil
				.stringToLocalDate(LocalDate.now().format(DateTimeFormatter.ofPattern(Constantes.DATE_PATTERN_PT_BR)));

		assertThat("Deve converter data em string para LocalDate", localDate, equalTo(LocalDate.now()));

	}

	@Test
	public void naoDeveConverterStringParaLocalDateComStringVazioOuNula() {

		LocalDate localDate = CobrancaUtil.stringToLocalDate(null);

		assertNull("Não deve converter data formatada em LocalDate pois o parâmetro String está nulo ou vazio",
				localDate);

	}

	@Test
	public void deveConverterLocalDateParaDate() {
		LocalDate now = LocalDate.now();
		Date date = CobrancaUtil.localDateToDate(now);

		assertThat("Deve converter LocalDate para Date", date.toInstant().atZone(ZoneId.systemDefault()).toLocalDate(),
				equalTo(now));

	}

	@Test
	public void naoDeveConverterLocalDateParaDateComLocalDateNulo() {
		Date date = CobrancaUtil.localDateToDate(null);

		assertNull("Não deve converter LocalDate para Date pois o parâmetro está nulo", date);

	}

	@Test
	public void deveConverterLocalDateParaString() {

		String format = LocalDate.now().format(DateTimeFormatter.ofPattern(Constantes.DATE_PATTERN_PT_BR));
		String localDateString = CobrancaUtil.localDateToString(LocalDate.now());

		assertThat("Deve converter LocalDate para String", format, equalTo(localDateString));

	}

	@Test
	public void naoDeveConverterLocalDateParaStringComLocalDateNulo() {

		String localDateString = CobrancaUtil.localDateToString(null);

		assertNull("Não deve converter LocalDate para String pois o LocalDate está nulo", localDateString);

	}

	@Test
	public void deveConverterDateToLocalDate() {

		Date hoje = new Date();
		LocalDate localDate = CobrancaUtil.dateToLocalDate(hoje);

		assertThat("Deve converter Date para LocalDate", localDate,
				Matchers.equalTo(hoje.toInstant().atZone(ZoneId.systemDefault()).toLocalDate()));

	}

	@Test
	public void naoDeveConverterDateToLocalDateComDateNulo() {

		LocalDate localDate = CobrancaUtil.dateToLocalDate(null);

		assertNull("Não deve converter Date para LocalDate pois o Date está nulo", localDate);

	}

	@Test
	public void deveGerarNumeroAleatorioLong() {

		Long randomLongValue = CobrancaUtil.generateLongValue();

		assertThat("Deve retornar um valor Long aleatório", randomLongValue, lessThanOrEqualTo(Long.MAX_VALUE));
		assertThat("Deve retornar um valor Long aleatório", randomLongValue, greaterThanOrEqualTo(Long.MIN_VALUE));

	}

}
