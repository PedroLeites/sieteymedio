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
    
    //Método toString
    @Override
    public String toString() {  
        return "img/" + palo + "/" + numero + ".png"; // Ruta de la imagen
    }
}