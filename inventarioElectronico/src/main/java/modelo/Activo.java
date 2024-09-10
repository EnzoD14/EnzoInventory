package modelo;

import java.util.Date;

import javax.persistence.*;

@Entity
@Table(name="Activo")
public class Activo {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="idActivo")
    private String id;
	
	@OneToOne
	@JoinColumn(name="idGarantia", referencedColumnName = "idGarantia")
    private Garantia garantia;
	
	@OneToOne
	@JoinColumn(name="idReparacion", referencedColumnName = "idReparacion")
    private Reparacion reparacion;
	
	@OneToOne
	@JoinColumn(name="idCompra", referencedColumnName = "idCompra")
    private Compra compra;
	
	@Column(name = "tipo")
    private String tipo;
	
	@Column(name = "marca")
    private String marca;
	
	@Column(name = "modelo")
    private String modelo;
	
	@Column(name = "numeroSerie", nullable = false, unique = true)
    private String numeroSerie;
	
	@Column(name = "especificaciones")
    private String especificaciones;
	
	@Column(name = "fechaAlta")
    private Date fechaAlta;
	
	@Column(name = "fechaMantenimiento")
    private Date fechaMantenimiento;
	
	@Column(name = "codigoProducto")
    private String codigoProducto;
	
	@Column(name = "valor")
    private String valor;
	
	@Column(name = "estado")
    private String estado;
	
	@Column(name = "baja")
    private Integer baja;
    
    // Constructor
	public Activo(String id, Garantia garantia, Reparacion reparacion, Compra compra, String tipo,
			String marca, String modelo, String numeroSerie, String especificaciones, Date fechaAlta,
			Date fechaMantenimiento, String codigoProducto, String valor, String estado, Integer baja) {
		this.id = id;
		this.garantia = garantia;
		this.reparacion = reparacion;
		this.compra = compra;
		this.tipo = tipo;
		this.marca = marca;
		this.modelo = modelo;
		this.numeroSerie = numeroSerie;
		this.especificaciones = especificaciones;
		this.fechaAlta = fechaAlta;
		this.fechaMantenimiento = fechaMantenimiento;
		this.codigoProducto = codigoProducto;
		this.valor = valor;
		this.estado = estado;
		this.baja = baja;
	}

	public Activo() {
		// TODO Auto-generated constructor stub
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public Garantia getGarantia() {
		return garantia;
	}

	public void setGarantia(Garantia garantia) {
		this.garantia = garantia;
	}

	public Reparacion getReparacion() {
		return reparacion;
	}

	public void setReparacion(Reparacion reparacion) {
		this.reparacion = reparacion;
	}

	public Compra getCompra() {
		return compra;
	}

	public void setCompra(Compra compra) {
		this.compra = compra;
	}

	public String getTipo() {
		return tipo;
	}

	public void setTipo(String tipo) {
		this.tipo = tipo;
	}

	public String getMarca() {
		return marca;
	}

	public void setMarca(String marca) {
		this.marca = marca;
	}

	public String getModelo() {
		return modelo;
	}

	public void setModelo(String modelo) {
		this.modelo = modelo;
	}

	public String getNumeroSerie() {
		return numeroSerie;
	}

	public void setNumeroSerie(String numeroSerie) {
		this.numeroSerie = numeroSerie;
	}

	public String getEspecificaciones() {
		return especificaciones;
	}

	public void setEspecificaciones(String especificaciones) {
		this.especificaciones = especificaciones;
	}

	public Date getFechaAlta() {
		return fechaAlta;
	}

	public void setFechaAlta(Date fechaAlta) {
		this.fechaAlta = fechaAlta;
	}

	public Date getFechaMantenimiento() {
		return fechaMantenimiento;
	}

	public void setFechaMantenimiento(Date fechaMantenimiento) {
		this.fechaMantenimiento = fechaMantenimiento;
	}

	public String getCodigoProducto() {
		return codigoProducto;
	}

	public void setCodigoProducto(String codigoProducto) {
		this.codigoProducto = codigoProducto;
	}

	public String getValor() {
		return valor;
	}

	public void setValor(String valor) {
		this.valor = valor;
	}

	public String getEstado() {
		return estado;
	}

	public void setEstado(String estado) {
		this.estado = estado;
	}

	public Integer getBaja() {
		return baja;
	}

	public void setBaja(Integer baja) {
		this.baja = baja;
	}
	
}
