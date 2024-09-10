package view.events;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Date;
import java.text.SimpleDateFormat;

import modelo.Compra;
import modelo.Proveedor;
import modelo.dao.impl.CompraDAOimpl;
import view.screen.CompraAltaView;

public class CompraAltaEvents {
	
	private CompraAltaView compraView;
	private CompraDAOimpl compraDAO;

	public CompraAltaEvents(CompraAltaView compraView) {
		this.compraView = compraView;
		this.compraDAO = new CompraDAOimpl();
		this.compraView.setVisible(true);
        initEventHandlers();
	}
	
	
	private void initEventHandlers() {
		
		compraView.getGrabarCompraButton().addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				grabarCompra();
			}
		});

		compraView.getCancelarCompraButton().addActionListener(new ActionListener() {
			 public void actionPerformed(ActionEvent e) {
			    compraView.dispose();
			 }
		});
		
	}
	
	private void grabarCompra() {
		
		System.out.println("Grabar Compra");
		Compra compra = new Compra();
		java.util.Date utilDate = compraView.getFechaCompraChooser().getDate();
	    java.sql.Date fechaCompra = new java.sql.Date(utilDate.getTime());
	    
	    System.out.println(compraView.getProveedoresComboBox().getSelectedItem());
		
		compra.setFechaCompra(fechaCompra);
		compra.setNumeroFactura(compraView.getNumFacturaField().getText());
		compra.setProveedor((Proveedor) compraView.getProveedoresComboBox().getSelectedItem());
		
		if (compraDAO.agregarCompra(compra)) {
			System.out.println("Compra guardada con exito bdd");
			compraView.dispose();
		} else {
			System.out.println("Error al guardar compra en bdd");
			compraView.dispose();
		}
		
	}
	
}
