package modelo.dao;

import java.util.List;

import modelo.Usuario;

public interface UsuarioDAO {
	void agregarUsuario(Usuario usuario);

	void modificarUsuario(Usuario usuario);

	void eliminarUsuario(Usuario usuario);

	Usuario obtenerUsuarioPorId(int id);

	List<Usuario> listarUsuarios();

	void imprimirUsuario(Usuario usuario);
}
