package com.santiagocontreras.webapp.biblioteca1.service;

import java.util.List;

import com.santiagocontreras.webapp.biblioteca1.model.Cliente;

public interface IClienteService {

    public List<Cliente> listarClientes();

    public Cliente buscarCliente(Long id);

    public Cliente guardarCliente(Cliente cliente);

    public void eliminarCiente(Cliente cliente);
}
