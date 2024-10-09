package view.screen;

import java.awt.Dimension;
import java.awt.event.ActionListener;

import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;


@SuppressWarnings({ "serial" })
public class SolicitudBajaView extends JFrame {
	private JFrame frame;
	private JButton btnGuardar;
	private JButton btnCancelar;
	private JButton btnBuscar;
	private JTextField txtBusqueda;
	private JComboBox<String> cmbMotivoBaja;

	public SolicitudBajaView() {
		initialize();
	}

	private void initialize() {
        
        frame = new JFrame("Solicitud de Baja - Inventario Electronico");
        frame.setSize(400, 400);
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        BoxLayout boxLayout = new BoxLayout(frame.getContentPane(), BoxLayout.Y_AXIS);
        frame.getContentPane().setLayout(boxLayout);
        
        frame.add(Box.createVerticalStrut(15));
        
        JLabel lblActivoBaja = new JLabel("Activo a dar de baja:");
        lblActivoBaja.setAlignmentX(0.5f);
        frame.add(lblActivoBaja);
        
        txtBusqueda = new JTextField();
        txtBusqueda.setAlignmentX(0.5f);
        txtBusqueda.setPreferredSize(new Dimension(200, 30));
        txtBusqueda.setMaximumSize(txtBusqueda.getPreferredSize());
        txtBusqueda.setColumns(10);
        frame.add(txtBusqueda);
        frame.add(Box.createVerticalStrut(5));
        
        btnBuscar = new JButton("Buscar");
        btnBuscar.setAlignmentX(0.5f);
        frame.add(btnBuscar);
        
        frame.add(Box.createVerticalStrut(10));
        
        JLabel lblCodigoProducto = new JLabel("Motivo de baja:");
        lblCodigoProducto.setAlignmentX(0.5f);
        frame.add(lblCodigoProducto);
        
        String[] motivoBaja = {"Extravio", "Robo", "Obsolecencia", "Venta", "Otros", "No"};
        cmbMotivoBaja = new JComboBox<>(motivoBaja);
        cmbMotivoBaja.setAlignmentX(0.5f);
        cmbMotivoBaja.setPreferredSize(new Dimension(200, 30));
        cmbMotivoBaja.setMaximumSize(cmbMotivoBaja.getPreferredSize());
        frame.add(cmbMotivoBaja);
        frame.add(Box.createVerticalStrut(5));
        cmbMotivoBaja.setSelectedIndex(5);
        
        btnGuardar = new JButton("Guardar");
        btnGuardar.setAlignmentX(0.5f);
        btnGuardar.setEnabled(false);
        frame.add(btnGuardar);
        frame.add(Box.createVerticalStrut(5));
        
        btnCancelar = new JButton("Cancelar");
        btnCancelar.setAlignmentX(0.5f);
        frame.add(btnCancelar);
        frame.add(Box.createVerticalStrut(5));
        
        frame.setVisible(true);
	}
	
	public String getMotivoBaja() {
        return cmbMotivoBaja.getSelectedItem().toString();
	}
	
	public String getBusqueda() {
		return txtBusqueda.getText();
	}
	
	public void setBusqueda(String busqueda) {
		txtBusqueda.setText(busqueda);
	}
	
	public void setBusquedaEnabled(boolean enabled) {
		txtBusqueda.setEnabled(enabled);
	}
	
	public void setBtnGuardarEnabled(boolean enabled) {
		btnGuardar.setEnabled(enabled);
	}
	
	public void setBtnGuardar(ActionListener actionListener) {
		btnGuardar.addActionListener(actionListener);
	}
	
	public void setBtnCancelar(ActionListener actionListener) {
		btnCancelar.addActionListener(actionListener);
	}
	
	public void setBtnBuscar(ActionListener actionListener) {
		btnBuscar.addActionListener(actionListener);
	}
}
