package br.com.cobrancamensal.repository;

import org.springframework.data.repository.CrudRepository;

import br.com.cobrancamensal.model.Contrato;
import br.com.cobrancamensal.model.pk.ContratoPK;

public interface ContratoRepository extends CrudRepository<Contrato, ContratoPK> {

}
