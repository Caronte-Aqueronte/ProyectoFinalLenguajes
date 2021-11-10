package modelos.tokens;

public class Token {

    private String tipoDeToken;
    private String tipoTokenParaSintaxis;
    private String lexema;
    private int fila;
    private int columna;

    public Token(String tipoDeToken, String tipoTokenParaSintaxis, String lexema, int fila, int columna) {
        this.tipoDeToken = tipoDeToken;
        this.tipoTokenParaSintaxis = tipoTokenParaSintaxis;
        this.lexema = lexema;
        this.fila = fila;
        this.columna = columna;
    }

    public String getTipoDeToken() {
        return tipoDeToken;
    }

    public void setTipoDeToken(String tipoDeToken) {
        this.tipoDeToken = tipoDeToken;
    }

    public String getTipoTokenParaSintaxis() {
        return tipoTokenParaSintaxis;
    }

    public void setTipoTokenParaSintaxis(String tipoTokenParaSintaxis) {
        this.tipoTokenParaSintaxis = tipoTokenParaSintaxis;
    }

    public String getLexema() {
        return lexema;
    }

    public void setLexema(String lexema) {
        this.lexema = lexema;
    }

    public int getFila() {
        return fila;
    }

    public void setFila(int fila) {
        this.fila = fila;
    }

    public int getColumna() {
        return columna;
    }

    public void setColumna(int columna) {
        this.columna = columna;
    }

    
}
