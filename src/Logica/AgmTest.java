package Logica;


import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;

import org.junit.Before;
import org.junit.Test;

public class AgmTest 
{
	
	private Agm agm;
	private ArrayList<String> letras;
	private Grafo grafo;
	
	@Before
	public void inicializacion()
	{
		letras = creaListaDeLetras();
		grafo = grafoDiapositiva(letras);
		agm= new Agm(grafo);
	}
	
	@Test
	public void ObtenerRegionesTest()
	{
		ArrayList<Set<Vertice>> regiones=new ArrayList<Set<Vertice>>();
		
		Grafo abGeneradorMinimo = agm.eliminaKAristas(agm.arbolGeneradorMinimo(grafo,grafo.getProvincias().get(0), letras),4);
		regiones= agm.obtenerRegiones(abGeneradorMinimo);
		ArrayList<Set<Vertice>> esperado = primGrafoDiapositivaRegiones(letras);
		
		
		assertEqualsRegiones(esperado,regiones);
		
	}


	@Test
	public void elminarKaristasTest()
	{
		Grafo abGeneradorMinimo = agm.eliminaKAristas(agm.arbolGeneradorMinimo(grafo,grafo.getProvincias().get(0), letras),4);
		Grafo esperado = primGrafoDiapositivaMenosKAristas(letras);
		
		assertEqualsArboles(esperado,abGeneradorMinimo);
	}


	@Test
	public void arbolGeneradorMinimoTest() 
	{
		Grafo abGeneradorMinimo = agm.arbolGeneradorMinimo(grafo,grafo.getProvincias().get(0), letras);
		Grafo esperado = primGrafoDiapositiva(letras);
		
		assertEqualsArboles(esperado,abGeneradorMinimo);
	}


	private ArrayList<Set<Vertice>> primGrafoDiapositivaRegiones(ArrayList<String> letras2) 
	{
		ArrayList<Set<Vertice>> esperado=new ArrayList<Set<Vertice>>();
		Set<Vertice> r1= new HashSet<Vertice> ();
		Set<Vertice> r2= new HashSet<Vertice> ();
		Set<Vertice> r3= new HashSet<Vertice> ();
		Set<Vertice> r4= new HashSet<Vertice> ();
		r1.add(grafo.getProvincias().get(0));
		r1.add(grafo.getProvincias().get(1));
		r2.add(grafo.getProvincias().get(2));
		r2.add(grafo.getProvincias().get(8));
		r2.add(grafo.getProvincias().get(5));
		r2.add(grafo.getProvincias().get(6));
		r2.add(grafo.getProvincias().get(7));
		r3.add(grafo.getProvincias().get(3));
		r4.add(grafo.getProvincias().get(4));
		esperado.add(r1);
		esperado.add(r2);
		esperado.add(r3);
		esperado.add(r4);
		return esperado;
	}

	
	private Grafo primGrafoDiapositivaMenosKAristas(ArrayList<String> letras) 
	{
		Grafo esperado = new Grafo(letras);
		esperado.agregarArista(new Arista(esperado.getProvincias().get(0), esperado.getProvincias().get(1), 4));
		esperado.agregarArista(new Arista(esperado.getProvincias().get(2), esperado.getProvincias().get(5), 4));
		esperado.agregarArista(new Arista(esperado.getProvincias().get(2), esperado.getProvincias().get(8), 3));
		esperado.agregarArista(new Arista(esperado.getProvincias().get(5), esperado.getProvincias().get(6), 3));
		esperado.agregarArista(new Arista(esperado.getProvincias().get(6), esperado.getProvincias().get(7), 1));
		
		return esperado;
	}
	
	private ArrayList<String> creaListaDeLetras() {
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
		return letras;
	}
	

	private Grafo grafoDiapositiva(ArrayList<String> vertices) 
	{
		ArrayList<String> letras = vertices;
				
		Grafo ret = new Grafo(letras);
		ret.agregarArista(new Arista(ret.getProvincias().get(0), ret.getProvincias().get(1), 4));
		ret.agregarArista(new Arista(ret.getProvincias().get(0), ret.getProvincias().get(7), 8));
		ret.agregarArista(new Arista(ret.getProvincias().get(7), ret.getProvincias().get(6), 8));
		ret.agregarArista(new Arista(ret.getProvincias().get(1), ret.getProvincias().get(7), 12));
		ret.agregarArista(new Arista(ret.getProvincias().get(2), ret.getProvincias().get(3), 6));
		ret.agregarArista(new Arista(ret.getProvincias().get(2), ret.getProvincias().get(8), 3));
		ret.agregarArista(new Arista(ret.getProvincias().get(2), ret.getProvincias().get(5), 4));
		ret.agregarArista(new Arista(ret.getProvincias().get(3), ret.getProvincias().get(4), 9));
		ret.agregarArista(new Arista(ret.getProvincias().get(3), ret.getProvincias().get(5), 13));		
		ret.agregarArista(new Arista(ret.getProvincias().get(4), ret.getProvincias().get(5), 10));
		ret.agregarArista(new Arista(ret.getProvincias().get(5), ret.getProvincias().get(6), 3));		
		ret.agregarArista(new Arista(ret.getProvincias().get(7), ret.getProvincias().get(6), 1));
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
	
	
	private boolean assertEqualsRegiones(ArrayList<Set<Vertice>> regiones, ArrayList<Set<Vertice>> esperado) 
	{
		return regiones == esperado;
		
	}

	private boolean assertEqualsArboles(Grafo abGeneradorMinimo, Grafo esperado) 
	{
		return abGeneradorMinimo.getProvincias().equals(esperado.getProvincias()) && abGeneradorMinimo.getListaDeAristas().equals(esperado.getListaDeAristas()) ;	
	}


}
