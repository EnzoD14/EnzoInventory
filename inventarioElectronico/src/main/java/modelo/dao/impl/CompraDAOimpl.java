package modelo.dao.impl;

import java.util.Date;
import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;

import modelo.Activo;
import modelo.Compra;
import modelo.Proveedor;
import modelo.dao.CompraDAO;

public class CompraDAOimpl implements CompraDAO {
	
	private SessionFactory sessionFactory;

	@SuppressWarnings("deprecation")
	public CompraDAOimpl() {
		Configuration configuration = new Configuration();
		configuration.configure("hibernate.cfg.xml");
		StandardServiceRegistry serviceRegistry = new StandardServiceRegistryBuilder()
				.applySettings(configuration.getProperties()).build();
		sessionFactory = configuration.buildSessionFactory(serviceRegistry);
	}

	public Boolean agregarCompra(Compra compra) {
		System.out.println("agregarCompra DAO IMPL");
		Session session = null;
		try {
			session = sessionFactory.openSession();
			session.beginTransaction();
			session.save(compra);
			session.getTransaction().commit();
			System.out.println("Compra guardada con exito bdd");
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

	public Boolean modificarCompra(Compra compra) {
		System.out.println("modificarCompra");
		Session session = null;
		try {
			session = sessionFactory.openSession();
			session.beginTransaction();
			session.update(compra);
			session.getTransaction().commit();
			System.out.println("Compra actualizada con éxito en la base de datos");
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

	public Boolean eliminarCompra(Compra compra) {
        System.out.println("eliminarCompra");
        Session session = null;
            try {
                session = sessionFactory.openSession();
                session.beginTransaction();
                session.delete(compra);
                session.getTransaction().commit();
                System.out.println("Compra eliminada con éxito en la base de datos");
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

	public Compra buscarCompraPorId(String id) {
        System.out.println("buscarCompraPorId");
        Session session = null;
        Compra compra = null;
            try {
                session = sessionFactory.openSession();
                session.beginTransaction();
                compra = (Compra) session.get(Compra.class, id);
                session.getTransaction().commit();
                return compra;
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
	
		public Compra buscarComprasPorNumeroFactura(String numeroFactura) {
			System.out.println("buscarComprasPorNumeroFactura");
			Session session = null;
			Compra compra = null;
			try {
				session = sessionFactory.openSession();
				Query query = session.createQuery("FROM Compra WHERE numeroFactura = :numeroFactura");
				query.setParameter("numeroFactura", numeroFactura);
				compra = (Compra) query.uniqueResult();
			} catch (Exception e) {
				e.printStackTrace();
			} finally {
				if (session != null) {
					session.close();
				}
			}
			return compra;
		}

		@Override
		public List<Compra> listarCompras() {
			// TODO Auto-generated method stub
			return null;
		}

		@Override
		public List<Compra> buscarComprasPorProveedor(Proveedor proveedor) {
			// TODO Auto-generated method stub
			return null;
		}

		@Override
		public List<Compra> buscarComprasPorFecha(Date fecha) {
			// TODO Auto-generated method stub
			return null;
		}

		@Override
		public List<Compra> buscarComprasPorProveedorYFecha(Proveedor proveedor, Date fecha) {
			// TODO Auto-generated method stub
			return null;
		}

		@Override
		public List<Compra> buscarComprasPorProveedorYNumeroFactura(Proveedor proveedor, String numeroFactura) {
			// TODO Auto-generated method stub
			return null;
		}

		@Override
		public List<Compra> buscarComprasPorFechaYNumeroFactura(Date fecha, String numeroFactura) {
			// TODO Auto-generated method stub
			return null;
		}

		@Override
		public List<Compra> buscarComprasPorProveedorFechaYNumeroFactura(Proveedor proveedor, Date fecha,
				String numeroFactura) {
			// TODO Auto-generated method stub
			return null;
		}

		@Override
		public List<Compra> buscarComprasPorProveedorYFechaYNumeroFactura(Proveedor proveedor, Date fecha,
				String numeroFactura) {
			// TODO Auto-generated method stub
			return null;
		}

		@Override
		public Compra getCompra() {
			// TODO Auto-generated method stub
			return null;
		}
}
