package modelo;

import java.sql.Date;

import javax.persistence.*;

@Entity
@Table(name="Compra")
public class Compra {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="idCompra")
	private String id;
	
	@OneToOne
	@JoinColumn(name="idProveedor", referencedColumnName = "idProveedor")
	private Proveedor proveedor;
	
	@Column(name="fechaCompra", length=255, nullable = true)
	private Date fechaCompra;
	
	@Column(name="numeroFactura", length=255, nullable = true)
	private String numeroFactura;
	
	// Constructor
	public Compra(String id, Proveedor proveedor, Date fechaCompra, String numeroFactura) {
		this.id = id;
		this.proveedor = proveedor;
		this.fechaCompra = fechaCompra;
		this.numeroFactura = numeroFactura;
	}

	public Compra() {
		// TODO Auto-generated constructor stub
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}
	
	public Proveedor getProveedor() {
		return proveedor;
	}
	
	public void setProveedor(Proveedor proveedor) {
        this.proveedor = proveedor;
    }

	public Date getFechaCompra() {
		return fechaCompra;
	}

	public void setFechaCompra(Date fechaCompra) {
		this.fechaCompra = fechaCompra;
	}

	public String getNumeroFactura() {
		return numeroFactura;
	}

	public void setNumeroFactura(String numeroFactura) {
		this.numeroFactura = numeroFactura;
	}
	
	
}
