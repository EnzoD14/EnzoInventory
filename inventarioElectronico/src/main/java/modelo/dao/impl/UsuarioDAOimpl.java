package modelo.dao.impl;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
import org.hibernate.service.ServiceRegistry;

import modelo.Usuario;

public class UsuarioDAOimpl {
	
	private SessionFactory sessionFactory;
	
	public UsuarioDAOimpl() {
		Configuration configuration = new Configuration();
        configuration.configure("hibernate.cfg.xml");
        ServiceRegistry serviceRegistry = new StandardServiceRegistryBuilder().applySettings(configuration.getProperties()).build();
        sessionFactory = configuration.buildSessionFactory(serviceRegistry);
	}

	public void agregarUsuario(Usuario usuario) {
	}

	public void modificarUsuario(Usuario usuario) {
	}

	public void eliminarUsuario(Usuario usuario) {
	}

	public Usuario obtenerUsuarioPorId(int id) {
		return null;
	}

	public List<Usuario> listarUsuarios(String usuarioAd) {
		Session session = null;
		
		try {
			session = sessionFactory.openSession();
			session.beginTransaction();
			Query query;
			if (usuarioAd == null || usuarioAd.isEmpty()) {
				query = session.createQuery("from Usuario");
			} else {
				query = session.createQuery("from Usuario where usuarioAd = :usuarioAd");
				query.setParameter("usuarioAd", usuarioAd);
			}
			@SuppressWarnings("unchecked")
			List<Usuario> usuarios = query.list();
			session.getTransaction().commit();
			return usuarios;
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

	public void imprimirUsuario(Usuario usuario) {
		System.out.println("Usuario: " + usuario.getNombre() + " " + usuario.getApellido() + "\nUsuario: "
				+ usuario.getUsuarioAd() + "\nDepartamento: " + usuario.getTipoUsuario() + ".");
	}
}
