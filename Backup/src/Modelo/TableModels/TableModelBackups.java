/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Modelo.TableModels;

import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.List;
import javax.swing.table.AbstractTableModel;

/**
 *
 * @author rodrigo valdes
 */
public class TableModelBackups extends AbstractTableModel{
        // ATRIBUTOS
    private List<BackUp> listaBackups;
    private String[] cabecerasColumna = {"FECHA", "DESCRIPCIÓN"};

    // MÉTODOS
    public TableModelBackups(List<BackUp> listaBackups) { // Le pasaremos la lista del controlador al instanciarlo
        this.listaBackups = listaBackups;
    }

    public BackUp getRow(int row) {
        return listaBackups.get(row);
    }

    @Override
    public int getRowCount() {
        return listaBackups.size();
    }

    @Override
    public int getColumnCount() {
        return cabecerasColumna.length;
    }

    @Override
    public Object getValueAt(int row, int col) {
        // la fila coincidirá 
        switch (col) {
            case 0:
                SimpleDateFormat dateFormat=new SimpleDateFormat("E dd/MM/YYYY HH:mm:ss");
                return dateFormat.format(listaBackups.get(row).getFecha());
            case 1:
                return listaBackups.get(row).getNombre();
            default:
                throw new AssertionError();
        }
    }

    @Override
    public String getColumnName(int col) {
        return cabecerasColumna[col]; //To change body of generated methods, choose Tools | Templates.
    }

    public void refrescar() {
        fireTableDataChanged();
    }

    public int removeRows(int[] rowsOrdenados) {
        // Los ÍNDICES tienen que estar ORDENADOS para que no de error al borrar uno posterior y luego otro anterior
        Arrays.sort(rowsOrdenados);
        int contador = 0;
        for (int row : rowsOrdenados) {
            boolean borrado = removeRow(row - contador);
            if (borrado) {
                contador++;
            }
        }
        return contador;
    }

    // Aquí lo tenía como mi ejercicio de la agenda, lo cambié, si peta lo cambio de nuevo
    public boolean removeRow(int row) {
        if (!(listaBackups.isEmpty()) && row < listaBackups.size()) {
            listaBackups.remove(row);
            fireTableDataChanged();
            return true;
        }
        return false;
    }

    public boolean addRow(BackUp backup) {
        return listaBackups.add(backup);
    }
    
}
