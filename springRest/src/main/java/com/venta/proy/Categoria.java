package com.venta.proy;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.*;

import com.fasterxml.jackson.annotation.JsonIgnore;



@Entity
@Table(name="categoria")
public class Categoria implements Serializable {
	
	private static final long serialVersionUID = 1L;
	
	@Id
	private int id;
	private String denominacion;
	
	@JsonIgnore
	@OneToMany(fetch = FetchType.EAGER, mappedBy = "categoria", cascade=CascadeType.ALL,orphanRemoval=true)
	private List<Producto> productos = new ArrayList<Producto>();

	
	public Categoria(int id, String denominacion) {
		super();
		this.id = id;
		this.denominacion = denominacion;
	}

	public Categoria(int id) {
		super();
		this.id = id;
	}

	public Categoria() {
		super();
	}
	
	public void addProducto(Producto p) {
		productos.add(p);
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getDenominacion() {
		return denominacion;
	}

	public void setDenominacion(String denominacion) {
		this.denominacion = denominacion;
	}
	
	
	public List<Producto> getProductos() {
		return productos;
	}

	public void setProductos(List<Producto> productos) {
		this.productos = productos;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + id;
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Categoria other = (Categoria) obj;
		if (id != other.id)
			return false;
		return true;
	}

}
