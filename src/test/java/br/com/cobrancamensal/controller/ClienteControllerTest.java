package br.com.cobrancamensal.controller;

import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.hasSize;
import static org.junit.Assert.assertThat;
import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.when;
import static org.mockito.Mockito.any;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;

import java.time.LocalDate;
import java.util.List;

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

import br.com.cobrancamensal.builder.ClienteDTOBuilder;
import br.com.cobrancamensal.builder.NovoClienteDTOBuilder;
import br.com.cobrancamensal.controller.advice.CobrancaControllerAdvice;
import br.com.cobrancamensal.dto.ClienteDTO;
import br.com.cobrancamensal.dto.ClientesDTO;
import br.com.cobrancamensal.dto.NovoClienteDTO;
import br.com.cobrancamensal.enums.EstadoCivil;
import br.com.cobrancamensal.exception.ClienteAlreadyExistsException;
import br.com.cobrancamensal.response.ErrorResponse;
import br.com.cobrancamensal.service.ClienteService;
import br.com.cobrancamensal.util.Constantes;

@RunWith(SpringJUnit4ClassRunner.class)
public class ClienteControllerTest {

	private static final String PATH_APP = "/api/v1/clientes";

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
		MvcResult response = mockMVC.perform(post(PATH_APP).contentType(MediaType.APPLICATION_JSON_UTF8)
				.content(mapper.writeValueAsString(novoClienteDTO))).andDo(print()).andReturn();

		assertThat("Deve criar cliente", HttpStatus.valueOf(response.getResponse().getStatus()),
				equalTo(HttpStatus.NO_CONTENT));

	}

	@Test
	public void naoDeveCriarClientePoisJaExiste() throws Exception {

		doThrow(ClienteAlreadyExistsException.class).when(clienteService).criarCliente(any(NovoClienteDTO.class));

		NovoClienteDTO novoClienteDTO = new NovoClienteDTOBuilder().nomeCliente("Cliente teste")
				.dataNascimento(LocalDate.now()).cpf("08459938018").estadoCivil(EstadoCivil.Casado.name()).build();
		MvcResult response = mockMVC.perform(post(PATH_APP).contentType(MediaType.APPLICATION_JSON_UTF8)
				.content(mapper.writeValueAsString(novoClienteDTO))).andDo(print()).andReturn();

		assertThat("Não deve criar cliente pois já existe", HttpStatus.valueOf(response.getResponse().getStatus()),
				equalTo(HttpStatus.CONFLICT));

	}

	@Test
	public void naoDeveCriarClientePoisNomeDoClienteNaoFoiInformado() throws Exception {

		NovoClienteDTO novoClienteDTO = new NovoClienteDTOBuilder().dataNascimento(LocalDate.now()).cpf("08459938018")
				.estadoCivil(EstadoCivil.Casado.name()).build();
		MvcResult response = mockMVC.perform(post(PATH_APP).contentType(MediaType.APPLICATION_JSON_UTF8)
				.content(mapper.writeValueAsString(novoClienteDTO))).andDo(print()).andReturn();

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
		MvcResult response = mockMVC.perform(post(PATH_APP).contentType(MediaType.APPLICATION_JSON_UTF8)
				.content(mapper.writeValueAsString(novoClienteDTO))).andDo(print()).andReturn();

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
		MvcResult response = mockMVC.perform(post(PATH_APP).contentType(MediaType.APPLICATION_JSON_UTF8)
				.content(mapper.writeValueAsString(novoClienteDTO))).andDo(print()).andReturn();

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
		MvcResult response = mockMVC.perform(post(PATH_APP).contentType(MediaType.APPLICATION_JSON_UTF8)
				.content(mapper.writeValueAsString(novoClienteDTO))).andDo(print()).andReturn();

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
		ClientesDTO clientesDTO = mapper.readValue(response.getResponse().getContentAsString(), ClientesDTO.class);

		assertThat("Deve retornar os clientes", clientesDTO.getClientes(), hasSize(quantidadeItens));

	}

}
