
package modelos;

import java.awt.Component;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import vistas.Vista;

public class Modelo {
    public List<List<String>> ListarProcesos() throws IOException {
        
        String[] comando = {"ps", "-eo", "pid,euser,comm"};
        
        Process proceso = Runtime.getRuntime().exec(comando);
        
        BufferedReader lector = new BufferedReader(new InputStreamReader(proceso.getInputStream()));
                
        String linea;
        List<List<String>> tablaProcesos = new ArrayList<List<String>>();
        
        while ((linea = lector.readLine()) != null) {
            List<String> filaProceso = new ArrayList<String>();
            filaProceso.add(linea.substring(0, 7));
            filaProceso.add(linea.substring(8, 15));
            filaProceso.add(linea.substring(16));
            tablaProcesos.add(filaProceso);
        }
        return tablaProcesos;
        
        
    }
    public void MatarProcesos(String proceso) throws IOException {
        ProcessBuilder pb01 = new ProcessBuilder("killall", proceso);
        Process p01 = pb01.start();

    }
    public void NuevoProceso(String proceso) throws IOException {                                       
       ProcessBuilder pb01 = new ProcessBuilder(proceso);
       try {
            Process p01 = pb01.start();
        } catch (IOException ex) {
            Logger.getLogger(Modelo.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
}
