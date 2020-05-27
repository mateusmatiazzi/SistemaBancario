package com.desafio.backend.model.repository;

import com.desafio.backend.model.Cliente;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ClienteRepositorio extends CrudRepository<Cliente, Long> {
    Cliente getByNumeroDaConta(int numeroDaConta);
}
