package modelo.dao.impl;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
import org.hibernate.service.ServiceRegistry;

import modelo.Proveedor;

public class ProveedorDAOimpl {
	
	private SessionFactory sessionFactory;
	
	@SuppressWarnings("deprecation")
	public ProveedorDAOimpl() {
		Configuration configuration = new Configuration();
        configuration.configure("hibernate.cfg.xml");
        ServiceRegistry serviceRegistry = new StandardServiceRegistryBuilder().applySettings(configuration.getProperties()).build();
        sessionFactory = configuration.buildSessionFactory(serviceRegistry);
	}
	
	public void agregarProveedor(Proveedor proveedor) {
		System.out.println("agregarProveedor DAO IMPL");
		 Session session = null;
		    try {
		        session = sessionFactory.openSession();
		        session.beginTransaction();
		        session.save(proveedor);
		        session.getTransaction().commit();
		        System.out.println("Proveedor guardado con exito bdd");
		    } catch (Exception e) {
		        if (session != null) {
		            session.getTransaction().rollback();
		        }
		        e.printStackTrace();
		    } finally {
		        if (session != null) {
		            session.close();
		        }
		    }
	}
	
	public void modificarProveedor() {
		System.out.println("modificarProveedor");
	}
	
	public void eliminarProveedor() {
		System.out.println("eliminarProveedor");
	}
	
	public void listarProveedor() {
		System.out.println("listarProveedor");
	}
	
}

