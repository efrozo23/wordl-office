package com.wordl.testoffice;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.Collections;
import java.util.List;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.batch.core.annotation.BeforeStep;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;

import com.wordl.testoffice.dao.ICarritoDao;
import com.wordl.testoffice.exceptions.MyException;
import com.wordl.testoffice.model.Carrito;
import com.wordl.testoffice.model.Inventario;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.DEFINED_PORT)
@Configuration
class TestOfficeApplicationTests {
	
	@Autowired
	private TestRestTemplate restTemplate;
	
	@Autowired
	private ICarritoDao icarDao;
	
	private Logger logger = LoggerFactory.getLogger(getClass());
	
	@Value("${server.port}")
	private String serverPort;
	
	private final String URL = "http://localhost:";
	
	@BeforeEach
	public void persisCar() throws MyException {
		Carrito car = new Carrito();
		car.setCantidad(2);
		car.setInventarioId(100);
		car.setEstado("pedido");
		Inventario inv = new Inventario();
		inv.setInventarioId(100);
		inv.setPrecio(200.0);
		inv.setMarca("test");
		inv.setNombre("test");
		inv.setCantStock(20);
		inv.setPorcentajeDesc(10);
		Carrito out =icarDao.addProductCarrito(car, inv);
		logger.info("SE GUARDO: {}", out);
		
	}

	@Test
	void testGetInventario() {
		
		
		ResponseEntity<List> response = restTemplate.getForEntity(URL + serverPort +"/api/shop/getinventario?nombre=hua&precioInicial=1&precioFinal=700000&marca=huaw&size=3" ,List.class);
		logger.info("Respuesta del servicio:{}", response.getBody());
		logger.info("Respuesta del servicio:{}", response.getBody().size());
		assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);
        assertThat(response.getBody().size()>0).isTrue();
		
	}
	
	@Test
	void addProductoCarrito() {
		HttpHeaders httpHeaders = new HttpHeaders();
		httpHeaders.setAccept(Collections.singletonList(MediaType.APPLICATION_JSON));
		Carrito car = new Carrito();
		car.setCantidad(2);
		car.setInventarioId(1);
		car.setEstado("pedido");
		HttpEntity<?> entity = new HttpEntity<Carrito>(car,httpHeaders);
		ResponseEntity<?> response = restTemplate.exchange(URL + serverPort +"/api/shop/saveproduct", HttpMethod.POST, entity, String.class);
		logger.info("Response:{}", response.getBody());
		assertThat(response.getStatusCode()).isEqualTo(HttpStatus.CREATED);
		
	}
	
	@Test
	void testGetProductosCarrito() {
		
		
		ResponseEntity<List> response = restTemplate.getForEntity(URL + serverPort +"/api/shop/getpedidos" ,List.class);
		logger.info("Respuesta del servicio carrito:{}", response.getBody());
		logger.info("Respuesta del servicio carrito:{}", response.getBody().size());
		assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);
        assertThat(response.getBody().size()>0).isTrue();
		
	}
	
	@Test
	void deleteProductoCarrito() {
		HttpHeaders httpHeaders = new HttpHeaders();
		httpHeaders.setAccept(Collections.singletonList(MediaType.APPLICATION_JSON));
		HttpEntity<?> entity = new HttpEntity<String>("",httpHeaders);
		ResponseEntity<?> response = restTemplate.exchange(URL + serverPort +"/api/shop/deletepedidos", HttpMethod.DELETE, entity, String.class);
		logger.info("Response delete:{}", response.getBody());
		assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);
		
	}
	

}
