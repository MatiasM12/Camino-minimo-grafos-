package Interfaz;


import javax.swing.JFrame;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JTextField;
import javax.swing.JScrollPane;
import javax.swing.JLabel;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;


import Controlador.Controlador;
import Logica.Arista;
import Logica.Tupla;
import Logica.Vertice;

import java.awt.Color;
import java.awt.EventQueue;

import javax.swing.border.LineBorder;
import javax.swing.SwingConstants;


import java.awt.Font;

public class InterfazGrafo {

	protected JFrame frame;
	private JTextField textField_Origen;
	private JTextField textField_Destino;
	private JTextField textField_Peso;
	private JTable table;
	private String[][] tablaAristas;
	private String[] encabezadoAristas;
	private JTable table_1;
	private String[][] tablaVertices;
	private String[] encabezadoVertices;
	protected static Controlador controlador;
	private JLabel loop;
	private JScrollPane scroll ,scroll2;
	private JTextField textField_Origen_eliminar;
	private JTextField textField_Destino_eliminar;
	private JTextField textField_Peso_Eliminar;
	private JTextField textField_aristasElminar;	
	

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) 
	{
		EventQueue.invokeLater(new Runnable() 
		{
			public void run() {
				try {
					InterfazGrafo window = new InterfazGrafo();
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
	public InterfazGrafo() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		controlador=new Controlador();
		frame = new JFrame();
		frame.setIconImage(new javax.swing.ImageIcon("testigos.png").getImage());
		frame.getContentPane().setBackground(new Color(240, 248, 255));
		frame.setBounds(200, 70, 1152, 677);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);frame.setTitle("Testigos de Java");
		frame.getContentPane().setLayout(null);

//------- ETIQUETAS ---------------
		
		//Etiqueta Vertices
		JLabel lblNewLabel = new JLabel("Vertices:");
		lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 26));
		lblNewLabel.setBounds(379, 85, 105, 33);
		frame.getContentPane().add(lblNewLabel);

		//Etiqueta Aristas
		JLabel lblNewLabel_1 = new JLabel("Agregar o Eiminar \r\n:");
		lblNewLabel_1.setFont(new Font("Tahoma", Font.PLAIN, 23));
		lblNewLabel_1.setBounds(25, 160, 215, 113);
		frame.getContentPane().add(lblNewLabel_1);

		//Etiqueta Origen
		JLabel lblNewLabel_2 = new JLabel("Origen:");
		lblNewLabel_2.setFont(new Font("Tahoma", Font.PLAIN, 20));
		lblNewLabel_2.setBounds(262, 168, 92, 23);
		frame.getContentPane().add(lblNewLabel_2);

		//Etiqueta Destino
		JLabel lblNewLabel_3 = new JLabel("Destino");
		lblNewLabel_3.setFont(new Font("Tahoma", Font.PLAIN, 20));
		lblNewLabel_3.setBounds(480, 170, 97, 19);
		frame.getContentPane().add(lblNewLabel_3);

		//Etiqueta Peso
		JLabel lblNewLabel_4 = new JLabel("Peso:");
		lblNewLabel_4.setFont(new Font("Tahoma", Font.PLAIN, 20));
		lblNewLabel_4.setBounds(694, 169, 59, 20);
		frame.getContentPane().add(lblNewLabel_4);

		//Etiqueta Aristas
		JLabel lblNewLabel_5 = new JLabel("Aristas:");
		lblNewLabel_5.setFont(new Font("Tahoma", Font.BOLD, 20));
		lblNewLabel_5.setBounds(130, 319, 86, 23);
		frame.getContentPane().add(lblNewLabel_5);

		//Etiqueta vertices
		JLabel lblNewLabel_7 = new JLabel("Vertices:");
		lblNewLabel_7.setFont(new Font("Tahoma", Font.BOLD, 20));
		lblNewLabel_7.setBounds(407, 319, 91, 23);
		frame.getContentPane().add(lblNewLabel_7);

		//Etiqueta predeterminado
		JLabel lblNewLabel_8 = new JLabel("Predeterminado:");
		lblNewLabel_8.setFont(new Font("Tahoma", Font.PLAIN, 24));
		lblNewLabel_8.setBounds(531, 18, 191, 55);
		frame.getContentPane().add(lblNewLabel_8);

		//Titulo
		JLabel lblNewLabel_6 = new JLabel("Grafos");
		lblNewLabel_6.setFont(new Font("Futura-Medium", Font.BOLD, 54));
		lblNewLabel_6.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_6.setHorizontalTextPosition(SwingConstants.CENTER);
		lblNewLabel_6.setBounds(10, 0, 215, 89);
		frame.getContentPane().add(lblNewLabel_6);

		//Eliminar destino
		JLabel lblNewLabel_9 = new JLabel("Destino:");
		lblNewLabel_9.setFont(new Font("Tahoma", Font.PLAIN, 20));
		lblNewLabel_9.setBounds(480, 242, 97, 21);
		frame.getContentPane().add(lblNewLabel_9);

		//Eliminar peso
		JLabel lblNewLabel_10 = new JLabel("Peso:");
		lblNewLabel_10.setFont(new Font("Tahoma", Font.PLAIN, 20));
		lblNewLabel_10.setBounds(694, 238, 70, 29);
		frame.getContentPane().add(lblNewLabel_10);

		//Eliminar origen
		JLabel lblOrigen = new JLabel("Origen:");
		lblOrigen.setFont(new Font("Tahoma", Font.PLAIN, 20));
		lblOrigen.setBounds(262, 241, 80, 22);
		frame.getContentPane().add(lblOrigen);

		//Cartel de loops
		loop = new JLabel("Hay un loop  (arista con origen = destino no esta permitido en grafo)");
		loop.setForeground(Color.RED);
		loop.setFont(new Font("Tahoma", Font.BOLD, 20));
		loop.setBounds(272, 198, 706, 38);
		frame.getContentPane().add(loop);
		loop.setVisible(false);


		//regiones que quiere formar
		JLabel lblNewLabel_11 = new JLabel("Regiones que desea formar:");
		lblNewLabel_11.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblNewLabel_11.setBounds(766, 280, 370, 183);
		frame.getContentPane().add(lblNewLabel_11);


		//Aviso de cantidad de aristas a eliminar
		JLabel lblNewLabel_12 = new JLabel("(n\u00FAmero positivo distinto de 0)");
		lblNewLabel_12.setBounds(784, 384, 222, 14);
		frame.getContentPane().add(lblNewLabel_12);

		//origen
		textField_Origen = new JTextField();
		textField_Origen.setBounds(344, 173, 86, 20);
		frame.getContentPane().add(textField_Origen);
		textField_Origen.setColumns(10);

		//destino
		textField_Destino = new JTextField();
		textField_Destino.setBounds(564, 173, 86, 20);
		frame.getContentPane().add(textField_Destino);
		textField_Destino.setColumns(10);

		//peso
		textField_Peso = new JTextField();
		textField_Peso.setBounds(763, 173, 86, 20);
		frame.getContentPane().add(textField_Peso);
		textField_Peso.setColumns(10);

		//origen eliminar
		textField_Origen_eliminar = new JTextField();
		textField_Origen_eliminar.setText("");
		textField_Origen_eliminar.setBounds(344, 246, 86, 19);
		frame.getContentPane().add(textField_Origen_eliminar);
		textField_Origen_eliminar.setColumns(10);

		//destino eliminar
		textField_Destino_eliminar = new JTextField();
		textField_Destino_eliminar.setBounds(564, 246, 86, 19);
		frame.getContentPane().add(textField_Destino_eliminar);
		textField_Destino_eliminar.setColumns(10);

		//peso eliminar
		textField_Peso_Eliminar = new JTextField();
		textField_Peso_Eliminar.setText("");
		textField_Peso_Eliminar.setBounds(774, 246, 80, 19);
		frame.getContentPane().add(textField_Peso_Eliminar);
		textField_Peso_Eliminar.setColumns(10);

		//Cant de aristas a eliminar
		textField_aristasElminar = new JTextField();
		textField_aristasElminar.setBounds(997, 362, 86, 23);
		frame.getContentPane().add(textField_aristasElminar);
		textField_aristasElminar.setColumns(10);
		
		
//------- BOTONES -----------------
		
		//Boton AgregarAristas
		JButton btnNewButton = new JButton("Agregar Arista");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) 
			{
				String origen=textField_Origen.getText();
				String destino=textField_Destino.getText();
				Vertice vOrigen=controlador.buscarVertice(origen);
				Vertice vDestino=controlador.buscarVertice(destino);
				int peso=Integer.parseInt(textField_Peso.getText());

				if(controlador.hayLoops(vOrigen, vDestino, peso)) 
				{
					textField_Origen.setText("Loop");
					textField_Destino.setText("Loop");
					loop.setVisible(true);
					textField_Origen.setText("Loop");
					textField_Destino.setText("Loop");
				}
				else 
				{
					loop.setVisible(false);
					Arista arista =new Arista(vOrigen, vDestino, peso);
					controlador.agregarArista(arista);
					actualizarTablaDeAristas();
				}
			}
		});
		btnNewButton.setBounds(872, 172, 136, 23);
		frame.getContentPane().add(btnNewButton);


		//Boton que hace agm
		JButton btnNewButton_3 = new JButton("Arbol generador ");
		btnNewButton_3.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent ev) 
			{
				controlador.setkAristasAEliminar(Integer.parseInt(textField_aristasElminar.getText()));
				lanzaNuevaInterfaz();
			}
		});
		btnNewButton_3.setBounds(872, 434, 179, 60);
		frame.getContentPane().add(btnNewButton_3);


		//Boton que crea el grafo de alemania
		JButton btnNewButton_4 = new JButton("Alemania");
		btnNewButton_4.setIcon(new javax.swing.ImageIcon("imagen.gif"));
		btnNewButton_4.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) 
			{
				controlador.agregarAlemania();
				controlador.crearGrafo();
				actualizarTablaDeAristas();
				actualizarTablaVertices();
				controlador.setCoordenadasAlemania();
			}
		});
		btnNewButton_4.setBounds(531, 80, 91, 55);
		frame.getContentPane().add(btnNewButton_4);


		//Boton eliminar arista
		JButton btnNewButton_2 = new JButton("Eliminar Arista");
		btnNewButton_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) 
			{
				String origen=textField_Origen_eliminar.getText();
				String destino=textField_Destino_eliminar.getText();
				Vertice vOrigen=controlador.buscarVertice(origen);
				Vertice vDestino=controlador.buscarVertice(destino);
				int peso=Integer.parseInt(textField_Peso_Eliminar.getText());
				Arista arista=new Arista(vOrigen ,vDestino,peso);
				controlador.eliminar(arista);
				actualizarTablaDeAristas();
			}
		});
		btnNewButton_2.setBounds(872, 244, 136, 25);
		frame.getContentPane().add(btnNewButton_2);


		//Boton aristas Alemania
		JButton btnNewButton_5 = new JButton("Aristas Alemania");
		btnNewButton_5.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e)
			{
				controlador.agragarAristasPredeterminadasAlemania();
				actualizarTablaDeAristas();
			}
		});
		btnNewButton_5.setBounds(830, 28, 148, 47);
		frame.getContentPane().add(btnNewButton_5);


		//Boton Aristas Argentina 
		JButton btnNewButton_7 = new JButton("Aristas Argentina");
		btnNewButton_7.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) 
			{
				controlador.agragarAristasPredeterminadasArgentina();
				actualizarTablaDeAristas();
			} 
		});
		btnNewButton_7.setBounds(830, 84, 148, 47);
		frame.getContentPane().add(btnNewButton_7);


		//Botón que crea el grafo de Argentina
		JButton btnNewButton_6 = new JButton("Argentina");
		btnNewButton_6.setIcon(new javax.swing.ImageIcon("imagen2.gif"));
		btnNewButton_6.addActionListener(new ActionListener() 
		{
			public void actionPerformed(ActionEvent e) 
			{
				controlador.agregarArgentina();
				controlador.crearGrafo();
				actualizarTablaDeAristas();
				actualizarTablaVertices();
				controlador.setCoordenadasArgentina();
			}
		});
		btnNewButton_6.setBounds(656, 80, 91, 55);
		frame.getContentPane().add(btnNewButton_6);
		
		

	}
	
