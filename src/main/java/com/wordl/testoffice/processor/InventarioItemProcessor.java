package com.wordl.testoffice.processor;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.batch.item.ItemProcessor;

import com.wordl.testoffice.model.Inventario;


public class InventarioItemProcessor implements ItemProcessor<Inventario, Inventario>{
	
	private static final Logger loger = LoggerFactory.getLogger(InventarioItemProcessor.class);
	
	
	
	
	@Override
	public Inventario process(Inventario item) throws Exception {
		Inventario out = new Inventario();
		
		out.setNombre(item.getNombre());
		out.setMarca(item.getMarca());
		out.setPrecio(item.getPrecio());
		out.setEstado(item.getEstado());
		out.setCantStock(item.getCantStock());
		out.setPorcentajeDesc(item.getPorcentajeDesc());
		
		if(item.getMarca() == null || item.getMarca().isEmpty() || item.getNombre() == null || item.getNombre().isEmpty() || item.getPrecio() ==null 
				|| item.getEstado() ==null || item.getEstado().isEmpty() ||item.getCantStock() ==null || item.getPorcentajeDesc() == null) {
			return null;
		}
			
		
		loger.info("Objeto de salida: {}", out.getNombre());
		
		return out;	
		
	}
	

}
