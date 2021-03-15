package com.wordl.testoffice.dao;

import java.util.List;

import org.springframework.stereotype.Service;

import com.wordl.testoffice.exceptions.MyException;
import com.wordl.testoffice.model.Carrito;
import com.wordl.testoffice.model.Inventario;

@Service
public interface ICarritoDao {
	
	Carrito addProductCarrito(Carrito car,Inventario inv) throws MyException;
	
	List<Carrito> getAll();
	
	void deleteAll();

}
