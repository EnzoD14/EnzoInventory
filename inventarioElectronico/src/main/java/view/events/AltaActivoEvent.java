package view.events;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Date;
import java.sql.SQLException;
import java.time.LocalDate;

import javax.swing.JOptionPane;

import controlador.ActivoController;
import controlador.CompraController;
import modelo.Activo;
import modelo.Compra;
import modelo.dao.CompraDAO;
import modelo.dao.impl.ActivoDAOimpl;
import modelo.dao.impl.CompraDAOimpl;
import view.screen.ActivoAltaView;
import view.screen.CompraBusquedaView;

public class AltaActivoEvent {
	private CompraBusquedaView viewCompra;
	private ActivoAltaView vista;
    private CompraDAOimpl compraDAO;
    private ActivoDAOimpl activoDAO;

    public AltaActivoEvent(CompraBusquedaView viewCompra, ActivoAltaView vista, CompraDAOimpl compraDAO, ActivoDAOimpl activoDAO) {
        this.viewCompra = viewCompra;
    	this.vista = vista;
        this.compraDAO = new CompraDAOimpl();
        this.activoDAO = new ActivoDAOimpl();
        initEventHandlers();
    }

	private void initEventHandlers() {
		
        vista.setControladorGuardar(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
					guardarActivo();
					System.out.println("teste");
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
            }
        });

        vista.setControladorCancelar(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                vista.dispose(); // Cierra la ventana si el usuario cancela
            }
        });
        
		vista.setControladorBuscarFactura(new ActionListener() {
			@Override
            public void actionPerformed(ActionEvent e) {
				
                System.out.println("Encontro");
                viewCompra = new CompraBusquedaView();
                new CompraBusquedaEvent(viewCompra, vista);
                viewCompra.setVisible(true);
            }
		});
    }

    private void guardarActivo() throws SQLException {
        Boolean flag = null;
        System.out.println("guardarActivo");
        Compra compra = compraDAO.buscarComprasPorNumeroFactura(vista.getBuscarFactura());
        LocalDate fecha = LocalDate.now();
        Date fechaSql = Date.valueOf(fecha);
        System.out.println(vista.getEstado());
        
        Activo activo = new Activo();
        activo.setTipo(vista.getTipo());
        activo.setMarca(vista.getMarca());
        activo.setModelo(vista.getModelo());
        activo.setNumeroSerie(vista.getNumeroSerie());
        activo.setEspecificaciones(vista.getEspecificaciones());
        activo.setFechaAlta(fechaSql);
        activo.setFechaMantenimiento(null);
        activo.setCodigoProducto(vista.getCodigoProducto());
        activo.setValor(vista.getValor());
        activo.setEstado(vista.getEstado());
        activo.setCompra(compra);
        activo.setBaja(0);
        
		if (flag = activoDAO.agregarActivo(activo)) {
			JOptionPane.showMessageDialog(null, "Activo guardado con exito bdd", "Información", JOptionPane.INFORMATION_MESSAGE);
			vista.dispose();
		} else {
			JOptionPane.showMessageDialog(null, "Error al guardar activo en bdd", "Error", JOptionPane.ERROR_MESSAGE);
			vista.dispose();
		}
		
        vista.dispose();
    }
    
}
