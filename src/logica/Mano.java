package logica;

import java.util.ArrayList;

public class Mano {
	 private ArrayList<Carta> cartas;
	 
	 //Constructor por defecto
	 public Mano() {
		 cartas = new ArrayList<>();
	 }
	 
	 /** Métodos primitivos **/
	 public void agregarCarta(Carta nuevaCarta) {
		 cartas.add(nuevaCarta);
	 }
	 
	 public Carta devolverCarta(int posicion) {
		 return cartas.get(posicion);
	 }
	 
	 public int cantidadCartas() {
		 return cartas.size();
	 }

	 public void vaciarMano() {
		 cartas.clear();
	 }
	 /** Fin de métodos primitivos **/
	 /********************************************************************/    
	 /** Métodos específicos **/
	 public double calcularPuntaje() {
		 double total = 0;
		 for (int indice = 0; indice < this.cantidadCartas(); indice++) {
			 Carta cartaActual = this.devolverCarta(indice);
			 total = total + cartaActual.obtenerValorParaSieteYMedio();
		 }
		 return total;
	 }
	 
	 public boolean estaPasado() {
		 boolean perdio = false;
		 if (calcularPuntaje() > 7.5) {
			 perdio = true;
		 }
		 return perdio;
	 }
	 /** Fin de métodos específicos **/
	 /********************************************************************/ 
}
