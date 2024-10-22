package modelo.dao;

import java.util.List;

import modelo.Reparacion;

public interface ReparacionDAO {
	Boolean agregarReparacion(Reparacion reparacion);

	Boolean modificarReparacion(Reparacion reparacion);

	void eliminarReparacion(Reparacion reparacion);

	Reparacion obtenerReparacionPorId(int id);

	Reparacion obtenerReparacionPorMotivo(String motivo);

	List<Reparacion> listarReparaciones(String motivo);

	List<Reparacion> listarReparacionesConBaja(String motivo);

	void imprimirReparacion(Reparacion reparacion);
}
