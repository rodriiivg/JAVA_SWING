/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Modelo.TableModels;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;

/**
 *
 * @author rodrigo valdes
 */
public class BackUp implements Serializable {

    // ATRIBUTOS
    private ConfiguracionBackUp configuracion;
    private List<FicheroCopia> listaFicherosCopiados; // Se asigna al dar a hacer backup
    private String nombre; // Se asigna al dar a hacer backup
    private Date fecha; // Se asigna al dar a hacer backup (se toma del sistema)
    private String nombreDirectorioCreado; // nombreDirectorioOrigen_YYYYMMDD_hhmmss

    // MÉTODOS
    // Getters + Setters
    public String getNombreDirectorioCreado() {
        return nombreDirectorioCreado;
    }

    public void setNombreDirectorioCreado(String nombreDirectorioCreado) {
        this.nombreDirectorioCreado = nombreDirectorioCreado;
    }

    public ConfiguracionBackUp getConfiguracion() {
        return configuracion;
    }

    public void setConfiguracion(ConfiguracionBackUp configuracion) {
        this.configuracion = configuracion;
    }

    public List<FicheroCopia> getListaFicherosCopiados() {
        return listaFicherosCopiados;
    }

    public void setListaFicherosCopiados(List<FicheroCopia> listaFicherosCopiados) {
        this.listaFicherosCopiados = listaFicherosCopiados;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public Date getFecha() {
        return fecha;
    }

    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }

    // Constructor
    public BackUp(ConfiguracionBackUp configuracion, List<FicheroCopia> listaFicherosCopiados, String nombre) {
        this.configuracion = configuracion;
        this.listaFicherosCopiados = listaFicherosCopiados;
        this.nombre = nombre;
        this.fecha = GregorianCalendar.getInstance().getTime();
    }

    public BackUp() {
        configuracion = new ConfiguracionBackUp();
        listaFicherosCopiados = new ArrayList<>();
    }

    
}
