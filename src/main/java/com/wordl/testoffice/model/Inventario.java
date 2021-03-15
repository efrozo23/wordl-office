package com.wordl.testoffice.model;


import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
public class Inventario {

	@Id
	@GeneratedValue
	@Column(name = "inventario_id")
	private Integer inventarioId;
	private String nombre;
	private String marca;
	private Double precio;
	private Integer cantStock;
	private String estado;
	private Integer porcentajeDesc;

	public Inventario() {

	}

	public Inventario(String nombre, String marca, Double precio, Integer cantStock, String estado,
			Integer porcentajeDesc) {
		super();
		this.nombre = nombre;
		this.marca = marca;
		this.precio = precio;
		this.cantStock = cantStock;
		this.estado = estado;
		this.porcentajeDesc = porcentajeDesc;
	}

	

	public Integer getInventarioId() {
		return inventarioId;
	}

	public void setInventarioId(Integer inventarioId) {
		this.inventarioId = inventarioId;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getMarca() {
		return marca;
	}

	public void setMarca(String marca) {
		this.marca = marca;
	}

	public Double getPrecio() {
		return precio;
	}

	public void setPrecio(Double precio) {
		this.precio = precio;
	}

	public Integer getCantStock() {
		return cantStock;
	}

	public void setCantStock(Integer cantStock) {
		this.cantStock = cantStock;
	}

	public String getEstado() {
		return estado;
	}

	public void setEstado(String estado) {
		this.estado = estado;
	}

	public Integer getPorcentajeDesc() {
		return porcentajeDesc;
	}

	public void setPorcentajeDesc(Integer porcentajeDesc) {
		this.porcentajeDesc = porcentajeDesc;
	}

}
