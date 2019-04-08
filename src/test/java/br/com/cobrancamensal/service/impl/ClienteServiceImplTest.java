package br.com.cobrancamensal.service.impl;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.Matchers.hasSize;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.reset;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import org.junit.After;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import br.com.cobrancamensal.builder.ClienteBuilder;
import br.com.cobrancamensal.builder.NovoClienteDTOBuilder;
import br.com.cobrancamensal.dto.ClienteDTO;
import br.com.cobrancamensal.dto.NovoClienteDTO;
import br.com.cobrancamensal.enums.EstadoCivil;
import br.com.cobrancamensal.exception.ClienteAlreadyExistsException;
import br.com.cobrancamensal.exception.ClienteNotFoundException;
import br.com.cobrancamensal.model.Cliente;
import br.com.cobrancamensal.repository.ClienteRepository;
import br.com.cobrancamensal.service.ClienteService;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration
public class ClienteServiceImplTest {

	@Autowired
	private ClienteRepository clienteRepository;

	@Autowired
	private ClienteService clienteService;

	@Test
	public void deveCriarCliente() throws ClienteAlreadyExistsException {

		when(clienteRepository.existsById(anyLong())).thenReturn(Boolean.FALSE);

		NovoClienteDTO novoClienteDTO = new NovoClienteDTOBuilder().nomeCliente("Cliente teste")
				.dataNascimento(LocalDate.now()).cpf("08459938018").estadoCivil(EstadoCivil.Casado.name()).build();
		clienteService.criarCliente(novoClienteDTO);
		verify(clienteRepository, times(1)).save(any(Cliente.class));

	}

	@Test(expected = ClienteAlreadyExistsException.class)
	public void naoDeveCriarClientePoisJaFoiCriado() throws ClienteAlreadyExistsException {

		when(clienteRepository.existsById(anyLong())).thenReturn(Boolean.TRUE);

		NovoClienteDTO novoClienteDTO = new NovoClienteDTOBuilder().cpf("08459938018").build();
		clienteService.criarCliente(novoClienteDTO);
		verify(clienteRepository, never()).save(any(Cliente.class));

	}

	@Test
	public void deveListarClientes() {

		List<Cliente> clientes = new ClienteBuilder().quantidadeItens(10).buildList();
		when(clienteRepository.findAll()).thenReturn(clientes);

		List<ClienteDTO> clienteDTOs = clienteService.listarClientes();
		assertThat("Deve retornar os clientes", clienteDTOs, hasSize(clientes.size()));

	}

	@Test
	public void deveBuscarCliente() throws ClienteNotFoundException {

		Cliente cliente = new ClienteBuilder().nomeCliente("Cliente teste").cpf("12345678901").build();
		when(clienteRepository.findById(anyLong())).thenReturn(Optional.ofNullable(cliente));

		ClienteDTO clienteDTO = clienteService.buscarCliente(10L);

		assertNotNull("Deve retornar os dados do cliente", clienteDTO);
		assertThat("Deve retornar o nome do cliente", clienteDTO.getNome(), equalTo(cliente.getNomeCliente()));
		assertThat("Deve retornar o CPF do cliente", clienteDTO.getCpf(), equalTo(cliente.getCpf().toString()));

	}

	@Test(expected = ClienteNotFoundException.class)
	public void naoDeveBuscarClientePoisNaoFoiEncontrado() throws ClienteNotFoundException {

		Cliente cliente = null;
		when(clienteRepository.findById(anyLong())).thenReturn(Optional.ofNullable(cliente));

		clienteService.buscarCliente(10L);

	}

	@After
	public void afterTest() {
		reset(clienteRepository);
	}

	@Configuration
	static class ClienteConfiguration {

		@Bean
		public ClienteRepository clienteRepository() {
			return mock(ClienteRepository.class);
		}

		@Bean
		public ClienteService clienteService() {
			return new ClienteServiceImpl();
		}

	}

}
