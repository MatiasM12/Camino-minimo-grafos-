package Logica;

public class Arista implements Comparable<Arista> 
{
	private int peso;
	private Vertice origen;
	private Vertice destino;

//-------- CONSTRUCTOR ----------------------
	
	public Arista (Vertice origen, Vertice destino, int peso) 
	{
		this.peso = peso;
		this.setOrigen(origen);
		this.setDestino(destino);
	}

//-------- EQUALS ---------------------------
	
	@Override
	public boolean equals(Object obj) 
	{
		Arista arista = (Arista) obj; 
		boolean ret = true;
		ret = ret && ((this.origen == arista.origen || this.origen == arista.destino ) );
		ret = ret && ((this.destino == arista.destino || this.destino == arista.origen) );
		return ret;
	}

//-------- COMPARE TO -----------------------
	
	@Override
	public int compareTo(Arista arista) {
		if(this.peso<arista.getPeso())
		{
			return -1;
		}
		if(this.peso==arista.getPeso())
		{
			return 0;
		}
		return 1;
	}
	
//-------- GETTERS Y SETTERS ----------------
	
	public int getPeso() 
	{
		return peso;
	}

	public void setPeso(int n)
	{
		peso = n;
	}

	public Vertice getOrigen() 
	{
		return origen;
	}

	public void setOrigen(Vertice origen)
	{
		this.origen = origen;
	}

	public Vertice getDestino() 
	{
		return destino;
	}

	public void setDestino(Vertice destino) 
	{
		this.destino = destino;
	}

//-------- TO STRING -------------------------
	@Override
	public String toString()
	{
		StringBuilder cadena= new StringBuilder();
		cadena.append("       Peso: " + this.peso+ '\n' + "        Origen: " + this.origen+ '\n' + "        Destino: " + this.destino + '\n');
		return cadena.toString();
	}

}