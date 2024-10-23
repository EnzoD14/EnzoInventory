package view.events;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JOptionPane;

import modelo.Proveedor;
import modelo.dao.impl.ProveedorDAOimpl;
import view.screen.ProveedorModificacionView;

public class ProveedorModificacionEvents {
	
	private ProveedorModificacionView view;
	private ProveedorDAOimpl proveedorDAO;
	private Proveedor proveedor;
	
	public ProveedorModificacionEvents(ProveedorModificacionView proveedorModificacionView, Proveedor proveedor) {
		this.view = proveedorModificacionView;
		this.proveedorDAO = new ProveedorDAOimpl();
		initEventHandlers(proveedor);
	}
	
	public void initEventHandlers(Proveedor proveedor) {
		view.setGuardarButtonActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (validarCampos()) {
					String razonSocial = view.getRazonSocial();
					if (proveedorDAO.buscarProveedorPorRazonSocial(razonSocial) != null) {
                        JOptionPane.showMessageDialog(null, "Ya existe un proveedor con esa razon social.", "Error", JOptionPane.ERROR_MESSAGE);
                        return;
                    } else {
                    	modificarProveedor(proveedor);
                    }
				}
			}
		});

		view.setCancelarButtonActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				view.setVisible(false);
			}
		});
	}
	
	private void modificarProveedor(Proveedor proveedor) {
		System.out.println("modificarProveedor");
		Proveedor proveedorId = new ProveedorDAOimpl().getIdProveedor(proveedor.getRazonSocial());
		
		
		System.out.println(proveedorId.getId());
		
		proveedorId.setNombre(view.getNombre());
		proveedorId.setRazonSocial(view.getRazonSocial());
		proveedorId.setEmail(view.getEmail());
		proveedorId.setTelefono(view.getTelefono());
		
		System.out.println(view.getTelefono());
		
		proveedorId.setBaja(0);
		
		if(proveedorDAO.modificarProveedor(proveedorId)) {
			System.out.println("Proveedor modificado con éxito en bdd");
            JOptionPane.showMessageDialog(null, "Proveedor modificado con éxito en bdd", "Información", JOptionPane.INFORMATION_MESSAGE);
            view.setVisible(false);
        } else {
            System.out.println("Error al modificar proveedor en bdd");
            JOptionPane.showMessageDialog(null, "Error al modificar proveedor en bdd", "Error", JOptionPane.ERROR_MESSAGE);
            view.setVisible(false);
		}
		
	}
	
	private boolean validarCampos() {
		if (view.getNombre().isEmpty() || view.getRazonSocial().isEmpty() || view.getEmail().isEmpty() || view.getTelefono().isEmpty()) {
            JOptionPane.showMessageDialog(null, "Por favor, complete todos los campos.", "Error", JOptionPane.ERROR_MESSAGE);
            return false;
		} else {
            return true;
        }
	}
}
