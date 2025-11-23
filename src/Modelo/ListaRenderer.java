package Modelo; 

import java.awt.Component;
import javax.swing.DefaultListCellRenderer;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.ListCellRenderer;

public class ListaRenderer extends DefaultListCellRenderer implements ListCellRenderer<Object> {
    private ImageIcon icono;

    public ListaRenderer() {
        // Cargar el icono por defecto (logo de música)
        java.net.URL url = getClass().getResource("/Img/letras-de-canciones.png");
        if (url != null) {
            icono = new ImageIcon(url);
            // Escalar a 20x20 píxeles
            icono = new ImageIcon(icono.getImage().getScaledInstance(20, 20, java.awt.Image.SCALE_SMOOTH));
        } else {
            // Fallback si no se encuentra
            icono = null;
        }
    }

    @Override
    public Component getListCellRendererComponent(JList<?> list, Object value, int index, boolean isSelected, boolean cellHasFocus) {
        JLabel label = (JLabel) super.getListCellRendererComponent(list, value, index, isSelected, cellHasFocus);
        
        // Establecer el icono por defecto
        label.setIcon(icono);
        
        // Establecer el texto
        label.setText((String) value);
        
        // Alinear texto a la derecha del icono
        label.setHorizontalTextPosition(JLabel.RIGHT);
        label.setIconTextGap(10);  // Espacio entre icono y texto
        
        return label;
    }
}