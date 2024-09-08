package view.screen;

import java.awt.GridLayout;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

@SuppressWarnings("serial")
public class CompraBusquedaView extends JFrame {
    private JTextField nroFacturaField;
    private JButton buscarButton;
    private JButton cancelarButton;

    public CompraBusquedaView() {
        setTitle("Busqueda Compra - Inventario Electronico");
        setSize(300, 200);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        JPanel panel = new JPanel();
        panel.setLayout(new GridLayout(3, 2));

        panel.add(new JLabel("Numero Factura:"));
        nroFacturaField = new JTextField(20);
        panel.add(nroFacturaField);

        buscarButton = new JButton("Buscar");
        panel.add(buscarButton);
        
        cancelarButton = new JButton("Cancelar"); // Inicializar el nuevo botón
        panel.add(cancelarButton); // Agregar el nuevo botón al panel

        add(panel);
    }

    public String getNroFactura() {
        return nroFacturaField.getText();
    }

    public void setBuscarButtonActionListener(ActionListener actionListener) {
        buscarButton.addActionListener(actionListener);
    }
    
    public void setCancelarButtonActionListener(ActionListener actionListener) {
        cancelarButton.addActionListener(actionListener);
    }
}
