package com.santiagocontreras.webapp.biblioteca1.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.santiagocontreras.webapp.biblioteca1.model.Prestamo;

public interface PrestamoRepository extends JpaRepository<Prestamo,Long>{

}
