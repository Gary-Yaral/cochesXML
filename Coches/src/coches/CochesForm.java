
package coches;

import java.awt.Component;
import java.awt.Frame;
import java.util.ArrayList;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

public class CochesForm extends javax.swing.JFrame {

    static DefaultTableModel modelo = new DefaultTableModel();
    static ArrayList<Coche> coches = new ArrayList<Coche>();
    static JFrame ventanaAlerta = new JFrame();
    static String serialSeleccionado = "";
    XMLReader reader;
    
    public CochesForm() {
        initComponents();
        
        reader = new XMLReader("coches.xml");
        coches = reader.getContent();
        agregarCabeceras();
        llenarTabla(coches);
        seleccionado.setText("");
    }
    
    public void agregarCabeceras() {
        modelo.addColumn("Serial");
        modelo.addColumn("Marca");
        modelo.addColumn("Modelo");
        modelo.addColumn("Año");
        modelo.addColumn("Color");
    }
    
    
    public void llenarTabla(ArrayList<Coche> coches) {
        limpiarTabla(); 
        for (int i = 0; i < coches.size(); i++) {        
            Object[] fila = new Object[5];
            Coche coche = coches.get(i);
            fila[0] = coche.getSerial();
            fila[1] = coche.getMarca();
            fila[2] = coche.getModelo();
            fila[3] = coche.getAño();
            fila[4] = coche.getColor();
            modelo.addRow(fila);
        }   
        
        jTable1.setModel(modelo);
        seleccionado.setText("");
    }
    
    public static void limpiarTabla() {
        int numDatos = modelo.getRowCount();
        for (int i = 0; i < numDatos; i++) {
         modelo.removeRow(0);
        }
    }
    
    public void eliminarFila() {
        int indice = jTable1.getSelectedRow();
        
        String serial = modelo.getValueAt(indice, 0).toString();
        String marca = modelo.getValueAt(indice, 1).toString();
        String modelo_f = modelo.getValueAt(indice, 2).toString();
        String año = modelo.getValueAt(indice, 3).toString();
        String color = modelo.getValueAt(indice, 3).toString();
        
    }
    
    public void llenarCampos() {
        // Primero limpiamos los campos
        limpiarCampos();
        
        // indice de la fila seleccionada
        int indice = jTable1.getSelectedRow();
          
        // Nos traemos los datos de las fila seleccionada
        String serial_f = modelo.getValueAt(indice, 0).toString();
        String marca_f = modelo.getValueAt(indice, 1).toString();
        String modelo_f = modelo.getValueAt(indice, 2).toString();
        String año_f = modelo.getValueAt(indice, 3).toString();
        String color_f = modelo.getValueAt(indice, 3).toString();
        
        // LLenamos los campos con los datos de la fila seleccionada
        serial.setText(serial_f);
        marca.setText(marca_f);
        modeloForm.setText(modelo_f);
        año.setText(año_f);
        color.setText(color_f);
        
        // Guardams este indice para cuando vayamos a modificar
        serialSeleccionado = serial_f;
        seleccionado.setText(serialSeleccionado);
    }
    
    public void limpiarCampos() {
        serial.setText("");
        marca.setText("");
        modeloForm.setText("");
        año.setText("");
        color.setText("");
        serial.requestFocusInWindow(); 
        seleccionado.setText("");
    }
    
    public void agregarCoche() {      
        boolean existe = buscarCoche(serial.getText());
        if(validarDatosLlenos()) {         
            if(existe) {           
               JOptionPane.showMessageDialog(ventanaAlerta, "Ya existe un coche con ese serial");
               serial.requestFocusInWindow(); 
            } else {
                Coche coche = new Coche();
                coche.setSerial(serial.getText());
                coche.setMarca(marca.getText());
                coche.setModelo(modeloForm.getText());
                coche.setAño(año.getText());
                coche.setColor(color.getText());

                reader.addRow(coche);

                coches = reader.getContent();
                llenarTabla(coches);
                JOptionPane.showMessageDialog(ventanaAlerta, "Coche guardado correctamente");
                limpiarCampos();
            }
        } else {
            JOptionPane.showMessageDialog(ventanaAlerta, "Debe llenar todos los campos");
        }
        
       
    }
    
