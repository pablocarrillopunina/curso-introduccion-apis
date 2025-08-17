package com.lta.apis.curso_introduccion_apis.repository;

import com.lta.apis.curso_introduccion_apis.entity.EstadoProducto;
import com.lta.apis.curso_introduccion_apis.entity.Producto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ProductoRepasitory extends JpaRepository<Producto, Long> {

    Optional<Producto> findByNombreProducto(String nombreProducto);

    Optional<Producto> findByIdProducto(Long idProducto);

    List<Producto> findByEstadoProducto(EstadoProducto estadoProducto);
}
