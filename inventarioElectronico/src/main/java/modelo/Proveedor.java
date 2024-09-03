package modelo;

import javax.persistence.*;

@Entity
@Table(name = "Proveedor")
public class Proveedor {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "idProveedor")
	private String id;
	
	@Column(name = "nombre")
	private String nombre;
	
	@Column(name = "razonSocial")
	private String razonSocial;
	
	@Column(name = "email")
	private String email;
	
	@Column(name = "telefono")
	private String telefono;
	
	@Column(name = "baja", nullable = false)
	private int baja;
	
	// Constructor
	public Proveedor(String id, String nombre, String razonSocial, String email, String telefono,
			int baja) {
		this.id = id;
		this.nombre = nombre;
		this.razonSocial = razonSocial;
		this.email = email;
		this.telefono = telefono;
		this.baja = baja;
	}

	public Proveedor(String nombre, String razonSocial, String email, String telefono,
			int baja) {
		// TODO Auto-generated constructor stub
		this.nombre = nombre;
		this.razonSocial = razonSocial;
		this.email = email;
		this.telefono = telefono;
		this.baja = baja;
	}

	public Proveedor() {
		super();
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

	public String getRazonSocial() {
		return razonSocial;
	}

	public void setRazonSocial(String razonSocial) {
		this.razonSocial = razonSocial;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getTelefono() {
		return telefono;
	}

	public void setTelefono(String telefono) {
		this.telefono = telefono;
	}

	public int getBaja() {
		return baja;
	}

	public void setBaja(int baja) {
		this.baja = baja;
	}
	
	@Override
	public String toString() {
		return razonSocial;
	}
	
}
