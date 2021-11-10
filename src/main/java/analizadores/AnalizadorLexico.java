package analizadores;

import java.util.ArrayList;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import modelos.tokens.*;
import modelos.TransicionLexica;
import modelos.TransicionSintactica;

public class AnalizadorLexico {

    private JTable tablaReporte;
    private AnalizadorSintactico analizadorSintactico;
    private String textoAnalizar;

    private ArrayList<Token> tokensParaReporte;
    private ArrayList<Token> tokensParaAnalisisSintactico;

    private ArrayList<TransicionLexica> tablaDeTransicionesLexicas;
    private ArrayList<String> palabrasReservadas;
    private ArrayList<TransicionSintactica> tablaDeTrasicionesSintacticas;

    public AnalizadorLexico(JTable tablaReporte, String textoAnalizar, ArrayList<TransicionLexica> tablaDeTransicionesLexicas, ArrayList<String> palabrasReservadas, ArrayList<TransicionSintactica> tablaDeTrasicionesSintacticas) {
        this.tablaReporte = tablaReporte;
        this.textoAnalizar = textoAnalizar;
        this.tablaDeTransicionesLexicas = tablaDeTransicionesLexicas;
        this.palabrasReservadas = palabrasReservadas;
        this.tablaDeTrasicionesSintacticas = tablaDeTrasicionesSintacticas;
        tokensParaReporte = new ArrayList<>();
        tokensParaAnalisisSintactico = new ArrayList<>();
    }

    /**
     * Meotodo que separa todo el texto en palabras y manda a analizar una a una
     */
    public void analizar() {
        //estos contadores serviran en caso haya error, se iran sumando
        int fila = 1;
        int columna = 0;
        boolean banderaError = false;
        //este String guardara la palabra que mandaremos a identificar
        String palabraAnalizar = "";
        for (int x = 0; x < textoAnalizar.length(); x++) {//for analiza todo el contenido del texto enviado
            if (textoAnalizar.charAt(x) == '"') {
                while (true) {
                    palabraAnalizar += textoAnalizar.charAt(x);
                    if (textoAnalizar.charAt(x + 1) == '"') {
                        palabraAnalizar += textoAnalizar.charAt(x + 1);
                        x += 2;
                        columna += 2;
                        break;
                    }
                    x++;
                    columna++;
                }
                if (textoAnalizar.charAt(x) == '\n') {//si llegamos a detectar un enter entonces sumamos las filas
                    fila++;
                    columna = 0;
                }
                Token tokenAnalizado;
                if ((tokenAnalizado = analizarPalabra(palabraAnalizar, fila, columna)) != null) {//si devolvio null entonces es un error
                    if (tokenAnalizado instanceof Comentario) {//vemamos que no se trata d eun comentario
                        ///si se trata de un comentrario lo agregamos al reporte pero no al de analisis
                        tokensParaReporte.add(tokenAnalizado);
                    } else {
                        tokensParaAnalisisSintactico.add(tokenAnalizado);
                        tokensParaReporte.add(tokenAnalizado);
                    }
                } else {
                    banderaError = true;
                    break;
                }
                palabraAnalizar = "";
            } else {
                if (x == (textoAnalizar.length() - 1)) {//vamios si se trata del final de la cadena
                    palabraAnalizar = palabraAnalizar + textoAnalizar.charAt(x);//obtenemos la letra final
                    columna++;
                }
                if (textoAnalizar.charAt(x) == '\n' || textoAnalizar.charAt(x) == ' ' || x == (textoAnalizar.length() - 1)) {//si se detecta un espacio entonces hasta aqui hay una palabra formada
                    if (!palabraAnalizar.equals("")) {//analizaremos la paabla siempre y cuedo no este vacia
                        Token tokenAnalizado;
                        if ((tokenAnalizado = analizarPalabra(palabraAnalizar, fila, columna)) != null) {//si devolvio null entonces es un error
                            if (tokenAnalizado instanceof Comentario) {//vemamos que no se trata d eun comentario
                                ///si se trata de un comentrario lo agregamos al reporte pero no al de analisis
                                tokensParaReporte.add(tokenAnalizado);
                            } else {
                                tokensParaAnalisisSintactico.add(tokenAnalizado);
                                tokensParaReporte.add(tokenAnalizado);
                            }
                        } else {
                            banderaError = true;
                            break;
                        }
                    }
                    palabraAnalizar = "";//despues del cada analizis reseteamos la palabra
                } else {//si no entonces sumamos el char a la palabra
                    palabraAnalizar = palabraAnalizar + textoAnalizar.charAt(x);
                    columna++;
                }

                if (textoAnalizar.charAt(x) == '\n') {//si llegamos a detectar un enter entonces sumamos las filas
                    fila++;
                    columna = 0;
                }
                if (textoAnalizar.charAt(x) == ' ') {//si llegamos a detectar un espacio entonces sumamos las columnas
                    columna++;
                }
            }
        }
        if (banderaError != true) {//si esto se cumple entonces no hay error
            //primero metemos el caracter de finalizacion para el analisis lexico
            tokensParaAnalisisSintactico.add(new CaracterEspecial("Caracter de aceptacion", "$","$", 0, 0));
            //hacmeos el reporte de los tokens
            hacerReporte();
            //mandamos al analisis sintactico
            analizadorSintactico = new AnalizadorSintactico(tablaDeTrasicionesSintacticas, tokensParaAnalisisSintactico);
            analizadorSintactico.analizar();
        }
    }

