package Interfaz;

import java.awt.Color;
import java.awt.Component;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollBar;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.border.LineBorder;
import javax.swing.table.DefaultTableModel;

import org.openstreetmap.gui.jmapviewer.Coordinate;
import org.openstreetmap.gui.jmapviewer.JMapViewer;
import org.openstreetmap.gui.jmapviewer.MapMarkerDot;
import org.openstreetmap.gui.jmapviewer.MapPolygonImpl;
import org.openstreetmap.gui.jmapviewer.interfaces.MapMarker;
import org.openstreetmap.gui.jmapviewer.interfaces.MapPolygon;

import Controlador.Controlador;
import Logica.Arista;
import Logica.NombreProvinciasJson;
import Logica.Tupla;
import Logica.Vertice;

import java.awt.Font;
import java.awt.Image;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.awt.event.ActionEvent;
import javax.swing.ScrollPaneConstants;

public class InterfazAgm {

	protected JFrame frame;
	private JTable table;
	private String[][] tablaAristas;
	private String[] encabezadoAristas;
	private JTable table_1;
	private String[][] tablaVertices;
	private String[] encabezadoVertices;
	private Controlador controlador;
	private JScrollPane scroll ,scroll2;
	private JButton btnNewButton;
	private ArrayList<Coordinate> lasCoordenadas; 
	private JMapViewer mapa;
	private ArrayList<String> alemania;
	private ArrayList<String> argentina;
	private MapPolygon camino;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args)
	{
		EventQueue.invokeLater(new Runnable() 
		{
			public void run() {
				try {
					InterfazAgm window = new InterfazAgm();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public InterfazAgm() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 * @param mapa 
	 * @param _lasCoordenadas 
	 * @param panelMapa 
	 */
	private void initialize() 
	{
		frame = new JFrame();
		frame.setIconImage(new javax.swing.ImageIcon("testigos.png").getImage());
		frame.getContentPane().setBackground(new Color(240, 248, 255));
		frame.getContentPane().setForeground(new Color(255, 255, 255));
		frame.setBounds(200, 70, 1152, 677);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		frame.setTitle("Testigos de Java"); 
		controlador= InterfazGrafo.controlador;

		JLabel lblNewLabel = new JLabel("Arbol Generador Minimo");
		lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 37));
		lblNewLabel.setBounds(196, 11, 521, 54);
		frame.getContentPane().add(lblNewLabel);

		controlador.hacerArbolGeneradorMinimo();
		controlador.eliminarKAristas();
		controlador.getAgm().obtenerRegiones(controlador.getAgm().getAgm());

		agregarAlemania(); 
		agregarArgentina();
		if(controlador.getProvincias().equals(alemania))
		{
			crearAlemania();
		}
		else if(controlador.getProvincias().equals(argentina))
		{
			crearArgentina();
		}


		cargarCoordenadasDeVertices();
		cargarAristas();

		actualizarTablaVertices();
		actualizarTablaDeAristas();


		//Boton volver
		btnNewButton = new JButton("Volver");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) 
			{
				lanzarInterfazAnterior();
			}
		});
		btnNewButton.setFont(new Font("Tahoma", Font.BOLD, 14));
		btnNewButton.setBounds(10, 11, 106, 37);
		frame.getContentPane().add(btnNewButton);


	}

	private void crearArgentina() {
		Coordinate coordinate = new Coordinate(-38.416097, -63.616672);

		mapa = new JMapViewer();
		mapa.setScrollWrapEnabled(true);
		mapa.setBounds(605, 70, 505, 530);
		frame.getContentPane().add(mapa);
		mapa.setDisplayPosition(coordinate, 3);
		mapa.setEnabled(true);

	}

	private void crearAlemania() {
		Coordinate coordinate = new Coordinate(50.9746, 10.3246);

		mapa = new JMapViewer();
		mapa.setScrollWrapEnabled(true);
		mapa.setBounds(605, 70, 505, 530);
		frame.getContentPane().add(mapa);
		mapa.setDisplayPosition(coordinate, 3);
		mapa.setEnabled(true);


	}

	private void cargarCoordenadasDeVertices() 
	{
		for(int i=0;i<controlador.getGrafo().getProvincias().size();i++) 
		{
			Vertice provincia = controlador.getGrafo().getProvincias().get(i);
			Tupla<Double,Double> coordenadas = provincia.getCordenada();

            MapMarker nombre1 = new MapMarkerDot(new Coordinate(coordenadas.getElem1(),coordenadas.getElem2()));
			nombre1.getStyle().setBackColor(Color.blue);
			nombre1.getStyle().setColor(Color.blue);
			mapa.addMapMarker(nombre1);
		}			
	}

	private void cargarAristas() 
	{
		for (int i=0;i<controlador.getAgm().getAgm().getListaDeAristas().size();i++)
		{
			lasCoordenadas= new ArrayList<Coordinate>();
			Arista arista= controlador.getAgm().getAgm().getListaDeAristas().get(i);
            Tupla<Double,Double> coordenadas1=obtenerVertice(arista.getOrigen()).getCordenada();
            Tupla<Double,Double> coordenadas2=obtenerVertice(arista.getDestino()).getCordenada();

            lasCoordenadas.add(new Coordinate(coordenadas1.getElem1(),coordenadas1.getElem2()));
            lasCoordenadas.add(new Coordinate(coordenadas2.getElem1(),coordenadas2.getElem2()));
            lasCoordenadas.add(new Coordinate(coordenadas1.getElem1(),coordenadas1.getElem2()));
			camino= new MapPolygonImpl(lasCoordenadas);
			mapa.addMapPolygon(camino); 
		}
  
	}
 

	private Vertice obtenerVertice(Vertice origen) 
	{
		for (Vertice v:controlador.getGrafo().getProvincias())
		{
			if(v.getNombre().equals(origen.getNombre()))
			{
				return v;
			}
		}
		return null;
	}

	private void lanzarInterfazAnterior() 
	{
		try {
			InterfazGrafo window = new InterfazGrafo();
			window.frame.setVisible(true);
			frame.setVisible(false);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}


	//-------- ACTUALIZAR TABLA DE VERTICES --------

	private void actualizarTablaVertices() 
	{
		table_1 = new JTable();
		table_1.setBorder(new LineBorder(new Color(1, 2,3)));
		table_1.setBackground(Color.LIGHT_GRAY);
		table_1.setBounds(46, 120, 900, 1000);

		tablaVertices=controlador.llenarTablaVerticesAgm();

		encabezadoVertices=controlador.llenarEncabezadoVertices();
		scroll=new JScrollPane();
		scroll.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_ALWAYS);
		scroll.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
		scroll.setAutoscrolls(true);
		scroll.setBounds(10, 107, 582, 252);
		frame.getContentPane().add(scroll);
		scroll.setViewportView(table_1);
		frame.getContentPane().add(scroll);
		table_1.setVisible(true);
		table_1.setModel(new DefaultTableModel(tablaVertices,encabezadoVertices));;

	}

	//-------- ACTUALIZAR TABLA DE ARISTAS --------	

	private void actualizarTablaDeAristas() 
	{
		table = new JTable();
		table.setBorder(new LineBorder(new Color(1, 2,3)));
		table.setBackground(Color.LIGHT_GRAY);

		tablaAristas= controlador.llenarTablaDeAristasDeAgm();

		encabezadoAristas=new String[] {"Origen", "Destino", "Peso"};

		scroll2=new JScrollPane();
		scroll2.setBounds(10, 370, 582, 245);
		frame.getContentPane().add(scroll2);
		scroll2.setViewportView(table);
		table.setVisible(true);
		table.setModel(new DefaultTableModel(tablaAristas,encabezadoAristas));

	}

	public void agregarArgentina() 
	{
		argentina= new ArrayList<String>();
		NombreProvinciasJson json= NombreProvinciasJson.leerArchivo("provinciasArgentina.json");
		
		for (String a:json.getProvincias())
		{
			argentina.add(a);
		}

	}

	public void agregarAlemania() 
	{
		alemania= new ArrayList<String>();
		NombreProvinciasJson json= NombreProvinciasJson.leerArchivo("provinciasAlemania.json");
		
		for (String a:json.getProvincias())
		{
			alemania.add(a);
		}
	}
	
	
}

