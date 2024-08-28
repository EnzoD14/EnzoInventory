package view.screen;

import controlador.UsuarioController;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class GestionActivosView {
    private UsuarioController usuarioLogin;
    private JFrame frame;
    private JButton btnAltaActivo;
    private JButton btnBajaActivo;
    private JButton btnModificarActivo;
    private JButton btnListarActivos;
    private JButton btnSolicitudAlta;
    private JButton btnSolicitudBaja;

    // Constructor
    public GestionActivosView(UsuarioController usuario) {
        this.usuarioLogin = usuario;
        initialize();
    }

    private void initialize() {
        frame = new JFrame("Gestión de Activos - Inventario Electronico");
        frame.setSize(500, 400);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLayout(new BorderLayout());

        // Crear y agregar una barra de menú
        JMenuBar menuBar = new JMenuBar();
        JMenu menuArchivo = new JMenu("Archivo");
        
        JMenuItem itemModulos = new JMenuItem("Modulos");
        itemModulos.addActionListener(new ActionListener() {
        	@Override
            public void actionPerformed(ActionEvent e) {
                frame.dispose();
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
                frame.dispose();
                LoginView loginView = new LoginView(usuarioLogin);
            }
        });
        
        menuArchivo.add(itemModulos);
        menuArchivo.add(itemCerrarSesion);
        menuArchivo.add(itemSalir);
        menuBar.add(menuArchivo);
        frame.setJMenuBar(menuBar);

        // Panel principal
        JPanel panel = new JPanel();
        panel.setLayout(new GridLayout(3, 1, 10, 10)); // Ajusta el diseño según sea necesario
        frame.add(panel, BorderLayout.CENTER);

        // Crear y agregar botones
        btnAltaActivo = new JButton("Alta de Activo");
        btnBajaActivo = new JButton("Baja de Activo");
        btnModificarActivo = new JButton("Modificar Activo");
        btnListarActivos = new JButton("Listar Activos");

        panel.add(btnAltaActivo);
        panel.add(btnBajaActivo);
        panel.add(btnModificarActivo);
        panel.add(btnListarActivos);
        
        System.out.println(usuarioLogin.mostrarTipoUsuario());

        // Verificar el tipo de usuario y agregar botones adicionales si es necesario
        if (usuarioLogin.mostrarTipoUsuario().equals("It")) {
        	btnSolicitudAlta = new JButton("Solicitud de Alta");
            btnSolicitudBaja = new JButton("Solicitud de Baja");
            panel.add(btnSolicitudAlta);
            panel.add(btnSolicitudBaja);
        }
        
        frame.setVisible(true);
    }
    
        public void setControladorAlta(ActionListener controlador) {
        	btnAltaActivo.addActionListener(controlador);
        }
        
        public void setControladorBaja(ActionListener controlador) {
        	btnBajaActivo.addActionListener(controlador);
        }
        
        public void setControladorModificar(ActionListener controlador) {
        	btnModificarActivo.addActionListener(controlador);
        }
        
        public void setControladorListar(ActionListener controlador) {
        	btnListarActivos.addActionListener(controlador);
        }
        
        public void setControladorSolicitudAlta(ActionListener controlador) {
        	if (btnSolicitudAlta != null) {
        		btnSolicitudAlta.addActionListener(controlador);
        	}
        }
        
        public void setControladorSolicitudBaja(ActionListener controlador) {
        	if (btnSolicitudBaja != null) {
        		btnSolicitudBaja.addActionListener(controlador);
        	}
        }
     
}



