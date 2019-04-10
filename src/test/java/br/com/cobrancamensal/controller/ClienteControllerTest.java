package br.com.cobrancamensal.controller;

import static org.apache.commons.lang3.builder.EqualsBuilder.reflectionEquals;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.hasSize;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertThat;
import static org.junit.Assert.assertTrue;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.patch;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;

import java.net.URI;
import java.time.LocalDate;
import java.util.HashMap;
import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.util.UriComponentsBuilder;

import com.fasterxml.jackson.databind.ObjectMapper;

import br.com.cobrancamensal.builder.AlterarClienteDTOBuilder;
import br.com.cobrancamensal.builder.ClienteDTOBuilder;
import br.com.cobrancamensal.builder.DetalheClienteDTOBuilder;
import br.com.cobrancamensal.builder.NovoClienteDTOBuilder;
import br.com.cobrancamensal.controller.advice.CobrancaControllerAdvice;
import br.com.cobrancamensal.dto.AlterarClienteDTO;
import br.com.cobrancamensal.dto.ClienteDTO;
import br.com.cobrancamensal.dto.DetalheClienteDTO;
import br.com.cobrancamensal.dto.NovoClienteDTO;
import br.com.cobrancamensal.enums.EstadoCivil;
import br.com.cobrancamensal.exception.ClienteDuplicadoException;
import br.com.cobrancamensal.exception.ClienteNotFoundException;
import br.com.cobrancamensal.request.AlterarClienteRequest;
import br.com.cobrancamensal.request.NovoClienteRequest;
import br.com.cobrancamensal.response.ClientesResponse;
import br.com.cobrancamensal.response.DetalheClienteResponse;
import br.com.cobrancamensal.response.ErrorResponse;
import br.com.cobrancamensal.service.ClienteService;
import br.com.cobrancamensal.util.Constantes;

@RunWith(SpringJUnit4ClassRunner.class)
public class ClienteControllerTest {

	private static final String PATH_APP = "/api/v1/clientes";
	private static final String GET_BUSCAR_CLIENTE = PATH_APP.concat("/{cpf}");
	private static final String DELETE_REMOVER_CLIENTE = PATH_APP.concat("/{cpf}");
	private static final String PATCH_ALTERAR_CLIENTE = PATH_APP.concat("/{cpf}");

	@InjectMocks
	private ClienteController clienteController;

	@Mock
	private ClienteService clienteService;

	private MockMvc mockMVC;

	private ObjectMapper mapper;

	@Before
	public void setup() {
		MockitoAnnotations.initMocks(this);
		this.mockMVC = MockMvcBuilders.standaloneSetup(clienteController)
				.setControllerAdvice(new CobrancaControllerAdvice()).build();
		this.mapper = new ObjectMapper();
	}

	@Test
	public void deveCriarCliente() throws Exception {

		NovoClienteDTO novoClienteDTO = new NovoClienteDTOBuilder().nomeCliente("Cliente teste")
				.dataNascimento(LocalDate.now()).cpf("08459938018").estadoCivil(EstadoCivil.Casado.name()).build();
		MvcResult response = mockMVC
				.perform(post(PATH_APP).contentType(MediaType.APPLICATION_JSON_UTF8)
						.content(mapper.writeValueAsString(new NovoClienteRequest(novoClienteDTO))))
				.andDo(print()).andReturn();

		assertThat("Deve criar cliente", HttpStatus.valueOf(response.getResponse().getStatus()),
				equalTo(HttpStatus.NO_CONTENT));

	}

	@Test
	public void naoDeveCriarClientePoisJaExiste() throws Exception {

		doThrow(ClienteDuplicadoException.class).when(clienteService).criarCliente(any(NovoClienteDTO.class));

		NovoClienteDTO novoClienteDTO = new NovoClienteDTOBuilder().nomeCliente("Cliente teste")
				.dataNascimento(LocalDate.now()).cpf("08459938018").estadoCivil(EstadoCivil.Casado.name()).build();
		MvcResult response = mockMVC
				.perform(post(PATH_APP).contentType(MediaType.APPLICATION_JSON_UTF8)
						.content(mapper.writeValueAsString(new NovoClienteRequest(novoClienteDTO))))
				.andDo(print()).andReturn();

		assertThat("Não deve criar cliente pois já existe", HttpStatus.valueOf(response.getResponse().getStatus()),
				equalTo(HttpStatus.CONFLICT));

	}

