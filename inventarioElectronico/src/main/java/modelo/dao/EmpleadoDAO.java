package modelo.dao;

import java.util.List;

import modelo.Empleado;

public interface EmpleadoDAO {

	Boolean agregarEmpleado(Empleado empleado);
	
	Boolean modificarEmpleado(Empleado empleado);
	
	Boolean eliminarEmpleado(Empleado empleado);
	
	Empleado obtenerEmpleadoPorId(String nombre);
	
	List<Empleado> getAllEmpleados();
	
	List<Empleado> listarEmpleados();
	
}
