package Logica;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

public class NombreProvinciasJson 
{
	private ArrayList<String> provincias;
	
	public NombreProvinciasJson()
	{
		setProvincias(new ArrayList<String>());
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
	
	public static NombreProvinciasJson leerArchivo(String archivo)
	{
		Gson gson= new Gson();
		NombreProvinciasJson ret= null;
		
		try 
		{
			BufferedReader br= new BufferedReader(new FileReader(archivo));
			ret= gson.fromJson(br, NombreProvinciasJson.class);
			
		}
		catch(IOException e)
		{
			e.printStackTrace();
		}
		return ret;
	}
	
	public void agregar(String a)
	{
		this.provincias.add(a);
	}

	public ArrayList<String> getProvincias() {
		return provincias;
	}

	public void setProvincias(ArrayList<String> provincias) {
		this.provincias = provincias;
	}
}
