package view.events;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;

import javax.swing.JFileChooser;

import modelo.Activo;
import modelo.dao.impl.ActivoDAOimpl;
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
				obtenerActivosCsv();
				System.out.println("Reporte 1");
			}
		});

		reportesView.setReporte2Listener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				//reporte2();
			}
		});

		reportesView.setReporte3Listener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				//reporte3();
			}
		});

		reportesView.setReporte4Listener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				//reporte4();
			}
		});

		reportesView.setReporte5Listener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				//reporte5();
			}
		});

	}
	
	private void obtenerActivosCsv() {
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
				csvActivos.append("Fecha de Mantenimiento");
				csvActivos.append(",");
				csvActivos.append("Meses de Amortizacion");
				csvActivos.append("\n");
				
				List<Activo> listaActivos = activoDAO.listarActivos(null);
				
				for (Activo activo : listaActivos) {
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
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
			
	}

}
