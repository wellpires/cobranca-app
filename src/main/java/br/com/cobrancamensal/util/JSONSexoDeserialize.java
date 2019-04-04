package br.com.cobrancamensal.util;

import java.io.IOException;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;

import br.com.cobrancamensal.enums.Sexo;

public class JSONSexoDeserialize extends JsonDeserializer<Sexo> {

	@Override
	public Sexo deserialize(JsonParser jp, DeserializationContext dc) throws IOException, JsonProcessingException {
		return Sexo.valueOf(jp.getValueAsString());
	}

}
