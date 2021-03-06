package br.com.cobrancamensal.service.impl;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.Matchers.hasSize;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.reset;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

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

import br.com.cobrancamensal.builder.AlterarPlanoDTOBuilder;
import br.com.cobrancamensal.builder.NovoPlanoDTOBuilder;
import br.com.cobrancamensal.builder.PlanoBuilder;
import br.com.cobrancamensal.dto.DetalhePlanoDTO;
import br.com.cobrancamensal.dto.PlanoDTO;
import br.com.cobrancamensal.exception.PlanoDuplicadoException;
import br.com.cobrancamensal.exception.PlanoNotFoundException;
import br.com.cobrancamensal.model.Plano;
import br.com.cobrancamensal.repository.PlanoRepository;
import br.com.cobrancamensal.service.PlanoService;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration
public class PlanoServiceImplTest {

	@Autowired
	private PlanoService planoService;

	@Autowired
	private PlanoRepository planoRepository;

	@Configuration
	static class PlanoConfiguration {

		@Bean
		public PlanoService planoService() {
			return new PlanoServiceImpl();
		}

		@Bean
		public PlanoRepository planoRepository() {
			return mock(PlanoRepository.class);
		}

	}

	@Test
	public void deveCriarPlano() throws PlanoDuplicadoException {

		when(planoRepository.existsById(anyString())).thenReturn(Boolean.FALSE);

		planoService.criarPlano(new NovoPlanoDTOBuilder().nome("Plano teste").build());
		verify(planoRepository, times(1)).save(any(Plano.class));
	}

	@Test(expected = PlanoDuplicadoException.class)
	public void naoDeveCriarPlanoPoisJaFoiCriado() throws PlanoDuplicadoException {

		when(planoRepository.existsById(anyString())).thenReturn(Boolean.TRUE);

		planoService.criarPlano(new NovoPlanoDTOBuilder().nome("Plano teste").build());
		verify(planoRepository, never()).save(any(Plano.class));
	}

	@Test
	public void deveListarPlanos() {

		int quantidadeItens = 10;
		when(planoRepository.findAll()).thenReturn(new PlanoBuilder().quantidadeItens(quantidadeItens).buildList());

		List<PlanoDTO> planosDTO = planoService.listarPlanos();

		assertThat("Deve retornar os planos", planosDTO, hasSize(quantidadeItens));
	}

	@Test
	public void deveBuscarPlano() throws PlanoNotFoundException {

		Plano plano = new PlanoBuilder().nomePlano("plano teste").build();
		when(planoRepository.findByNomePlanoIgnoreCase(anyString())).thenReturn(Optional.ofNullable(plano));

		DetalhePlanoDTO detalhePlanoDTO = planoService.buscarPlano("Plano teste");

		assertNotNull("Deve retornar o plano", detalhePlanoDTO);
		assertThat("Deve retornar o nome do plano", detalhePlanoDTO.getNome(), equalTo(plano.getNomePlano()));

	}

	@Test(expected = PlanoNotFoundException.class)
	public void naoDeveBuscarPlanoPoisNaoFoiEncontrado() throws PlanoNotFoundException {

		Plano plano = null;
		when(planoRepository.findByNomePlanoIgnoreCase(anyString())).thenReturn(Optional.ofNullable(plano));

		planoService.buscarPlano("Plano teste");

	}

	@Test
	public void deveRemoverPlano() throws PlanoNotFoundException {

		Plano plano = new PlanoBuilder().build();
		when(planoRepository.findByNomePlanoIgnoreCase(anyString())).thenReturn(Optional.ofNullable(plano));

		planoService.removerPlano("plano teste");

		verify(planoRepository, times(1)).delete(any(Plano.class));

	}

	@Test(expected = PlanoNotFoundException.class)
	public void naoDeveRemoverPlanoPoisPlanoNaoFoiEncontrado() throws PlanoNotFoundException {

		Plano plano = null;
		when(planoRepository.findByNomePlanoIgnoreCase(anyString())).thenReturn(Optional.ofNullable(plano));

		planoService.removerPlano("plano teste");

		verify(planoRepository, never()).delete(any(Plano.class));

	}

	@Test
	public void deveAlterarPlano() throws PlanoNotFoundException {

		Plano plano = new PlanoBuilder().build();
		when(planoRepository.findByNomePlanoIgnoreCase(anyString())).thenReturn(Optional.ofNullable(plano));

		planoService.alterarPlano("plano teste", new AlterarPlanoDTOBuilder().valor(29.99).build());

		verify(planoRepository, times(1)).save(any(Plano.class));

	}

	@Test(expected = PlanoNotFoundException.class)
	public void naoDeveAlterarPlanoPoisPlanoNaoFoiEncontrado() throws PlanoNotFoundException {

		Plano plano = null;
		when(planoRepository.findByNomePlanoIgnoreCase(anyString())).thenReturn(Optional.ofNullable(plano));

		planoService.alterarPlano("plano teste", new AlterarPlanoDTOBuilder().valor(29.99).build());

		verify(planoRepository, never()).save(any(Plano.class));

	}

	@After
	public void afterTest() {
		reset(planoRepository);
	}

}
