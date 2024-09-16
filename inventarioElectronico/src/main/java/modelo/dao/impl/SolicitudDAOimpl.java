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
	public void agregarSolicitud(Solicitud solicitud) throws SQLException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void imprimirSolicitud(Solicitud solicitud) {
		// TODO Auto-generated method stub
		
	}
	
	public List<Solicitud> listarSolicitudes(String tipoSolicitud) {
		Session session = null;
	    try {
	        session = sessionFactory.openSession();
	        session.beginTransaction();
	        Query query;
	        if (tipoSolicitud == null || tipoSolicitud.isEmpty()) {
	            query = session.createQuery("from Solicitud");
	        } else {
	            query = session.createQuery("from Activo where tipoSolicitud = :tipoSolicitud");
	            query.setParameter("tipoSolicitud", tipoSolicitud);
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

}
