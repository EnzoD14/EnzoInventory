package modelo.dao.impl;

import java.sql.SQLException;
import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
import org.hibernate.service.ServiceRegistry;

import modelo.Activo;
import modelo.dao.ActivoDAO;

public class ActivoDAOimpl implements ActivoDAO {
	
	private SessionFactory sessionFactory;
	
	public ActivoDAOimpl() {
		Configuration configuration = new Configuration();
        configuration.configure("hibernate.cfg.xml");
        ServiceRegistry serviceRegistry = new StandardServiceRegistryBuilder().applySettings(configuration.getProperties()).build();
        sessionFactory = configuration.buildSessionFactory(serviceRegistry);
	}

	@Override
	public Boolean agregarActivo(Activo activo) throws SQLException {
		System.out.println("agregarActivo DAO IMPL");
		Session session = null;
	    try {
	        session = sessionFactory.openSession();
	        session.beginTransaction();
	        session.save(activo);
	        session.getTransaction().commit();
	        System.out.println("Activo guardado con exito bdd");
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

	@Override
	public void modificarActivo(Activo activo) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void eliminarActico(Activo activo) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public Activo obtenerActivoPorId(int id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Activo> listarActivos() {
		// TODO Auto-generated method stub
		return null;
	}
	
	@Override
	public void imprimirActivo(Activo activo) {
		System.out.println("Modelo Activo: " + activo.getModelo() + "\nActivo: " + activo.getTipo() 
		+ "\nMarca Activo: " + activo.getMarca() + ".");
	}

}
