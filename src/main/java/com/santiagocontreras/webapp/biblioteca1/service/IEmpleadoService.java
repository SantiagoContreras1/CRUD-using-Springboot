package com.santiagocontreras.webapp.biblioteca1.service;

import java.util.List;

import com.santiagocontreras.webapp.biblioteca1.model.Empleado;

public interface IEmpleadoService {
    
    public List<Empleado> listarEmpleados();

    public Empleado buscarEmpleado(Long id);

    public Boolean guardarEmpleado(Empleado empleado);

    public void eliminarEmpleado(Empleado empleado);

    public Boolean verificarDpiDuplicado(Empleado empleadoNew); //Metodo de validación

}
