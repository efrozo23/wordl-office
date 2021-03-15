package com.wordl.testoffice.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import com.wordl.testoffice.model.Carrito;

public interface CarritoRepository extends JpaRepository<Carrito,Integer>{
	
	@Modifying
	@Query(value = "delete from carrito where estado = 'pedido' ",nativeQuery = true)
	void deleteByEstado();
}
