/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package intefazYBackend;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Stack;
import javax.swing.JFileChooser;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextArea;
import javax.swing.filechooser.FileNameExtensionFilter;
import javax.swing.text.BadLocationException;

/**
 *
 * @author Luis Monterroso
 */
public class BackendMenuPrincipal {

    private Stack<String> undo;
    private Stack<String> redo;
    private boolean banderaCambios;
    private String archivoAbierto;

    /**
     * Contrustor de la clase BackendMenuPrincipal
     */
    public BackendMenuPrincipal() {
        this.undo = new Stack<>();
        this.redo = new Stack<>();
        this.archivoAbierto = "";
    }

    /**
     * Este metodo verifica que la pila no este vacia para desapilar la cima e
     * igualar el texto de un JTextArea a la cima de la misma
     *
     * @param txtContenedor
     */
    public void undo(JTextArea txtContenedor) {
        if (!undo.empty()) {
            redo.push(undo.peek());//apilamos la cima del undo
            undo.pop();//desapilamos la cima del undo
            if (!undo.empty()) {
                txtContenedor.setText(undo.peek());//escribimos la nueva cima de la pila undo
                banderaCambios = true;//hay cambios entonces lo notificamos
            } else {
                txtContenedor.setText("");
                banderaCambios = true;//hay cambios entonces lo notificamos
            }
        }
    }

    /**
     * Este metodo verifica que la pila no este vacia para desapilar la cima e
     * igualar el texto de un JTextArea a la cima de la misma
     *
     * @param txtContenedor
     */
    public void redo(JTextArea txtContenedor) {
        if (!redo.empty()) {
            undo.push(redo.peek());//apilamos la cima del redp
            redo.pop();//desapilamos la cima del redo
            if (!redo.empty()) {
                txtContenedor.setText(redo.peek());//escribimos la nueva cima de la pila redo 
                banderaCambios = true;//hay cambios entonces lo notificamos
            }
        }
    }

    /**
     * Este metodo dever ainvocarse cada que se detecte una tecla en el text
     * area y apila el texto del mismo
     *
     * @param evt
     * @param txtContenedor
     */
    public void apilarUndo(java.awt.event.KeyEvent evt, JTextArea txtContenedor) {
        if (!evt.isActionKey()) {
            undo.push(txtContenedor.getText());
            redo.clear();
            banderaCambios = true;
        }
    }

    /**
     * Apila el texto del JTextArea que se mando
     *
     * @param txtContenedor
     */
    public void apilarUndo(JTextArea txtContenedor) {
        undo.push(txtContenedor.getText());
        redo.clear();
        banderaCambios = true;
    }

    /**
     * Abre un Jfilechooser e iguala el texto del JTextArea al de algun archivo
     * seleccionado
     */
    public void seleccionarArchivo(JTextArea contenedor) {
        if (banderaCambios == false) {
            cargarArchivo(contenedor);
        } else {
            //si hay cambios entonces preguntamos si quiere guardar
            if (JOptionPane.showConfirmDialog(null, "Hay cambios sin guardar ¿Desea guardarlos?", "CUIDADO",
                    JOptionPane.YES_NO_OPTION) == JOptionPane.YES_OPTION) {
                guardarArchivo(contenedor, "txt");
                cargarArchivo(contenedor);
            } else {//si cierra o dice no entonces solo abrimos
                cargarArchivo(contenedor);
            }

        }
    }

    public void guardarArchivo(JTextArea contenedor, String extension) {
        if (archivoAbierto.equals("")) {//si esta abierto algun archivo entonces el nombre no estara vacio
            JFileChooser chooser = new JFileChooser();//creamos el chooser
            FileNameExtensionFilter filter = new FileNameExtensionFilter("Archivos "+extension, extension);//este filtro muestra solo los .txt
            chooser.setFileFilter(filter);//este metodo setea el filtro al chooser
            chooser.setDialogTitle("Guardar archivo");//le damos titulo al dialog
            chooser.setAcceptAllFileFilterUsed(false);//quitamos la opcion de mostrar todos los archivos
            if (chooser.showSaveDialog(null) == JFileChooser.APPROVE_OPTION) {//esperamos a que el chooser de una respuesta valida
                String ruta = chooser.getSelectedFile().toString().concat("."+extension);//seleccionamos la ruta que eligio el usuario le concatenamos el .txt
                File archivoDeTexto = new File(ruta);//creamos un archivo con la ruta y nombre que le dio el usuario
                if (archivoDeTexto.exists()) {//si ya existe lo borramos
                    archivoDeTexto.delete();
                }
                try {
                    FileWriter exportador = new FileWriter(archivoDeTexto);//preparar el archivo que se exportara
                    exportador.write(contenedor.getText());//le escribimos al archivo el texto que se quiere exportar     
                    exportador.close();
                    banderaCambios = false;
                    archivoAbierto = chooser.getSelectedFile().toString();
                } catch (IOException ex) {
                    System.out.println(ex.getMessage());
                }
            }
        } else {
            String ruta = archivoAbierto.concat("."+extension);//seleccionamos la ruta del archivo abierto
            File archivoDeTexto = new File(ruta);//creamos un archivo con la ruta y nombre que le dio el usuario
            if (archivoDeTexto.exists()) {//si ya existe lo borramos
                archivoDeTexto.delete();
            }
            try {
                FileWriter exportador = new FileWriter(archivoDeTexto);//preparar el archivo que se exportara
                exportador.write(contenedor.getText());//le escribimos al archivo el texto que se quiere exportar     
                exportador.close();
                banderaCambios = false;
            } catch (IOException ex) {
                System.out.println(ex.getMessage());
            }
        }
        System.out.println(archivoAbierto);
    }

