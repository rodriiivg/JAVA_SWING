/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Modelo;

import Modelo.FileFilters.FiltroFileDirectorio;
import Modelo.FileFilters.FiltroFileExtension;
import Modelo.FileFilters.FiltroFileNoDirectorio;
import Modelo.TableModels.BackUp;
import Modelo.TableModels.FicheroCopia;
import java.io.File;
import java.io.FileFilter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 *
 * @author rodrigo valdes
 */
public class OperacionesFicheros {
     // ATRIBUTOS
    public static List<FicheroCopia> listaCopiados = new ArrayList<>();
    public static List<File> listaSubficheros = new ArrayList<>();

    // MÉTODOS

    public static List<FicheroCopia> copiarFicheros(File directorioOrigen, File directorioDestino, FileFilter filtro) throws IOException {
        reiniciarListaSubficheros();
        List<FicheroCopia> listaFicherosCopiados = new ArrayList<>();

        // Listamos recursivamente todos los ficheros que pasen el filtro
        List<File> listaFicheros = listarFicherosRecursivoFiltrado(directorioOrigen, filtro);

        // Coopiamos a la ruta de destino los ficheros no directorio, si su directorio padre no existe lo creamos
        for (File ficheroOriginal : listaFicheros) {
            if (!ficheroOriginal.isDirectory()) {
              String rutaAPartirDeDirectorioOrigen = ficheroOriginal.getCanonicalPath().substring(directorioOrigen.getCanonicalPath().length());
                String rutaFicheroCopia = directorioDestino.getCanonicalPath() + File.separator + rutaAPartirDeDirectorioOrigen;
                FicheroCopia ficheroCopia = new FicheroCopia(rutaFicheroCopia, ficheroOriginal.getCanonicalPath());
                // Si el directorio padre no existiera, lo creamos
                if (!ficheroCopia.getParentFile().exists()) {
                    ficheroCopia.getParentFile().mkdirs(); // Creamos los directorios padres si fuera necesario
                }
                // Copiamos el fichero
                Files.copy(Paths.get(ficheroOriginal.getCanonicalPath()), Paths.get(rutaFicheroCopia), StandardCopyOption.REPLACE_EXISTING);
                // Añadimos el objeto a la lista de copiados
                listaFicherosCopiados.add(ficheroCopia);
            }
        }

        return listaFicherosCopiados;
    }

    public static List<File> restaurarFicherosCopiados(BackUp backup) throws IOException{
        reiniciarListaSubficheros();

        List<File> listaRestaurados = new ArrayList<>();
        // Primero borramos todos los ficheros que existan en el directorio original del que se hizo el backup
        List<File> ficherosActualesDirectorioOriginal = listarFicherosRecursivo(backup.getConfiguracion().getDirectorioOrigen());
        for (File ficheroActual : ficherosActualesDirectorioOriginal) {
            if (ficheroActual.exists()) {
                ficheroActual.delete();
            }
        }
        // Luego borramos todos los directorios, que habrán quedado vacíos
        List<File> subdirectoriosVacios = listarFicherosRecursivoFiltrado(backup.getConfiguracion().getDirectorioOrigen(), new FiltroFileDirectorio());
        for (File subdirectorioVacio : subdirectoriosVacios) {
            if (subdirectorioVacio.exists() && subdirectorioVacio.list().length == 0) {
                subdirectorioVacio.delete();
            }
        }
        // Copiamos los ficheros que no sean directorios, creando sus directorios padres cuando sea necesario
        for (FicheroCopia ficheroCopiado : backup.getListaFicherosCopiados()) {
            if (ficheroCopiado.exists()) {
                if (!ficheroCopiado.isDirectory()) {
                    // Fichero con la ruta original
                    File ficheroARestaurar = new File(ficheroCopiado.getRutaOrigen());
                    // Si el directorio padre no existiera, lo creamos
                    if (!ficheroARestaurar.getParentFile().exists()) {
                        ficheroARestaurar.getParentFile().mkdirs(); // Creamos los directorios padres si fuera necesario
                    }
                    // Copiamos el fichero
                    Files.copy(Paths.get(ficheroCopiado.getCanonicalPath()), Paths.get(ficheroCopiado.getRutaOrigen()), StandardCopyOption.REPLACE_EXISTING);
                    // Añadimos el objeto a la lista de restaurados
                    listaRestaurados.add(ficheroARestaurar);
                }
            }

        }
        return listaRestaurados;
    }