	@Test
	public void naoDeveCriarClientePoisNomeDoClienteNaoFoiInformado() throws Exception {

		NovoClienteDTO novoClienteDTO = new NovoClienteDTOBuilder().dataNascimento(LocalDate.now()).cpf("08459938018")
				.estadoCivil(EstadoCivil.Casado.name()).build();
		MvcResult response = mockMVC
				.perform(post(PATH_APP).contentType(MediaType.APPLICATION_JSON_UTF8)
						.content(mapper.writeValueAsString(new NovoClienteRequest(novoClienteDTO))))
				.andDo(print()).andReturn();

		ErrorResponse errorResponse = mapper.readValue(response.getResponse().getContentAsString(),
				ErrorResponse.class);
		assertThat("Deve retornar a mensagem de erro", errorResponse.getMessage(),
				equalTo(Constantes.CAMPO_NOME_CLIENTE_OBRIGATORIO));
		assertThat("Não deve criar cliente por falta do nome do cliente",
				HttpStatus.valueOf(response.getResponse().getStatus()), equalTo(HttpStatus.BAD_REQUEST));

	}

	@Test
	public void naoDeveCriarClientePoisCPFNaoFoiInformado() throws Exception {

		NovoClienteDTO novoClienteDTO = new NovoClienteDTOBuilder().nomeCliente("Cliente teste")
				.dataNascimento(LocalDate.now()).estadoCivil(EstadoCivil.Casado.name()).build();

		MvcResult response = mockMVC
				.perform(post(PATH_APP).contentType(MediaType.APPLICATION_JSON_UTF8)
						.content(mapper.writeValueAsString(new NovoClienteRequest(novoClienteDTO))))
				.andDo(print()).andReturn();

		ErrorResponse errorResponse = mapper.readValue(response.getResponse().getContentAsString(),
				ErrorResponse.class);
		assertThat("Deve retornar a mensagem de erro", errorResponse.getMessage(),
				equalTo(Constantes.CAMPO_CPF_OBRIGATORIO));
		assertThat("Não deve criar cliente por falta do CPF", HttpStatus.valueOf(response.getResponse().getStatus()),
				equalTo(HttpStatus.BAD_REQUEST));

	}

	@Test
	public void naoDeveCriarClientePoisCPFInvalido() throws Exception {

		NovoClienteDTO novoClienteDTO = new NovoClienteDTOBuilder().nomeCliente("Cliente teste").cpf("1234")
				.dataNascimento(LocalDate.now()).estadoCivil(EstadoCivil.Casado.name()).build();
		MvcResult response = mockMVC
				.perform(post(PATH_APP).contentType(MediaType.APPLICATION_JSON_UTF8)
						.content(mapper.writeValueAsString(new NovoClienteRequest(novoClienteDTO))))
				.andDo(print()).andReturn();

		ErrorResponse errorResponse = mapper.readValue(response.getResponse().getContentAsString(),
				ErrorResponse.class);
		assertThat("Deve retornar a mensagem de erro", errorResponse.getMessage(),
				equalTo(Constantes.CAMPO_CPF_INVALIDO));
		assertThat("Não deve criar cliente com CPF inválido", HttpStatus.valueOf(response.getResponse().getStatus()),
				equalTo(HttpStatus.BAD_REQUEST));

	}

	@Test
	public void naoDeveCriarClientePoisEstadoCivilInvalido() throws Exception {

		NovoClienteDTO novoClienteDTO = new NovoClienteDTOBuilder().nomeCliente("Cliente teste").cpf("08459938018")
				.dataNascimento(LocalDate.now()).estadoCivil("K").build();
		MvcResult response = mockMVC
				.perform(post(PATH_APP).contentType(MediaType.APPLICATION_JSON_UTF8)
						.content(mapper.writeValueAsString(new NovoClienteRequest(novoClienteDTO))))
				.andDo(print()).andReturn();

		ErrorResponse errorResponse = mapper.readValue(response.getResponse().getContentAsString(),
				ErrorResponse.class);
		assertThat("Deve retornar a mensagem de erro", errorResponse.getMessage(),
				equalTo(Constantes.CAMPO_ESTADO_CIVIL_INVALIDO));
		assertThat("Não deve criar cliente com Estado civil inválido",
				HttpStatus.valueOf(response.getResponse().getStatus()), equalTo(HttpStatus.BAD_REQUEST));

	}

	@Test
	public void deveListarClientes() throws Exception {

		int quantidadeItens = 10;
		List<ClienteDTO> clienteDTOs = new ClienteDTOBuilder().quantidadeItens(quantidadeItens).buildList();
		when(clienteService.listarClientes()).thenReturn(clienteDTOs);

		MvcResult response = mockMVC.perform(get(PATH_APP)).andDo(print()).andReturn();
		ClientesResponse clientesDTO = mapper.readValue(response.getResponse().getContentAsString(),
				ClientesResponse.class);

		assertThat("Deve retornar os clientes", clientesDTO.getClientes(), hasSize(quantidadeItens));

	}

