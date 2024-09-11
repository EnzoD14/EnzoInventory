package view.events;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import view.screen.BackupView;

public class BackupEvents {
	private BackupView backupView;

	public BackupEvents(BackupView backupView) {
		this.backupView = backupView;
		initEventHandlers();
	}
	
	private void initEventHandlers() {
		backupView.setModificarButton(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				System.out.println("Modificar Backup");
				
			}
		});

		
	}
}
