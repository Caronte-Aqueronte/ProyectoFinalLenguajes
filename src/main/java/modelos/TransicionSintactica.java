package modelos;

public class TransicionSintactica {

    private String noTerminalEnLaCima;
    private String terminalEntrante;
    private String sustitucion;

    public TransicionSintactica(String noTerminalEnLaCima, String terminalEntrante, String sustitucion) {
        this.noTerminalEnLaCima = noTerminalEnLaCima;
        this.terminalEntrante = terminalEntrante;
        this.sustitucion = sustitucion;
    }

    public String getNoTerminalEnLaCima() {
        return noTerminalEnLaCima;
    }

    public void setNoTerminalEnLaCima(String noTerminalEnLaCima) {
        this.noTerminalEnLaCima = noTerminalEnLaCima;
    }

    public String getTerminalEntrante() {
        return terminalEntrante;
    }

    public void setTerminalEntrante(String terminalEntrante) {
        this.terminalEntrante = terminalEntrante;
    }

    public String getSustitucion() {
        return sustitucion;
    }

    public void setSustitucion(String sustitucion) {
        this.sustitucion = sustitucion;
    }

}
