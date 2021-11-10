package intefazYBackend;

import modelos.TransicionLexica;
import java.util.ArrayList;

public class LlenadorLexico {

    private ArrayList<TransicionLexica> tablaDeTransicionesLexicas;
    private ArrayList<String> palabrasReservadas;

    /**
     * Contructor que ejecuta todos los metodos de la clase
     *
     * @param tablaDeTransicionesLexicas
     * @param palabrasReservadas
     */
    public LlenadorLexico(ArrayList<TransicionLexica> tablaDeTransicionesLexicas, ArrayList<String> palabrasReservadas) {
        this.tablaDeTransicionesLexicas = tablaDeTransicionesLexicas;
        this.palabrasReservadas = palabrasReservadas;
        llenarPalabraReservada();
        llenarParaComentario();
        llenarParaEntero();
        llenarParaIdentificador();
        llenarParaLiteral();
        llenarParaCacterEspecial();
    }

    private void llenarParaEntero() {
        //llenando trnaiciones para entero
        //1 al 9
        for (int x = 49; x <= 57; x++) {
            tablaDeTransicionesLexicas.add(new TransicionLexica("s1", (char) x, "s3"));
            tablaDeTransicionesLexicas.add(new TransicionLexica("s2", (char) x, "s3"));
        }
        //0 al 9
        for (int x = 48; x <= 57; x++) {
            tablaDeTransicionesLexicas.add(new TransicionLexica("s3", (char) x, "s3"));
            //esta es una trancision para identificador
            tablaDeTransicionesLexicas.add(new TransicionLexica("s4", (char) x, "s4"));
        }
        tablaDeTransicionesLexicas.add(new TransicionLexica("s1", '-', "s2"));
    }

    private void llenarParaIdentificador() {
        //llenando para identificador
        tablaDeTransicionesLexicas.add(new TransicionLexica("s1", '_', "s4"));
        //"a" a la "z"
        for (int x = 97; x <= 122; x++) {
            tablaDeTransicionesLexicas.add(new TransicionLexica("s1", (char) x, "s4"));
            tablaDeTransicionesLexicas.add(new TransicionLexica("s4", (char) x, "s4"));
        }
        //"A" a la "Z"
        for (int x = 65; x <= 90; x++) {
            tablaDeTransicionesLexicas.add(new TransicionLexica("s1", (char) x, "s4"));
            tablaDeTransicionesLexicas.add(new TransicionLexica("s4", (char) x, "s4"));
        }
        tablaDeTransicionesLexicas.add(new TransicionLexica("s4", '_', "s4"));
        tablaDeTransicionesLexicas.add(new TransicionLexica("s4", '-', "s4"));
    }

    private void llenarPalabraReservada() {
        palabrasReservadas.add("ESCRIBIR");
        palabrasReservadas.add("FIN");
        palabrasReservadas.add("REPETIR");
        palabrasReservadas.add("INICIAR");
        palabrasReservadas.add("SI");
        palabrasReservadas.add("VERDADERO");
        palabrasReservadas.add("FALSO");
        palabrasReservadas.add("ENTONCES");

    }

    private void llenarParaLiteral() {
        tablaDeTransicionesLexicas.add(new TransicionLexica("s1", '"', "s6"));
        tablaDeTransicionesLexicas.add(new TransicionLexica("s7", '"', "s8"));
        for (int x = 34; x <= 125; x++) {
            tablaDeTransicionesLexicas.add(new TransicionLexica("s6", (char) x, "s7"));
            tablaDeTransicionesLexicas.add(new TransicionLexica("s7", (char) x, "s7"));
            //transiciones de comentario
            tablaDeTransicionesLexicas.add(new TransicionLexica("s10", (char) x, "s10"));
        }
    }

    private void llenarParaComentario() {
        tablaDeTransicionesLexicas.add(new TransicionLexica("s1", '/', "s9"));
        tablaDeTransicionesLexicas.add(new TransicionLexica("s9", '/', "s10"));
    }
    private void llenarParaCacterEspecial(){
        tablaDeTransicionesLexicas.add(new TransicionLexica("s1", '+', "s11"));
        tablaDeTransicionesLexicas.add(new TransicionLexica("s1", '*', "s11"));
        tablaDeTransicionesLexicas.add(new TransicionLexica("s1", '=', "s11"));
        tablaDeTransicionesLexicas.add(new TransicionLexica("s1", '(', "s11"));
        tablaDeTransicionesLexicas.add(new TransicionLexica("s1", ')', "s11"));
    }
}
