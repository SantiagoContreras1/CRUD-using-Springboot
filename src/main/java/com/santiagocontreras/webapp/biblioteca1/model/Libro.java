package com.santiagocontreras.webapp.biblioteca1.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.Data;

@Entity
@Data
@Table(name= "Libros")
public class Libro {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String isbn;
    private String nombre;
    @Column(columnDefinition = "TEXT")
    private String sinopsis;
    private String autor;
    private String editorial;
    @Column(columnDefinition = "TINYINT(1)")
    private Boolean disponibilidad; //Cada vez que se preste un libro, este cambiará
    // Verdadero cuando se preste, Falso cuando se llegue a devolver a la biblio
    private String numeroEstanteria;
    private String cluster;
    // FK RELACIÓN
    @ManyToOne(fetch = FetchType.EAGER)
    private Categoria categoria;

}
