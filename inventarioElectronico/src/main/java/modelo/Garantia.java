package modelo;

import java.util.Date;

import javax.persistence.*;

@Entity
@Table(name="Garantia")
public class Garantia {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="idGarantia")
	private String id;
	
	@Column(name="fechaInicio")
	private Date fechaInicio;
	
	@Column(name="fechaFin")
	private Date fechaFin;
	
	// Constructor
	public Garantia(String id, Date fechaInicio, Date fechaFin) {
		this.id = id;
		this.fechaInicio = fechaInicio;
		this.fechaFin = fechaFin;
	}
	
	public Garantia() {
	// TODO Auto-generated
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public Date getFechaInicio() {
		return fechaInicio;
	}

	public void setFechaInicio(Date fechaInicio) {
		this.fechaInicio = fechaInicio;
	}

	public Date getFechaFin() {
		return fechaFin;
	}

	public void setFechaFin(Date fechaFin) {
		this.fechaFin = fechaFin;
	}
	
	
}
