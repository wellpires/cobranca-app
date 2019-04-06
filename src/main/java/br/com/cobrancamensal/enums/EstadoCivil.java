package br.com.cobrancamensal.enums;

import java.util.Objects;

public enum EstadoCivil {
	Solteiro, Casado, Viuvo, Divorciado;

	public static EstadoCivil parseOf(String estadoCivil) {
		return find(estadoCivil);
	}

	public static boolean exists(String estadoCivil) {
		return Objects.nonNull(find(estadoCivil));
	}

	private static EstadoCivil find(String estadoCivil) {
		for (EstadoCivil estadoCivilItem : values()) {
			if (estadoCivilItem.name().equalsIgnoreCase(estadoCivil)) {
				return estadoCivilItem;
			}
		}
		return null;
	}
}
