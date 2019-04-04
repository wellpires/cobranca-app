package br.com.cobrancamensal.repository;

import org.springframework.data.repository.CrudRepository;

import br.com.cobrancamensal.model.Cliente;

public interface ClienteRepository extends CrudRepository<Cliente, Long> {

}
