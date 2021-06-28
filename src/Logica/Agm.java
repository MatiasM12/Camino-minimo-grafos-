package Logica;

import java.util.ArrayList;
import java.util.Set;

public class Agm 
{
	private Grafo agm;
	private ArrayList<Set<Vertice>> regiones;
	private int cantDeComponentesConexas;
	private ArrayList<Vertice> vertices;

//-------- CONSTRUCTOR --------	
	
	public Agm(Grafo g) 
	{
		agm=g;
		regiones= new ArrayList<Set<Vertice>>();
	}
	
//-------- ARBOL GENERADOR MINIMO --------
	
	public Grafo arbolGeneradorMinimo(Grafo g ,Vertice inicio, ArrayList<String> alemania)
	{
		Grafo nuevo= new Grafo(alemania);
		vertices= new ArrayList<Vertice>();
		ArrayList<Arista> aristas= g.getListaDeAristas();
		ordenarAristas(aristas);
		vertices.add(inicio);
		int cont=0;
		while(cont<=g.tamanio()-1)
		{ 
			ArrayList<Arista> listaDeAristasPosibles=new ArrayList<Arista>();

			llenarListaDeAristasPosibles(vertices,aristas, listaDeAristasPosibles);
			
			if(listaDeAristasPosibles.size()!=0) 
			{
				ordenarAristas(listaDeAristasPosibles);
				vertices.add(listaDeAristasPosibles.get(0).getDestino());
				nuevo.agregarArista(listaDeAristasPosibles.get(0));
			}
			cont++;
		} 
		return nuevo;
	}

//-------- LLENAR LISTA DE ARISTAS POSIBLES --------
	
	private void llenarListaDeAristasPosibles(ArrayList<Vertice> vertices,ArrayList<Arista> aristas, ArrayList<Arista> aVerificar)
	{
		for(int j=0;j<aristas.size();j++)
		{
			Arista actual= aristas.get(j);
			Arista inversa=new Arista(actual.getDestino(),actual.getOrigen(),actual.getPeso());
			
			if(contiene(actual.getOrigen()) && !contiene(actual.getDestino()))
			{
				aVerificar.add(actual);
			}
			if(contiene(inversa.getOrigen()) && !contiene(inversa.getDestino()))
			{
				aVerificar.add(inversa);
			}
		}
	}
	
	private boolean contiene(Vertice origen) 
	{ 
		boolean ret= false;
		for(Vertice v: vertices)
		{
			ret=ret|| v.getNombre().equals(origen.getNombre());
		}
		return ret;
	}

//-------- ELIMINAR K ARISTAS --------	
    
	public Grafo eliminaKAristas(Grafo g,int k)
	{
		ArrayList<Arista> aristas=g.getListaDeAristas();
		ordenarAristas(aristas);
		int cont=0;
		while(cont!=k-1) 
		{
			g.eliminarArista(aristas.get(aristas.size()-1));
			cont++;
		}
		return g;	
	}
	
//--------- OBTENER REGIONES --------	
    
	public ArrayList<Set<Vertice>> obtenerRegiones(Grafo g)
	{
		ArrayList<Set<Vertice>> regiones= new ArrayList<Set<Vertice>>();
		for(int i=0;i<g.getProvincias().size();i++)
		{
			Vertice actual =g.getProvincias().get(i);
			if(!estaContenidaEnOtra(actual,regiones))
				regiones.add(BFS.alcanzables(g, actual));
		}
		this.cantDeComponentesConexas=regiones.size();
		this.regiones=regiones;
		return regiones;
	}
    
//-------- ESTA CONTENIDA EN OTRA --------    
	
	private boolean estaContenidaEnOtra(Vertice actual, ArrayList<Set<Vertice>> regiones) 
	{
		boolean ret=false;
		for(Set<Vertice> conjunto:regiones)
		{
			for(Vertice v:conjunto) 
			{
				ret=ret || v.getNombre()==actual.getNombre();
			}
		}
		return ret;
	}
	
//-------- ORDENAR ARISTAS --------	
	
	private void ordenarAristas(ArrayList<Arista> aristas)
	{
		aristas.sort(null);
	}
	
//-------- GETTERS Y SETTERS --------
	
	public int getCantDeComponentesConexas() {
		return cantDeComponentesConexas;
	}

	public void setCantDeComponentesConexas(int cantDeComponentesConexas) {
		this.cantDeComponentesConexas = cantDeComponentesConexas;
	}

	public Grafo getAgm() {
		return agm;
	}

	public void setAgm(Grafo agm) {
		this.agm = agm;
	}

	public ArrayList<Set<Vertice>> getRegiones() {
		return regiones;
	}

	public void setRegiones(ArrayList<Set<Vertice>> regiones) {
		this.regiones = regiones;
	}
	
// -------- TO STRING --------
	
	@Override
	public String toString()
	{
		return this.agm.toString();
	}
}