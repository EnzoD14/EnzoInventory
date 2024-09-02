package view.screen;

import java.util.List;

import javax.swing.JFrame;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.metamodel.Metadata;
import org.hibernate.metamodel.MetadataSources;
import org.hibernate.service.ServiceRegistry;

import modelo.Proveedor;

@SuppressWarnings("serial")
public class ProveedorListaView extends JFrame {
    private JTable table;
    private DefaultTableModel model;
    private SessionFactory factory;
    
    public ProveedorListaView() {
    	ServiceRegistry serviceRegistry = new StandardServiceRegistryBuilder().configure().build();
        Metadata metadata = new MetadataSources(serviceRegistry).getMetadataBuilder().build();
        factory = metadata.getSessionFactoryBuilder().build();
        model = new DefaultTableModel(new Object[]{"Nombre", "Razon Social", "Email", "Telefono", "Baja"}, 0);
        table = new JTable(model);
        JScrollPane scrollPane = new JScrollPane(table);
        add(scrollPane);
        setSize(500, 400);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        loadProveedores();
    }

    @SuppressWarnings({ "unchecked" })
	private void loadProveedores() {
    	List<Proveedor> proveedores = null;
        Session session = factory.openSession();
        try {
        	proveedores = (List<Proveedor>) session.createQuery("from Proveedor").list();
        } catch (Exception e) {
        	e.printStackTrace();
        }
        session.close();
        for (Proveedor proveedor : proveedores) {
            model.addRow(new Object[]{proveedor.getNombre(), proveedor.getRazonSocial(), proveedor.getEmail(), proveedor.getTelefono(), proveedor.getBaja()});
        }
    }
}
