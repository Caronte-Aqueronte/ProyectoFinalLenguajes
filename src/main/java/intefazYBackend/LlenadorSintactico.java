package intefazYBackend;

import modelos.TransicionSintactica;
import java.util.ArrayList;

public class LlenadorSintactico {

    private ArrayList<TransicionSintactica> tablaDeTrasicionesSintacticas;

    public LlenadorSintactico(ArrayList<TransicionSintactica> tablaDeTrasicionesSintacticas) {
        this.tablaDeTrasicionesSintacticas = tablaDeTrasicionesSintacticas;
        llenaTransiciones();
    }

    private void llenaTransiciones() {
        tablaDeTrasicionesSintacticas.add(new TransicionSintactica("E", "ESCRIBIR", "E A"));
        tablaDeTrasicionesSintacticas.add(new TransicionSintactica("E", "REPETIR", "E B"));
        tablaDeTrasicionesSintacticas.add(new TransicionSintactica("E", "SI", "E C"));
        tablaDeTrasicionesSintacticas.add(new TransicionSintactica("E", "identificador", "E D"));
        tablaDeTrasicionesSintacticas.add(new TransicionSintactica("E", "$", "epsilon"));
        tablaDeTrasicionesSintacticas.add(new TransicionSintactica("A", "ESCRIBIR", "FIN TOKENESCRIBIR ESCRIBIR"));
        tablaDeTrasicionesSintacticas.add(new TransicionSintactica("B", "REPETIR", "FIN J INICIAR TOKENREPETIR REPETIR"));
        tablaDeTrasicionesSintacticas.add(new TransicionSintactica("C", "SI", "FIN J ENTONCES CONDICION SI"));
        tablaDeTrasicionesSintacticas.add(new TransicionSintactica("D", "identificador", "FIN EXPRESION = identificador"));
        tablaDeTrasicionesSintacticas.add(new TransicionSintactica("TOKENESCRIBIR", "indentificador", "identificador"));
        tablaDeTrasicionesSintacticas.add(new TransicionSintactica("TOKENESCRIBIR", "entero", "entero"));
        tablaDeTrasicionesSintacticas.add(new TransicionSintactica("TOKENESCRIBIR", "literal", "literal"));
        tablaDeTrasicionesSintacticas.add(new TransicionSintactica("TOKENREPETIR", "identificador", "FIN EXPRESION = identificador"));
        tablaDeTrasicionesSintacticas.add(new TransicionSintactica("TOKENREPETIR", "entero", "entero"));
        tablaDeTrasicionesSintacticas.add(new TransicionSintactica("CONDICION", "VERDADERO", "VERDADERO"));
        tablaDeTrasicionesSintacticas.add(new TransicionSintactica("CONDICION", "FALSO", "FALSO"));
        tablaDeTrasicionesSintacticas.add(new TransicionSintactica("EXPRESION", "identificador", "OPERANDO SIMBOLO OPERANDO"));
        tablaDeTrasicionesSintacticas.add(new TransicionSintactica("EXPRESION", "entero", "OPERANDO SIMBOLO OPERANDO"));
        tablaDeTrasicionesSintacticas.add(new TransicionSintactica("EXPRESION", "(", "OPERANDO SIMBOLO OPERANDO"));
        tablaDeTrasicionesSintacticas.add(new TransicionSintactica("J", "ESCRIBIR", "J A"));
        tablaDeTrasicionesSintacticas.add(new TransicionSintactica("J", "FIN", "epsilon"));
        tablaDeTrasicionesSintacticas.add(new TransicionSintactica("OPERANDO", "identificador", "identificador"));
        tablaDeTrasicionesSintacticas.add(new TransicionSintactica("OPERANDO", "entero", "entero"));
        tablaDeTrasicionesSintacticas.add(new TransicionSintactica("OPERANDO", "(", ") EXPRESION ("));
        tablaDeTrasicionesSintacticas.add(new TransicionSintactica("SIMBOLO", "*", "*"));
        tablaDeTrasicionesSintacticas.add(new TransicionSintactica("SIMBOLO", "+", "+"));
        
                

    }
}
