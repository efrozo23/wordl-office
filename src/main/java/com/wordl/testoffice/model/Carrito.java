package com.wordl.testoffice.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;

@Entity
public class Carrito {
	
	@Id
	@Column(name = "carrito_id")
	@GeneratedValue(strategy = GenerationType.AUTO,generator = "car_sequence")
	private Integer carritoId;
	
	@Column(name = "inventario_id")
	private Integer inventarioId;
	
	@Column(name = "precio_total")
	private Double precioTotal;
	
	private Integer cantidad;
	
	private String estado;

	public Integer getCarritoId() {
		return carritoId;
	}

	public void setCarritoId(Integer carritoId) {
		this.carritoId = carritoId;
	}

	public Integer getInventarioId() {
		return inventarioId;
	}

	public void setInventarioId(Integer inventarioId) {
		this.inventarioId = inventarioId;
	}

	public Double getPrecioTotal() {
		return precioTotal;
	}

	public void setPrecioTotal(Double precioTotal) {
		this.precioTotal = precioTotal;
	}

	public Integer getCantidad() {
		return cantidad;
	}

	public void setCantidad(Integer cantidad) {
		this.cantidad = cantidad;
	}

	public String getEstado() {
		return estado;
	}

	public void setEstado(String estado) {
		this.estado = estado;
	}
	
	

}
