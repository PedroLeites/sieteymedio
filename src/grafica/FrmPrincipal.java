package grafica;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import logica.*;

public class FrmPrincipal extends JFrame {

    private SieteYMedio juego;

    private JPanel contentPane;
    private JLabel lblCartaJugador;
    private JLabel lblPuntajeJugador;
    private JLabel lblPuntajeBanca;
    private JPanel pnlCartasBanca;

    private JButton btnPedir;
    private JButton btnPlantarse;
    private JButton btnNuevaRonda;

    public static void main(String[] args) {
        EventQueue.invokeLater(() -> {
            try {
                FrmPrincipal frame = new FrmPrincipal();
                frame.setVisible(true);
            } catch (Exception e) {
                e.printStackTrace();
            }
        });
    }

    public FrmPrincipal() {

        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(100, 100, 750, 500);

        contentPane = new JPanel();
        contentPane.setLayout(null);
        setContentPane(contentPane);

        // Carta del jugador
        lblCartaJugador = new JLabel();
        lblCartaJugador.setBounds(40, 50, 215, 320);
        contentPane.add(lblCartaJugador);

        // Puntaje del jugador
        lblPuntajeJugador = new JLabel("0");
        lblPuntajeJugador.setFont(new Font("Segoe UI", Font.BOLD, 18));
        lblPuntajeJugador.setBounds(40, 380, 100, 30);
        contentPane.add(lblPuntajeJugador);

        // Panel de cartas de banca
        pnlCartasBanca = new JPanel();
        pnlCartasBanca.setLayout(new FlowLayout());
        pnlCartasBanca.setBounds(300, 50, 400, 150);
        pnlCartasBanca.setBorder(BorderFactory.createTitledBorder("Cartas de la banca"));
        contentPane.add(pnlCartasBanca);

        // Puntaje banca
        lblPuntajeBanca = new JLabel("0");
        lblPuntajeBanca.setFont(new Font("Segoe UI", Font.BOLD, 18));
        lblPuntajeBanca.setBounds(300, 210, 100, 30);
        contentPane.add(lblPuntajeBanca);

        // Botón pedir
        btnPedir = new JButton("Pedir carta");
        btnPedir.setBounds(300, 280, 150, 30);
        contentPane.add(btnPedir);

        // Botón plantarse
        btnPlantarse = new JButton("Plantarse");
        btnPlantarse.setBounds(300, 320, 150, 30);
        contentPane.add(btnPlantarse);

        // Botón nueva ronda
        btnNuevaRonda = new JButton("Nueva ronda");
        btnNuevaRonda.setBounds(300, 360, 150, 30);
        contentPane.add(btnNuevaRonda);

        // Eventos
        btnPedir.addActionListener(e -> pedirCarta());
        btnPlantarse.addActionListener(e -> plantarse());
        btnNuevaRonda.addActionListener(e -> iniciarJuego());

        iniciarJuego();
    }

    private void iniciarJuego() {

        juego = new SieteYMedio();
        juego.iniciarNuevaRonda();

        lblCartaJugador.setIcon(new ImageIcon("img/otra/tapa.png"));

        pnlCartasBanca.removeAll();
        pnlCartasBanca.repaint();

        lblPuntajeJugador.setText("0");
        lblPuntajeBanca.setText("0");

        btnPedir.setEnabled(true);
        btnPlantarse.setEnabled(true);
    }

    private void pedirCarta() {

        Carta carta = juego.jugadorPideCarta();

        if (carta == null) return;

        lblCartaJugador.setIcon(new ImageIcon(carta.toString()));
        lblPuntajeJugador.setText(String.valueOf(juego.obtenerPuntajeJugador()));

        if (juego.obtenerPuntajeJugador() > 7.5) {
            finalizarRonda();
        }
    }

    private void plantarse() {

        juego.jugadorSePlanta();

        mostrarCartasBanca();

        lblPuntajeJugador.setText(String.valueOf(juego.obtenerPuntajeJugador()));
        lblPuntajeBanca.setText(String.valueOf(juego.obtenerPuntajeBanca()));

        finalizarRonda();
    }

    private void mostrarCartasBanca() {

        pnlCartasBanca.removeAll();

        Mano mano = juego.getManoBanca();

        for (int i = 0; i < mano.cantidadCartas(); i++) {

            Carta c = mano.devolverCarta(i);

            ImageIcon original = new ImageIcon(c.toString());
            Image mini = original.getImage().getScaledInstance(60, 90, Image.SCALE_SMOOTH);

            JLabel lblMini = new JLabel(new ImageIcon(mini));
            pnlCartasBanca.add(lblMini);
        }

        pnlCartasBanca.revalidate();
        pnlCartasBanca.repaint();
    }

    private void finalizarRonda() {

        JOptionPane.showMessageDialog(this,
                juego.obtenerResultadoTexto(),
                "Resultado",
                JOptionPane.INFORMATION_MESSAGE);

        btnPedir.setEnabled(false);
        btnPlantarse.setEnabled(false);

        mostrarCartasBanca();

        lblPuntajeJugador.setText(String.valueOf(juego.obtenerPuntajeJugador()));
        lblPuntajeBanca.setText(String.valueOf(juego.obtenerPuntajeBanca()));
    }
}
