package modelo.dao.impl;

import java.util.List;

import modelo.Usuario;

public class UsuarioDAOimpl {
	public UsuarioDAOimpl() {
	}

	public void agregarUsuario(Usuario usuario) {
	}

	public void modificarUsuario(Usuario usuario) {
	}

	public void eliminarUsuario(Usuario usuario) {
	}

	public Usuario obtenerUsuarioPorId(int id) {
		return null;
	}

	public List<Usuario> listarUsuarios() {
		return null;
	}

	public void imprimirUsuario(Usuario usuario) {
		System.out.println("Usuario: " + usuario.getNombre() + " " + usuario.getApellido() + "\nUsuario: "
				+ usuario.getUsuarioAd() + "\nDepartamento: " + usuario.getTipoUsuario() + ".");
	}
}
