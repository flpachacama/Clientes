package com.banquito.cores.clientes.repository;

import com.banquito.cores.clientes.model.NumeroTelefonico;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface NumeroTelefonicoRepository extends MongoRepository<NumeroTelefonico, String> {
}
