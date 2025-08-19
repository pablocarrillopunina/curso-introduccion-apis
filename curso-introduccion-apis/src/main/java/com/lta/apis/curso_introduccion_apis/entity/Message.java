package com.lta.apis.curso_introduccion_apis.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Representa un objeto de mensaje simple (POJO).
 * No es una entidad de base de datos, se usa para demostración.
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Message {

    /**
     * Identificador del mensaje.
     */
    private int id;

    /**
     * Contenido del mensaje.
     */
    private String content;

}