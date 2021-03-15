package com.wordl.testoffice.dao;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.wordl.testoffice.exceptions.MyException;
import com.wordl.testoffice.model.Inventario;
import com.wordl.testoffice.repository.InventarioRepository;

@Repository
public class InventarioDao implements IInventarioDao {

	private Logger logger = LoggerFactory.getLogger(getClass());

	@Autowired
	private InventarioRepository inventarioRepository;

	@Override
	public List<Inventario> getInventarioFilter(String nombre, String marca, String valInicial, String valFinal,
			String size) throws MyException {
		try {
			if (size.isBlank()) {
				size = "50";
			}
			logger.info("Valores a buscar: Nombre: {}", nombre);
			return inventarioRepository.getInventario(nombre, marca, valInicial, valFinal, size);
		} catch (Exception e) {
			logger.error("ERROR AL CONSULTAR INFORMACIÓN: {}", e.getMessage());
			throw new MyException("ERROR EN PARAMETROS DE CONSULTA");
		}
	}

	@Override
	public Inventario getProductByID(String id) throws MyException {
		try {
			return inventarioRepository.getSizeInventarioByName(Integer.valueOf(id));
			
		} catch (Exception e) {
			logger.error("ERROR AL CONSULTAR INFORMACIÓN: {}", e.getMessage());
			throw new MyException("ERROR EN PARAMETROS DE CONSULTA");
		}

	}

}