    /**
     * Meotodo que explora caracter por caracter la palabra que hay que analizar
     * se mueve dentro de los estados del automata, por ultimo veirifica si el
     * estado en que se termino es de aceptacion
     *
     * @param palabra
     * @param fila
     * @param columna
     * @return
     */
    private Token analizarPalabra(String palabra, int fila, int columna) {
        if (palabrasReservadas.contains(palabra)) {//sise cumple entonces sertrata de una palabvra reservada
            Token palabraReservada = new PalabraReservada("Palabra reservada", palabra.trim(), palabra, fila, columna);
            return palabraReservada;
        } else {//de lo contrario anamlizamos de que token se trata
            String estado = "s1";//comenzamos con el estado inicial s1
            for (int x = 0; x < palabra.length(); x++) {//for que exprolara caracter por caracter la palabra
                for (TransicionLexica item : tablaDeTransicionesLexicas) {//vemos la tabla de trancisiones
                    if (item.getEstadoInicial().equals(estado) && item.getCaracter() == palabra.charAt(x)) {//comparamos de que estado se trata y si es el caracter indicado
                        estado = item.getEstadoObjetivo();//si cumple entoncs cambiamos el estado
                        break;//rompemos el foreach
                    }
                }
            }
            //aqui ya se movio de estados entonces comparamos en que estado finalizo
            switch (estado) {//cada estado corresponde ea un tipo de token
                case "s3":
                    Token entero = new Entero("Entero","entero", palabra, fila, columna);
                    return entero;
                case "s4":
                    Token identificador = new Identificador("Identificador","identificador", palabra, fila, columna);
                    return identificador;
                case "s8":
                    Token literal = new Literal("Literal", "literal",palabra, fila, columna);
                    return literal;
                case "s10":
                    Token comentario = new Comentario("Comentario", "comentario",palabra, fila, columna);//no devolvemos nada pues se trata de un comentario
                    return comentario;
                case "s11":
                    Token caracterEspecial = new CaracterEspecial("Caracter especial", palabra, palabra, fila, columna);
                    return caracterEspecial;
                default:
                    JOptionPane.showMessageDialog(null, "Error lexico encontrado, no se hara el analisis sintactico.");
                    return null;
            }
        }
    }

    /**
     * Llena la tabla con los tokens del arreglo tokesReporte
     */
    public void hacerReporte() {
        DefaultTableModel modelo = setearModelo();
        //anadir todas las columnas necesarias para la tabla
        modelo.addColumn("Tipo de token");
        modelo.addColumn("Lexema");
        modelo.addColumn("Fila");
        modelo.addColumn("Columna");
        tablaReporte.setModel(modelo);//una vez acabado el
        //exploramos todas los tokens, y creamos una fila a partir de los atributos del objeto
        for (Token item : tokensParaReporte) {
            Object[] fila = new Object[4];
            fila[0] = item.getTipoDeToken();
            fila[1] = item.getLexema();
            fila[2] = item.getFila();
            fila[3] = item.getColumna();
            modelo.addRow(fila);//anadimos la nueva fila
        }
        tablaReporte.setModel(modelo);//una vez acabado el

    }

    /**
     * Este metodo le da un formato a la tabla que sea aplicado, desavilita su
     * edicion
     *
     * @return
     */
    private DefaultTableModel setearModelo() {
        DefaultTableModel modelo = new DefaultTableModel() {
            @Override
            public boolean isCellEditable(int i, int i1) {
                return false; //esto hace que todas las fias no sean eitables
            }
        };
        return modelo;
    }

}
