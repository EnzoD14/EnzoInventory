package view.screen;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Comparator;
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
import view.events.GarantiaEvents;

@SuppressWarnings("serial")
public class GarantiaView extends JFrame{
	private UsuarioController usuarioLogin;
	private JPanel busquedaPanel;
	private JTextField busquedaTxtField;
	private JLabel busquedaLabel;
	private JButton agregarButton;
	private JButton modificarButton;
	private JButton buscarButton;
	private JTable activosTable;
	private DefaultTableModel modeloTable;
	private String numeroSerie;

	public GarantiaView(UsuarioController usuario) {
		this.usuarioLogin = usuario;
		initialize();
	}

	private void initialize () {
		 // Configura el JFrame
        setTitle("Garantia - Inventario Electronico");
        setSize(800, 600);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());

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
        agregarButton = new JButton("Agregar Garantia");
        modificarButton = new JButton("Modificar Garantia");
        buttonPanel.add(agregarButton);
        buttonPanel.add(modificarButton);
        agregarButton.setEnabled(false);
        modificarButton.setEnabled(false);
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
						agregarButton.setEnabled(true);
						modificarButton.setEnabled(true);
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
	
	@SuppressWarnings("unused")
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
	
	public void setAgregarButton(ActionListener listener) {
		agregarButton.addActionListener(listener);
	}
	
	public void setModificarButton(ActionListener listener) {
		modificarButton.addActionListener(listener);
	}

}