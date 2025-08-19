package com.lta.apis.curso_introduccion_apis.controller;

import com.lta.apis.curso_introduccion_apis.entity.Categoria;
import com.lta.apis.curso_introduccion_apis.service.CategoriaService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import lombok.SneakyThrows;
import org.apache.coyote.BadRequestException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

/**
 * Controlador para gestionar las operaciones CRUD de las categorías.
 */
@RestController
@RequestMapping("/api/categorias")
public class CategoriaController {

    @Autowired
    private CategoriaService categoriaService;

    /**
     * Crea una nueva categoría.
     * @param categoria El objeto de la categoría a crear.
     * @return La categoría creada con estado 201 (Created).
     * @throws BadRequestException Si los datos de la categoría son inválidos.
     */
    @Operation(summary = "Crear una nueva categoría")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Categoría creada exitosamente"),
            @ApiResponse(responseCode = "400", description = "Solicitud inválida")
    })
    @PostMapping
    public ResponseEntity<Categoria> crearCategoria(@RequestBody Categoria categoria) throws BadRequestException {
        Categoria nuevaCategoria = categoriaService.crearCategoria(categoria);
        return new ResponseEntity<>(nuevaCategoria, HttpStatus.CREATED);
    }

    /**
     * Obtiene una lista de todas las categorías.
     * @return Lista de todas las categorías con estado 200 (OK).
     */
    @Operation(summary = "Listar todas las categorías")
    @ApiResponse(responseCode = "200", description = "Lista de categorías obtenida")
    @GetMapping
    public ResponseEntity<List<Categoria>> listarCategorias(){
        List<Categoria> categorias = categoriaService.listarCategorias();
        return new ResponseEntity<>(categorias, HttpStatus.OK);
    }

    /**
     * Obtiene una categoría por su ID.
     * @param idCategoria ID de la categoría a buscar.
     * @return La categoría encontrada con estado 200 (OK).
     * @throws Exception si la categoría no se encuentra.
     */
    @Operation(summary = "Obtener una categoría por su ID")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Categoría encontrada"),
            @ApiResponse(responseCode = "404", description = "Categoría no encontrada")
    })
    @GetMapping("/{idCategoria}")
    public ResponseEntity<Categoria> obtenerCategoriaPorId(@PathVariable Long idCategoria) throws Exception{
        Optional<Categoria> categoriaOptional = categoriaService.obtenerCategoriaPorId(idCategoria);
        if (categoriaOptional.isPresent()){
            return new ResponseEntity<>(categoriaOptional.get(), HttpStatus.OK);
        }else {
            throw new Exception("Categoria no encontrada");
        }
    }

    /**
     * Actualiza una categoría existente.
     * @param idCategoria ID de la categoría a actualizar.
     * @param categoria Objeto con los nuevos datos de la categoría.
     * @return La categoría actualizada con estado 200 (OK).
     */
    @Operation(summary = "Actualizar una categoría existente")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Categoría actualizada"),
            @ApiResponse(responseCode = "404", description = "Categoría no encontrada")
    })
    @PutMapping("/{idCategoria}")
    @SneakyThrows
    public ResponseEntity<Categoria> actualizarCategoria(@PathVariable Long idCategoria, @RequestBody Categoria categoria){
        try {
            Categoria categoriaActualizada = categoriaService.actualizarCategoria(idCategoria, categoria);
            if (categoriaActualizada != null){
                return new ResponseEntity<>(categoriaActualizada, HttpStatus.OK);
            }else {
                throw new Exception("Categoria no encontrada para actualizar");
            }
        }catch (Exception exception){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    /**
     * Elimina una categoría por su ID.
     * @param idCategoria ID de la categoría a eliminar.
     * @return Estado 204 (No Content) si se elimina correctamente.
     */
    @Operation(summary = "Eliminar una categoría por su ID")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "204", description = "Categoría eliminada"),
            @ApiResponse(responseCode = "404", description = "Categoría no encontrada")
    })
    @DeleteMapping("/{idCategoria}")
    public ResponseEntity<Void> eliminarCategoria(@PathVariable Long idCategoria){

        try {
            categoriaService.eliminarCategoria(idCategoria);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }catch (Exception exception){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
}