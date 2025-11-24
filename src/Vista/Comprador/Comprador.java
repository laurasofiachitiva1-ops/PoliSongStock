package Vista.Comprador;

import Modelo.Cancion;
import Modelo.CancionDAO;
import Modelo.Carrito;
import Modelo.CarritoDAO;
import Modelo.CatalogoDAO;
import Modelo.Disco_mp3;
import Modelo.Disco_mp3DAO;
import Modelo.Disco_vinilo;
import Modelo.Disco_viniloDAO;
import Modelo.ProductoCatalogo;
import Modelo.RenderImagen;
import Modelo.Sesion;
import java.awt.Color;
import java.awt.Image;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import static javax.swing.SwingConstants.CENTER;
import javax.swing.table.DefaultTableCellRenderer;
import Vista.Login;
import javax.swing.ButtonGroup;
import Modelo.Venta;
import Modelo.VentaDAO;
import Modelo.Detalle_venta;
import Modelo.Detalle_ventaDAO;
import Modelo.ListaRenderer;
import Modelo.Lista_reproduccion;
import Modelo.Lista_reproduccionDAO;
import Vista.Vendedor.InfoPedido;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfWriter;
import java.awt.Component;
import java.io.FileOutputStream;
import java.util.List;
import javax.swing.DefaultCellEditor;
import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JFileChooser;
import javax.swing.JTable;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableCellRenderer;
import javax.swing.table.TableColumn;
import javax.swing.text.Document;

public class Comprador extends javax.swing.JFrame {

    private static final java.util.logging.Logger logger = java.util.logging.Logger.getLogger(Comprador.class.getName());

    // Clases para el botón en la tabla (ButtonRenderer y ButtonEditor)
    class ButtonRenderer extends JButton implements TableCellRenderer {

        public ButtonRenderer() {
            setOpaque(true);
        }