	@Test
	public void deveBuscarCliente() throws Exception {

		DetalheClienteDTO detalheClienteDTO = new DetalheClienteDTOBuilder().nomeCliente("Cliente teste").cpf(100L)
				.dataNascimento(LocalDate.now()).estadoCivil(EstadoCivil.Solteiro).build();
		when(clienteService.buscarCliente(Mockito.anyLong())).thenReturn(detalheClienteDTO);

		HashMap<String, Object> variables = new HashMap<String, Object>();
		variables.put("cpf", 58190950061L);
		URI buscarCliente = UriComponentsBuilder.fromPath(GET_BUSCAR_CLIENTE).buildAndExpand(variables).toUri();

		MvcResult response = mockMVC.perform(get(buscarCliente)).andDo(print()).andReturn();

		DetalheClienteResponse detalheClienteResponse = mapper.readValue(response.getResponse().getContentAsString(),
				DetalheClienteResponse.class);

		assertNotNull("Deve retornar o detalhe do cliente", detalheClienteResponse);
		assertTrue("Deve retornar o detalhe do cliente",
				reflectionEquals(detalheClienteResponse.getDetalheClienteDTO(), detalheClienteDTO, false));

	}

	@Test
	public void naoDeveBuscarClientePoisOClienteNaoFoiEncontrado() throws Exception {

		doThrow(new ClienteNotFoundException()).when(clienteService).buscarCliente(Mockito.anyLong());

		HashMap<String, Object> variables = new HashMap<String, Object>();
		variables.put("cpf", 58190950061L);
		URI buscarCliente = UriComponentsBuilder.fromPath(GET_BUSCAR_CLIENTE).buildAndExpand(variables).toUri();

		MvcResult response = mockMVC.perform(get(buscarCliente)).andDo(print()).andReturn();

		ErrorResponse errorResponse = mapper.readValue(response.getResponse().getContentAsString(),
				ErrorResponse.class);
		assertThat("Deve retornar a mensagem de erro", errorResponse.getMessage(),
				equalTo(new ClienteNotFoundException().getMessage()));
		assertThat("Não deve buscar o cliente pois não foi encontrado",
				HttpStatus.valueOf(response.getResponse().getStatus()), equalTo(HttpStatus.NOT_FOUND));

	}

	@Test
	public void deveRemoverCliente() throws Exception {

		HashMap<String, Object> variables = new HashMap<String, Object>();
		variables.put("cpf", 58190950061L);
		URI removerCliente = UriComponentsBuilder.fromPath(DELETE_REMOVER_CLIENTE).buildAndExpand(variables).toUri();
		MvcResult response = mockMVC.perform(delete(removerCliente)).andDo(print()).andReturn();

		assertThat("Deve remover cliente com sucesso", HttpStatus.valueOf(response.getResponse().getStatus()),
				equalTo(HttpStatus.NO_CONTENT));

	}

	@Test
	public void naoDeveRemoverClientePoisOClienteNaoFoiEncontrado() throws Exception {

		doThrow(new ClienteNotFoundException()).when(clienteService).removerCliente(Mockito.anyLong());

		HashMap<String, Object> variables = new HashMap<String, Object>();
		variables.put("cpf", 58190950061L);
		URI removerCliente = UriComponentsBuilder.fromPath(DELETE_REMOVER_CLIENTE).buildAndExpand(variables).toUri();
		MvcResult response = mockMVC.perform(delete(removerCliente)).andDo(print()).andReturn();

		ErrorResponse errorResponse = mapper.readValue(response.getResponse().getContentAsString(),
				ErrorResponse.class);
		assertThat("Deve retornar a mensagem de erro", errorResponse.getMessage(),
				equalTo(new ClienteNotFoundException().getMessage()));
		assertThat("Não deve remover cliente pois o cliente não foi encontrado",
				HttpStatus.valueOf(response.getResponse().getStatus()), equalTo(HttpStatus.NOT_FOUND));

	}

