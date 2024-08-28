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

public class SolicitudAltaView extends JFrame {
	private JTextField txtTipo;
    private JTextField txtMarca;
    private JTextField txtModelo;
    private JTextField txtNumeroSerie;
    private JTextField txtEspecificaciones;
    private JTextField txtFechaCompra;
    private JTextField txtValor;
    private JTextField txtCodigoProducto;
    private JTextField txtCodigoProveedor;
    private JTextField txtNroFactura;
    private JTextField txtGarantia;
    private JComboBox<String> comboBackup;
    private JButton btnGuardar;
    private JButton btnCancelar;

    public SolicitudAltaView() {
        setTitle("Solicitud de Alta - Inventario Electronico");
        setSize(600, 500);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);

        // Inicializar componentes
        txtTipo = new JTextField(20);
        txtMarca = new JTextField(20);
        txtModelo = new JTextField(20);
        txtNumeroSerie = new JTextField(20);
        txtEspecificaciones = new JTextField(100);
        txtFechaCompra = new JTextField(20);
        txtValor = new JTextField(20);
        txtCodigoProducto = new JTextField(20);
        txtCodigoProveedor = new JTextField(20);
        txtNroFactura = new JTextField(20);
        txtGarantia = new JTextField(20);
        comboBackup = new JComboBox<>(new String[] {"No", "Si"});
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
        panel.add(new JLabel("Fecha de Compra (YYYY-MM-DD):"));
        panel.add(txtFechaCompra);
        panel.add(new JLabel("Valor:"));
        panel.add(txtValor);
        panel.add(new JLabel("Codigo de Producto:"));
        panel.add(txtCodigoProducto);
        panel.add(new JLabel("Codigo de Proveedor:"));
        panel.add(txtCodigoProveedor);
        panel.add(new JLabel("Numero de factura: "));
        panel.add(txtNroFactura);
        panel.add(new JLabel("Anios Garantia:"));
        panel.add(txtGarantia);
        panel.add(new JLabel("Es para backup?"));
        panel.add(comboBackup);
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
    
    public String getCodigoProveedor() {
        return txtCodigoProveedor.getText();
    }
    
    public String getGarantia() {
        return txtGarantia.getText();
    }
    
    public String getFechaCompra() {
    	return txtFechaCompra.getText();
    }
    
    public String getNroFactura() {
    	return txtNroFactura.getText();
    }
    
    public String getValor() {
    	return txtValor.getText();
    }
    
    public boolean getEsBackup() {
    	return comboBackup.getSelectedItem().equals("Si");
    }

    public void setControladorGuardar(ActionListener controlador) {
        btnGuardar.addActionListener(controlador);
    }

    public void setControladorCancelar(ActionListener controlador) {
        btnCancelar.addActionListener(controlador);
    }
}
