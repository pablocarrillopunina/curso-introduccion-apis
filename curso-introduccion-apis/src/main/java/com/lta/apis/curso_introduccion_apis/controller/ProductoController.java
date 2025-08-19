package com.lta.apis.curso_introduccion_apis.controller;

import com.lta.apis.curso_introduccion_apis.entity.EstadoProducto;
import com.lta.apis.curso_introduccion_apis.entity.Producto;
import com.lta.apis.curso_introduccion_apis.service.ProductoService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

/**
 * Controlador para gestionar las operaciones CRUD de los productos.
 */
@RestController
@RequestMapping("/api/productos")
public class ProductoController {

    @Autowired
    private ProductoService productoService;

    /**
     * Registra un nuevo producto asociado a una categoría.
     * @param categoriaId ID de la categoría a la que pertenece el producto.
     * @param nombreProducto Nombre del producto.
     * @param descripcion Descripción del producto.
     * @param precio Precio del producto.
     * @param cantidad Stock inicial del producto.
     * @param estado Estado inicial del producto (DISPONIBLE, NO_DISPONIBLE).
     * @return El producto creado con estado 201 (Created).
     */
    @Operation(summary = "Registrar un nuevo producto")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Producto registrado exitosamente"),
            @ApiResponse(responseCode = "404", description = "Categoría no encontrada")
    })
    @PostMapping("/registrar/{categoriaId}")
    public ResponseEntity<?> registrarProducto(
            @PathVariable("categoriaId") Long categoriaId,
            @RequestParam("Nombre del producto") String nombreProducto,
            @RequestParam("descripcion") String descripcion,
            @RequestParam("precio") Double precio,
            @RequestParam("cantidad") int cantidad,
            @RequestParam("estado") EstadoProducto estado){

        Producto producto = new Producto();
        producto.setNombreProducto(nombreProducto);
        producto.setDescripcion(descripcion);
        producto.setPrecio(precio);
        producto.setCantidad(cantidad);
        producto.setEstadoProducto(estado);

        Producto productoBBDD = productoService.registrarProducto(categoriaId, producto);
        return ResponseEntity.status(HttpStatus.CREATED).body(producto);
    }

    /**
     * Obtiene una lista de todos los productos.
     * @return Lista de todos los productos con estado 200 (OK).
     */
    @Operation(summary = "Listar todos los productos")
    @ApiResponse(responseCode = "200", description = "Lista de productos obtenida")
    @GetMapping
    public ResponseEntity<List<Producto>> listarProducto(){
        List<Producto> productos = productoService.listarProductos();
        return ResponseEntity.ok(productos);
    }

    /**
     * Busca un producto por su nombre.
     * @param nombre Nombre del producto a buscar.
     * @return El producto encontrado con estado 200 (OK) o un mensaje de no encontrado con estado 404.
     */
    @Operation(summary = "Buscar un producto por su nombre")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Producto encontrado"),
            @ApiResponse(responseCode = "404", description = "Producto no encontrado")
    })
    @GetMapping("/buscar/nombre/{nombre}")
    public ResponseEntity<?> buscarPorNombre(@PathVariable String nombre){
        Optional<Producto> producto = productoService.buscarPorNombre(nombre);
        return producto.isPresent() ? ResponseEntity.ok(producto.get())
                : ResponseEntity.status(HttpStatus.NOT_FOUND).body("Producto no encontrado");
    }

    /**
     * Busca un producto por su ID.
     * @param idProducto ID del producto a buscar.
     * @return El producto encontrado con estado 200 (OK) o un mensaje de no encontrado con estado 404.
     */
    @Operation(summary = "Buscar un producto por su ID")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Producto encontrado"),
            @ApiResponse(responseCode = "404", description = "Producto no encontrado")
    })
    @GetMapping("/buscar/id/{idProducto}")
    public ResponseEntity<?> bucarPorId(@PathVariable Long idProducto){
        Optional<Producto> producto = productoService.buscarPorId(idProducto);
        return producto.isPresent() ? ResponseEntity.ok(producto.get())
                : ResponseEntity.status(HttpStatus.NOT_FOUND).body("Producto no encontrado");
    }

    /**
     * Actualiza un producto existente.
     * @param idProducto ID del producto a actualizar.
     * @param nombreProducto Nuevo nombre del producto.
     * @param descripcion Nueva descripción del producto.
     * @param precio Nuevo precio del producto.
     * @param cantidad Nueva cantidad en stock.
     * @param estado Nuevo estado del producto.
     * @return El producto actualizado con estado 200 (OK) o un mensaje de error con estado 404.
     */
    @Operation(summary = "Actualizar un producto existente")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Producto actualizado"),
            @ApiResponse(responseCode = "404", description = "Producto no encontrado")
    })
    @PutMapping("/actualizar/{idProducto}")
    public ResponseEntity<?> actualizarProducto(
            @PathVariable Long idProducto,
            @RequestParam("Nombre del producto") String nombreProducto,
            @RequestParam("descripcion") String descripcion,
            @RequestParam("precio") Double precio,
            @RequestParam("cantidad") int cantidad,
             @RequestParam("estado") EstadoProducto estado){
        try {
            Producto producto = new Producto();
            producto.setNombreProducto(nombreProducto);
            producto.setDescripcion(descripcion);
            producto.setPrecio(precio);
            producto.setCantidad(cantidad);
            producto.setEstadoProducto(estado);

            Producto productoBBDD = productoService.actualizarProducto(idProducto, producto);
            return ResponseEntity.ok(productoBBDD);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        }
    }

    /**
     * Elimina un producto por su ID.
     * @param idProducto ID del producto a eliminar.
     * @return Estado 204 (No Content) si se elimina correctamente o un mensaje de error con estado 404.
     */
    @Operation(summary = "Eliminar un producto por su ID")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "204", description = "Producto eliminado"),
            @ApiResponse(responseCode = "404", description = "Producto no encontrado")
    })
    @DeleteMapping("/{idProducto}")
    public ResponseEntity<?> eliminarProducto(@PathVariable Long idProducto){
        try {
        productoService.eliminarProducto(idProducto);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
        }catch (Exception e){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        }
    }

    /**
     * Cambia el estado de un producto (e.g., de DISPONIBLE a NO_DISPONIBLE).
     * @param idProducto ID del producto a modificar.
     * @param estadoProducto Nuevo estado del producto.
     * @return El producto con el estado actualizado.
     */
    @Operation(summary = "Cambiar el estado de un producto")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Estado del producto actualizado"),
            @ApiResponse(responseCode = "404", description = "Producto no encontrado")
    })
    @PutMapping("/estado/{idProducto}")
    public ResponseEntity<?> cambiarEstadoProducto(@PathVariable Long idProducto, @RequestBody EstadoProducto estadoProducto){

        try{

            Producto productoActualizado = productoService.cambiarEstadoProducto(idProducto, estadoProducto);
            return ResponseEntity.ok(productoActualizado);
        }catch (Exception exception){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(exception.getMessage());
        }
    }

    /**
     * Lista todos los productos que coinciden con un estado específico.
     * @param estado Estado por el cual filtrar los productos.
     * @return Una lista de productos que coinciden con el estado.
     */
    @Operation(summary = "Listar productos por estado")
    @ApiResponse(responseCode = "200", description = "Productos encontrados para el estado proveído")
    @GetMapping("/estado/{estado}")
    public ResponseEntity<List<Producto>> listarProductosPorEstado(@PathVariable EstadoProducto estado){
       List<Producto> productos = productoService.obtenerProductoPorEstado(estado);
       return ResponseEntity.ok(productos);
    }

}