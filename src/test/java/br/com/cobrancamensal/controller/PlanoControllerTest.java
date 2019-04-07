package br.com.cobrancamensal.controller;

import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.hasSize;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertThat;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;

import org.apache.commons.lang3.StringUtils;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import com.fasterxml.jackson.databind.ObjectMapper;

import br.com.cobrancamensal.builder.NovoPlanoDTOBuilder;
import br.com.cobrancamensal.builder.PlanoDTOBuilder;
import br.com.cobrancamensal.controller.advice.CobrancaControllerAdvice;
import br.com.cobrancamensal.dto.NovoPlanoDTO;
import br.com.cobrancamensal.dto.PlanosDTO;
import br.com.cobrancamensal.response.ErrorResponse;
import br.com.cobrancamensal.service.PlanoService;
import br.com.cobrancamensal.util.Constantes;

@RunWith(SpringJUnit4ClassRunner.class)
public class PlanoControllerTest {

	private static final String PATH_APP = "/api/v1/planos";

	@InjectMocks
	private PlanoController planoController;

	@Mock
	private PlanoService planoService;

	private MockMvc mockMVC;

	private ObjectMapper mapper;

	@Before
	public void setup() {

		MockitoAnnotations.initMocks(this);

		this.mockMVC = MockMvcBuilders.standaloneSetup(planoController)
				.setControllerAdvice(new CobrancaControllerAdvice()).build();
		this.mapper = new ObjectMapper();

	}

	@Test
	public void deveCriarPlano() throws Exception {

		NovoPlanoDTO novoPlanoDTO = new NovoPlanoDTOBuilder().nome("Plano teste").valor(20.0).build();
		MvcResult response = this.mockMVC.perform(post(PATH_APP).contentType(MediaType.APPLICATION_JSON_UTF8)
				.content(mapper.writeValueAsString(novoPlanoDTO))).andDo(print()).andReturn();
		assertThat("Deve criar o plano com sucesso", response.getResponse().getStatus(),
				equalTo(HttpStatus.NO_CONTENT.value()));

	}

	@Test
	public void naoDeveCriarPlanoComNomeVazioOuNulo() throws Exception {

		NovoPlanoDTO novoPlanoDTO = new NovoPlanoDTOBuilder().valor(20.0).build();
		MvcResult response = this.mockMVC.perform(post(PATH_APP).contentType(MediaType.APPLICATION_JSON_UTF8)
				.content(mapper.writeValueAsString(novoPlanoDTO))).andDo(print()).andReturn();

		ErrorResponse errorResponse = mapper.readValue(response.getResponse().getContentAsString(),
				ErrorResponse.class);
		assertThat("Deve retornar a mensagem de erro", errorResponse.getMessage(),
				equalTo(Constantes.CAMPO_NOME_PLANO_OBRIGATORIO));
		assertThat("Não deve criar o plano pois está faltando o nome", response.getResponse().getStatus(),
				equalTo(HttpStatus.BAD_REQUEST.value()));

	}

	@Test
	public void naoDeveCriarPlanoComNomeMuitoGrande() throws Exception {

		NovoPlanoDTO novoPlanoDTO = new NovoPlanoDTOBuilder().nome(StringUtils.leftPad("", 41, 'W')).valor(20.0)
				.build();
		MvcResult response = this.mockMVC.perform(post(PATH_APP).contentType(MediaType.APPLICATION_JSON_UTF8)
				.content(mapper.writeValueAsString(novoPlanoDTO))).andDo(print()).andReturn();
		
		ErrorResponse errorResponse = mapper.readValue(response.getResponse().getContentAsString(),
				ErrorResponse.class);
		assertThat("Deve retornar a mensagem de erro", errorResponse.getMessage(),
				equalTo(Constantes.CAMPO_NOME_PLANO_TAMANHO_MAXIMO));
		assertThat("Não deve criar o plano pois o nome está muito grande", response.getResponse().getStatus(),
				equalTo(HttpStatus.BAD_REQUEST.value()));

	}

	@Test
	public void naoDeveCriarPlanoComValorNulo() throws Exception {

		NovoPlanoDTO novoPlanoDTO = new NovoPlanoDTOBuilder().nome("Plano teste").build();
		MvcResult response = this.mockMVC.perform(post(PATH_APP).contentType(MediaType.APPLICATION_JSON_UTF8)
				.content(mapper.writeValueAsString(novoPlanoDTO))).andDo(print()).andReturn();
		
		ErrorResponse errorResponse = mapper.readValue(response.getResponse().getContentAsString(),
				ErrorResponse.class);
		assertThat("Deve retornar a mensagem de erro", errorResponse.getMessage(),
				equalTo(Constantes.CAMPO_VALOR_OBRIGATORIO));
		assertThat("Não deve criar o plano pois está faltando o valor", response.getResponse().getStatus(),
				equalTo(HttpStatus.BAD_REQUEST.value()));

	}

	@Test
	public void naoDeveCriarPlanoComValorMuitoAlto() throws Exception {

		NovoPlanoDTO novoPlanoDTO = new NovoPlanoDTOBuilder().nome("Plano teste").valor(12345.00).build();
		MvcResult response = this.mockMVC.perform(post(PATH_APP).contentType(MediaType.APPLICATION_JSON_UTF8)
				.content(mapper.writeValueAsString(novoPlanoDTO))).andDo(print()).andReturn();
		
		ErrorResponse errorResponse = mapper.readValue(response.getResponse().getContentAsString(),
				ErrorResponse.class);
		assertThat("Deve retornar a mensagem de erro", errorResponse.getMessage(),
				equalTo(Constantes.CAMPO_VALOR_MAXIMO));
		assertThat("Não deve criar o plano pois o valor está muito alto", response.getResponse().getStatus(),
				equalTo(HttpStatus.BAD_REQUEST.value()));

	}

	@Test
	public void deveListarPlanos() throws Exception {

		int quantidadeItens = 10;
		when(planoService.listarPlanos())
				.thenReturn(new PlanoDTOBuilder().quantidadeItens(quantidadeItens).buildList());

		MvcResult response = this.mockMVC.perform(get(PATH_APP)).andDo(print()).andReturn();
		PlanosDTO planosDTO = mapper.readValue(response.getResponse().getContentAsString(), PlanosDTO.class);

		assertNotNull("Deve retornar os planos", planosDTO.getPlanos());
		assertThat("Deve retornar os planos", planosDTO.getPlanos(), hasSize(quantidadeItens));

	}

}
