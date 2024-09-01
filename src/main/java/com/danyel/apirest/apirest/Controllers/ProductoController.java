package com.danyel.apirest.apirest.Controllers;

import java.util.List;

import javax.management.RuntimeErrorException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.danyel.apirest.apirest.Repositories.ProductoRepository;
import com.danyel.apirest.apirest.Entities.Producto;;

@RestController
@RequestMapping("/productos")
public class ProductoController {

    //!!con las siguientes dos lineas de codigo hacemos una instanciacion de nuestro repositorio(CONECCION CON N UESTRA BASE DE DATOS), ahorrandonos el trabajo pesado de hacer un monton de lineas de codigpo JPA
    @Autowired
    private ProductoRepository productoRepository;

    @GetMapping
    public List<Producto> ObtenerProducto(){
        return productoRepository.findAll();//asi traemos todos los productos que tenga este repositorio
    }

    @GetMapping("/{id}")
    public Producto obtenerProductoPorId(@PathVariable Long id){
        return productoRepository.findById(id)
        .orElseThrow(() -> new RuntimeException("No se encontro el producto con el ID: " + id));
    }

    @PostMapping
    public Producto crearProducto(@RequestBody Producto producto){
        return productoRepository.save(producto);//asi estamos grabando en la base de datos con el .save y tambien esto devuelve un producto y este si vendra con el id autogenerable que tiene como atributo en la entidad 
    }



    @PutMapping("/{id}")
    public Producto actualizarProducto(@PathVariable Long id, @RequestBody Producto detallesProducto){

        //primero debemos buscar el producto; si lo encuentra lo guarda en producto y si no lo encuentra manda el error No se encontro el producto con el ID
        Producto producto = productoRepository.findById(id)
        .orElseThrow(() -> new RuntimeException("No se encontro el producto con el ID: " + id));

        //Ahora modificamos el producto
        producto.setNombre(detallesProducto.getNombre());
        producto.setPrecio(detallesProducto.getPrecio());

        return productoRepository.save(producto); //y aqui devolvemos el producto actualizado
    }

    //hacemos ahora el delete
    @DeleteMapping("/{id}")
    public String eliminarProducto(@PathVariable Long id){
        //Buscamos el producto que se quiere elimianar
        Producto producto = productoRepository.findById(id)
        .orElseThrow(() -> new RuntimeException("No se encontro el producto con el id: " + id));

        productoRepository.delete(producto);

        return "El producto con el ID:" + id + " fue eliminado correctamente";
        //tambien podemos devolver el producto eliminado y se haria poniendo el metodo en tipo Producto y en el return
        //poner return productoRepository.delete(producto)

    }

}