//-------- LANZA LA INTERFAZ DE AGM ----------
	
	private void lanzaNuevaInterfaz() 
	{
		try
		{
			InterfazAgm window = new InterfazAgm();
			window.frame.setVisible(true);
			frame.setVisible(false);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

//-------- ACTUALIZAR TABLA DE VERTICES -----------

	private void actualizarTablaVertices() 
	{
		table_1 = new JTable();
		table_1.setBorder(new LineBorder(new Color(1, 2,3)));
		table_1.setBackground(Color.LIGHT_GRAY);
		tablaVertices=controlador.llenarTablaVertices();
		encabezadoVertices=controlador.llenarEncabezadoVertices();
		scroll=new JScrollPane();
		scroll.setBounds(400, 350, 350, 280);
		frame.getContentPane().add(scroll);
		table_1.setVisible(true);	
		frame.getContentPane().add(scroll);
		scroll.setViewportView(table_1);
		table_1.setModel(new DefaultTableModel(tablaVertices,encabezadoVertices));
	}
	
//-------- ACTUALIZAR TABLA DE ARISTAS -------------	
	
	private void actualizarTablaDeAristas() 
	{
		table = new JTable();
		table.setBorder(new LineBorder(new Color(1, 2,3)));
		table.setBackground(Color.LIGHT_GRAY);
		tablaAristas= controlador.llenarTablaDeAristas();
		encabezadoAristas=new String[] {"Origen", "Destino", "Peso"};
		
		
		scroll2=new JScrollPane();
		scroll2.setBounds(50, 350, 300, 280);
		frame.getContentPane().add(scroll2);
		scroll2.setViewportView(table);
		table.setVisible(true);
		table.setModel(new DefaultTableModel(tablaAristas,encabezadoAristas));
	}
	
	public void setCoordenadasAlemania() 
    {
//        controlador.getGrafo().getProvincias().get(0).setCordenada(new Tupla<Double, Double>(48.7619, 8.24083));
//        controlador.getGrafo().getProvincias().get(1).setCordenada(new Tupla<Double, Double>(48.7775, 11.431111));
//        controlador.getGrafo().getProvincias().get(2).setCordenada(new Tupla<Double, Double>(52.52437, 13.41053));
//        controlador.getGrafo().getProvincias().get(3).setCordenada(new Tupla<Double, Double>(52.4089, 12.5631));
//        controlador.getGrafo().getProvincias().get(4).setCordenada(new Tupla<Double, Double>(53.07582, 8.80717));
//        controlador.getGrafo().getProvincias().get(5).setCordenada(new Tupla<Double, Double>(53.55073, 9.99302));
//        controlador.getGrafo().getProvincias().get(6).setCordenada(new Tupla<Double, Double>(50.666111, 8.591111));
//        controlador.getGrafo().getProvincias().get(7).setCordenada(new Tupla<Double, Double>(53.8427, 11.4633));
//        controlador.getGrafo().getProvincias().get(8).setCordenada(new Tupla<Double, Double>(52.756111, 9.393056));
//        controlador.getGrafo().getProvincias().get(9).setCordenada(new Tupla<Double, Double>(51.466667, 7.55));
//        controlador.getGrafo().getProvincias().get(10).setCordenada(new Tupla<Double, Double>(49.913056, 7.449722));
//        controlador.getGrafo().getProvincias().get(11).setCordenada(new Tupla<Double, Double>(49.37715, 6.878378));
//        controlador.getGrafo().getProvincias().get(12).setCordenada(new Tupla<Double, Double>(51.026944, 13.358889));
//        controlador.getGrafo().getProvincias().get(13).setCordenada(new Tupla<Double, Double>(51.971111, 11.47));
//        controlador.getGrafo().getProvincias().get(14).setCordenada(new Tupla<Double, Double>(54.470038, 9.51416));
//        controlador.getGrafo().getProvincias().get(15).setCordenada(new Tupla<Double, Double>(50.88506821695049, 10.811302498687338));
    }
	
	public void setCoordenadasArgentina()
	{
		controlador.getGrafo().getProvincias().get(0).setCordenada(new Tupla<Double, Double>(-34.61315, -58.37723));
        controlador.getGrafo().getProvincias().get(1).setCordenada(new Tupla<Double, Double>(-28.466667, -65.783333));
        controlador.getGrafo().getProvincias().get(2).setCordenada(new Tupla<Double, Double>(-27.451389, -58.986667));
        controlador.getGrafo().getProvincias().get(3).setCordenada(new Tupla<Double, Double>(-43.3, -65.1));
        controlador.getGrafo().getProvincias().get(4).setCordenada(new Tupla<Double, Double>(-31.4135, -64.18105));
        controlador.getGrafo().getProvincias().get(5).setCordenada(new Tupla<Double, Double>(-27.46784, -58.8344));
        controlador.getGrafo().getProvincias().get(6).setCordenada(new Tupla<Double, Double>(-32.0477, -60.281));
        controlador.getGrafo().getProvincias().get(7).setCordenada(new Tupla<Double, Double>(-26.18489, -58.17313));
        controlador.getGrafo().getProvincias().get(8).setCordenada(new Tupla<Double, Double>(-23.75, -65.5));
        controlador.getGrafo().getProvincias().get(9).setCordenada(new Tupla<Double, Double>(-36.616667, -64.283333));
        controlador.getGrafo().getProvincias().get(10).setCordenada(new Tupla<Double, Double>(-29.41105, -66.85067));
        controlador.getGrafo().getProvincias().get(11).setCordenada(new Tupla<Double, Double>(-32.89084, -68.82717));
        controlador.getGrafo().getProvincias().get(12).setCordenada(new Tupla<Double, Double>(-26.92, -54.52));
        controlador.getGrafo().getProvincias().get(13).setCordenada(new Tupla<Double, Double>(-38.95161, -68.0591));
        controlador.getGrafo().getProvincias().get(14).setCordenada(new Tupla<Double, Double>(-41.031547, -62.783461));
        controlador.getGrafo().getProvincias().get(15).setCordenada(new Tupla<Double, Double>(-24.7859, -65.41166));
        controlador.getGrafo().getProvincias().get(16).setCordenada(new Tupla<Double, Double>( -31.5375, -68.53639));
        controlador.getGrafo().getProvincias().get(17).setCordenada(new Tupla<Double, Double>(-33.29501, -66.33563));
        controlador.getGrafo().getProvincias().get(18).setCordenada(new Tupla<Double, Double>(-48.823889, -69.815));
        controlador.getGrafo().getProvincias().get(19).setCordenada(new Tupla<Double, Double>(-31.64881, -60.70868));
        controlador.getGrafo().getProvincias().get(20).setCordenada(new Tupla<Double, Double>(-27.79511, -64.26149));
        controlador.getGrafo().getProvincias().get(21).setCordenada(new Tupla<Double, Double>(-54.8064, -68.305));
        controlador.getGrafo().getProvincias().get(22).setCordenada(new Tupla<Double, Double>(-26.82414, -65.2226));
	} 

}
