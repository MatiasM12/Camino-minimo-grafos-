package Logica;

import static org.junit.Assert.*;

import java.util.ArrayList;

import org.junit.Before;
import org.junit.Test;

public class GrafoTest 
{
	
	private ArrayList<String> alemania;
	private Grafo grafo1;

	@Before
	public void crearGrafo() 
	{
		crearPais();
		grafo1 = new Grafo(alemania);
	}

	@Test (expected = IllegalArgumentException.class)
	public void loopTest()
	{
		Arista arista = new Arista(grafo1.getProvincias().get(0), grafo1.getProvincias().get(0), 10);
		
		grafo1.agregarArista(arista);
		
		assertTrue(grafo1.existeArista(arista));
	}
	
	@Test
	public void agregarAristaTest() 
	{
		Arista arista = new Arista(grafo1.getProvincias().get(0), grafo1.getProvincias().get(1), 10);
		
		grafo1.agregarArista(arista);
		
		assertTrue(grafo1.existeArista(arista));
	} 
	
	@Test
	public void eliminarAristaTest() 
	{
		Arista arista = new Arista(grafo1.getProvincias().get(0), grafo1.getProvincias().get(1), 10);
		
		grafo1.agregarArista(arista);
		grafo1.eliminarArista(arista);
		
		assertFalse(grafo1.existeArista(arista));
	}
	
	@Test
	public void noExisteAristaTest() 
	{
		Arista arista = new Arista(grafo1.getProvincias().get(0), grafo1.getProvincias().get(1), 10);
		
		assertFalse(grafo1.existeArista(arista));
	} 
	
	@Test
	public void existeAristaTest() 
	{
		Arista arista = new Arista(grafo1.getProvincias().get(0), grafo1.getProvincias().get(1), 10);
		Arista inversa = new Arista(grafo1.getProvincias().get(1), grafo1.getProvincias().get(0), 10);
		
		grafo1.agregarArista(arista);
		
		assertTrue(grafo1.existeArista(inversa));
	}
	
	public void arbolGeneradorMinimoTest() 
	{
		ArrayList<String> letras = new ArrayList<String>();
		letras.add("A");	//0	
		letras.add("B");	//1
		letras.add("C");	//2
		letras.add("D");	//3
		letras.add("E");	//4
		letras.add("F");	//5
		letras.add("G");	//6
		letras.add("H");	//7
		letras.add("I");	//8
		
		Grafo grafo = grafoDiapositiva(letras);
		Agm agm= new Agm(grafo);

		Grafo abGeneradorMinimo = agm.arbolGeneradorMinimo(grafo,grafo.getProvincias().get(0), letras);

		Grafo esperado = primGrafoDiapositiva(letras);
		
		assertEquals(abGeneradorMinimo, esperado);
	}

	private void crearPais() {
		alemania = new ArrayList<String>();
		alemania.add("Baden");
		alemania.add("Baviera");
		alemania.add("Berlin");
		alemania.add("Brandeburgo");
		alemania.add("Bremen");
		alemania.add("Hamburgo");
		alemania.add("Hesse");
		alemania.add("Mecklemburgo");
		alemania.add("Baja Sajonia");
		alemania.add("Westfalia");
		alemania.add("Palatinado");
		alemania.add("Sarre");
		alemania.add("Sajonia");
		alemania.add("Sajonia-Anhalt");
		alemania.add("Holstein");
		alemania.add("Turingia");
	}
	
	private Grafo grafoDiapositiva(ArrayList<String> vertices) 
	{
		ArrayList<String> letras = vertices;
				
		Grafo ret = new Grafo(letras);
		ret.agregarArista(new Arista(ret.getProvincias().get(0), ret.getProvincias().get(1), 4));
		ret.agregarArista(new Arista(ret.getProvincias().get(0), ret.getProvincias().get(7), 8));
		ret.agregarArista(new Arista(ret.getProvincias().get(1), ret.getProvincias().get(2), 8));
		ret.agregarArista(new Arista(ret.getProvincias().get(1), ret.getProvincias().get(7), 12));
		ret.agregarArista(new Arista(ret.getProvincias().get(2), ret.getProvincias().get(3), 6));
		ret.agregarArista(new Arista(ret.getProvincias().get(2), ret.getProvincias().get(8), 3));
		ret.agregarArista(new Arista(ret.getProvincias().get(2), ret.getProvincias().get(5), 4));
		ret.agregarArista(new Arista(ret.getProvincias().get(3), ret.getProvincias().get(4), 9));
		ret.agregarArista(new Arista(ret.getProvincias().get(3), ret.getProvincias().get(5), 13));		
		ret.agregarArista(new Arista(ret.getProvincias().get(4), ret.getProvincias().get(5), 10));
		ret.agregarArista(new Arista(ret.getProvincias().get(5), ret.getProvincias().get(6), 3));		
		ret.agregarArista(new Arista(ret.getProvincias().get(6), ret.getProvincias().get(7), 1));
		ret.agregarArista(new Arista(ret.getProvincias().get(6), ret.getProvincias().get(8), 5));		
		ret.agregarArista(new Arista(ret.getProvincias().get(7), ret.getProvincias().get(8), 6));		

		return ret;
	}

	private Grafo primGrafoDiapositiva(ArrayList<String> vertices)
	{
		Grafo esperado = new Grafo(vertices);
		esperado.agregarArista(new Arista(esperado.getProvincias().get(0), esperado.getProvincias().get(1), 4));
		esperado.agregarArista(new Arista(esperado.getProvincias().get(1), esperado.getProvincias().get(2), 8));
		esperado.agregarArista(new Arista(esperado.getProvincias().get(2), esperado.getProvincias().get(3), 6));
		esperado.agregarArista(new Arista(esperado.getProvincias().get(2), esperado.getProvincias().get(5), 4));
		esperado.agregarArista(new Arista(esperado.getProvincias().get(2), esperado.getProvincias().get(8), 3));
		esperado.agregarArista(new Arista(esperado.getProvincias().get(3), esperado.getProvincias().get(4), 9));
		esperado.agregarArista(new Arista(esperado.getProvincias().get(5), esperado.getProvincias().get(6), 3));
		esperado.agregarArista(new Arista(esperado.getProvincias().get(6), esperado.getProvincias().get(7), 1));
		
		return esperado;
	}

}



















