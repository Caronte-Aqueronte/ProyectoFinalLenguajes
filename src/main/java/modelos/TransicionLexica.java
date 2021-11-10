package modelos;

public class TransicionLexica {

    private String estadoInicial;
    private char caracter;
    private String estadoObjetivo;

    /**
     * COnstructor de la clase
     *
     * @param estadoInicial
     * @param caracter
     * @param estadoObjetivo
     */
    public TransicionLexica(String estadoInicial, char caracter, String estadoObjetivo) {
        this.estadoInicial = estadoInicial;
        this.caracter = caracter;
        this.estadoObjetivo = estadoObjetivo;
    }

    //getters y setters
    public String getEstadoInicial() {
        return estadoInicial;
    }

    public void setEstadoInicial(String estadoInicial) {
        this.estadoInicial = estadoInicial;
    }

    public char getCaracter() {
        return caracter;
    }

    public void setCaracter(char caracter) {
        this.caracter = caracter;
    }

    public String getEstadoObjetivo() {
        return estadoObjetivo;
    }

    public void setEstadoObjetivo(String estadoObjetivo) {
        this.estadoObjetivo = estadoObjetivo;
    }

}
