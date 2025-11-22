package Vista.Vendedor;

import Modelo.Autor;
import Modelo.AutorDAO;
import javax.swing.JOptionPane;

public class AñadirArtista extends javax.swing.JFrame {
    
    private static final java.util.logging.Logger logger = java.util.logging.Logger.getLogger(AñadirArtista.class.getName());
    
    Autor au = new Autor();
    AutorDAO auD = new AutorDAO();
    
    public AñadirArtista() {
        initComponents();
        
        // Cursor tipo mano en los botones añadir artista
        btnAnadirArtista.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        // Cursor tipo mano en el label de salir
        imgSalir.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        lbVendedor = new javax.swing.JLabel();
        jSeparator1 = new javax.swing.JSeparator();
        lbVendedor1 = new javax.swing.JLabel();
        lbVendedor2 = new javax.swing.JLabel();
        lbVendedor8 = new javax.swing.JLabel();
        txtNacionalidad = new javax.swing.JTextField();
        btnAnadirArtista = new javax.swing.JButton();
        txtNombre = new javax.swing.JTextField();
        jdcFecha = new com.toedter.calendar.JDateChooser();
        imgSalir = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jPanel1.setBackground(new java.awt.Color(89, 89, 89));

        lbVendedor.setFont(new java.awt.Font("Segoe UI", 1, 24)); // NOI18N
        lbVendedor.setText("Agregar nuevo artista");

        jSeparator1.setForeground(new java.awt.Color(51, 51, 51));

        lbVendedor1.setFont(new java.awt.Font("Segoe UI", 0, 16)); // NOI18N
        lbVendedor1.setText("Nombre");

        lbVendedor2.setFont(new java.awt.Font("Segoe UI", 0, 16)); // NOI18N
        lbVendedor2.setText("Fecha de nacimiento");

        lbVendedor8.setFont(new java.awt.Font("Segoe UI", 0, 16)); // NOI18N
        lbVendedor8.setText("Nacionalidad");

        txtNacionalidad.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N

        btnAnadirArtista.setBackground(new java.awt.Color(204, 204, 204));
        btnAnadirArtista.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        btnAnadirArtista.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Img/mas 15px.png"))); // NOI18N
        btnAnadirArtista.setText("Añadir artista");
        btnAnadirArtista.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAnadirArtistaActionPerformed(evt);
            }
        });

        txtNombre.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N

        imgSalir.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Img/logo salir.png"))); // NOI18N
        imgSalir.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                imgSalirMouseClicked(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(12, 12, 12)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(lbVendedor)
                    .addComponent(jSeparator1, javax.swing.GroupLayout.PREFERRED_SIZE, 385, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                        .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel1Layout.createSequentialGroup()
                            .addGap(115, 115, 115)
                            .addComponent(btnAnadirArtista, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 65, Short.MAX_VALUE)
                            .addComponent(imgSalir))
                        .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel1Layout.createSequentialGroup()
                            .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(lbVendedor1)
                                .addComponent(lbVendedor8)
                                .addComponent(lbVendedor2))
                            .addGap(29, 29, 29)
                            .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                .addComponent(txtNacionalidad)
                                .addComponent(txtNombre, javax.swing.GroupLayout.DEFAULT_SIZE, 191, Short.MAX_VALUE)
                                .addComponent(jdcFecha, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))))
                .addContainerGap(23, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(22, 22, 22)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(imgSalir, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(lbVendedor)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jSeparator1, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(6, 6, 6)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(lbVendedor1)
                            .addComponent(txtNombre, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(lbVendedor2)
                            .addComponent(jdcFecha, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(txtNacionalidad, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(lbVendedor8))
                        .addGap(18, 18, 18)
                        .addComponent(btnAnadirArtista, javax.swing.GroupLayout.PREFERRED_SIZE, 58, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(27, Short.MAX_VALUE))
        );

        getContentPane().add(jPanel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 420, 270));

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnAnadirArtistaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAnadirArtistaActionPerformed
        String nombre = txtNombre.getText().trim();
        java.util.Date fechaSeleccionada = jdcFecha.getDate();
        String nacionalidad = txtNacionalidad.getText().trim();

        if (nombre.isEmpty() || fechaSeleccionada == null || nacionalidad.isEmpty()) {
            JOptionPane.showMessageDialog(null, "Por favor, llene todos los campos");
            return;
        }
        
        // Convertir java.util.Date → java.sql.Date
        java.sql.Date fechaSql = new java.sql.Date(fechaSeleccionada.getTime());

        au.setNombre(nombre);
        au.setFecha_nacimiento(fechaSql);
        au.setNacionalidad(nacionalidad);

        boolean registroExitoso = auD.CrearAutor(au);

        if (registroExitoso) {
            JOptionPane.showMessageDialog(null, "Autor creado correctamente");
            AgregarProducto prod = new AgregarProducto();
            prod.setVisible(true);
            prod.setLocationRelativeTo(null);
            prod.setResizable(false);
            dispose();
        } else {
            JOptionPane.showMessageDialog(null, "Error al crear el autor. Intente nuevamente.");
        }
    }//GEN-LAST:event_btnAnadirArtistaActionPerformed

    private void imgSalirMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_imgSalirMouseClicked
        AgregarProducto prod = new AgregarProducto();
        prod.setVisible(true);
        prod.setLocationRelativeTo(null);
        prod.setResizable(false);
        dispose();
    }//GEN-LAST:event_imgSalirMouseClicked

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
        } catch (ReflectiveOperationException | javax.swing.UnsupportedLookAndFeelException ex) {
            logger.log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(() -> new AñadirArtista().setVisible(true));
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnAnadirArtista;
    private javax.swing.JLabel imgSalir;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JSeparator jSeparator1;
    private com.toedter.calendar.JDateChooser jdcFecha;
    private javax.swing.JLabel lbVendedor;
    private javax.swing.JLabel lbVendedor1;
    private javax.swing.JLabel lbVendedor2;
    private javax.swing.JLabel lbVendedor8;
    private javax.swing.JTextField txtNacionalidad;
    private javax.swing.JTextField txtNombre;
    // End of variables declaration//GEN-END:variables
}
