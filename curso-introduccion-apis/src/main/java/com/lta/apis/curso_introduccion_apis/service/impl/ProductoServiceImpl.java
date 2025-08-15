package com.lta.apis.curso_introduccion_apis.service.impl;

import com.lta.apis.curso_introduccion_apis.entity.EstadoProducto;
import com.lta.apis.curso_introduccion_apis.entity.Producto;
import com.lta.apis.curso_introduccion_apis.repository.ProductoRepasitory;
import com.lta.apis.curso_introduccion_apis.service.ProductoService;
import jakarta.persistence.Id;
import lombok.SneakyThrows;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;
import java.util.Optional;

public class ProductoServiceImpl implements ProductoService {

    @Autowired
    private ProductoRepasitory productoRepasitory;

    @Override
    public Producto registrarProducto(Producto producto) {
        return productoRepasitory.save(producto);

    }

    @Override
    public List<Producto> listarProductos() {
       return productoRepasitory.findAll();
    }

    @Override
    public Optional<Producto> buscarPorNombre(String nombre) {
        return productoRepasitory.findByNombreProducto(nombre);
    }

    @Override
    public Optional<Producto> buscarPorId(Long idProducto) {
        return productoRepasitory.findByIdProducto(idProducto);
    }

    @Override
    @SneakyThrows
    public Producto actualizarProducto(Long idProducto, Producto producto) {
        Producto productoExistente = productoRepasitory.findByIdProducto(idProducto)
                .orElseThrow(() -> new Exception(" Producto con Id " + idProducto + " no encontrado "));
        productoExistente.setNombreProducto(producto.getNombreProducto());
        productoExistente.setDescripcion(producto.getDescripcion());
        productoExistente.setPrecio(producto.getPrecio());
        productoExistente.setCantidad(producto.getCantidad());
        productoExistente.setEstadoProducto(producto.getEstadoProducto());

        return productoRepasitory.save(productoExistente);
    }

    @Override
    @SneakyThrows
    public void eliminarProducto(Long idProducto) {

        productoRepasitory.findByIdProducto(idProducto)
                .orElseThrow(() -> new Exception(" Producto con Id " + idProducto + " no encontrado "));
        productoRepasitory.deleteById(idProducto);
    }

    @Override
    @SneakyThrows
    public Producto cambiarEstadoProducto(Long idProducto, EstadoProducto nuevoEstadoProducto) {
       Producto productoExistente =  productoRepasitory.findByIdProducto(idProducto)
                .orElseThrow(() -> new Exception(" Producto con Id " + idProducto + " no encontrado "));
        return productoRepasitory.save(productoExistente);
    }

    @Override
    public List<Producto> obtenerProductoPorEstado(EstadoProducto estadoProducto) {
        return productoRepasitory.findByEstado(estadoProducto);
    }
}
