package view.events;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Date;
import java.time.LocalDate;
import modelo.Activo;
import modelo.Solicitud;
import view.screen.SolicitudAltaView;

public class SolicitudAltaEvent {
	
	private SolicitudAltaView solicitudAltaView;
	

	public SolicitudAltaEvent(SolicitudAltaView solicitudAltaView) {
		this.solicitudAltaView = solicitudAltaView;
		initEventHandlers();
	}
       

    private void initEventHandlers() {
       
		solicitudAltaView.setBtnGuardar(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				guardarSolicitud();
			}
		});

		solicitudAltaView.setBtnCancelar(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				System.out.println("Cancelar Solicitud");
				solicitudAltaView.dispose();
			}
		});
    	
    }
    
	public void guardarSolicitud() {
    	
    	Solicitud solicitud = new Solicitud();
    	Activo activo = new Activo();
    	LocalDate fechaSolicitud = LocalDate.now();
    	Date fechaSolicitudSQL = Date.valueOf(fechaSolicitud);
    	
    	activo.setTipo(solicitudAltaView.getTipo());
    	activo.setMarca(solicitudAltaView.getMarca());
    	activo.setModelo(solicitudAltaView.getModelo());
    	activo.setNumeroSerie(solicitudAltaView.getNumeroSerie());
    	activo.setEspecificaciones(solicitudAltaView.getEspecificaciones());
    	activo.setValor(solicitudAltaView.getValor());
    	activo.setCodigoProducto(solicitudAltaView.getCodigoProducto());
    	activo.setEstado("");
    	
    	solicitud.setActivo(activo);
    	solicitud.setTipoSolicitud("ALTA");
    	solicitud.setFechaSolicitud(fechaSolicitudSQL);
    	solicitud.setBaja(0);
    	
	}

    

}
