package br.com.cobrancamensal.controller;

import static org.hamcrest.Matchers.equalTo;
import static org.junit.Assert.assertThat;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.doThrow;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;

import java.net.URI;
import java.util.HashMap;
import java.util.Map;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.util.UriComponents;
import org.springframework.web.util.UriComponentsBuilder;

import com.fasterxml.jackson.databind.ObjectMapper;

import br.com.cobrancamensal.controller.advice.CobrancaControllerAdvice;
import br.com.cobrancamensal.exception.ClienteNotFoundException;
import br.com.cobrancamensal.exception.ContratoDuplicadoException;
import br.com.cobrancamensal.exception.PlanoNotFoundException;
import br.com.cobrancamensal.response.ErrorResponse;
import br.com.cobrancamensal.service.ContratoService;

@RunWith(SpringJUnit4ClassRunner.class)
public class ContratoControllerTest {

	private static final String POST_CONTRATAR = "/api/v1/contrato/cliente/{cpf}/plano/{nomePlano}/contratar";

	@InjectMocks
	private ContratoController contratoController;

	@Mock
	private ContratoService contratoService;

	private MockMvc mockMVC;

	private ObjectMapper mapper;

	@Before
	public void setup() {

		MockitoAnnotations.initMocks(this);

		this.mockMVC = MockMvcBuilders.standaloneSetup(contratoController)
				.setControllerAdvice(new CobrancaControllerAdvice()).build();

		this.mapper = new ObjectMapper();

	}

	@Test
	public void deveContratarPlano() throws Exception {

		Map<String, Object> parametros = new HashMap<>();
		parametros.put("cpf", 123456);
		parametros.put("nomePlano", "plano teste");

		UriComponents uri = UriComponentsBuilder.newInstance().path(POST_CONTRATAR).buildAndExpand(parametros);

		MvcResult response = this.mockMVC.perform(post(uri.toUri())).andDo(print()).andReturn();

		assertThat("Deve contratar o plano com sucesso", HttpStatus.valueOf(response.getResponse().getStatus()),
				equalTo(HttpStatus.NO_CONTENT));

	}

	@Test
	public void naoDeveContratarPlanoPoisClienteNaoFoiEncontrado() throws Exception {

		doThrow(new ClienteNotFoundException()).when(contratoService).contratarPlano(anyLong(), anyString());

		Map<String, Object> parametros = new HashMap<>();
		parametros.put("cpf", 123456);
		parametros.put("nomePlano", "plano teste");

		URI postContratar = UriComponentsBuilder.newInstance().path(POST_CONTRATAR).buildAndExpand(parametros).toUri();

		MvcResult response = this.mockMVC.perform(post(postContratar)).andDo(print()).andReturn();

		ErrorResponse errorResponse = mapper.readValue(response.getResponse().getContentAsString(),
				ErrorResponse.class);
		assertThat("Deve retornar mensagem de erro", errorResponse.getMessage(),
				equalTo(new ClienteNotFoundException().getMessage()));
		assertThat("Não deve contratar o plano pois o cliente informado não existe",
				HttpStatus.valueOf(response.getResponse().getStatus()), equalTo(HttpStatus.NOT_FOUND));

	}

	@Test
	public void naoDeveContratarPlanoPoisPlanoNaoFoiEncontrado() throws Exception {

		doThrow(new PlanoNotFoundException()).when(contratoService).contratarPlano(anyLong(), anyString());

		Map<String, Object> parametros = new HashMap<>();
		parametros.put("cpf", 123456);
		parametros.put("nomePlano", "plano teste");

		UriComponents uri = UriComponentsBuilder.newInstance().path(POST_CONTRATAR).buildAndExpand(parametros);

		MvcResult response = this.mockMVC.perform(post(uri.toUri())).andDo(print()).andReturn();

		ErrorResponse errorResponse = mapper.readValue(response.getResponse().getContentAsString(),
				ErrorResponse.class);
		assertThat("Deve retornar mensagem de erro", errorResponse.getMessage(),
				equalTo(new PlanoNotFoundException().getMessage()));
		assertThat("Não deve contratar o plano pois o plano informado não existe",
				HttpStatus.valueOf(response.getResponse().getStatus()), equalTo(HttpStatus.NOT_FOUND));

	}

	@Test
	public void naoDeveContratarPlanoPoisOContratoJaExiste() throws Exception {

		String cpf = "12345678901";
		String nomePlano = "Plano teste";
		doThrow(new ContratoDuplicadoException(cpf, nomePlano)).when(contratoService).contratarPlano(anyLong(),
				anyString());

		Map<String, Object> parametros = new HashMap<>();
		parametros.put("cpf", 123456);
		parametros.put("nomePlano", "plano teste");

		UriComponents uri = UriComponentsBuilder.newInstance().path(POST_CONTRATAR).buildAndExpand(parametros);

		MvcResult response = this.mockMVC.perform(post(uri.toUri())).andDo(print()).andReturn();

		ErrorResponse errorResponse = mapper.readValue(response.getResponse().getContentAsString(),
				ErrorResponse.class);
		assertThat("Deve retornar mensagem de erro", errorResponse.getMessage(),
				equalTo(new ContratoDuplicadoException(cpf, nomePlano).getMessage()));
		assertThat("Não deve contratar o plano pois o contrato requerido já existe",
				HttpStatus.valueOf(response.getResponse().getStatus()), equalTo(HttpStatus.CONFLICT));

	}

}
