package Logica;

import java.util.ArrayList;

public class Grafo 
{
	private ArrayList<Arista> listaDeAristas;
	private ArrayList<Vertice> provincias;

//---------------- CONSTRUCTOR -----------------------------
	
	public Grafo(ArrayList<String> pais) 
	{
		int cantProvincias = pais.size();
		provincias = new ArrayList<Vertice>(cantProvincias);
		listaDeAristas = new ArrayList<Arista>();

		for(int i=0;i<pais.size();i++)
		{
			provincias.add(new Vertice(pais.get(i),i)); 
		}
	}
	
//---------------- AGREGAR ARISTA --------------------------

	public void agregarArista(Arista arista) 
	{
		if(!existeArista(arista))
		{
			int indice1 = arista.getOrigen().getIndice();
			int indice2 = arista.getDestino().getIndice();
			verificarDistintos(arista.getOrigen(),arista.getDestino());
			provincias.get(indice1).getVecinos().add(arista.getDestino());
			provincias.get(indice2).getVecinos().add(arista.getOrigen());
			listaDeAristas.add(arista);	
		}
		
	}

//---------------- DISTINCION DE VERTICES ------------------	

	public void verificarDistintos(Vertice provincia1, Vertice provincia2)
	{
		if( provincia1.getNombre().equals(provincia2.getNombre()))
			throw new IllegalArgumentException("No se permiten loops: (" + provincia1.toString() + ", " + provincia2.toString() + ")");
	}
	
//----------------- ELIMINAR ARISTA ------------------------
	
	public void eliminarArista(Arista arista) 
	{
		if(existeArista(arista))
		{
			int indice1 = arista.getOrigen().getIndice();
			int indice2 = arista.getDestino().getIndice();
			provincias.get(indice1).getVecinos().remove(arista.getDestino());
			provincias.get(indice2).getVecinos().remove(arista.getOrigen());
			listaDeAristas.remove(arista);
		}
	}

//----------------- EXISTE ARISTA --------------------------
	
	public boolean existeArista(Arista arista)
	{
		Arista inversa= new Arista(arista.getDestino(),arista.getOrigen(),arista.getPeso());
		return listaDeAristas.contains(arista) ||  listaDeAristas.contains(inversa);	
	}

//----------------- METODOS EXTRA  -------------------------	
	
	protected int tamanio()
	{
		return provincias.size();
	}
	
//----------------- GETTERS Y SETTERS ----------------------
	
	public ArrayList<Vertice> getProvincias()
	{
		return provincias;
	}

	public void setProvincias(ArrayList<Vertice> provincias) 
	{
		this.provincias = provincias;
	}

	public ArrayList<Arista> getListaDeAristas() 
	{
		return listaDeAristas;
	}

	public void setListaDeAristas(ArrayList<Arista> listaDeAristas) 
	{ 
		this.listaDeAristas = listaDeAristas;
	}

//------------------ TO STRING ------------------------------	
	@Override
	public String toString()
	{
		StringBuilder cadena = new StringBuilder();
		for (Vertice prov:this.provincias)
		{
			cadena.append("Provincia:  ");
			cadena.append(prov.getNombre());
			cadena.append(" :  Vecinos:");
			for (Vertice veci:prov.getVecinos())
			{
				cadena.append(veci.getNombre()+" ");
			}
			cadena.append('\n');
		}
		for (int i=0;i<listaDeAristas.size();i++) //saltea aristas inversas
		{
			cadena.append("Arista:  \n ");
			cadena.append(listaDeAristas.get(i).toString());
			cadena.append('\n');
		}
		return cadena.toString();
	}

}