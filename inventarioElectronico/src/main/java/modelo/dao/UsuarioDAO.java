package modelo.dao;

import java.util.List;

import modelo.Usuario;

public interface UsuarioDAO {
	Boolean agregarUsuario(Usuario usuario);

	Boolean modificarUsuario(Usuario usuario);

	void eliminarUsuario(Usuario usuario);

	Usuario obtenerUsuarioPorUsuarioAd(String usuarioAd);

	List<Usuario> listarUsuarios();

	void imprimirUsuario(Usuario usuario);
}
