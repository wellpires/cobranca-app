package br.com.cobrancamensal.controller;

import static org.apache.commons.lang3.builder.EqualsBuilder.reflectionEquals;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.hasSize;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertThat;
import static org.junit.Assert.assertTrue;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.patch;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;

import java.net.URI;
import java.util.HashMap;
import java.util.Map;

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
import org.springframework.web.util.UriComponentsBuilder;

import com.fasterxml.jackson.databind.ObjectMapper;

import br.com.cobrancamensal.builder.AlterarPlanoDTOBuilder;
import br.com.cobrancamensal.builder.DetalhePlanoDTOBuilder;
import br.com.cobrancamensal.builder.NovoPlanoDTOBuilder;
import br.com.cobrancamensal.builder.PlanoDTOBuilder;
import br.com.cobrancamensal.controller.advice.CobrancaControllerAdvice;
import br.com.cobrancamensal.dto.AlterarPlanoDTO;
import br.com.cobrancamensal.dto.DetalhePlanoDTO;
import br.com.cobrancamensal.dto.NovoPlanoDTO;
import br.com.cobrancamensal.exception.PlanoDuplicadoException;
import br.com.cobrancamensal.exception.PlanoNotFoundException;
import br.com.cobrancamensal.request.AlterarPlanoRequest;
import br.com.cobrancamensal.request.NovoPlanoRequest;
import br.com.cobrancamensal.response.DetalhePlanoResponse;
import br.com.cobrancamensal.response.ErrorResponse;
import br.com.cobrancamensal.response.PlanosResponse;
import br.com.cobrancamensal.service.PlanoService;
import br.com.cobrancamensal.util.Constantes;

@RunWith(SpringJUnit4ClassRunner.class)
public class PlanoControllerTest {

