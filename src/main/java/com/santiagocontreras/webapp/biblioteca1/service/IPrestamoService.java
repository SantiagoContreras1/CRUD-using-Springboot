package com.santiagocontreras.webapp.biblioteca1.service;


import java.util.List;

import com.santiagocontreras.webapp.biblioteca1.model.Prestamo;
import com.santiagocontreras.webapp.biblioteca1.util.MethodType;

public interface IPrestamoService {
    public List<Prestamo>listarPrestamos();

    public Prestamo buscarPrestamoPorId(Long id);

    public Prestamo guardarPrestamo(Prestamo prestamo,MethodType methodType);

    public void eliminarPrestamo(Prestamo prestamo);

}
