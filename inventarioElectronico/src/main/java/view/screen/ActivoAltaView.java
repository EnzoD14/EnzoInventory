package view.screen;

import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

@SuppressWarnings("serial")
public class ActivoAltaView extends JFrame {
	 	private JTextField txtTipo;
	    private JTextField txtMarca;
	    private JTextField txtModelo;
	    private JTextField txtNumeroSerie;
	    private JTextField txtEspecificaciones;
	    private JTextField txtValor;
	    private JTextField txtCodigoProducto;
	    private JTextField txtGarantia;
	    private JTextField txtBuscarFactura;
	    private JComboBox<String> comboEstado;
	    private JButton btnBuscarFactura;
	    private JButton btnGuardar;
	    private JButton btnCancelar;

	    public ActivoAltaView() {
	        setTitle("Alta de Activo - Inventario Electronico");
	        setSize(1000, 500);
	        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
	        setLocationRelativeTo(null);

	        // Inicializar componentes
	        txtTipo = new JTextField(20);
	        txtMarca = new JTextField(20);
	        txtModelo = new JTextField(20);
	        txtNumeroSerie = new JTextField(20);
	        txtEspecificaciones = new JTextField(100);
	        txtValor = new JTextField(20);
	        txtCodigoProducto = new JTextField(20);
	        txtGarantia = new JTextField(20);
	        txtBuscarFactura = new JTextField(20);
	        comboEstado = new JComboBox<>(new String[] {"Utilizada", "Backup", "Reparacion", "Baja"});
	        btnBuscarFactura = new JButton("Buscar Factura");
	        btnGuardar = new JButton("Guardar");
	        btnCancelar = new JButton("Cancelar");

	        // Diseño de la interfaz
	        JPanel panel = new JPanel(new GridLayout(5, 2));
	        panel.add(new JLabel("Tipo:"));
	        panel.add(txtTipo);
	        panel.add(new JLabel("Marca:"));
	        panel.add(txtMarca);
	        panel.add(new JLabel("Modelo:"));
	        panel.add(txtModelo);
	        panel.add(new JLabel("Número de Serie:"));
	        panel.add(txtNumeroSerie);
	        panel.add(new JLabel("Especificaciones:"));
	        panel.add(txtEspecificaciones);
	        panel.add(new JLabel("Valor:"));
	        panel.add(txtValor);
	        panel.add(new JLabel("Codigo de Producto:"));
	        panel.add(txtCodigoProducto);
	        panel.add(new JLabel("Anios Garantia:"));
	        panel.add(txtGarantia);
	        panel.add(new JLabel("Estado:"));
	        panel.add(comboEstado);
	        panel.add(new JLabel("Buscar Factura:"));
	        panel.add(txtBuscarFactura);
	        txtBuscarFactura.setEditable(false);
	        panel.add(btnBuscarFactura);
	        panel.add(btnGuardar);
	        panel.add(btnCancelar);

	        getContentPane().add(panel, BorderLayout.CENTER);
	    }

	    // Getters para los campos y botones
	    public String getTipo() {
	        return txtTipo.getText();
	    }

	    public String getMarca() {
	        return txtMarca.getText();
	    }

	    public String getModelo() {
	        return txtModelo.getText();
	    }

	    public String getNumeroSerie() {
	        return txtNumeroSerie.getText();
	    }
	    
	    public String getEspecificaciones() {
	        return txtEspecificaciones.getText();
	    }
	    
	    public String getCodigoProducto() {
	        return txtCodigoProducto.getText();
	    }
	    
	    public String getGarantia() {
	        return txtGarantia.getText();
	    }
	    
	    public String getValor() {
	    	return txtValor.getText();
	    }
	    
	    public String getEstado() {
	    	return comboEstado.getSelectedItem().toString();
	    }
	    
		public String getBuscarFactura() {
			return txtBuscarFactura.getText();
		}
		
		public void setBuscarFactura(String nroFactura) {
			txtBuscarFactura.setText(nroFactura);
		}
		
		public void setControladorBuscarFactura(ActionListener controlador) {
			btnBuscarFactura.addActionListener(controlador);
		}

	    public void setControladorGuardar(ActionListener controlador) {
	        btnGuardar.addActionListener(controlador);
	    }

	    public void setControladorCancelar(ActionListener controlador) {
	        btnCancelar.addActionListener(controlador);
	    }
	    
		public void mostrarMensaje(String mensaje) {
			System.out.println(mensaje);
		}

		public void setBtnBuscarFacturaEnabled(boolean b) {
			// TODO Auto-generated method stub
			btnBuscarFactura.setEnabled(b);
		}
	}

