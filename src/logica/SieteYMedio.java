package logica;

public class SieteYMedio {
    private Mazo mazo;
    private Mano manoJugador;
    private Mano manoBanca;
    private boolean turnoJugadorTerminado;
    private boolean rondaTerminada;

    public SieteYMedio() {
        mazo = new Mazo();
        manoJugador = new Mano();
        manoBanca = new Mano();
    }

    /** Comienza una nueva ronda: vacía manos, rearma y mezcla el mazo. */
    public void iniciarNuevaRonda() {
        manoJugador.vaciarMano();
        manoBanca.vaciarMano();
        mazo.reiniciar();
        turnoJugadorTerminado = false;
        rondaTerminada = false;
    }

    /** Acción Pedir carta del jugador. Devuelve la carta que se robó. */
    public Carta jugadorPideCarta() {
        if (turnoJugadorTerminado || rondaTerminada) {
            return null;
        }
        Carta nuevaCarta = mazo.robarCarta();
        manoJugador.agregarCarta(nuevaCarta);

        if (manoJugador.estaPasado()) {
            turnoJugadorTerminado = true;
            rondaTerminada = true;
        }
        return nuevaCarta;
    }

    /** Acción Plantarse: el jugador cierra su turno y juega la banca. */
    public void jugadorSePlanta() {
        if (rondaTerminada) {
            return;
        }
        turnoJugadorTerminado = true;
        jugarTurnoBanca();
        rondaTerminada = true;
    }

    /** Lógica de la banca: pide cartas hasta alcanzar o superar al jugador sin pasarse. */
    private void jugarTurnoBanca() {
        double puntajeJugador = manoJugador.calcularPuntaje();

        while (manoBanca.calcularPuntaje() < puntajeJugador &&
               manoBanca.calcularPuntaje() <= 7.5) {
            Carta cartaNueva = mazo.robarCarta();
            manoBanca.agregarCarta(cartaNueva);
        }
    }

    public double obtenerPuntajeJugador() {
        return manoJugador.calcularPuntaje();
    }

    public double obtenerPuntajeBanca() {
        return manoBanca.calcularPuntaje();
    }

    public String obtenerResultadoTexto() {
        double puntajeJugador = obtenerPuntajeJugador();
        double puntajeBanca = obtenerPuntajeBanca();

        boolean jugadorPasado = puntajeJugador > 7.5;
        boolean bancaPasada = puntajeBanca > 7.5;

        if (jugadorPasado && bancaPasada) {
            return "Ambos se pasaron. Gana la banca.";
        } else if (jugadorPasado) {
            return "Te pasaste. Gana la banca.";
        } else if (bancaPasada) {
            return "La banca se pasó. ¡Ganaste!";
        } else if (puntajeJugador > puntajeBanca) {
            return "¡Ganaste! Estás más cerca de 7,5.";
        } else if (puntajeJugador < puntajeBanca) {
            return "Perdiste. La banca está más cerca de 7,5.";
        } else {
            return "Empate.";
        }
    }

    public Mano getManoJugador() {
        return manoJugador;
    }

    public Mano getManoBanca() {
        return manoBanca;
    }
}
