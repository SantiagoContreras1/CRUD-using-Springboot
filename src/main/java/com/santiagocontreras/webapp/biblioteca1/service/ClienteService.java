package com.santiagocontreras.webapp.biblioteca1.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.santiagocontreras.webapp.biblioteca1.model.Cliente;
import com.santiagocontreras.webapp.biblioteca1.repository.ClienteRepository;

@Service
public class ClienteService implements IClienteService{

    @Autowired
    private ClienteRepository clienteRepository;

    @Override
    public List<Cliente> listarClientes() {  
       return clienteRepository.findAll();
    }

    @Override
    public Cliente buscarCliente(Long id) {
       return clienteRepository.findById(id).orElse(null);
    }

    @Override
    public Cliente guardarCliente(Cliente cliente) {
        return clienteRepository.save(cliente);
    }

    @Override
    public void eliminarCiente(Cliente cliente) {
        clienteRepository.delete(cliente);
    }

}
