package view.screen;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
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
import javax.swing.SwingConstants;
import javax.swing.table.DefaultTableModel;
import modelo.Proveedor;
import modelo.Usuario;
import modelo.dao.impl.ProveedorDAOimpl;

public class CompraView {
	@SuppressWarnings("unused")
	private Usuario usuarioLogin;
    private JFrame frame;
    private JButton btnAltaProveedor;
    private JButton btnBajaProveedor;
    private JButton btnModificarProveedor;
    private JButton btnAltaCompra;
    private JTable proveedoresTable;
    private DefaultTableModel modeloTable;

	public CompraView(Usuario usuarioLogin) {
		this.usuarioLogin = usuarioLogin;
        initialize(usuarioLogin);
	}

	public void initialize(Usuario usuarioLogin) {
		frame = new JFrame("Compras - Inventario Electronico");
        frame.setSize(800, 600);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLayout(new BorderLayout());

        // Crear y agregar una barra de menú
        JMenuBar menuBar = new JMenuBar();
        JMenu menuArchivo = new JMenu("Archivo");
        
        JMenuItem itemActualizar = new JMenuItem("Actualizar");
        itemActualizar.addActionListener(new ActionListener() {
        	@Override
            public void actionPerformed(ActionEvent e) {
                actualizarVista();
            }
        });
        
        JMenuItem itemModulos = new JMenuItem("Modulos");
        itemModulos.addActionListener(new ActionListener() {
        	@SuppressWarnings("unused")
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
            @SuppressWarnings("unused")
			@Override
            public void actionPerformed(ActionEvent e) {
                frame.dispose();
                LoginView loginView = new LoginView();
            }
        });
        
        menuArchivo.add(itemActualizar);
        menuArchivo.add(itemModulos);
        menuArchivo.add(itemCerrarSesion);
        menuArchivo.add(itemSalir);
        menuBar.add(menuArchivo);
        frame.setJMenuBar(menuBar);

        JPanel textPanel = new JPanel(new BorderLayout());
        JLabel lblProveedores = new JLabel("Proveedores:", SwingConstants.CENTER);
        textPanel.add(lblProveedores, BorderLayout.CENTER);
        frame.add(textPanel, BorderLayout.NORTH);
        
        //Panel botones
        JPanel buttonPanel = new JPanel();

        // Crear y agregar botones
        btnAltaCompra = new JButton("Alta de Compra");
        btnAltaProveedor = new JButton("Alta de Proveedor");
        btnBajaProveedor = new JButton("Baja de Proveedor");
        btnModificarProveedor = new JButton("Modificar Proveedor");

        buttonPanel.add(btnAltaCompra);
        buttonPanel.add(btnAltaProveedor);
        buttonPanel.add(btnBajaProveedor);
        buttonPanel.add(btnModificarProveedor);
        frame.add(buttonPanel, BorderLayout.SOUTH);
        
        modeloTable = new DefaultTableModel(new Object[]{"Nombre", "Razon Social", "Email", "Telefono", "Baja"}, 0);
        proveedoresTable = new JTable(modeloTable);
        frame.add(new JScrollPane(proveedoresTable), BorderLayout.CENTER);
        loadProveedores();
    }
	
		private void loadProveedores() {
			ProveedorDAOimpl proveedorDAO = new ProveedorDAOimpl();
	    	List<Proveedor> proveedores = proveedorDAO.listarProveedor("");
	        
	        for (Proveedor proveedor : proveedores) {
	            modeloTable.addRow(new Object[]{proveedor.getNombre(), proveedor.getRazonSocial(), proveedor.getEmail(), proveedor.getTelefono(), proveedor.getBaja()});
	        }
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
		
		public void deshabilitarBotones() {
			btnAltaProveedor.setEnabled(false);
			btnBajaProveedor.setEnabled(false);
			btnModificarProveedor.setEnabled(false);
			btnAltaCompra.setEnabled(false);
		}
		
		public void habilitarBotones() {
			btnAltaProveedor.setEnabled(true);
			btnBajaProveedor.setEnabled(true);
			btnModificarProveedor.setEnabled(true);
			btnAltaCompra.setEnabled(true);
			frame.revalidate();
			System.out.println("botones habilitados");
		}
		
		public void actualizarVista() {
			modeloTable.setRowCount(0);
			habilitarBotones();
			loadProveedores();
			frame.revalidate();
		}

}
