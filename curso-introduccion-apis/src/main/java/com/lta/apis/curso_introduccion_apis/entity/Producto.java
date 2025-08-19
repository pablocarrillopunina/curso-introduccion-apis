package com.lta.apis.curso_introduccion_apis.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Representa la entidad Producto en la base de datos.
 */
@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Producto {

    /**
     * Identificador único del producto.
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_producto")
    private Long idProducto;

    /**
     * Nombre del producto.
     */
    @Column(name = "nombreProducto", nullable = false, length = 100)
    private String nombreProducto;

    /**
     * Descripción detallada del producto.
     */
    @Column(name = "descripcion")
    private String descripcion;

    /**
     * Precio del producto.
     */
    @Column(name = "precio" , nullable = false)
    private Double precio;

    /**
     * Cantidad de stock disponible.
     */
    @Column(name = "cantidad", nullable = false)
    private int cantidad;

    /**
     * Estado actual del producto (e.g., DISPONIBLE, NO_DISPONIBLE).
     */
    @Enumerated(EnumType.STRING)
    @Column(name = "estado_producto", nullable = false)
    private EstadoProducto estadoProducto;

    /**
     * Categoría a la que pertenece el producto.
     * Relación muchos a uno con la entidad Categoria.
     */
    @ManyToOne
    @JoinColumn(name = "id_categoria", nullable = false)
    private Categoria categoria;

}