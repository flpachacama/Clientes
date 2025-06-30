package com.banquito.cores.clientes.repository;

import com.banquito.cores.clientes.model.Direccion;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface DireccionRepository extends MongoRepository<Direccion, String> {
}