	private static final String PATH_APP = "/api/v1/planos";
	private static final String GET_BUSCAR_PLANO = PATH_APP.concat("/{nomePlano}");
	private static final String DELETE_REMOVER_PLANO = PATH_APP.concat("/{nomePlano}");
	private static final String PATCH_ALTERAR_PLANO = PATH_APP.concat("/{nomePlano}");

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
		MvcResult response = this.mockMVC
				.perform(post(PATH_APP).contentType(MediaType.APPLICATION_JSON_UTF8)
						.content(mapper.writeValueAsString(new NovoPlanoRequest(novoPlanoDTO))))
				.andDo(print()).andReturn();
		assertThat("Deve criar o plano com sucesso", response.getResponse().getStatus(),
				equalTo(HttpStatus.NO_CONTENT.value()));

	}

	@Test
	public void naoDeveCriarPlanoPoisJaExiste() throws Exception {

		doThrow(PlanoDuplicadoException.class).when(planoService).criarPlano(any(NovoPlanoDTO.class));

		NovoPlanoDTO novoPlanoDTO = new NovoPlanoDTOBuilder().nome("Plano teste").valor(20.0).build();
		MvcResult response = this.mockMVC
				.perform(post(PATH_APP).contentType(MediaType.APPLICATION_JSON_UTF8)
						.content(mapper.writeValueAsString(new NovoPlanoRequest(novoPlanoDTO))))
				.andDo(print()).andReturn();
		assertThat("Não deve criar o plano pois já existe", HttpStatus.valueOf(response.getResponse().getStatus()),
				equalTo(HttpStatus.CONFLICT));

	}

	@Test
	public void naoDeveCriarPlanoComNomeVazioOuNulo() throws Exception {

		NovoPlanoDTO novoPlanoDTO = new NovoPlanoDTOBuilder().valor(20.0).build();
		MvcResult response = this.mockMVC
				.perform(post(PATH_APP).contentType(MediaType.APPLICATION_JSON_UTF8)
						.content(mapper.writeValueAsString(new NovoPlanoRequest(novoPlanoDTO))))
				.andDo(print()).andReturn();

		ErrorResponse errorResponse = mapper.readValue(response.getResponse().getContentAsString(),
				ErrorResponse.class);
		assertThat("Deve retornar a mensagem de erro", errorResponse.getMessage(),
				equalTo(Constantes.CAMPO_NOME_PLANO_OBRIGATORIO));
		assertThat("Não deve criar o plano pois está faltando o nome",
				HttpStatus.valueOf(response.getResponse().getStatus()), equalTo(HttpStatus.BAD_REQUEST));

	}

	@Test
	public void naoDeveCriarPlanoComNomeMuitoGrande() throws Exception {

		NovoPlanoDTO novoPlanoDTO = new NovoPlanoDTOBuilder().nome(StringUtils.leftPad("", 41, 'W')).valor(20.0)
				.build();
		MvcResult response = this.mockMVC
				.perform(post(PATH_APP).contentType(MediaType.APPLICATION_JSON_UTF8)
						.content(mapper.writeValueAsString(new NovoPlanoRequest(novoPlanoDTO))))
				.andDo(print()).andReturn();

		ErrorResponse errorResponse = mapper.readValue(response.getResponse().getContentAsString(),
				ErrorResponse.class);
		assertThat("Deve retornar a mensagem de erro", errorResponse.getMessage(),
				equalTo(Constantes.CAMPO_NOME_PLANO_TAMANHO_MAXIMO));
		assertThat("Não deve criar o plano pois o nome está muito grande",
				HttpStatus.valueOf(response.getResponse().getStatus()), equalTo(HttpStatus.BAD_REQUEST));

	}

	@Test
	public void naoDeveCriarPlanoComValorNulo() throws Exception {

		NovoPlanoDTO novoPlanoDTO = new NovoPlanoDTOBuilder().nome("Plano teste").build();
		MvcResult response = this.mockMVC
				.perform(post(PATH_APP).contentType(MediaType.APPLICATION_JSON_UTF8)
						.content(mapper.writeValueAsString(new NovoPlanoRequest(novoPlanoDTO))))
				.andDo(print()).andReturn();

		ErrorResponse errorResponse = mapper.readValue(response.getResponse().getContentAsString(),
				ErrorResponse.class);
		assertThat("Deve retornar a mensagem de erro", errorResponse.getMessage(),
				equalTo(Constantes.CAMPO_VALOR_OBRIGATORIO));
		assertThat("Não deve criar o plano pois está faltando o valor",
				HttpStatus.valueOf(response.getResponse().getStatus()), equalTo(HttpStatus.BAD_REQUEST));

	}

	@Test
	public void naoDeveCriarPlanoComValorMuitoAlto() throws Exception {

		NovoPlanoDTO novoPlanoDTO = new NovoPlanoDTOBuilder().nome("Plano teste").valor(12345.00).build();
		MvcResult response = this.mockMVC
				.perform(post(PATH_APP).contentType(MediaType.APPLICATION_JSON_UTF8)
						.content(mapper.writeValueAsString(new NovoPlanoRequest(novoPlanoDTO))))
				.andDo(print()).andReturn();

		ErrorResponse errorResponse = mapper.readValue(response.getResponse().getContentAsString(),
				ErrorResponse.class);
		assertThat("Deve retornar a mensagem de erro", errorResponse.getMessage(),
				equalTo(Constantes.CAMPO_VALOR_MAXIMO));
		assertThat("Não deve criar o plano pois o valor está muito alto",
				HttpStatus.valueOf(response.getResponse().getStatus()), equalTo(HttpStatus.BAD_REQUEST));

	}

	@Test
	public void deveListarPlanos() throws Exception {

		int quantidadeItens = 10;
		when(planoService.listarPlanos())
				.thenReturn(new PlanoDTOBuilder().quantidadeItens(quantidadeItens).buildList());

		MvcResult response = this.mockMVC.perform(get(PATH_APP)).andDo(print()).andReturn();
		PlanosResponse planosDTO = mapper.readValue(response.getResponse().getContentAsString(), PlanosResponse.class);

		assertNotNull("Deve retornar os planos", planosDTO.getPlanos());
		assertThat("Deve retornar os planos", planosDTO.getPlanos(), hasSize(quantidadeItens));

	}

	@Test
	public void deveBuscarPlano() throws Exception {

		DetalhePlanoDTO detalhePlanoDTO = new DetalhePlanoDTOBuilder().nome("Plano teste").valor(99.99).build();
		when(planoService.buscarPlano(anyString())).thenReturn(detalhePlanoDTO);

		Map<String, Object> variable = new HashMap<String, Object>();
		variable.put("nomePlano", "Plano teste");
		URI getBuscarPlano = UriComponentsBuilder.fromPath(GET_BUSCAR_PLANO).buildAndExpand(variable).toUri();
		MvcResult response = this.mockMVC.perform(get(getBuscarPlano)).andDo(print()).andReturn();

		DetalhePlanoResponse detalhePlanoResponse = mapper.readValue(response.getResponse().getContentAsString(),
				DetalhePlanoResponse.class);

		assertTrue("Deve retornar o plano",
				reflectionEquals(detalhePlanoDTO, detalhePlanoResponse.getDetalhePlanoDTO(), false));

	}

	@Test
	public void naoDeveBuscarPlanoPoisNaoFoiEncontrado() throws Exception {

		doThrow(PlanoNotFoundException.class).when(planoService).buscarPlano(anyString());

		Map<String, Object> variable = new HashMap<String, Object>();
		variable.put("nomePlano", "Plano teste");
		URI getBuscarPlano = UriComponentsBuilder.fromPath(GET_BUSCAR_PLANO).buildAndExpand(variable).toUri();
		MvcResult response = this.mockMVC.perform(get(getBuscarPlano)).andDo(print()).andReturn();

		assertThat("Não deve buscar o plano pois não foi encontrado",
				HttpStatus.valueOf(response.getResponse().getStatus()), equalTo(HttpStatus.NOT_FOUND));

	}

	@Test
	public void deveRemoverPlano() throws Exception {

		Map<String, Object> variable = new HashMap<String, Object>();
		variable.put("nomePlano", "Plano teste");
		URI deleteRemoverPlano = UriComponentsBuilder.fromPath(DELETE_REMOVER_PLANO).buildAndExpand(variable).toUri();
		MvcResult response = this.mockMVC.perform(delete(deleteRemoverPlano)).andDo(print()).andReturn();

		assertThat("Deve remover plano", HttpStatus.valueOf(response.getResponse().getStatus()),
				equalTo(HttpStatus.NO_CONTENT));

	}

	@Test
	public void naoDeveRemoverPlanoPoisNaoFoiEncontrado() throws Exception {

		doThrow(PlanoNotFoundException.class).when(planoService).removerPlano(anyString());

		Map<String, Object> variable = new HashMap<String, Object>();
		variable.put("nomePlano", "Plano teste");
		URI deleteRemoverPlano = UriComponentsBuilder.fromPath(DELETE_REMOVER_PLANO).buildAndExpand(variable).toUri();
		MvcResult response = this.mockMVC.perform(delete(deleteRemoverPlano)).andDo(print()).andReturn();

		assertThat("Não deve remover plano pois não foi encontrado",
				HttpStatus.valueOf(response.getResponse().getStatus()), equalTo(HttpStatus.NOT_FOUND));

	}

	@Test
	public void deveAlterarPlano() throws Exception {

		AlterarPlanoDTO alterarPlanoDTO = new AlterarPlanoDTOBuilder().valor(99.99).build();

		Map<String, Object> variable = new HashMap<String, Object>();
		variable.put("nomePlano", "Plano teste");
		URI deleteRemoverPlano = UriComponentsBuilder.fromPath(PATCH_ALTERAR_PLANO).buildAndExpand(variable).toUri();
		MvcResult response = this.mockMVC
				.perform(patch(deleteRemoverPlano).contentType(MediaType.APPLICATION_JSON_UTF8)
						.content(mapper.writeValueAsString(new AlterarPlanoRequest(alterarPlanoDTO))))
				.andDo(print()).andReturn();

		assertThat("Deve alterar plano", HttpStatus.valueOf(response.getResponse().getStatus()),
				equalTo(HttpStatus.NO_CONTENT));

	}

	@Test
	public void naoDeveAlterarPlanoPoisOValorEstaNulo() throws Exception {

		AlterarPlanoDTO alterarPlanoDTO = new AlterarPlanoDTOBuilder().build();

		Map<String, Object> variable = new HashMap<String, Object>();
		variable.put("nomePlano", "Plano teste");
		URI deleteRemoverPlano = UriComponentsBuilder.fromPath(PATCH_ALTERAR_PLANO).buildAndExpand(variable).toUri();
		MvcResult response = this.mockMVC
				.perform(patch(deleteRemoverPlano).contentType(MediaType.APPLICATION_JSON_UTF8)
						.content(mapper.writeValueAsString(new AlterarPlanoRequest(alterarPlanoDTO))))
				.andDo(print()).andReturn();

		ErrorResponse errorResponse = mapper.readValue(response.getResponse().getContentAsString(),
				ErrorResponse.class);

		assertThat("Deve retornar a mensagem para o valor obrigatório", errorResponse.getMessage(),
				equalTo(Constantes.CAMPO_VALOR_OBRIGATORIO));
		assertThat("Não deve alterar plano pois o valor está nulo",
				HttpStatus.valueOf(response.getResponse().getStatus()), equalTo(HttpStatus.BAD_REQUEST));

	}

	@Test
	public void naoDeveAlterarPlanoPoisOValorEstaMaiorQueOValorMaximo() throws Exception {

		double valorMaximo = 1001.00;
		AlterarPlanoDTO alterarPlanoDTO = new AlterarPlanoDTOBuilder().valor(valorMaximo).build();

		Map<String, Object> variable = new HashMap<String, Object>();
		variable.put("nomePlano", "Plano teste");
		URI deleteRemoverPlano = UriComponentsBuilder.fromPath(PATCH_ALTERAR_PLANO).buildAndExpand(variable).toUri();
		MvcResult response = this.mockMVC
				.perform(patch(deleteRemoverPlano).contentType(MediaType.APPLICATION_JSON_UTF8)
						.content(mapper.writeValueAsString(new AlterarPlanoRequest(alterarPlanoDTO))))
				.andDo(print()).andReturn();

		ErrorResponse errorResponse = mapper.readValue(response.getResponse().getContentAsString(),
				ErrorResponse.class);

		assertThat("Deve retornar a mensagem para o tamanho máximo do valor", errorResponse.getMessage(),
				equalTo(Constantes.CAMPO_VALOR_MAXIMO));
		assertThat("Não deve alterar plano pois o valor é maior que o valor máximo estabelecido",
				HttpStatus.valueOf(response.getResponse().getStatus()), equalTo(HttpStatus.BAD_REQUEST));

	}

	@Test
	public void naoDeveAlterarPlanoPoisOValorEstaMenorQueOValorMinimo() throws Exception {

		double valorMinimo = 0.04;
		AlterarPlanoDTO alterarPlanoDTO = new AlterarPlanoDTOBuilder().valor(valorMinimo).build();

		Map<String, Object> variable = new HashMap<String, Object>();
		variable.put("nomePlano", "Plano teste");
		URI deleteRemoverPlano = UriComponentsBuilder.fromPath(PATCH_ALTERAR_PLANO).buildAndExpand(variable).toUri();
		MvcResult response = this.mockMVC
				.perform(patch(deleteRemoverPlano).contentType(MediaType.APPLICATION_JSON_UTF8)
						.content(mapper.writeValueAsString(new AlterarPlanoRequest(alterarPlanoDTO))))
				.andDo(print()).andReturn();

		ErrorResponse errorResponse = mapper.readValue(response.getResponse().getContentAsString(),
				ErrorResponse.class);

		assertThat("Deve retornar a mensagem para o tamanho mínimo do valor", errorResponse.getMessage(),
				equalTo(Constantes.CAMPO_VALOR_MINIMO));
		assertThat("Não deve alterar plano pois o valor é menor que o valor mínimo estabelecido",
				HttpStatus.valueOf(response.getResponse().getStatus()), equalTo(HttpStatus.BAD_REQUEST));

	}

}
