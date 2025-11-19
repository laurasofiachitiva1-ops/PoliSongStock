package Vista;

import Modelo.CompradorDAO;
import Modelo.Comprador;
import Modelo.Vendedor;
import Modelo.VendedorDAO;
import javax.swing.ButtonGroup;
import javax.swing.JOptionPane;

public class CreateAccount extends javax.swing.JFrame {

    Comprador com = new Comprador();
    CompradorDAO comD = new CompradorDAO();
    Vendedor ven = new Vendedor();
    VendedorDAO venD = new VendedorDAO();

    public CreateAccount() {
        initComponents();

        // Cursor tipo mano en el label de salir
        imgSalir.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));

        // Cursor tipo mano en el boton registrar
        btnRegistrarse.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));

        // Agrupar radios
        ButtonGroup grupo = new ButtonGroup();
        grupo.add(rdbComprador);
        grupo.add(rdbVendedor);

    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jpfondocreate = new javax.swing.JPanel();
        jpCrearCuenta = new javax.swing.JPanel();
        lbUsuarioc = new javax.swing.JLabel();
        jlCrearCuenta = new javax.swing.JLabel();
        lbContrasenac = new javax.swing.JLabel();
        txtContrasenaC = new javax.swing.JPasswordField();
        txtDireccionC = new javax.swing.JTextField();
        btnRegistrarse = new javax.swing.JButton();
        lbemail = new javax.swing.JLabel();
        txtEmail = new javax.swing.JTextField();
        lbtipocuenta = new javax.swing.JLabel();
        txtConfContrasena = new javax.swing.JPasswordField();
        lbConContrasena = new javax.swing.JLabel();
        rdbVendedor = new javax.swing.JRadioButton();
        rdbComprador = new javax.swing.JRadioButton();
        lbdireccion = new javax.swing.JLabel();
        txtUsuarioC = new javax.swing.JTextField();
        imLogoPolic = new javax.swing.JLabel();
        imgSalir = new javax.swing.JLabel();

        setMinimumSize(new java.awt.Dimension(710, 400));
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jpfondocreate.setBackground(new java.awt.Color(51, 51, 51));
        jpfondocreate.setPreferredSize(new java.awt.Dimension(710, 400));

        jpCrearCuenta.setBackground(new java.awt.Color(89, 89, 89));
        jpCrearCuenta.setPreferredSize(new java.awt.Dimension(302, 341));

        lbUsuarioc.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        lbUsuarioc.setText("Nombre");

        jlCrearCuenta.setFont(new java.awt.Font("Segoe UI", 1, 24)); // NOI18N
        jlCrearCuenta.setText("Crear cuenta");

        lbContrasenac.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        lbContrasenac.setText("Contraseña");

        txtContrasenaC.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtContrasenaCActionPerformed(evt);
            }
        });

        txtDireccionC.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtDireccionCActionPerformed(evt);
            }
        });

        btnRegistrarse.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        btnRegistrarse.setText("→ Registrarse");
        btnRegistrarse.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnRegistrarseActionPerformed(evt);
            }
        });

        lbemail.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        lbemail.setText("Email");

        lbtipocuenta.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        lbtipocuenta.setText("Tipo de cuenta:");

        lbConContrasena.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        lbConContrasena.setText("Confirmar contraseña");

        rdbVendedor.setText("Vendedor");
        rdbVendedor.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                rdbVendedorActionPerformed(evt);
            }
        });

        rdbComprador.setText("Comprador");

        lbdireccion.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        lbdireccion.setText("Dirección");

        txtUsuarioC.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtUsuarioCActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jpCrearCuentaLayout = new javax.swing.GroupLayout(jpCrearCuenta);
        jpCrearCuenta.setLayout(jpCrearCuentaLayout);
        jpCrearCuentaLayout.setHorizontalGroup(
            jpCrearCuentaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jpCrearCuentaLayout.createSequentialGroup()
                .addGroup(jpCrearCuentaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jpCrearCuentaLayout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jlCrearCuenta))
                    .addGroup(jpCrearCuentaLayout.createSequentialGroup()
                        .addGap(30, 30, 30)
                        .addGroup(jpCrearCuentaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                            .addGroup(jpCrearCuentaLayout.createSequentialGroup()
                                .addGroup(jpCrearCuentaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(rdbComprador)
                                    .addComponent(rdbVendedor))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(btnRegistrarse))
                            .addGroup(jpCrearCuentaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(txtContrasenaC, javax.swing.GroupLayout.PREFERRED_SIZE, 232, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(lbContrasenac)
                                .addComponent(lbUsuarioc)
                                .addComponent(txtDireccionC, javax.swing.GroupLayout.PREFERRED_SIZE, 232, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(lbemail)
                                .addComponent(txtEmail, javax.swing.GroupLayout.PREFERRED_SIZE, 232, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(lbConContrasena)
                                .addComponent(lbtipocuenta)
                                .addComponent(txtConfContrasena, javax.swing.GroupLayout.PREFERRED_SIZE, 232, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(lbdireccion)
                                .addComponent(txtUsuarioC, javax.swing.GroupLayout.PREFERRED_SIZE, 232, javax.swing.GroupLayout.PREFERRED_SIZE)))))
                .addContainerGap(40, Short.MAX_VALUE))
        );
        jpCrearCuentaLayout.setVerticalGroup(
            jpCrearCuentaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jpCrearCuentaLayout.createSequentialGroup()
                .addComponent(jlCrearCuenta)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(lbUsuarioc)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(txtUsuarioC, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(lbemail)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(txtEmail, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(lbdireccion)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(txtDireccionC, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(lbContrasenac)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(txtContrasenaC, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(lbConContrasena)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(txtConfContrasena, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(lbtipocuenta)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jpCrearCuentaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jpCrearCuentaLayout.createSequentialGroup()
                        .addComponent(rdbComprador)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(rdbVendedor))
                    .addComponent(btnRegistrarse, javax.swing.GroupLayout.PREFERRED_SIZE, 48, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(40, Short.MAX_VALUE))
        );

        imLogoPolic.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Img/Logo PoliSong.png"))); // NOI18N

        imgSalir.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Img/logo salir.png"))); // NOI18N
        imgSalir.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                imgSalirMouseClicked(evt);
            }
        });

        javax.swing.GroupLayout jpfondocreateLayout = new javax.swing.GroupLayout(jpfondocreate);
        jpfondocreate.setLayout(jpfondocreateLayout);
        jpfondocreateLayout.setHorizontalGroup(
            jpfondocreateLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jpfondocreateLayout.createSequentialGroup()
                .addGroup(jpfondocreateLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jpfondocreateLayout.createSequentialGroup()
                        .addGap(25, 25, 25)
                        .addComponent(imLogoPolic))
                    .addComponent(imgSalir))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jpCrearCuenta, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(41, Short.MAX_VALUE))
        );
        jpfondocreateLayout.setVerticalGroup(
            jpfondocreateLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jpfondocreateLayout.createSequentialGroup()
                .addComponent(imgSalir, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 66, Short.MAX_VALUE)
                .addComponent(imLogoPolic, javax.swing.GroupLayout.PREFERRED_SIZE, 318, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(40, 40, 40))
            .addGroup(jpfondocreateLayout.createSequentialGroup()
                .addGap(23, 23, 23)
                .addComponent(jpCrearCuenta, javax.swing.GroupLayout.PREFERRED_SIZE, 422, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        getContentPane().add(jpfondocreate, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 710, 460));
    }// </editor-fold>//GEN-END:initComponents

    private void txtDireccionCActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtDireccionCActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtDireccionCActionPerformed

    private void imgSalirMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_imgSalirMouseClicked
        Login log = new Login();
        log.setVisible(true);
        dispose();
    }//GEN-LAST:event_imgSalirMouseClicked

    private void btnRegistrarseActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnRegistrarseActionPerformed

        String usuario = txtDireccionC.getText().trim();
        String email = txtEmail.getText().trim();
        String contrasena = String.valueOf(txtContrasenaC.getPassword());
        String confirmar = String.valueOf(txtConfContrasena.getPassword());
        String direccion = txtDireccionC.getText().trim();

        if (usuario.isEmpty() || email.isEmpty() || contrasena.isEmpty() || confirmar.isEmpty() || direccion.isEmpty()) {
            JOptionPane.showMessageDialog(null, "Por favor, llene todos los campos");
            return;
        }

        if (!rdbComprador.isSelected() && !rdbVendedor.isSelected()) {
            JOptionPane.showMessageDialog(null, "Seleccione un tipo de cuenta");
            return;
        }

        if (!contrasena.equals(confirmar)) {
            JOptionPane.showMessageDialog(null, "Las contraseñas no coinciden");
            return;
        }

        // Verificar el correo en AMBAS tablas
        boolean correoExisteEnComprador = comD.existeCorreo(email);
        boolean correoExisteEnVendedor = venD.existeCorreo(email);

        if (correoExisteEnComprador || correoExisteEnVendedor) {
            JOptionPane.showMessageDialog(null, "Ya existe un usuario registrado con este correo.");
            return;
        }

        boolean registroExitoso = false;

        if (rdbComprador.isSelected()) {
            com.setNombre(usuario);
            com.setCorreo(email);
            com.setPassword(contrasena);
            com.setDireccion(direccion);

            registroExitoso = comD.CrearComprador(com);

        } else if (rdbVendedor.isSelected()) {
            ven.setNombre(usuario);
            ven.setCorreo(email);
            ven.setPassword(contrasena);
            ven.setDireccion(direccion);

            registroExitoso = venD.CrearVendedor(ven);
        }

        if (registroExitoso) {
            JOptionPane.showMessageDialog(null, "Usuario creado correctamente");
            Login log = new Login();
            log.setVisible(true);
            dispose();
        } else {
            JOptionPane.showMessageDialog(null, "Error al crear el usuario. Intente nuevamente.");
        }
    }//GEN-LAST:event_btnRegistrarseActionPerformed

    private void txtContrasenaCActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtContrasenaCActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtContrasenaCActionPerformed

    private void rdbVendedorActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_rdbVendedorActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_rdbVendedorActionPerformed

    private void txtUsuarioCActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtUsuarioCActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtUsuarioCActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnRegistrarse;
    private javax.swing.JLabel imLogoPolic;
    private javax.swing.JLabel imgSalir;
    private javax.swing.JLabel jlCrearCuenta;
    private javax.swing.JPanel jpCrearCuenta;
    private javax.swing.JPanel jpfondocreate;
    private javax.swing.JLabel lbConContrasena;
    private javax.swing.JLabel lbContrasenac;
    private javax.swing.JLabel lbUsuarioc;
    private javax.swing.JLabel lbdireccion;
    private javax.swing.JLabel lbemail;
    private javax.swing.JLabel lbtipocuenta;
    private javax.swing.JRadioButton rdbComprador;
    private javax.swing.JRadioButton rdbVendedor;
    private javax.swing.JPasswordField txtConfContrasena;
    private javax.swing.JPasswordField txtContrasenaC;
    private javax.swing.JTextField txtDireccionC;
    private javax.swing.JTextField txtEmail;
    private javax.swing.JTextField txtUsuarioC;
    // End of variables declaration//GEN-END:variables
}
