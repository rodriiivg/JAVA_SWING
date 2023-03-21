/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controlador;

import Modelo.TableModels.BackUp;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author rodrigo valdes
 */
public class ControladorBackUp {
    
    // ATRIBUTOS
    private List<BackUp> listaBackUps;

    // MÉTODOS
    // Getters + Setters
    public List<BackUp> getListaBackUps() {
        return listaBackUps;
    }

    public void setListaBackUps(List<BackUp> listaBackUps) {
        this.listaBackUps = listaBackUps;
    }

    // Constructor
    public ControladorBackUp() throws FileNotFoundException, IOException, ClassNotFoundException {
       File backupsGuardados = new File("backups.dat"); // Fichero donde se habrá grabado el map de listaBackUps antes de cerrar la aplicación
        // Si ya existe el fichero, es decir ya hay backups registrados, los cargamos
        if (backupsGuardados.exists()) {
            FileInputStream fis = new FileInputStream(backupsGuardados);
            ObjectInputStream ois = new ObjectInputStream(fis);
            this.listaBackUps = (List<BackUp>) ois.readObject();
            ois.close();
            fis.close();
        } else {
            // Si no existe el fichero, no hay backups registrados aún, inicializamos el map
            FileOutputStream fos = new FileOutputStream(backupsGuardados, false); // Grabamos sobreescribiendo, no añadiendo
            ObjectOutputStream oos = new ObjectOutputStream(fos);
            this.listaBackUps = new ArrayList<BackUp>();
            oos.writeObject(this.listaBackUps); // No haría realmente falta...¿?
            oos.close();
            fos.close();
        }

        refrescarListaBackups();
    }

  
    public boolean actualizarRegistroBackups() throws FileNotFoundException, IOException {
        // Al cerrar el programa, se graba el map de listaBackUps 
        File backupsGuardados = new File("backups.dat");
        FileOutputStream fos = new FileOutputStream(backupsGuardados, false); 
        ObjectOutputStream oos = new ObjectOutputStream(fos);
        oos.writeObject(this.listaBackUps);
        oos.close();
        fos.close();
        return true;
    }

    public void refrescarListaBackups() {
        // Comprobamos que existen todos los backups que se habían registrado
        if (listaBackUps != null && !listaBackUps.isEmpty()) {
            List<BackUp> noExistentes = new ArrayList<>();
            for (BackUp backUp : listaBackUps) {
                File directorioPadreBackup = new File(backUp.getNombreDirectorioCreado());
                if (!directorioPadreBackup.exists()) {
                    noExistentes.add(backUp);
                }
            }
           listaBackUps.removeAll(noExistentes);
        }
    }
}
