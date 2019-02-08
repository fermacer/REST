package com.venta.controladores.web.rest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.venta.proy.Categoria;
import com.venta.proy.Producto;
import com.venta.repositorios.ProductoRepository;
import com.venta.servicios.ServicioVenta;


	@RestController
	@RequestMapping("/apiproducto")
	public class ProductoRestController {
		@Autowired
		ServicioVenta servicio;

		@GetMapping(value = "/all", produces = MediaType.APPLICATION_JSON_VALUE)
		public Iterable<Producto> findAllProductos() {

			//return servicio.findAllProd(); YA NO LO USAMOS - SOLO MOSTRAMOS LOS ACTIVOS
			return servicio.findByEstado("A");
		}

		@GetMapping(value = "/find/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
		public Producto findOne(@PathVariable Integer id) {

			return servicio.findOneProd(id);
		}

		@PostMapping(value = "/save", produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
		public void save(@RequestBody Producto Producto) {
				//Al crear nuevo producto lo colocamos con estado activo
			    Producto.setEstado("A");
				servicio.saveProd(Producto);

		}

		@PutMapping(value = "/update/{id}", produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
		public void update(@RequestBody Producto Producto, @PathVariable Integer id) {

			Producto c = servicio.findOneProd(id);
			// informacion que viene de la peticion put para modificar
			c.setNombre(Producto.getNombre());
			c.setStock(Producto.getStock());
			c.setCategoria(Producto.getCategoria());
			servicio.saveProd(c);
		}

		@PutMapping(value = "/erase/{id}", produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
		public void erase(@RequestBody Producto Producto, @PathVariable Integer id) {

			Producto c = servicio.findOneProd(id);
			// seteamos el atributo estado para dar de baja
			c.setEstado("B");
			servicio.saveProd(c);
		}
		
		
	}

