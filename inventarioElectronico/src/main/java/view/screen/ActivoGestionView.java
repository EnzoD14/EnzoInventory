package view.screen;
import modelo.Activo;
import modelo.Garantia;
import modelo.Usuario;
import modelo.dao.impl.ActivoDAOimpl;
import modelo.dao.impl.GarantiaDAOimpl;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.List;

@SuppressWarnings("serial")
public class ActivoGestionView extends JFrame{
    private Usuario usuarioLogin;
    private JPanel busquedaPanel;
	private JTextField busquedaTxtField;
	private JLabel busquedaLabel;
	private JButton buscarButton;
    private JButton btnSolicitudActivo;
    private JButton btnModificarActivo;
	private JButton btnModificarGarantia;
    private JTable activosTable;
	private DefaultTableModel modeloTable;
	private String numeroSerie;

    // Constructor
    public ActivoGestionView(Usuario usuario) {
        this.usuarioLogin = usuario;
        initialize();
    }

    private void initialize() {
        setTitle("Gestión de Activos - Inventario Electronico");
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
				new ModulosView(usuarioLogin);
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
                new LoginView();
            }
        });
        
        menuArchivo.add(itemModulos);
        menuArchivo.add(itemCerrarSesion);
        menuArchivo.add(itemSalir);
        menuBar.add(menuArchivo);
        setJMenuBar(menuBar);
        
        busquedaPanel = new JPanel(new BorderLayout());
        busquedaTxtField= new JTextField();
        busquedaLabel = new JLabel("Buscar activo:");
        buscarButton = new JButton("Buscar");
        busquedaPanel.add(busquedaLabel, BorderLayout.WEST);
        busquedaPanel.add(busquedaTxtField, BorderLayout.CENTER);
        busquedaPanel.add(buscarButton, BorderLayout.EAST);
        add(busquedaPanel, BorderLayout.NORTH);

        // Panel principal
        JPanel buttonPanel = new JPanel();

        // Crear y agregar botones
        btnSolicitudActivo = new JButton("Solicitudes");
        btnModificarActivo = new JButton("Modificar Activo");
        btnModificarGarantia = new JButton("Modificar Garantia");

        buttonPanel.add(btnSolicitudActivo);
        buttonPanel.add(btnModificarActivo);
        buttonPanel.add(btnModificarGarantia);
        btnModificarActivo.setEnabled(false);
        btnModificarGarantia.setEnabled(false);
        add(buttonPanel, BorderLayout.SOUTH);
        
        // Crea la tabla de activos
        modeloTable = new DefaultTableModel(new Object[]{"Tipo", "Marca", "Modelo", "Número de Serie", "Estado", "Garantia", "Fecha Inicio", "Fecha Fin"}, 0);
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
						btnModificarGarantia.setEnabled(true);
						numeroSerie = busquedaTxtField.getText();
						System.out.println("Numero de serie: " + numeroSerie);
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
			GarantiaDAOimpl garantiaDAO = new GarantiaDAOimpl();
	        List<Activo> activos = activoDAO.listarActivos(busqueda);
	        
			if (activos.isEmpty()) {
				return false;
			}
			
			// Formato de fecha
			SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
	
	        // Agregar activos a la tabla
	        for (Activo activo : activos) {
	        	if (activo.getGarantia() != null) {
	        		Garantia garantia = garantiaDAO.obtenerGarantiaPorIdActivo(activo.getGarantia().getId());
	        	
		            //System.out.println(garantia.getFechaInicio() + " - " + garantia.getFechaFin());
		        	
		        	String fechaInicio = sdf.format(garantia.getFechaInicio());
		            String fechaFin = sdf.format(garantia.getFechaFin());
		        	modeloTable.addRow(new Object[]{activo.getTipo(), activo.getMarca(), activo.getModelo(), activo.getNumeroSerie(), activo.getEstado() , "SI", 
		            		fechaInicio, fechaFin});
	
	        	} else {
	        		modeloTable.addRow(new Object[]{activo.getTipo(), activo.getMarca(), activo.getModelo(), activo.getNumeroSerie(), activo.getEstado() , "NO", 
	            			"", ""});
	        	}
	        	
	        }
	        
	        return true;
		}
	    
	    public String getNumeroSerie() {
	    	return busquedaTxtField.getText();
	    }
    
        public void setControladorSolicitud(ActionListener controlador) {
        	btnSolicitudActivo.addActionListener(controlador);
        }
        
        public void setControladorActivoModificar(ActionListener controlador) {
        	btnModificarActivo.addActionListener(controlador);
        }
		
		public void setControladorGarantiaModificar(ActionListener controlador) {
			btnModificarGarantia.addActionListener(controlador);
		}
     
}