    public boolean buscarCoche(String serial) {
        boolean existe = false;
        for (Coche coche : coches) {
            if (coche.getSerial().equals(serial)) {
                existe = true;
            }
        }
        
        return existe;
    }
    
    public boolean eliminarCoche(String serial) {       
        return reader.deleteRow(serial);
    }
    
    public boolean validarDatosLlenos() {
        boolean estanLlenos = false;    
        String serial_c = serial.getText();
        String marca_c = marca.getText();
        String modelo_c = modeloForm.getText();
        String año_c = año.getText();
        String color_c = color.getText();
       
        if(serial_c.equals("")) {
            serial.requestFocusInWindow();
            return estanLlenos;
        } else if(marca_c.equals("")) {
            marca.requestFocusInWindow();
            return estanLlenos;
        } else if(modelo_c.equals("")) {
            modeloForm.requestFocusInWindow();
            return estanLlenos;
        } else if(año_c.equals("")) {
            año.requestFocusInWindow();
            return estanLlenos;
        } else if(color_c.equals("")) {
            color.requestFocusInWindow();
            return estanLlenos;
        } else {
            
            estanLlenos = true;
        }
        
        System.out.println(estanLlenos);
        return estanLlenos;

    }
    
    
   
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jTextField8 = new javax.swing.JTextField();
        jPanel1 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTable1 = new javax.swing.JTable();
        jButton1 = new javax.swing.JButton();
        serial = new javax.swing.JTextField();
        marca = new javax.swing.JTextField();
        año = new javax.swing.JTextField();
        modeloForm = new javax.swing.JTextField();
        color = new javax.swing.JTextField();
        jButton2 = new javax.swing.JButton();
        jButton3 = new javax.swing.JButton();
        jButton4 = new javax.swing.JButton();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        seleccionado = new javax.swing.JLabel();

