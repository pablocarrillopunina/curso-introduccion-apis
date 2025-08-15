package com.lta.apis.curso_introduccion_apis.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 - Controller : Es una clase en java que gestiona las solicitudes HTTP(come GET, POST, PUT, DELETE)
    que llega a la aplicacion web o API, es el puente entre el cliente y la lógica de negocio o servicios.

 - En otras palabras es donde defines qué hacer cuando alguien visita cierta URL
 Controlador REST: Es una clase en Java que expone rutas HTTP (Urls) para que otros sistemas puedan enviarle
 peticiones y revcibir respuesta en fomrato JSON
 * -- Indica que esta clase es un controlador REST
 * -- Combina @Cotroller y @ResponseBody
 * -- No vistas HTML
 */
//Indicamos que esta clase es un controlador Web en APIs REST
@RestController
//Configruaramos una URL para todos los métodos de controlador.
@RequestMapping("/micontroller")
public class SaludoController {

    @GetMapping("/saludo")
    public String saludar(){
        return "Hola Mundo";
    }

}