    public static List<File> listarDuplicados(File directorio) throws IOException {
        reiniciarListaSubficheros();

        // Listamos todos los ficheros hijos del directorio origen
        List<File> listaFicheros = listarFicherosRecursivoFiltrado(directorio, new FiltroFileNoDirectorio());
        List<File> listaDuplicados = new ArrayList<>();
        Collections.sort(listaFicheros, new Comparators.comparatorFileTamanhoYNombre());

        for (int i = 1; i < listaFicheros.size(); i++) { // Empezamos en 1, no en 0, pues el 1º elemento no tiene un anterior para comparar
            // Necesitamos comparar objetos de nuestra clase, porque es la que tiene sobreescrito el equals
            FicheroCopia ficheroAnterior = new FicheroCopia(listaFicheros.get(i - 1).getCanonicalPath());
            FicheroCopia ficheroLeido = new FicheroCopia(listaFicheros.get(i).getCanonicalPath());
            if (ficheroLeido.equals(ficheroAnterior)) {
         
                if (!listaDuplicados.contains(ficheroAnterior)) {
                    listaDuplicados.add(ficheroAnterior);
                }
                listaDuplicados.add(ficheroLeido);
            }
        }
        return listaDuplicados;
    }

    public static List<File> listarSinDuplicados(File directorio) throws IOException {
        reiniciarListaSubficheros();
        //reiniciarListas();
        // Listamos todos los ficheros hijos del directorio origen
        List<File> listaFicheros = listarFicherosRecursivo(directorio);
        List<File> listaSinDuplicados = new ArrayList<>();
        Collections.sort(listaFicheros, new Comparators.comparatorFileTamanhoYNombre());
        // LOS DIRECTORIOS DEVUELVEN SIEMPRE LENGTH()=0, NO MIRA EL TAMAÑO DEL CONTENIDO
        listaSinDuplicados.add(listaFicheros.get(0));
        for (int i = 1; i < listaFicheros.size(); i++) {
            FicheroCopia ficheroAnterior = new FicheroCopia(listaFicheros.get(i - 1).getCanonicalPath());
            // Necesitamos comparar objetos de nuestra clase, porque es la que tiene sobreescrito el equals
            FicheroCopia ficheroLeido = new FicheroCopia(listaFicheros.get(i).getCanonicalPath());
            if (!ficheroLeido.equals(ficheroAnterior)) {
                listaSinDuplicados.add(ficheroLeido);
            }
        }
        return listaSinDuplicados;
    }

    public static List<File> listarFicherosRecursivo(File directorio) throws IOException {
        // Aquí no podemos reiniciar la lista de subficheros, porque la pisaría una y otra vez al ser un método recursivo
        listaSubficheros.addAll(Arrays.asList(directorio.listFiles()));
        // Recorremos los sub-directorios y a su vez listamos sus sub-ficheros
        for (File fichero : directorio.listFiles(new FiltroFileDirectorio())) {
            listarFicherosRecursivo(fichero);
        }
        return listaSubficheros;
    }

    public static List<File> listarFicherosRecursivoFiltrado(File directorio, FileFilter filtro) throws IOException {
        listaSubficheros.addAll(Arrays.asList(directorio.listFiles(filtro)));
        // Recorremos los sub-directorios y a su vez listamos sus sub-ficheros filtrados
        for (File fichero : directorio.listFiles(new FiltroFileDirectorio())) {
            listarFicherosRecursivoFiltrado(fichero, filtro);
        }
        return listaSubficheros;
    }

    public static List<File> eliminarDuplicados(File directorio) throws IOException {
        reiniciarListaSubficheros();

        List<File> borrados = new ArrayList<>();
        List<File> duplicados = listarDuplicados(directorio);

        for (File fichero : duplicados) {
            if (!fichero.isDirectory()) {
                borrados.add(fichero);
                fichero.delete();
            }
        }
        // Borramos los directorios que hayan quedado vacíos
        eliminarDirectoriosVacios(directorio);

        return borrados;
    }

    public static List<File> eliminarDirectoriosVacios(File directorio) throws IOException {
        reiniciarListaSubficheros();

        List<File> borrados = new ArrayList<>();

        for (File fichero : listarFicherosRecursivo(directorio)) {
            if (fichero.isDirectory() && fichero.list().length == 0) {
                if (fichero.delete()) {
                    borrados.add(fichero);
                }
            }
        }

        return borrados;
    }

