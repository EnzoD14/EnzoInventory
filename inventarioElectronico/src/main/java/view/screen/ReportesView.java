package view.screen;

import java.awt.BorderLayout;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import modelo.Usuario;

@SuppressWarnings("serial")
public class ReportesView extends JFrame{
	private Usuario usuarioLogin;
	private JButton btnReporte1;
	private JButton btnReporte2;
	private JButton btnReporte3;
	private JButton btnReporte4;
	private JButton btnReporte5;
	private JButton btnReporte6;
	private JButton btnReporte7;
	private JFrame frame;

	public ReportesView(Usuario usuario) {
		this.usuarioLogin = usuario;
		initialize();
	}

	private void initialize() {
		System.out.println("asdasd");
		frame = new JFrame("Reportes - Inventario Electronico");
		frame.setSize(400, 450);
		frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

		JMenuBar menuBar = new JMenuBar();
		JMenu menuArchivo = new JMenu("Archivo");

		JMenuItem itemModulos = new JMenuItem("Modulos");
		itemModulos.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				frame.dispose();
				@SuppressWarnings("unused")
				ModulosView moduloVista = new ModulosView(usuarioLogin);
			}
		});

		JMenuItem itemSalir = new JMenuItem("Salir");
		itemSalir.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				System.exit(0);
			}
		});

		JMenuItem itemCerrarSesion = new JMenuItem("Cerrar Sesion");
		itemCerrarSesion.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				frame.dispose();
				@SuppressWarnings("unused")
				LoginView loginView = new LoginView();
			}
		});

		menuArchivo.add(itemModulos);
		menuArchivo.add(itemCerrarSesion);
		menuArchivo.add(itemSalir);
		menuBar.add(menuArchivo);
		frame.setJMenuBar(menuBar);
		
		JPanel buttonPanel = new JPanel(new GridBagLayout());
		JPanel gridPanel = new JPanel(new GridLayout(8, 1, 0, 10));
		
		JLabel lblCategoria2 = new JLabel("Selecciona un reporte:");
		gridPanel.add(lblCategoria2);
		
		btnReporte1 = new JButton("Activos por sector");
		gridPanel.add(btnReporte1);
		
		btnReporte2 = new JButton("Activos de backup disponibles");
		gridPanel.add(btnReporte2);
		
		btnReporte3 = new JButton("Activos en reparacion");
		gridPanel.add(btnReporte3);
		
		btnReporte4 = new JButton("Proveedores");
		gridPanel.add(btnReporte4);
		
		btnReporte5 = new JButton("Amortizacion de activos");
		gridPanel.add(btnReporte5);
		
		btnReporte6 = new JButton("Activos de baja");
		gridPanel.add(btnReporte6);
		
		btnReporte7 = new JButton("Garantia de activos");
		gridPanel.add(btnReporte7);
		
		GridBagConstraints gbc = new GridBagConstraints();
		gbc.anchor = GridBagConstraints.CENTER;
		gbc.weightx = 1;
		gbc.weighty = 1;
		
		buttonPanel.add(gridPanel, gbc);
		
		frame.add(buttonPanel, BorderLayout.CENTER);

		frame.setVisible(true);

	}
	
	public void mostrarReporte1(Boolean enabled) {
		btnReporte1.setEnabled(enabled);
	}
	
	public void mostrarReporte2(Boolean enabled) {
		btnReporte2.setEnabled(enabled);
	}
	
	public void mostrarReporte3(Boolean enabled) {
		btnReporte3.setEnabled(enabled);
	}
	
	public void mostrarReporte4(Boolean enabled) {
		btnReporte4.setEnabled(enabled);
	}
	
	public void mostrarReporte5(Boolean enabled) {
		btnReporte5.setEnabled(enabled);
	}
	
	public void mostrarReporte6(Boolean enabled) {
		btnReporte6.setEnabled(enabled);
	}
	
	public void mostrarReporte7(Boolean enabled) {
		btnReporte7.setEnabled(enabled);
	}
	
	public void setReporte1Listener(ActionListener listener) {
		btnReporte1.addActionListener(listener);
	}
	
	public void setReporte2Listener(ActionListener listener) {
		btnReporte2.addActionListener(listener);
	}
	
	public void setReporte3Listener(ActionListener listener) {
		btnReporte3.addActionListener(listener);
	}
	
	public void setReporte4Listener(ActionListener listener) {
		btnReporte4.addActionListener(listener);
	}
	
	public void setReporte5Listener(ActionListener listener) {
		btnReporte5.addActionListener(listener);
	}

	public void setReporte6Listener(ActionListener listener) {
		btnReporte6.addActionListener(listener);
	}
	
	public void setReporte7Listener(ActionListener listener) {
		btnReporte7.addActionListener(listener);
	}
}
