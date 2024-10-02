package view.screen;

import java.awt.GridLayout;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;
import modelo.Proveedor;
import modelo.dao.impl.ProveedorDAOimpl;

@SuppressWarnings("serial")
public class ProveedorModificacionView extends JFrame {
	private JTextField nombreField;
    private JTextField razonSocialField;
    private JTextField emailField;
    private JTextField telefonoField;
    private JButton guardarButton;
    private JButton cancelarButton;
    private ProveedorDAOimpl proveedorDAO;
    private Proveedor proveedor;

    public ProveedorModificacionView(Proveedor proveedor) {
        this.proveedor = proveedor;
        this.proveedorDAO = new ProveedorDAOimpl();
        
        setTitle("Modificación de Proveedor");
        setSize(400, 200);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLayout(new GridLayout(5, 2));
        
        add(new JLabel("Nombre:"));
        nombreField = new JTextField(proveedor.getNombre());
        add(nombreField);

        add(new JLabel("Razón Social:"));
        razonSocialField = new JTextField(proveedor.getRazonSocial());
        add(razonSocialField);

        add(new JLabel("Email:"));
        emailField = new JTextField(proveedor.getEmail());
        add(emailField);

        add(new JLabel("Teléfono:"));
        telefonoField = new JTextField(proveedor.getTelefono());
        add(telefonoField);

        guardarButton = new JButton("Guardar");
        add(guardarButton);
        
        cancelarButton = new JButton("Cancelar");
        add(cancelarButton);
    }
    
	public String getNombre() {
		return nombreField.getText();
	}
	
	public String getRazonSocial() {
		return razonSocialField.getText();
	}
	
	public String getEmail() {
		return emailField.getText();
	}
	
	public String getTelefono() {
		return telefonoField.getText();
	}
    
	public void setGuardarButtonActionListener(ActionListener actionListener) {
		guardarButton.addActionListener(actionListener);
	}
	
	public void setCancelarButtonActionListener(ActionListener actionListener) {
		cancelarButton.addActionListener(actionListener);
	}
    
	public void setViewVisible(boolean visible) {
		setVisible(visible);
	}
}
