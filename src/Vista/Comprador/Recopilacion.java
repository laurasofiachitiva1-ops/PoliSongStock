package Vista.Comprador;

import Modelo.Cancion;
import Modelo.CancionDAO;
import Modelo.Canciones_listas;
import Modelo.Canciones_listasDAO;
import Modelo.Lista_reproduccion;
import Modelo.Lista_reproduccionDAO;
import Modelo.Sesion;
import java.util.List;
import javax.swing.DefaultListModel;
import javax.swing.JOptionPane;

/**
 *
 * @author PC
 */
public class Recopilacion extends javax.swing.JFrame {

    private static final java.util.logging.Logger logger = java.util.logging.Logger.getLogger(Recopilacion.class.getName());

    private int idLista;
    private Comprador padre;
    private Lista_reproduccion listaActual;

    public Recopilacion(int idLista, Comprador padre) {
        this.padre = padre;
        initComponents();
        this.idLista = idLista;
        cargarDatosLista();
        btnEliminar.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnAgregar.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnEditar.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        lbVendedor = new javax.swing.JLabel();
        lbVendedor2 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        jLista = new javax.swing.JList<>();
        jSeparator1 = new javax.swing.JSeparator();
        lbNombre = new javax.swing.JLabel();
        btnAgregar = new javax.swing.JButton();
        btnEditar = new javax.swing.JButton();
        btnEliminar = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jPanel1.setBackground(new java.awt.Color(89, 89, 89));

        lbVendedor.setFont(new java.awt.Font("Segoe UI", 1, 24)); // NOI18N
        lbVendedor.setText("Recopilación");

        lbVendedor2.setFont(new java.awt.Font("Segoe UI", 0, 16)); // NOI18N
        lbVendedor2.setText("Canciones disponibles:");

        jLista.setBackground(new java.awt.Color(89, 89, 89));
        jLista.setModel(new javax.swing.AbstractListModel<String>() {
            String[] strings = { "Item 1", "Item 2", "Item 3", "Item 4", "Item 5" };
            public int getSize() { return strings.length; }
            public String getElementAt(int i) { return strings[i]; }
        });
        jLista.setSelectionBackground(new java.awt.Color(51, 51, 51));
        jScrollPane1.setViewportView(jLista);

        jSeparator1.setForeground(new java.awt.Color(51, 51, 51));

        lbNombre.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        lbNombre.setText("texto");

        btnAgregar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Img/logo mas.png"))); // NOI18N
        btnAgregar.setText("Agregar recopilación");
        btnAgregar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAgregarActionPerformed(evt);
            }
        });

        btnEditar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Img/editar.png"))); // NOI18N
        btnEditar.setText("Editar Recopilación");
        btnEditar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnEditarActionPerformed(evt);
            }
        });

        btnEliminar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Img/eliminar.png"))); // NOI18N
        btnEliminar.setText("Eliminar recopilacion");
        btnEliminar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnEliminarActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(11, 11, 11)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(lbNombre)
                    .addComponent(jSeparator1, javax.swing.GroupLayout.PREFERRED_SIZE, 371, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 240, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(btnAgregar, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(btnEditar, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(btnEliminar, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                    .addComponent(lbVendedor)
                    .addComponent(lbVendedor2))
                .addGap(0, 14, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(17, 17, 17)
                .addComponent(lbVendedor)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jSeparator1, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(2, 2, 2)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(lbNombre)
                        .addGap(18, 18, 18)
                        .addComponent(lbVendedor2)
                        .addGap(18, 18, 18)
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(btnAgregar)
                        .addGap(18, 18, 18)
                        .addComponent(btnEliminar)
                        .addGap(17, 17, 17)
                        .addComponent(btnEditar)
                        .addGap(6, 6, 6)))
                .addContainerGap(34, Short.MAX_VALUE))
        );

        getContentPane().add(jPanel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 460, 330));

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnEditarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnEditarActionPerformed
        // Verifica que listaActual esté cargada (por si acaso)
        if (listaActual == null) {
            JOptionPane.showMessageDialog(this, "Error: Lista no cargada.");
            return;
        }

        // Obtén el nombre y la privacidad de listaActual
        String nombre = listaActual.getNombre();
        // Asume que Lista_reproduccion tiene un método isPublica() que devuelve boolean (ajusta si es diferente)
        String publica = listaActual.isPublica() ? "Sí" : "No";

        // Crea EditarReco con los datos
        EditarReco ed = new EditarReco(padre, idLista, nombre, publica);
        ed.setVisible(true);
        ed.setLocationRelativeTo(null);
        ed.setResizable(false);
        if (padre != null) {
            padre.cargarListasReproduccion();
        }
        this.dispose();
    }//GEN-LAST:event_btnEditarActionPerformed

    private void btnEliminarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnEliminarActionPerformed

        int confirm = JOptionPane.showConfirmDialog(
                this,
                "¿Estás seguro de que quieres eliminar esta recopilación? Esta acción no se puede deshacer.",
                "Confirmar eliminación",
                JOptionPane.YES_NO_OPTION,
                JOptionPane.WARNING_MESSAGE
        );

        if (confirm == JOptionPane.YES_OPTION) {
            // Llamar al DAO para eliminar la lista
            Lista_reproduccionDAO dao = new Lista_reproduccionDAO();
            if (dao.eliminarListaReproduccion(idLista)) {
                JOptionPane.showMessageDialog(this, "Recopilación eliminada exitosamente.");
                if (padre != null) {
                    padre.cargarListasReproduccion();
                }
                this.dispose();
            } else {
                JOptionPane.showMessageDialog(this, "Error al eliminar la recopilación.", "Error", JOptionPane.ERROR_MESSAGE);
            }
        }
    }//GEN-LAST:event_btnEliminarActionPerformed

    private void btnAgregarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAgregarActionPerformed

        // Obtener el ID del comprador logueado
        int idCompradorLogueado = Sesion.getIdComprador();

        // Crear una nueva lista privada para el usuario logueado, copiando el nombre de la lista pública
        Lista_reproduccion nuevaLista = new Lista_reproduccion();
        nuevaLista.setNombre("Copia de " + listaActual.getNombre());  // Nombre con prefijo para indicar copia
        nuevaLista.setId_comprador(idCompradorLogueado);
        nuevaLista.setPublica("No");  // Privada
        nuevaLista.setFecha_creacion(new java.text.SimpleDateFormat("yyyy-MM-dd").format(new java.util.Date()));  // Fecha actual

        // Insertar la nueva lista en la BD
        Lista_reproduccionDAO listaDao = new Lista_reproduccionDAO();
        if (!listaDao.insertarListaReproduccion(nuevaLista)) {
            JOptionPane.showMessageDialog(this, "Error al crear la nueva lista privada.");
            return;
        }

        // Obtener el ID de la nueva lista (generado por la BD)
        int idNuevaLista = nuevaLista.getId_lista();

        // Copiar las canciones de la lista pública a la nueva lista privada
        Canciones_listasDAO cancionesDao = new Canciones_listasDAO();
        List<Canciones_listas> cancionesOriginales = cancionesDao.listarCancionesPorLista(idLista);

        for (Canciones_listas cancionOriginal : cancionesOriginales) {
            Canciones_listas nuevaCancionLista = new Canciones_listas();
            nuevaCancionLista.setId_lista(idNuevaLista);
            nuevaCancionLista.setId_cancion(cancionOriginal.getId_cancion());

            if (!cancionesDao.insertarCancionLista(nuevaCancionLista)) {
                JOptionPane.showMessageDialog(this, "Error al copiar una canción a la nueva lista.");
                // Opcional: Podrías continuar o detener, pero para simplicidad, continúa
            }
        }

        // Recargar las listas en la ventana padre
        if (padre != null) {
            padre.cargarListasReproduccion();
        }

        // Mostrar mensaje de éxito y cerrar la ventana
        JOptionPane.showMessageDialog(this, "Lista pública agregada como privada exitosamente.");
        this.dispose();

    }//GEN-LAST:event_btnAgregarActionPerformed

    // Método para cargar los datos de la lista
    private void cargarDatosLista() {
        Lista_reproduccionDAO listaDao = new Lista_reproduccionDAO();
        Lista_reproduccion lista = listaDao.buscarPorIdLista(idLista);
        this.listaActual = lista;
        if (lista == null) {
            JOptionPane.showMessageDialog(this, "Lista no encontrada.");
            this.dispose();
            return;
        }
        // Establecer el nombre de la lista
        lbNombre.setText(lista.getNombre());
        // Verificar si la lista pertenece al usuario logueado
        int idCompradorLogueado = Sesion.getIdComprador();
        if (lista.getId_comprador() == idCompradorLogueado) {
            btnAgregar.setVisible(false);
            btnEditar.setVisible(true);
            btnEliminar.setVisible(true);
        } else {
            btnAgregar.setVisible(true);
            btnEditar.setVisible(false);// Ocultar si no es del usuario
            btnEliminar.setVisible(false);
        }
        // Cargar las canciones de la lista
        Canciones_listasDAO cancionesDao = new Canciones_listasDAO();
        List<Canciones_listas> cancionesEnLista = cancionesDao.listarCancionesPorLista(idLista);
        DefaultListModel<String> modeloLista = new DefaultListModel<>();
        CancionDAO cancionDao = new CancionDAO();
        for (Canciones_listas item : cancionesEnLista) {
            Cancion cancion = cancionDao.obtenerPorId(item.getId_cancion());
            if (cancion != null) {
                modeloLista.addElement(cancion.getNombre());
            } else {
                modeloLista.addElement("Canción no encontrada (ID: " + item.getId_cancion() + ")");
            }
        }
        jLista.setModel(modeloLista);
    }

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
        //java.awt.EventQueue.invokeLater(() -> new Recopilacion().setVisible(true));
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnAgregar;
    private javax.swing.JButton btnEditar;
    private javax.swing.JButton btnEliminar;
    private javax.swing.JList<String> jLista;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JSeparator jSeparator1;
    private javax.swing.JLabel lbNombre;
    private javax.swing.JLabel lbVendedor;
    private javax.swing.JLabel lbVendedor2;
    // End of variables declaration//GEN-END:variables
}
