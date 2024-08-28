package modelo;

import java.util.Date;

import javax.persistence.*;

@Entity
@Table(name="Reparacion")
public class Reparacion {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="idReparacion")
	private String id;
	
	@Column(name="motivoReparacion")
	private String motivoReparacion;
	
	@Column(name="fechaInicio")
	private Date fechaInicio;
	
	@Column(name="enReparacion")
	private Boolean enReparacion;
	
	@Column(name="valorReparacion")
	private String valor;
	
	// Constructor
	public Reparacion(String id, String motivoReparacion, Date fechaInicio, Boolean enReparacion, String valor) {
		this.id = id;
		this.motivoReparacion = motivoReparacion;
		this.fechaInicio = fechaInicio;
		this.enReparacion = enReparacion;
		this.valor = valor;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getMotivoReparacion() {
		return motivoReparacion;
	}

	public void setMotivoReparacion(String motivoReparacion) {
		this.motivoReparacion = motivoReparacion;
	}

	public Date getFechaInicio() {
		return fechaInicio;
	}

	public void setFechaInicio(Date fechaInicio) {
		this.fechaInicio = fechaInicio;
	}

	public Boolean getEnReparacion() {
		return enReparacion;
	}

	public void setEnReparacion(Boolean enReparacion) {
		this.enReparacion = enReparacion;
	}

	public String getValor() {
		return valor;
	}

	public void setValor(String valor) {
		this.valor = valor;
	}
	
	
}