    public static List<File> eliminarDirectorioConContenido(File directorioPadre) throws IOException {
        reiniciarListaSubficheros();

        List<File> borrados = new ArrayList<File>();

        // Borramos todos los ficheros para dejar los directorios vacíos
        List<File> ficherosNoDirectorio = listarFicherosRecursivoFiltrado(directorioPadre, new FiltroFileNoDirectorio());
        for (File fichero : ficherosNoDirectorio) {
            if (fichero.exists()) {
                if (fichero.delete()) {
                    borrados.add(fichero);
                }
            }
        }

        // Borramos todos los directorios ya vacíos
        reiniciarListaSubficheros();
        List<File> ficherosDirectorio = listarFicherosRecursivoFiltrado(directorioPadre, new FiltroFileDirectorio());
        // Ordenamos la lista de mayor a menor ruta (orden reverso al comparar Strings) para que borre primero los directorios hijos
        // Si no borra en orden de hijos a padres, quedan directorios que contienen otros directorios vacíos y no los borra 
        ficherosDirectorio.sort(new Comparators.ComparatorFileRuta().reversed());
        for (File directorioVacio : ficherosDirectorio) {
            if (directorioVacio.exists()) {
                directorioVacio.delete();
            }
        }

        if (directorioPadre.exists() && directorioPadre.list().length == 0) {
            directorioPadre.delete();
        }

        return borrados;
    }

    public static List<File> agruparPorCategoria(File directorio, List<String> listaExtensiones, String categoria) throws IOException {
        reiniciarListaSubficheros();

        List<File> ficherosMovidos = new ArrayList<>();

        String nombreNuevoDirectorio = categoria.toUpperCase();
        String rutaNuevoDirectorio = directorio.getCanonicalPath() + File.separator + nombreNuevoDirectorio;
        File nuevoDirectorio = new File(rutaNuevoDirectorio);
        nuevoDirectorio.mkdir();

        List<File> ficherosFiltrados = listarFicherosRecursivoFiltrado(directorio, new FiltroFileExtension(listaExtensiones));
        for (File ficheroOriginal : ficherosFiltrados) {
            String rutaDestino = rutaNuevoDirectorio + File.separator + ficheroOriginal.getName();
            File ficheroDestino = new File(rutaDestino);
            Files.move(Paths.get(ficheroOriginal.getCanonicalPath()), Paths.get(ficheroDestino.getCanonicalPath()), StandardCopyOption.REPLACE_EXISTING);
            // Necesitamos devolver los ficheros creados, para luego poder actualizar 
            // la ruta en que se encuentran en la lista de copiados del backup
            ficherosMovidos.add(ficheroDestino);
        }
        return ficherosMovidos;
    }

    public static double espacioLibre(File directorioRaiz) {
        return directorioRaiz.getFreeSpace() / (Math.pow(1024, 3));
    }

    public static double espacioTotal(File directorioRaiz) {
        // Le daremos DecimalFormat al mostrarlo
        return directorioRaiz.getTotalSpace() / (Math.pow(1024, 3));
    }

    public static double tamanhoDirectorio(File directorio) throws IOException{
        double tamanhoTotal=0;
        if (directorio.isDirectory()) {
            List<File> listaSubficherosNoDirectorio = listarFicherosRecursivoFiltrado(directorio,new FiltroFileNoDirectorio());
            for (File fichero : listaSubficherosNoDirectorio) {
                tamanhoTotal+=fichero.length();
            }
        }
        return tamanhoTotal/(Math.pow(1024, 3)); // En GB, para comparar con el espacio libre en el directorio raíz
    }
    
    public static void mostrarListaDeFicheros(List<File> listaAMostrar) throws IOException {
        for (File fileLeido : listaAMostrar) {
            System.out.println(fileLeido.getCanonicalPath() + "  Extensión: " + OperacionesAuxiliares.getExtension(fileLeido));
        }
    }

    public static void mostrarListaDeFicherosCopiados(List<FicheroCopia> listaAMostrar) throws IOException {
        for (File fileLeido : listaAMostrar) {
            System.out.println(fileLeido.getCanonicalPath() + "  Extensión: " + OperacionesAuxiliares.getExtension(fileLeido));
        }
    }

    public static void mostrarMapDeFicheros(Map<List<String>, List<File>> mapAMostrar) throws IOException {
        Set<List<String>> keySet = mapAMostrar.keySet();
        for (List<String> extensiones : keySet) {
            List listaSubdirectorios = mapAMostrar.get(extensiones);
            for (String extension : extensiones) {
                System.out.print(extension.toUpperCase() + " - ");
            }
            System.out.println("");
            mostrarListaDeFicheros(listaSubdirectorios);
            System.out.println("-------------------------");
        }
    }

    public static void reiniciarListaSubficheros() {
        listaSubficheros = new ArrayList<>();
    }
    
}
