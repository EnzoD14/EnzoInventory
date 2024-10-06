package modelo;

import javax.persistence.*;

@Entity
@Table(name = "Empleado")
public class Empleado {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "idEmpleado", nullable = true)
	private String id;
	
	@Column(name = "nombre")
	private String nombre;
	
	@Column(name = "apellido")
	private String apellido;
	
	@Column(name = "usuarioAD")
	private String usuarioAD;
	
	@Column(name = "email")
	private String email;
	
	@Column(name = "sector")
	private String sector;
	
	@Column(name = "baja")
	private int baja;
	
	public Empleado(String nombre, String apellido, String usuarioAd , String email, String sector, int baja) {
		this.nombre = nombre;
		this.apellido = apellido;
		this.usuarioAD = usuarioAd;
		this.email = email;
		this.sector = sector;
		this.baja = baja;
	}
	
	public Empleado() {
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
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
	
	public String getUsuarioAD() {
		return usuarioAD;
	}
	
	public void setUsuarioAD(String usuarioAD) {
		this.usuarioAD = usuarioAD;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getSector() {
		return sector;
	}

	public void setSector(String sector) {
		this.sector = sector;
	}

	public int getBaja() {
		return baja;
	}

	public void setBaja(int baja) {
		this.baja = baja;
	}
	
	@Override
	public String toString() {
		return usuarioAD + (" (") + sector + (").");
	}
	
}
