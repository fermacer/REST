package com.venta.test;



import org.junit.*;

import com.venta.proy.Categoria;
import com.venta.proy.Producto;

public class DeleteTest extends JPAUnitTest{
	
	
	@Test 
	public void eliminar() {
		
		Categoria c = em.find(Categoria.class, 1);
		em.getTransaction().begin();
		em.remove(c);
		em.getTransaction().commit();
		
	
		Producto p = em.find(Producto.class, 31);
		em.getTransaction().begin();
		em.remove(p);
		em.getTransaction().commit();
		
		
	}

}
