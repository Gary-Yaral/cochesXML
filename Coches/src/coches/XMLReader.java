
package coches;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerConfigurationException;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NamedNodeMap;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

public class XMLReader {
    private String archivo;

    public XMLReader(String archivo) {
        this.archivo = archivo;
    }
    
    
    public ArrayList<Coche> getContent(){
        ArrayList<Coche> coches = new ArrayList<Coche>();     
   
        try {
            
            DocumentBuilderFactory fabrica = DocumentBuilderFactory.newInstance();
            DocumentBuilder builder = fabrica.newDocumentBuilder();
            Document documento = builder.parse(new File(archivo));
            
            NodeList listaCoches = documento.getElementsByTagName("coche");
           
            
            for (int i = 0; i < listaCoches.getLength(); i++) {
                Node cocheData = listaCoches.item(i);             
                if(cocheData.getNodeType() == Node.ELEMENT_NODE) {
                    Element atributos = (Element) cocheData;  // Convertimos a elemento para extraer los hijos
                    NodeList hijos = atributos.getChildNodes();
                    
                    Coche coche = new Coche();
                    coche.setSerial(atributos.getAttribute("serial"));
                    
                    for (int j = 0; j < hijos.getLength(); j++) {
                        Node info = hijos.item(j);         
                        if(info.getNodeType() == Node.ELEMENT_NODE) {
                            Element datos = (Element) info;
                            String etiqueta = datos.getNodeName();
                           
                            switch(etiqueta) {
                                case "marca":
                                    coche.setMarca(datos.getTextContent());
                                    break;
                                case "modelo":
                                    coche.setModelo(datos.getTextContent());
                                    break;
                                case "año":
                                    coche.setAño(datos.getTextContent());
                                    break;
                                case "color":
                                    coche.setColor(datos.getTextContent());
                                    break;
                            }
                        }
                    }  
                    
                    coches.add(coche);
                }                            
            }
            
           return coches;
            
            
        } catch (ParserConfigurationException | IOException | SAXException ex) {
            Logger.getLogger(XMLReader.class.getName()).log(Level.SEVERE, null, ex);
        }
        
     return coches;
     
    }
    
