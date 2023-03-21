/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Modelo;

import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JOptionPane;

/**
 *
 * @author rodrigo valdes
 */
public class MyExceptions {
      public static class DirectorioVacio extends Exception {

        /**
         * Se lanza cuando la ruta introducida corresponde a un directorio vacío
         *
         * @param string @param string mensaje a mostrar al usuario
         */
        public DirectorioVacio(String string) {
            super(string);
        }
        // Mostraría el JOptionPane sin ponerlo en el cath
        public DirectorioVacio(JFrame parent) {
            JOptionPane.showMessageDialog(parent, "El directorio elegido no tiene contenido,\npor favor seleccione otro.", "ERROR", JOptionPane.ERROR_MESSAGE);
        }

        public DirectorioVacio(JDialog parent) {
            JOptionPane.showMessageDialog(parent, "El directorio elegido no tiene contenido,\npor favor seleccione otro.", "ERROR", JOptionPane.ERROR_MESSAGE);
        }

        public DirectorioVacio() {
            
        }

    }

    
    public static class EspacioInsuficiente extends Exception {

        /**
         * Se lanza cuando el espacio libre en el directorio raíz donde se copiará un directorio
         * es menor que el tamaño del directorio que se intenta copiar
         *
         * @param string @param string mensaje a mostrar al usuario
         */
        public EspacioInsuficiente(String string) {
            super(string);
        }

        public EspacioInsuficiente(JFrame parent) {
            JOptionPane.showMessageDialog(parent, "No queda suficiente espacio en disco.", "ERROR", JOptionPane.ERROR_MESSAGE);
        }

        public EspacioInsuficiente(JDialog parent) {
            JOptionPane.showMessageDialog(parent, "No queda suficiente espacio en disco.", "ERROR", JOptionPane.ERROR_MESSAGE);
        }

        public EspacioInsuficiente() {
            throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
        }

    }
    
}
