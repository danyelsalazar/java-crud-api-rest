package com.danyel.apirest.apirest.Repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.danyel.apirest.apirest.Entities.Producto;
                                                    //<nombreEntidad, tipoDelId>
public interface ProductoRepository extends JpaRepository<Producto, Long>{

}
