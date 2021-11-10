package analizadores;

import java.util.ArrayList;
import java.util.Stack;
import javax.swing.JOptionPane;
import modelos.TransicionSintactica;
import modelos.tokens.*;

public class AnalizadorSintactico {

    private ArrayList<TransicionSintactica> tablaDeTrasicionesSintacticas;
    private ArrayList<Token> tokensParaAnalisisSintactico;
    private Stack<String> pila;

    public AnalizadorSintactico(ArrayList<TransicionSintactica> tablaDeTrasicionesSintacticas, ArrayList<Token> tokensParaAnalisisSintactico) {
        this.tablaDeTrasicionesSintacticas = tablaDeTrasicionesSintacticas;
        this.tokensParaAnalisisSintactico = tokensParaAnalisisSintactico;
        pila = new Stack<>();
        //apilamos los primeros temrinales y no terminales a la pila
        pila.push("$");
        pila.push("E");
    }

    public void analizar() {
        forGlobal:
        for (Token token : tokensParaAnalisisSintactico) {//hacemos el get token recorriendo todos los tokens de array
            whilePerpetuo:
            while (true) {//revisamos las transiciones perpetuamente hasta que se haga un pop o se rompa el forGlobal
                forTransicion:
                for (TransicionSintactica transicion : tablaDeTrasicionesSintacticas) {
                    if (!pila.empty()) {
                        if (pila.peek().equals(transicion.getNoTerminalEnLaCima()) && transicion.getTerminalEntrante().equals(token.getTipoTokenParaSintaxis())) {//verificamos que en el tope de la pila haya un temrinal que este en la cima de la trancision
                            switch (transicion.getTerminalEntrante()) {
                                case "entero":
                                    if (token instanceof Entero) {
                                        String[] sustitucionConTerminalesGenerales = separarCampos(transicion.getSustitucion());
                                        String[] sustitucionFinal = susutituirTerminalPorLexema(sustitucionConTerminalesGenerales, token.getLexema());
                                        apilarSusttitucion(sustitucionFinal);
                                        System.out.println(pila.toString());
                                        break forTransicion;
                                    }
                                    break;
                                case "identificador":
                                    if (token instanceof Identificador) {
                                        String[] sustitucionConTerminalesGenerales = separarCampos(transicion.getSustitucion());
                                        String[] sustitucionFinal = susutituirTerminalPorLexema(sustitucionConTerminalesGenerales, token.getLexema());
                                        apilarSusttitucion(sustitucionFinal);
                                        System.out.println(pila.toString());
                                        break forTransicion;
                                    }
                                    break;
                                case "literal":
                                    if (token instanceof Literal) {
                                        String[] sustitucionConTerminalesGenerales = separarCampos(transicion.getSustitucion());
                                        String[] sustitucionFinal = susutituirTerminalPorLexema(sustitucionConTerminalesGenerales, token.getLexema());
                                        apilarSusttitucion(sustitucionFinal);
                                        System.out.println(pila.toString());
                                        break forTransicion;
                                    }
                                    break;
                                default:
                                    if ((token instanceof PalabraReservada || token instanceof CaracterEspecial) && transicion.getTerminalEntrante().equals(token.getLexema())) {
                                        String[] sustitucion = separarCampos(transicion.getSustitucion());
                                        apilarSusttitucion(sustitucion);
                                        System.out.println(pila.toString());
                                        break forTransicion;
                                    }
                                    break;

                            }
                            if (token.getLexema().equals(pila.peek())) {
                                pila.pop();
                                System.out.println(pila.toString());
                                break whilePerpetuo;
                            } else if (pila.peek().equals("epsilon")) {
                                pila.pop();
                                System.out.println(pila.toString());
                                break;
                            }
                        } else if (token.getLexema().equals(pila.peek())) {
                            pila.pop();
                            System.out.println(pila.toString());
                            break whilePerpetuo;
                        } else if (pila.peek().equals("epsilon")) {
                            pila.pop();
                            System.out.println(pila.toString());
                            break;
                        } else if (transicion.equals(tablaDeTrasicionesSintacticas.get(tablaDeTrasicionesSintacticas.size() - 1))) {//vemos si se trata del ultimo objeto en las transiciones
                            break forGlobal;
                        }
                    }
                }
            }

        }

        if (pila.empty()) {
            JOptionPane.showMessageDialog(null, "El escrito tiene la sintaxis correcta");
        } else {
            JOptionPane.showMessageDialog(null, "Error sintactico la pila no fue vaciada\n" + pila.toString());

        }
    }

    private String[] separarCampos(String campos) {
        return campos.split(" ");
    }

    private void apilarSusttitucion(String[] sustitucion) {
        pila.pop();
        for (int x = 0; x < sustitucion.length; x++) {
            pila.push(sustitucion[x]);
        }
    }

    private String[] susutituirTerminalPorLexema(String[] sustitucion, String lexema) {
        for (int x = 0; x < sustitucion.length; x++) {
            if (x == (sustitucion.length - 1)) {//si estamos enla ultima posicion del arreglo
                switch (sustitucion[x]) {
                    case "entero":
                    case "identificador":
                    case "literal":
                        sustitucion[x] = lexema;
                        break;
                }
            }
        }
        return sustitucion;
    }
}
