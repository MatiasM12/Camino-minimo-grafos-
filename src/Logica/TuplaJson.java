package Logica;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;


public class TuplaJson 
{
    private ArrayList<Tupla<Double,Double>> tuplas;

    public TuplaJson()
    {
        setTuplaJson(new ArrayList<Tupla<Double,Double>>());
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

    public static TuplaJson leerArchivo(String archivo)
    {
        Gson gson= new Gson();
        TuplaJson ret= null;

        try 
        {
            BufferedReader br= new BufferedReader(new FileReader(archivo));
            ret= gson.fromJson(br, TuplaJson.class);

        }
        catch(IOException e)
        {
            e.printStackTrace();
        }
        return ret;
    }

    public void agregar(Tupla<Double,Double> a)
    {
        this.tuplas.add(a); 
    }

    public ArrayList<Tupla<Double,Double>> getTuplaJson() {
        return tuplas;
    }

    public void setTuplaJson(ArrayList<Tupla<Double,Double>> tuplas) {
        this.tuplas = tuplas;
    }
}
