package view.screen;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;
import controlador.UsuarioController;
import modelo.Activo;
import modelo.dao.impl.ActivoDAOimpl;

@SuppressWarnings("serial")
public class BackupView extends JFrame {
	private UsuarioController usuarioLogin;
	private JPanel busquedaPanel;
	private JTextField busquedaTxtField;
	private JLabel busquedaLabel;
	private JButton backupButton;
	private JButton enUsoButton;
	private JButton buscarButton;
	private JTable activosTable;
	private DefaultTableModel modeloTable;
	private String numeroSerie;

	public BackupView(UsuarioController usuario) {
		this.usuarioLogin = usuario;
		initialize();
	}

	private void initialize() {
		// Configura el JFrame
        setTitle("Backups - Inventario Electronico");
        setSize(800, 600);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());
        
        // Crear y agregar una barra de menú
        JMenuBar menuBar = new JMenuBar();
        JMenu menuArchivo = new JMenu("Archivo");
        
        JMenuItem itemModulos = new JMenuItem("Modulos");
        itemModulos.addActionListener(new ActionListener() {
        	@Override
            public void actionPerformed(ActionEvent e) {
                dispose();
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
                dispose();
                @SuppressWarnings("unused")
				LoginView loginView = new LoginView(usuarioLogin);
            }
        });
        
        menuArchivo.add(itemModulos);
        menuArchivo.add(itemCerrarSesion);
        menuArchivo.add(itemSalir);
        menuBar.add(menuArchivo);
        setJMenuBar(menuBar);
        
     // Crea el campo de búsqueda
        busquedaPanel = new JPanel(new BorderLayout());
        busquedaTxtField= new JTextField();
        busquedaLabel = new JLabel("Buscar activo:");
        buscarButton = new JButton("Buscar");
        busquedaPanel.add(busquedaLabel, BorderLayout.WEST);
        busquedaPanel.add(busquedaTxtField, BorderLayout.CENTER);
        busquedaPanel.add(buscarButton, BorderLayout.EAST);
        add(busquedaPanel, BorderLayout.NORTH);

        // Crea los botones
        JPanel buttonPanel = new JPanel();
        backupButton = new JButton("Activo Backup");
        buttonPanel.add(backupButton);
        backupButton.setEnabled(false);
        
        enUsoButton = new JButton("Activo En Uso");
        buttonPanel.add(enUsoButton);
        enUsoButton.setEnabled(false);
        add(buttonPanel, BorderLayout.SOUTH);

        // Crea la tabla de activos
        modeloTable = new DefaultTableModel(new Object[]{"Número de Serie", "Tipo", "Marca", "Modelo", "Estado"}, 0);
        activosTable = new JTable(modeloTable); // Aquí puedes configurar el modelo de la tabla para mostrar los activos
        add(new JScrollPane(activosTable), BorderLayout.CENTER);
        
        // Actualizar tabla al iniciar
        try {
            actualizarTabla("");
        } catch (SQLException e) {
            e.printStackTrace();
        }
        
        buscarButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				try {
					if (actualizarTabla(busquedaTxtField.getText())) {
						backupButton.setEnabled(true);
						enUsoButton.setEnabled(true);
						numeroSerie = busquedaTxtField.getText();
						System.out.println("Numero de serie: " + numeroSerie);
					}
					
					if (busquedaTxtField.getText().isEmpty()) {
						backupButton.setEnabled(false);
						enUsoButton.setEnabled(false);
					}
					
				} catch (SQLException ex) {
					ex.printStackTrace();
				}
			}
		});
		
        setVisible(true);
    }
	
	private boolean actualizarTabla(String busqueda) throws SQLException {
		// Limpiar tabla
			modeloTable.setRowCount(0);
			ActivoDAOimpl activoDAO = new ActivoDAOimpl();
		    List<Activo> activos = activoDAO.listarActivos(busqueda);
		        
			if (activos.isEmpty()) {
				return false;
			}
			
			List<Activo> backupActivos = new ArrayList<>();
			List<Activo> otrosActivos = new ArrayList<>();

		// Agregar activos a la tabla
		    for (Activo activo : activos) {
		        if (activo.getEstado() != null && activo.getEstado().equalsIgnoreCase("Backup")) {
			        backupActivos.add(activo);
		        } else {
		        	otrosActivos.add(activo);
		        }
		    }
		    
			for (Activo activo : backupActivos) {
				modeloTable.addRow(new Object[] { activo.getNumeroSerie(), activo.getTipo(), activo.getMarca(),
						activo.getModelo(), activo.getEstado() });
			}
			
			//modeloTable.addRow(new Object[] { "","","","","" });
			
			for (Activo activo : otrosActivos) {
                modeloTable.addRow(new Object[] { activo.getNumeroSerie(), activo.getTipo(), activo.getMarca(),
                        activo.getModelo(), activo.getEstado() });
			}
		        
	return true;
	}
	
	public String getNumeroSerie() {
        return busquedaTxtField.getText();
	}
	
	public void setBackupButton(ActionListener actionListener) {
		backupButton.addActionListener(actionListener);
	}
	
	public void setEnUsoButton(ActionListener actionListener) {
		enUsoButton.addActionListener(actionListener);
	}
}
