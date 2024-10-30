package view.screen;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Enumeration;
import javax.swing.AbstractButton;
import javax.swing.BoxLayout;
import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import controlador.UsuarioController;
import modelo.Compra;
import modelo.Usuario;
import modelo.dao.impl.ActivoDAOimpl;
import modelo.dao.impl.CompraDAOimpl;
import view.events.CompraEvents;
import view.events.MantenimientoEvents;
import view.events.ReportesEvents;
import view.events.ActivoGestionEvents;
import view.events.BackupEvents;
import view.events.UsuarioGestionEvents;

public class ModulosView {
	private Usuario usuarioLogin;
	private CompraDAOimpl compraDAO;
	private ActivoDAOimpl activoDAO;
    private JFrame frame;
    private JPanel panelOpciones;
    private JButton btnSeleccionar;
    private ButtonGroup buttonGroup;
    private String[] categorias = {"Gestión Activos", "Gestión Usuarios", "Compras" , "Mantenimiento / Reparación", "Backup", "Reportes"};
    
    public ModulosView(Usuario usuarioLogin, Compra compra, ActivoDAOimpl activoDAO) {
        this.usuarioLogin = usuarioLogin;
        this.compraDAO = new CompraDAOimpl();
        this.activoDAO = activoDAO;
        initialize();
    }
    
	public ModulosView(Usuario usuario) {
		this.usuarioLogin = usuario;
		initialize();
	}

    private void initialize() {
    	UsuarioController usuarioController = new UsuarioController(usuarioLogin);
        frame = new JFrame("Módulos - Inventario Electronico");
        frame.setSize(400, 400);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLayout(null);

        JLabel lblCategoria = new JLabel("Modulos");
        lblCategoria.setBounds(30, 30, 200, 20);
        frame.add(lblCategoria);
        
        JLabel lblCategoria2 = new JLabel("Selecciona una categoría:");
        lblCategoria2.setBounds(30, 30, 200, 100);
        frame.add(lblCategoria2);

        // Configuración de las opciones
        panelOpciones = new JPanel();
        panelOpciones.setBounds(30, 100, 340, 200);
        panelOpciones.setLayout(new BoxLayout(panelOpciones, BoxLayout.Y_AXIS));

        buttonGroup = new ButtonGroup();

        // Crear botones de opción y agregarlos al panel y al ButtonGroup
        for (String categoria : categorias) {
            JRadioButton radioButton = new JRadioButton(categoria);
            
			if (usuarioController.tienePermiso(usuarioLogin, categoria)) {
				radioButton.setEnabled(true);
			} else {
				radioButton.setEnabled(false);
			}
            
            panelOpciones.add(radioButton);
            buttonGroup.add(radioButton);
        }
        
        frame.add(panelOpciones);
        
        // Configuración del botón
        btnSeleccionar = new JButton("Seleccionar");
        btnSeleccionar.setBounds(120, 300, 150, 25);
        frame.add(btnSeleccionar);

        // Configuración del evento de botón
        btnSeleccionar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Obtener la opción seleccionada
            	Enumeration<AbstractButton> buttons = buttonGroup.getElements();
                while (buttons.hasMoreElements()) {
                    AbstractButton button = buttons.nextElement();
                    if (button.isSelected()) {
                        String categoriaSeleccionada = button.getText();
                        JOptionPane.showMessageDialog(frame, "Categoría seleccionada: " + categoriaSeleccionada);
                        // Lógica para abrir la siguiente vista o pantalla
                        abrirVistaCategoria(categoriaSeleccionada);
                        return;
                    }
                }
                JOptionPane.showMessageDialog(frame, "Por favor, selecciona una categoría.");
            }
        });

        frame.setVisible(true);
    }

    private void abrirVistaCategoria(String categoria) {
        // Aquí puedes agregar la lógica para abrir la vista correspondiente
        // Por ejemplo:
        switch (categoria) {
            case "Gestión Activos":
                // new GestionActivosVista();
            	//GestionActivosView activosVista = new GestionActivosView(usuarioLogin);
            	ActivoGestionView view = new ActivoGestionView(usuarioLogin);
                CompraBusquedaView compraView = null;
            	new ActivoGestionEvents(compraView, view, compraDAO, activoDAO, usuarioLogin);
                break;
            case "Gestión Usuarios":
                // new GestionUsersVista();
            	UsuarioGestionView usuarioVista = new UsuarioGestionView(usuarioLogin);
            	new UsuarioGestionEvents(usuarioVista);
                break;
            case "Compras":
				// new ComprasVista();
				CompraView comprasVista = new CompraView(usuarioLogin);
				new CompraEvents(comprasVista);
				break;
            case "Mantenimiento / Reparación":
                // new MantenimientoVista();
            	MantenimientoView mantenimientoVista = new MantenimientoView(usuarioLogin);
            	new MantenimientoEvents(mantenimientoVista);
                break;
            case "Backup":
                // new BackupVista();
            	BackupView backupVista = new BackupView(usuarioLogin);
            	new BackupEvents(backupVista);
                break;
            case "Reportes":
                // new ReportesVista();
            	ReportesView reportesVista = new ReportesView(usuarioLogin);
				new ReportesEvents(reportesVista, usuarioLogin);
				break;
            default:
                throw new IllegalArgumentException("Categoría no reconocida: " + categoria);
        }
        frame.setVisible(false); // Ocultar la ventana actual si es necesario
    }
    
}
