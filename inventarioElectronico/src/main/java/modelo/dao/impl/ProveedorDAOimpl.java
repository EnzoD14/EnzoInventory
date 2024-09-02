package modelo.dao.impl;

import org.hibernate.Query;
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
	
	public Boolean agregarProveedor(Proveedor proveedor) {
		System.out.println("agregarProveedor DAO IMPL");
		 Session session = null;
		    try {
		        session = sessionFactory.openSession();
		        session.beginTransaction();
		        session.save(proveedor);
		        session.getTransaction().commit();
		        System.out.println("Proveedor guardado con exito bdd");
		        return true;
		    } catch (Exception e) {
		        if (session != null) {
		            session.getTransaction().rollback();
		        }
		        e.printStackTrace();
		        return false;
		    } finally {
		        if (session != null) {
		            session.close();
		        }
		    }
	}
	
	public void modificarProveedor(Proveedor proveedor) {
		System.out.println("modificarProveedor");
		
		 Session session = null;
		    try {
		        session = sessionFactory.openSession();
		        session.beginTransaction();
		        session.update(proveedor);
		        session.getTransaction().commit();
		        System.out.println("Proveedor actualizado con éxito en la base de datos");
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
	
	public void eliminarProveedor(Proveedor proveedor) {
		System.out.println("eliminarProveedor");
		
		Session session = null; 
		
		try { 
			session = sessionFactory.openSession(); session.beginTransaction();

		    // Actualizar el campo baja a 1
		    proveedor.setBaja(1);
		    session.update(proveedor);
	
		    session.getTransaction().commit();
		    System.out.println("Proveedor actualizado con exito en la base de datos");
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
	
	public void listarProveedor() {
		System.out.println("listarProveedor");
	}
	
	public Proveedor buscarProveedorPorRazonSocial(String razonSocial) {
		Session session = null; 
		Proveedor proveedor = null; 
		try { 
			session = sessionFactory.openSession(); 
			Query query = session.createQuery("FROM Proveedor WHERE razonSocial = :razonSocial AND baja != 1"); 
			query.setParameter("razonSocial", razonSocial); 
			proveedor = (Proveedor) query.uniqueResult(); 
			} catch (Exception e) { 
				e.printStackTrace(); 
			} finally { 
				if (session != null) { 
					session.close(); 
				} 
				
			} 
		
		return proveedor; 
	}
}

