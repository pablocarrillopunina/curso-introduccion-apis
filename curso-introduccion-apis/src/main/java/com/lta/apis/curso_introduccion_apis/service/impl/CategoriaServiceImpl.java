package com.lta.apis.curso_introduccion_apis.service.impl;

import com.lta.apis.curso_introduccion_apis.entity.Categoria;
import com.lta.apis.curso_introduccion_apis.repository.CategoriaRepository;
import com.lta.apis.curso_introduccion_apis.service.CategoriaService;
import lombok.SneakyThrows;
import org.apache.coyote.BadRequestException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

/**
 * Implementación del servicio de gestión de categorías.
 */
@Service
public class CategoriaServiceImpl implements CategoriaService {

    @Autowired
    private CategoriaRepository categoriaRepository;

    /**
     * {@inheritDoc}
     * Verifica si ya existe una categoría con el mismo nombre antes de crearla.
     * @throws BadRequestException si ya existe una categoría con ese nombre.
     */
    @Override
    @SneakyThrows
    public Categoria crearCategoria(Categoria categoria) {
        if (categoriaRepository.existsByNombreCategoria(categoria.getNombreCategoria())){
            throw new BadRequestException("Ya existe una categoria con ese nombre");
        }
        return categoriaRepository.save(categoria);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public List<Categoria> listarCategorias() {
        return categoriaRepository.findAll();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Optional<Categoria> obtenerCategoriaPorId(Long idCategoria) {
        return categoriaRepository.findById(idCategoria);
    }

    /**
     * {@inheritDoc}
     * @throws Exception si la categoría a actualizar no se encuentra.
     */
    @Override
    @SneakyThrows
    public Categoria actualizarCategoria(Long idCategoria, Categoria categoria) {
        // Busca la categoría existente o lanza una excepción si no se encuentra.
        Categoria categoriaExistente = categoriaRepository.findById(idCategoria).
                orElseThrow(() -> new Exception("Categoria no encontrada"));

        // Actualiza el nombre de la categoría.
        categoriaExistente.setNombreCategoria(categoria.getNombreCategoria());
        return categoriaRepository.save(categoriaExistente);
    }

    /**
     * {@inheritDoc}
     * @throws Exception si la categoría a eliminar no se encuentra.
     */
    @Override
    @SneakyThrows
    public void eliminarCategoria(Long idCategoria) {
        // Asegura que la categoría exista antes de intentar borrarla.
        Optional<Categoria> categoriaExistente = categoriaRepository.findById(idCategoria);
        if(!categoriaExistente.isPresent()){
            throw new Exception("Categoria no encontrada para eliminar");
        }
        categoriaRepository.deleteById(idCategoria);
    }
}