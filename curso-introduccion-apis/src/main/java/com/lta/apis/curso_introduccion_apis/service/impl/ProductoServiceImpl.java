package com.lta.apis.curso_introduccion_apis.service.impl;

import com.lta.apis.curso_introduccion_apis.entity.Categoria;
import com.lta.apis.curso_introduccion_apis.entity.EstadoProducto;
import com.lta.apis.curso_introduccion_apis.entity.Producto;
import com.lta.apis.curso_introduccion_apis.repository.CategoriaRepository;
import com.lta.apis.curso_introduccion_apis.repository.ProductoRepasitory;
import com.lta.apis.curso_introduccion_apis.service.ProductoService;
import lombok.SneakyThrows;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

/**
 * Implementación del servicio de gestión de productos.
 */
@Service
public class ProductoServiceImpl implements ProductoService {

    @Autowired
    private ProductoRepasitory productoRepasitory;

    @Autowired
    private CategoriaRepository categoriaRepository;

    /**
     * {@inheritDoc}
     * Primero busca la categoría y, si existe, asocia el producto a ella.
     * @throws Exception si la categoría con el ID proporcionado no se encuentra.
     */
    @Override
    @SneakyThrows
    public Producto registrarProducto(Long categoriaId, Producto producto) {
        // Busca la categoría por su ID. Si no la encuentra, lanza una excepción.
        Categoria categoria = categoriaRepository.findById(categoriaId)
                .orElseThrow(()-> new Exception("Categoria con ID " + categoriaId + " no encontrada"));

        // Asocia el producto con la categoría encontrada.
        producto.setCategoria(categoria);

        return productoRepasitory.save(producto);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public List<Producto> listarProductos() {
       return productoRepasitory.findAll();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Optional<Producto> buscarPorNombre(String nombre) {
        return productoRepasitory.findByNombreProducto(nombre);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Optional<Producto> buscarPorId(Long idProducto) {
        return productoRepasitory.findByIdProducto(idProducto);
    }

    /**
     * {@inheritDoc}
     * @throws Exception si el producto o la categoría a actualizar no se encuentran.
     */
    @Override
    @SneakyThrows
    public Producto actualizarProducto(Long idProducto, Producto producto) {
        // Busca el producto existente o lanza una excepción si no se encuentra.
        Producto productoExistente = productoRepasitory.findByIdProducto(idProducto)
                .orElseThrow(() -> new Exception(" Producto con Id " + idProducto + " no encontrado "));
        
        // Actualiza los campos del producto existente.
        productoExistente.setNombreProducto(producto.getNombreProducto());
        productoExistente.setDescripcion(producto.getDescripcion());
        productoExistente.setPrecio(producto.getPrecio());
        productoExistente.setCantidad(producto.getCantidad());
        productoExistente.setEstadoProducto(producto.getEstadoProducto());

        // Si se proporciona una nueva categoría, la actualiza.
        if (producto.getCategoria() != null  && producto.getCategoria().getIdCategoria() != null){
            Categoria categoria = categoriaRepository.findById(producto.getCategoria().getIdCategoria())
                    .orElseThrow(()-> new Exception("Categoria no encontrada"));

            productoExistente.setCategoria(categoria);
        }
        return productoRepasitory.save(productoExistente);
    }

    /**
     * {@inheritDoc}
     * @throws Exception si el producto a eliminar no se encuentra.
     */
    @Override
    @SneakyThrows
    public void eliminarProducto(Long idProducto) {
        // Asegura que el producto exista antes de intentar borrarlo.
        productoRepasitory.findByIdProducto(idProducto)
                .orElseThrow(() -> new Exception(" Producto con Id " + idProducto + " no encontrado "));
        productoRepasitory.deleteById(idProducto);
    }

    /**
     * {@inheritDoc}
     * @throws Exception si el producto a actualizar no se encuentra.
     */
    @Override
    @SneakyThrows
    public Producto cambiarEstadoProducto(Long idProducto, EstadoProducto nuevoEstadoProducto) {
       Producto productoExistente =  productoRepasitory.findByIdProducto(idProducto)
                .orElseThrow(() -> new Exception(" Producto con Id " + idProducto + " no encontrado "));
       // Se debe actualizar el estado del producto existente
       productoExistente.setEstadoProducto(nuevoEstadoProducto);
       return productoRepasitory.save(productoExistente);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public List<Producto> obtenerProductoPorEstado(EstadoProducto estadoProducto) {
        return productoRepasitory.findByEstadoProducto(estadoProducto);
    }
}