    private void cargarArchivo(JTextArea contenedor) {
        JFileChooser chooser = new JFileChooser();//creamos el nuevo objeto filechoooser
        FileNameExtensionFilter filter = new FileNameExtensionFilter("Archivos .txt", "txt");
        chooser.setFileFilter(filter);//le damos el filtro al chooser
        chooser.setDialogTitle("Seleccionar archivo");//le damos un titulo al dialog
        chooser.setAcceptAllFileFilterUsed(false);//eliminamos la opcion "todos los archivos" del filechooser
        if (chooser.showOpenDialog(null) == JFileChooser.APPROVE_OPTION) {//esperamos a que se de el una opcion valida
            File archivo = chooser.getSelectedFile();//decimos que el fichero sera igual al archivo elegido
            contenedor.setText("");//borramos el texto del textArea en caso lo haya
            String linea;
            FileReader fr;
            try {
                fr = new FileReader(archivo);
                BufferedReader br = new BufferedReader(fr);
                while ((linea = br.readLine()) != null) {
                    contenedor.append(linea + "\n");
                }
                
                //indicar que hay un archivo abierto
                archivoAbierto = chooser.getSelectedFile().toString().replaceAll(".txt","");//le quitamos la extencion .xxx
                //indicamos que no hay ningun cambio pendiente
                banderaCambios = false;
                //vaciamos las pilas
                redo.clear();
                undo.clear();
            } catch (IOException ex) {
            }
        }
    }

    public void nuevoProyecto(JTextArea contenedor) {
        if (JOptionPane.showConfirmDialog(null, "¿Quieres crear un nuevo proyecto?", "CUIDADO",
                JOptionPane.YES_NO_OPTION) == JOptionPane.YES_OPTION) {
            if (banderaCambios == false) {
                resetear(contenedor);
            } else {
                if (JOptionPane.showConfirmDialog(null, "Hay cambios sin guardar ¿Desea guardarlos?", "CUIDADO",
                        JOptionPane.YES_NO_OPTION) == JOptionPane.YES_OPTION) {
                    guardarArchivo(contenedor, "txt");
                    resetear(contenedor);
                } else {//si cierra o dice no entonces solo abrimos
                    resetear(contenedor);
                }
            }
        }

    }

    private void resetear(JTextArea contenedor) {
        contenedor.setText("");
        archivoAbierto = "";
        banderaCambios = false;
        //vaciamos las pilas
        redo.clear();
        undo.clear();
    }
    /**
     * Este metodo cuenta las filas y las columnas segun se vaya desplazando por el textarea
     * @param labelFila
     * @param labelColumna
     * @param evt 
     */
    public void contarFilasYColumnas(JLabel labelFila, JLabel labelColumna, javax.swing.event.CaretEvent evt) {
        JTextArea textoSeleccionado = (JTextArea) evt.getSource();//obtener una referencia al JTextArea en el que se ha producido el evento
        int linea;//valores iniciales
        int columna;//
        try {
            int posicionDelCursor = textoSeleccionado.getCaretPosition();// da la posición del cursor relativa al número de caracteres insertados
            linea = textoSeleccionado.getLineOfOffset(posicionDelCursor);//devuelve el numero de linea en donde esta el cursor
            columna = posicionDelCursor - textoSeleccionado.getLineStartOffset(linea);//posicion del cursor menos el número de caracteres que hay en las anteriores líneas   
            linea += 1;// Ya que las líneas las cuenta desde la 0
            labelFila.setText(String.valueOf(linea));//cargamos los valores a las labels
            labelColumna.setText(String.valueOf(columna));//
        } catch (BadLocationException ex) {
        }
    }
}
