/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controlador;

import Modelo.OperacionesFicheros;
import java.io.File;
import java.io.IOException;

/**
 *
 * @author rodrigo valdes
 */
public class MainPruebas {
      /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws IOException {


            

            
        /*
        OperacionesFicheros.mostrarListaDeFicherosCopiados(OperacionesFicheros.copiarFicheros(new File("misficheros"), new File("copias"),null));
        System.out.println("-----------------------------------------------");
        OperacionesFicheros.mostrarListaDeFicherosCopiados(OperacionesFicheros.copiarFicheros(new File("misficheros"), new File("copias"),null));
        System.out.println("-----------------------------------------------");
        OperacionesFicheros.mostrarListaDeFicherosCopiados(OperacionesFicheros.copiarFicheros(new File("misficheros"), new File("copias"),null));
        System.out.println("-----------------------------------------------");
        */
        
        /*
        OperacionesFicheros.mostrarListaDeFicheros(OperacionesFicheros.listarDuplicados(new File("copias")));
        System.out.println("----------");
        OperacionesFicheros.mostrarListaDeFicheros(OperacionesFicheros.listarDuplicados(new File("copias")));
        System.out.println("----------");
        OperacionesFicheros.mostrarListaDeFicheros(OperacionesFicheros.listarDuplicados(new File("copias")));
        System.out.println("------------------------------");
        OperacionesFicheros.mostrarListaDeFicheros(OperacionesFicheros.listarSinDuplicados(new File("copias")));
        */
        
        /*
        ArrayList<String> extensiones = new ArrayList<String>();
        extensiones.add(".txt");
        File file = new File("copias");
        OperacionesFicheros.mostrarListaDeFicheros(OperacionesFicheros.listarFicherosRecursivoFiltrado(file,new FiltroFileExtension(extensiones)));
        */
        /*
        ArrayList<String> extensiones1 = new ArrayList<String>();
        extensiones1.add(".txt");
        extensiones1.add("docx");
        ArrayList<String> extensiones2 = new ArrayList<String>();
        extensiones2.add("bmp");
        extensiones2.add(".png");
        List<List<String>> extensiones=new ArrayList<>();
        extensiones.add(extensiones1);
        extensiones.add(extensiones2);
        
        File file = new File("copias");
        //OperacionesFicheros.mostrarListaDeFicheros(OperacionesFicheros.agruparUnTipo(file, extensiones1));
        OperacionesFicheros.mostrarMapDeFicheros(OperacionesFicheros.agruparPorTipo(file, extensiones));
        */
        
        /*
        // Copio un fichero de misficheros/directorio, le cambio la ruta de origen para que me lo restaure en misficheros y
        // comprobar que lo ha hecho (como sobreescribe el existente, si lo copio en el mismo directorio no noto la diferencia)
        File file = new File("misficheros/directorio");
        List<FicheroCopia> listaCopiados = OperacionesFicheros.copiarFicheros(file, new File("copias"), null);
        System.out.println(listaCopiados.get(0).getRutaOrigen());
        System.out.println(file.getCanonicalPath());
        System.out.println(file.getParentFile().getCanonicalPath());
        // Lo grabaré en el directorio padre, para ver si lo hace
        listaCopiados.get(0).setRutaOrigen(file.getParentFile().getCanonicalPath() + File.separator + listaCopiados.get(0).getName());
        System.out.println(listaCopiados.get(0).getRutaOrigen());
        List<File> listaRestaurados = OperacionesFicheros.restaurarFicherosCopiados(listaCopiados);
        OperacionesFicheros.mostrarListaDeFicheros(listaRestaurados);
        
        
        System.out.println(file.getName());
        System.out.println(file.getParent());
        System.out.println(file.getPath());
        System.out.println(file.getAbsolutePath());
        */
        
        /*
        File file = new File("misficheros");
        try {
        OperacionesFicheros.eliminarDirectoriosVacios(file);
        } catch (IOException ex) {
        Logger.getLogger(MainPruebas.class.getName()).log(Level.SEVERE, null, ex);
        }
        */
        /*
        File raizPendrive =new File("ubicacion/../..");
        System.out.println(raizPendrive.getCanonicalPath());
        
        }
        */
        /*
        File file = new File("misficheros");
        OperacionesFicheros.agruparPorCategoria(file, ExtensionesFicheros.extensionesTexto(),"TEXTO");
        */
        
        File file = new File("misficheros");
         System.out.println(OperacionesFicheros.tamanhoDirectorio(file));   
     

    }
    
}
