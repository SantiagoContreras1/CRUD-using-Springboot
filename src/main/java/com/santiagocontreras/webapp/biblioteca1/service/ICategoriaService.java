package com.santiagocontreras.webapp.biblioteca1.service;

import java.util.List;
import com.santiagocontreras.webapp.biblioteca1.model.Categoria;

public interface ICategoriaService {
    public List<Categoria> listarCategoria();

    public Categoria buscarCategoriaPorId(Long id);

    public Boolean guardarCategoria(Categoria categoria); // Es el agregar y el editar

    public void eliminarCategoria(Categoria categoria);

    public Boolean verificarCategoriaDuplicada(Categoria categoriaNew);
}
