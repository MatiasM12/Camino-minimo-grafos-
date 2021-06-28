package Logica;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;

import org.junit.Before;
import org.junit.Test;

public class BfsTest {
	
	private ArrayList<String> provincias;
	private Grafo grafo;
	
	@Before
	public void inicializacion()
	{
		provincias=new ArrayList<String>() ;
		inicializarLista();
		grafo= new Grafo(provincias);
	}

	@Test (expected=IllegalArgumentException.class)
	public void testNull() 
	{
		BFS.esConexo(null);
	}
	
	@Test
	public void testVacio()
	{
		assertTrue(BFS.esConexo(new Grafo(new ArrayList<String>())));
	}
	
	@Test
	public void testNoConexo()
	{
		Grafo g = inicializarEjemplo();
		
		assertFalse(BFS.esConexo(g));
	}

	private Grafo inicializarEjemplo() {
		grafo.agregarArista(new Arista(grafo.getProvincias().get(0), grafo.getProvincias().get(7), 8));
		grafo.agregarArista(new Arista(grafo.getProvincias().get(7), grafo.getProvincias().get(6), 8));
		grafo.agregarArista(new Arista(grafo.getProvincias().get(2), grafo.getProvincias().get(3), 6));
		grafo.agregarArista(new Arista(grafo.getProvincias().get(2), grafo.getProvincias().get(8), 3));
		grafo.agregarArista(new Arista(grafo.getProvincias().get(2), grafo.getProvincias().get(5), 4));
		grafo.agregarArista(new Arista(grafo.getProvincias().get(3), grafo.getProvincias().get(4), 9));
		grafo.agregarArista(new Arista(grafo.getProvincias().get(3), grafo.getProvincias().get(5), 13));		
		grafo.agregarArista(new Arista(grafo.getProvincias().get(4), grafo.getProvincias().get(5), 10));
		grafo.agregarArista(new Arista(grafo.getProvincias().get(5), grafo.getProvincias().get(6), 3));		
		grafo.agregarArista(new Arista(grafo.getProvincias().get(7), grafo.getProvincias().get(6), 1));
		grafo.agregarArista(new Arista(grafo.getProvincias().get(6), grafo.getProvincias().get(8), 5));		
		grafo.agregarArista(new Arista(grafo.getProvincias().get(7), grafo.getProvincias().get(8), 6));		

		return grafo;
	}

	@Test
	public void testConexo()
	{
		Grafo g = inicializarEjemplo();
		Arista arista= new Arista(grafo.getProvincias().get(0), grafo.getProvincias().get(1), 4);
		Arista arista2=new Arista(grafo.getProvincias().get(1), grafo.getProvincias().get(7), 12);
		
		g.agregarArista(arista);
		g.agregarArista(arista2);
		
		assertTrue(BFS.esConexo(g));
	}
	
	@Test
	public void alcanzablesTest()
	{
		Grafo g = inicializarEjemplo();
		Set<Vertice> alcanzables = BFS.alcanzables(g, grafo.getProvincias().get(0));
		
		Set<Vertice> esperado = setEsperado();
		assertIguales(esperado, alcanzables);
	}
	

	
	private Set<Vertice> setEsperado() {
		Set<Vertice> r1= new HashSet<Vertice> ();
		
		r1.add(grafo.getProvincias().get(0));
		r1.add(grafo.getProvincias().get(1));
		
		return r1;
	}

	private boolean assertIguales(Set<Vertice> esperado, Set<Vertice> alcanzables) 
	{
		return esperado == alcanzables;	
	}

	private void inicializarLista() 
	{
		provincias.add("A");	//0	
		provincias.add("B");	//1
		provincias.add("C");	//2
		provincias.add("D");	//3
		provincias.add("E");	//4
		provincias.add("F");	//5
		provincias.add("G");	//6
		provincias.add("H");	//7
		provincias.add("I");	//8	
	}
	

}
