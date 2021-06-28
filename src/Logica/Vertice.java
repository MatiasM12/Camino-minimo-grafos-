package Logica;

import java.util.HashSet;

public class Vertice 
{
	private String nombre;
	private int indice;
	private HashSet<Vertice> vecinos;
	private Tupla<Double, Double> cordenada;

//-------- CONSTRUCTOR ---------------------
		

	public Vertice(String n,int indice) 
	{
		this.nombre = n;
		vecinos = new HashSet<Vertice>();
		this.setIndice(indice);		
	}
	
//-------- IGUAL (COMPARADOR) --------------
	
	public boolean igual(Vertice a) 
	{
		return this.nombre == a.nombre;
	}

//-------- GETTERS Y SETTERS ---------------
	
	public HashSet<Vertice> getVecinos() 
	{
		return vecinos;
	}

	public void setVecinos(HashSet<Vertice> vecinos) 
	{
		this.vecinos = vecinos;
	}

	public String getNombre() 
	{
		return nombre;
	}

	public void setNombre(String nombre) 
	{
		this.nombre = nombre;
	}

	public int getIndice() 
	{
		return indice;
	}

	public void setIndice(int indice) 
	{
		this.indice = indice;
	}

//--------- TO STRING -----------------------
	
	@Override
	public String toString()
	{
		return this.nombre ;
	}

	public Tupla<Double, Double> getCordenada() 
	{
		return cordenada;
	}

	public void setCordenada(Tupla<Double, Double> cordenada) 
	{
		this.cordenada = cordenada;
	}

}