package view.screen;

import java.awt.BorderLayout;
import java.util.List;

import javax.swing.JFrame;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import org.hibernate.SessionFactory;
import modelo.Proveedor;
import modelo.dao.impl.ProveedorDAOimpl;

@SuppressWarnings("serial")
public class ProveedorListaView extends JFrame {
    private JTable table;
    private DefaultTableModel model;
    
    public ProveedorListaView() {
    	setTitle("Lista de Proveedores - Inventario Electronico");
    	setSize(800, 600);
        model = new DefaultTableModel(new Object[]{"Nombre", "Razon Social", "Email", "Telefono", "Baja"}, 0);
        table = new JTable(model);
        JScrollPane scrollPane = new JScrollPane(table);
        add(scrollPane);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());
        
        
        
        loadProveedores();
        
        setVisible(true);
    }

    @SuppressWarnings({ "unchecked" })
	private void loadProveedores() {
    	ProveedorDAOimpl proveedorDAO = new ProveedorDAOimpl();
    	List<Proveedor> proveedores = proveedorDAO.listarProveedor("");
        
        for (Proveedor proveedor : proveedores) {
            model.addRow(new Object[]{proveedor.getNombre(), proveedor.getRazonSocial(), proveedor.getEmail(), proveedor.getTelefono(), proveedor.getBaja()});
        }
    }
}
