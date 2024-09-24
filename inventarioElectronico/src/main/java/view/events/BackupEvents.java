package view.events;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JOptionPane;
import modelo.Activo;
import modelo.dao.impl.ActivoDAOimpl;
import view.screen.BackupView;

public class BackupEvents {
	private BackupView backupView;

	public BackupEvents(BackupView backupView) {
		this.backupView = backupView;
		initEventHandlers();
	}
	
	private void initEventHandlers() {
		backupView.setBackupButton(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				System.out.println("Backup");
				modificarActivo("Backup");
			}
		});
		
		backupView.setEnUsoButton(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				System.out.println("En uso");
				modificarActivo("Utilizada");
			}
		});

		
	}
	
	private void modificarActivo(String estado) {
		ActivoDAOimpl activoDAO = new ActivoDAOimpl();
		Activo activo = new Activo();
		System.out.println("Backup");
		String numeroSerie = backupView.getNumeroSerie();
		
		activo = activoDAO.obtenerActivoPorNumeroSerie(numeroSerie);
		System.out.println(activo.getMarca());
		
		activo.setEstado(estado);
		activoDAO.modificarActivo(activo);
		
		JOptionPane.showMessageDialog(null, "Activo asignado como " + estado + " correctamente.");
	}
}
