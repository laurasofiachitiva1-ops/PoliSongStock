package Vista;

import Modelo.Cancion;
import Modelo.CancionDAO;
import Modelo.Cancion_disco_mp3DAO;
import Modelo.Cancion_viniloDAO;
import Modelo.CatalogoDAO;
import Modelo.Disco_mp3DAO;
import Modelo.Disco_viniloDAO;
import Modelo.ProductoCatalogo;
import Modelo.RenderImagen;
import Modelo.Sesion;
import java.awt.Color;
import java.awt.Image;
import java.util.List;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import static javax.swing.SwingConstants.CENTER;
import javax.swing.table.DefaultTableCellRenderer;

public class Vendedor extends javax.swing.JFrame {

    private static final java.util.logging.Logger logger = java.util.logging.Logger.getLogger(Vendedor.class.getName());

    public Vendedor() {
        initComponents();

        jTable.setRowHeight(60);
        jTable.getColumnModel().getColumn(6).setCellRenderer(new RenderImagen());

        // --- CENTRAR DATOS DEL RESTO DE COLUMNAS ---
        DefaultTableCellRenderer centerRenderer = new DefaultTableCellRenderer();
        centerRenderer.setHorizontalAlignment(JLabel.CENTER);

        for (int i = 0; i < jTable.getColumnCount(); i++) {
            if (i != 6) {  // evitar columna de imagen
                jTable.getColumnModel().getColumn(i).setCellRenderer(centerRenderer);
            }
        }

        cargarCatalogo();

        // Cursor tipo mano en el boton catalogo
        btnCatalogoV.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        // Cursor tipo mano en el boton ordenes
        btnOrdenes.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        // Cursor tipo mano en el boton reporte compras
        btnRepDeCompras.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        // Cursor tipo mano en el boton CerrarSesion
        btnCerrarSesionV.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        // Cursor tipo mano en el boton producto
        btnProducto.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        // Cursor tipo mano en el boton modificar
        btnModificar.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        // Cursor tipo mano en el boton eliminar
        btnEliminar.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));

        // Ocultar las pestañas (tabs)
        jTabbedPane1.setUI(new javax.swing.plaf.basic.BasicTabbedPaneUI() {
            @Override
            protected int calculateTabAreaHeight(int tabPlacement, int runCount, int maxTabHeight) {
                return 0;
            }
        });

        // Cambiar tabs con botones
        btnCatalogoV.addActionListener(e -> jTabbedPane1.setSelectedIndex(0));
        btnOrdenes.addActionListener(e -> jTabbedPane1.setSelectedIndex(1));
        btnRepDeCompras.addActionListener(e -> jTabbedPane1.setSelectedIndex(2));

        // Ajuste de texto para botones del catálogo
        btnProducto.setText("<html><center>Agregar nuevo<br>producto/artista</center></html>");
        ajustarTextoBoton(btnModificar, 50);
        ajustarTextoBoton(btnEliminar, 50);

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

    }

    private void ajustarTextoBoton(javax.swing.JButton boton, int ancho) {
        String texto = boton.getText();
        boton.setText("<html><div style='width:" + ancho + "px; text-align:center;'>" + texto + "</div></html>");
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jpVendedor = new javax.swing.JPanel();
        lbUsuarioVen = new javax.swing.JLabel();
        btnOrdenes = new javax.swing.JButton();
        jSeparator1 = new javax.swing.JSeparator();
        btnCatalogoV = new javax.swing.JButton();
        btnCerrarSesionV = new javax.swing.JButton();
        btnRepDeCompras = new javax.swing.JButton();
        lbVendedor = new javax.swing.JLabel();
        jpFondoVen = new javax.swing.JPanel();
        jSeparator2 = new javax.swing.JSeparator();
        jSeparator3 = new javax.swing.JSeparator();
        imglogolabelV = new javax.swing.JLabel();
        imgNotificacionV = new javax.swing.JLabel();
        imglogoV = new javax.swing.JLabel();
        jTabbedPane1 = new javax.swing.JTabbedPane();
        jPanel1 = new javax.swing.JPanel();
        jlCrearCuenta1 = new javax.swing.JLabel();
        btnProducto = new javax.swing.JButton();
        btnModificar = new javax.swing.JButton();
        btnEliminar = new javax.swing.JButton();
        jScrollPane2 = new javax.swing.JScrollPane();
        jTable = new javax.swing.JTable();
        jPanel3 = new javax.swing.JPanel();
        jlCrearCuenta3 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTable1 = new javax.swing.JTable();
        jPanel2 = new javax.swing.JPanel();
        jlCrearCuenta5 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jpVendedor.setBackground(new java.awt.Color(89, 89, 89));
        jpVendedor.setPreferredSize(new java.awt.Dimension(253, 530));

        lbUsuarioVen.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Img/Usuario logo.png"))); // NOI18N

        btnOrdenes.setBackground(new java.awt.Color(153, 153, 153));
        btnOrdenes.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        btnOrdenes.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Img/logo ordenes pedido.png"))); // NOI18N
        btnOrdenes.setText("Órdenes de pedido");

        btnCatalogoV.setBackground(new java.awt.Color(153, 153, 153));
        btnCatalogoV.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        btnCatalogoV.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Img/logo vinilo.png"))); // NOI18N
        btnCatalogoV.setText("Catálogo de productos");

        btnCerrarSesionV.setBackground(new java.awt.Color(153, 153, 153));
        btnCerrarSesionV.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Img/logo salir.png"))); // NOI18N
        btnCerrarSesionV.setText(" Cerrar sesión");
        btnCerrarSesionV.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCerrarSesionVActionPerformed(evt);
            }
        });

        btnRepDeCompras.setBackground(new java.awt.Color(153, 153, 153));
        btnRepDeCompras.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        btnRepDeCompras.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Img/logo reporte compras.png"))); // NOI18N
        btnRepDeCompras.setText("Reporte de compras");

        lbVendedor.setFont(new java.awt.Font("Segoe UI", 1, 30)); // NOI18N
        lbVendedor.setText("Vendedor");

        javax.swing.GroupLayout jpVendedorLayout = new javax.swing.GroupLayout(jpVendedor);
        jpVendedor.setLayout(jpVendedorLayout);
        jpVendedorLayout.setHorizontalGroup(
            jpVendedorLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jpVendedorLayout.createSequentialGroup()
                .addGroup(jpVendedorLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jpVendedorLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(jpVendedorLayout.createSequentialGroup()
                            .addGap(19, 19, 19)
                            .addGroup(jpVendedorLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(jSeparator1, javax.swing.GroupLayout.PREFERRED_SIZE, 214, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(btnCatalogoV, javax.swing.GroupLayout.PREFERRED_SIZE, 214, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(btnOrdenes, javax.swing.GroupLayout.PREFERRED_SIZE, 214, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(btnRepDeCompras, javax.swing.GroupLayout.PREFERRED_SIZE, 214, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGroup(jpVendedorLayout.createSequentialGroup()
                            .addGap(34, 34, 34)
                            .addComponent(lbUsuarioVen))
                        .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jpVendedorLayout.createSequentialGroup()
                            .addContainerGap()
                            .addComponent(btnCerrarSesionV)))
                    .addGroup(jpVendedorLayout.createSequentialGroup()
                        .addGap(56, 56, 56)
                        .addComponent(lbVendedor)))
                .addContainerGap(20, Short.MAX_VALUE))
        );
        jpVendedorLayout.setVerticalGroup(
            jpVendedorLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jpVendedorLayout.createSequentialGroup()
                .addGap(15, 15, 15)
                .addComponent(lbUsuarioVen, javax.swing.GroupLayout.PREFERRED_SIZE, 126, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(lbVendedor)
                .addGap(18, 18, 18)
                .addComponent(jSeparator1, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(27, 27, 27)
                .addComponent(btnCatalogoV, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(btnOrdenes, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(btnRepDeCompras, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(46, 46, 46)
                .addComponent(btnCerrarSesionV, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(35, Short.MAX_VALUE))
        );

        getContentPane().add(jpVendedor, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, -1, 500));

        jpFondoVen.setBackground(new java.awt.Color(51, 51, 51));

        jSeparator3.setOrientation(javax.swing.SwingConstants.VERTICAL);

        imglogolabelV.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Img/Letra soplisong.png"))); // NOI18N

        imgNotificacionV.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Img/logo noti.png"))); // NOI18N

        imglogoV.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Img/Logo PoliSong 150 px.png"))); // NOI18N

        jPanel1.setBackground(new java.awt.Color(51, 51, 51));
        jPanel1.setPreferredSize(new java.awt.Dimension(340, 377));

        jlCrearCuenta1.setFont(new java.awt.Font("Segoe UI", 1, 24)); // NOI18N
        jlCrearCuenta1.setForeground(new java.awt.Color(255, 255, 255));
        jlCrearCuenta1.setText("Catálogo de productos");

        btnProducto.setBackground(new java.awt.Color(204, 204, 204));
        btnProducto.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        btnProducto.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Img/logo mas.png"))); // NOI18N
        btnProducto.setText("Agregar nuevo producto/artista");
        btnProducto.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnProductoActionPerformed(evt);
            }
        });

        btnModificar.setBackground(new java.awt.Color(204, 204, 204));
        btnModificar.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        btnModificar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Img/editar.png"))); // NOI18N
        btnModificar.setText("Editar producto");
        btnModificar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnModificarActionPerformed(evt);
            }
        });

        btnEliminar.setBackground(new java.awt.Color(204, 204, 204));
        btnEliminar.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        btnEliminar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Img/eliminar.png"))); // NOI18N
        btnEliminar.setText("Eliminar producto");
        btnEliminar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnEliminarActionPerformed(evt);
            }
        });

        jTable.setBackground(new java.awt.Color(89, 89, 89));
        jTable.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null}
            },
            new String [] {
                "ID", "Producto", "Nombre", "Artista", "Género", "Precio", "Imagen"
            }
        ));
        jTable.setGridColor(new java.awt.Color(89, 89, 89));
        jTable.setSelectionBackground(new java.awt.Color(51, 51, 51));
        jScrollPane2.setViewportView(jTable);

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addComponent(jlCrearCuenta1, javax.swing.GroupLayout.PREFERRED_SIZE, 263, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(btnProducto, javax.swing.GroupLayout.PREFERRED_SIZE, 180, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(36, 36, 36)
                .addComponent(btnModificar, javax.swing.GroupLayout.PREFERRED_SIZE, 180, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 39, Short.MAX_VALUE)
                .addComponent(btnEliminar, javax.swing.GroupLayout.PREFERRED_SIZE, 180, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
            .addComponent(jScrollPane2)
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addComponent(jlCrearCuenta1)
                .addGap(12, 12, 12)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnProducto)
                    .addComponent(btnEliminar)
                    .addComponent(btnModificar))
                .addGap(18, 18, 18)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 234, Short.MAX_VALUE))
        );

        jTabbedPane1.addTab("tab1", jPanel1);

        jPanel3.setBackground(new java.awt.Color(51, 51, 51));

        jlCrearCuenta3.setFont(new java.awt.Font("Segoe UI", 1, 24)); // NOI18N
        jlCrearCuenta3.setForeground(new java.awt.Color(255, 255, 255));
        jlCrearCuenta3.setText("Órdenes de pedido");

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
        jScrollPane1.setViewportView(jTable1);

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addComponent(jlCrearCuenta3)
                .addGap(0, 0, Short.MAX_VALUE))
            .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 627, Short.MAX_VALUE)
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addComponent(jlCrearCuenta3)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 297, Short.MAX_VALUE))
        );

        jTabbedPane1.addTab("tab3", jPanel3);

        jPanel2.setBackground(new java.awt.Color(51, 51, 51));

        jlCrearCuenta5.setFont(new java.awt.Font("Segoe UI", 1, 24)); // NOI18N
        jlCrearCuenta5.setForeground(new java.awt.Color(255, 255, 255));
        jlCrearCuenta5.setText("Órdenes de pedido");

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(17, 17, 17)
                .addComponent(jlCrearCuenta5)
                .addContainerGap(393, Short.MAX_VALUE))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(15, 15, 15)
                .addComponent(jlCrearCuenta5)
                .addContainerGap(288, Short.MAX_VALUE))
        );

        jTabbedPane1.addTab("tab2", jPanel2);

        javax.swing.GroupLayout jpFondoVenLayout = new javax.swing.GroupLayout(jpFondoVen);
        jpFondoVen.setLayout(jpFondoVenLayout);
        jpFondoVenLayout.setHorizontalGroup(
            jpFondoVenLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jpFondoVenLayout.createSequentialGroup()
                .addGroup(jpFondoVenLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jpFondoVenLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(jpFondoVenLayout.createSequentialGroup()
                            .addGap(269, 269, 269)
                            .addComponent(jSeparator3, javax.swing.GroupLayout.PREFERRED_SIZE, 12, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGroup(jpFondoVenLayout.createSequentialGroup()
                            .addGap(298, 298, 298)
                            .addGroup(jpFondoVenLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                .addComponent(jSeparator2, javax.swing.GroupLayout.Alignment.LEADING)
                                .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jpFondoVenLayout.createSequentialGroup()
                                    .addComponent(imglogoV, javax.swing.GroupLayout.PREFERRED_SIZE, 70, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                    .addComponent(imglogolabelV, javax.swing.GroupLayout.PREFERRED_SIZE, 211, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addGap(303, 303, 303)
                                    .addComponent(imgNotificacionV, javax.swing.GroupLayout.PREFERRED_SIZE, 43, javax.swing.GroupLayout.PREFERRED_SIZE)))))
                    .addComponent(jTabbedPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 627, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(29, Short.MAX_VALUE))
        );
        jpFondoVenLayout.setVerticalGroup(
            jpFondoVenLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jpFondoVenLayout.createSequentialGroup()
                .addGroup(jpFondoVenLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jpFondoVenLayout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(imgNotificacionV, javax.swing.GroupLayout.PREFERRED_SIZE, 41, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jpFondoVenLayout.createSequentialGroup()
                        .addGap(12, 12, 12)
                        .addGroup(jpFondoVenLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(imglogolabelV, javax.swing.GroupLayout.PREFERRED_SIZE, 42, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(imglogoV, javax.swing.GroupLayout.PREFERRED_SIZE, 57, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jSeparator2, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jTabbedPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 370, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(64, 64, 64)
                .addComponent(jSeparator3, javax.swing.GroupLayout.PREFERRED_SIZE, 311, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        getContentPane().add(jpFondoVen, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 960, 500));

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnCerrarSesionVActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCerrarSesionVActionPerformed
        Login log = new Login();
        log.setVisible(true);
        dispose();
    }//GEN-LAST:event_btnCerrarSesionVActionPerformed

    private void cargarCatalogo() {

        int idVen = Sesion.getIdVendedor();

        CatalogoDAO dao = new CatalogoDAO();
        List<ProductoCatalogo> lista = dao.listarTodoPorVendedor(idVen);

        javax.swing.table.DefaultTableModel modelo
                = (javax.swing.table.DefaultTableModel) jTable.getModel();
        modelo.setRowCount(0);

        for (ProductoCatalogo p : lista) {

            ImageIcon icono = null;

            // ======================
            // 1. Imagen desde BD
            // ======================
            if (p.getImagen() != null) {
                Image img = new ImageIcon(p.getImagen())
                        .getImage()
                        .getScaledInstance(60, 60, Image.SCALE_SMOOTH);
                icono = new ImageIcon(img);
            }

            JLabel lblImg;

            // ======================
            // 2. Si tiene imagen BD 
            // ======================
            if (icono != null) {
                lblImg = new JLabel(icono);

            } else {
                // ======================
                // 3. Evitar NULL en el tipo
                // ======================
                String tipo = (p.getTipo() == null) ? "" : p.getTipo().toLowerCase();

                // ======================
                // 4. Seleccionar ruta por tipo
                // ======================
                String ruta;

                switch (tipo) {
                    case "mp3":
                    case "cancion":
                    case "canción":   // con tilde
                        ruta = "/Img/mp3.png";
                        break;

                    case "vinilo":
                    case "disco":
                    case "vinilo mp3":
                    case "disco mp3":
                        ruta = "/Img/disco.png";
                        break;

                    default:
                        ruta = "/Img/mp3.png"; // fallback seguro
                        break;
                }

                // ======================
                // 5. Cargar imagen (segura)
                // ======================
                java.net.URL url = getClass().getResource(ruta);

                if (url == null) {
                    System.out.println("⚠ No se encontró la imagen: " + ruta);
                    lblImg = new JLabel("No img");
                } else {
                    ImageIcon defaultIcon = new ImageIcon(url);
                    Image imgDef = defaultIcon.getImage()
                            .getScaledInstance(60, 60, Image.SCALE_SMOOTH);
                    lblImg = new JLabel(new ImageIcon(imgDef));
                }
            }

            modelo.addRow(new Object[]{
                p.getId(),
                p.getTipo(),
                p.getNombre(),
                p.getArtista(),
                p.getGenero(),
                p.getPrecio(),
                lblImg
            });
        }
    }


    private void btnProductoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnProductoActionPerformed
        AgregarProducto ap = new AgregarProducto();
        ap.setLocationRelativeTo(null);
        ap.setResizable(false);
        ap.setVisible(true);
        dispose();
    }//GEN-LAST:event_btnProductoActionPerformed

    private void btnModificarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnModificarActionPerformed
        int fila = jTable.getSelectedRow();

        if (fila == -1) {
            JOptionPane.showMessageDialog(this, "Seleccione un producto primero.");
            return;
        }

        // Tomar datos de la tabla
        int id = Integer.parseInt(jTable.getValueAt(fila, 0).toString());
        String tipo = jTable.getValueAt(fila, 1).toString().toLowerCase();

        //    ABRIR SEGÚN TIPO
        if (tipo.contains("cancion") || tipo.contains("canción")) {

            //ABRIR VENTANA PARA EDITAR CANCIÓN
            EditarCancion ec = new EditarCancion(id);
            ec.setLocationRelativeTo(null);
            ec.setResizable(false);
            ec.setVisible(true);
            dispose();
        } else if (tipo.contains("vinilo")) {

            //ABRIR VENTANA PARA EDITAR DISCO
            EditarVinilo ed = new EditarVinilo(id);
            ed.setLocationRelativeTo(null);
            ed.setResizable(false);
            ed.setVisible(true);
            dispose();
        } else if (tipo.contains("mp3")) {

            //ABRIR VENTANA PARA EDITAR MP3
            EditarMp3 mp = new EditarMp3(id);
            mp.setLocationRelativeTo(null);
            mp.setResizable(false);
            mp.setVisible(true);
            dispose();
        } else {
            JOptionPane.showMessageDialog(this, "Tipo de producto desconocido.");
        }
    }//GEN-LAST:event_btnModificarActionPerformed

    private void btnEliminarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnEliminarActionPerformed
        int fila = jTable.getSelectedRow();

        if (fila == -1) {
            JOptionPane.showMessageDialog(this, "Seleccione un producto primero.");
            return;
        }

        int id = Integer.parseInt(jTable.getValueAt(fila, 0).toString());
        String tipo = jTable.getValueAt(fila, 1).toString().toLowerCase();

        int confirm = JOptionPane.showConfirmDialog(
                this,
                "¿Eliminar el producto seleccionado?",
                "Confirmar eliminación",
                JOptionPane.YES_NO_OPTION
        );

        if (confirm != JOptionPane.YES_OPTION) {
            return;
        }

        boolean ok = false;

        CancionDAO cancionDAO = new CancionDAO();
        Cancion_viniloDAO cvDAO = new Cancion_viniloDAO();
        Cancion_disco_mp3DAO cmDAO = new Cancion_disco_mp3DAO();

        // ===============================================
        //         ELIMINAR VINILO
        // ===============================================
        if (tipo.contains("vinilo")) {
            cvDAO.eliminarPorVinilo(id);    // eliminar relaciones
            Disco_viniloDAO dvDAO = new Disco_viniloDAO();
            ok = dvDAO.eliminarVinilo(id);  // eliminar disco
        } // ===============================================
        //         ELIMINAR MP3
        // ===============================================
        else if (tipo.contains("mp3")) {
            cmDAO.eliminarPorMp3(id);      // eliminar relaciones
            Disco_mp3DAO mpDAO = new Disco_mp3DAO();
            ok = mpDAO.eliminarMp3(id);    // eliminar disco
        } // ===============================================
        //         ELIMINAR CANCION
        // ===============================================
        else if (tipo.contains("canción")) {

            // 1. eliminar relaciones vinilo
            cvDAO.eliminarPorCancion(id);

            // 2. eliminar relaciones mp3
            cmDAO.eliminarPorCancion(id);

            // 3. finalmente eliminar canción
            ok = cancionDAO.eliminarCancion(id);
        }

        if (ok) {
            JOptionPane.showMessageDialog(this, "Producto eliminado correctamente.");
            cargarCatalogo();
        } else {
            JOptionPane.showMessageDialog(this, "No se pudo eliminar.");
        }
    }//GEN-LAST:event_btnEliminarActionPerformed

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
        java.awt.EventQueue.invokeLater(() -> new Vendedor().setVisible(true));
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnCatalogoV;
    private javax.swing.JButton btnCerrarSesionV;
    private javax.swing.JButton btnEliminar;
    private javax.swing.JButton btnModificar;
    private javax.swing.JButton btnOrdenes;
    private javax.swing.JButton btnProducto;
    private javax.swing.JButton btnRepDeCompras;
    private javax.swing.JLabel imgNotificacionV;
    private javax.swing.JLabel imglogoV;
    private javax.swing.JLabel imglogolabelV;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JSeparator jSeparator1;
    private javax.swing.JSeparator jSeparator2;
    private javax.swing.JSeparator jSeparator3;
    private javax.swing.JTabbedPane jTabbedPane1;
    private javax.swing.JTable jTable;
    private javax.swing.JTable jTable1;
    private javax.swing.JLabel jlCrearCuenta1;
    private javax.swing.JLabel jlCrearCuenta3;
    private javax.swing.JLabel jlCrearCuenta5;
    private javax.swing.JPanel jpFondoVen;
    private javax.swing.JPanel jpVendedor;
    private javax.swing.JLabel lbUsuarioVen;
    private javax.swing.JLabel lbVendedor;
    // End of variables declaration//GEN-END:variables
}
