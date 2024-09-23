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
import modelo.Solicitud;
import modelo.dao.SolicitudDAO;

public class SolicitudDAOimpl implements SolicitudDAO {
	
	private SessionFactory sessionFactory;
	
	public SolicitudDAOimpl() {
		Configuration configuration = new Configuration();
        configuration.configure("hibernate.cfg.xml");
        ServiceRegistry serviceRegistry = new StandardServiceRegistryBuilder().applySettings(configuration.getProperties()).build();
        sessionFactory = configuration.buildSessionFactory(serviceRegistry);
	}

	@Override
	public Boolean agregarSolicitud(Solicitud solicitud) throws SQLException {
		
		Session session = null;
		try {
			session = sessionFactory.openSession();
			session.beginTransaction();
			session.save(solicitud);
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
	public void imprimirSolicitud(Solicitud solicitud) {
		// TODO Auto-generated method stub
		
	}
	
	public List<Solicitud> listarSolicitudes(String idSolicitud) {
		Session session = null;
	    try {
	        session = sessionFactory.openSession();
	        session.beginTransaction();
	        Query query;
	        if (idSolicitud == null || idSolicitud.isEmpty()) {
	            query = session.createQuery("from Solicitud where baja = 0");
	        } else {
	            query = session.createQuery("from Solicitud where idSolicitud = :idSolicitud");
	            query.setParameter("idSolicitud", idSolicitud);
	        }
	        @SuppressWarnings("unchecked")
			List<Solicitud> solicitudes = query.list();
	        session.getTransaction().commit();
	        return solicitudes;
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
	
	public Activo obtenerActivoPorSolicitudId(int id) throws SQLException {
		Session session = null;
		try {
            session = sessionFactory.openSession();
            session.beginTransaction();
            Solicitud solicitud = (Solicitud) session.get(Solicitud.class, id);
            session.getTransaction().commit();
            if (solicitud != null) {
                return solicitud.getActivo();
            } else {
                return null;
            }
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
	
	public Solicitud obtenerSolicitudPorId(int id) throws SQLException {
		Session session = null;
		try {
			session = sessionFactory.openSession();
			session.beginTransaction();
			Solicitud solicitud = (Solicitud) session.get(Solicitud.class, id);
			session.getTransaction().commit();
			return solicitud;
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
	
	public Boolean actualizarSolicitud(Solicitud solicitud) throws SQLException {
		Session session = null;
		try {
			session = sessionFactory.openSession();
			session.beginTransaction();
			session.update(solicitud);
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

}
