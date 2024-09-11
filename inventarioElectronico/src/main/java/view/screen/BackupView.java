package view.screen;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
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
import modelo.Garantia;
import modelo.dao.impl.ActivoDAOimpl;
import modelo.dao.impl.GarantiaDAOimpl;

@SuppressWarnings("serial")
public class BackupView extends JFrame {
	private UsuarioController usuarioLogin;
	private JPanel busquedaPanel;
	private JTextField busquedaTxtField;
	private JLabel busquedaLabel;
	private JButton modificarButton;
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
        modificarButton = new JButton("Modificar Backup");
        buttonPanel.add(modificarButton);
        modificarButton.setEnabled(false);
        add(buttonPanel, BorderLayout.SOUTH);

        // Crea la tabla de activos
        modeloTable = new DefaultTableModel(new Object[]{"Tipo", "Marca", "Modelo", "Número de Serie", "Estado"}, 0);
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
						modificarButton.setEnabled(true);
						numeroSerie = busquedaTxtField.getText();
						System.out.println("Numero de serie: " + numeroSerie);
					}
					
					if (busquedaTxtField.getText().isEmpty()) {
						modificarButton.setEnabled(false);
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

		// Agregar activos a la tabla
		    for (Activo activo : activos) {
		    	System.out.println(activo.getEstado());
		        if (activo.getEstado() != null && activo.getEstado().equalsIgnoreCase("Backup")) {
			        modeloTable.addRow(new Object[]{activo.getTipo(), activo.getMarca(), activo.getModelo(), activo.getNumeroSerie(), activo.getEstado()});
		        }
		    }
		        
	return true;
	}
	
	
	public void setModificarButton(ActionListener actionListener) {
		modificarButton.addActionListener(actionListener);
	}
}
