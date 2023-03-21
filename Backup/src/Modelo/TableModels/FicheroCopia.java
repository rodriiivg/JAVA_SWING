/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Modelo.TableModels;

import java.io.File;
import java.io.Serializable;
import java.util.Objects;

/**
 *
 * @author rodrigo valdes
 */
public class FicheroCopia extends File implements Serializable {

    // ATRIBUTOS
    private String rutaOrigen;

    // MÉTODOS
    // Getters + Setters
    public String getRutaOrigen() {
        return rutaOrigen;
    }

    public void setRutaOrigen(String rutaOrigen) {
        this.rutaOrigen = rutaOrigen;
    }

    // Constructor
    public FicheroCopia(String rutaDestino, String rutaOrigen) {
        super(rutaDestino);
        this.rutaOrigen = rutaOrigen;
    }

    public FicheroCopia(String ruta) {
        super(ruta);
    }

    // Equals y HashCode para saber si un fichero es duplicado sin necesidad del FileFilter
    @Override
    public int hashCode() {
        int hash = 7;
        hash = 37 * hash + Objects.hashCode(this.rutaOrigen);
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        final FicheroCopia other = (FicheroCopia) obj;
  
        if (!this.isDirectory()&&this.getName().equalsIgnoreCase(other.getName()) && this.length() == other.length()) {
            return true;
        }
        return false;
    }

    
}
