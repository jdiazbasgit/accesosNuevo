package cap.curso.accesos.servicios;

import org.springframework.stereotype.Service;

import cap.curso.accesos.entidades.Empleado;
import cap.curso.accesos.entidades.Jornada;
import cap.curso.accesos.exception.EmpleadoNotFoundException;

@Service
public interface EmpleadoServiceInterface
{
	public Empleado save(Empleado empleado);
	
	public Iterable<Empleado> findAll();
	
	public Empleado findById(Integer id);
	
	public Empleado modificarJornada(Integer idEmpleado, Jornada idJornada) throws EmpleadoNotFoundException;
}
