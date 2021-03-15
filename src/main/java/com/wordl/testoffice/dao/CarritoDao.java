package com.wordl.testoffice.dao;

import java.util.Arrays;
import java.util.List;



import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.wordl.testoffice.model.Carrito;
import com.wordl.testoffice.model.Inventario;
import com.wordl.testoffice.repository.CarritoRepository;

@Repository
@Transactional
public class CarritoDao implements ICarritoDao {

	private Logger logger = LoggerFactory.getLogger(getClass());

	@Autowired
	private CarritoRepository carritoRepository;

	@Override
	public Carrito addProductCarrito(Carrito car, Inventario inv) {
		try {

			double valorTotal = inv.getPrecio() - (inv.getPrecio() * inv.getPorcentajeDesc()) / 100;
			car.setPrecioTotal(valorTotal * car.getCantidad());
			return carritoRepository.save(car);

		} catch (Exception e) {
			logger.error("ERROR AL PERSISTIR:{}", e.getMessage());
			return null;
		}
	}

	@Override
	public List<Carrito> getAll() {
		try {
			return carritoRepository.findAll();
		} catch (Exception e) {
			logger.error("ERROR AL CONSULTAR:{}", e.getMessage());
			return Arrays.asList();
		}
	}

	@Override
	public void deleteAll() {
		try {
			carritoRepository.deleteByEstado();;
		} catch (Exception e) {
			logger.error("ERROR AL ELIMINAR:{}", e.getMessage());
		}

	}

}
