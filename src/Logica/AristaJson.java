package Logica;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import Logica.Arista;

public class AristaJson
{
	private ArrayList<Arista> aristas;
	
	public AristaJson()
	{
		setAristas(new ArrayList<Arista>());
	}

	public String generarJsonPretty()
	{
		Gson gson= new GsonBuilder().setPrettyPrinting().create();
		String json= gson.toJson(this);
		return json;
	}
	
	public void guardarJson(String jsonParaGuardar,String archivoDestino)
	{
		try
		{
			FileWriter escribe= new FileWriter(archivoDestino);
			escribe.write(jsonParaGuardar);
			escribe.close();
		}
		catch(IOException e)
		{
			e.printStackTrace();
		}
	}
	
	public static AristaJson leerArchivo(String archivo)
	{
		Gson gson= new Gson();
		AristaJson ret= null;
		
		try 
		{
			BufferedReader br= new BufferedReader(new FileReader(archivo));
			ret= gson.fromJson(br, AristaJson.class);
			
		}
		catch(IOException e)
		{
			e.printStackTrace();
		}
		return ret;
	}
	
	public void agregar(Arista a)
	{
		this.aristas.add(a);
	}

	public ArrayList<Arista> getAristas() {
		return aristas;
	}

	public void setAristas(ArrayList<Arista> aristas) {
		this.aristas = aristas;
	}
}


