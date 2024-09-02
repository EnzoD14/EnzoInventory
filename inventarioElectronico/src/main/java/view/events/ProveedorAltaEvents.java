package view.events;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JOptionPane;

import controlador.UsuarioController;
import modelo.Proveedor;
import modelo.dao.impl.ProveedorDAOimpl;
import view.screen.ProveedorAltaView;

public class ProveedorAltaEvents {
	private ProveedorAltaView view;
	private ProveedorDAOimpl proveedorDAO;
	
	public ProveedorAltaEvents(ProveedorAltaView proveedorAltaView) {
		this.view = proveedorAltaView;
		this.proveedorDAO = new ProveedorDAOimpl();
		System.out.println("teste");
		initEventHandlers();
	}

	public void initEventHandlers() {
		view.setAceptar(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				agregarProveedor();
			}
		});
		
		view.setCancelar(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                view.setVisible(false);
            }
        });
	}
	
	private void agregarProveedor() {
		Boolean flag;
		System.out.println("agregarProveedor");
		
		Proveedor proveedor = new Proveedor();
		proveedor.setNombre(view.getNombre());
		proveedor.setRazonSocial(view.getRazonSocial());
		proveedor.setEmail(view.getEmail());
		proveedor.setTelefono(view.getTelefono());
		proveedor.setBaja(0);
		
		if (flag = proveedorDAO.agregarProveedor(proveedor)) {
			System.out.println("Proveedor guardado con exito bdd");
			JOptionPane.showMessageDialog(null, "Proveedor guardado con exito bdd", "Información", JOptionPane.INFORMATION_MESSAGE);
			view.setVisible(false);
		} else {
			System.out.println("Error al guardar proveedor en bdd");
			JOptionPane.showMessageDialog(null, "Error al guardar proveedor en bdd", "Error", JOptionPane.ERROR_MESSAGE);
			view.setVisible(false);
		};
	}
	
	private void cancelarProveedor() {
		System.out.println("cancelarProveedor");
	}
}
