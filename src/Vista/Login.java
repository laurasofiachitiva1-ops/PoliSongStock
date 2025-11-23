package Vista;

import Modelo.CompradorDAO;
import Modelo.Sesion;
import Modelo.VendedorDAO;
import Vista.Comprador.Comprador;
import Vista.Vendedor.Vendedor;
import javax.swing.JOptionPane;


public class Login extends javax.swing.JFrame {
    
    private static final java.util.logging.Logger logger = java.util.logging.Logger.getLogger(Login.class.getName());

    CompradorDAO comD = new CompradorDAO();
    VendedorDAO venD = new VendedorDAO();
    
    public Login() {
        initComponents();
        this.setLocationRelativeTo(null);
        this.setResizable(false);
        
        // Cursor tipo mano en el botón Entrar
        btnEntrar.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));

        // ----- HOVER PARA lbCrearCuenta -----
        lbCrearCuenta.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        lbCrearCuenta.addMouseListener(new java.awt.event.MouseAdapter() {
            @Override
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                lbCrearCuenta.setForeground(new java.awt.Color(0, 200, 255)); // Color al hacer hover
            }
            @Override
            public void mouseExited(java.awt.event.MouseEvent evt) {
                lbCrearCuenta.setForeground(new java.awt.Color(0, 153, 255)); // Color original
            }
        });
    }
    public void validar(){
        String correo = txtUsuario.getText();
        String password = String.valueOf(txtContrasena.getPassword());
        
        
        if (correo.equals("") || password.equals("")) {
            JOptionPane.showMessageDialog(null, "Por favor, llene los campos");
            return;
        }

        // ----- 1. BUSCAR EN TABLA COMPRADOR -----
        Modelo.Comprador c = comD.logC(correo, password);
        

        if (c.getId_comprador() != 0) {
            // SI ES COMPRADOR
            JOptionPane.showMessageDialog(null, "Bienvenido comprador: " + c.getNombre());
            
            Sesion.setIdComprador(c.getId_comprador());//Guardar el id del comprador
  
            Comprador comprador = new Comprador ();
            comprador.setVisible(true);
            comprador.setLocationRelativeTo(null);
            comprador.setResizable(false);
            dispose();
            return;
        }

        // ----- 2. BUSCAR EN TABLA VENDEDOR -----
        Modelo.Vendedor v = venD.logV(correo, password);

        if (v.getId_vendedor() != 0) {
            // SI ES VENDEDOR
            JOptionPane.showMessageDialog(null, "Bienvenido vendedor: " + v.getNombre());
            
            Sesion.setIdVendedor(v.getId_vendedor());//Guardar el id del vendedor

            Vendedor vendedor = new Vendedor();
            vendedor.setVisible(true);
            vendedor.setLocationRelativeTo(null);
            vendedor.setResizable(false);
            dispose();
            return;
        }

        // ----- 3. SI NO EXISTE EN NINGUNA TABLA -----
        JOptionPane.showMessageDialog(null, "Correo o contraseña incorrecta");
    }
    
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jpFondo = new javax.swing.JPanel();
        jpLogin = new javax.swing.JPanel();
        lbUsuario = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        lbContrasena = new javax.swing.JLabel();
        txtContrasena = new javax.swing.JPasswordField();
        txtUsuario = new javax.swing.JTextField();
        btnEntrar = new javax.swing.JButton();
        lb01 = new javax.swing.JLabel();
        lbCrearCuenta = new javax.swing.JLabel();
        imLogoPoli = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jpFondo.setBackground(new java.awt.Color(51, 51, 51));

        jpLogin.setBackground(new java.awt.Color(89, 89, 89));

        lbUsuario.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        lbUsuario.setText("Usuario (Email)");

        jLabel2.setFont(new java.awt.Font("Segoe UI", 1, 24)); // NOI18N
        jLabel2.setText("Iniciar Sesión");

        lbContrasena.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        lbContrasena.setText("Contraseña");

        btnEntrar.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        btnEntrar.setText("→ Entrar");
        btnEntrar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnEntrarActionPerformed(evt);
            }
        });

        lb01.setFont(new java.awt.Font("Segoe UI", 0, 10)); // NOI18N
        lb01.setText("¿No tiene cuenta?");

        lbCrearCuenta.setFont(new java.awt.Font("Segoe UI", 0, 10)); // NOI18N
        lbCrearCuenta.setForeground(new java.awt.Color(0, 153, 255));
        lbCrearCuenta.setText("Crear cuenta PoliSonStock");
        lbCrearCuenta.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                lbCrearCuentaMouseClicked(evt);
            }
        });

        javax.swing.GroupLayout jpLoginLayout = new javax.swing.GroupLayout(jpLogin);
        jpLogin.setLayout(jpLoginLayout);
        jpLoginLayout.setHorizontalGroup(
            jpLoginLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jpLoginLayout.createSequentialGroup()
                .addGroup(jpLoginLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jpLoginLayout.createSequentialGroup()
                        .addGap(30, 30, 30)
                        .addGroup(jpLoginLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(txtContrasena, javax.swing.GroupLayout.PREFERRED_SIZE, 232, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(lbContrasena)
                            .addComponent(jLabel2)
                            .addComponent(txtUsuario, javax.swing.GroupLayout.PREFERRED_SIZE, 232, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(lbUsuario, javax.swing.GroupLayout.PREFERRED_SIZE, 124, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(jpLoginLayout.createSequentialGroup()
                        .addGap(112, 112, 112)
                        .addGroup(jpLoginLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(btnEntrar)
                            .addComponent(lb01)))
                    .addGroup(jpLoginLayout.createSequentialGroup()
                        .addGap(96, 96, 96)
                        .addComponent(lbCrearCuenta)))
                .addContainerGap(40, Short.MAX_VALUE))
        );
        jpLoginLayout.setVerticalGroup(
            jpLoginLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jpLoginLayout.createSequentialGroup()
                .addGap(31, 31, 31)
                .addComponent(jLabel2)
                .addGap(24, 24, 24)
                .addComponent(lbUsuario)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(txtUsuario, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(lbContrasena)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(txtContrasena, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(40, 40, 40)
                .addComponent(btnEntrar, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(lb01)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(lbCrearCuenta)
                .addContainerGap(17, Short.MAX_VALUE))
        );

        imLogoPoli.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Img/Logo PoliSong.png"))); // NOI18N

        javax.swing.GroupLayout jpFondoLayout = new javax.swing.GroupLayout(jpFondo);
        jpFondo.setLayout(jpFondoLayout);
        jpFondoLayout.setHorizontalGroup(
            jpFondoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jpFondoLayout.createSequentialGroup()
                .addGap(25, 25, 25)
                .addComponent(imLogoPoli)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jpLogin, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(41, Short.MAX_VALUE))
        );
        jpFondoLayout.setVerticalGroup(
            jpFondoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jpFondoLayout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(imLogoPoli, javax.swing.GroupLayout.PREFERRED_SIZE, 318, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(40, 40, 40))
            .addGroup(jpFondoLayout.createSequentialGroup()
                .addGap(27, 27, 27)
                .addComponent(jpLogin, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(32, Short.MAX_VALUE))
        );

        getContentPane().add(jpFondo, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 710, 400));

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnEntrarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnEntrarActionPerformed
        validar();
    }//GEN-LAST:event_btnEntrarActionPerformed

    private void lbCrearCuentaMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lbCrearCuentaMouseClicked
        CreateAccount cre = new CreateAccount();
        cre.pack();
        cre.setVisible(true);
        cre.setLocationRelativeTo(null);
        cre.setResizable(false); 
        dispose();
    }//GEN-LAST:event_lbCrearCuentaMouseClicked

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
        } catch (ReflectiveOperationException | javax.swing.UnsupportedLookAndFeelException ex) {
            logger.log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(() -> new Login().setVisible(true));
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnEntrar;
    private javax.swing.JLabel imLogoPoli;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JPanel jpFondo;
    private javax.swing.JPanel jpLogin;
    private javax.swing.JLabel lb01;
    private javax.swing.JLabel lbContrasena;
    private javax.swing.JLabel lbCrearCuenta;
    private javax.swing.JLabel lbUsuario;
    private javax.swing.JPasswordField txtContrasena;
    private javax.swing.JTextField txtUsuario;
    // End of variables declaration//GEN-END:variables
}
