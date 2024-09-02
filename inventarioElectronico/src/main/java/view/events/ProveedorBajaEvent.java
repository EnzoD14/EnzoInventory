package view.events;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JOptionPane;

import modelo.Proveedor;
import modelo.dao.impl.ProveedorDAOimpl;
import view.screen.ProveedorAltaView;
import view.screen.ProveedorBajaView;

public class ProveedorBajaEvent {
	
		private ProveedorBajaView view;
		private ProveedorDAOimpl proveedorDAO;
		
		public ProveedorBajaEvent(ProveedorBajaView proveedorBajaView) {
			this.view = proveedorBajaView;
			this.proveedorDAO = new ProveedorDAOimpl();
			System.out.println("teste");
			initEventHandlers();
		}

		public void initEventHandlers() {
			view.setBuscarButtonActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					borrarProveedor();
				}
			});
			
			view.setCancelarButtonActionListener(new ActionListener() {
	            public void actionPerformed(ActionEvent e) {
	                view.setVisible(false);
	            }
	        });
		}
		
		private void borrarProveedor() {
			System.out.println("borrarProveedor");

			String razonSocial = view.getRazonSocial();
			System.out.println(razonSocial);
			Proveedor proveedor = proveedorDAO.buscarProveedorPorRazonSocial(razonSocial);

			if (proveedor != null) {
			    proveedorDAO.eliminarProveedor(proveedor);
			} else {
			    JOptionPane.showMessageDialog(view, "No se encontró un proveedor con la razón social: " + razonSocial, "Error", JOptionPane.ERROR_MESSAGE);
			}
			
		}
		
		private void cancelarProveedor() {
			System.out.println("cancelarBajaProveedor");
		}
	}
