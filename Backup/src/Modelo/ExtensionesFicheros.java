/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Modelo;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author rodrigo valdes
 */
public class ExtensionesFicheros {
     public static List<String> extensionesImagen(){
        List<String> listaExtensionesImagen=new ArrayList<>();
        listaExtensionesImagen.add("JPG");
        listaExtensionesImagen.add("JPEG");
        listaExtensionesImagen.add("PNG");
        listaExtensionesImagen.add("GIF");
        listaExtensionesImagen.add("BMP");
        listaExtensionesImagen.add("RAW");
        listaExtensionesImagen.add("TIF");
        listaExtensionesImagen.add("TIFF");
        return listaExtensionesImagen;
    }
    
    public static List<String> extensionesVideo(){
        List<String> listaExtensionesVideo=new ArrayList<>();
        listaExtensionesVideo.add("AVI");
        listaExtensionesVideo.add("MKV");
        listaExtensionesVideo.add("M4V");
        listaExtensionesVideo.add("MOV");
        listaExtensionesVideo.add("MPG");
        listaExtensionesVideo.add("MPEG");
        listaExtensionesVideo.add("SWF");
        listaExtensionesVideo.add("WMV");
        return listaExtensionesVideo;
    }
    
    public static List<String> extensionesAudio(){
        List<String> listaExtensionesAudio=new ArrayList<>();
        listaExtensionesAudio.add("MP3");
        listaExtensionesAudio.add("MP4");
        listaExtensionesAudio.add("M4A");
        listaExtensionesAudio.add("WAV");
        listaExtensionesAudio.add("WMA");
        listaExtensionesAudio.add("MIDI");
        listaExtensionesAudio.add("MPGA");
        listaExtensionesAudio.add("WAV");
        return listaExtensionesAudio;
    }
    
    public static List<String> extensionesTexto(){
        List<String> listaExtensionesTexto=new ArrayList<>();
        listaExtensionesTexto.add("TXT");
        listaExtensionesTexto.add("DOCX");
        listaExtensionesTexto.add("ODT");
        listaExtensionesTexto.add("DOC");
        return listaExtensionesTexto;
    }
    
    public static List<String> extensionesComprimidos(){
        List<String> listaExtensionesComprimido=new ArrayList<>();
        listaExtensionesComprimido.add("ZIP");
        listaExtensionesComprimido.add("7Z");
        listaExtensionesComprimido.add("RAR");
        return listaExtensionesComprimido;
    }
    
     public static List<String> extensionesPDF(){
        List<String> listaExtensionesPDF=new ArrayList<>();
        listaExtensionesPDF.add("PDF");
        return listaExtensionesPDF;
    }
    
}
