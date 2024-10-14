package view.screen;

import java.awt.BorderLayout;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.time.LocalDate;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.List;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import modelo.Activo;
import modelo.Usuario;
import modelo.dao.impl.ActivoDAOimpl;

@SuppressWarnings("serial")
public class MantenimientoView extends JFrame {
	private Usuario usuarioLogin;
	private JTable reparTable;
	private JTable mantenTable;
	private JButton btnAltaReparacion;
	private JButton btnBajaReparacion;
	private JButton btnMantenimiento;
	private DefaultTableModel reparacionTable;
	private DefaultTableModel mantenimientoTable;

	public MantenimientoView(Usuario usuario) {
		this.usuarioLogin = usuario;
		initialize();
	}

	private void initialize() {
		setTitle("Mantenimiento/Reparacion - Inventario Electronico");
		setSize(1000, 600);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setLayout(new BorderLayout());

		JMenuBar menuBar = new JMenuBar();
		JMenu menuArchivo = new JMenu("Archivo");

		JMenuItem itemModulos = new JMenuItem("Modulos");
		itemModulos.addActionListener(new ActionListener() {
			@SuppressWarnings("unused")
			@Override
			public void actionPerformed(ActionEvent e) {
				dispose();
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
			@SuppressWarnings("unused")
			@Override
			public void actionPerformed(ActionEvent e) {
				dispose();
				LoginView loginView = new LoginView();
			}
		});

		menuArchivo.add(itemModulos);
		menuArchivo.add(itemCerrarSesion);
		menuArchivo.add(itemSalir);
		menuBar.add(menuArchivo);
		setJMenuBar(menuBar);
		
		//Botones
		JPanel buttonPanel = new JPanel();
		btnAltaReparacion = new JButton("Alta Reparacion");
		btnBajaReparacion = new JButton("Baja Reparacion");
		btnMantenimiento = new JButton("Mantenimiento");
		
		buttonPanel.add(btnAltaReparacion);
		buttonPanel.add(btnBajaReparacion);
		buttonPanel.add(btnMantenimiento);
		add(buttonPanel, BorderLayout.SOUTH);
		
		//Texto
		JPanel textPanel = new JPanel();
		textPanel.setLayout(new BoxLayout(textPanel, BoxLayout.X_AXIS));
		JLabel lblCategoria2 = new JLabel("Activos en reparacion:");
		JLabel lblCategoria3 = new JLabel("Proximo mantenimiento:");
		textPanel.add(Box.createHorizontalGlue());
		
		textPanel.add(lblCategoria2);
		textPanel.add(Box.createHorizontalGlue());
		
		textPanel.add(lblCategoria3);
		textPanel.add(Box.createHorizontalGlue());
		add(textPanel, BorderLayout.NORTH);
		
		//Tablas Reparacion-Mantenimiento
		reparacionTable = new DefaultTableModel(new Object[]{ "Numero de serie", "Marca", "Modelo", "Estado" }, 0);
		reparTable = new JTable(reparacionTable);
		
		mantenimientoTable = new DefaultTableModel(new Object[]{ "Numero de serie", "Marca", "Modelo", "Ultimo mantenimiento" }, 0);
		mantenTable = new JTable(mantenimientoTable);
		
		JPanel tablePanel = new JPanel(new GridBagLayout());
		GridBagConstraints gbc = new GridBagConstraints();
		
		gbc.gridx = 0;
		gbc.gridy = 0;
		gbc.weightx = 0.5;
		gbc.weighty = 1;
		gbc.fill = GridBagConstraints.BOTH;
		tablePanel.add(new JScrollPane(reparTable), gbc);
		
		gbc.gridx = 1;
		tablePanel.add(new JScrollPane(mantenTable), gbc);
		
		add(tablePanel, BorderLayout.CENTER);
		
		//Actualizar tablas
		try {
			actualizarTabla("");
		} catch (Exception e) {
			e.printStackTrace();
		}

		setVisible(true);

	}
	
	private void actualizarTabla(String numeroSerie) {
		reparacionTable.setRowCount(0);
		mantenimientoTable.setRowCount(0);
		ActivoDAOimpl activoDAO = new ActivoDAOimpl();
		List<Activo> activos = activoDAO.listarActivos(numeroSerie);
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
		
		for (Activo activo : activos) {
			if(activo.getReparacion() != null) {
				reparacionTable.addRow(new Object[] { activo.getNumeroSerie(), activo.getMarca(), activo.getModelo(),
					activo.getEstado() });
				System.out.println("Activo en reparacion: " + activo.getNumeroSerie());
			}
			
			LocalDate localDate = activo.getFechaMantenimiento().toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
			String date = localDate.format(formatter);
			mantenimientoTable.addRow(new Object[] { activo.getNumeroSerie(), activo.getMarca(), activo.getModelo(), date});
		}
	}
	
	public void setBtnAltaReparacionListener(ActionListener actionListener) {
		btnAltaReparacion.addActionListener(actionListener);
	}
	
	public void setBtnBajaReparacionListener(ActionListener actionListener) {
		btnBajaReparacion.addActionListener(actionListener);
	}
	
	public void setBtnMantenimientoListener(ActionListener actionListener) {
		btnMantenimiento.addActionListener(actionListener);
	}

}