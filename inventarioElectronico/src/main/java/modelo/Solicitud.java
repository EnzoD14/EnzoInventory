package modelo;

import java.util.Date;

import javax.persistence.*;

@Entity
@Table(name="Solicitud")
public class Solicitud {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="idSolicitud")
	private String id;
	
	@OneToOne
	@JoinColumn(name="idActivo")
	private Activo activo;
	
	@OneToOne
	@JoinColumn(name="idUsuario")
	private Usuario usuario;
	
	@Column(name="tipoSolicitud")
	private String tipoSolicitud;
	
	@Column(name="fechaSolicitud")
	private Date fechaSolicitud;
	
	@Column(name="motivoBaja")
	private String motivoBaja;
	
	@Column(name="estado")
	private Boolean estado;
	
	@Column(name="baja")
	private Boolean baja;
	
	// Constructor
	public Solicitud(String id, Activo activo, Usuario usuario, String tipoSolicitud, Date fechaSolicitud,
			String motivoBaja, Boolean estado, Boolean baja) {
		this.id = id;
		this.activo = activo;
		this.usuario = usuario;
		this.tipoSolicitud = tipoSolicitud;
		this.fechaSolicitud = fechaSolicitud;
		this.motivoBaja = motivoBaja;
		this.estado = estado;
		this.baja = baja;
	}
	
	public Solicitud() {
	// TODO Auto-generated
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public Activo getActivo() {
		return activo;
	}

	public void setActivo(Activo activo) {
		this.activo = activo;
	}

	public Usuario getUsuario() {
		return usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}

	public String getTipoSolicitud() {
		return tipoSolicitud;
	}

	public void setTipoSolicitud(String tipoSolicitud) {
		this.tipoSolicitud = tipoSolicitud;
	}

	public Date getFechaSolicitud() {
		return fechaSolicitud;
	}

	public void setFechaSolicitud(Date fechaSolicitud) {
		this.fechaSolicitud = fechaSolicitud;
	}

	public String getMotivoBaja() {
		return motivoBaja;
	}

	public void setMotivoBaja(String motivoBaja) {
		this.motivoBaja = motivoBaja;
	}

	public Boolean getEstado() {
		return estado;
	}

	public void setEstado(Boolean estado) {
		this.estado = estado;
	}

	public Boolean getBaja() {
		return baja;
	}

	public void setBaja(Boolean baja) {
		this.baja = baja;
	}
	
	
}
