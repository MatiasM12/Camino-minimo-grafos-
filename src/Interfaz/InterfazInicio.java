package Interfaz;

import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import java.awt.Font;
import java.awt.Color;
import java.awt.SystemColor;

public class InterfazInicio {

	private JFrame frmTestigosDeJava;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					InterfazInicio window = new InterfazInicio();
					window.frmTestigosDeJava.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public InterfazInicio() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frmTestigosDeJava = new JFrame();
		frmTestigosDeJava.setIconImage(new javax.swing.ImageIcon("testigos.png").getImage());
		frmTestigosDeJava.setForeground(SystemColor.activeCaption);
		frmTestigosDeJava.setBackground(SystemColor.activeCaption);
		frmTestigosDeJava.setTitle("Testigos de Java");
		frmTestigosDeJava.getContentPane().setBackground(Color.WHITE);
		frmTestigosDeJava.setBounds(200, 70, 1152, 677);
		frmTestigosDeJava.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frmTestigosDeJava.getContentPane().setLayout(null);
		
		//Boton iniciar
		JButton btnNewButton_4 = new JButton("Iniciar");
		btnNewButton_4.setForeground(new Color(0, 0, 0));
		btnNewButton_4.setBackground(new Color(255, 0, 0));
		btnNewButton_4.setFont(new Font("Sitka Banner", Font.BOLD, 34));
		btnNewButton_4.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) 
			{
				lanzaNuevaInterfaz();
			}
		});
		btnNewButton_4.setBounds(502, 494, 202, 51);
		frmTestigosDeJava.getContentPane().add(btnNewButton_4);
		
		//Etiqueta titulo
		JLabel lblNewLabel = new JLabel();
		lblNewLabel.setIcon(new javax.swing.ImageIcon("titulo.png"));
		lblNewLabel.setBounds(10, 11, 1190, 603);
		frmTestigosDeJava.getContentPane().add(lblNewLabel);
		
	}
	
//-------- LANZA LA SIGUIENTE INTERFAZ --------
	
	private void lanzaNuevaInterfaz() 
	{
		try
		{
			InterfazGrafo window = new InterfazGrafo();
			window.frame.setVisible(true);
			frmTestigosDeJava.setVisible(false);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
}