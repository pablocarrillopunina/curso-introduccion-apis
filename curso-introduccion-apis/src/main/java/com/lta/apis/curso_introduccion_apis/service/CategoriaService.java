package com.lta.apis.curso_introduccion_apis.service;

import com.lta.apis.curso_introduccion_apis.entity.Categoria;

import java.util.List;
import java.util.Optional;

/**
 * Interfaz para el servicio de gestión de categorías.
 * Define las operaciones de negocio para las categorías.
 */
public interface CategoriaService {

    /**
     * Crea una nueva categoría.
     * @param categoria La categoría a crear.
     * @return La categoría guardada.
     */
    Categoria crearCategoria(Categoria categoria);

    /**
     * Devuelve una lista de todas las categorías.
     * @return Lista de categorías.
     */
    List<Categoria> listarCategorias();

    /**
     * Obtiene una categoría por su ID.
     * @param idCategoria El ID de la categoría a buscar.
     * @return Un Optional que contiene la categoría si se encuentra.
     */
    Optional<Categoria> obtenerCategoriaPorId(Long idCategoria);

    /**
     * Actualiza una categoría existente.
     * @param idCategoria El ID de la categoría a actualizar.
     * @param categoria El objeto categoría con la nueva información.
     * @return La categoría actualizada.
     */
    Categoria actualizarCategoria(Long idCategoria, Categoria categoria);

    /**
     * Elimina una categoría de la base de datos.
     * @param idCategoria El ID de la categoría a eliminar.
     */
    void eliminarCategoria(Long idCategoria);
}