package com.banquito.cores.clientes.repository;

import com.banquito.cores.clientes.model.ContactoTransaccional;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface ContactoTransaccionalRepository extends MongoRepository<ContactoTransaccional, String> {
}
