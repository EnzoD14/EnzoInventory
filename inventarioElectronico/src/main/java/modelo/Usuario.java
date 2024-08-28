package modelo;

import javax.persistence.*;

@Entity
@Table(name = "Usuario")
public class Usuario {
	// Atributos de la clase Usuario
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "idUsuario")
	private String id;
	
	@Column(name = "usuarioAd", unique = true)
	private String usuarioAd;
	
	@Column(name = "contrasena")
	private String contrasena;
	
	@Column(name = "nombre")
	private String nombre;
	
	@Column(name = "apellido")
	private String apellido;
	
	@Column(name = "email")
	private String email;
	
	@Column(name = "tipoUsuario")
	private String tipoUsuario;
	
	@Column(name = "baja")
	private Boolean baja;

	// Constructor
	public Usuario(String id, String usuarioAd, String contrasena, String nombre, String apellido, String email,
			String tipoUsuario, Boolean baja) {
		this.id = id;
		this.usuarioAd = usuarioAd;
		this.contrasena = contrasena;
		this.nombre = nombre;
		this.apellido = apellido;
		this.email = email;
		this.tipoUsuario = tipoUsuario;
		this.baja = baja;
	}

	// Getters y Setters
	
	public String getId() {
		return id;
	}
	
	public void setId(String id) {
        this.id = id;
	}

	public String getUsuarioAd() {
		return usuarioAd;
	}

	public void setUsuarioAd(String usuarioAd) {
		this.usuarioAd = usuarioAd;
	}

	public String getContrasena() {
		return contrasena;
	}

	public void setContrasena(String contrasena) {
		this.contrasena = contrasena;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getApellido() {
		return apellido;
	}

	public void setApellido(String apellido) {
		this.apellido = apellido;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getTipoUsuario() {
		return tipoUsuario;
	}

	public void setTipoUsuario(String tipoUsuario) {
		this.tipoUsuario = tipoUsuario;
	}

	public Boolean getBaja() {
		return baja;
	}

	public void setBaja(Boolean baja) {
		this.baja = baja;
	}

	// Método para validar credenciales
	public boolean validarCredenciales(String usuario, String password) {
		// Lógica de validación simplificada
		return this.usuarioAd.equals(usuario) && password.equals("1234"); // solo para fines ilustrativos
	}
}
