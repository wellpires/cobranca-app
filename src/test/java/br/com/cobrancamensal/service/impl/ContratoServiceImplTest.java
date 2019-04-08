package br.com.cobrancamensal.service.impl;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import br.com.cobrancamensal.builder.ClienteDTOBuilder;
import br.com.cobrancamensal.builder.PlanoDTOBuilder;
import br.com.cobrancamensal.dto.ClienteDTO;
import br.com.cobrancamensal.dto.PlanoDTO;
import br.com.cobrancamensal.exception.ClienteNotFoundException;
import br.com.cobrancamensal.exception.ContratoAlreadyExistsException;
import br.com.cobrancamensal.exception.PlanoNotFoundException;
import br.com.cobrancamensal.model.Contrato;
import br.com.cobrancamensal.model.pk.ContratoPK;
import br.com.cobrancamensal.repository.ContratoRepository;
import br.com.cobrancamensal.service.ClienteService;
import br.com.cobrancamensal.service.ContratoService;
import br.com.cobrancamensal.service.PlanoService;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration
public class ContratoServiceImplTest {

	@Autowired
	private ContratoService contratoService;

	@Autowired
	private ContratoRepository contratoRepository;

	@Autowired
	private ClienteService clienteService;

	@Autowired
	private PlanoService planoService;

	@Configuration
	static class ContratoConfiguration {

		@Bean
		public ContratoService contratoService() {
			return new ContratoServiceImpl();
		}

		@Bean
		public ContratoRepository contratoRepository() {
			return mock(ContratoRepository.class);
		}

		@Bean
		public ClienteService clienteService() {
			return mock(ClienteService.class);
		}

		@Bean
		public PlanoService planoService() {
			return mock(PlanoService.class);
		}

	}

	@Test
	public void deveContratarPlano()
			throws ClienteNotFoundException, PlanoNotFoundException, ContratoAlreadyExistsException {

		ClienteDTO clienteDTO = new ClienteDTOBuilder().cpf(12345678901L).build();
		when(clienteService.buscarCliente(anyLong())).thenReturn(clienteDTO);

		PlanoDTO planoDTO = new PlanoDTOBuilder().nome("plano teste").build();
		when(planoService.buscarPlano(anyString())).thenReturn(planoDTO);

		when(contratoRepository.existsById(any(ContratoPK.class))).thenReturn(Boolean.FALSE);

		contratoService.contratarPlano(12345678901L, "Plano teste");

		verify(contratoRepository, times(1)).save(any(Contrato.class));

	}

	@Test(expected = ContratoAlreadyExistsException.class)
	public void naoDeveContratarPlanoPoisJaExiste()
			throws ClienteNotFoundException, PlanoNotFoundException, ContratoAlreadyExistsException {

		ClienteDTO clienteDTO = new ClienteDTOBuilder().cpf(12345678901L).build();
		when(clienteService.buscarCliente(anyLong())).thenReturn(clienteDTO);

		PlanoDTO planoDTO = new PlanoDTOBuilder().nome("plano teste").build();
		when(planoService.buscarPlano(anyString())).thenReturn(planoDTO);

		when(contratoRepository.existsById(any(ContratoPK.class))).thenReturn(Boolean.TRUE);

		contratoService.contratarPlano(12345678901L, "Plano teste");

	}

}
