package br.com.cobrancamensal.repository;

import java.util.Optional;

import org.springframework.data.repository.CrudRepository;

import br.com.cobrancamensal.model.Plano;

public interface PlanoRepository extends CrudRepository<Plano, String> {

	Optional<Plano> findByNomePlano(String nomePlano);

}
