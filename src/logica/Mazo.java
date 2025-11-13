package logica;

import java.util.ArrayList; 
import java.util.Random;
import java.util.Collections;

public class Mazo {
    //Atributos
    private ArrayList<Carta> baraja; //Colección dinámica de cartas
    private Random aleatorio;
    
    //Método constructor
    public Mazo() {
    	aleatorio = new Random();
        baraja = new ArrayList<>();
        armarMazo();
    }
    
    /** Métodos primitivos **/
    public void insertar(Carta c) {
        baraja.add(c);
    }
    public void eliminar(int i) {
        baraja.remove(i);
    }
    public int cantidad() {
        return baraja.size();
    }
    public boolean sinCartas(){
        return baraja.isEmpty();
    }
    public boolean encuentra(Carta c){
        return baraja.contains(c);
    }
    public Carta devolver(int i) {
        return baraja.get(i);
    }
    /** Fin de métodos primitivos **/
/********************************************************************/    
    /** Métodos específicos **/
    private void armarMazo() {
        String[] palos = {"basto", "copa", "espada", "oro"};
        
        for (int indicePalo = 0; indicePalo < palos.length; indicePalo++) {
        	String paloActual = palos[indicePalo];
        	
        	for (int numero = 1; numero <= 12; numero++) {
        		if (numero == 8 || numero == 9) {
        			continue; //Se saltean las cartas 8 y 9
        		}
        		
        		Carta nuevaCarta = new Carta(numero, paloActual);
        		this.insertar(nuevaCarta);        		
        	}//Fin recorrido por numero
        }//Fin recorrido por palo
    }
    
    /** Mezcla el mazo usando el objeto Random. */
    public void mezclar() {
        Collections.shuffle(baraja, aleatorio);
    }
    
    /** Devuelve la carta del tope y la elimina del mazo. */
    public Carta robarCarta() {
    	Carta cartaRobada = null;
        if (!this.sinCartas()) {
            cartaRobada = this.devolver(0);
            this.eliminar(0);
        }
        return cartaRobada;
    }
    
    /** Vuelve a armar y mezclar el mazo. */
    public void reiniciar() {
    	baraja.clear(); //Eliminamos todas las cartas para luego no tener duplicadas
        armarMazo();
        mezclar();
    }
    
    /** Fin de métodos específicos **/
/********************************************************************/           
    @Override
    public String toString() {
        return "Mazo \n" + baraja;
    }
}