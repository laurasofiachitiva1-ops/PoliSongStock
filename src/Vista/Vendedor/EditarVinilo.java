package Vista.Vendedor;

import Vista.Vendedor.Vendedor;
import Modelo.AutorDAO;
import Modelo.Cancion;
import Modelo.CancionDAO;
import Modelo.Cancion_vinilo;
import Modelo.Cancion_viniloDAO;
import Modelo.Disco_vinilo;
import Modelo.Disco_viniloDAO;
import Modelo.Sesion;
import java.awt.Color;
import java.awt.Image;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.util.List;
import javax.swing.ImageIcon;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import static javax.swing.SwingConstants.CENTER;
import javax.swing.filechooser.FileNameExtensionFilter;

public class EditarVinilo extends javax.swing.JFrame {

    String Ruta = "";

    private static final java.util.logging.Logger logger = java.util.logging.Logger.getLogger(EditarVinilo.class.getName());

    private int idViniloEditar;

    public EditarVinilo(int idVinilo) {
        initComponents();
        idViniloEditar = idVinilo;

        // Primero cargar los artistas
        cargarArtistas();

        // Cargar datos del vinilo
        cargarDatosVinilo();

        // Cargar canciones del vinilo en jLista
        cargarCancionesDelVinilo(idViniloEditar);

        // Cargar canciones 
        cargarCancionesEnTabla();

        // Configurar cursores
        imgSalir.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnSubirImagen.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnEditarVinilo.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));

        // Cursor tipo mano en el botón añadir cancion
        btnAnadirCancion.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        // Cursor tipo mano en el botón añadir cancion
        btnBorrarCancion.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));

        // FORZAR QUE EL BOTÓN EMPECIE OCULTO
        btnBorrarCancion.setVisible(false);

        // COLOR DE FONDO PARA EL HEADER
        javax.swing.table.JTableHeader header = jTable.getTableHeader();
        header.setDefaultRenderer(new javax.swing.table.DefaultTableCellRenderer() {
            @Override
            public java.awt.Component getTableCellRendererComponent(
                    javax.swing.JTable table, Object value, boolean isSelected,
                    boolean hasFocus, int row, int column) {

                java.awt.Component c = super.getTableCellRendererComponent(
                        table, value, isSelected, hasFocus, row, column);
                jTable.getTableHeader().setBackground(new java.awt.Color(89, 89, 89));

                jScrollPane2.getViewport().setBackground(new java.awt.Color(89, 89, 89));
                c.setBackground(new java.awt.Color(89, 89, 89));
                c.setForeground(Color.WHITE);
                setHorizontalAlignment(CENTER);
                return c;
            }
        });

        // MOSTRAR EL BOTON PARA BORRAR LA CANCION DE LA LISTA
        jLista.addListSelectionListener(e -> {
            if (!e.getValueIsAdjusting()) {
                if (jLista.getSelectedIndex() != -1) {
                    btnBorrarCancion.setVisible(true);
                } else {
                    btnBorrarCancion.setVisible(false);
                }
            }
        });

    }

    private void cargarDatosVinilo() {

        Disco_viniloDAO dao = new Disco_viniloDAO();
        Disco_vinilo v = dao.buscarPorIdV(idViniloEditar);

        if (v == null) {
            JOptionPane.showMessageDialog(this, "No se encontró el vinilo.");
            return;
        }

        lbId.setText(String.valueOf(v.getId_disco_vinilo()));
        txtNomAlbum.setText(v.getNombre());
        txtGeneroAlb.setText(v.getGenero());
        txtAnioAlb.setText(String.valueOf(v.getAnio_salida()));
        txtPrecioAlb.setText(String.valueOf(v.getPrecio()));
        txtInventario.setText(String.valueOf(v.getCantidad()));

        // Seleccionar artista por nombre
        cmbArtistaVinl.setSelectedItem(v.getAutorNombre());

        // Cargar imagen si existe
        if (v.getImagen() != null) {
            ImageIcon icono = new ImageIcon(v.getImagen());
            Image img = icono.getImage().getScaledInstance(
                    imagenDisco.getWidth(),
                    imagenDisco.getHeight(),
                    Image.SCALE_SMOOTH
            );
            imagenDisco.setIcon(new ImageIcon(img));
        }
    }

    private void cargarArtistas() {
        AutorDAO dao = new AutorDAO();
        List<String> lista = dao.obtenerAutores();

        cmbArtistaVinl.removeAllItems();

        for (String artista : lista) {
            cmbArtistaVinl.addItem(artista);
        }
    }

    private void cargarCancionesDelVinilo(int idVinilo) {
        Cancion_viniloDAO cavD = new Cancion_viniloDAO();
        List<Cancion> lista = cavD.listarCancionesPorVinilo(idVinilo);

        javax.swing.DefaultListModel<String> modelo = new javax.swing.DefaultListModel<>();

        for (Cancion c : lista) {
            modelo.addElement(
                    c.getId_cancion() + " - " + c.getNombre() + " - " + c.getDuracion()
            );
        }

        jLista.setModel(modelo);
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        lbVendedor = new javax.swing.JLabel();
        jSeparator1 = new javax.swing.JSeparator();
        jPanel1 = new javax.swing.JPanel();
        imagenDisco = new javax.swing.JLabel();
        imgSalir = new javax.swing.JLabel();
        lbId = new javax.swing.JLabel();
        lbVendedor3 = new javax.swing.JLabel();
        txtPrecioAlb = new javax.swing.JTextField();
        lbVendedor5 = new javax.swing.JLabel();
        lbVendedor9 = new javax.swing.JLabel();
        lbVendedor8 = new javax.swing.JLabel();
        lbVendedor2 = new javax.swing.JLabel();
        lbVendedor1 = new javax.swing.JLabel();
        txtAnioAlb = new javax.swing.JTextField();
        txtGeneroAlb = new javax.swing.JTextField();
        txtNomAlbum = new javax.swing.JTextField();
        cmbArtistaVinl = new javax.swing.JComboBox<>();
        jlcan = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        jLista = new javax.swing.JList<>();
        btnBorrarCancion = new javax.swing.JButton();
        jScrollPane2 = new javax.swing.JScrollPane();
        jTable = new javax.swing.JTable();
        lbtitulo2 = new javax.swing.JLabel();
        btnAnadirCancion = new javax.swing.JButton();
        jSeparator2 = new javax.swing.JSeparator();
        jSeparator3 = new javax.swing.JSeparator();
        txtInventario = new javax.swing.JTextField();
        lbVendedor4 = new javax.swing.JLabel();
        btnEditarVinilo = new javax.swing.JButton();
        btnSubirImagen = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        lbVendedor.setFont(new java.awt.Font("Segoe UI", 1, 24)); // NOI18N
        lbVendedor.setText("Editar vinilo");
        getContentPane().add(lbVendedor, new org.netbeans.lib.awtextra.AbsoluteConstraints(12, 6, -1, -1));

        jSeparator1.setForeground(new java.awt.Color(51, 51, 51));
        getContentPane().add(jSeparator1, new org.netbeans.lib.awtextra.AbsoluteConstraints(12, 44, 710, 10));

        jPanel1.setBackground(new java.awt.Color(89, 89, 89));

        imgSalir.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Img/logo salir.png"))); // NOI18N
        imgSalir.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                imgSalirMouseClicked(evt);
            }
        });

        lbId.setFont(new java.awt.Font("Segoe UI", 0, 16)); // NOI18N
        lbId.setText("texto");

        lbVendedor3.setFont(new java.awt.Font("Segoe UI", 0, 16)); // NOI18N
        lbVendedor3.setText("Precio");

        txtPrecioAlb.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N

        lbVendedor5.setFont(new java.awt.Font("Segoe UI", 0, 16)); // NOI18N
        lbVendedor5.setText("Año");

        lbVendedor9.setFont(new java.awt.Font("Segoe UI", 0, 16)); // NOI18N
        lbVendedor9.setText("Género");

        lbVendedor8.setFont(new java.awt.Font("Segoe UI", 0, 16)); // NOI18N
        lbVendedor8.setText("Nombre álbum");

        lbVendedor2.setFont(new java.awt.Font("Segoe UI", 0, 16)); // NOI18N
        lbVendedor2.setText("Artista");

        lbVendedor1.setFont(new java.awt.Font("Segoe UI", 0, 16)); // NOI18N
        lbVendedor1.setText("ID álbum");

        txtAnioAlb.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N

        txtGeneroAlb.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N

        txtNomAlbum.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N

        cmbArtistaVinl.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        cmbArtistaVinl.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));
        cmbArtistaVinl.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cmbArtistaVinlActionPerformed(evt);
            }
        });

        jlcan.setFont(new java.awt.Font("Segoe UI", 0, 16)); // NOI18N
        jlcan.setText("Canciones:");

        jLista.setBackground(new java.awt.Color(89, 89, 89));
        jLista.setModel(new javax.swing.AbstractListModel<String>() {
            String[] strings = { "Item 1", "Item 2", "Item 3", "Item 4", "Item 5" };
            public int getSize() { return strings.length; }
            public String getElementAt(int i) { return strings[i]; }
        });
        jLista.setSelectionBackground(new java.awt.Color(51, 51, 51));
        jScrollPane1.setViewportView(jLista);

        btnBorrarCancion.setBackground(new java.awt.Color(204, 204, 204));
        btnBorrarCancion.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        btnBorrarCancion.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Img/eliminar15px.png"))); // NOI18N
        btnBorrarCancion.setText("Borrar Canción");
        btnBorrarCancion.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnBorrarCancionActionPerformed(evt);
            }
        });

        jTable.setBackground(new java.awt.Color(89, 89, 89));
        jTable.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null}
            },
            new String [] {
                "ID canción", "Autor", "Nombre", "Género", "duración"
            }
        ));
        jTable.setGridColor(new java.awt.Color(89, 89, 89));
        jTable.setSelectionBackground(new java.awt.Color(51, 51, 51));
        jScrollPane2.setViewportView(jTable);

        lbtitulo2.setFont(new java.awt.Font("Segoe UI", 0, 16)); // NOI18N
        lbtitulo2.setText("Canciones disponibles para agregar");

        btnAnadirCancion.setBackground(new java.awt.Color(204, 204, 204));
        btnAnadirCancion.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        btnAnadirCancion.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Img/mas 15px.png"))); // NOI18N
        btnAnadirCancion.setText("Agregar canción");
        btnAnadirCancion.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAnadirCancionActionPerformed(evt);
            }
        });

        jSeparator2.setForeground(new java.awt.Color(51, 51, 51));

        jSeparator3.setForeground(new java.awt.Color(51, 51, 51));

        txtInventario.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N

        lbVendedor4.setFont(new java.awt.Font("Segoe UI", 0, 16)); // NOI18N
        lbVendedor4.setText("Inventario");

        btnEditarVinilo.setBackground(new java.awt.Color(204, 204, 204));
        btnEditarVinilo.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        btnEditarVinilo.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Img/editar15px.png"))); // NOI18N
        btnEditarVinilo.setText("Editar Vinilo");
        btnEditarVinilo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnEditarViniloActionPerformed(evt);
            }
        });

        btnSubirImagen.setBackground(new java.awt.Color(153, 153, 153));
        btnSubirImagen.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        btnSubirImagen.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Img/subir.png"))); // NOI18N
        btnSubirImagen.setText("Subir imagen");
        btnSubirImagen.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSubirImagenActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(21, 21, 21)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(imgSalir)
                        .addGap(30, 30, 30))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(lbtitulo2)
                            .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 667, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addContainerGap(62, Short.MAX_VALUE))))
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(271, 271, 271)
                .addComponent(btnAnadirCancion, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGap(21, 21, 21)
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(lbVendedor3)
                                    .addComponent(lbVendedor5)
                                    .addComponent(lbVendedor9)
                                    .addComponent(lbVendedor8)
                                    .addComponent(lbVendedor2)
                                    .addComponent(lbVendedor1)
                                    .addComponent(lbVendedor4)))
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGap(20, 20, 20)
                                .addComponent(jlcan, javax.swing.GroupLayout.PREFERRED_SIZE, 110, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGap(1, 1, 1)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGap(42, 42, 42)
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                        .addComponent(txtAnioAlb, javax.swing.GroupLayout.PREFERRED_SIZE, 269, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addComponent(txtPrecioAlb, javax.swing.GroupLayout.PREFERRED_SIZE, 269, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addComponent(txtGeneroAlb, javax.swing.GroupLayout.PREFERRED_SIZE, 269, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addComponent(txtNomAlbum, javax.swing.GroupLayout.PREFERRED_SIZE, 269, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addComponent(txtInventario, javax.swing.GroupLayout.PREFERRED_SIZE, 269, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addComponent(cmbArtistaVinl, javax.swing.GroupLayout.PREFERRED_SIZE, 270, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(lbId)))
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGap(41, 41, 41)
                                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 269, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(btnEditarVinilo, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(btnSubirImagen, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(57, 57, 57))
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(jPanel1Layout.createSequentialGroup()
                                        .addGap(63, 63, 63)
                                        .addComponent(imagenDisco, javax.swing.GroupLayout.PREFERRED_SIZE, 166, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addGroup(jPanel1Layout.createSequentialGroup()
                                        .addGap(46, 46, 46)
                                        .addComponent(btnBorrarCancion, javax.swing.GroupLayout.PREFERRED_SIZE, 198, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                .addGap(0, 0, Short.MAX_VALUE))))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jSeparator3)
                            .addComponent(jSeparator2, javax.swing.GroupLayout.Alignment.TRAILING))))
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(imgSalir, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(213, 213, 213))
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(lbVendedor1)
                                .addGap(18, 18, 18)
                                .addComponent(lbVendedor2)
                                .addGap(18, 18, 18)
                                .addComponent(lbVendedor8)
                                .addGap(18, 18, 18)
                                .addComponent(lbVendedor9)
                                .addGap(18, 18, 18)
                                .addComponent(lbVendedor5)
                                .addGap(8, 8, 8))
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(lbId)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(cmbArtistaVinl, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(txtNomAlbum, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(txtGeneroAlb, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(txtAnioAlb, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGap(9, 9, 9)
                                .addComponent(txtPrecioAlb, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(lbVendedor3))))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(btnSubirImagen)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(imagenDisco, javax.swing.GroupLayout.PREFERRED_SIZE, 166, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 8, Short.MAX_VALUE)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lbVendedor4)
                    .addComponent(txtInventario, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnEditarVinilo))
                .addGap(5, 5, 5)
                .addComponent(jSeparator2, javax.swing.GroupLayout.PREFERRED_SIZE, 16, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(btnBorrarCancion, javax.swing.GroupLayout.PREFERRED_SIZE, 73, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 73, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                        .addComponent(jlcan)
                        .addGap(48, 48, 48)))
                .addComponent(jSeparator3, javax.swing.GroupLayout.PREFERRED_SIZE, 15, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(lbtitulo2)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 151, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btnAnadirCancion)
                .addGap(14, 14, 14))
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

    private void cmbArtistaVinlActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cmbArtistaVinlActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_cmbArtistaVinlActionPerformed

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

    private void actualizarVinilo() {
        // ===== OBTENER DATOS DEL FORMULARIO =====
        String nombre = txtNomAlbum.getText().trim();
        String genero = txtGeneroAlb.getText().trim();
        String anioTxt = txtAnioAlb.getText().trim();
        String precioTxt = txtPrecioAlb.getText().trim();
        String inventarioTxt = txtInventario.getText().trim();
        String artista = (String) cmbArtistaVinl.getSelectedItem();

        // ===== OBTENER ORIGINAL DE LA BD =====
        Disco_viniloDAO dao = new Disco_viniloDAO();
        Disco_vinilo original = dao.buscarPorIdV(idViniloEditar);

        if (original == null) {
            JOptionPane.showMessageDialog(this, "Error: el vinilo ya no existe.");
            return;
        }

        // Validar campos vacíos
        if (nombre.isEmpty() || genero.isEmpty() || anioTxt.isEmpty() || inventarioTxt.isEmpty()
                || precioTxt.isEmpty() || artista == null) {
            JOptionPane.showMessageDialog(this, "Por favor llene todos los campos.");
            return;
        }

        // ---- VALIDACIÓN DEL AÑO ----
        int anioSalida;
        try {
            anioSalida = Integer.parseInt(anioTxt);
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(null, "El año debe ser numérico.");
            return;
        }
        int anioActual = java.time.Year.now().getValue();
        if (anioTxt.length() != 4 || anioSalida < 1900 || anioSalida > anioActual) {
            JOptionPane.showMessageDialog(null, "Ingrese un año válido entre 1900 y " + anioActual + ".");
            return;
        }

        // Validar números restantes
        int inventario;
        double precioVal;
        try {
            inventario = Integer.parseInt(inventarioTxt);
            precioVal = Double.parseDouble(precioTxt);
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(this, "Los campos de inventario y precio deben ser numéricos.");
            return;
        }

        // Obtener bytes de imagen
        byte[] nuevaImagenBytes = Ruta.isEmpty() ? null : getImagen(Ruta);
        if (!Ruta.isEmpty() && nuevaImagenBytes == null) {
            JOptionPane.showMessageDialog(this, "Error al cargar la imagen seleccionada. Verifique el archivo.");
            return;
        }

        // ===== VERIFICAR SI NO HAY CAMBIOS =====
        boolean imagenCambiada = (nuevaImagenBytes != null && !java.util.Arrays.equals(nuevaImagenBytes, original.getImagen()))
                || (nuevaImagenBytes == null && original.getImagen() != null && !Ruta.isEmpty());

        boolean sinCambios = java.util.Objects.equals(nombre, original.getNombre())
                && java.util.Objects.equals(genero, original.getGenero())
                && anioSalida == original.getAnio_salida()
                && inventario == original.getCantidad()
                && Math.abs(precioVal - original.getPrecio()) < 0.001
                && java.util.Objects.equals(artista, original.getAutorNombre())
                && !imagenCambiada;

        if (sinCambios) {
            JOptionPane.showMessageDialog(this, "No hay cambios para guardar.");
            return;
        }

        // ===== CREAR OBJETO MODIFICADO =====
        int idAutorNuevo = new AutorDAO().obtenerIdPorNombre(artista);
        Disco_vinilo modificada = new Disco_vinilo();
        modificada.setId_disco_vinilo(idViniloEditar);
        modificada.setNombre(nombre);
        modificada.setGenero(genero);
        modificada.setAnio_salida(anioSalida);
        modificada.setCantidad(inventario);
        modificada.setPrecio(precioVal);
        modificada.setId_autor(idAutorNuevo);
        modificada.setId_vendedor(original.getId_vendedor());
        modificada.setImagen(nuevaImagenBytes != null ? nuevaImagenBytes : original.getImagen());

        // ===== GUARDAR CAMBIOS =====
        boolean ok = dao.modificarVinilo(modificada);
        if (ok) {
            JOptionPane.showMessageDialog(this, "Vinilo modificado correctamente.");
            Ruta = "";
        } else {
            JOptionPane.showMessageDialog(this, "Error al modificar el vinilo.");
        }
    }
    
    private void btnEditarViniloActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnEditarViniloActionPerformed
        actualizarVinilo();
    }//GEN-LAST:event_btnEditarViniloActionPerformed

    private void imgSalirMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_imgSalirMouseClicked

        Vendedor v = new Vendedor();
        v.setVisible(true);
        v.setVisible(true);
        v.setLocationRelativeTo(null);
        v.setResizable(false);
        dispose();
    }//GEN-LAST:event_imgSalirMouseClicked

    private void cargarCancionesEnTabla() {
        int idVendedor = Sesion.getIdVendedor();

        CancionDAO caD = new CancionDAO();
        List<Cancion> lista = caD.listarCancionesPorVendedor(idVendedor);

        String[] columnas = {"ID canción", "Autor", "Nombre", "Género", "Duración"};

        javax.swing.table.DefaultTableModel modelo = new javax.swing.table.DefaultTableModel(columnas, 0);

        for (Cancion c : lista) {
            modelo.addRow(new Object[]{
                c.getId_cancion(),
                c.getAutorNombre(),
                c.getNombre(),
                c.getGenero(),
                c.getDuracion()
            });
        }

        jTable.setModel(modelo);
    }

    private void btnBorrarCancionActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnBorrarCancionActionPerformed
        // 1. Validar selección
        int index = jLista.getSelectedIndex();
        if (index == -1) {
            JOptionPane.showMessageDialog(this, "Seleccione una canción de la lista.");
            return;
        }
        // 2. Obtener ID real de la canción desde el JList
        String textoCancion = jLista.getModel().getElementAt(index);
        int idCancion = Integer.parseInt(textoCancion.split(" - ")[0]);
        // 3. Obtener ID del disco
        int idDisco = Integer.parseInt(lbId.getText().trim());
        // 4. Eliminar la canción de MP3
        Cancion_viniloDAO cmDAO = new Cancion_viniloDAO();
        boolean ok = cmDAO.eliminarCancionDeVinilo(idDisco, idCancion);
        if (ok) {
            cargarCancionesDelVinilo(idDisco);
            JOptionPane.showMessageDialog(this, "Canción eliminada correctamente.");
        } else {
            JOptionPane.showMessageDialog(this, "No se pudo eliminar la canción.");
        }
    }//GEN-LAST:event_btnBorrarCancionActionPerformed

    private void btnAnadirCancionActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAnadirCancionActionPerformed

        // 1. Validar ID del disco
        String idTexto = lbId.getText().trim();
        if (idTexto.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Debe buscar un disco primero.");
            return;
        }
        int idDisco;
        try {
            idDisco = Integer.parseInt(idTexto);
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(this, "ID del disco inválido.");
            return;
        }
        // 2. Validar selección en la tabla
        int fila = jTable.getSelectedRow();
        if (fila == -1) {
            JOptionPane.showMessageDialog(this, "Seleccione una canción de la tabla.");
            return;
        }
        // 3. Obtener ID de la canción con manejo de errores
        Object valorCelda = jTable.getValueAt(fila, 0);
        if (valorCelda == null) {
            JOptionPane.showMessageDialog(this, "Error: la celda seleccionada está vacía.");
            return;
        }
        int idCancion;
        try {
            idCancion = Integer.parseInt(valorCelda.toString());
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(this, "ID de canción inválido en la tabla.");
            return;
        }

        // 5. Crear objeto y verificar duplicado
        Cancion_vinilo cv = new Cancion_vinilo();
        cv.setId_cancion(idCancion);
        cv.setId_disco_vinilo(idDisco);
        Cancion_viniloDAO cvD = new Cancion_viniloDAO();
        if (cvD.existeCancionEnVinilo(idDisco, idCancion)) {
            JOptionPane.showMessageDialog(this, "Esta canción ya está asociada a este vinilo.");
            return;
        }
        // 6. Agregar la canción
        boolean ok = cvD.agregarCancionAVinilo(cv);
        if (ok) {
            cargarCancionesDelVinilo(idDisco);
            JOptionPane.showMessageDialog(this, "Canción agregada correctamente al vinilo.");
        } else {
            JOptionPane.showMessageDialog(this, "No se pudo agregar la canción.");
        }
    }//GEN-LAST:event_btnAnadirCancionActionPerformed

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
        //java.awt.EventQueue.invokeLater(() -> new EditarVinilo().setVisible(true));
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnAnadirCancion;
    private javax.swing.JButton btnBorrarCancion;
    private javax.swing.JButton btnEditarVinilo;
    private javax.swing.JButton btnSubirImagen;
    private javax.swing.JComboBox<String> cmbArtistaVinl;
    private javax.swing.JLabel imagenDisco;
    private javax.swing.JLabel imgSalir;
    private javax.swing.JList<String> jLista;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JSeparator jSeparator1;
    private javax.swing.JSeparator jSeparator2;
    private javax.swing.JSeparator jSeparator3;
    private javax.swing.JTable jTable;
    private javax.swing.JLabel jlcan;
    private javax.swing.JLabel lbId;
    private javax.swing.JLabel lbVendedor;
    private javax.swing.JLabel lbVendedor1;
    private javax.swing.JLabel lbVendedor2;
    private javax.swing.JLabel lbVendedor3;
    private javax.swing.JLabel lbVendedor4;
    private javax.swing.JLabel lbVendedor5;
    private javax.swing.JLabel lbVendedor8;
    private javax.swing.JLabel lbVendedor9;
    private javax.swing.JLabel lbtitulo2;
    private javax.swing.JTextField txtAnioAlb;
    private javax.swing.JTextField txtGeneroAlb;
    private javax.swing.JTextField txtInventario;
    private javax.swing.JTextField txtNomAlbum;
    private javax.swing.JTextField txtPrecioAlb;
    // End of variables declaration//GEN-END:variables
}
