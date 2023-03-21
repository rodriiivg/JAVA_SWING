/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Modelo;

import java.io.File;
import java.io.IOException;
import java.util.Comparator;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author rodrigo valdes
 */
public class Comparators {
     public static class comparatorFileTamanhoYNombre implements Comparator<File> {

        /**
         * Compara dos ficheros por su tamaño (de mayor a menor), y en caso de
         * ser de igual tamaño los compara por su nombre (en orden alfabético)
         *
         * @param file1
         * @param file2
         * @return +1 si fichero1.length()>fichero2.length(), -1 si fichero1.length()<fichero2.length(),
         * si fichero1.length()=fichero2.length(), entonces
         * +1 si file1.getName>file2.getName, -1 si file1.getName<file2.getName,
         * 0 si file1.getName=file2.getName
         */
        @Override
        public int compare(File file1, File file2) {
            if (file1.length() > file2.length()) {
                return 1;
            } else if (file1.length() < file2.length()) {
                return -1;
            } else {
                return file1.getName().compareToIgnoreCase(file2.getName());
            }
        }

        /**
         * Compara dos ficheros por su tamaño (de menor a menor), y en caso de
         * ser de igual tamaño los compara por su nombre (en orden alfabético
         * inverso)
         *
         * @param file1
         * @param file2
         * @return +1 si fichero1.length()<fichero2.length(),
         * -1 si fichero1.length()>fichero2.length(), si
         * fichero1.length()=fichero2.length(), entonces +1 si file1.getName<file2.getName,
         * -1 si file1.getName>file2.getName, 0 si file1.getName=file2.getName
         */
        @Override
        public Comparator<File> reversed() {
            return Comparator.super.reversed();
        }
    }

    public static class ComparatorFileRuta implements Comparator<File> {

        /**
         * Compara dos ficheros por su ruta (en orden alfabético)
         *
         * @param file1
         * @param file2
         * @return +1 si
         * fichero1.getCanonicalPath()>fichero2.getCanonicalPath(), -1 si
         * fichero1.getCanonicalPath()<fichero2.getCanonicalPath(), 0 si
         * fichero1.getCanonicalPath()=fichero2.getCanonicalPath(),
         */
        @Override
        public int compare(File file1, File file2) {
            int resultado = 0;
            try {
                resultado = (file1.getCanonicalPath().compareToIgnoreCase(file2.getCanonicalPath()));
            } catch (IOException ex) {
                Logger.getLogger(Comparators.class.getName()).log(Level.SEVERE, null, ex);
            }
            return resultado;
        }

        /**
         * Compara dos ficheros por su ruta (en orden alfabético)
         *
         * @param file1
         * @param file2
         * @return +1 si          fichero1.getCanonicalPath()<fichero2.getCanonicalPath(), -1 si
         * fichero1.getCanonicalPath()>fichero2.getCanonicalPath(), 0 si
         * fichero1.getCanonicalPath()=fichero2.getCanonicalPath(),
         */
        @Override
        public Comparator<File> reversed() {
            return Comparator.super.reversed();
        }

    }
    
}
