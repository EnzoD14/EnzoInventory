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
import modelo.Usuario;
import modelo.dao.impl.UsuarioDAOimpl;

@SuppressWarnings("serial")
public class UsuarioGestionView extends JFrame {
	private UsuarioController usuarioLogin;
	private JPanel busquedaPanel;
	private JTextField busquedaTxtField;
	private JLabel busquedaLabel;
	private JButton crearButton;
	private JButton modificarButton;
	private JButton eliminarButton;
	private JButton buscarButton;
	private JTable activosTable;
	private DefaultTableModel modeloTable;
	private String usuarioAd;

	public UsuarioGestionView(UsuarioController usuario) {
		this.usuarioLogin = usuario;
		initialize();
	}

	private void initialize() {
		// Configura el JFrame
        setTitle("Gestion de Usuarios - Inventario Electronico");
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
        busquedaLabel = new JLabel("Buscar usuario (AD):");
        buscarButton = new JButton("Buscar");
        busquedaPanel.add(busquedaLabel, BorderLayout.WEST);
        busquedaPanel.add(busquedaTxtField, BorderLayout.CENTER);
        busquedaPanel.add(buscarButton, BorderLayout.EAST);
        add(busquedaPanel, BorderLayout.NORTH);

        // Crea los botones
        JPanel buttonPanel = new JPanel();
        crearButton = new JButton("Crear Usuario");
        buttonPanel.add(crearButton);
        crearButton.setEnabled(true);
        
        modificarButton = new JButton("Modificar Usuario");
        buttonPanel.add(modificarButton);
        modificarButton.setEnabled(false);
        
        eliminarButton = new JButton("Eliminar Usuario");
        buttonPanel.add(eliminarButton);
        eliminarButton.setEnabled(false);
        add(buttonPanel, BorderLayout.SOUTH);

        // Crea la tabla de activos
        modeloTable = new DefaultTableModel(new Object[]{"UsuarioAD", "Nombre", "Apellido", "Email", "Tipo Usuario"}, 0) {
			@Override
			public boolean isCellEditable(int row, int column) {
				return false;
			}
        };
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
						eliminarButton.setEnabled(true);
						usuarioAd = busquedaTxtField.getText();
						System.out.println("Usuario: " + usuarioAd);
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
	
	public boolean actualizarTabla(String busqueda) throws SQLException {
		// Limpiar tabla
			modeloTable.setRowCount(0);
			UsuarioDAOimpl usuarioDAO = new UsuarioDAOimpl();
		    List<Usuario> usuarios = usuarioDAO.listarUsuarios(busqueda);
		        
			if (usuarios.isEmpty()) {
				return false;
			}

		// Agregar activos a la tabla
		    for (Usuario usuario : usuarios) {
		    	System.out.println(usuario.getTipoUsuario());
		        if (usuario.getUsuarioAd() != null) {
			        modeloTable.addRow(new Object[]{
                    		usuario.getUsuarioAd(),
                    		usuario.getNombre(),
                    		usuario.getApellido(),
                    		usuario.getEmail(),
                    		usuario.getTipoUsuario()});
		        }
		    }
	return true;
	}
	
	
	public void setModificarButton(ActionListener actionListener) {
		modificarButton.addActionListener(actionListener);
	}
	
	public void setEliminarButton(ActionListener actionListener) {
		eliminarButton.addActionListener(actionListener);
	}
	
	public void setCrearButton(ActionListener actionListener) {
		crearButton.addActionListener(actionListener);
	}
	
	public String getUsuarioAd() {
		return busquedaTxtField.getText();
	}
	
	public void setBusquedaTxtField(String busqueda) {
		busquedaTxtField.setText(busqueda);
	}
}
