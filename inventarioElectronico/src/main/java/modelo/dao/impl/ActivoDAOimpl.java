package modelo.dao.impl;

import java.sql.SQLException;
import java.util.List;

import org.hibernate.Query;
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
	        //System.out.println("Activo guardado con exito bdd");
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
	public Boolean modificarActivo(Activo activo) {
		
		Session session = null;
        try {
            session = sessionFactory.openSession();
            session.beginTransaction();
            session.update(activo);
            session.getTransaction().commit();
            //System.out.println("Activo modificado con exito bdd");
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
	public void eliminarActico(Activo activo) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public Activo obtenerActivoPorId(int id) {
		Session session = null;
		
		try {
            session = sessionFactory.openSession();
            session.beginTransaction();
            Activo activo = (Activo) session.get(Activo.class, id);
            session.getTransaction().commit();
            return activo;
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
	
	public Activo obtenerActivoPorNumeroSerie(String numeroSerie) {
		Session session = null;
		try {
			session = sessionFactory.openSession();
			session.beginTransaction();
			Query query = session.createQuery("from Activo where numeroSerie = :numeroSerie");
			query.setParameter("numeroSerie", numeroSerie);
			Activo activo = (Activo) query.uniqueResult();
			session.getTransaction().commit();
			return activo;
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
	public List<Activo> listarActivos(String numeroSerie) {
		Session session = null;
	    try {
	        session = sessionFactory.openSession();
	        session.beginTransaction();
	        Query query;
	        if (numeroSerie == null || numeroSerie.isEmpty()) {
	            query = session.createQuery("from Activo where baja = 0");
	        } else {
	            query = session.createQuery("from Activo where numeroSerie = :numeroSerie and baja = 0");
	            query.setParameter("numeroSerie", numeroSerie);
	        }
	        @SuppressWarnings("unchecked")
			List<Activo> activos = query.list();
	        session.getTransaction().commit();
	        return activos;
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
	public List<Activo> listarActivosConBaja(String numeroSerie) {
		Session session = null;
	    try {
	        session = sessionFactory.openSession();
	        session.beginTransaction();
	        Query query;
	        if (numeroSerie == null || numeroSerie.isEmpty()) {
	            query = session.createQuery("from Activo");
	        } else {
	            query = session.createQuery("from Activo where numeroSerie = :numeroSerie");
	            query.setParameter("numeroSerie", numeroSerie);
	        }
	        @SuppressWarnings("unchecked")
			List<Activo> activos = query.list();
	        session.getTransaction().commit();
	        return activos;
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
	public void imprimirActivo(Activo activo) {
		System.out.println("Modelo Activo: " + activo.getModelo() + "\nActivo: " + activo.getTipo() 
		+ "\nMarca Activo: " + activo.getMarca() + ".");
	}

}
