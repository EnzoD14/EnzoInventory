package modelo.dao.impl;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;

import modelo.Garantia;
import modelo.dao.GarantiaDAO;

public class GarantiaDAOimpl implements GarantiaDAO {
	private SessionFactory sessionFactory;
	
	public GarantiaDAOimpl() {
		Configuration configuration = new Configuration();
		configuration.configure("hibernate.cfg.xml");
		StandardServiceRegistry serviceRegistry = new StandardServiceRegistryBuilder()
				.applySettings(configuration.getProperties()).build();
		sessionFactory = configuration.buildSessionFactory(serviceRegistry);
	}
	
	public Boolean agregarGarantia(Garantia garantia) {
		System.out.println("agregarGarantia DAO IMPL");
		Session session = null;
		
		try {
			session = sessionFactory.openSession();
			session.beginTransaction();
			session.save(garantia);
			session.getTransaction().commit();
			System.out.println("Garantia guardada con exito bdd");
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

	public Boolean modificarGarantia(Garantia garantia) {
		
		Session session = null;
		
		try {
			session = sessionFactory.openSession();
			session.beginTransaction();
			session.update(garantia);
			session.getTransaction().commit();
			System.out.println("Garantia modificada con exito bdd");
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

	public void eliminarGarantia(Garantia garantia) {

	}

	@Override
	public void buscarGarantiaPorId(int id) {
		// TODO Auto-generated method stub
		
	}
	
	@Override
	public Garantia obtenerGarantiaPorIdActivo(String id) {
		// TODO Auto-generated method stub
		Session session = null;
		Garantia garantia = null;
		
		try {
            session = sessionFactory.openSession();
            session.beginTransaction();
            garantia = (Garantia) session.createQuery("FROM Garantia WHERE idGarantia = :id").setParameter("id", id).uniqueResult();
            //System.out.println("Garantia: " + garantia);
            session.getTransaction().commit();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (session != null) {
                session.close();
            }
        }
		return garantia;
	}
}
