package com.santiagocontreras.webapp.biblioteca1.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.santiagocontreras.webapp.biblioteca1.model.Cliente;

public interface ClienteRepository extends JpaRepository<Cliente,Long>{

}
