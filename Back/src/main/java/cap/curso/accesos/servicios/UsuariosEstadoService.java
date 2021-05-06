package cap.curso.accesos.servicios;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cap.curso.accesos.DTOs.RegistroDto;
import cap.curso.accesos.entidades.Calendario;
import cap.curso.accesos.entidades.Empleado;
import cap.curso.accesos.entidades.Estado;
import cap.curso.accesos.entidades.Jornada;
import cap.curso.accesos.entidades.EmpleadoEstado;
import cap.curso.accesos.repositorios.CalendarioRepository;
import cap.curso.accesos.repositorios.EmpleadoRepositoryInterface;
import cap.curso.accesos.repositorios.EstadosRepository;
import cap.curso.accesos.repositorios.JornadaRepository;
import cap.curso.accesos.repositorios.EmpleadosEstadosRepository;

@Service
public class UsuariosEstadoService implements UsuariosEstadoServiceInterface
{

	@Autowired
	private EmpleadosEstadosRepository usuariosEstadosRepository;

	@Autowired
	private EmpleadoRepositoryInterface empleadosRepository;

	@Autowired
	private JornadaRepository jornadaRepository;

	@Autowired
	private EstadosRepository estadoRepository;

	@Autowired
	private CalendarioRepository calendarioRepository;

	public EmpleadosEstadosRepository getUsuariosEstadosRepository()
	{
		return usuariosEstadosRepository;
	}

	public void setUsuariosEstadosRepository(EmpleadosEstadosRepository usuariosEstadosRepository)
	{
		this.usuariosEstadosRepository = usuariosEstadosRepository;
	}

	public EmpleadoRepositoryInterface getEmpleadosRepository()
	{
		return empleadosRepository;
	}

	public void setEmpleadosRepository(EmpleadoRepositoryInterface empleadosRepository)
	{
		this.empleadosRepository = empleadosRepository;
	}

	public JornadaRepository getJornadaRepository()
	{
		return jornadaRepository;
	}

	public void setJornadaRepository(JornadaRepository jornadaRepository)
	{
		this.jornadaRepository = jornadaRepository;
	}

	public EstadosRepository getEstadoRepository()
	{
		return estadoRepository;
	}

	public void setEstadoRepository(EstadosRepository estadoRepository)
	{
		this.estadoRepository = estadoRepository;
	}

	public CalendarioRepository getCalendarioRepository()
	{
		return calendarioRepository;
	}

	public void setCalendarioRepository(CalendarioRepository calendarioRepository)
	{
		this.calendarioRepository = calendarioRepository;
	}

	public Iterable<EmpleadoEstado> findAll()
	{
		return getUsuariosEstadosRepository().findAll();
	}

	public EmpleadoEstado findById(Integer id)
	{
		return getUsuariosEstadosRepository().findById(id).orElse(null);
	}

	public EmpleadoEstado save(RegistroDto registro)
	{
		Empleado emp = getEmpleadosRepository().findById(registro.getIdEmpleado()).orElse(null);
		Jornada jor = getJornadaRepository().findById(registro.getIdJornada()).orElse(null);
		Estado est = getEstadoRepository().findById(registro.getIdEstado()).orElse(null);
		Calendario cal = getCalendarioRepository().findById(registro.getIdCalendario()).orElse(null);

		EmpleadoEstado ue = getUsuariosEstadosRepository().getUsuarioEstadoByEmpleado(emp, cal);
		if (ue == null)
		{ // no existe en la bd
			EmpleadoEstado nuevoRegistro = new EmpleadoEstado();
			nuevoRegistro.setEmpleado(emp);
			nuevoRegistro.setJornada(jor);
			nuevoRegistro.setEstado(est);
			nuevoRegistro.setCalendario(cal);
			return getUsuariosEstadosRepository().save(nuevoRegistro);
		} else
		{
			ue.setEstado(est);
			ue.setJornada(jor);
			return getUsuariosEstadosRepository().save(ue);
		}

	}

	public EmpleadoEstado save(EmpleadoEstado ue)
	{
		return getUsuariosEstadosRepository().save(ue);
	}

	public void delete(EmpleadoEstado ue)
	{
		getUsuariosEstadosRepository().delete(ue);
	}

	public EmpleadoEstado getDiasTrabajadosEmpleadoByYearByMes(Empleado empleado, Calendario calendario)
	{
		return getUsuariosEstadosRepository().getUsuarioEstadoByEmpleado(empleado, calendario);
	}

}