	@Test
	public void deveAlterarCliente() throws Exception {

		HashMap<String, Object> variables = new HashMap<String, Object>();
		variables.put("cpf", 58190950061L);
		URI alterarCliente = UriComponentsBuilder.fromPath(PATCH_ALTERAR_CLIENTE).buildAndExpand(variables).toUri();

		AlterarClienteDTO alterarClienteDTO = new AlterarClienteDTOBuilder()
				.dataNascimento(LocalDate.now().minusYears(18)).estadoCivil(EstadoCivil.Casado).build();
		MvcResult response = mockMVC
				.perform(patch(alterarCliente).contentType(MediaType.APPLICATION_JSON_UTF8)
						.content(mapper.writeValueAsString(new AlterarClienteRequest(alterarClienteDTO))))
				.andDo(print()).andReturn();

		assertThat("Deve alterar cliente", HttpStatus.valueOf(response.getResponse().getStatus()),
				equalTo(HttpStatus.NO_CONTENT));

	}

	@Test
	public void naoDeveAlterarClientePoisEstadoCivilEstaInvalido() throws Exception {

		HashMap<String, Object> variables = new HashMap<String, Object>();
		variables.put("cpf", 58190950061L);
		URI alterarCliente = UriComponentsBuilder.fromPath(PATCH_ALTERAR_CLIENTE).buildAndExpand(variables).toUri();

		AlterarClienteDTO alterarClienteDTO = new AlterarClienteDTOBuilder()
				.dataNascimento(LocalDate.now().minusYears(18)).estadoCivil("TESTE").build();
		MvcResult response = mockMVC
				.perform(patch(alterarCliente).contentType(MediaType.APPLICATION_JSON_UTF8)
						.content(mapper.writeValueAsString(new AlterarClienteRequest(alterarClienteDTO))))
				.andDo(print()).andReturn();

		ErrorResponse errorResponse = mapper.readValue(response.getResponse().getContentAsString(),
				ErrorResponse.class);
		assertThat("Deve retornar a mensagem de erro", errorResponse.getMessage(),
				equalTo(Constantes.CAMPO_ESTADO_CIVIL_INVALIDO));
		assertThat("Não deve alterar cliente pois o estado civil está incorreto",
				HttpStatus.valueOf(response.getResponse().getStatus()), equalTo(HttpStatus.BAD_REQUEST));

	}

	@Test
	public void naoDeveAlterarClientePoisClienteNaoEstaNulo() throws Exception {

		HashMap<String, Object> variables = new HashMap<String, Object>();
		variables.put("cpf", 58190950061L);
		URI alterarCliente = UriComponentsBuilder.fromPath(PATCH_ALTERAR_CLIENTE).buildAndExpand(variables).toUri();

		AlterarClienteDTO alterarClienteDTO = null;
		MvcResult response = mockMVC
				.perform(patch(alterarCliente).contentType(MediaType.APPLICATION_JSON_UTF8)
						.content(mapper.writeValueAsString(new AlterarClienteRequest(alterarClienteDTO))))
				.andDo(print()).andReturn();

		ErrorResponse errorResponse = mapper.readValue(response.getResponse().getContentAsString(),
				ErrorResponse.class);
		assertThat("Deve retornar a mensagem de erro", errorResponse.getMessage(),
				equalTo(Constantes.CLIENTE_OBRIGATORIO));
		assertThat("Não deve alterar cliente pois o cliente está nulo",
				HttpStatus.valueOf(response.getResponse().getStatus()), equalTo(HttpStatus.BAD_REQUEST));

	}

	@Test
	public void naoDeveAlterarClientePoisOClienteNaoFoiEncontrado() throws Exception {

		doThrow(new ClienteNotFoundException()).when(clienteService).alterarCliente(Mockito.anyLong(),
				any(AlterarClienteDTO.class));

		HashMap<String, Object> variables = new HashMap<String, Object>();
		variables.put("cpf", 58190950061L);
		URI alterarCliente = UriComponentsBuilder.fromPath(PATCH_ALTERAR_CLIENTE).buildAndExpand(variables).toUri();

		AlterarClienteDTO alterarClienteDTO = new AlterarClienteDTOBuilder()
				.dataNascimento(LocalDate.now().minusYears(18)).estadoCivil(EstadoCivil.Casado).build();

		MvcResult response = mockMVC
				.perform(patch(alterarCliente).contentType(MediaType.APPLICATION_JSON_UTF8)
						.content(mapper.writeValueAsString(new AlterarClienteRequest(alterarClienteDTO))))
				.andDo(print()).andReturn();

		ErrorResponse errorResponse = mapper.readValue(response.getResponse().getContentAsString(),
				ErrorResponse.class);
		assertThat("Deve retornar a mensagem de erro", errorResponse.getMessage(),
				equalTo(new ClienteNotFoundException().getMessage()));
		assertThat("Deve alterar cliente", HttpStatus.valueOf(response.getResponse().getStatus()),
				equalTo(HttpStatus.NOT_FOUND));

	}

}
