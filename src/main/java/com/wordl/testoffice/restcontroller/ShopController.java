package com.wordl.testoffice.restcontroller;

import java.util.Arrays;
import java.util.List;

import javax.websocket.server.PathParam;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.wordl.testoffice.dao.ICarritoDao;
import com.wordl.testoffice.dao.IInventarioDao;
import com.wordl.testoffice.model.Carrito;
import com.wordl.testoffice.model.Inventario;


@RestController
@RequestMapping("/shop")
public class ShopController {

	private Logger logger = LoggerFactory.getLogger(getClass());
	
	@Autowired
	private IInventarioDao invetarioDao;
	
	@Autowired
	private ICarritoDao icarritoDao;

	@GetMapping("/getinventario")
	public ResponseEntity<List<?>> getInventario(@PathParam("nombre") String nombre, @PathParam("precioInicial") String precioInicial,
			@PathParam("precioFinal") String precioFinal,
			@PathParam("size") String size, @PathParam("marca") String marca) {
		logger.info("Ingreo a buscar por: {} - {} - {} - {} - {}", nombre,precioInicial,precioFinal,size,marca);
		try {
			return new ResponseEntity<List<?>>(this.invetarioDao.getInventarioFilter(nombre, marca, precioInicial, precioFinal, size),HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<List<?>>(Arrays.asList("ERROR EN CONSULTA"),new HttpHeaders(),HttpStatus.BAD_REQUEST);
		}
		
	}
	
	@PostMapping("/saveproduct")
	public ResponseEntity<?> saveProduct(@RequestBody Carrito car){
		try {
			
			String id = car.getInventarioId().toString();
			Inventario inv = invetarioDao.getProductByID(id);
			if(inv.getCantStock() < car.getCantidad()) {
				return new ResponseEntity<List<?>>(Arrays.asList("NO HAY PRODUCTOS SUFICIENTES"),new HttpHeaders(),HttpStatus.BAD_REQUEST);
			}
			
			Carrito carOut = icarritoDao.addProductCarrito(car,inv);
			logger.info("DTO GUARDADO: {} ",carOut);
			if(carOut == null) {
				throw new Exception();
			}
			return new ResponseEntity<>(carOut,HttpStatus.CREATED);
		} catch (Exception e) {
			return new ResponseEntity<List<?>>(Arrays.asList("ERROR AL PERSISTIR"),new HttpHeaders(),HttpStatus.BAD_REQUEST);
		}
		
		
	}
	
	@GetMapping("/getpedidos")
	public  ResponseEntity<List<?>> getPedidos(){
		try {
			return new ResponseEntity<List<?>>(icarritoDao.getAll(),HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<List<?>>(Arrays.asList(),HttpStatus.BAD_REQUEST);
		}
	}
	

	@DeleteMapping("/deletepedidos")
	public  ResponseEntity<?> deletePedidos(){
		try {
			icarritoDao.deleteAll();
			return new ResponseEntity("OK",HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<List<?>>(Arrays.asList("ERROR AL ELIMINAR"),HttpStatus.BAD_REQUEST);
		}
	}

}
