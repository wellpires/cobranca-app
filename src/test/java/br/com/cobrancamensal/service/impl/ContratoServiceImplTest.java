package br.com.cobrancamensal.service.impl;

import static org.hamcrest.Matchers.hasSize;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import br.com.cobrancamensal.builder.ContratoBuilder;
import br.com.cobrancamensal.builder.DetalheClienteDTOBuilder;
import br.com.cobrancamensal.builder.DetalhePlanoDTOBuilder;
import br.com.cobrancamensal.dto.ContratoDTO;
import br.com.cobrancamensal.dto.DetalheClienteDTO;
import br.com.cobrancamensal.dto.DetalheContratoDTO;
import br.com.cobrancamensal.dto.DetalhePlanoDTO;
import br.com.cobrancamensal.exception.ClienteNotFoundException;
import br.com.cobrancamensal.exception.ContratoDuplicadoException;
import br.com.cobrancamensal.exception.ContratoNotFoundException;
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
			throws ClienteNotFoundException, PlanoNotFoundException, ContratoDuplicadoException {

		DetalheClienteDTO clienteDTO = new DetalheClienteDTOBuilder().cpf(12345678901L).build();
		when(clienteService.buscarCliente(anyLong())).thenReturn(clienteDTO);

		DetalhePlanoDTO detalhePlanoDTO = new DetalhePlanoDTOBuilder().nome("plano teste").valor(12.0).build();
		when(planoService.buscarPlano(anyString())).thenReturn(detalhePlanoDTO);

		when(contratoRepository.existsById(any(ContratoPK.class))).thenReturn(Boolean.FALSE);

		contratoService.contratarPlano(12345678901L, "Plano teste");

		verify(contratoRepository, times(1)).save(any(Contrato.class));

	}

	@Test(expected = ContratoDuplicadoException.class)
	public void naoDeveContratarPlanoPoisJaExiste()
			throws ClienteNotFoundException, PlanoNotFoundException, ContratoDuplicadoException {

		DetalheClienteDTO detalheClienteDTO = new DetalheClienteDTOBuilder().cpf(12345678901L).build();
		when(clienteService.buscarCliente(anyLong())).thenReturn(detalheClienteDTO);

		DetalhePlanoDTO detalhePlanoDTO = new DetalhePlanoDTOBuilder().nome("plano teste").valor(12.0).build();
		when(planoService.buscarPlano(anyString())).thenReturn(detalhePlanoDTO);

		when(contratoRepository.existsById(any(ContratoPK.class))).thenReturn(Boolean.TRUE);

		contratoService.contratarPlano(12345678901L, "Plano teste");

	}

	@Test
	public void deveListarContratos() {

		List<Contrato> contratos = new ContratoBuilder().quantidadeItens(10).buildList();
		when(contratoRepository.findAll()).thenReturn(contratos);

		List<ContratoDTO> contratosDTO = contratoService.listarContratos();

		assertThat("Deve retornar todos os contratos", contratosDTO, hasSize(contratos.size()));

	}

	@Test
	public void deveBuscarContrato() throws ContratoNotFoundException {

		Contrato contrato = new ContratoBuilder().cpf(12345678901L).nomePlano("Plano teste").dataContratacao(new Date())
				.build();
		when(contratoRepository.findById(any(ContratoPK.class))).thenReturn(Optional.ofNullable(contrato));

		DetalheContratoDTO detalheContratoDTO = contratoService.buscarContrato("1234567901", "Plano teste1");

		assertNotNull("Retornar o detalhe do contrato", detalheContratoDTO);

	}

	@Test(expected = ContratoNotFoundException.class)
	public void naoDeveBuscarContratoPoisNaoExiste() throws ContratoNotFoundException {

		Contrato contrato = null;
		when(contratoRepository.findById(any(ContratoPK.class))).thenReturn(Optional.ofNullable(contrato));

		DetalheContratoDTO detalheContratoDTO = contratoService.buscarContrato("1234567901", "Plano teste1");

		assertNull("Não deve retornar o contrato pois não existe", detalheContratoDTO);

	}

}
