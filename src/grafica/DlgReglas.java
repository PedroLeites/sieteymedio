package grafica;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;

public class DlgReglas extends JDialog {

    private final JPanel contentPanel = new JPanel();

    public DlgReglas(JFrame padre, String titulo, boolean modal) {
        super(padre, titulo, modal);
        setSize(550, 470);
        setLocationRelativeTo(padre);
        setLayout(new BorderLayout());
        setResizable(false);

        // Colores oscuros
        Color rojoOscuro = new Color(150, 30, 30);
        Color verdeOscuro = new Color(20, 120, 20);
        Color azulOscuro = new Color(25, 60, 150);

        // Panel principal
        contentPanel.setBackground(Color.WHITE);
        contentPanel.setLayout(new BorderLayout());
        contentPanel.setBorder(new EmptyBorder(15, 15, 15, 15));
        add(contentPanel, BorderLayout.CENTER);

        // Título
        JLabel lblTitulo = new JLabel("Reglas del Siete y Medio", SwingConstants.CENTER);
        lblTitulo.setFont(new Font("Segoe UI", Font.BOLD, 22));
        lblTitulo.setForeground(azulOscuro);
        contentPanel.add(lblTitulo, BorderLayout.NORTH);

        // ===== Texto con colores utilizando HTML =====
        JLabel lblTexto = new JLabel();
        lblTexto.setVerticalAlignment(SwingConstants.TOP);
        lblTexto.setFont(new Font("Segoe UI", Font.PLAIN, 15));

        // TEXTO HTML UTF-8 con colores
        String html =
            "<html>"
          + "<body style='font-family: Segoe UI; font-size: 14px;'>"
          
          + "<ul>"

          + "<li>Se juega con baraja española de <b style='color:" + color(azulOscuro) + "'>40 cartas</b> (sin 8 ni 9).</li><br>"

          + "<li>Objetivo: acercarse a <b style='color:" + color(verdeOscuro) + "'>7,5 puntos</b> sin pasarse.</li><br>"

          + "<li>Valor de las cartas:<br>"
          + " &nbsp;&nbsp;• <span style='color:" + color(verdeOscuro) + "'>1 al 7</span>: valen su número.<br>"
          + " &nbsp;&nbsp;• <span style='color:" + color(rojoOscuro) + "'>10, 11 y 12</span>: valen 0,5.<br><br>"
          + "</li>"

          + "<li>Al comenzar la ronda recibís una carta inicial.</li><br>"

          + "<li>Opciones del jugador:<br>"
          + " &nbsp;&nbsp;✔ <b style='color:" + color(verdeOscuro) + "'>Pedir carta</b>: sumás su valor.<br>"
          + " &nbsp;&nbsp;✔ <b style='color:" + color(azulOscuro) + "'>Plantarse</b>: mantenés tu puntaje.<br><br>"
          + "</li>"

          + "<li>Si superás los <b style='color:" + color(rojoOscuro) + "'>7,5</b>, perdés automáticamente.</li><br>"

          + "<li>La banca pide cartas hasta igualarte o superarte sin pasarse.</li><br>"

          + "<li>Ganador:<br>"
          + " &nbsp;&nbsp;• Gana quien quede más cerca de <b style='color:" + color(verdeOscuro) + "'>7,5</b>.<br>"
          + " &nbsp;&nbsp;• Si ambos se pasan → <b style='color:" + color(rojoOscuro) + "'>gana la banca</b>.<br>"
          + " &nbsp;&nbsp;• Si empatan → <b style='color:" + color(azulOscuro) + "'>empate</b>.<br><br>"
          + "</li>"

          + "<li>Al finalizar, se muestra el resultado y podés iniciar otra ronda.</li>"

          + "</ul>"

          + "</body>"
          + "</html>";

        lblTexto.setText(html);
        contentPanel.add(lblTexto, BorderLayout.CENTER);

        // Botón cerrar
        JPanel panelBoton = new JPanel();
        panelBoton.setBackground(Color.WHITE);

        JButton btnCerrar = new JButton("Cerrar");
        btnCerrar.setBackground(rojoOscuro);
        btnCerrar.setForeground(Color.WHITE);
        btnCerrar.setFont(new Font("Segoe UI", Font.BOLD, 14));
        btnCerrar.setFocusPainted(false);
        btnCerrar.addActionListener(e -> dispose());

        panelBoton.add(btnCerrar);
        add(panelBoton, BorderLayout.SOUTH);
    }

    // Convierte un color a formato HTML (#RRGGBB)
    private String color(Color c) {
        return String.format("#%02x%02x%02x", c.getRed(), c.getGreen(), c.getBlue());
    }
}
