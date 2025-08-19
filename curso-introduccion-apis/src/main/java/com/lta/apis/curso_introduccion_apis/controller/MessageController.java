package com.lta.apis.curso_introduccion_apis.controller;

import com.lta.apis.curso_introduccion_apis.entity.Message;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

/**
 * Controlador para gestionar mensajes de demostración.
 * Utiliza una lista en memoria, por lo que los datos se reinician con la aplicación.
 */
@RestController
@RequestMapping("/api/messages")
public class MessageController {

    private List<Message> mensajes = new ArrayList<>();

    public MessageController() {
        mensajes.add(new Message(1, "Suscribete en la tecnologia avanzada"));
        mensajes.add(new Message(2, "Comparte el video en tus redes sociales."));
    }

    /**
     * Obtiene la lista de todos los mensajes.
     * @return Lista de mensajes.
     */
    @Operation(summary = "Listar todos los mensajes")
    @ApiResponse(responseCode = "200", description = "Lista de mensajes obtenida")
    @GetMapping
    public List<Message> listaMensajes(){
        return mensajes;
    }

    /**
     * Obtiene un mensaje específico por su ID.
     * @param id El ID del mensaje a buscar.
     * @return El mensaje si se encuentra, o null si no.
     */
    @Operation(summary = "Obtener un mensaje por su ID")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Mensaje encontrado"),
            @ApiResponse(responseCode = "404", description = "Mensaje no encontrado")
    })
    @GetMapping("/{id}")
    public ResponseEntity<Message> obtenerMessagePorId(@PathVariable int id){
        Optional<Message> message = mensajes.stream()
                .filter(m -> m.getId() == id)
                .findFirst();
        return message.map(ResponseEntity::ok).orElse(ResponseEntity.notFound().build());

    }

    /**
     * Crea un nuevo mensaje.
     * @param message El mensaje a crear.
     * @return El mensaje creado.
     */
    @Operation(summary = "Crear un nuevo mensaje")
    @ApiResponse(responseCode = "201", description = "Mensaje creado exitosamente")
    @PostMapping
    public ResponseEntity<Message> crearMensaje(@RequestBody Message message){
        // Asignar un nuevo ID simple (para demostración)
        int nextId = mensajes.stream().mapToInt(Message::getId).max().orElse(0) + 1;
        message.setId(nextId);
        mensajes.add(message);
        return new ResponseEntity<>(message, HttpStatus.CREATED);
    }

    /**
     * Elimina un mensaje por su ID.
     * @param id El ID del mensaje a eliminar.
     * @return Respuesta vacía con estado 204 (No Content).
     */
    @Operation(summary = "Eliminar un mensaje por su ID")
    @ApiResponse(responseCode = "204", description = "Mensaje eliminado")
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminarMensaje(@PathVariable int id){
        mensajes.removeIf(m -> m.getId() == id);
        return ResponseEntity.noContent().build();
    }
}
