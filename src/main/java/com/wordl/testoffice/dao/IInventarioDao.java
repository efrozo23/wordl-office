package com.wordl.testoffice.dao;

import java.util.List;


import org.springframework.stereotype.Service;

import com.wordl.testoffice.exceptions.MyException;
import com.wordl.testoffice.model.Inventario;

@Service
public interface IInventarioDao {
	
	List<Inventario> getInventarioFilter( String nombre,String marca,String valInicial,String valFinal,String size ) throws MyException;
	
	Inventario getProductByID(String id) throws MyException;

}
