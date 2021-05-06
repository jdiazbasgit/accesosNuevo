package cap.curso.accesos.servicios;

import org.springframework.stereotype.Service;

import cap.curso.accesos.DTOs.RegistroDto;
import cap.curso.accesos.entidades.Calendario;
import cap.curso.accesos.entidades.Empleado;
import cap.curso.accesos.entidades.EmpleadoEstado;

@Service
public interface UsuariosEstadoServiceInterface {
	
	public Iterable<EmpleadoEstado> findAll();
	
	public EmpleadoEstado findById(Integer id);
	
	public EmpleadoEstado save(RegistroDto registro);
	
	public EmpleadoEstado save(EmpleadoEstado ue);
	
	public void delete(EmpleadoEstado ue);

	public EmpleadoEstado getDiasTrabajadosEmpleadoByYearByMes (Empleado empleado, Calendario calendario);
}
