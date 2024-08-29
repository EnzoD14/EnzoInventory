package view.screen;

import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;

import controlador.UsuarioController;

public class CompraView {
	
	private UsuarioController usuarioLogin;
    private JFrame frame;
    private JButton btnAltaProveedor;
    private JButton btnBajaProveedor;
    private JButton btnModificarProveedor;
    private JButton btnListarProveedor;
    private JButton btnAltaCompra;

	public CompraView(UsuarioController usuarioLogin) {
		this.usuarioLogin = usuarioLogin;
        initialize();
	}

	public void initialize() {
		frame = new JFrame("Compras - Inventario Electronico");
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
        btnAltaCompra = new JButton("Alta de Compra");
        btnAltaProveedor = new JButton("Alta de Proveedor");
        btnBajaProveedor = new JButton("Baja de Proveedor");
        btnModificarProveedor = new JButton("Modificar Proveedor");
        btnListarProveedor = new JButton("Listar Proveedores");

        panel.add(btnAltaCompra);
        panel.add(btnAltaProveedor);
        panel.add(btnBajaProveedor);
        panel.add(btnModificarProveedor);
        panel.add(btnListarProveedor);
        
        
    }
    
        public void setControladorAlta(ActionListener controlador) {
        	btnAltaProveedor.addActionListener(controlador);
        }
        
        public void setControladorBaja(ActionListener controlador) {
        	btnBajaProveedor.addActionListener(controlador);
        }
        
        public void setControladorModificar(ActionListener controlador) {
        	btnModificarProveedor.addActionListener(controlador);
        }
        
        public void setControladorListar(ActionListener controlador) {
        	btnListarProveedor.addActionListener(controlador);
        }
        
        public void setControladorSolicitudAlta(ActionListener controlador) {
        	btnAltaCompra.addActionListener(controlador);
        }
        
		public void mostrar() {
			frame.setVisible(true);
		}

		public void setVisible(boolean b) {
			// TODO Auto-generated method stub
			frame.setVisible(b);
		}

}
