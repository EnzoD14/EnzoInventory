package view.screen;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import modelo.Proveedor;
import modelo.Usuario;
import modelo.dao.impl.ProveedorDAOimpl;

@SuppressWarnings("serial")
public class ProveedorListaView extends JFrame {
	private Usuario usuarioLogin;
    private JTable table;
    private DefaultTableModel model;
    
    public ProveedorListaView(Usuario usuario) {
    	this.usuarioLogin = usuario;
    	initialize();
    }
    
    private void initialize() {
    	setTitle("Lista de Proveedores - Inventario Electronico");
    	setSize(800, 600);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLayout(new BorderLayout());
        
        JMenuBar menuBar = new JMenuBar();
		JMenu menuArchivo = new JMenu("Archivo");

		JMenuItem itemModulos = new JMenuItem("Modulos");
		itemModulos.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				dispose();
				@SuppressWarnings("unused")
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
				@SuppressWarnings("unused")
				LoginView loginView = new LoginView();
			}
		});

		menuArchivo.add(itemModulos);
		menuArchivo.add(itemCerrarSesion);
		menuArchivo.add(itemSalir);
		menuBar.add(menuArchivo);
		setJMenuBar(menuBar);
        
		model = new DefaultTableModel(new Object[]{"Nombre", "Razon Social", "Email", "Telefono", "Baja"}, 0);
        table = new JTable(model);
        JScrollPane scrollPane = new JScrollPane(table);
        add(scrollPane);
        loadProveedores();
        
        setVisible(true);
    }

	private void loadProveedores() {
    	ProveedorDAOimpl proveedorDAO = new ProveedorDAOimpl();
    	List<Proveedor> proveedores = proveedorDAO.listarProveedor("");
        
        for (Proveedor proveedor : proveedores) {
            model.addRow(new Object[]{proveedor.getNombre(), proveedor.getRazonSocial(), proveedor.getEmail(), proveedor.getTelefono(), proveedor.getBaja()});
        }
    }
}
