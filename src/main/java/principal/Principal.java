package principal;

import intefazYBackend.LlenadorLexico;
import intefazYBackend.LlenadorSintactico;
import intefazYBackend.Contenedor;
import modelos.TransicionSintactica;
import modelos.TransicionLexica;
import java.util.ArrayList;

public class Principal {

    private ArrayList<TransicionLexica> tablaDeTransicionesLexicas;
    private ArrayList<String> palabrasReservadas;
    private ArrayList<TransicionSintactica> tablaDeTrasicionesSintacticas;
    private LlenadorLexico llenadorLexico;
    private LlenadorSintactico llenadoSintactico;

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        Principal principal = new Principal();
    }

    public Principal() {
        tablaDeTransicionesLexicas = new ArrayList<>();
        palabrasReservadas = new ArrayList<>();
        tablaDeTrasicionesSintacticas = new ArrayList<>();
        llenadorLexico = new LlenadorLexico(tablaDeTransicionesLexicas, palabrasReservadas);
        llenadoSintactico = new LlenadorSintactico(tablaDeTrasicionesSintacticas);
        Contenedor menuPrincipal = new Contenedor(tablaDeTransicionesLexicas, palabrasReservadas, tablaDeTrasicionesSintacticas);
        menuPrincipal.setVisible(true);

    }
}
