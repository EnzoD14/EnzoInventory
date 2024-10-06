package modelo.dao.impl;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
import org.hibernate.service.ServiceRegistry;

import modelo.Empleado;
import modelo.Usuario;

public class EmpleadoDAOimpl {
	
	private SessionFactory sessionFactory;
	
	public EmpleadoDAOimpl() {
		Configuration configuration = new Configuration();
		configuration.configure("hibernate.cfg.xml");
		ServiceRegistry serviceRegistry = new StandardServiceRegistryBuilder()
				.applySettings(configuration.getProperties()).build();
		sessionFactory = configuration.buildSessionFactory(serviceRegistry);
	}
	
	public Boolean agregarEmpleado(Empleado empleado) {
		Session session = null;

		try {
			session = sessionFactory.openSession();
			session.beginTransaction();
			session.save(empleado);
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
	
	public Boolean modificarEmpleado(Empleado empleado) {
		Session session = null;

		try {
			session = sessionFactory.openSession();
			session.beginTransaction();
			session.update(empleado);
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
	
	public Boolean eliminarEmpleado(Empleado empleado) {
		Session session = null;

		try {
			session = sessionFactory.openSession();
			session.beginTransaction();
			session.delete(empleado);
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
	
	public Empleado obtenerEmpleadoPorId(String empleadoId) {
		Session session = null;
		Empleado empleado = null;

		try {
			session = sessionFactory.openSession();
			session.beginTransaction();
			Query query = session.createQuery("from Empleado where idEmpleado = :idEmpleado AND baja != 1");
			query.setParameter("idEmpleado", empleadoId);
			empleado = (Empleado) query.uniqueResult();
			session.getTransaction().commit();
			return empleado;
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
		
		return null;
	}

	public List<Empleado> getAllEmpleados() {
		Session session = null;
		List<Empleado> empleados = null;
		
		try {
			session = sessionFactory.openSession();
			Query query = session.createQuery("FROM Empleado WHERE baja != 1");
			empleados = query.list();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (session != null) {
				session.close();
			}
		}
		return empleados;
	}

}
