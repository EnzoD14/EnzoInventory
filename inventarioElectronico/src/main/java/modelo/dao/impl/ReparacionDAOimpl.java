package modelo.dao.impl;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
import org.hibernate.service.ServiceRegistry;

import modelo.Reparacion;
import modelo.dao.ReparacionDAO;

public class ReparacionDAOimpl implements ReparacionDAO {
	
	private SessionFactory sessionFactory;
	
	public ReparacionDAOimpl() {
		Configuration configuration = new Configuration();
        configuration.configure("hibernate.cfg.xml");
        ServiceRegistry serviceRegistry = new StandardServiceRegistryBuilder().applySettings(configuration.getProperties()).build();
        sessionFactory = configuration.buildSessionFactory(serviceRegistry);
	}

	@Override
	public Boolean agregarReparacion(Reparacion reparacion) {
		System.out.println("agregarReparacion DAO IMPL");
		Session session = null;
		try {
			session = sessionFactory.openSession();
			session.beginTransaction();
			session.save(reparacion);
			session.getTransaction().commit();
			// System.out.println("Reparacion guardado con exito bdd");
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
	public Boolean modificarReparacion(Reparacion reparacion) {
		Session session = null;
		try {
			session = sessionFactory.openSession();
			session.beginTransaction();
			session.update(reparacion);
			session.getTransaction().commit();
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
	public void eliminarReparacion(Reparacion reparacion) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public Reparacion obtenerReparacionPorId(int id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Reparacion obtenerReparacionPorMotivo(String motivo) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Reparacion> listarReparaciones(String motivo) {
		Session session = null;
		try {
			session = sessionFactory.openSession();
			session.beginTransaction();
			List<Reparacion> reparaciones = session.createQuery("from Reparacion").list();
			session.getTransaction().commit();
			return reparaciones;
		} catch (Exception e) {
			if (session != null) {
				session.getTransaction().rollback();
			}
			e.printStackTrace();
			return null;
		} finally {
			if (session != null) {
				session.close();
			}
		}
	}

	@Override
	public List<Reparacion> listarReparacionesConBaja(String motivo) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void imprimirReparacion(Reparacion reparacion) {
		// TODO Auto-generated method stub
		
	}
	
	
	
}
