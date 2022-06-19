
package lectorenv;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;

public class LectorEnv {
 
    public static void main(String[] args) {
       Leer env = new Leer("variables.env");
       env.leerMap(env.getData());      
       env.leerAgregar();
    }
}
