package logica;

public class Carta {
    //Atributos
    private int numero;
    private String palo;    
    
    //Métodos constructores
    public Carta() {
    
    }

    public Carta(int numero, String palo) {
        this.numero = numero;
        this.palo = palo;
    }
    
    //Getters y Setters
    public int getNumero() {
        return numero;
    }
    public void setNumero(int numero) {
        this.numero = numero;
    }
    public String getPalo() {
        return palo;
    }
    public void setPalo(String palo) {
        this.palo = palo;
    }
    
    //Métodos específicos
    /** Devuelve el valor de la carta según las reglas del siete y medio. */
    public double obtenerValorParaSieteYMedio() {
        if (numero >= 1 && numero <= 7) {
            return numero;          // valen su número
        } else {                    // 10, 11, 12
            return 0.5;             
        }
    }
    
    //Método toString
    @Override
    public String toString() {  
        return "img/" + palo + "/" + numero + ".png"; // Ruta de la imagen
    }
}