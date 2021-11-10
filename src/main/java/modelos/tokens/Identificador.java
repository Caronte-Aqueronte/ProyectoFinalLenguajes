/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modelos.tokens;

/**
 *
 * @author Luis Monterroso
 */
public class Identificador extends Token{

    public Identificador(String tipoDeToken, String tipoTokenParaSintaxis, String lexema, int fila, int columna) {
        super(tipoDeToken, tipoTokenParaSintaxis, lexema, fila, columna);
    }

}
