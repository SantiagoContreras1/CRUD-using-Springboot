package com.santiagocontreras.webapp.biblioteca1.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.santiagocontreras.webapp.biblioteca1.model.Libro;

public interface LibroRepository extends JpaRepository<Libro,Long>{
    
}
