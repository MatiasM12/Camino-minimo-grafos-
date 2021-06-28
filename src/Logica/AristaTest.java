package Logica;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

public class AristaTest 
{
	
	private Arista arista1,arista2,arista3;

	@Before 
	public void inicializacion() 
	{
		Vertice vertice1= new Vertice("Berlin",0);
		Vertice vertice2= new Vertice("Baden",1);
		Vertice vertice3= new Vertice("Baviera",2);
		Vertice vertice4= new Vertice("Baden",1);
		arista1= new Arista(vertice1,vertice2,10);
		arista2= new Arista(vertice1,vertice2,10);
		arista3= new Arista(vertice3,vertice4,10);	
	}
	
	@Test
	public void equalsTestTrue() 
	{
		assertTrue(arista1.equals(arista2));
	}

	@Test
	public void equalsTestFalse() 
	{
		assertFalse(arista1.equals(arista3));
	}
}













