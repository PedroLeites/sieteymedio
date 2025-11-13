package logica;

import java.util.ArrayList; 

public class Mazo {
    //Atributos
    private ArrayList<Carta> baraja; //Colección dinámica de cartas
    
    //Método constructor
    public Mazo() {
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
/********************************************************************/           
    @Override
    public String toString() {
        return "Mazo \n" + baraja;
    }
}