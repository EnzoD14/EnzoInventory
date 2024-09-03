package view.events;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JOptionPane;

import modelo.Proveedor;
import modelo.dao.impl.ProveedorDAOimpl;
import view.screen.ProveedorAltaView;
import view.screen.ProveedorBajaView;
import view.screen.ProveedorModificacionBusquedaView;
import view.screen.ProveedorModificacionView;

public class ProveedorModificacionBusquedaEvent {
	
		private ProveedorModificacionBusquedaView view;
		private ProveedorModificacionView view2;
		private ProveedorDAOimpl proveedorDAO;
		
		public ProveedorModificacionBusquedaEvent(ProveedorModificacionBusquedaView proveedorModificacionView) {
			this.view = proveedorModificacionView;
			this.proveedorDAO = new ProveedorDAOimpl();
			System.out.println("teste");
			initEventHandlers();
		}

		public void initEventHandlers() {
			view.setBuscarButtonActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					modificarProveedor();
				}
			});
			
			view.setCancelarButtonActionListener(new ActionListener() {
	            public void actionPerformed(ActionEvent e) {
	                view.setVisible(false);
	            }
	        });
		}
		
		private void modificarProveedor() {
			System.out.println("modificarProveedor");

			String razonSocial = view.getRazonSocial();
			System.out.println(razonSocial);
			Proveedor proveedor = proveedorDAO.buscarProveedorPorRazonSocial(razonSocial);

			if (proveedor != null) {
				view2 = new ProveedorModificacionView(proveedor);
				new ProveedorModificacionEvents(view2, proveedor);
				view.setVisible(false);
				view2.setVisible(true);
			} else {
				JOptionPane.showMessageDialog(view, "No se encontró un proveedor con la razón social: " + razonSocial,
						"Error", JOptionPane.ERROR_MESSAGE);
				view.setVisible(false);
			}

		}
}
