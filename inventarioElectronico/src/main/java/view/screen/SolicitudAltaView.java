package view.screen;
import java.awt.Dimension;
import java.awt.event.ActionListener;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;

@SuppressWarnings("serial")
public class SolicitudAltaView extends JFrame {
	private JFrame frame;
	private JTextField txtTipo;
	private JTextField txtMarca;
	private JTextField txtModelo;
	private JTextField txtNumeroSerie;
	private JTextField txtEspecificaciones;
	private JTextField txtValor;
	private JTextField txtCodigoProducto;
	private JButton btnGuardar;
	private JButton btnCancelar;
	
	public SolicitudAltaView() {
		initialize();
	}
	
	private void initialize() {
		
		frame = new JFrame("Solicitud de Alta - Inventario Electronico");
        frame.setSize(400, 400);
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        BoxLayout boxLayout = new BoxLayout(frame.getContentPane(), BoxLayout.Y_AXIS);
        frame.setLayout(boxLayout);
        
        frame.add(Box.createVerticalStrut(15));
        
        JLabel lblTipo = new JLabel("Tipo Activo:");
        lblTipo.setAlignmentX(0.5f);
        frame.add(lblTipo);
        
        txtTipo = new JTextField();
        txtTipo.setAlignmentX(0.5f);
        txtTipo.setPreferredSize(new Dimension(200, 30));
        txtTipo.setMaximumSize(txtTipo.getPreferredSize());
        frame.add(txtTipo);
        frame.add(Box.createVerticalStrut(5));
        
        
        JLabel lblMarca = new JLabel("Marca:");
        lblMarca.setAlignmentX(0.5f);
        frame.add(lblMarca);
        
        txtMarca = new JTextField();
        txtMarca.setAlignmentX(0.5f);
        txtMarca.setPreferredSize(new Dimension(200, 30));
        txtMarca.setMaximumSize(txtMarca.getPreferredSize());
        frame.add(txtMarca);
        frame.add(Box.createVerticalStrut(5));
        
        
        JLabel lblModelo = new JLabel("Modelo:");
        lblModelo.setAlignmentX(0.5f);
        frame.add(lblModelo);
        
        txtModelo = new JTextField();
        txtModelo.setAlignmentX(0.5f);
        txtModelo.setPreferredSize(new Dimension(200, 30));
        txtModelo.setMaximumSize(txtModelo.getPreferredSize());
        frame.add(txtModelo);
        frame.add(Box.createVerticalStrut(5));
        
        
        JLabel lblNumeroSerie = new JLabel("Numero de Serie:");
        lblNumeroSerie.setAlignmentX(0.5f);
        frame.add(lblNumeroSerie);
        
        txtNumeroSerie = new JTextField();
        txtNumeroSerie.setAlignmentX(0.5f);
        txtNumeroSerie.setPreferredSize(new Dimension(200, 30));
        txtNumeroSerie.setMaximumSize(txtNumeroSerie.getPreferredSize());
        frame.add(txtNumeroSerie);
        frame.add(Box.createVerticalStrut(5));
        
        
        JLabel lblEspecificaciones = new JLabel("Especificaciones:");
        lblEspecificaciones.setAlignmentX(0.5f);
        frame.add(lblEspecificaciones);
        
        txtEspecificaciones = new JTextField();
        txtEspecificaciones.setAlignmentX(0.5f);
        txtEspecificaciones.setPreferredSize(new Dimension(200, 30));
        txtEspecificaciones.setMaximumSize(txtEspecificaciones.getPreferredSize());
        frame.add(txtEspecificaciones);
        frame.add(Box.createVerticalStrut(5));
        
        
        JLabel lblValor = new JLabel("Valor:");
        lblValor.setAlignmentX(0.5f);
        frame.add(lblValor);
        
        txtValor = new JTextField();
        txtValor.setAlignmentX(0.5f);
        txtValor.setPreferredSize(new Dimension(200, 30));
        txtValor.setMaximumSize(txtValor.getPreferredSize());
        frame.add(txtValor);
        frame.add(Box.createVerticalStrut(5));
        
        
        JLabel lblCodigoProducto = new JLabel("Codigo de Producto:");
        lblCodigoProducto.setAlignmentX(0.5f);
        frame.add(lblCodigoProducto);
        
        txtCodigoProducto = new JTextField();
        txtCodigoProducto.setAlignmentX(0.5f);
        txtCodigoProducto.setPreferredSize(new Dimension(200, 30));
        txtCodigoProducto.setMaximumSize(txtCodigoProducto.getPreferredSize());
        frame.add(txtCodigoProducto);
        frame.add(Box.createVerticalStrut(5));
        
        JButton btnGuardar = new JButton("Guardar");
        btnGuardar.setAlignmentX(0.5f);
        frame.add(btnGuardar);
        frame.add(Box.createVerticalStrut(5));
        
        JButton btnCancelar = new JButton("Cancelar");
        btnCancelar.setAlignmentX(0.5f);
        frame.add(btnCancelar);
        frame.add(Box.createVerticalStrut(5));
        
        frame.setVisible(true);
        
	}
	
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
	
	public String getValor() {
		return txtValor.getText();
	}
	
	public String getCodigoProducto() {
		return txtCodigoProducto.getText();
	}
	
	public void setBtnGuardar(ActionListener actionListener) {
		btnGuardar.addActionListener(actionListener);
	}
	
	public void setBtnCancelar(ActionListener actionListener) {
		btnCancelar.addActionListener(actionListener);
	}


	
}
