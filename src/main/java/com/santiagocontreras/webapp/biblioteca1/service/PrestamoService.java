package com.santiagocontreras.webapp.biblioteca1.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.santiagocontreras.webapp.biblioteca1.model.Prestamo;
import com.santiagocontreras.webapp.biblioteca1.repository.PrestamoRepository;
import com.santiagocontreras.webapp.biblioteca1.util.MethodType;

@Service
public class PrestamoService implements IPrestamoService{
    @Autowired
    PrestamoRepository prestamoRepository;

    @Override
    public List<Prestamo> listarPrestamos() {
        return prestamoRepository.findAll();
    }

    @Override
    public Prestamo buscarPrestamoPorId(Long id) {
        return prestamoRepository.findById(id).orElse(null);
    }

    @Override
    public Prestamo guardarPrestamo(Prestamo prestamo,MethodType methodType) {
       if (methodType == MethodType.POST) {
        return prestamoRepository.save(prestamo);
       }else if (methodType == MethodType.PUT) {
        return prestamoRepository.save(prestamo);    
       }else{
        return prestamoRepository.save(null);
       }
    }

    @Override
    public void eliminarPrestamo(Prestamo prestamo) {
        prestamoRepository.delete(prestamo);
    }

}
