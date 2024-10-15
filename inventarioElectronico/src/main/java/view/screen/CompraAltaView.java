package view.screen;

import java.awt.GridLayout;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;

import com.toedter.calendar.JDateChooser;

import modelo.Proveedor;
import modelo.dao.impl.ProveedorDAOimpl;

@SuppressWarnings("serial")
public class CompraAltaView extends JFrame {
    private JDateChooser fechaCompraChooser;
    private JTextField numFacturaField;
    private JComboBox<Proveedor> proveedoresComboBox;
    private JButton grabarCompraButton;
    private JButton cancelarCompraButton;
	
    public CompraAltaView() {
        setTitle("Alta de Compras - Inventario Electronico");
        setSize(500, 200);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new GridLayout(5, 2));

        // Fecha de la compra
        add(new JLabel("Fecha de la compra:"));
        fechaCompraChooser = new JDateChooser();
        add(fechaCompraChooser);

        // Número de factura
        add(new JLabel("Número de factura:"));
        numFacturaField = new JTextField();
        add(numFacturaField);

        // Lista de proveedores
        add(new JLabel("Proveedor:"));
        proveedoresComboBox = new JComboBox<>();
        fillProveedoresComboBox();
        add(proveedoresComboBox);
        
        grabarCompraButton = new JButton("Grabar Compra");
        add(grabarCompraButton);

        cancelarCompraButton = new JButton("Cancelar Compra");
        add(cancelarCompraButton);
        
        setVisible(true);
    }

    private void fillProveedoresComboBox() {
        ProveedorDAOimpl proveedorDAO = new ProveedorDAOimpl();
        for (Proveedor proveedor : proveedorDAO.getAllProveedores()) {
            proveedoresComboBox.addItem(proveedor);
        }
    }
 
    public JDateChooser getFechaCompraChooser() {
    	return fechaCompraChooser;
    }
	
	public JTextField getNumFacturaField() {
		return numFacturaField;
	}
	
	public JComboBox<Proveedor> getProveedoresComboBox() {
		return proveedoresComboBox;
	}
	
	public void setNumFacturaField(JTextField numFacturaField) {
		this.numFacturaField = numFacturaField;
	}
	
	public void setProveedoresComboBox(JComboBox<Proveedor> proveedoresComboBox) {
		this.proveedoresComboBox = proveedoresComboBox;
	}
	
	public void setGrabarCompraButton(ActionListener actionListener) {
		grabarCompraButton.addActionListener(actionListener);
	}
	
	public void setCancelarCompraButton(ActionListener actionListener) {
		cancelarCompraButton.addActionListener(actionListener);
	}
	
	public JButton getGrabarCompraButton() {
        return grabarCompraButton;
    }

    public JButton getCancelarCompraButton() {
        return cancelarCompraButton;
    }
	
	@Override
	public void setVisible(boolean visible) {
		super.setVisible(visible);
	}
}
