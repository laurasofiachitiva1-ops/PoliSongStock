package Vista.Vendedor;

import Vista.Vendedor.AñadirArtista;
import Vista.Vendedor.Vendedor;
import Modelo.AutorDAO;
import Modelo.Cancion;
import Modelo.CancionDAO;
import Modelo.Disco_mp3;
import Modelo.Disco_mp3DAO;
import Modelo.Disco_vinilo;
import Modelo.Disco_viniloDAO;
import Modelo.Sesion;
import java.awt.Image;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.util.List;
import javax.swing.ButtonGroup;
import javax.swing.DefaultComboBoxModel;
import javax.swing.ImageIcon;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.filechooser.FileNameExtensionFilter;

public class AgregarProducto extends javax.swing.JFrame {

    Cancion ca = new Cancion();
    CancionDAO caD = new CancionDAO();

    Disco_viniloDAO dvD = new Disco_viniloDAO();
    Disco_vinilo dv = new Disco_vinilo();

    Disco_mp3DAO dmD = new Disco_mp3DAO();
    Disco_mp3 dm = new Disco_mp3();

    String Ruta = "";


    private static final java.util.logging.Logger logger = java.util.logging.Logger.getLogger(AgregarProducto.class.getName());

    public AgregarProducto() {
        initComponents();
        
        // Cursor tipo mano en el label de salir
        imgSalir.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));

        // Cursor tipo mano en los botones añadir artista
        btnAnadirArtista.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnAnadirArtista1.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        // Cursor tipo mano en el boton subir imagen
        btnSubirImagen.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        // Cursor tipo mano en el boton agregar album
        btnAgregarAlbum.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        // Cursor tipo mano en el boton asociar cancion
        btnAsociarCan.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        // Cursor tipo mano en el boton agregar cancion
        btnAgregarCancion.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));

        cargarArtistas();

        // Agrupar radios
        ButtonGroup grupo = new ButtonGroup();
        grupo.add(rdbmp3);
        grupo.add(rdbvinilo);
    }

    private void cargarArtistas() {
        AutorDAO dao = new AutorDAO();
        List<String> lista = dao.obtenerAutores();

        cmbArtistaVinl.removeAllItems();
        cmbArtistaCan.removeAllItems();

        for (String artista : lista) {
            cmbArtistaVinl.addItem(artista);
            cmbArtistaCan.addItem(artista);
        }

        cargarDuracion();
    }

    private DefaultComboBoxModel<String> minutosModel = new DefaultComboBoxModel<>();
    private DefaultComboBoxModel<String> segundosModel = new DefaultComboBoxModel<>();

    private void cargarDuracion() {
        minutosModel.removeAllElements();
        segundosModel.removeAllElements();

        // Placeholder
        minutosModel.addElement("(minutos)");
        segundosModel.addElement("(segundos)");

        //Para minutos
        for (int i = 0; i <= 60; i++) {
            minutosModel.addElement(String.format("%02d", i));
        }
        //Para segundos
        for (int i = 0; i < 60; i++) {
            segundosModel.addElement(String.format("%02d", i));
        }

        cmbMin.setModel(minutosModel);
        cmbSeg.setModel(segundosModel);

        cmbMin.setSelectedIndex(0);
        cmbSeg.setSelectedIndex(0);
    }

    private void limpiarCamposCancion() {

        txtNombreCancion.setText("");
        txtGeneroCancion.setText("");
        txtTamanioCan.setText("");
        txtCalidadCan.setText("");
        txtPrecioCancion.setText("");

        // Restaurar combos de tiempo
        cmbMin.setSelectedIndex(0);
        cmbSeg.setSelectedIndex(0);

        // Restaurar combo de artista
        if (cmbArtistaCan.getItemCount() > 0) {
            cmbArtistaCan.setSelectedIndex(0);
        }
    }

    private void limpiarCamposDisco() {

        txtIDAlbum.setText("");
        txtNomAlbum.setText("");
        txtGeneroAlb.setText("");
        txtAnioAlb.setText("");
        txtInventario.setText("");
        txtPrecioAlb.setText("");

        // Restaurar combo de artista
        if (cmbArtistaVinl.getItemCount() > 0) {
            cmbArtistaVinl.setSelectedIndex(0);
        }

        // Limpiar imagen
        imagenDisco.setIcon(null);
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        lbVendedor = new javax.swing.JLabel();
        jSeparator1 = new javax.swing.JSeparator();
        lbVendedor1 = new javax.swing.JLabel();
        lbVendedor2 = new javax.swing.JLabel();
        lbVendedor3 = new javax.swing.JLabel();
        lbVendedor4 = new javax.swing.JLabel();
        lbVendedor5 = new javax.swing.JLabel();
        txtNomAlbum = new javax.swing.JTextField();
        txtGeneroAlb = new javax.swing.JTextField();
        txtAnioAlb = new javax.swing.JTextField();
        txtPrecioAlb = new javax.swing.JTextField();
        btnAgregarAlbum = new javax.swing.JButton();
        jSeparator2 = new javax.swing.JSeparator();
        lbVendedor6 = new javax.swing.JLabel();
        lbVendedor7 = new javax.swing.JLabel();
        cmbArtistaVinl = new javax.swing.JComboBox<>();
        btnSubirImagen = new javax.swing.JButton();
        btnAsociarCan = new javax.swing.JButton();
        lbVendedor8 = new javax.swing.JLabel();
        txtIDAlbum = new javax.swing.JTextField();
        lbVendedor9 = new javax.swing.JLabel();
        txtInventario = new javax.swing.JTextField();
        lbVendedor10 = new javax.swing.JLabel();
        lbVendedor11 = new javax.swing.JLabel();
        lbVendedor12 = new javax.swing.JLabel();
        lbVendedor14 = new javax.swing.JLabel();
        txtNombreCancion = new javax.swing.JTextField();
        txtGeneroCancion = new javax.swing.JTextField();
        btnAnadirArtista = new javax.swing.JButton();
        lbVendedor16 = new javax.swing.JLabel();
        cmbArtistaCan = new javax.swing.JComboBox<>();
        btnAnadirArtista1 = new javax.swing.JButton();
        jPanel1 = new javax.swing.JPanel();
        cmbMin = new javax.swing.JComboBox<>();
        cmbSeg = new javax.swing.JComboBox<>();
        imagenDisco = new javax.swing.JLabel();
        rdbvinilo = new javax.swing.JRadioButton();
        rdbmp3 = new javax.swing.JRadioButton();
        txtTamanioCan = new javax.swing.JTextField();
        txtCalidadCan = new javax.swing.JTextField();
        txtPrecioCancion = new javax.swing.JTextField();
        btnAgregarCancion = new javax.swing.JButton();
        lbVendedor17 = new javax.swing.JLabel();
        lbVendedor13 = new javax.swing.JLabel();
        lbVendedor15 = new javax.swing.JLabel();
        imgSalir = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        lbVendedor.setFont(new java.awt.Font("Segoe UI", 1, 24)); // NOI18N
        lbVendedor.setText("Agregar nuevo disco");
        getContentPane().add(lbVendedor, new org.netbeans.lib.awtextra.AbsoluteConstraints(12, 6, -1, -1));

        jSeparator1.setForeground(new java.awt.Color(51, 51, 51));
        getContentPane().add(jSeparator1, new org.netbeans.lib.awtextra.AbsoluteConstraints(12, 44, 710, 10));

        lbVendedor1.setFont(new java.awt.Font("Segoe UI", 0, 16)); // NOI18N
        lbVendedor1.setText("ID álbum");
        getContentPane().add(lbVendedor1, new org.netbeans.lib.awtextra.AbsoluteConstraints(12, 60, -1, -1));

        lbVendedor2.setFont(new java.awt.Font("Segoe UI", 0, 16)); // NOI18N
        lbVendedor2.setText("Artista");
        getContentPane().add(lbVendedor2, new org.netbeans.lib.awtextra.AbsoluteConstraints(12, 94, -1, -1));

        lbVendedor3.setFont(new java.awt.Font("Segoe UI", 0, 16)); // NOI18N
        lbVendedor3.setText("Precio");
        getContentPane().add(lbVendedor3, new org.netbeans.lib.awtextra.AbsoluteConstraints(12, 221, -1, -1));

        lbVendedor4.setFont(new java.awt.Font("Segoe UI", 0, 16)); // NOI18N
        lbVendedor4.setText("Inventario (para vinilo)");
        getContentPane().add(lbVendedor4, new org.netbeans.lib.awtextra.AbsoluteConstraints(12, 253, -1, -1));

        lbVendedor5.setFont(new java.awt.Font("Segoe UI", 0, 16)); // NOI18N
        lbVendedor5.setText("Año");
        getContentPane().add(lbVendedor5, new org.netbeans.lib.awtextra.AbsoluteConstraints(12, 189, -1, -1));

        txtNomAlbum.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        getContentPane().add(txtNomAlbum, new org.netbeans.lib.awtextra.AbsoluteConstraints(174, 125, 269, -1));

        txtGeneroAlb.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        getContentPane().add(txtGeneroAlb, new org.netbeans.lib.awtextra.AbsoluteConstraints(174, 156, 269, -1));

        txtAnioAlb.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        getContentPane().add(txtAnioAlb, new org.netbeans.lib.awtextra.AbsoluteConstraints(174, 188, 269, -1));

        txtPrecioAlb.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        getContentPane().add(txtPrecioAlb, new org.netbeans.lib.awtextra.AbsoluteConstraints(174, 220, 269, -1));

        btnAgregarAlbum.setBackground(new java.awt.Color(204, 204, 204));
        btnAgregarAlbum.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        btnAgregarAlbum.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Img/mas 15px.png"))); // NOI18N
        btnAgregarAlbum.setText("Agregar Álbum");
        btnAgregarAlbum.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAgregarAlbumActionPerformed(evt);
            }
        });
        getContentPane().add(btnAgregarAlbum, new org.netbeans.lib.awtextra.AbsoluteConstraints(490, 260, 200, -1));

        jSeparator2.setForeground(new java.awt.Color(51, 51, 51));
        getContentPane().add(jSeparator2, new org.netbeans.lib.awtextra.AbsoluteConstraints(12, 326, 710, 11));

        lbVendedor6.setFont(new java.awt.Font("Segoe UI", 1, 24)); // NOI18N
        lbVendedor6.setText("Agregar nueva canción (mp3)");
        getContentPane().add(lbVendedor6, new org.netbeans.lib.awtextra.AbsoluteConstraints(12, 343, -1, -1));

        lbVendedor7.setFont(new java.awt.Font("Segoe UI", 0, 16)); // NOI18N
        lbVendedor7.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Img/logo musica grande.png"))); // NOI18N
        getContentPane().add(lbVendedor7, new org.netbeans.lib.awtextra.AbsoluteConstraints(490, 380, 220, 280));

        cmbArtistaVinl.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        cmbArtistaVinl.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));
        cmbArtistaVinl.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cmbArtistaVinlActionPerformed(evt);
            }
        });
        getContentPane().add(cmbArtistaVinl, new org.netbeans.lib.awtextra.AbsoluteConstraints(174, 92, 129, -1));

        btnSubirImagen.setBackground(new java.awt.Color(153, 153, 153));
        btnSubirImagen.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        btnSubirImagen.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Img/subir.png"))); // NOI18N
        btnSubirImagen.setText("Subir imagen");
        btnSubirImagen.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSubirImagenActionPerformed(evt);
            }
        });
        getContentPane().add(btnSubirImagen, new org.netbeans.lib.awtextra.AbsoluteConstraints(490, 60, 200, -1));

        btnAsociarCan.setBackground(new java.awt.Color(204, 204, 204));
        btnAsociarCan.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        btnAsociarCan.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Img/letras-de-canciones.png"))); // NOI18N
        btnAsociarCan.setText("Asociar canciones");
        btnAsociarCan.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAsociarCanActionPerformed(evt);
            }
        });
        getContentPane().add(btnAsociarCan, new org.netbeans.lib.awtextra.AbsoluteConstraints(490, 293, 200, -1));

        lbVendedor8.setFont(new java.awt.Font("Segoe UI", 0, 16)); // NOI18N
        lbVendedor8.setText("Nombre álbum");
        getContentPane().add(lbVendedor8, new org.netbeans.lib.awtextra.AbsoluteConstraints(12, 128, -1, -1));

        txtIDAlbum.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        getContentPane().add(txtIDAlbum, new org.netbeans.lib.awtextra.AbsoluteConstraints(174, 60, 269, -1));

        lbVendedor9.setFont(new java.awt.Font("Segoe UI", 0, 16)); // NOI18N
        lbVendedor9.setText("Género");
        getContentPane().add(lbVendedor9, new org.netbeans.lib.awtextra.AbsoluteConstraints(12, 160, -1, -1));

        txtInventario.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        getContentPane().add(txtInventario, new org.netbeans.lib.awtextra.AbsoluteConstraints(174, 252, 269, -1));

        lbVendedor10.setFont(new java.awt.Font("Segoe UI", 0, 16)); // NOI18N
        lbVendedor10.setText("Seleccione formato del disco:");
        getContentPane().add(lbVendedor10, new org.netbeans.lib.awtextra.AbsoluteConstraints(12, 296, -1, -1));

        lbVendedor11.setFont(new java.awt.Font("Segoe UI", 0, 16)); // NOI18N
        lbVendedor11.setText("Género");
        getContentPane().add(lbVendedor11, new org.netbeans.lib.awtextra.AbsoluteConstraints(12, 452, -1, -1));

        lbVendedor12.setFont(new java.awt.Font("Segoe UI", 0, 16)); // NOI18N
        lbVendedor12.setText("Artista");
        getContentPane().add(lbVendedor12, new org.netbeans.lib.awtextra.AbsoluteConstraints(12, 420, -1, -1));

        lbVendedor14.setFont(new java.awt.Font("Segoe UI", 0, 16)); // NOI18N
        lbVendedor14.setText("Duración");
        getContentPane().add(lbVendedor14, new org.netbeans.lib.awtextra.AbsoluteConstraints(12, 484, -1, -1));

        txtNombreCancion.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        getContentPane().add(txtNombreCancion, new org.netbeans.lib.awtextra.AbsoluteConstraints(172, 387, 269, -1));

        txtGeneroCancion.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        getContentPane().add(txtGeneroCancion, new org.netbeans.lib.awtextra.AbsoluteConstraints(172, 451, 269, -1));

        btnAnadirArtista.setBackground(new java.awt.Color(204, 204, 204));
        btnAnadirArtista.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        btnAnadirArtista.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Img/mas 15px.png"))); // NOI18N
        btnAnadirArtista.setText("Añadir artista");
        btnAnadirArtista.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAnadirArtistaActionPerformed(evt);
            }
        });
        getContentPane().add(btnAnadirArtista, new org.netbeans.lib.awtextra.AbsoluteConstraints(303, 92, 140, -1));

        lbVendedor16.setFont(new java.awt.Font("Segoe UI", 0, 16)); // NOI18N
        lbVendedor16.setText("Nombe");
        getContentPane().add(lbVendedor16, new org.netbeans.lib.awtextra.AbsoluteConstraints(12, 388, -1, -1));

        cmbArtistaCan.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        cmbArtistaCan.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));
        cmbArtistaCan.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cmbArtistaCanActionPerformed(evt);
            }
        });
        getContentPane().add(cmbArtistaCan, new org.netbeans.lib.awtextra.AbsoluteConstraints(170, 420, 129, -1));

        btnAnadirArtista1.setBackground(new java.awt.Color(204, 204, 204));
        btnAnadirArtista1.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        btnAnadirArtista1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Img/mas 15px.png"))); // NOI18N
        btnAnadirArtista1.setText("Añadir artista");
        btnAnadirArtista1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAnadirArtista1ActionPerformed(evt);
            }
        });
        getContentPane().add(btnAnadirArtista1, new org.netbeans.lib.awtextra.AbsoluteConstraints(302, 420, 140, -1));

        jPanel1.setBackground(new java.awt.Color(89, 89, 89));

        cmbMin.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        cmbMin.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));
        cmbMin.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cmbMinActionPerformed(evt);
            }
        });

        cmbSeg.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        cmbSeg.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));
        cmbSeg.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cmbSegActionPerformed(evt);
            }
        });

        rdbvinilo.setText("Vinilo");

        rdbmp3.setText("Mp3");

        txtTamanioCan.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N

        txtCalidadCan.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N

        txtPrecioCancion.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N

        btnAgregarCancion.setBackground(new java.awt.Color(204, 204, 204));
        btnAgregarCancion.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        btnAgregarCancion.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Img/mas 15px.png"))); // NOI18N
        btnAgregarCancion.setText("Agregar canción");
        btnAgregarCancion.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAgregarCancionActionPerformed(evt);
            }
        });

        lbVendedor17.setFont(new java.awt.Font("Segoe UI", 0, 16)); // NOI18N
        lbVendedor17.setText("Precio");

        lbVendedor13.setFont(new java.awt.Font("Segoe UI", 0, 16)); // NOI18N
        lbVendedor13.setText("Calidad (Kbps)");

        lbVendedor15.setFont(new java.awt.Font("Segoe UI", 0, 16)); // NOI18N
        lbVendedor15.setText("Tamaño (MB)");

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
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(262, 262, 262)
                        .addComponent(rdbvinilo)
                        .addGap(48, 48, 48)
                        .addComponent(rdbmp3))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(13, 13, 13)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(lbVendedor15)
                            .addComponent(lbVendedor17)
                            .addComponent(lbVendedor13))
                        .addGap(59, 59, 59)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(txtTamanioCan, javax.swing.GroupLayout.PREFERRED_SIZE, 269, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(cmbMin, javax.swing.GroupLayout.PREFERRED_SIZE, 129, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(cmbSeg, javax.swing.GroupLayout.PREFERRED_SIZE, 129, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(txtCalidadCan, javax.swing.GroupLayout.PREFERRED_SIZE, 269, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txtPrecioCancion, javax.swing.GroupLayout.PREFERRED_SIZE, 269, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(btnAgregarCancion, javax.swing.GroupLayout.PREFERRED_SIZE, 429, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(309, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                        .addComponent(imagenDisco, javax.swing.GroupLayout.PREFERRED_SIZE, 166, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(77, 77, 77))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                        .addComponent(imgSalir)
                        .addGap(30, 30, 30))))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(imgSalir, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(47, 47, 47)
                .addComponent(imagenDisco, javax.swing.GroupLayout.PREFERRED_SIZE, 166, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(44, 44, 44)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(rdbmp3)
                    .addComponent(rdbvinilo))
                .addGap(170, 170, 170)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(cmbSeg, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(cmbMin, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtTamanioCan, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lbVendedor15))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtCalidadCan, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lbVendedor13))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtPrecioCancion, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lbVendedor17))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 28, Short.MAX_VALUE)
                .addComponent(btnAgregarCancion)
                .addGap(23, 23, 23))
        );

        getContentPane().add(jPanel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 750, 690));

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnSubirImagenActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSubirImagenActionPerformed
        JFileChooser fileChooser = new JFileChooser();
        FileNameExtensionFilter extensionFilter = new FileNameExtensionFilter("JPG, PNG & GIF", "jpg", "png", "gif");
        fileChooser.setFileFilter(extensionFilter);

        if (fileChooser.showOpenDialog(this) == JFileChooser.APPROVE_OPTION) {
            Ruta = fileChooser.getSelectedFile().getAbsolutePath();
            Image mImagen = new ImageIcon(Ruta).getImage();
            ImageIcon micono = new ImageIcon(mImagen.getScaledInstance(imagenDisco.getWidth(), imagenDisco.getHeight(), 0));
            imagenDisco.setIcon(micono);
        }
    }//GEN-LAST:event_btnSubirImagenActionPerformed

    private void btnAnadirArtistaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAnadirArtistaActionPerformed
        AñadirArtista art = new AñadirArtista();
        art.setVisible(true);
        art.setLocationRelativeTo(null);
        art.setResizable(false);
        dispose();

    }//GEN-LAST:event_btnAnadirArtistaActionPerformed

    private void cmbArtistaVinlActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cmbArtistaVinlActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_cmbArtistaVinlActionPerformed

    private void cmbArtistaCanActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cmbArtistaCanActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_cmbArtistaCanActionPerformed

    private void btnAnadirArtista1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAnadirArtista1ActionPerformed
        AñadirArtista art = new AñadirArtista();
        art.setVisible(true);
        art.setLocationRelativeTo(null);
        art.setResizable(false);
        dispose();
    }//GEN-LAST:event_btnAnadirArtista1ActionPerformed

    private void cmbMinActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cmbMinActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_cmbMinActionPerformed

    private void cmbSegActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cmbSegActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_cmbSegActionPerformed

    private void btnAgregarCancionActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAgregarCancionActionPerformed
        String nombre = txtNombreCancion.getText().trim();
        String genero = txtGeneroCancion.getText().trim();
        String tamano = txtTamanioCan.getText().trim();
        String calidad = txtCalidadCan.getText().trim();
        String precio = txtPrecioCancion.getText().trim();
        String min = (String) cmbMin.getSelectedItem();
        String seg = (String) cmbSeg.getSelectedItem();
        String artista = (String) cmbArtistaCan.getSelectedItem();

        // Validar si NO hay artistas cargados
        if (cmbArtistaCan.getItemCount() == 0 || artista == null) {
            JOptionPane.showMessageDialog(null, "Debe crear un artista antes de registrar una canción.");
            return;
        }

        if (nombre.isEmpty() || genero.isEmpty() || tamano.isEmpty() || calidad.isEmpty() || precio.isEmpty() || artista == null
                || min.equals("(minutos)") || seg.equals("(segundos)")) {

            JOptionPane.showMessageDialog(null, "Por favor llene todos los campos.");
            return;
        }
        try {
            ca.setTamano_mb(Double.parseDouble(tamano));
            ca.setCalidad_kbps(Integer.parseInt(calidad));
            ca.setPrecio(Double.parseDouble(precio));
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(null, "Los campos de precio, tamaño o calidad deben ser numéricos.");
            return;
        }

        // Construir duración en formato texto
        String duracion = String.format("%02d:%02d", Integer.parseInt(min), Integer.parseInt(seg));
        ca.setDuracion(duracion);
        ca.setNombre(nombre);
        ca.setGenero(genero);
        ca.setId_vendedor(Sesion.getIdVendedor());

        //Obener id autor por nombre
        int idAutor = new AutorDAO().obtenerIdPorNombre(artista);
        ca.setId_autor(idAutor);

        boolean registroExitoso = caD.CrearCancion(ca);

        if (registroExitoso) {
            JOptionPane.showMessageDialog(null, "Canción creada correctamente");
            limpiarCamposCancion();
        } else {
            JOptionPane.showMessageDialog(null, "Error al crear la canción. Intente nuevamente.");
        }
    }//GEN-LAST:event_btnAgregarCancionActionPerformed

    //Método para pasar la ruta a un arreglo de bytes
    private byte[] getImagen(String Ruta) {
        File imagen = new File(Ruta);
        try {
            byte[] icono = new byte[(int) imagen.length()];
            InputStream input = new FileInputStream(imagen);
            input.read(icono);
            return icono;
        } catch (Exception e) {
            return null;
        }
    }

    private void btnAgregarAlbumActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAgregarAlbumActionPerformed
        String id = txtIDAlbum.getText().trim();
        String nombre = txtNomAlbum.getText().trim();
        String genero = txtGeneroAlb.getText().trim();
        String anio = txtAnioAlb.getText().trim();
        String precio = txtPrecioAlb.getText().trim();
        String inventario = txtInventario.getText().trim();
        String artista = (String) cmbArtistaVinl.getSelectedItem();

        if (rdbvinilo.isSelected()) {

            // Validar si NO hay artistas cargados
            if (cmbArtistaCan.getItemCount() == 0 || artista == null) {
                JOptionPane.showMessageDialog(null, "Debe crear un artista antes de registrar una disco.");
                return;
            }

            if (id.isEmpty() || nombre.isEmpty() || genero.isEmpty() || anio.isEmpty() || precio.isEmpty() || inventario.isEmpty()) {

                JOptionPane.showMessageDialog(null, "Por favor llene todos los campos.");
                return;
            }
            try {
                dv.setId_disco_vinilo(Integer.parseInt(id));
                dv.setCantidad(Integer.parseInt(inventario));
                dv.setPrecio(Double.parseDouble(precio));

            } catch (NumberFormatException e) {
                JOptionPane.showMessageDialog(null, "Los campos de ID, precio o inventario deben ser numéricos.");
                return;
            }
            // ---- VALIDACIÓN DEL AÑO ----
            int anioSalida;

            try {
                anioSalida = Integer.parseInt(anio);
            } catch (NumberFormatException e) {
                JOptionPane.showMessageDialog(null, "El año debe ser numérico.");
                return;
            }

            int anioActual = java.time.Year.now().getValue();

            if (anio.length() != 4 || anioSalida < 1900 || anioSalida > anioActual) {
                JOptionPane.showMessageDialog(null, "Ingrese un año válido entre 1900 y " + anioActual + ".");
                return;
            }

            dv.setAnio_salida(anioSalida);

            dv.setNombre(nombre);
            dv.setGenero(genero);
            dv.setId_vendedor(Sesion.getIdVendedor());

            //Obener id autor por nombre
            int idAutor = new AutorDAO().obtenerIdPorNombre(artista);
            dv.setId_autor(idAutor);

            // Validación: imagen no subida
            if (Ruta == null || Ruta.isEmpty() || imagenDisco.getIcon() == null) {
                int opcion = JOptionPane.showConfirmDialog(
                        null,
                        "No ha subido ninguna imagen.\n¿Desea continuar sin imagen?",
                        "Confirmación",
                        JOptionPane.YES_NO_OPTION,
                        JOptionPane.WARNING_MESSAGE
                );

                if (opcion == JOptionPane.NO_OPTION) {
                    return;
                }

                dv.setImagen(null);
            } else {
                dv.setImagen(getImagen(Ruta));
            }

            boolean registroExitoso = dvD.CrearVinilo(dv);
            if (registroExitoso) {
                JOptionPane.showMessageDialog(null,
                        "Disco vinilo creado correctamente");
                limpiarCamposDisco();
            } else {
                JOptionPane.showMessageDialog(null,
                        "Error al crear el disco vinilo. Intente nuevamente.");
            }

        } else if (rdbmp3.isSelected()) {
            // Validar si NO hay artistas cargados
            if (cmbArtistaCan.getItemCount() == 0 || artista == null) {
                JOptionPane.showMessageDialog(null, "Debe crear un artista antes de registrar un disco.");
                return;
            }

            if (id.isEmpty() || nombre.isEmpty() || genero.isEmpty() || anio.isEmpty() || precio.isEmpty()) {

                JOptionPane.showMessageDialog(null, "Por favor llene todos los campos.");
                return;
            }
            try {
                dm.setId_disco_mp3(Integer.parseInt(id));
                dm.setPrecio(Double.parseDouble(precio));

            } catch (NumberFormatException e) {
                JOptionPane.showMessageDialog(null, "Los campos de ID o precio deben ser numéricos.");
                return;
            }
            // ---- VALIDACIÓN DEL AÑO ----
            int anioSalida;

            try {
                anioSalida = Integer.parseInt(anio);
            } catch (NumberFormatException e) {
                JOptionPane.showMessageDialog(null, "El año debe ser numérico.");
                return;
            }

            int anioActual = java.time.Year.now().getValue();

            if (anio.length() != 4 || anioSalida < 1900 || anioSalida > anioActual) {
                JOptionPane.showMessageDialog(null, "Ingrese un año válido entre 1900 y " + anioActual + ".");
                return;
            }

            dm.setAnio_salida(anioSalida);

            dm.setNombre(nombre);
            dm.setGenero(genero);
            dm.setId_vendedor(Sesion.getIdVendedor());

            //Obener id autor por nombre
            int idAutor = new AutorDAO().obtenerIdPorNombre(artista);
            dm.setId_autor(idAutor);

            // Validación: imagen no subida
            if (Ruta == null || Ruta.isEmpty() || imagenDisco.getIcon() == null) {
                int opcion = JOptionPane.showConfirmDialog(
                        null,
                        "No ha subido ninguna imagen.\n¿Desea continuar sin imagen?",
                        "Confirmación",
                        JOptionPane.YES_NO_OPTION,
                        JOptionPane.WARNING_MESSAGE
                );

                if (opcion == JOptionPane.NO_OPTION) {
                    return;
                }
                dm.setImagen(null);
            } else {
                dm.setImagen(getImagen(Ruta));
            }
            boolean registroExitoso = dmD.CrearMp3(dm);
            if (registroExitoso) {
                JOptionPane.showMessageDialog(null,
                        "Disco Mp3 creado correctamente");
                limpiarCamposDisco();
            } else {
                JOptionPane.showMessageDialog(null,
                        "Error al crear el disco Mp3. Intente nuevamente.");
            }
        } else {
            JOptionPane.showMessageDialog(null,
                    "Debe de seleccionar el formato del disco.");
        }
    }//GEN-LAST:event_btnAgregarAlbumActionPerformed

    private void btnAsociarCanActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAsociarCanActionPerformed
        AsociarCanciones can = new AsociarCanciones();
        can.setVisible(true);
        can.setLocationRelativeTo(null);
        can.setResizable(false);
        dispose();
    }//GEN-LAST:event_btnAsociarCanActionPerformed

    private void imgSalirMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_imgSalirMouseClicked

        Vendedor v = new Vendedor();
        v.setVisible(true);
        v.setVisible(true);
        v.setLocationRelativeTo(null);
        v.setResizable(false);
        dispose();
    }//GEN-LAST:event_imgSalirMouseClicked

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
        java.awt.EventQueue.invokeLater(() -> new AgregarProducto().setVisible(true));
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnAgregarAlbum;
    private javax.swing.JButton btnAgregarCancion;
    private javax.swing.JButton btnAnadirArtista;
    private javax.swing.JButton btnAnadirArtista1;
    private javax.swing.JButton btnAsociarCan;
    private javax.swing.JButton btnSubirImagen;
    private javax.swing.JComboBox<String> cmbArtistaCan;
    private javax.swing.JComboBox<String> cmbArtistaVinl;
    private javax.swing.JComboBox<String> cmbMin;
    private javax.swing.JComboBox<String> cmbSeg;
    private javax.swing.JLabel imagenDisco;
    private javax.swing.JLabel imgSalir;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JSeparator jSeparator1;
    private javax.swing.JSeparator jSeparator2;
    private javax.swing.JLabel lbVendedor;
    private javax.swing.JLabel lbVendedor1;
    private javax.swing.JLabel lbVendedor10;
    private javax.swing.JLabel lbVendedor11;
    private javax.swing.JLabel lbVendedor12;
    private javax.swing.JLabel lbVendedor13;
    private javax.swing.JLabel lbVendedor14;
    private javax.swing.JLabel lbVendedor15;
    private javax.swing.JLabel lbVendedor16;
    private javax.swing.JLabel lbVendedor17;
    private javax.swing.JLabel lbVendedor2;
    private javax.swing.JLabel lbVendedor3;
    private javax.swing.JLabel lbVendedor4;
    private javax.swing.JLabel lbVendedor5;
    private javax.swing.JLabel lbVendedor6;
    private javax.swing.JLabel lbVendedor7;
    private javax.swing.JLabel lbVendedor8;
    private javax.swing.JLabel lbVendedor9;
    private javax.swing.JRadioButton rdbmp3;
    private javax.swing.JRadioButton rdbvinilo;
    private javax.swing.JTextField txtAnioAlb;
    private javax.swing.JTextField txtCalidadCan;
    private javax.swing.JTextField txtGeneroAlb;
    private javax.swing.JTextField txtGeneroCancion;
    private javax.swing.JTextField txtIDAlbum;
    private javax.swing.JTextField txtInventario;
    private javax.swing.JTextField txtNomAlbum;
    private javax.swing.JTextField txtNombreCancion;
    private javax.swing.JTextField txtPrecioAlb;
    private javax.swing.JTextField txtPrecioCancion;
    private javax.swing.JTextField txtTamanioCan;
    // End of variables declaration//GEN-END:variables
}
