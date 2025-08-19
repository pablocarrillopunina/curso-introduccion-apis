package com.lta.apis.curso_introduccion_apis.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Controlador de ejemplo para un endpoint de saludo simple.
 */
@RestController
@RequestMapping("/api/saludo") // Se cambió "/micontroller" a "/api/saludo" para consistencia
public class SaludoController {

    /**
     * Devuelve un saludo simple.
     * @return Un string con el mensaje "Hola Mundo".
     */
    @Operation(summary = "Obtener un saludo simple")
    @ApiResponse(responseCode = "200", description = "Saludo obtenido exitosamente")
    @GetMapping
    public String saludar(){
        return "Hola Mundo";
    }

}