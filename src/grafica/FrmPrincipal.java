package grafica;

import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import logica.Carta;
import logica.SieteYMedio;

public class FrmPrincipal extends JFrame {

    // Lógica del juego
    private SieteYMedio juego;

    // Componentes gráficos
    private JPanel contentPane;
    private JLabel lblCartaJugador;
    private JLabel lblPuntajeJugador;
    private JLabel lblPuntajeBanca;

    private JButton btnPedirCarta;
    private JButton btnPlantarse;
    private JButton btnNuevaRonda;

    /**
     * Método main: lanza la aplicación.
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
     * Constructor: crea la ventana y los componentes.
     */
    public FrmPrincipal() {
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setTitle("Juego Siete y Medio");
        setBounds(100, 100, 600, 450);

        contentPane = new JPanel();
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        contentPane.setLayout(null);
        setContentPane(contentPane);

        // === Carta del jugador ===
        lblCartaJugador = new JLabel();
        lblCartaJugador.setBounds(40, 60, 215, 320);
        lblCartaJugador.setBorder(BorderFactory.createEtchedBorder());
        contentPane.add(lblCartaJugador);

        // === Puntaje del jugador ===
        JLabel lblTituloPuntajeJugador = new JLabel("Puntaje jugador:");
        lblTituloPuntajeJugador.setBounds(300, 60, 120, 20);
        contentPane.add(lblTituloPuntajeJugador);

        lblPuntajeJugador = new JLabel("0");
        lblPuntajeJugador.setFont(new Font("Segoe UI", Font.BOLD, 18));
        lblPuntajeJugador.setBounds(430, 55, 100, 30);
        contentPane.add(lblPuntajeJugador);

        // === Puntaje de la banca ===
        JLabel lblTituloPuntajeBanca = new JLabel("Puntaje banca:");
        lblTituloPuntajeBanca.setBounds(300, 100, 120, 20);
        contentPane.add(lblTituloPuntajeBanca);

        lblPuntajeBanca = new JLabel("0");
        lblPuntajeBanca.setFont(new Font("Segoe UI", Font.BOLD, 18));
        lblPuntajeBanca.setBounds(430, 95, 100, 30);
        contentPane.add(lblPuntajeBanca);

        // === Botón Pedir carta ===
        btnPedirCarta = new JButton("Pedir carta");
        btnPedirCarta.setFont(new Font("Segoe UI", Font.PLAIN, 12));
        btnPedirCarta.setBounds(300, 170, 150, 30);
        contentPane.add(btnPedirCarta);

        // === Botón Plantarse ===
        btnPlantarse = new JButton("Plantarse");
        btnPlantarse.setFont(new Font("Segoe UI", Font.PLAIN, 12));
        btnPlantarse.setBounds(300, 210, 150, 30);
        contentPane.add(btnPlantarse);

        // === Botón Nueva ronda ===
        btnNuevaRonda = new JButton("Nueva ronda");
        btnNuevaRonda.setFont(new Font("Segoe UI", Font.PLAIN, 12));
        btnNuevaRonda.setBounds(300, 250, 150, 30);
        contentPane.add(btnNuevaRonda);

        // === Eventos de los botones ===

        // Cuando el jugador pide carta
        btnPedirCarta.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                pedirCarta();
            }
        });

        // Cuando el jugador se planta
        btnPlantarse.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                plantarse();
            }
        });

        // Cuando se inicia una nueva ronda
        btnNuevaRonda.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                iniciarJuego();
            }
        });

        // Iniciar la primera ronda al abrir la ventana
        iniciarJuego();
    }

    /**
     * Inicia el juego o una nueva ronda.
     * Muestra la tapa, pone puntajes en 0 y prepara la lógica.
     */
    private void iniciarJuego() {
        if (juego == null) {
            juego = new SieteYMedio();
        }

        // Prepara la lógica (vacía manos, reinicia mazo, etc.)
        juego.iniciarNuevaRonda();

        // Mostrar la tapa como en el prototipo
        String tapa = "img/otra/tapa.png";
        lblCartaJugador.setIcon(new ImageIcon(tapa));

        // Puntajes en 0 al inicio
        lblPuntajeJugador.setText("0");
        lblPuntajeBanca.setText("0");

        // Habilitar botones de juego
        btnPedirCarta.setEnabled(true);
        btnPlantarse.setEnabled(true);
    }

    /**
     * Lógica cuando el jugador presiona "Pedir carta".
     */
    private void pedirCarta() {
        Carta nuevaCarta = juego.jugadorPideCarta();
        if (nuevaCarta == null) {
            return;
        }

        // Mostrar la carta pedida
        lblCartaJugador.setIcon(new ImageIcon(nuevaCarta.toString()));

        // Actualizar puntaje del jugador
        lblPuntajeJugador.setText(String.valueOf(juego.obtenerPuntajeJugador()));

        // Si se pasó, la ronda termina
        if (juego.obtenerPuntajeJugador() > 7.5) {
            finalizarRonda();
        }
    }

    /**
     * Lógica cuando el jugador presiona "Plantarse".
     */
    private void plantarse() {
        juego.jugadorSePlanta();

        // Actualizar puntajes finales
        lblPuntajeJugador.setText(String.valueOf(juego.obtenerPuntajeJugador()));
        lblPuntajeBanca.setText(String.valueOf(juego.obtenerPuntajeBanca()));

        finalizarRonda();
    }

    /**
     * Finaliza la ronda: muestra el resultado y deshabilita botones.
     */
    private void finalizarRonda() {
        JOptionPane.showMessageDialog(
                this,
                juego.obtenerResultadoTexto(),
                "Resultado",
                JOptionPane.INFORMATION_MESSAGE
        );

        btnPedirCarta.setEnabled(false);
        btnPlantarse.setEnabled(false);
    }
}