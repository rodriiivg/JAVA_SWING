/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Modelo;

import java.io.File;
import java.util.StringTokenizer;

/**
 *
 * @author rodrigo valdes
 */
public class OperacionesAuxiliares {
      /**
     *
     * @param fichero
     * @return El nombre del fichero, sin extensión
     */
    public static String getNombreSinExtension(File fichero) {
      
        StringTokenizer stringTokenizer = new StringTokenizer(fichero.getName(), ".");
        return stringTokenizer.nextToken();
    }

    /**
     *
     * @param fichero
     * @return La canonicalPath sin la extensión del tipo de fichero
     */
    public static String getCanonicalPathSinExtension(File fichero) {
        String nombreDirectorioPadre = fichero.getParent();
        StringTokenizer stringTokenizer = new StringTokenizer(fichero.getName(), ".");
        String nombreFicheroSinExtension = stringTokenizer.nextToken();
        return nombreDirectorioPadre + File.separator + nombreFicheroSinExtension;
    }

    /**
     *
     * @param fichero
     * @return La extensión del fichero (sin ".") pasado por parámetro, null si
     * se trata de un directorio y no tiene ninguna extensión
     */
    public static String getExtension(File fichero) {
        String extension = null;
        StringTokenizer stringTokenizer = new StringTokenizer(fichero.getName(), ".");
        stringTokenizer.nextToken();
        if (stringTokenizer.hasMoreTokens()) {
            extension = stringTokenizer.nextToken();
        }
        return extension;
    }

    /**
     *
     * @param file
     * @param apendiceModificacion
     * @return nombre del fichero original con un apéndice añadido que indique
     * la modificación realizada
     */
    public static String nombreFicheroModificado(File file, String apendiceModificacion) {
        return getCanonicalPathSinExtension(file) + apendiceModificacion + "." + getExtension(file);
    }

    public static String extensionSinPunto(String extension) {
        if (extension.charAt(0) == '.') {
            extension = extension.substring(1);
        }
        return extension;
    }
    
}
