/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Modelo.TableModels;

import java.io.File;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author rodrigo valdes
 */
public class ConfiguracionBackUp implements Serializable {

    // ATRIBUTOS
    private File directorioOrigen; // Lo escoge el usuario
    private File directorioDestino; // Se asigna al elegir en el FileChooser
    private List<String> listaExtensiones; // Extensiones de los tipos de ficheros a copiar, Se asigna al dar a hacer backup

    // MÉTODOS
    // Getters + Setters
    public File getDirectorioOrigen() {
        return directorioOrigen;
    }

    public void setDirectorioOrigen(File directorioOrigen) {
        this.directorioOrigen = directorioOrigen;
    }

    public File getDirectorioDestino() {
        return directorioDestino;
    }

    public void setDirectorioDestino(File directorioDestino) {
        this.directorioDestino = directorioDestino;
    }

    public List<String> getListaExtensiones() {
        return listaExtensiones;
    }

    public void setListaExtensiones(List<String> listaExtensiones) {
        this.listaExtensiones = listaExtensiones;
    }

    // Constructor
    public ConfiguracionBackUp(File directorioOrigen) {
        listaExtensiones = new ArrayList<>();
        this.directorioOrigen = directorioOrigen;
        // El directorio destino será el padre del proyecto, el directorio raíz del pendrive
        this.directorioDestino = new File("ubicacion/../..");
    }

    public ConfiguracionBackUp() {
        listaExtensiones = new ArrayList<>();
        // El directorio destino será el padre del proyecto, el directorio raíz del pendrive
        this.directorioDestino = new File("ubicacion/../..");
    }

    
}
