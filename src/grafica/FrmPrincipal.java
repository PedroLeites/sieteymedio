package grafica;


import javax.swing.ImageIcon;
import javax.swing.JOptionPane;
import logica.Carta;
import logica.Mano;
import logica.SieteYMedio;

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
	private SieteYMedio juego;

	private JPanel contentPane;
	
	private JLabel lblCartaJugador;
	private JLabel lblCartaBanca;
	private JLabel lblPuntajeJugador;
	private JLabel lblPuntajeBanca;

	private JButton btnPedirCarta;
	private JButton btnPlantarse;
	private JButton btnNuevaRonda;

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
		setBounds(100, 100, 700, 500);
        setTitle("Juego - Siete y Medio");
        setLocationRelativeTo(null);
        
		contentPane = new JPanel();
		contentPane.setLayout(null);
		setContentPane(contentPane);
		
		//=== CARTA DEL JUGADOR ===
        lblCartaJugador = new JLabel("");
        lblCartaJugador.setBounds(50, 80, 215, 320);
        contentPane.add(lblCartaJugador);

        lblPuntajeJugador = new JLabel("Puntaje jugador: 0");
        lblPuntajeJugador.setBounds(50, 40, 200, 20);
        contentPane.add(lblPuntajeJugador);
        
        //=== CARTA DE LA BANCA ===
        lblCartaBanca = new JLabel("");
        lblCartaBanca.setBounds(420, 80, 215, 320);
        contentPane.add(lblCartaBanca);

        lblPuntajeBanca = new JLabel("Puntaje banca: 0");
        lblPuntajeBanca.setBounds(420, 40, 200, 20);
        contentPane.add(lblPuntajeBanca);
		
        //=== BOTÓN PEDIR CARTA ===
        btnPedirCarta = new JButton("Pedir carta");
        btnPedirCarta.setFont(new Font("Segoe UI", Font.PLAIN, 14));
        btnPedirCarta.setBounds(280, 80, 130, 30);
        btnPedirCarta.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                pedirCartaJugador();
            }
        });
        contentPane.add(btnPedirCarta);

        //=== BOTÓN PLANTARSE ===
        btnPlantarse = new JButton("Plantarse");
        btnPlantarse.setFont(new Font("Segoe UI", Font.PLAIN, 14));
        btnPlantarse.setBounds(280, 130, 130, 30);
        btnPlantarse.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                plantarseJugador();
            }
        });
        contentPane.add(btnPlantarse);

        //=== BOTÓN NUEVA RONDA ===
        btnNuevaRonda = new JButton("Nueva ronda");
        btnNuevaRonda.setFont(new Font("Segoe UI", Font.PLAIN, 14));
        btnNuevaRonda.setBounds(280, 180, 130, 30);
        btnNuevaRonda.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                iniciarJuego();
            }
        });
        contentPane.add(btnNuevaRonda);
        
		iniciarJuego();
	}
	
	public void iniciarJuego(){
		juego = new SieteYMedio();
	    juego.iniciarNuevaRonda();
	    mostrarEstadoInicial();
    }
	
	private void mostrarEstadoInicial() {
	    String tapa = "img/otra/tapa.png";
	    
	    //Mostrar la tapa hasta que el jugador robe
	    lblCartaJugador.setIcon(new ImageIcon(tapa));

	    //La banca empieza sin cartas visibles
	    lblCartaBanca.setIcon(new ImageIcon(tapa));

	    //Mostrar puntajes
	    lblPuntajeJugador.setText("Puntaje jugador: " + juego.obtenerPuntajeJugador());
	    lblPuntajeBanca.setText("Puntaje banca: " + juego.obtenerPuntajeBanca());
	}
	
	private void pedirCartaJugador() {
        Carta carta = juego.jugadorPideCarta();

        if (carta != null) {
            lblCartaJugador.setIcon(new ImageIcon(carta.toString()));
            lblPuntajeJugador.setText("Puntaje jugador: " + juego.obtenerPuntajeJugador());
        }

        if (juego.obtenerPuntajeJugador() > 7.5) {
            JOptionPane.showMessageDialog(this, juego.obtenerResultadoTexto());
            habilitarBotones(false);
        }
    }

    private void plantarseJugador() {
        juego.jugadorSePlanta();

        // Mostrar última carta de la banca
        Mano banca = juego.getManoBanca();
        int cant = banca.cantidadCartas();
        if (cant > 0) {
            Carta carta = banca.devolverCarta(cant - 1);
            lblCartaBanca.setIcon(new ImageIcon(carta.toString()));
        }

        lblPuntajeJugador.setText("Puntaje jugador: " + juego.obtenerPuntajeJugador());
        lblPuntajeBanca.setText("Puntaje banca: " + juego.obtenerPuntajeBanca());

        JOptionPane.showMessageDialog(this, juego.obtenerResultadoTexto());

        habilitarBotones(false);
    }

    private void habilitarBotones(boolean estado) {
        btnPedirCarta.setEnabled(estado);
        btnPlantarse.setEnabled(estado);
    }

}