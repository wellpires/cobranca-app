package br.com.cobrancamensal.enums;

import java.util.Objects;

public enum Sexo {

	M("M", "Masculino"), F("F", "Feminino");

	private String codigo;
	private String texto;

	private Sexo(String codigo, String texto) {
		this.codigo = codigo;
		this.texto = texto;
	}

	public static Sexo parseOf(String value) {
		return findSexo(value);
	}

	private static Sexo findSexo(String value) {
		for (Sexo sexo : values()) {
			if (sexo.codigo.equalsIgnoreCase(value) || sexo.texto.equalsIgnoreCase(value)) {
				return sexo;
			}
		}
		return null;
	}

	public static boolean exists(String value) {
		return Objects.nonNull(findSexo(value));
	}

}
