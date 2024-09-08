package view.events;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JOptionPane;

import modelo.Compra;
import modelo.Proveedor;
import modelo.dao.impl.CompraDAOimpl;
import modelo.dao.impl.ProveedorDAOimpl;
import view.screen.AltaActivoView;
import view.screen.CompraBusquedaView;
import view.screen.ProveedorModificacionBusquedaView;
import view.screen.ProveedorModificacionView;

public class CompraBusquedaEvent {
	
		private CompraBusquedaView view;
		private AltaActivoView view2;
		private CompraDAOimpl compraDAO;
		
		public CompraBusquedaEvent(CompraBusquedaView compraBusquedaView, AltaActivoView altaActivoView) {
			this.view = compraBusquedaView;
			this.view2 = altaActivoView;
			this.compraDAO = new CompraDAOimpl();
			System.out.println("teste");
			initEventHandlers();
		}

		public void initEventHandlers() {
			view.setBuscarButtonActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					buscarFactura();
				}
			});
			
			view.setCancelarButtonActionListener(new ActionListener() {
	            public void actionPerformed(ActionEvent e) {
	                view.setVisible(false);
	            }
	        });
		}
		
		private void buscarFactura() {
			System.out.println("buscarFactura");

			String nroFactura = view.getNroFactura();
			System.out.println(nroFactura);
			Compra compra = compraDAO.buscarComprasPorNumeroFactura(nroFactura);

			if (compra != null) {
				System.out.println("Compra encontrada");
				view2.setBuscarFactura(nroFactura);
				view2.setBtnBuscarFacturaEnabled(false);
				view.setVisible(false);
			} else {
				JOptionPane.showMessageDialog(view, "No se encontró una factura denominada: " + nroFactura,
						"Error", JOptionPane.ERROR_MESSAGE);
				view.setVisible(false);
			}

		}
}
