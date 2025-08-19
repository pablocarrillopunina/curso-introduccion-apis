package com.lta.apis.curso_introduccion_apis.repository;

import com.lta.apis.curso_introduccion_apis.entity.Categoria;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

/**
 * Repositorio para la entidad Categoria.
 * Proporciona métodos para realizar operaciones de base de datos en categorías.
 */
@Repository
public interface CategoriaRepository extends JpaRepository<Categoria, Long> {

    /**
     * Busca una categoría por su nombre.
     * @param nombreCategoria El nombre de la categoría a buscar.
     * @return Un Optional que contiene la categoría si se encuentra.
     */
    Optional<Categoria> findByNombreCategoria(String nombreCategoria);

    /**
     * Verifica si ya existe una categoría con un nombre específico.
     * @param nombreCategoria El nombre de la categoría a verificar.
     * @return true si la categoría existe, false en caso contrario.
     */
    boolean existsByNombreCategoria(String nombreCategoria);

}