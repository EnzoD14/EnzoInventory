package view.screen;

import java.awt.GridLayout;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

@SuppressWarnings("serial")
public class ProveedorBajaView extends JFrame {
    private JTextField razonSocialField;
    private JButton buscarButton;
    private JButton cancelarButton;

    public ProveedorBajaView() {
        setTitle("Baja de Proveedor - Inventario Electronico");
        setSize(300, 200);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);

        JPanel panel = new JPanel();
        panel.setLayout(new GridLayout(3, 2));

        panel.add(new JLabel("Razon Social:"));
        razonSocialField = new JTextField(20);
        panel.add(razonSocialField);

        buscarButton = new JButton("Buscar");
        panel.add(buscarButton);
        
        cancelarButton = new JButton("Cancelar"); // Inicializar el nuevo botón
        panel.add(cancelarButton); // Agregar el nuevo botón al panel

        add(panel);
    }

    public String getRazonSocial() {
        return razonSocialField.getText();
    }

    public void setBuscarButtonActionListener(ActionListener actionListener) {
        buscarButton.addActionListener(actionListener);
    }
    
    public void setCancelarButtonActionListener(ActionListener actionListener) {
        cancelarButton.addActionListener(actionListener);
    }
}
