package Logica;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;

public class BFS 
{
	private static ArrayList<Vertice> L;
	private static boolean[] marcados;

//-------- ES CONEXO --------
	
	public static boolean esConexo(Grafo g) 
	{
		if (g==null)
			throw new IllegalArgumentException("Se intento consultar con un grafo null!" );
		if (g.getProvincias().size() == 0)
			return true;
		return alcanzables(g, g.getProvincias().get(0)).size() == g.getProvincias().size();
	}

//-------- ALCANZABLES --------
	
	static Set<Vertice> alcanzables(Grafo g, Vertice origen)
	{
		Set<Vertice> ret = new HashSet<Vertice>();
		inicializar(g, origen);
		while (L.size() >0)
		{
			Vertice i =	L.get(0);
			marcados[i.getIndice()] = true;
			ret.add(i);
			agregarVecinosPendientes(g, i);
			L.remove(0);			
		}
		return ret;
	}

//-------- INICIALIZAR --------	
	
	private static void inicializar(Grafo g, Vertice origen) 
	{
		L = new ArrayList<Vertice>();	
		L.add(origen);
		marcados = new boolean[g.getProvincias().size()];
	}

//-------- AGREGAR VECINOS PENDIENTES --------	
	
	private static void agregarVecinosPendientes(Grafo g, Vertice i) 
	{
		for (Vertice vertice : g.getProvincias().get(i.getIndice()).getVecinos())
			if (marcados[vertice.getIndice()] == false && L.contains(vertice) == false)
				L.add(vertice);
	}
	
}