    public void addRow(Coche coche) {
          
        try {
            DocumentBuilderFactory fabrica = DocumentBuilderFactory.newInstance();
            DocumentBuilder builder = fabrica.newDocumentBuilder();
            Document documento = builder.parse(new File(archivo));
            
            documento.getDocumentElement().normalize();
            Node etiquetaRaiz = documento.getDocumentElement();
            
            Element etiquetaCoche = documento.createElement("coche"); // Etiqueta coche
            etiquetaCoche.setAttribute("serial", coche.getSerial());
            
            // Creamos las etiquetas hijos
            Element etiquetaMarca = documento.createElement("marca");
            Element etiquetaModelo = documento.createElement("modelo");
            Element etiquetaAño = documento.createElement("año");
            Element etiquetaColor = documento.createElement("color");
            
            // Rellenamos las etiquetas hijos
            etiquetaMarca.setTextContent(coche.getMarca());
            etiquetaModelo.setTextContent(coche.getModelo());
            etiquetaAño.setTextContent(coche.getAño());
            etiquetaColor.setTextContent(coche.getColor());
            
            // Agregamos los hijos a la etiqueta coche
            etiquetaCoche.appendChild(etiquetaMarca);
            etiquetaCoche.appendChild(etiquetaModelo);
            etiquetaCoche.appendChild(etiquetaAño);
            etiquetaCoche.appendChild(etiquetaColor);
            
            // Ahora pasamos la etiqueta coche a la etiquetas raiz llamada coches
            etiquetaRaiz.appendChild(etiquetaCoche);
            
            Transformer transformer = TransformerFactory.newInstance().newTransformer();
            DOMSource src = new DOMSource(documento);
            StreamResult resultado = new StreamResult(new File(archivo));
            transformer.transform(src, resultado);
          
            
        } catch (ParserConfigurationException | SAXException | IOException | TransformerConfigurationException ex) {
            Logger.getLogger(XMLReader.class.getName()).log(Level.SEVERE, null, ex);
        } catch (TransformerException ex) {
            Logger.getLogger(XMLReader.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public boolean updateRow(String serial, String marca, String modelo, String año, String color, String serialAntiguo) {
            boolean fueModificado = false;
            
        try { 
            
            DocumentBuilderFactory fabrica = DocumentBuilderFactory.newInstance();
            DocumentBuilder builder = fabrica.newDocumentBuilder();
            Document documento = builder.parse(new File(archivo));
            
            documento.getDocumentElement().normalize();
        
            
            NodeList listaCoches = documento.getElementsByTagName("coche");
            
            for (int i = 0; i < listaCoches.getLength(); i++) {
                Node coche_c = listaCoches.item(i);
                NamedNodeMap namedNodeMap = coche_c.getAttributes();
                Node serialSeleccionado = namedNodeMap.getNamedItem("serial");
                
                if(serialSeleccionado.getTextContent().equals(serialAntiguo)) {
                    serialSeleccionado.setTextContent(serial);                   
                  
                    NodeList hijos = coche_c.getChildNodes();
                    Element listaHijos = (Element) hijos;
                    
                    Node marca_c = listaHijos.getElementsByTagName("marca").item(0);
                    Element marca_cast = (Element) marca_c;
                    marca_cast.setTextContent(marca);

                    Node modelo_c = listaHijos.getElementsByTagName("modelo").item(0);
                    Element modelo_cast = (Element) modelo_c;
                    modelo_cast.setTextContent(modelo);

                    Node año_c = listaHijos.getElementsByTagName("año").item(0);
                    Element año_cast = (Element) año_c;
                    año_cast.setTextContent(año);

                    Node color_c = listaHijos.getElementsByTagName("color").item(0);
                    Element color_cast = (Element) color_c;
                    color_cast.setTextContent(color);
                    
                    fueModificado = true;
                }
                
            }
                   
            
            Transformer transformer = TransformerFactory.newInstance().newTransformer();
            DOMSource src = new DOMSource(documento);
            StreamResult resultado = new StreamResult(new File(archivo));
            transformer.transform(src, resultado);
            
            return fueModificado;
        } catch (ParserConfigurationException | SAXException | IOException ex) {
            Logger.getLogger(XMLReader.class.getName()).log(Level.SEVERE, null, ex);
        } catch (TransformerConfigurationException ex) {
            Logger.getLogger(XMLReader.class.getName()).log(Level.SEVERE, null, ex);
        } catch (TransformerException ex) {
            Logger.getLogger(XMLReader.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return fueModificado;
       
        
    }
    
    public boolean deleteRow(String serial) {
            boolean fueEliminado = false;
            
        try { 
            
            DocumentBuilderFactory fabrica = DocumentBuilderFactory.newInstance();
            DocumentBuilder builder = fabrica.newDocumentBuilder();
            Document documento = builder.parse(new File(archivo));
            
            Element nodoRaiz = documento.getDocumentElement();
            NodeList listaCoches = documento.getElementsByTagName("coche");
            
            for (int i = 0; i < listaCoches.getLength(); i++) {
                Node coche_c = listaCoches.item(i);
                NamedNodeMap namedNodeMap = coche_c.getAttributes();
                Node serialSeleccionado = namedNodeMap.getNamedItem("serial");
                
                if(serialSeleccionado.getTextContent().equals(serial)) {
                   nodoRaiz.removeChild(coche_c);
                   fueEliminado = true;
                }
                
            }               
            
            Transformer transformer = TransformerFactory.newInstance().newTransformer();
            DOMSource src = new DOMSource(documento);
            StreamResult resultado = new StreamResult(new File(archivo));
            transformer.transform(src, resultado);
            
            return fueEliminado;
        } catch (ParserConfigurationException | SAXException | IOException ex) {
            Logger.getLogger(XMLReader.class.getName()).log(Level.SEVERE, null, ex);
        } catch (TransformerConfigurationException ex) {
            Logger.getLogger(XMLReader.class.getName()).log(Level.SEVERE, null, ex);
        } catch (TransformerException ex) {
            Logger.getLogger(XMLReader.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return fueEliminado;
       
        
    }
          
}
