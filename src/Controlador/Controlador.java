package Controlador;

import java.util.ArrayList;
import java.util.Set;

import Logica.Agm;
import Logica.Arista;
import Logica.AristaJson;
import Logica.Grafo;
import Logica.NombreProvinciasJson;
import Logica.Tupla;
import Logica.TuplaJson;
import Logica.Vertice;

public class Controlador {
	
	private Grafo grafo;
	private Agm agm;
	private ArrayList<String> provincias; 
	private int kAristasAEliminar;
	
	public Controlador()
	{
		provincias=new ArrayList<String>();
	}
	
	public void crearGrafo()
	{
		grafo=new Grafo(provincias);
	}
		
	public void agregarVertices(String provincia)
	{
		provincias.add(provincia);
	}
	
	public void agregarArista(Arista arista)
	{
		grafo.agregarArista(arista);
	}
	
	
	public String[][] llenarTablaDeAristas()
	{
		String[][] tabla= new String[grafo.getListaDeAristas().size()][3];
		for(int i=0;i<grafo.getListaDeAristas().size();i++)
		{
			Arista actual=grafo.getListaDeAristas().get(i);
			String peso = "";
			
			tabla[i][0]=actual.getOrigen().getNombre();
			tabla[i][1]=actual.getDestino().getNombre();
			peso+=actual.getPeso();
			tabla[i][2]=peso;
			
		}
		return tabla;
		
	}
	
	public String[][] llenarTablaVertices()
	{
		String[][] tabla= new String[grafo.getProvincias().size()][1];
		for(int i=0;i<grafo.getProvincias().size();i++)
		{
			Vertice actual=grafo.getProvincias().get(i);
			tabla[i][0]=actual.getNombre();
		}
		return tabla;
	}

	public String[][] llenarTablaVerticesAgm() 
	{
		String[][] tabla= new String[agm.getAgm().getProvincias().size()][1];
		for(int i=0;i<agm.getRegiones().size();i++)
		{ 
			Set<Vertice> actual=agm.getRegiones().get(i);
			tabla[i][0]= "Region "+(i+1)+" :";
			for(Vertice v:actual)
			{
				tabla[i][0]+=v.getNombre()+" , ";
			}
		}
		return tabla;
	}
	 
	
	public String[][] llenarTablaDeAristasDeAgm()
	{
		String[][] tabla= new String[agm.getAgm().getListaDeAristas().size()][3];
		for(int i=0;i<agm.getAgm().getListaDeAristas().size();i++)
		{
			Arista actual=agm.getAgm().getListaDeAristas().get(i);
			String peso = "";
			
			tabla[i][0]=actual.getOrigen().getNombre();
			tabla[i][1]=actual.getDestino().getNombre();
			peso+=actual.getPeso();
			tabla[i][2]=peso;
			
		}
		return tabla;
		
	}
	
	public String[] llenarEncabezadoVertices()
	{
		String[] encabezado= new String[1];
		encabezado[0]="Provincias:";
		return encabezado;
	}
	

	public void hacerArbolGeneradorMinimo() 
	{
		agm= new Agm(grafo);
		agm.setAgm(agm.arbolGeneradorMinimo(grafo,grafo.getProvincias().get(0), provincias));
		
	} 
	

	public void eliminarKAristas()
	{
		agm.setAgm(agm.eliminaKAristas(agm.getAgm(), this.kAristasAEliminar));
	}

	public boolean hayLoops(Vertice origen,Vertice destino,int peso)
	{
		try {
			grafo.verificarDistintos(origen, destino);
			return false;
		} catch (Exception e) {
			return true;
		}
	}

	public void eliminar(Arista arista) 
	{
		grafo.eliminarArista(arista);
	}

	
	public Vertice buscarVertice(String origen) 
	{
		for (Vertice v:grafo.getProvincias())
		{
			if(v.getNombre().equals(origen))
			{
				return v;
			}
		}
		return null;
	}

	public void agragarAristasPredeterminadasAlemania() 
	{
		AristaJson json= AristaJson.leerArchivo("aristasAlemania.json");
		
		for (Arista a:json.getAristas())
		{
			grafo.agregarArista(a);
		}
		
	}
	
	public void agregarAlemania() 
	{
		NombreProvinciasJson json= NombreProvinciasJson.leerArchivo("provinciasAlemania.json");
		
		for (String a:json.getProvincias())
		{
			provincias.add(a);
		}
		
	}
	

	public void agragarAristasPredeterminadasArgentina() 
	{
		AristaJson json= AristaJson.leerArchivo("aristasArgentina.json");
		
		for (Arista a:json.getAristas())
		{
			grafo.agregarArista(a);
		}
	}

	public void agregarArgentina() 
	{
		NombreProvinciasJson json= NombreProvinciasJson.leerArchivo("provinciasArgentina.json");
		
		for (String a:json.getProvincias())
		{
			provincias.add(a);
		}
		
	}
	
	public void setCoordenadasAlemania()
	{
		TuplaJson json= TuplaJson.leerArchivo("coordenadasAlemania.json");
		
		int n = 0;
		for (Tupla<Double, Double> coor : json.getTuplaJson())
		{
			grafo.getProvincias().get(n).setCordenada(coor);
			n++;
		}
		
		
	}

	public void setCoordenadasArgentina()
	{
		TuplaJson json= TuplaJson.leerArchivo("coordenadasArgentina.json");
		int n = 0;
		for (Tupla<Double, Double> coor : json.getTuplaJson())
		{
			grafo.getProvincias().get(n).setCordenada(coor);
			n++;
		}
		
	}
	
	public Grafo getGrafo() {
		return this.grafo;
	}

	public Agm getAgm() {
		return agm;
	}

	public void setAgm(Agm agm) {
		this.agm = agm;
	}

	public int getkAristasAEliminar() {
		return kAristasAEliminar;
	}

	public void setkAristasAEliminar(int kAristasAEliminar) {
		this.kAristasAEliminar = kAristasAEliminar;
	}

	public ArrayList<String> getProvincias() {
		return provincias;
	}

	public void setProvincias(ArrayList<String> provincias) {
		this.provincias = provincias;
	}
	


	

}

