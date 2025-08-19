package com.lta.apis.curso_introduccion_apis.service;

import com.lta.apis.curso_introduccion_apis.entity.EstadoProducto;
import com.lta.apis.curso_introduccion_apis.entity.Producto;

import java.util.List;
import java.util.Optional;

/**
 * Interfaz para el servicio de gestión de productos.
 * Define las operaciones de negocio para los productos.
 */
public interface ProductoService {

    /**
     * Registra un nuevo producto y lo asocia a una categoría.
     * @param categoriaId ID de la categoría existente.
     * @param producto El producto a registrar.
     * @return El producto guardado.
     */
    Producto registrarProducto(Long categoriaId, Producto producto);

    /**
     * Devuelve una lista de todos los productos.
     * @return Lista de productos.
     */
    List<Producto> listarProductos();

    /**
     * Busca un producto por su nombre.
     * @param nombre El nombre del producto a buscar.
     * @return Un Optional que contiene el producto si se encuentra.
     */
    Optional<Producto> buscarPorNombre(String nombre);

    /**
     * Busca un producto por su ID.
     * @param idProducto El ID del producto a buscar.
     * @return Un Optional que contiene el producto si se encuentra.
     */
    Optional<Producto> buscarPorId(Long idProducto);

    /**
     * Actualiza la información de un producto existente.
     * @param idProducto El ID del producto a actualizar.
     * @param producto El objeto producto con la nueva información.
     * @return El producto actualizado.
     */
    Producto actualizarProducto(Long idProducto, Producto producto);

    /**
     * Elimina un producto de la base de datos.
     * @param idProducto El ID del producto a eliminar.
     */
    void eliminarProducto(Long idProducto);

    /**
     * Cambia el estado de un producto.
     * @param idProducto El ID del producto cuyo estado se va a cambiar.
     * @param nuevoEstadoProducto El nuevo estado para el producto.
     * @return El producto con el estado actualizado.
     */
    Producto cambiarEstadoProducto(Long idProducto, EstadoProducto nuevoEstadoProducto);

    /**
     * Obtiene una lista de productos filtrada por su estado.
     * @param estadoProducto El estado por el cual filtrar.
     * @return Lista de productos que coinciden con el estado.
     */
    List<Producto> obtenerProductoPorEstado(EstadoProducto estadoProducto);
}