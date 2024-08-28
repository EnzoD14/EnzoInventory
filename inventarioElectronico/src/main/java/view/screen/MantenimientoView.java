package view.screen;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;

import controlador.UsuarioController;

public class MantenimientoView {
	private UsuarioController usuarioLogin;
	private JFrame frame;

	public MantenimientoView(UsuarioController usuario) {
		this.usuarioLogin = usuario;
		initialize();
	}

	private void initialize() {
		frame = new JFrame("Mantenimiento/Reparacion - Inventario Electronico");
		frame.setSize(400, 400);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setLayout(null);

		JLabel lblCategoria2 = new JLabel("Activos en reparacion:");
		lblCategoria2.setBounds(30, 30, 200, 100);
		frame.add(lblCategoria2);

		JMenuBar menuBar = new JMenuBar();
		JMenu menuArchivo = new JMenu("Archivo");

		JMenuItem itemModulos = new JMenuItem("Modulos");
		itemModulos.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				frame.dispose();
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
				LoginView loginView = new LoginView(usuarioLogin);
			}
		});

		menuArchivo.add(itemModulos);
		menuArchivo.add(itemCerrarSesion);
		menuArchivo.add(itemSalir);
		menuBar.add(menuArchivo);
		frame.setJMenuBar(menuBar);

		frame.setVisible(true);

	}

}