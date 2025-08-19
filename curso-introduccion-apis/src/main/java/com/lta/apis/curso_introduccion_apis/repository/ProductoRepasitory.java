package com.lta.apis.curso_introduccion_apis.repository;

import com.lta.apis.curso_introduccion_apis.entity.EstadoProducto;
import com.lta.apis.curso_introduccion_apis.entity.Producto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

/**
 * Repositorio para la entidad Producto.
 * Proporciona métodos para realizar operaciones de base de datos en productos.
 */
@Repository
public interface ProductoRepasitory extends JpaRepository<Producto, Long> {

    /**
     * Busca un producto por su nombre.
     * @param nombreProducto El nombre del producto a buscar.
     * @return Un Optional que contiene el producto si se encuentra.
     */
    Optional<Producto> findByNombreProducto(String nombreProducto);

    /**
     * Busca un producto por su ID.
     * @param idProducto El ID del producto a buscar.
     * @return Un Optional que contiene el producto si se encuentra.
     */
    Optional<Producto> findByIdProducto(Long idProducto);

    /**
     * Busca todos los productos que coinciden con un estado específico.
     * @param estadoProducto El estado del producto a buscar.
     * @return Una lista de productos que coinciden con el estado.
     */
    List<Producto> findByEstadoProducto(EstadoProducto estadoProducto);
}