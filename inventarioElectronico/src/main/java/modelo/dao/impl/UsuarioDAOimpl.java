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

	public Boolean agregarUsuario(Usuario usuario) {
		Session session = null;
		
		try {
			session = sessionFactory.openSession();
			session.beginTransaction();
			session.save(usuario);
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

	public Boolean modificarUsuario(Usuario usuario) {
		Session session = null;
		
		try {
			session = sessionFactory.openSession();
			session.beginTransaction();
			session.update(usuario);
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

	public Boolean eliminarUsuario(Usuario usuario) {
		Session session = null;
		
		try {
			usuario.setBaja(1);
			session = sessionFactory.openSession();
			session.beginTransaction();
			session.update(usuario);
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

	public Usuario obtenerUsuarioPorUsuarioAd(String usuarioAd) {
		Session session = null;
		Usuario usuario = null;
		
		try {
			session = sessionFactory.openSession();
			session.beginTransaction();
			Query query = session.createQuery("from Usuario where usuarioAd = :usuarioAd AND baja != 1");
			query.setParameter("usuarioAd", usuarioAd);
			usuario = (Usuario) query.uniqueResult();
			session.getTransaction().commit();
			return usuario;
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
