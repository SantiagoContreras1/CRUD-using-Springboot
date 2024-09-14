package com.santiagocontreras.webapp.biblioteca1.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.santiagocontreras.webapp.biblioteca1.model.Empleado;
import com.santiagocontreras.webapp.biblioteca1.repository.EmpleadoRepository;

@Service
public class EmpleadoService implements IEmpleadoService{
    @Autowired
    private EmpleadoRepository empleadoRepository;

    @Override
    public List<Empleado> listarEmpleados() {
        return empleadoRepository.findAll();
    }

    @Override
    public Empleado buscarEmpleado(Long id) {
       return empleadoRepository.findById(id).orElse(null);
    }

   @Override
   public Boolean guardarEmpleado(Empleado empleado) {
      if(!verificarDpiDuplicado(empleado)){
         empleadoRepository.save(empleado);//DPI no duplicado
         return true; //Este le sirve al conroller para saber que pasó al final, si si estaba duplicado o no
      }else{
         return false;//DPI si está duplicado
      }
   }

   @Override
   public void eliminarEmpleado(Empleado empleado) {
      empleadoRepository.delete(empleado);
   }

   //Validación
   @Override
   public Boolean verificarDpiDuplicado(Empleado empleadoNew) {
      List<Empleado>empleados = listarEmpleados();
      Boolean flag = false;
      for (Empleado empleado : empleados) {
         if (empleado.getDpi().equals(empleadoNew.getDpi()) && !empleado.getId().equals(empleadoNew.getId())) {//Si el DPI del empleado nuevo si es igual al dpi del empleado de la lista
            flag = true;//Si hay DPI Duplicado
         }
      }
      return flag;
   }//Este metodo se manda a llamar en el guardarEmpleado

    

}
