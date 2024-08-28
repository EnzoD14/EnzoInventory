package app;

import org.hibernate.Transaction;

import org.hibernate.Session;

import controlador.UsuarioController;
import modelo.Proveedor;
import modelo.Usuario;
import utils.HibernateUtil;
import view.screen.LoginView;

public class Main {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		// Instancia del controlador y modelo necesarios para la vista
       // UsuarioController controlador = new UsuarioController(new Usuario("1", "1", "Enzo", "Dolera", "enzo", "It", true));
       // UsuarioController controlador2 = new UsuarioController(new Usuario("2", "2", "Gonzalo", "Agostino", "gonzalo", "Finanzas", true));
        
        // Instancia de la vista que quieres probar
       // LoginView loginView = new LoginView(controlador);
		
		Proveedor proveedor = new Proveedor("Enzo", "Enzo", "enzo", "1234", 0);
		
		Session session = null;
		Transaction transaction = null;
		
		try {
			
			session = HibernateUtil.getSessionFactory().openSession();
            // Iniciar una transacción de base de datos
            transaction = session.beginTransaction();

            // Guardar el proveedor en la base de datos
            session.save(proveedor);

            // Confirmar la transacción
            transaction.commit();

            // Imprimir el ID del proveedor generado automáticamente
            System.out.println("Proveedor guardado con ID: " + proveedor.getId());
			
		} catch (Exception e) {
			e.printStackTrace();
			if (transaction != null) {
				transaction.rollback();
			}
		} finally {
			if (session != null) {
				session.close();
			}
		}
	}

}
