package com.wordl.testoffice.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.wordl.testoffice.model.Inventario;

public interface InventarioRepository  extends JpaRepository<Inventario	, Integer>{
	
	@Query(value = "select * from inventario where LOWER(nombre) like LOWER('%'||:nombre||'%') and LOWER(marca) like LOWER('%'||:marca||'%' ) and precio between :valInicial and :valFinal order by precio asc LIMIT :tam  ",nativeQuery = true)
	public List<Inventario> getInventario(@Param("nombre") String nombre,@Param("marca") String marca , @Param("valInicial") String valInicial,@Param("valFinal") String valFinal,@Param("tam") String tam );

	@Query(value = "select i from Inventario i where i.inventarioId= :id ")
	public Inventario getSizeInventarioByName(@Param("id") Integer id);

}