        @Override
        public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) {
            setText((value == null) ? "" : value.toString());
            return this;
        }
    }

    class ButtonEditor extends DefaultCellEditor {

        protected JButton button;
        private String label;
        private boolean isPushed;

        public ButtonEditor(JCheckBox checkBox) {
            super(checkBox);
            button = new JButton();
            button.setOpaque(true);
            button.addActionListener(e -> fireEditingStopped());
        }

        @Override
        public Component getTableCellEditorComponent(JTable table, Object value, boolean isSelected, int row, int column) {
            label = (value == null) ? "" : value.toString();
            button.setText(label);
            isPushed = true;
            return button;
        }

        @Override
        public Object getCellEditorValue() {
            if (isPushed) {
                // Obtener ID de la venta de la fila actual
                int row = jTableHistorial.getSelectedRow();  // Cambiado: usa jTableHistorial
                if (row != -1) {
                    int idVenta = (Integer) jTableHistorial.getValueAt(row, 0);  // Cambiado: usa jTableHistorial
                    descargarFacturaPDF(idVenta);
                }
            }
            isPushed = false;
            return label;
        }

        @Override
        public boolean stopCellEditing() {
            isPushed = false;
            return super.stopCellEditing();
        }
    }

    public Comprador() {
        initComponents();
        cargarCatalogo("");
        cargarCarrito();

        // Configurar columnas de jTableHistorial (cambiado de jTable1)
        jTableHistorial.setModel(new javax.swing.table.DefaultTableModel(
                new Object[][]{},
                new String[]{"Pedido", "Fecha", "Producto", "Estado", "Factura"}
        ));

        // Configurar renderer y editor para la columna "Factura" (índice 4)
        TableColumn facturaColumn = jTableHistorial.getColumnModel().getColumn(4);  // Cambiado: usa jTableHistorial
        facturaColumn.setCellRenderer(new ButtonRenderer());
        facturaColumn.setCellEditor(new ButtonEditor(new JCheckBox()));

        // Centrar datos en columnas (excepto "Factura")
        DefaultTableCellRenderer centerRenderer = new DefaultTableCellRenderer();
        centerRenderer.setHorizontalAlignment(JLabel.CENTER);
        for (int i = 0; i < jTableHistorial.getColumnCount() - 1; i++) {  // Excepto la última columna, usa jTableHistorial
            jTableHistorial.getColumnModel().getColumn(i).setCellRenderer(centerRenderer);
        }

        // ... (el resto del constructor, como listeners, etc.)
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

        btnEliminar.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnEditar.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnPagar.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));

        btnNueva.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));

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
        btnHistorialDeCompras.addActionListener(e -> jTabbedPane1.setSelectedIndex(1));

        // Listener para recargar carrito al cambiar a la pestaña
        jTabbedPane1.addChangeListener(e -> {
            if (jTabbedPane1.getSelectedIndex() == 3) {  // Cambiado: índice 3 (Carrito)
                cargarCarrito();
            }
        });

        jTable.setRowHeight(60);
        jTable.getColumnModel().getColumn(6).setCellRenderer(new RenderImagen());

        // --- CENTRAR DATOS DEL RESTO DE COLUMNAS ---
        //DefaultTableCellRenderer centerRenderer = new DefaultTableCellRenderer();
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

                jScrollPane5.getViewport().setBackground(new java.awt.Color(89, 89, 89));
                c.setBackground(new java.awt.Color(89, 89, 89));
                c.setForeground(Color.WHITE);
                setHorizontalAlignment(CENTER);
                return c;
            }
        });

        // Configuración de estilo para jTableCarrito (igual a jTable)
        jTableCarrito.setModel(new javax.swing.table.DefaultTableModel(
                new Object[][]{}, // Cambiado: vacío
                new String[]{ // Cambiado: agregar "ID Vendedor"
                    "ID", "Producto", "Nombre", "Cantidad", "Precio Unitario", "Total", "ID Vendedor"
                }
        ));

        // Centrar datos en todas las columnas (no hay columna de imagen en el carrito)
        DefaultTableCellRenderer centerRendererCarrito = new DefaultTableCellRenderer();
        centerRendererCarrito.setHorizontalAlignment(JLabel.CENTER);
        for (int i = 0; i < jTableCarrito.getColumnCount(); i++) {
            jTableCarrito.getColumnModel().getColumn(i).setCellRenderer(centerRendererCarrito);
        }

        // COLOR DE FONDO PARA EL HEADER de jTableCarrito
        javax.swing.table.JTableHeader headerCarrito = jTableCarrito.getTableHeader();
        headerCarrito.setDefaultRenderer(new javax.swing.table.DefaultTableCellRenderer() {
            @Override
            public java.awt.Component getTableCellRendererComponent(
                    javax.swing.JTable table, Object value, boolean isSelected,
                    boolean hasFocus, int row, int column) {

                java.awt.Component c = super.getTableCellRendererComponent(
                        table, value, isSelected, hasFocus, row, column);
                jTableCarrito.getTableHeader().setBackground(new java.awt.Color(89, 89, 89));
                jScrollPane4.getViewport().setBackground(new java.awt.Color(89, 89, 89));  // Fondo del viewport
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

        // Agrupar radios
        ButtonGroup grupo = new ButtonGroup();
        grupo.add(rdTarjeta);
        grupo.add(rdNequi);
        grupo.add(rdPse);
        grupo.add(rdDavi);

        // Ocultar botones inicialmente
        btnEliminar.setVisible(false);
        btnEditar.setVisible(false);

        /// Listener para mostrar/ocultar botones según selección en jTableCarrito
        jTableCarrito.getSelectionModel().addListSelectionListener(e -> {
            if (!e.getValueIsAdjusting()) {
                int filaSeleccionada = jTableCarrito.getSelectedRow();
                if (filaSeleccionada != -1) {
                    // Mostrar botón eliminar siempre
                    btnEliminar.setVisible(true);

                    // Mostrar botón editar solo si es vinilo
                    String tipo = jTableCarrito.getValueAt(filaSeleccionada, 1).toString();  // Cambiado: Columna 1 (Producto)
                    if ("vinilo".equalsIgnoreCase(tipo)) {
                        btnEditar.setVisible(true);
                    } else {
                        btnEditar.setVisible(false);
                    }
                } else {
                    // Ocultar ambos si no hay selección
                    btnEliminar.setVisible(false);
                    btnEditar.setVisible(false);
                }
            }
        });

        // Action listener para btnEliminar
        btnEliminar.addActionListener(e -> {
            int fila = jTableCarrito.getSelectedRow();
            if (fila != -1) {
                int idItem = (Integer) jTableCarrito.getValueAt(fila, 0);  // Columna 0: ID

                int confirm = JOptionPane.showConfirmDialog(this, "¿Eliminar este item del carrito?", "Confirmar", JOptionPane.YES_NO_OPTION);
                if (confirm == JOptionPane.YES_OPTION) {
                    CarritoDAO dao = new CarritoDAO();
                    if (dao.eliminarItemCarrito(idItem)) {
                        JOptionPane.showMessageDialog(this, "Item eliminado del carrito.");
                        cargarCarrito();  // Recargar tabla
                    } else {
                        JOptionPane.showMessageDialog(this, "Error al eliminar.");
                    }
                }
            }
        });

        // Action listener para btnEditar (solo vinilos)
        btnEditar.addActionListener(e -> {
            int fila = jTableCarrito.getSelectedRow();
            if (fila != -1) {
                String tipo = jTableCarrito.getValueAt(fila, 1).toString();  // Cambiado: Columna 1 (Producto), usar toString() para evitar cast error
                if ("vinilo".equalsIgnoreCase(tipo)) {
                    int idItem = (Integer) jTableCarrito.getValueAt(fila, 0);  // Columna 0: ID
                    int cantidadActual = (Integer) jTableCarrito.getValueAt(fila, 3);  // Columna 3: Cantidad

                    String nuevaCantidadStr = JOptionPane.showInputDialog(this, "Nueva cantidad:", cantidadActual);
                    if (nuevaCantidadStr != null) {
                        try {
                            int nuevaCantidad = Integer.parseInt(nuevaCantidadStr);
                            if (nuevaCantidad > 0) {
                                CarritoDAO dao = new CarritoDAO();
                                Carrito item = dao.buscarPorIdItem(idItem);
                                if (item != null) {
                                    item.setCantidad(nuevaCantidad);
                                    if (dao.modificarItemCarrito(item)) {
                                        JOptionPane.showMessageDialog(this, "Cantidad actualizada.");
                                        cargarCarrito();  // Recargar tabla
                                    } else {
                                        JOptionPane.showMessageDialog(this, "Error al actualizar.");
                                    }
                                }
                            } else {
                                JOptionPane.showMessageDialog(this, "Cantidad debe ser mayor a 0.");
                            }
                        } catch (NumberFormatException ex) {
                            JOptionPane.showMessageDialog(this, "Ingrese un número válido.");
                        }
                    }
                }
            }
        });

        ///HISTORIAL DE VENTAS////
        

        jTableHistorial.setModel(new javax.swing.table.DefaultTableModel(
                new Object[][]{},
                new String[]{"Pedido", "Fecha", "Producto", "Estado", "Factura"}
        ));

        // Aumentar la altura de las filas para más espacio vertical
        jTableHistorial.setRowHeight(70);

        TableColumn HistorialColumn = jTableHistorial.getColumnModel().getColumn(4);
        HistorialColumn.setCellRenderer(new ButtonRenderer());
        HistorialColumn.setCellEditor(new ButtonEditor(new JCheckBox()));

        DefaultTableCellRenderer centerRendererHistorial = new DefaultTableCellRenderer();
        centerRendererHistorial.setHorizontalAlignment(JLabel.CENTER);
        for (int i = 0; i < jTableHistorial.getColumnCount() - 1; i++) {  // Excepto la última columna
            jTableHistorial.getColumnModel().getColumn(i).setCellRenderer(centerRendererHistorial);
        }

        javax.swing.table.JTableHeader headerHistorial = jTableHistorial.getTableHeader();
        headerHistorial.setDefaultRenderer(new javax.swing.table.DefaultTableCellRenderer() {
            @Override
            public java.awt.Component getTableCellRendererComponent(
                    javax.swing.JTable table, Object value, boolean isSelected,
                    boolean hasFocus, int row, int column) {

                java.awt.Component c = super.getTableCellRendererComponent(
                        table, value, isSelected, hasFocus, row, column);
                jTableHistorial.getTableHeader().setBackground(new java.awt.Color(89, 89, 89));
                jScrollPane5.getViewport().setBackground(new java.awt.Color(89, 89, 89));
                c.setBackground(new java.awt.Color(89, 89, 89));
                c.setForeground(Color.WHITE);
                setHorizontalAlignment(CENTER);
                return c;
            }
        });

        class ButtonRenderer extends JButton implements TableCellRenderer {

            public ButtonRenderer() {
                setOpaque(true);
                setBackground(new java.awt.Color(51, 51, 51));  // Fondo oscuro para el botón
                setForeground(Color.WHITE);  // Texto blanco
                setBorder(javax.swing.BorderFactory.createRaisedBevelBorder());  // Borde elevado para mejor apariencia
                setFocusPainted(false);  // Sin borde de foco
            }

            @Override
            public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) {
                setText((value == null) ? "" : value.toString());
                return this;
            }
        }

        jTableHistorial.addMouseListener(new java.awt.event.MouseAdapter() {
            @Override
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                int fila = jTableHistorial.getSelectedRow();
                if (fila != -1) {
                    // Corrige el cast directo por una conversión segura
                    int idVenta = Integer.parseInt(jTableHistorial.getValueAt(fila, 0).toString()); // Columna Pedido (ID venta, índice 0)
                    String estado = jTableHistorial.getValueAt(fila, 3).toString(); // Columna Estado (índice 3)

                    // Obtener detalles de la venta para verificar si hay vinilos
                    Detalle_ventaDAO detalleDAO = new Detalle_ventaDAO();
                    List<Detalle_venta> detalles = detalleDAO.listarDetallesPorVenta(idVenta);
                    boolean tieneVinilos = detalles.stream().anyMatch(d -> "vinilo".equalsIgnoreCase(d.getTipo()));

                    if (tieneVinilos) {
                        if ("aceptado".equalsIgnoreCase(estado)) {
                            // Obtener el primer vinilo de los detalles (asumiendo que hay al menos uno)
                            Detalle_venta viniloDetalle = detalles.stream()
                                    .filter(d -> "vinilo".equalsIgnoreCase(d.getTipo()))
                                    .findFirst()
                                    .orElse(null);
                            if (viniloDetalle != null) {
                                // Nuevo: Crear y mostrar un diálogo inline para confirmar recepción
                                mostrarDialogoConfirmacion(idVenta);
                                // Después de cerrar el diálogo, recarga el historial para actualizar el estado
                                cargarHistorialCompras();
                            }
                        } else {
                            JOptionPane.showMessageDialog(Comprador.this, "La venta no está aceptada. No se puede confirmar recepción.");
                        }
                    }
                    // Si no tiene vinilos, no hacer nada
                }
            }
        });

        cargarListasReproduccion();

        // Aplicar renderer con imagen por defecto
        listaComprador.setCellRenderer(new ListaRenderer());
        listaPublica.setCellRenderer(new ListaRenderer());

        // Agregar listener para listaComprador
        listaComprador.addListSelectionListener(new ListSelectionListener() {
            @Override
            public void valueChanged(ListSelectionEvent e) {
                if (!e.getValueIsAdjusting()) {  // Evitar eventos duplicados
                    String selected = listaComprador.getSelectedValue();
                    if (selected != null) {
                        int idLista = extraerIdDeTexto(selected);
                        if (idLista != -1) {
                            Recopilacion recopilacion = new Recopilacion(idLista, Comprador.this);  // Pasar 'this' como padre
                            recopilacion.setVisible(true);
                            recopilacion.setLocationRelativeTo(null);
                            recopilacion.setResizable(false);
                        } else {
                            JOptionPane.showMessageDialog(Comprador.this, "Error al extraer ID de la lista.");
                        }
                    }
                }
            }
        });

        // Agregar listener para listaPublica
        listaPublica.addListSelectionListener(new ListSelectionListener() {
            @Override
            public void valueChanged(ListSelectionEvent e) {
                if (!e.getValueIsAdjusting()) {  // Evitar eventos duplicados
                    String selected = listaPublica.getSelectedValue();
                    if (selected != null) {
                        int idLista = extraerIdDeTexto(selected);
                        if (idLista != -1) {
                            Recopilacion recopilacion = new Recopilacion(idLista, Comprador.this);  // Pasar 'this' como padre
                            recopilacion.setVisible(true);
                            recopilacion.setLocationRelativeTo(null);
                            recopilacion.setResizable(false);
                        } else {
                            JOptionPane.showMessageDialog(Comprador.this, "Error al extraer ID de la lista.");
                        }
                    }
                }
            }
        });

        // Listener para recargar carrito o listas al cambiar a la pestaña
        jTabbedPane1.addChangeListener(e -> {
            int selectedIndex = jTabbedPane1.getSelectedIndex();
            if (selectedIndex == 2) {
                cargarCarrito();
            } else if (selectedIndex == 3) {
                cargarListasReproduccion();
            } else if (jTabbedPane1.getSelectedIndex() == 1) {
                cargarHistorialCompras();
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
        imglogoC = new javax.swing.JLabel();
        jTabbedPane1 = new javax.swing.JTabbedPane();
        jPanel1 = new javax.swing.JPanel();
        jlCrearCuenta1 = new javax.swing.JLabel();
        jScrollPane3 = new javax.swing.JScrollPane();
        jTable = new javax.swing.JTable();
        txtBuscar = new javax.swing.JTextField();
        btnBuscar = new javax.swing.JButton();
        jPanel4 = new javax.swing.JPanel();
        jlCrearCuenta4 = new javax.swing.JLabel();
        jScrollPane5 = new javax.swing.JScrollPane();
        jTableHistorial = new javax.swing.JTable();
        jPanel3 = new javax.swing.JPanel();
        jlCrearCuenta3 = new javax.swing.JLabel();
        jScrollPane4 = new javax.swing.JScrollPane();
        jTableCarrito = new javax.swing.JTable();
        btnPagar = new javax.swing.JButton();
        jlCrearCuenta5 = new javax.swing.JLabel();
        rdNequi = new javax.swing.JRadioButton();
        rdTarjeta = new javax.swing.JRadioButton();
        rdPse = new javax.swing.JRadioButton();
        rdDavi = new javax.swing.JRadioButton();
        jLabel1 = new javax.swing.JLabel();
        totalpago = new javax.swing.JLabel();
        btnEliminar = new javax.swing.JButton();
        btnEditar = new javax.swing.JButton();
        jPanel2 = new javax.swing.JPanel();
        jlCrearCuenta2 = new javax.swing.JLabel();
        btnNueva = new javax.swing.JButton();
        jlCrearCuenta6 = new javax.swing.JLabel();
        jScrollPane6 = new javax.swing.JScrollPane();
        listaPublica = new javax.swing.JList<>();
        jScrollPane7 = new javax.swing.JScrollPane();
        listaComprador = new javax.swing.JList<>();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jpComprador.setBackground(new java.awt.Color(89, 89, 89));

        lbUsuarioComp.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Img/Usuario logo.png"))); // NOI18N

        btnMisRecopilaciones.setBackground(new java.awt.Color(153, 153, 153));
        btnMisRecopilaciones.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        btnMisRecopilaciones.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Img/logo musica.png"))); // NOI18N
        btnMisRecopilaciones.setText("Mis recopilaciones");
        btnMisRecopilaciones.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnMisRecopilacionesActionPerformed(evt);
            }
        });

        btnCatalogo.setBackground(new java.awt.Color(153, 153, 153));
        btnCatalogo.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        btnCatalogo.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Img/logo audifonos.png"))); // NOI18N
        btnCatalogo.setText("Catálogo");
        btnCatalogo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCatalogoActionPerformed(evt);
            }
        });

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
        jpFondoComp.setForeground(new java.awt.Color(153, 153, 153));

        jSeparator3.setOrientation(javax.swing.SwingConstants.VERTICAL);

        imglogolabelC.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Img/Letra soplisong.png"))); // NOI18N

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

        jPanel4.setBackground(new java.awt.Color(51, 51, 51));

        jlCrearCuenta4.setFont(new java.awt.Font("Segoe UI", 1, 24)); // NOI18N
        jlCrearCuenta4.setForeground(new java.awt.Color(255, 255, 255));
        jlCrearCuenta4.setText("Historial de compras");

        jTableHistorial.setBackground(new java.awt.Color(89, 89, 89));
        jTableHistorial.setModel(new javax.swing.table.DefaultTableModel(
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
        jTableHistorial.setGridColor(new java.awt.Color(89, 89, 89));
        jTableHistorial.setSelectionBackground(new java.awt.Color(51, 51, 51));
        jScrollPane5.setViewportView(jTableHistorial);

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addComponent(jlCrearCuenta4)
                        .addGap(0, 378, Short.MAX_VALUE))
                    .addComponent(jScrollPane5, javax.swing.GroupLayout.DEFAULT_SIZE, 615, Short.MAX_VALUE))
                .addContainerGap())
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addGap(14, 14, 14)
                .addComponent(jlCrearCuenta4)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane5, javax.swing.GroupLayout.DEFAULT_SIZE, 277, Short.MAX_VALUE)
                .addContainerGap())
        );

        jTabbedPane1.addTab("tab4", jPanel4);

        jPanel3.setBackground(new java.awt.Color(51, 51, 51));

        jlCrearCuenta3.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jlCrearCuenta3.setForeground(new java.awt.Color(255, 255, 255));
        jlCrearCuenta3.setText("Métodos de pago:");

        jTableCarrito.setBackground(new java.awt.Color(89, 89, 89));
        jTableCarrito.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null}
            },
            new String [] {
                "Producto", "Nombre", "Cantidad", "Precio Unitario", "Total"
            }
        ));
        jTableCarrito.setGridColor(new java.awt.Color(89, 89, 89));
        jTableCarrito.setSelectionBackground(new java.awt.Color(51, 51, 51));
        jScrollPane4.setViewportView(jTableCarrito);

        btnPagar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Img/pago.png"))); // NOI18N
        btnPagar.setText("Pagar");
        btnPagar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnPagarActionPerformed(evt);
            }
        });

        jlCrearCuenta5.setFont(new java.awt.Font("Segoe UI", 1, 24)); // NOI18N
        jlCrearCuenta5.setForeground(new java.awt.Color(255, 255, 255));
        jlCrearCuenta5.setText("Carrito de compras");

        rdNequi.setForeground(new java.awt.Color(255, 255, 255));
        rdNequi.setText("Nequi");
        rdNequi.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                rdNequiActionPerformed(evt);
            }
        });

        rdTarjeta.setForeground(new java.awt.Color(255, 255, 255));
        rdTarjeta.setText("Tarjeta");
        rdTarjeta.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                rdTarjetaActionPerformed(evt);
            }
        });

        rdPse.setForeground(new java.awt.Color(255, 255, 255));
        rdPse.setText("PSE");
        rdPse.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                rdPseActionPerformed(evt);
            }
        });

        rdDavi.setForeground(new java.awt.Color(255, 255, 255));
        rdDavi.setText("DaviPlata");
        rdDavi.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                rdDaviActionPerformed(evt);
            }
        });

        jLabel1.setForeground(new java.awt.Color(255, 255, 255));
        jLabel1.setText("Total al pagar:");

        totalpago.setFont(new java.awt.Font("Segoe UI", 1, 16)); // NOI18N
        totalpago.setForeground(new java.awt.Color(255, 255, 255));
        totalpago.setText("txt");

        btnEliminar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Img/eliminar15px.png"))); // NOI18N
        btnEliminar.setText("Eliminar del carrito");

        btnEditar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Img/editar15px.png"))); // NOI18N
        btnEditar.setText("Editar cantidad");

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane4)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addComponent(btnPagar, javax.swing.GroupLayout.PREFERRED_SIZE, 292, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel3Layout.createSequentialGroup()
                                .addComponent(jlCrearCuenta5)
                                .addGap(0, 0, Short.MAX_VALUE))
                            .addGroup(jPanel3Layout.createSequentialGroup()
                                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                    .addComponent(btnEliminar, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(jlCrearCuenta3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(jPanel3Layout.createSequentialGroup()
                                        .addComponent(rdTarjeta, javax.swing.GroupLayout.PREFERRED_SIZE, 68, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGap(58, 58, 58)
                                        .addComponent(rdPse, javax.swing.GroupLayout.PREFERRED_SIZE, 68, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGap(42, 42, 42)
                                        .addComponent(rdNequi, javax.swing.GroupLayout.PREFERRED_SIZE, 68, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createSequentialGroup()
                                        .addComponent(btnEditar, javax.swing.GroupLayout.DEFAULT_SIZE, 154, Short.MAX_VALUE)
                                        .addGap(115, 115, 115)
                                        .addComponent(jLabel1)
                                        .addGap(6, 6, 6)))
                                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(rdDavi, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(totalpago))))
                        .addContainerGap())))
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jlCrearCuenta5)
                .addGap(4, 4, 4)
                .addComponent(jScrollPane4, javax.swing.GroupLayout.DEFAULT_SIZE, 180, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(totalpago)
                    .addComponent(btnEliminar)
                    .addComponent(btnEditar))
                .addGap(9, 9, 9)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jlCrearCuenta3)
                    .addComponent(rdTarjeta)
                    .addComponent(rdPse)
                    .addComponent(rdNequi)
                    .addComponent(rdDavi))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btnPagar, javax.swing.GroupLayout.PREFERRED_SIZE, 39, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(2, 2, 2))
        );

        jTabbedPane1.addTab("tab3", jPanel3);

        jPanel2.setBackground(new java.awt.Color(51, 51, 51));

        jlCrearCuenta2.setFont(new java.awt.Font("Segoe UI", 1, 24)); // NOI18N
        jlCrearCuenta2.setForeground(new java.awt.Color(255, 255, 255));
        jlCrearCuenta2.setText("Mis recopilaciones");

        btnNueva.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Img/mas 15px.png"))); // NOI18N
        btnNueva.setText("Nueva recopilación");
        btnNueva.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnNuevaActionPerformed(evt);
            }
        });

        jlCrearCuenta6.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jlCrearCuenta6.setForeground(new java.awt.Color(255, 255, 255));
        jlCrearCuenta6.setText("Recopilaciones públicas");

        listaPublica.setBackground(new java.awt.Color(89, 89, 89));
        listaPublica.setModel(new javax.swing.AbstractListModel<String>() {
            String[] strings = { "Item 1", "Item 2", "Item 3", "Item 4", "Item 5" };
            public int getSize() { return strings.length; }
            public String getElementAt(int i) { return strings[i]; }
        });
        listaPublica.setSelectionBackground(new java.awt.Color(51, 51, 51));
        jScrollPane6.setViewportView(listaPublica);

        listaComprador.setBackground(new java.awt.Color(89, 89, 89));
        listaComprador.setModel(new javax.swing.AbstractListModel<String>() {
            String[] strings = { "Item 1", "Item 2", "Item 3", "Item 4", "Item 5" };
            public int getSize() { return strings.length; }
            public String getElementAt(int i) { return strings[i]; }
        });
        listaComprador.setSelectionBackground(new java.awt.Color(51, 51, 51));
        jScrollPane7.setViewportView(listaComprador);

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addComponent(jlCrearCuenta2)
                .addGap(0, 0, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jScrollPane6)
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel2Layout.createSequentialGroup()
                        .addComponent(btnNueva, javax.swing.GroupLayout.PREFERRED_SIZE, 170, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel2Layout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jScrollPane7)
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addComponent(jlCrearCuenta6)
                                .addGap(0, 413, Short.MAX_VALUE)))))
                .addContainerGap())
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addComponent(jlCrearCuenta2)
                .addGap(7, 7, 7)
                .addComponent(btnNueva, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(9, 9, 9)
                .addComponent(jScrollPane7, javax.swing.GroupLayout.PREFERRED_SIZE, 87, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jlCrearCuenta6)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane6, javax.swing.GroupLayout.DEFAULT_SIZE, 127, Short.MAX_VALUE)
                .addContainerGap())
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
                            .addGroup(jpFondoCompLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                .addComponent(jSeparator2, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, 633, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jpFondoCompLayout.createSequentialGroup()
                                    .addComponent(imglogoC, javax.swing.GroupLayout.PREFERRED_SIZE, 70, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                    .addComponent(imglogolabelC, javax.swing.GroupLayout.PREFERRED_SIZE, 211, javax.swing.GroupLayout.PREFERRED_SIZE)))))
                    .addComponent(jTabbedPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 627, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(568, Short.MAX_VALUE))
        );
        jpFondoCompLayout.setVerticalGroup(
            jpFondoCompLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jpFondoCompLayout.createSequentialGroup()
                .addGap(12, 12, 12)
                .addGroup(jpFondoCompLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(imglogolabelC, javax.swing.GroupLayout.PREFERRED_SIZE, 42, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(imglogoC, javax.swing.GroupLayout.PREFERRED_SIZE, 57, javax.swing.GroupLayout.PREFERRED_SIZE))
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

    private void mostrarDialogoConfirmacion(int idVenta) {
        // Crear el diálogo
        javax.swing.JDialog dialogo = new javax.swing.JDialog(Comprador.this, "Confirmar Recepción", true);
        dialogo.setSize(400, 250);
        dialogo.setLayout(new java.awt.FlowLayout());
        dialogo.setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);

        // Etiqueta y campo para observación
        javax.swing.JLabel lblObservacion = new javax.swing.JLabel("Observación del producto:");
        javax.swing.JTextArea txtObservacion = new javax.swing.JTextArea(5, 30);
        txtObservacion.setLineWrap(true);
        txtObservacion.setWrapStyleWord(true);
        javax.swing.JScrollPane scrollObservacion = new javax.swing.JScrollPane(txtObservacion);

        // Botón para confirmar
        javax.swing.JButton btnConfirmar = new javax.swing.JButton("Confirmar Recepción");
        btnConfirmar.addActionListener(e -> {
            String observacion = txtObservacion.getText().trim();
            // Actualizar el estado de la venta a "entregado" y agregar observación
            VentaDAO ventaDAO = new VentaDAO();
            if (ventaDAO.actualizarEstadoYObservacion(idVenta, "entregado", observacion)) {
                JOptionPane.showMessageDialog(dialogo, "Recepción confirmada. Estado actualizado a 'entregado'.");
                
                dialogo.dispose();  // Cierra el diálogo
            } else {
                JOptionPane.showMessageDialog(dialogo, "Error al actualizar la venta.");
            }
        });

        // Agregar componentes al diálogo
        dialogo.add(lblObservacion);
        dialogo.add(scrollObservacion);
        dialogo.add(btnConfirmar);

        // Centrar y mostrar el diálogo
        dialogo.setLocationRelativeTo(Comprador.this);
        dialogo.setVisible(true);
    }

    // En Comprador.java, agrega este método auxiliar para obtener el nombre del producto
    private String obtenerNombreProducto(int idProducto, String tipo) {
        try {
            if ("vinilo".equalsIgnoreCase(tipo)) {
                Disco_viniloDAO dao = new Disco_viniloDAO();
                Disco_vinilo v = dao.buscarPorIdV(idProducto);
                return (v != null) ? v.getNombre() : "Desconocido";
            } else if ("mp3".equalsIgnoreCase(tipo)) {
                Disco_mp3DAO dao = new Disco_mp3DAO();
                Disco_mp3 m = dao.buscarPorIM(idProducto);
                return (m != null) ? m.getNombre() : "Desconocido";
            } else if ("cancion".equalsIgnoreCase(tipo) || "canción".equalsIgnoreCase(tipo)) {
                CancionDAO dao = new CancionDAO();
                Cancion c = dao.obtenerPorId(idProducto);
                return (c != null) ? c.getNombre() : "Desconocido";
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "Desconocido";
    }

    private void descargarFacturaPDF(int idVenta) {
        JFileChooser fileChooser = new JFileChooser();
        fileChooser.setSelectedFile(new java.io.File("Factura_Venta_" + idVenta + ".pdf"));
        int result = fileChooser.showSaveDialog(this);
        if (result == JFileChooser.APPROVE_OPTION) {
            try {
                com.itextpdf.text.Document document = new com.itextpdf.text.Document();
                PdfWriter.getInstance(document, new FileOutputStream(fileChooser.getSelectedFile()));
                document.open();
                document.add(new Paragraph("Factura de Venta ID: " + idVenta));
                VentaDAO ventaDAO = new VentaDAO();
                Detalle_ventaDAO detalleDAO = new Detalle_ventaDAO();
                Venta venta = ventaDAO.buscarPorIdVenta(idVenta);
                if (venta != null) {
                    document.add(new Paragraph("Fecha: " + venta.getFecha()));
                    document.add(new Paragraph("Total: " + venta.getTotal()));
                    document.add(new Paragraph("Método de Pago: " + venta.getMetodo_pago()));
                    List<Detalle_venta> detalles = detalleDAO.listarDetallesPorVenta(idVenta);
                    for (Detalle_venta detalle : detalles) {
                        String nombreProducto = obtenerNombreProducto(detalle.getId_producto(), detalle.getTipo());
                        document.add(new Paragraph("Producto: " + detalle.getTipo() + "; Nombre: " + nombreProducto + ", Cantidad: " + detalle.getCantidad() + ", Precio: " + detalle.getPrecio_unit()));
                    }
                }
                document.close();
                JOptionPane.showMessageDialog(this, "PDF descargado exitosamente.");
            } catch (Exception e) {
                JOptionPane.showMessageDialog(this, "Error al generar PDF: " + e.getMessage());
            }
        }
    }

    // Método para cargar el historial de compras
    private void cargarHistorialCompras() {
        int idComprador = Sesion.getIdComprador();
        if (idComprador == 0) {
            JOptionPane.showMessageDialog(this, "No hay comprador logueado.");
            return;
        }

        VentaDAO ventaDAO = new VentaDAO();
        Detalle_ventaDAO detalleDAO = new Detalle_ventaDAO();

        List<Venta> ventas = ventaDAO.listarVentasPorComprador(idComprador);

        DefaultTableModel modelo = (DefaultTableModel) jTableHistorial.getModel();  // Cambiado: usa jTableHistorial
        modelo.setRowCount(0); // Limpiar tabla

        for (Venta venta : ventas) {
            // Obtener detalles para concatenar productos
            List<Detalle_venta> detalles = detalleDAO.listarDetallesPorVenta(venta.getId_venta());
            StringBuilder productos = new StringBuilder();
            for (Detalle_venta detalle : detalles) {
                if (productos.length() > 0) {
                    productos.append(", ");
                }
                productos.append(detalle.getTipo()).append(" ID: ").append(detalle.getId_producto());
            }

            // Agregar fila a la tabla
            modelo.addRow(new Object[]{
                venta.getId_venta(), // Pedido (ID venta)
                venta.getFecha(), // Fecha
                productos.toString(), // Producto
                venta.getEstado(), // Estado
                "Descargar PDF" // Factura (botón)
            });
        }
    }

    private int extraerIdDeTexto(String texto) {
        try {
            // Buscar "ID: " y extraer el número después
            int index = texto.indexOf("ID: ");
            if (index != -1) {
                String idStr = texto.substring(index + 4).replace(")", "").trim();  // Remover ")" y espacios
                return Integer.parseInt(idStr);
            }
        } catch (NumberFormatException | StringIndexOutOfBoundsException ex) {
            // Manejar errores de parsing
        }
        return -1;  // Retornar -1 si falla
    }

    public void cargarListasReproduccion() {
        int idComprador = Sesion.getIdComprador();
        Lista_reproduccionDAO dao = new Lista_reproduccionDAO();

        // Cargar listas del comprador (sin nombre del creador, ya que es el mismo)
        List<Lista_reproduccion> listasComprador = dao.listarListasPorComprador(idComprador);
        javax.swing.DefaultListModel<String> modeloComprador = new javax.swing.DefaultListModel<>();
        for (Lista_reproduccion l : listasComprador) {
            modeloComprador.addElement(l.getNombre() + " (ID: " + l.getId_lista() + ")");
        }
        listaComprador.setModel(modeloComprador);

        // Cargar listas públicas (con nombre del creador)
        List<Lista_reproduccion> listasPublicas = dao.listarListasPublicas(idComprador);
        javax.swing.DefaultListModel<String> modeloPublica = new javax.swing.DefaultListModel<>();
        for (Lista_reproduccion l : listasPublicas) {
            modeloPublica.addElement(l.getNombre() + " (Creador: " + l.getNombre_creador() + ", ID: " + l.getId_lista() + ")");
        }
        listaPublica.setModel(modeloPublica);
    }


    private void btnCerrarSesionActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCerrarSesionActionPerformed
        Login log = new Login();
        log.setVisible(true);
        dispose();
    }//GEN-LAST:event_btnCerrarSesionActionPerformed

    private void cargarCarrito() {
        int idComprador = Sesion.getIdComprador();
        CarritoDAO dao = new CarritoDAO();
        List<Carrito> lista = dao.listarCarritoPorComprador(idComprador);

        javax.swing.table.DefaultTableModel modelo = (javax.swing.table.DefaultTableModel) jTableCarrito.getModel();
        modelo.setRowCount(0);  // Limpiar tabla

        double totalGeneral = 0;  // Variable para acumular el total general

        for (Carrito c : lista) {
            String nombreProducto = "";
            double total = c.getCantidad() * c.getPrecio_unitario();
            totalGeneral += total;  // Sumar al total general

            // Obtener id_vendedor
            int idVendedor = obtenerIdVendedorPorProducto(c.getId_producto(), c.getTipo());

            // Obtener nombre según tipo (código existente)
            if ("vinilo".equalsIgnoreCase(c.getTipo())) {
                Disco_viniloDAO dvDao = new Disco_viniloDAO();
                Disco_vinilo v = dvDao.buscarPorIdV(c.getId_producto());
                if (v != null) {
                    nombreProducto = v.getNombre();
                }
            } else if ("mp3".equalsIgnoreCase(c.getTipo())) {
                Disco_mp3DAO mpDao = new Disco_mp3DAO();
                Disco_mp3 m = mpDao.buscarPorIM(c.getId_producto());
                if (m != null) {
                    nombreProducto = m.getNombre();
                }
            } else if ("cancion".equalsIgnoreCase(c.getTipo()) || "canción".equalsIgnoreCase(c.getTipo())) {
                CancionDAO canDao = new CancionDAO();
                Cancion can = canDao.obtenerPorId(c.getId_producto());
                if (can != null) {
                    nombreProducto = can.getNombre();
                }
            }

            modelo.addRow(new Object[]{
                c.getId_item(),
                c.getTipo(),
                nombreProducto,
                c.getCantidad(),
                c.getPrecio_unitario(),
                total,
                idVendedor // Nueva columna: ID Vendedor
            });
        }

        // Actualizar el label con el total general formateado
        totalpago.setText("$" + String.format("%.2f", totalGeneral));

        // Forzar refrescamiento de la tabla
        modelo.fireTableDataChanged();
    }

    // Método auxiliar para obtener id_vendedor de un producto
    private int obtenerIdVendedorPorProducto(int idProducto, String tipo) {
        try {
            if ("vinilo".equalsIgnoreCase(tipo)) {
                Disco_viniloDAO dao = new Disco_viniloDAO();
                Disco_vinilo v = dao.buscarPorIdV(idProducto);
                return (v != null) ? v.getId_vendedor() : -1;  // Asumiendo que Disco_vinilo tiene getId_vendedor()
            } else if ("mp3".equalsIgnoreCase(tipo)) {
                Disco_mp3DAO dao = new Disco_mp3DAO();
                Disco_mp3 m = dao.buscarPorIM(idProducto);
                return (m != null) ? m.getId_vendedor() : -1;  // Asumiendo que Disco_mp3 tiene getId_vendedor()
            } else if ("cancion".equalsIgnoreCase(tipo) || "canción".equalsIgnoreCase(tipo)) {
                CancionDAO dao = new CancionDAO();
                Cancion c = dao.obtenerPorId(idProducto);
                return (c != null) ? c.getId_vendedor() : -1;  // Asumiendo que Cancion tiene getId_vendedor()
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return -1;
    }


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

    private void btnMisRecopilacionesActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnMisRecopilacionesActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_btnMisRecopilacionesActionPerformed

    private void btnCatalogoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCatalogoActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_btnCatalogoActionPerformed

    private void rdNequiActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_rdNequiActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_rdNequiActionPerformed

    private void rdTarjetaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_rdTarjetaActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_rdTarjetaActionPerformed

    private void rdPseActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_rdPseActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_rdPseActionPerformed

    private void rdDaviActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_rdDaviActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_rdDaviActionPerformed


    private void btnPagarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnPagarActionPerformed

        // Verificar si hay items en el carrito
        if (jTableCarrito.getRowCount() == 0) {
            JOptionPane.showMessageDialog(this, "El carrito está vacío.");
            return;
        }

        // Verificar método de pago seleccionado
        String metodoPago = null;
        if (rdTarjeta.isSelected()) {
            metodoPago = "Tarjeta";
        } else if (rdNequi.isSelected()) {
            metodoPago = "Nequi";
        } else if (rdPse.isSelected()) {
            metodoPago = "PSE";
        } else if (rdDavi.isSelected()) {
            metodoPago = "DaviPlata";
        } else {
            JOptionPane.showMessageDialog(this, "Selecciona un método de pago.");
            return;
        }

        int idComprador = Sesion.getIdComprador();
        CarritoDAO carritoDao = new CarritoDAO();
        VentaDAO ventaDao = new VentaDAO();
        Detalle_ventaDAO detalleDao = new Detalle_ventaDAO();

        // Verificar que todos los productos sean del mismo vendedor
        int idVendedorEsperado = -1;
        boolean mismoVendedor = true;
        for (int i = 0; i < jTableCarrito.getRowCount(); i++) {
            int idItem = (Integer) jTableCarrito.getValueAt(i, 0);
            Carrito item = carritoDao.buscarPorIdItem(idItem);
            if (item == null) {
                JOptionPane.showMessageDialog(this, "Error al obtener item del carrito.");
                return;
            }
            int idVendedorProducto = obtenerIdVendedorPorProducto(item.getId_producto(), item.getTipo());
            if (idVendedorProducto == -1) {
                JOptionPane.showMessageDialog(this, "Error al obtener vendedor para " + item.getTipo() + ".");
                return;
            }
            if (idVendedorEsperado == -1) {
                idVendedorEsperado = idVendedorProducto;
            } else if (idVendedorProducto != idVendedorEsperado) {
                mismoVendedor = false;
                break;
            }
        }
        if (!mismoVendedor) {
            JOptionPane.showMessageDialog(this, "Todos los productos deben ser del mismo vendedor para realizar la compra.");
            return;
        }

        // Calcular total general del carrito usando streams para mayor eficiencia
        double totalGeneral = java.util.stream.IntStream.range(0, jTableCarrito.getRowCount())
                .mapToObj(i -> {
                    int idItem = (Integer) jTableCarrito.getValueAt(i, 0);
                    Carrito item = carritoDao.buscarPorIdItem(idItem);
                    return item != null ? item.getCantidad() * item.getPrecio_unitario() : 0.0;
                })
                .mapToDouble(Double::doubleValue)
                .sum();

        // Crear objeto Venta
        Venta venta = new Venta();
        venta.setId_comprador(idComprador);
        venta.setFecha(new java.util.Date());
        venta.setTotal(totalGeneral);
        venta.setMetodo_pago(metodoPago);
        venta.setEstado("pendiente");
        venta.setObservacion(null);

        // Insertar venta
        if (!ventaDao.insertarVenta(venta)) {
            JOptionPane.showMessageDialog(this, "Error al crear venta.");
            return;
        }

        int idVenta = venta.getId_venta();

        // Insertar detalles de venta para cada item del carrito
        for (int i = 0; i < jTableCarrito.getRowCount(); i++) {
            int idItem = (Integer) jTableCarrito.getValueAt(i, 0);
            Carrito item = carritoDao.buscarPorIdItem(idItem);
            if (item == null) {
                continue;
            }

            // Obtener id_vendedor del producto específico
            int idVendedorProducto = obtenerIdVendedorPorProducto(item.getId_producto(), item.getTipo());
            if (idVendedorProducto == -1) {
                JOptionPane.showMessageDialog(this, "Error al obtener vendedor para " + item.getTipo() + ".");
                return;
            }

            Detalle_venta detalle = new Detalle_venta();
            detalle.setId_venta(idVenta);
            detalle.setId_vendedor(idVendedorProducto);
            detalle.setId_producto(item.getId_producto());
            detalle.setTipo(item.getTipo());
            detalle.setCantidad(item.getCantidad());
            detalle.setPrecio_unit(item.getPrecio_unitario());
            detalle.setTotal(item.getCantidad() * item.getPrecio_unitario());

            if (!detalleDao.insertarDetalleVenta(detalle)) {
                JOptionPane.showMessageDialog(this, "Error al insertar detalle para " + item.getTipo() + ".");
                return;
            }
        }

        // Cerrar carrito (cambiar estado a "pagado")
        carritoDao.marcarCarritoPagado(idComprador);

        // Recargar carrito y actualizar total
        cargarCarrito();

        // Mensaje de éxito
        JOptionPane.showMessageDialog(this, "Venta realizada exitosamente.");


    }//GEN-LAST:event_btnPagarActionPerformed

    private void btnNuevaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnNuevaActionPerformed
        CrearReco crearReco = new CrearReco(this);
        crearReco.setVisible(true);
        crearReco.setLocationRelativeTo(null);
        crearReco.setResizable(false);
    }//GEN-LAST:event_btnNuevaActionPerformed

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
    private javax.swing.JButton btnEditar;
    private javax.swing.JButton btnEliminar;
    private javax.swing.JButton btnHistorialDeCompras;
    private javax.swing.JButton btnMisRecopilaciones;
    private javax.swing.JButton btnNueva;
    private javax.swing.JButton btnPagar;
    private javax.swing.JLabel imglogoC;
    private javax.swing.JLabel imglogolabelC;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JScrollPane jScrollPane4;
    private javax.swing.JScrollPane jScrollPane5;
    private javax.swing.JScrollPane jScrollPane6;
    private javax.swing.JScrollPane jScrollPane7;
    private javax.swing.JSeparator jSeparator1;
    private javax.swing.JSeparator jSeparator2;
    private javax.swing.JSeparator jSeparator3;
    private javax.swing.JTabbedPane jTabbedPane1;
    private javax.swing.JTable jTable;
    private javax.swing.JTable jTableCarrito;
    private javax.swing.JTable jTableHistorial;
    private javax.swing.JLabel jlCrearCuenta1;
    private javax.swing.JLabel jlCrearCuenta2;
    private javax.swing.JLabel jlCrearCuenta3;
    private javax.swing.JLabel jlCrearCuenta4;
    private javax.swing.JLabel jlCrearCuenta5;
    private javax.swing.JLabel jlCrearCuenta6;
    private javax.swing.JPanel jpComprador;
    private javax.swing.JPanel jpFondoComp;
    private javax.swing.JLabel lbComprador;
    private javax.swing.JLabel lbUsuarioComp;
    private javax.swing.JList<String> listaComprador;
    private javax.swing.JList<String> listaPublica;
    private javax.swing.JRadioButton rdDavi;
    private javax.swing.JRadioButton rdNequi;
    private javax.swing.JRadioButton rdPse;
    private javax.swing.JRadioButton rdTarjeta;
    private javax.swing.JLabel totalpago;
    private javax.swing.JTextField txtBuscar;
    // End of variables declaration//GEN-END:variables
}
