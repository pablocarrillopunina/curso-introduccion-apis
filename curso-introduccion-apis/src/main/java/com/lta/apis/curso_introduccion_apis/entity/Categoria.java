package com.lta.apis.curso_introduccion_apis.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Representa la entidad Categoria en la base de datos.
 */
@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Categoria {

    /**
     * Identificador único de la categoría.
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_categoria")
    private Long idCategoria;

    /**
     * Nombre de la categoría.
     */
    @Column(name = "nombre_categoria", nullable = false, length = 50)
    private String nombreCategoria;

    /**
     * Representa una relación jerárquica (categoría padre).
     * Permite anidar categorías.
     */
    @ManyToOne
    @JoinColumn(name = "id_categorias", referencedColumnName = "id_categoria")
    private Categoria categoria;
}