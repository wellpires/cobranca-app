package br.com.cobrancamensal.service.impl;

import static org.hamcrest.Matchers.hasSize;
import static org.junit.Assert.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.only;
import static org.mockito.Mockito.reset;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.List;

import org.junit.After;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import br.com.cobrancamensal.builder.PlanoBuilder;
import br.com.cobrancamensal.dto.NovoPlanoDTO;
import br.com.cobrancamensal.dto.PlanoDTO;
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
	public void deveCriarPlano() {
		planoService.criarPlano(new NovoPlanoDTO());
		verify(planoRepository, only()).save(any(Plano.class));
	}

	@Test
	public void deveListarPlanos() {

		int quantidadeItens = 10;
		when(planoRepository.findAll()).thenReturn(new PlanoBuilder().quantidadeItens(quantidadeItens).buildList());

		List<PlanoDTO> planosDTO = planoService.listarPlanos();

		assertThat("Deve retornar os planos", planosDTO, hasSize(quantidadeItens));
	}

	@After
	public void afterTest() {
		reset(planoRepository);
	}

}
