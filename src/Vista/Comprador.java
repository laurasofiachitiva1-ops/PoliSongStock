package Vista;

import Modelo.CatalogoDAO;
import Modelo.ProductoCatalogo;
import Modelo.RenderImagen;
import java.awt.Color;
import java.awt.Image;
import java.util.List;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import static javax.swing.SwingConstants.CENTER;
import javax.swing.table.DefaultTableCellRenderer;
import Vista.InfoVinilo;  
import Vista.InfoMp3;     
import Vista.InfoCancion; 

public class Comprador extends javax.swing.JFrame {

    private static final java.util.logging.Logger logger = java.util.logging.Logger.getLogger(Comprador.class.getName());

    public Comprador() {
        initComponents();
        cargarCatalogo("");

        this.setSize(960, 525);

        // Cursor tipo mano en el boton catalogo
        btnCatalogo.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        // Cursor tipo mano en el boton recopilaciones
        btnMisRecopilaciones.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        // Cursor tipo mano en el boton carrito
        btnCarritoDeCompras.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        // Cursor tipo mano en el boton CerrarSesion
        btnCerrarSesion.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        // Cursor tipo mano en el boton historialcompras
        btnHistorialDeCompras.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        // Cursor tipo mano en el boton buscar
        btnBuscar.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));

        // Ocultar las pestañas (tabs)
        jTabbedPane1.setUI(new javax.swing.plaf.basic.BasicTabbedPaneUI() {
            @Override
            protected int calculateTabAreaHeight(int tabPlacement, int runCount, int maxTabHeight) {
                return 0;
            }
        });

        // Cambiar tabs con botones
        btnCatalogo.addActionListener(e -> jTabbedPane1.setSelectedIndex(0));
        btnMisRecopilaciones.addActionListener(e -> jTabbedPane1.setSelectedIndex(3));
        btnCarritoDeCompras.addActionListener(e -> jTabbedPane1.setSelectedIndex(2));
        btnHistorialDeCompras.addActionListener(e -> jTabbedPane1.setSelectedIndex(3));

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

        // Placeholder para txtBuscar
        txtBuscar.addFocusListener(new java.awt.event.FocusAdapter() {
            @Override
            public void focusGained(java.awt.event.FocusEvent evt) {
                if (txtBuscar.getText().equals("Buscar canciones, MP3, vinilos")) {
                    txtBuscar.setText("");
                }
            }

            @Override
            public void focusLost(java.awt.event.FocusEvent evt) {
                if (txtBuscar.getText().trim().isEmpty()) {
                    txtBuscar.setText("Buscar canciones, MP3, vinilos");
                }
            }
        });

        // Listener para abrir ventanas al hacer clic en filas de jTable
        jTable.addMouseListener(new java.awt.event.MouseAdapter() {
            @Override
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                int fila = jTable.getSelectedRow();
                if (fila != -1) {
                    // Obtener ID y tipo de la fila
                    int id = Integer.parseInt(jTable.getValueAt(fila, 0).toString());
                    String tipo = jTable.getValueAt(fila, 1).toString().toLowerCase();

                    // Abrir ventana según tipo
                    if (tipo.contains("vinilo")) {
                        InfoVinilo infoVinilo = new InfoVinilo(id);
                        infoVinilo.setVisible(true);
                        infoVinilo.setLocationRelativeTo(null);
                        infoVinilo.setResizable(false);
                    } else if (tipo.contains("mp3")) {
                        InfoMp3 infoMp3 = new InfoMp3(id);
                        infoMp3.setVisible(true);
                        infoMp3.setLocationRelativeTo(null);
                        infoMp3.setResizable(false);
                    } else if (tipo.contains("canción") || tipo.contains("cancion")) {
                        InfoCancion infoCancion = new InfoCancion(id);
                        infoCancion.setVisible(true);
                        infoCancion.setLocationRelativeTo(null);
                        infoCancion.setResizable(false);
                    } else {
                        JOptionPane.showMessageDialog(null, "Tipo de producto desconocido.");
                    }
                }
            }
        });

    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jpComprador = new javax.swing.JPanel();
        lbUsuarioComp = new javax.swing.JLabel();
        btnMisRecopilaciones = new javax.swing.JButton();
        jSeparator1 = new javax.swing.JSeparator();
        btnCatalogo = new javax.swing.JButton();
        btnCerrarSesion = new javax.swing.JButton();
        btnCarritoDeCompras = new javax.swing.JButton();
        btnHistorialDeCompras = new javax.swing.JButton();
        lbComprador = new javax.swing.JLabel();
        jpFondoComp = new javax.swing.JPanel();
        jSeparator2 = new javax.swing.JSeparator();
        jSeparator3 = new javax.swing.JSeparator();
        imglogolabelC = new javax.swing.JLabel();
        imgNotificacion = new javax.swing.JLabel();
        imglogoC = new javax.swing.JLabel();
        jTabbedPane1 = new javax.swing.JTabbedPane();
        jPanel1 = new javax.swing.JPanel();
        jlCrearCuenta1 = new javax.swing.JLabel();
        jScrollPane3 = new javax.swing.JScrollPane();
        jTable = new javax.swing.JTable();
        txtBuscar = new javax.swing.JTextField();
        btnBuscar = new javax.swing.JButton();
        jPanel3 = new javax.swing.JPanel();
        jlCrearCuenta3 = new javax.swing.JLabel();
        jPanel4 = new javax.swing.JPanel();
        jlCrearCuenta4 = new javax.swing.JLabel();
        jScrollPane2 = new javax.swing.JScrollPane();
        jTable1 = new javax.swing.JTable();
        jPanel2 = new javax.swing.JPanel();
        jlCrearCuenta2 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        jList1 = new javax.swing.JList<>();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jpComprador.setBackground(new java.awt.Color(89, 89, 89));

        lbUsuarioComp.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Img/Usuario logo.png"))); // NOI18N

        btnMisRecopilaciones.setBackground(new java.awt.Color(153, 153, 153));
        btnMisRecopilaciones.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        btnMisRecopilaciones.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Img/logo musica.png"))); // NOI18N
        btnMisRecopilaciones.setText("Mis recopilaciones");

        btnCatalogo.setBackground(new java.awt.Color(153, 153, 153));
        btnCatalogo.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        btnCatalogo.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Img/logo audifonos.png"))); // NOI18N
        btnCatalogo.setText("Catálogo");

        btnCerrarSesion.setBackground(new java.awt.Color(153, 153, 153));
        btnCerrarSesion.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Img/logo salir.png"))); // NOI18N
        btnCerrarSesion.setText(" Cerrar sesión");
        btnCerrarSesion.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCerrarSesionActionPerformed(evt);
            }
        });

        btnCarritoDeCompras.setBackground(new java.awt.Color(153, 153, 153));
        btnCarritoDeCompras.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        btnCarritoDeCompras.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Img/logo carrito.png"))); // NOI18N
        btnCarritoDeCompras.setText("Carrito de compras");

        btnHistorialDeCompras.setBackground(new java.awt.Color(153, 153, 153));
        btnHistorialDeCompras.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        btnHistorialDeCompras.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Img/logo historial.png"))); // NOI18N
        btnHistorialDeCompras.setText("Historial de compras");

        lbComprador.setFont(new java.awt.Font("Segoe UI", 1, 30)); // NOI18N
        lbComprador.setText("Comprador");

        javax.swing.GroupLayout jpCompradorLayout = new javax.swing.GroupLayout(jpComprador);
        jpComprador.setLayout(jpCompradorLayout);
        jpCompradorLayout.setHorizontalGroup(
            jpCompradorLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jpCompradorLayout.createSequentialGroup()
                .addGroup(jpCompradorLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jpCompradorLayout.createSequentialGroup()
                        .addGap(19, 19, 19)
                        .addGroup(jpCompradorLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jSeparator1, javax.swing.GroupLayout.PREFERRED_SIZE, 214, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(btnCatalogo, javax.swing.GroupLayout.PREFERRED_SIZE, 214, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(btnMisRecopilaciones, javax.swing.GroupLayout.PREFERRED_SIZE, 214, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(btnCarritoDeCompras, javax.swing.GroupLayout.PREFERRED_SIZE, 214, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(btnHistorialDeCompras, javax.swing.GroupLayout.PREFERRED_SIZE, 214, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(jpCompradorLayout.createSequentialGroup()
                        .addGap(43, 43, 43)
                        .addComponent(lbComprador))
                    .addGroup(jpCompradorLayout.createSequentialGroup()
                        .addGap(34, 34, 34)
                        .addComponent(lbUsuarioComp))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jpCompradorLayout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(btnCerrarSesion)))
                .addContainerGap(20, Short.MAX_VALUE))
        );
        jpCompradorLayout.setVerticalGroup(
            jpCompradorLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jpCompradorLayout.createSequentialGroup()
                .addGap(15, 15, 15)
                .addComponent(lbUsuarioComp, javax.swing.GroupLayout.PREFERRED_SIZE, 126, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(lbComprador)
                .addGap(18, 18, 18)
                .addComponent(jSeparator1, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(26, 26, 26)
                .addComponent(btnCatalogo, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(btnMisRecopilaciones, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(btnCarritoDeCompras, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(btnHistorialDeCompras, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(btnCerrarSesion, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(65, Short.MAX_VALUE))
        );

        getContentPane().add(jpComprador, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, -1, 530));

        jpFondoComp.setBackground(new java.awt.Color(51, 51, 51));

        jSeparator3.setOrientation(javax.swing.SwingConstants.VERTICAL);

        imglogolabelC.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Img/Letra soplisong.png"))); // NOI18N

        imgNotificacion.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Img/logo noti.png"))); // NOI18N

        imglogoC.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Img/Logo PoliSong 150 px.png"))); // NOI18N

        jPanel1.setBackground(new java.awt.Color(51, 51, 51));
        jPanel1.setPreferredSize(new java.awt.Dimension(340, 377));

        jlCrearCuenta1.setFont(new java.awt.Font("Segoe UI", 1, 24)); // NOI18N
        jlCrearCuenta1.setForeground(new java.awt.Color(255, 255, 255));
        jlCrearCuenta1.setText("Catálogo");

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
        jScrollPane3.setViewportView(jTable);

        txtBuscar.setText("Buscar canciones, MP3, vinilos");

        btnBuscar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Img/lupa 15px.png"))); // NOI18N
        btnBuscar.setText("Buscar");
        btnBuscar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnBuscarActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane3, javax.swing.GroupLayout.DEFAULT_SIZE, 627, Short.MAX_VALUE)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jlCrearCuenta1)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addComponent(txtBuscar, javax.swing.GroupLayout.PREFERRED_SIZE, 375, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(btnBuscar, javax.swing.GroupLayout.PREFERRED_SIZE, 154, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jlCrearCuenta1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtBuscar, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnBuscar))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 258, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(20, 20, 20))
        );

        jTabbedPane1.addTab("tab1", jPanel1);

        jPanel3.setBackground(new java.awt.Color(51, 51, 51));

        jlCrearCuenta3.setFont(new java.awt.Font("Segoe UI", 1, 24)); // NOI18N
        jlCrearCuenta3.setForeground(new java.awt.Color(255, 255, 255));
        jlCrearCuenta3.setText("Carrito de compras");

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jlCrearCuenta3)
                .addContainerGap(402, Short.MAX_VALUE))
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGap(16, 16, 16)
                .addComponent(jlCrearCuenta3)
                .addContainerGap(287, Short.MAX_VALUE))
        );

        jTabbedPane1.addTab("tab3", jPanel3);

        jPanel4.setBackground(new java.awt.Color(51, 51, 51));

        jlCrearCuenta4.setFont(new java.awt.Font("Segoe UI", 1, 24)); // NOI18N
        jlCrearCuenta4.setForeground(new java.awt.Color(255, 255, 255));
        jlCrearCuenta4.setText("Historial de compras");

        jTable1.setBackground(new java.awt.Color(204, 204, 204));
        jTable1.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        jTable1.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null}
            },
            new String [] {
                "Pedido", "Fecha", "Producto", "Estado", "Factura"
            }
        ));
        jTable1.setGridColor(new java.awt.Color(153, 153, 153));
        jTable1.setSelectionBackground(new java.awt.Color(153, 153, 153));
        jScrollPane2.setViewportView(jTable1);

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 615, Short.MAX_VALUE)
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addComponent(jlCrearCuenta4)
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addGap(14, 14, 14)
                .addComponent(jlCrearCuenta4)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 284, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jTabbedPane1.addTab("tab4", jPanel4);

        jPanel2.setBackground(new java.awt.Color(51, 51, 51));

        jlCrearCuenta2.setFont(new java.awt.Font("Segoe UI", 1, 24)); // NOI18N
        jlCrearCuenta2.setForeground(new java.awt.Color(255, 255, 255));
        jlCrearCuenta2.setText("Mis recopilaciones");

        jList1.setModel(new javax.swing.AbstractListModel<String>() {
            String[] strings = { "Item 1", "Item 2", "Item 3", "Item 4", "Item 5" };
            public int getSize() { return strings.length; }
            public String getElementAt(int i) { return strings[i]; }
        });
        jScrollPane1.setViewportView(jList1);

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jlCrearCuenta2))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGap(17, 17, 17)
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 322, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(288, Short.MAX_VALUE))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jlCrearCuenta2)
                .addGap(27, 27, 27)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(124, Short.MAX_VALUE))
        );

        jTabbedPane1.addTab("tab2", jPanel2);

        javax.swing.GroupLayout jpFondoCompLayout = new javax.swing.GroupLayout(jpFondoComp);
        jpFondoComp.setLayout(jpFondoCompLayout);
        jpFondoCompLayout.setHorizontalGroup(
            jpFondoCompLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jpFondoCompLayout.createSequentialGroup()
                .addGroup(jpFondoCompLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jpFondoCompLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(jpFondoCompLayout.createSequentialGroup()
                            .addGap(269, 269, 269)
                            .addComponent(jSeparator3, javax.swing.GroupLayout.PREFERRED_SIZE, 12, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGroup(jpFondoCompLayout.createSequentialGroup()
                            .addGap(298, 298, 298)
                            .addGroup(jpFondoCompLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                .addComponent(jSeparator2, javax.swing.GroupLayout.Alignment.LEADING)
                                .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jpFondoCompLayout.createSequentialGroup()
                                    .addComponent(imglogoC, javax.swing.GroupLayout.PREFERRED_SIZE, 70, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                    .addComponent(imglogolabelC, javax.swing.GroupLayout.PREFERRED_SIZE, 211, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addGap(303, 303, 303)
                                    .addComponent(imgNotificacion, javax.swing.GroupLayout.PREFERRED_SIZE, 43, javax.swing.GroupLayout.PREFERRED_SIZE)))))
                    .addComponent(jTabbedPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 627, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(568, Short.MAX_VALUE))
        );
        jpFondoCompLayout.setVerticalGroup(
            jpFondoCompLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jpFondoCompLayout.createSequentialGroup()
                .addGroup(jpFondoCompLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jpFondoCompLayout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(imgNotificacion, javax.swing.GroupLayout.PREFERRED_SIZE, 41, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jpFondoCompLayout.createSequentialGroup()
                        .addGap(12, 12, 12)
                        .addGroup(jpFondoCompLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(imglogolabelC, javax.swing.GroupLayout.PREFERRED_SIZE, 42, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(imglogoC, javax.swing.GroupLayout.PREFERRED_SIZE, 57, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jSeparator2, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jTabbedPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 370, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(85, 85, 85)
                .addComponent(jSeparator3, javax.swing.GroupLayout.PREFERRED_SIZE, 311, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(190, Short.MAX_VALUE))
        );

        getContentPane().add(jpFondoComp, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, -1, -1));

        pack();
    }// </editor-fold>//GEN-END:initComponents


    private void btnCerrarSesionActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCerrarSesionActionPerformed
        Login log = new Login();
        log.setVisible(true);
        dispose();
    }//GEN-LAST:event_btnCerrarSesionActionPerformed

    private void btnBuscarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnBuscarActionPerformed
        // Obtener el texto del campo de búsqueda
        String filtro = txtBuscar.getText().trim();
        // Si está vacío o es el placeholder, limpiar filtro y mostrar todo
        if (filtro.isEmpty() || filtro.equals("Buscar canciones, MP3, vinilos")) {
            txtBuscar.setText("Buscar canciones, MP3, vinilos");  // Restaurar placeholder
            cargarCatalogo("");  // Mostrar todo
        } else {
            // Aplicar filtro
            cargarCatalogo(filtro);
        }
    }//GEN-LAST:event_btnBuscarActionPerformed

    private void cargarCatalogo(String filtro) {
        // Obtener todos los productos
        CatalogoDAO dao = new CatalogoDAO();
        List<ProductoCatalogo> listaCompleta = dao.listarTodo();

        List<ProductoCatalogo> lista;
        if (filtro != null && !filtro.trim().isEmpty()) {
            String filtroLower = filtro.toLowerCase();
            lista = listaCompleta.stream()
                    .filter(p -> (p.getNombre() != null && p.getNombre().toLowerCase().contains(filtroLower))
                    || (p.getArtista() != null && p.getArtista().toLowerCase().contains(filtroLower))
                    || (p.getTipo() != null && p.getTipo().toLowerCase().contains(filtroLower)))
                    .collect(java.util.stream.Collectors.toList());
        } else {
            lista = listaCompleta;
        }

        javax.swing.table.DefaultTableModel modelo
                = (javax.swing.table.DefaultTableModel) jTable.getModel();
        modelo.setRowCount(0);

        for (ProductoCatalogo p : lista) {
            ImageIcon icono = null;

            // 1. Imagen desde BD
            if (p.getImagen() != null) {
                Image img = new ImageIcon(p.getImagen())
                        .getImage()
                        .getScaledInstance(60, 60, Image.SCALE_SMOOTH);
                icono = new ImageIcon(img);
            }

            JLabel lblImg;

            // 2. Si tiene imagen BD 
            if (icono != null) {
                lblImg = new JLabel(icono);
            } else {
                // 3. Evitar NULL en el tipo
                String tipo = (p.getTipo() == null) ? "" : p.getTipo().toLowerCase();
                // 4. Seleccionar ruta por tipo
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

                // 5. Cargar imagen (segura)
                java.net.URL url = getClass().getResource(ruta);

                if (url == null) {
                    System.out.println("No se encontró la imagen: " + ruta);
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
        java.awt.EventQueue.invokeLater(() -> new Comprador().setVisible(true));
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnBuscar;
    private javax.swing.JButton btnCarritoDeCompras;
    private javax.swing.JButton btnCatalogo;
    private javax.swing.JButton btnCerrarSesion;
    private javax.swing.JButton btnHistorialDeCompras;
    private javax.swing.JButton btnMisRecopilaciones;
    private javax.swing.JLabel imgNotificacion;
    private javax.swing.JLabel imglogoC;
    private javax.swing.JLabel imglogolabelC;
    private javax.swing.JList<String> jList1;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JSeparator jSeparator1;
    private javax.swing.JSeparator jSeparator2;
    private javax.swing.JSeparator jSeparator3;
    private javax.swing.JTabbedPane jTabbedPane1;
    private javax.swing.JTable jTable;
    private javax.swing.JTable jTable1;
    private javax.swing.JLabel jlCrearCuenta1;
    private javax.swing.JLabel jlCrearCuenta2;
    private javax.swing.JLabel jlCrearCuenta3;
    private javax.swing.JLabel jlCrearCuenta4;
    private javax.swing.JPanel jpComprador;
    private javax.swing.JPanel jpFondoComp;
    private javax.swing.JLabel lbComprador;
    private javax.swing.JLabel lbUsuarioComp;
    private javax.swing.JTextField txtBuscar;
    // End of variables declaration//GEN-END:variables
}
