/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Modelo.FileFilters;

import java.io.File;
import java.io.FileFilter;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author rodrigo valdes
 */
public class FiltroFileExtension implements FileFilter {

    private List<String> listaExtensiones;

    public FiltroFileExtension(String extension) {
        listaExtensiones = new ArrayList<String>();
        // Si introducimos la extension con "." delante lo eliminamos, para que no provoque errores al comparar cadenas en el accept
        if (extension.charAt(0) == '.') {
            extension = extension.substring(1);
        }
        listaExtensiones.add(extension.toUpperCase());
    }

    public FiltroFileExtension(List<String> listaExtensiones) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public boolean accept(File file) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
}
