package grafica;


import javax.swing.ImageIcon;
import javax.swing.JOptionPane;
import logica.Carta;
import logica.Mazo;

import java.awt.EventQueue;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JButton;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.Font;

public class FrmPrincipal extends JFrame {
	private Mazo cartas;
    private int i;

	private JPanel contentPane;
	private JTextField txtContarCartas;
	private JLabel lblCarta;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					FrmPrincipal frame = new FrmPrincipal();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public FrmPrincipal() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 560, 460);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		lblCarta = new JLabel("");
		lblCarta.setBounds(53, 55, 215, 320);
		contentPane.add(lblCarta);
		
		JButton btnSiguienteCarta = new JButton("Siguiente carta");
		btnSiguienteCarta.setFont(new Font("Segoe UI", Font.PLAIN, 12));
		btnSiguienteCarta.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				if (i != 48) {
		            Carta unaCarta = cartas.devolver(i);
		            lblCarta.setIcon(new ImageIcon(unaCarta.toString()));
		            i++;
		            txtContarCartas.setText(String.valueOf(i));
		        } else {
		            iniciarJuego();
		            JOptionPane.showMessageDialog(null, "No hay mas cartas", 
		                    "Fin", JOptionPane.ERROR_MESSAGE);
		        }
			}
		});
		btnSiguienteCarta.setBounds(306, 69, 185, 23);
		contentPane.add(btnSiguienteCarta);
		
		JLabel lblContarCartas = new JLabel("Cartas sacadas:");
		lblContarCartas.setBounds(306, 125, 99, 14);
		contentPane.add(lblContarCartas);
		
		txtContarCartas = new JTextField();
		txtContarCartas.setHorizontalAlignment(SwingConstants.CENTER);
		txtContarCartas.setEnabled(false);
		txtContarCartas.setText("0");
		txtContarCartas.setBounds(415, 122, 53, 20);
		contentPane.add(txtContarCartas);
		txtContarCartas.setColumns(10);
		
		iniciarJuego();
	}
	
	public void iniciarJuego(){
        cartas = new Mazo();
        i = 0;
        String tapa = "img/otra/tapa.png";
        lblCarta.setIcon(new ImageIcon(tapa));
        txtContarCartas.setText("0");
    }
}