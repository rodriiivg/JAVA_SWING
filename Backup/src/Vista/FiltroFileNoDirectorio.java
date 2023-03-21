/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Vista;

import java.io.File;

/**
 *
 * @author rodrigo valdes
 */
public class FiltroFileNoDirectorio {
      public boolean accept(File file) {
        return !file.isDirectory();
    }
}