        jTextField8.setText("jTextField6");

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));

        jTable1.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        jTable1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jTable1MouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(jTable1);

        jButton1.setText("Limpiar");
        jButton1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jButton1MouseClicked(evt);
            }
        });

        serial.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                serialActionPerformed(evt);
            }
        });

        jButton2.setBackground(new java.awt.Color(153, 255, 153));
        jButton2.setForeground(new java.awt.Color(0, 102, 51));
        jButton2.setText("Agregar");
        jButton2.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jButton2MouseClicked(evt);
            }
        });

        jButton3.setBackground(new java.awt.Color(255, 255, 153));
        jButton3.setForeground(new java.awt.Color(51, 51, 0));
        jButton3.setText("Modificar");
        jButton3.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jButton3MouseClicked(evt);
            }
        });

        jButton4.setBackground(new java.awt.Color(255, 153, 153));
        jButton4.setForeground(new java.awt.Color(153, 0, 0));
        jButton4.setText("Eliminar");
        jButton4.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jButton4MouseClicked(evt);
            }
        });

        jLabel1.setFont(new java.awt.Font("Tahoma", 1, 24)); // NOI18N
        jLabel1.setText("Coches");

        jLabel2.setText("Serial");

        jLabel3.setText("Marca");

        jLabel4.setText("Modelo");

        jLabel5.setText("Año");

        jLabel6.setText("Color");

        seleccionado.setText("jLabel7");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(64, 64, 64)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel5)
                    .addComponent(jLabel2)
                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 587, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGroup(jPanel1Layout.createSequentialGroup()
                            .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                .addComponent(jButton2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(año, javax.swing.GroupLayout.DEFAULT_SIZE, 190, Short.MAX_VALUE))
                            .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addGroup(jPanel1Layout.createSequentialGroup()
                                    .addGap(10, 10, 10)
                                    .addComponent(jButton3, javax.swing.GroupLayout.PREFERRED_SIZE, 190, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                    .addComponent(jButton4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                .addGroup(jPanel1Layout.createSequentialGroup()
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addGroup(jPanel1Layout.createSequentialGroup()
                                            .addComponent(jLabel6)
                                            .addGap(0, 0, Short.MAX_VALUE))
                                        .addGroup(jPanel1Layout.createSequentialGroup()
                                            .addComponent(color, javax.swing.GroupLayout.PREFERRED_SIZE, 190, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                            .addComponent(jButton1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))))
                        .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                            .addComponent(serial, javax.swing.GroupLayout.PREFERRED_SIZE, 190, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                            .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                .addGroup(jPanel1Layout.createSequentialGroup()
                                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addComponent(marca, javax.swing.GroupLayout.PREFERRED_SIZE, 190, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addComponent(jLabel3))
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addComponent(jLabel4)
                                        .addComponent(modeloForm, javax.swing.GroupLayout.PREFERRED_SIZE, 187, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                .addGroup(jPanel1Layout.createSequentialGroup()
                                    .addGap(50, 50, 50)
                                    .addComponent(jLabel1)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(seleccionado)
                                    .addGap(39, 39, 39))))))
                .addContainerGap(64, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(seleccionado))
                .addGap(15, 15, 15)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(jLabel3)
                    .addComponent(jLabel4))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(serial, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(marca, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(modeloForm, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(24, 24, 24)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel5)
                    .addComponent(jLabel6))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(año, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(color, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButton1))
                .addGap(29, 29, 29)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButton2)
                    .addComponent(jButton3)
                    .addComponent(jButton4))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 53, Short.MAX_VALUE)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 180, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(25, 25, 25))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 7, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jButton1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jButton1MouseClicked
       limpiarCampos();
    }//GEN-LAST:event_jButton1MouseClicked

    private void jTable1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTable1MouseClicked
        llenarCampos();
    }//GEN-LAST:event_jTable1MouseClicked

    private void serialActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_serialActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_serialActionPerformed

    private void jButton2MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jButton2MouseClicked
        agregarCoche();
    }//GEN-LAST:event_jButton2MouseClicked

    private void jButton3MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jButton3MouseClicked
        boolean existe = buscarCoche(serialSeleccionado);
        if(validarDatosLlenos()) {
            if(existe) {  
                String serial_c = serial.getText();
                String marca_c = marca.getText();
                String modelo_c = modeloForm.getText();
                String año_c = año.getText();
                String color_c = color.getText();

                reader = new XMLReader("coches.xml");
                if(reader.updateRow(serial_c, marca_c, modelo_c, año_c, color_c, serialSeleccionado)) {  
                    JOptionPane.showMessageDialog(ventanaAlerta, "Coche modificado correctamente");
                    llenarTabla(reader.getContent());
                    limpiarCampos();             
                } else {
                     JOptionPane.showMessageDialog(ventanaAlerta, "No se ha podido modificar el coche");
                }

            } else {
                JOptionPane.showMessageDialog(ventanaAlerta, "No existe un coche con ese serial");
            }
        } else {
            JOptionPane.showMessageDialog(ventanaAlerta, "Debe llenar todos los campos");
        }
       
    }//GEN-LAST:event_jButton3MouseClicked

    private void jButton4MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jButton4MouseClicked
        if(!serialSeleccionado.equals("")) {
            if(eliminarCoche(serialSeleccionado)) {
                JOptionPane.showMessageDialog(ventanaAlerta, "Coche eliminado correctamente");
                llenarTabla(reader.getContent());
                limpiarCampos();             
            } else {
                 JOptionPane.showMessageDialog(ventanaAlerta, "No se ha podido eliminar el coche");
            }
        } else {
            JOptionPane.showMessageDialog(ventanaAlerta, "No ha seleccioando el coche que desea eliminar");
        }
        
    }//GEN-LAST:event_jButton4MouseClicked

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(CochesForm.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(CochesForm.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(CochesForm.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(CochesForm.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new CochesForm().setVisible(true);
            }
        }); 
        
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JTextField año;
    private javax.swing.JTextField color;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton3;
    private javax.swing.JButton jButton4;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable jTable1;
    private javax.swing.JTextField jTextField8;
    private javax.swing.JTextField marca;
    private javax.swing.JTextField modeloForm;
    private javax.swing.JLabel seleccionado;
    private javax.swing.JTextField serial;
    // End of variables declaration//GEN-END:variables
}
