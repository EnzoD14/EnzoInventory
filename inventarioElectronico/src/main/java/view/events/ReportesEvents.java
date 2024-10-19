package view.events;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.Period;
import java.time.ZoneId;
import java.time.temporal.ChronoUnit;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.swing.JFileChooser;
import javax.swing.JOptionPane;

import modelo.Activo;
import modelo.Solicitud;
import modelo.dao.impl.ActivoDAOimpl;
import modelo.dao.impl.SolicitudDAOimpl;
import view.screen.ReportesView;

public class ReportesEvents {
	
	private ReportesView reportesView;
	
	public ReportesEvents(ReportesView reportesView) {
		if (reportesView == null) {
			throw new IllegalArgumentException("reportesView cannot be null");
		}

		this.reportesView = reportesView;
		initEventHandlers();
	}
	
	private void initEventHandlers() {

		reportesView.setReporte1Listener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				obtenerActivosSectorCsv();
			}
		});

		reportesView.setReporte2Listener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				obtenerActivosBackupCsv();
			}
		});

		reportesView.setReporte3Listener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				obtenerActivosReparacionCsv();
			}
		});

		reportesView.setReporte4Listener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				obtenerProveedoresCsv();
			}
		});

		reportesView.setReporte5Listener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				//obtenerAmortizacionCsv();
				obtenerAmortizacionCsv();
			}
		});
		
		reportesView.setReporte6Listener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				try {
					obtenerActivosBajaCsv();
				} catch (NumberFormatException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		});
		
		reportesView.setReporte7Listener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				obtenerActivosGarantiaCsv();
			}
		});

	}
	
	private void obtenerActivosSectorCsv() {
		JFileChooser fileChooser = new JFileChooser();
		int userSelection = fileChooser.showSaveDialog(reportesView);
		
		if (userSelection == JFileChooser.APPROVE_OPTION) {
			File fileToSave = fileChooser.getSelectedFile();
			
			try {
				FileWriter csvActivos = new FileWriter(fileToSave.getAbsolutePath() + ".csv");
				ActivoDAOimpl activoDAO = new ActivoDAOimpl();
				
				csvActivos.append("Sector");
				csvActivos.append(",");
				csvActivos.append("Usuario");
				csvActivos.append(",");
				csvActivos.append("Tipo");
				csvActivos.append(",");
				csvActivos.append("Marca");
				csvActivos.append(",");
				csvActivos.append("Modelo");
				csvActivos.append(",");
				csvActivos.append("Numero de Serie");
				csvActivos.append(",");
				csvActivos.append("Especificaciones");
				csvActivos.append(",");
				csvActivos.append("Valor");
				csvActivos.append(",");
				csvActivos.append("Estado");
				csvActivos.append(",");
				csvActivos.append("Fecha de Alta");
				csvActivos.append(",");
				csvActivos.append("Fecha de Mantenimiento");
				csvActivos.append(",");
				csvActivos.append("Meses de Amortizacion");
				csvActivos.append("\n");
				
				List<Activo> listaActivos = activoDAO.listarActivos(null);
				listaActivos.sort(Comparator.comparing(activo -> activo.getEmpleado().getSector()));
				
				for (Activo activo : listaActivos) {
					String sector = activo.getEmpleado().getSector();
					String usuario = activo.getEmpleado().getUsuarioAD();
					csvActivos.append(sector);
	                csvActivos.append(",");
	                csvActivos.append(usuario);
	                csvActivos.append(",");
					csvActivos.append(activo.getTipo());
	                csvActivos.append(",");
	                csvActivos.append(activo.getMarca());
	                csvActivos.append(",");
	                csvActivos.append(activo.getModelo());
	                csvActivos.append(",");
	                csvActivos.append(activo.getNumeroSerie());
	                csvActivos.append(",");
	                csvActivos.append(activo.getEspecificaciones());
	                csvActivos.append(",");
	                csvActivos.append(String.valueOf(activo.getValor()));
	                csvActivos.append(",");
	                csvActivos.append(activo.getEstado());
	                csvActivos.append(",");
	                csvActivos.append(activo.getFechaAlta().toString());
	                csvActivos.append(",");
	                csvActivos.append(activo.getFechaMantenimiento().toString());
	                csvActivos.append(",");
	                csvActivos.append(String.valueOf(activo.getMesesAmortizacion()));
	                csvActivos.append("\n");
				}	
				csvActivos.flush();
				csvActivos.close();
				String rutaArchivo = fileToSave.getAbsolutePath();
				JOptionPane.showMessageDialog(null, "Reporte ActivosSector generado con exito y guardado en " + rutaArchivo + ".csv");
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}
	
	private void obtenerActivosBackupCsv() {
		JFileChooser fileChooser = new JFileChooser();
		int userSelection = fileChooser.showSaveDialog(reportesView);
		
		if (userSelection == JFileChooser.APPROVE_OPTION) {
			File fileToSave = fileChooser.getSelectedFile();
			
			try {
				FileWriter csvActivos = new FileWriter(fileToSave.getAbsolutePath() + ".csv");
				ActivoDAOimpl activoDAO = new ActivoDAOimpl();
				
				csvActivos.append("Tipo");
				csvActivos.append(",");
				csvActivos.append("Marca");
				csvActivos.append(",");
				csvActivos.append("Modelo");
				csvActivos.append(",");
				csvActivos.append("Especificaciones");
				csvActivos.append("\n");
				
				List<Activo> listaActivos = activoDAO.listarActivos(null);
				//listaActivos.sort(Comparator.comparing(activo -> activo.getEmpleado().getSector()));
				
				for (Activo activo : listaActivos) {
					if(activo.getEstado().equals("Backup")){
						csvActivos.append(activo.getTipo());
		                csvActivos.append(",");
		                csvActivos.append(activo.getMarca());
		                csvActivos.append(",");
		                csvActivos.append(activo.getModelo());
		                csvActivos.append(",");
		                csvActivos.append(activo.getEspecificaciones());
		                csvActivos.append("\n");
					}
				}	
				csvActivos.flush();
				csvActivos.close();
				String rutaArchivo = fileToSave.getAbsolutePath();
				JOptionPane.showMessageDialog(null, "Reporte ActivosBackup generado con exito y guardado en " + rutaArchivo + ".csv");
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}
	
	private void obtenerActivosReparacionCsv() {
		JFileChooser fileChooser = new JFileChooser();
		int userSelection = fileChooser.showSaveDialog(reportesView);
		
		if (userSelection == JFileChooser.APPROVE_OPTION) {
			File fileToSave = fileChooser.getSelectedFile();
			
			try {
				FileWriter csvActivos = new FileWriter(fileToSave.getAbsolutePath() + ".csv");
				ActivoDAOimpl activoDAO = new ActivoDAOimpl();
				
				csvActivos.append("Tipo");
				csvActivos.append(",");
				csvActivos.append("Marca");
				csvActivos.append(",");
				csvActivos.append("Modelo");
				csvActivos.append(",");
				csvActivos.append("Especificaciones");
				csvActivos.append(",");
				csvActivos.append("Fecha Reparacion");
				csvActivos.append(",");
				csvActivos.append("Motivo Reparacion");
				csvActivos.append("\n");
				
				List<Activo> listaActivos = activoDAO.listarActivos(null);
				//listaActivos.sort(Comparator.comparing(activo -> activo.getEmpleado().getSector()));
				
				for (Activo activo : listaActivos) {
					if(activo.getReparacion() != null){
						csvActivos.append(activo.getTipo());
		                csvActivos.append(",");
		                csvActivos.append(activo.getMarca());
		                csvActivos.append(",");
		                csvActivos.append(activo.getModelo());
		                csvActivos.append(",");
		                csvActivos.append(activo.getEspecificaciones());
		                csvActivos.append(",");
		                csvActivos.append(activo.getReparacion().getFechaInicio().toString());
		                csvActivos.append(",");
		                csvActivos.append(activo.getReparacion().getMotivoReparacion());
		                csvActivos.append("\n");
					}
				}	
				csvActivos.flush();
				csvActivos.close();
				String rutaArchivo = fileToSave.getAbsolutePath();
				JOptionPane.showMessageDialog(null, "Reporte ActivosReparacion generado con exito y guardado en " + rutaArchivo + ".csv");
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}
	
	private void obtenerProveedoresCsv() {
		JFileChooser fileChooser = new JFileChooser();
		int userSelection = fileChooser.showSaveDialog(reportesView);
		
		if (userSelection == JFileChooser.APPROVE_OPTION) {
			File fileToSave = fileChooser.getSelectedFile();
			
			try {
				FileWriter csvProveedores = new FileWriter(fileToSave.getAbsolutePath() + ".csv");
			      ActivoDAOimpl activoDAO = new ActivoDAOimpl();

			      csvProveedores.append("Sector");
			      csvProveedores.append(",");
			      csvProveedores.append("Proveedor");
			      csvProveedores.append(",");
			      csvProveedores.append("Cantidad");
			      csvProveedores.append("\n");

			      List<Activo> listaActivos = activoDAO.listarActivos(null);

			      // Crear un mapa para almacenar el conteo de proveedores por sector
			      Map<String, Map<String, Integer>> proveedoresPorSector = new HashMap<>();

			      for (Activo activo : listaActivos) {
			        String sector = activo.getEmpleado().getSector();
			        if (activo.getCompra() != null && activo.getCompra().getProveedor() != null && activo.getCompra().getProveedor().getRazonSocial() != null) {
			        	String proveedor = activo.getCompra().getProveedor().getRazonSocial();
			        	
			        	// Actualizar el mapa
				        proveedoresPorSector.putIfAbsent(sector, new HashMap<>());
				        proveedoresPorSector.get(sector).put(proveedor, proveedoresPorSector.get(sector).getOrDefault(proveedor, 0) + 1);
			        }

			      }

			      // Escribir la información en el archivo CSV
			      for (Map.Entry<String, Map<String, Integer>> entry : proveedoresPorSector.entrySet()) {
			        String sector = entry.getKey();
			        for (Map.Entry<String, Integer> subEntry : entry.getValue().entrySet()) {
			          String proveedor = subEntry.getKey();
			          int cantidad = subEntry.getValue();

			          csvProveedores.append(sector);
			          csvProveedores.append(",");
			          csvProveedores.append(proveedor);
			          csvProveedores.append(",");
			          csvProveedores.append(String.valueOf(cantidad));
			          csvProveedores.append("\n");
			        }
			      }

			      csvProveedores.flush();
			      csvProveedores.close();
			      String rutaArchivo = fileToSave.getAbsolutePath();
			      JOptionPane.showMessageDialog(null, "Reporte ProveedoresPorSector generado con exito y guardado en " + rutaArchivo + ".csv");
			    } catch (IOException e) {
			      e.printStackTrace();
			    }
		}
	 }
	
	private void obtenerAmortizacionCsv() {
		ActivoDAOimpl activoDAO = new ActivoDAOimpl();
		List<Activo> listaActivos = activoDAO.listarActivos(null);
		String valorAmortizacion = null;
		
		for (Activo activo : listaActivos) {
			valorAmortizacion  = "0";
            if (activo.getMesesAmortizacion() != null) {
            	
            	LocalDate fechaAlta = activo.getFechaAlta().toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
            	LocalDate fechaHoy = LocalDate.now();	
            	long mesesTranscurridos = ChronoUnit.MONTHS.between(fechaAlta, fechaHoy);
            	
            	if(mesesTranscurridos <= activo.getMesesAmortizacion()){
	            	// Calcular el valor de amortización
	            	double valorActivo = Double.parseDouble(activo.getValor());
	            	int amortizacionMensual = (int) (valorActivo / activo.getMesesAmortizacion());
	            	int mesesRestantes = activo.getMesesAmortizacion() - (int) mesesTranscurridos;
	            	valorAmortizacion = String.valueOf(amortizacionMensual * mesesRestantes);
            	}
            	
            	activo.setValorAmortizacion(valorAmortizacion);
                activoDAO.modificarActivo(activo);
            }
        }
	}
	
	
	private void obtenerActivosBajaCsv() throws NumberFormatException, SQLException {
		JFileChooser fileChooser = new JFileChooser();
		int userSelection = fileChooser.showSaveDialog(reportesView);
		
		if (userSelection == JFileChooser.APPROVE_OPTION) {
			File fileToSave = fileChooser.getSelectedFile();
			
			try {
				FileWriter csvActivos = new FileWriter(fileToSave.getAbsolutePath() + ".csv");
				ActivoDAOimpl activoDAO = new ActivoDAOimpl();
				
				csvActivos.append("Tipo");
				csvActivos.append(",");
				csvActivos.append("Marca");
				csvActivos.append(",");
				csvActivos.append("Modelo");
				csvActivos.append(",");
				csvActivos.append("Numero de Serie");
				csvActivos.append(",");
				csvActivos.append("Especificaciones");
				csvActivos.append(",");
				csvActivos.append("Valor");
				csvActivos.append(",");
				csvActivos.append("Estado");
				csvActivos.append(",");
				csvActivos.append("Fecha de Alta");
				csvActivos.append(",");
				csvActivos.append("Fecha Baja");
				csvActivos.append(",");
				csvActivos.append("Motivo Baja");
				csvActivos.append("\n");
				
				List<Activo> listaActivos = activoDAO.listarActivosConBaja(null);
				
				for (Activo activo : listaActivos) {
					System.out.println(activo.getBaja());
					SolicitudDAOimpl scBaja = new SolicitudDAOimpl();
					Solicitud solicitud = new Solicitud();
					solicitud = scBaja.obtenerSolicitudPorIdActivo(activo.getId(), "Baja");
					//System.out.println(solicitud.getMotivoBaja());
					
					if (activo.getBaja() == 1 && solicitud != null) {
						csvActivos.append(activo.getTipo());
		                csvActivos.append(",");
		                csvActivos.append(activo.getMarca());
		                csvActivos.append(",");
		                csvActivos.append(activo.getModelo());
		                csvActivos.append(",");
		                csvActivos.append(activo.getNumeroSerie());
		                csvActivos.append(",");
		                csvActivos.append(activo.getEspecificaciones());
		                csvActivos.append(",");
		                csvActivos.append(String.valueOf(activo.getValor()));
		                csvActivos.append(",");
		                csvActivos.append(activo.getEstado());
		                csvActivos.append(",");
		                csvActivos.append(activo.getFechaAlta().toString());
		                csvActivos.append(",");
		                csvActivos.append(solicitud.getFechaSolicitud().toString());
		                csvActivos.append(",");
		                csvActivos.append(solicitud.getMotivoBaja());
		                csvActivos.append("\n");
					}
				}	
				csvActivos.flush();
				csvActivos.close();
				String rutaArchivo = fileToSave.getAbsolutePath();
				JOptionPane.showMessageDialog(null, "Reporte ActivosBaja generado con exito y guardado en " + rutaArchivo + ".csv");
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}
	
	private void obtenerActivosGarantiaCsv() {
		JFileChooser fileChooser = new JFileChooser();
		int userSelection = fileChooser.showSaveDialog(reportesView);
		
		if (userSelection == JFileChooser.APPROVE_OPTION) {
			File fileToSave = fileChooser.getSelectedFile();
			
			try {
				FileWriter csvActivos = new FileWriter(fileToSave.getAbsolutePath() + ".csv");
				ActivoDAOimpl activoDAO = new ActivoDAOimpl();
				
				csvActivos.append("Tipo");
				csvActivos.append(",");
				csvActivos.append("Marca");
				csvActivos.append(",");
				csvActivos.append("Modelo");
				csvActivos.append(",");
				csvActivos.append("Numero de Serie");
				csvActivos.append(",");
				csvActivos.append("Estado");
				csvActivos.append(",");
				csvActivos.append("Fecha de Alta");
				csvActivos.append(",");
				csvActivos.append("Garantia");
				csvActivos.append(",");
				csvActivos.append("Meses restantes");
				csvActivos.append(",");
				csvActivos.append("Finalizacion Garantia");	
				csvActivos.append("\n");
				
				List<Activo> listaActivos = activoDAO.listarActivos(null);
				
				for (Activo activo : listaActivos) {
					String garantia = "";
					int mesesGarantia = 0;
					java.util.Date garantiaFecha = activo.getGarantia().getFechaFin();
					java.util.Date fechaActual = new java.util.Date();
					LocalDate fechaHoy = LocalDate.now();
					
					if (garantiaFecha.before(fechaActual)) {
						garantia = "NO";
					} else {
						garantia = "SI";
						Period periodo = Period.between(fechaHoy, garantiaFecha.toInstant().atZone(ZoneId.systemDefault()).toLocalDate());
						mesesGarantia = periodo.getMonths() + periodo.getYears() * 12;
					}
					
					csvActivos.append(activo.getTipo());
	                csvActivos.append(",");
	                csvActivos.append(activo.getMarca());
	                csvActivos.append(",");
	                csvActivos.append(activo.getModelo());
	                csvActivos.append(",");
	                csvActivos.append(activo.getNumeroSerie());
	                csvActivos.append(",");
	                csvActivos.append(activo.getEstado());
	                csvActivos.append(",");
	                csvActivos.append(activo.getFechaAlta().toString());
	                csvActivos.append(",");
	                csvActivos.append(garantia);
	                csvActivos.append(",");
	                csvActivos.append(String.valueOf(mesesGarantia));
	                csvActivos.append(",");
	                csvActivos.append(garantiaFecha.toString());
	                csvActivos.append("\n");
				}	
				csvActivos.flush();
				csvActivos.close();
				String rutaArchivo = fileToSave.getAbsolutePath();
				JOptionPane.showMessageDialog(null, "Reporte ActivosGarantia generado con exito y guardado en " + rutaArchivo + ".csv");
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}
	
	

}
