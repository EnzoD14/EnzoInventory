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
import modelo.Solicitud;
import modelo.dao.impl.SolicitudDAOimpl;

@SuppressWarnings("serial")
public class SolicitudGestionView extends JFrame{
	private UsuarioController usuarioLogin;
    private JPanel busquedaPanel;
	private JTextField busquedaTxtField;
	private JLabel txtSolicitudPendiente;
	private JLabel busquedaLabel;
	private JButton buscarButton;
	private JButton btnSolicitudAprobar;
	private JButton btnSolicitudRechazar;
	private JButton btnSolicitudAlta;
	private JButton btnSolicitudBaja;
	private JTable solicitudesTable;
	private DefaultTableModel modeloTable;
	
	public SolicitudGestionView() {
		initialize();
	}
	
	private void initialize() {
		setTitle("Gestión de Solicitudes - Inventario Electronico");
		setSize(800, 600);
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
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
        busquedaLabel = new JLabel("Buscar solicitud:");
        buscarButton = new JButton("Buscar");
        txtSolicitudPendiente = new JLabel("Solicitudes Pendientes: ");
        busquedaPanel.add(busquedaLabel, BorderLayout.WEST);
        busquedaPanel.add(busquedaTxtField, BorderLayout.CENTER);
        busquedaPanel.add(buscarButton, BorderLayout.EAST);
        busquedaPanel.add(txtSolicitudPendiente, BorderLayout.WEST);
        add(busquedaPanel, BorderLayout.NORTH);
        
        // Panel Botones
        JPanel buttonPanel = new JPanel();
        btnSolicitudAlta = new JButton("Alta Solicitud");
        btnSolicitudBaja = new JButton("Baja Solicitud");
        btnSolicitudAprobar = new JButton("Aprobar Solicitud");
        btnSolicitudRechazar = new JButton("Rechazar Solicitud");
        buttonPanel.add(btnSolicitudAlta);
        buttonPanel.add(btnSolicitudBaja);
        buttonPanel.add(btnSolicitudAprobar);
        buttonPanel.add(btnSolicitudRechazar);
        btnSolicitudAprobar.setEnabled(false);
        btnSolicitudRechazar.setEnabled(false);
        add(buttonPanel, BorderLayout.SOUTH);
        
        // Crea la tabla de activos
        modeloTable = new DefaultTableModel(new Object[]{"Id Solicitud", "Tipo Solicitud" , "Fecha Solicitud" , "Estado Solicitud"}, 0);
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
						String numeroSerie = busquedaTxtField.getText();
						if (numeroSerie.equals("")) {
							btnSolicitudAprobar.setEnabled(false);
							btnSolicitudRechazar.setEnabled(false);
						} else {
							btnSolicitudAprobar.setEnabled(true);
							btnSolicitudRechazar.setEnabled(true);
						}
					}
				} catch (SQLException ex) {
					ex.printStackTrace();
				}
			}
		});
        
        
        setVisible(true);
	}
	
	public Boolean actualizarTabla(String busqueda) throws SQLException {
		modeloTable.setRowCount(0);
		SolicitudDAOimpl solicitudDAO = new SolicitudDAOimpl();
		List<Solicitud> solicitudes = solicitudDAO.listarSolicitudes(busqueda);
		
		// Formato de fecha
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
		
		for (Solicitud solicitud : solicitudes) {
			String fecha = sdf.format(solicitud.getFechaSolicitud());
			String estado = "";
			
			if (solicitud.getEstado() == 0) {
				estado = "Pendiente";
			} else if (solicitud.getEstado() == 1) {
				estado = "Utilizada";
			} else {
				estado = "Rechazada";
			}
			
			modeloTable.addRow(new Object[] { solicitud.getId(), solicitud.getTipoSolicitud(), fecha, estado});
		}
		
		return true;
	}
	
	// Getters y Setters
	
	public String getBusqueda() {
		return busquedaTxtField.getText();
	}
	
	
	public void setControladorSolicitudAlta(ActionListener solicitudCargar) {
	  btnSolicitudAlta.addActionListener(solicitudCargar); }
	
	public void setControladorSolicitudBaja(ActionListener solicitudBaja) {
		btnSolicitudBaja.addActionListener(solicitudBaja);}
	
	public void setControladorSolicitudAprobar(ActionListener solicitud) {
	  btnSolicitudAprobar.addActionListener(solicitud); }
	  
	public void setControladorSolicitudRechazar(ActionListener solicitud2) {
	  btnSolicitudRechazar.addActionListener(solicitud2); }
	 
	 
	
	
}
