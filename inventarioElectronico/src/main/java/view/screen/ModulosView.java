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

import controlador.ActivoController;
import controlador.UsuarioController;
import view.events.GestionActivosEvents;

public class ModulosView {
	private UsuarioController usuarioLogin;
    private JFrame frame;
    private JPanel panelOpciones;
    private JButton btnSeleccionar;
    private ButtonGroup buttonGroup;
    private String[] categorias = {"Gestión Activos", "Gestión Usuarios", "Mantenimiento / Reparación", "Garantía", "Backup", "Reportes"};
    
    public ModulosView(UsuarioController usuario) {
        this.usuarioLogin = usuario;
        initialize();
    }

    private void initialize() {
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
        panelOpciones.setBounds(30, 100, 340, 150);
        panelOpciones.setLayout(new BoxLayout(panelOpciones, BoxLayout.Y_AXIS));

        buttonGroup = new ButtonGroup();

        // Crear botones de opción y agregarlos al panel y al ButtonGroup
        for (String categoria : categorias) {
            JRadioButton radioButton = new JRadioButton(categoria);
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
            	GestionActivosView view = new GestionActivosView(usuarioLogin);
                ActivoController controller = new ActivoController();
            	new GestionActivosEvents(view, controller);
                break;
            case "Gestión Usuarios":
                // new GestionUsersVista();
            	GestionUsuariosView gestionUsuariosVista = new GestionUsuariosView(usuarioLogin);
                break;
            case "Mantenimiento / Reparación":
                // new MantenimientoVista();
            	MantenimientoView mantenimientoVista = new MantenimientoView(usuarioLogin);
                break;
            case "Garantía":
                // new GarantiaVista();
            	GarantiaView garantiaVista = new GarantiaView(usuarioLogin);
                break;
            case "Backup":
                // new BackupVista();
            	BackupView backupVista = new BackupView(usuarioLogin);
                break;
            case "Reportes":
                // new ReportesVista();
            	ReportesView reportesVista = new ReportesView(usuarioLogin);
            	System.out.println("llego");
                break;
            default:
                throw new IllegalArgumentException("Categoría no reconocida: " + categoria);
        }
        frame.setVisible(false); // Ocultar la ventana actual si es necesario
    }
}
