package cap.curso.accesos.controladores;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import cap.curso.accesos.DTOs.RegistroDto;
import cap.curso.accesos.entidades.EmpleadoEstado;
import cap.curso.accesos.servicios.UsuariosEstadoServiceInterface;

@RestController
@RequestMapping("/api/usuarios-estados")
public class UsuariosEstadosRestController
{

	@Autowired
	private UsuariosEstadoServiceInterface usuariosEstadosService;
	

	public UsuariosEstadoServiceInterface getUsuariosEstadosService()
	{
		return usuariosEstadosService;
	}

	public void setUsuariosEstadosService(UsuariosEstadoServiceInterface usuariosEstadosService)
	{
		this.usuariosEstadosService = usuariosEstadosService;
	}

	@GetMapping("/")
	public Iterable<EmpleadoEstado> getCuadrante()
	{
		return getUsuariosEstadosService().findAll();
	}

	@GetMapping("/{id}")
	public EmpleadoEstado getCalendarioEmpleadoById(@PathVariable("id") Integer id)
	{
		return getUsuariosEstadosService().findById(id);
	}

	@PostMapping("/guardar-registro")
	public EmpleadoEstado guardar(@RequestBody RegistroDto registro)
	{
		return getUsuariosEstadosService().save(registro);
	}

	@PostMapping("/borrar-registro")
	public void delete(@RequestBody EmpleadoEstado ue)
	{
		getUsuariosEstadosService().delete(ue);
	}

}
