package com.venta.servicios;

import com.venta.proy.Categoria;
import com.venta.proy.Producto;
import com.venta.repositorios.CategoriaRepository;
import com.venta.repositorios.ProductoRepository;

public interface ServicioVenta {

	ProductoRepository getRepoproducto();

	void setRepoproducto(ProductoRepository repoproducto);

	CategoriaRepository getRepocategoria();

	void setRepocategoria(CategoriaRepository repocategoria);

	Producto findOneProd(Integer id);

	Iterable<Producto> findAllProd();

	void saveProd(Producto producto);

	void deleteProd(Producto producto);

	Categoria findOneCat(Integer id);

	Iterable<Categoria> findAllCat();

	void saveCat(Categoria categoria);

	void deleteCat(Categoria categoria);
	
	Iterable<Producto> findByEstado(String estado);

}