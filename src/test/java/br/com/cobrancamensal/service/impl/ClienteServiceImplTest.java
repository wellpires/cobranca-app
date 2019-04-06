package br.com.cobrancamensal.service.impl;

import static org.hamcrest.Matchers.hasSize;
import static org.junit.Assert.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.only;
import static org.mockito.Mockito.reset;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.time.LocalDate;
import java.util.List;

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
	public void deveCriarCliente() {

		NovoClienteDTO novoClienteDTO = new NovoClienteDTOBuilder().nomeCliente("Cliente teste")
				.dataNascimento(LocalDate.now()).cpf("08459938018").estadoCivil(EstadoCivil.Casado.name()).build();
		clienteService.criarCliente(novoClienteDTO);
		verify(clienteRepository, only()).save(any(Cliente.class));

	}

	@Test
	public void deveListarClientes() {

		List<Cliente> clientes = new ClienteBuilder().quantidadeItens(10).buildList();
		when(clienteRepository.findAll()).thenReturn(clientes);

		List<ClienteDTO> clienteDTOs = clienteService.listarClientes();
		assertThat("Deve retornar os clientes", clienteDTOs, hasSize(clientes.size()));

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
