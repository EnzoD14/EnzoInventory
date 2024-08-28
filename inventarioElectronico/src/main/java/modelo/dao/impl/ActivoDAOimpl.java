package modelo.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import utils.DatabaseConnection;
import java.util.List;

import modelo.Activo;
import modelo.dao.ActivoDAO;

public class ActivoDAOimpl implements ActivoDAO {
	
	
	public ActivoDAOimpl() {
		
	}

	@Override
	public void agregarActivo(Activo activo) throws SQLException {
		
	}

	@Override
	public void modificarActivo(Activo activo) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void eliminarActico(Activo activo) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public Activo obtenerActivoPorId(int id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Activo> listarActivos() {
		// TODO Auto-generated method stub
		return null;
	}
	
	@Override
	public void imprimirActivo(Activo activo) {
		System.out.println("Modelo Activo: " + activo.getModelo() + "\nActivo: " + activo.getTipo() 
		+ "\nMarca Activo: " + activo.getMarca() + ".");
	}

}
