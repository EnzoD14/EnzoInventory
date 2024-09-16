package view.screen;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;
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
import modelo.Solicitud;
import modelo.dao.impl.SolicitudDAOimpl;

@SuppressWarnings("serial")
public class SolicitudGestionView extends JFrame{
	private UsuarioController usuarioLogin;
    private JPanel busquedaPanel;
	private JTextField busquedaTxtField;
	private JLabel busquedaLabel;
	private JButton buscarButton;
	private JButton btnSolicitudAprobar;
	private JButton btnSolicitudRechazar;
	private JButton btnSolicitudCargar;
	private JTable solicitudesTable;
	private DefaultTableModel modeloTable;
	
	public SolicitudGestionView() {
		initialize();
	}
	
	private void initialize() {
		setTitle("Gestión de Solicitudes - Inventario Electronico");
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
                new LoginView(usuarioLogin);
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
        
        // Panel Botones
        JPanel buttonPanel = new JPanel();
        btnSolicitudCargar = new JButton("Cargar Solicitud");
        btnSolicitudAprobar = new JButton("Aprobar Solicitud");
        btnSolicitudRechazar = new JButton("Rechazar Solicitud");
        buttonPanel.add(btnSolicitudCargar);
        buttonPanel.add(btnSolicitudAprobar);
        buttonPanel.add(btnSolicitudRechazar);
        add(buttonPanel, BorderLayout.SOUTH);
        
        // Crea la tabla de activos
        modeloTable = new DefaultTableModel(new Object[]{"Tipo Solicitud" , "Fecha Solicitud" , "Estado Solicitud"}, 0);
        solicitudesTable = new JTable(modeloTable); // Aquí puedes configurar el modelo de la tabla para mostrar los activos
        add(new JScrollPane(solicitudesTable), BorderLayout.CENTER);
        
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
						//numeroSerie = busquedaTxtField.getText();
					}
				} catch (SQLException ex) {
					ex.printStackTrace();
				}
			}
		});
        
        
        setVisible(true);
	}
	
	public Boolean actualizarTabla(String busqueda) throws SQLException {
		
		SolicitudDAOimpl solicitudDAO = new SolicitudDAOimpl();
		List<Solicitud> solicitudes = solicitudDAO.listarSolicitudes(busqueda);
		
		for (Solicitud solicitud : solicitudes) {
			modeloTable.addRow(new Object[] { solicitud.getTipoSolicitud(), solicitud.getFechaSolicitud(), solicitud.getEstado()});
		}
		
		return true;
	}
	
	// Getters y Setters
	  public void setControladorSolicitudCargar(ActionListener solicitudCargar) {
	  btnSolicitudCargar.addActionListener(solicitudCargar); }
	  
	
	  public void setControladorSolicitud(ActionListener solicitud) {
	  btnSolicitudAprobar.addActionListener(solicitud); }
	  
	  public void setControladorSolicitud2(ActionListener solicitud2) {
	  btnSolicitudRechazar.addActionListener(solicitud2); }
	 
	 
	
	
}
