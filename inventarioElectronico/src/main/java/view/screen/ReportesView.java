package view.screen;

import java.awt.BorderLayout;
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

import controlador.UsuarioController;

@SuppressWarnings("serial")
public class ReportesView extends JFrame{
	private UsuarioController usuarioLogin;
	private JButton btnReporte1;
	private JButton btnReporte2;
	private JButton btnReporte3;
	private JButton btnReporte4;
	private JButton btnReporte5;
	private JFrame frame;

	public ReportesView(UsuarioController usuario) {
		this.usuarioLogin = usuario;
		initialize();
	}

	private void initialize() {
		System.out.println("asdasd");
		frame = new JFrame("Reportes - Inventario Electronico");
		frame.setSize(400, 400);
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
				LoginView loginView = new LoginView(usuarioLogin);
			}
		});

		menuArchivo.add(itemModulos);
		menuArchivo.add(itemCerrarSesion);
		menuArchivo.add(itemSalir);
		menuBar.add(menuArchivo);
		frame.setJMenuBar(menuBar);
		
		JPanel buttonPanel = new JPanel(new GridLayout(6, 1));
		
		JLabel lblCategoria2 = new JLabel("Selecciona un reporte:");
		buttonPanel.add(lblCategoria2);
		
		btnReporte1 = new JButton("Reporte 1");
		buttonPanel.add(btnReporte1);
		
		btnReporte2 = new JButton("Reporte 2");
		buttonPanel.add(btnReporte2);
		
		btnReporte3 = new JButton("Reporte 3");
		buttonPanel.add(btnReporte3);
		
		btnReporte4 = new JButton("Reporte 4");
		buttonPanel.add(btnReporte4);
		
		btnReporte5 = new JButton("Reporte 5");
		buttonPanel.add(btnReporte5);
		
		frame.add(buttonPanel, BorderLayout.CENTER);

		frame.setVisible(true);

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

}
