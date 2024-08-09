package com.santiagocontreras.webapp.biblioteca1.model;

import java.sql.Date;
import java.util.List;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.Data;

@Entity
@Data
@Table(name = "Prestamos")
public class Prestamo {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Date fechaDePrestamo;
    private Date fechaDeDevolucion;
    private Boolean vigencia; // Saber si la persona ya devolvió los libros o no
    @ManyToOne //Se dice en la entidad en la que estas, de Prestamos a Empleados, te da el primero, el Many
    private Empleado empleado;
    @ManyToOne
    private Cliente cliente;
    @ManyToMany
    @JoinTable(name = "prestamos_libros", //Le especifica a Springboot que esta en Prestamos y se va a conectar a la Detalle
    joinColumns = @JoinColumn(name = "prestamos_id",referencedColumnName = "id"), // Info de la tabla Prestamos
    inverseJoinColumns = @JoinColumn(name = "libros_id", referencedColumnName = "id")) // Info tabla libros
    private List<Libro>libros; // Es una lista dentro de un modelo
}
