
package lectorenv;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;


public class Leer {
    static Map<String, String> variables = new HashMap<String, String>();
    static Scanner teclado = new Scanner(System.in);
    static String rutaArchivo;

    public Leer(String ruta) {
        rutaArchivo = ruta;
    }
    
    
    public Map<String, String> getData() {
        FileReader lector = null;
        try {
            lector = new FileReader(rutaArchivo);
            BufferedReader iterador = new BufferedReader(lector);
            String linea = "";
            while((linea = iterador.readLine()) != null) {
                String [] rd = linea.split("=");
                variables.put(rd[0], rd[1]);
            }
            
            return variables;
        } catch (FileNotFoundException ex) {
            Logger.getLogger(Leer.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(Leer.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            try {
                lector.close();
            } catch (IOException ex) {
                Logger.getLogger(Leer.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        
        return variables;
    }
    
    public void leerAgregar(){
  
        String clave;
        String valor = "";
        boolean salir = false;
        do{            
                System.out.println("Ingrese la clave a buscar");  
                clave = teclado.nextLine();
                if(clave.equals("")) { // LA CLAVE INGRESADA ESTÁ VACIA
                    salir = true;
                } else { // LA CLAVE INGRESADA NO ESTA VACIA
                    if(variables.get(clave) == null) { // LA CLAVE NO EXISTE
                        System.out.println("Ingrese valor");         
                        valor = teclado.nextLine();         
                        if(!valor.equals("")){ try {
                            // VERIFICAMOS QUE VALOR SEA DIFERENTE DE VACIO
                            variables.put(clave, valor);
                            // GUARDAR EN ARCHIVO EL ORIGINAL                      
                            FileOutputStream archivo = new FileOutputStream(rutaArchivo, true);
                            String lineaParaAgregar = "\n"+clave+"="+valor;
                            byte[] datos= lineaParaAgregar.getBytes(); 
                            archivo.write(datos);
                            archivo.close();
                            } catch (IOException ex) {
                                Logger.getLogger(Leer.class.getName()).log(Level.SEVERE, null, ex);
                            }
                            
                        }                 
                    } else { // LA CLAVE EXISTE
                         System.out.println(variables.get(clave));
                    }           
                }  
            } while(salir != true);
      
    }
    
    public void leerMap(Map<String, String>variables) {
        for (Map.Entry<String, String> dato : variables.entrySet()) {
           System.out.println(dato);            
        }
    }
    
}
