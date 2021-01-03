package cap.curso.accesos.controladores;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import cap.curso.accesos.entidades.Empleado;
import cap.curso.accesos.entidades.Jornada;
import cap.curso.accesos.exception.EmpleadoNotFoundException;
import cap.curso.accesos.servicios.EmpleadoServiceInterface;

@RestController
@RequestMapping("/api/empleados")
public class EmpleadosRestController
{

	@Autowired
	private EmpleadoServiceInterface empleadosService;

	public EmpleadoServiceInterface getEmpleadosService()
	{
		return empleadosService;
	}

	public void setEmpleadosService(EmpleadoServiceInterface empleadosService)
	{
		this.empleadosService = empleadosService;
	}

	@GetMapping("/")
	public Iterable<Empleado> getAllEmpleados()
	{
		return getEmpleadosService().findAll();
	}

	@GetMapping("/{id}")
	public Empleado getEmpleado(@PathVariable("id") Integer id)
	{
		return getEmpleadosService().findById(id);
	}

	@PostMapping("/{id}/modificar-jornada")
	public Empleado modificarJornadaEmpleado(@PathVariable("id") Integer idEmpleado, @RequestBody Jornada jornada)
	{
		try
		{
			return getEmpleadosService().modificarJornada(idEmpleado, jornada);
		} catch (EmpleadoNotFoundException e)
		{
			return null;
		}
	}

}
