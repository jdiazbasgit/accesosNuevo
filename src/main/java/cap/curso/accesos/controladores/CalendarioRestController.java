package cap.curso.accesos.controladores;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import cap.curso.accesos.DTOs.AnyoRequestDto;
import cap.curso.accesos.entidades.Calendario;
import cap.curso.accesos.entidades.Estado;
import cap.curso.accesos.exception.CalendarioAlreadyExistsException;
import cap.curso.accesos.exception.CalendarioNotFoundException;
import cap.curso.accesos.exception.EstadoNotFoundException;
import cap.curso.accesos.servicios.CalendarioService;
import cap.curso.accesos.servicios.CalendarioServiceInterface;

@RestController
@RequestMapping("/api/calendario")
public class CalendarioRestController
{
	@Autowired
	private CalendarioServiceInterface calendarioService;

	public CalendarioServiceInterface getCalendarioService()
	{
		return calendarioService;
	}

	public void setCalendarioService(CalendarioService calendarioService)
	{
		this.calendarioService = calendarioService;
	}

	@GetMapping("/")
	public Iterable<Calendario> findAll()
	{
		return getCalendarioService().findAll();
	}

	@GetMapping("/all/{anyo}")
	public Iterable<Calendario> findByAnyo(@PathVariable("anyo") Integer anyo)
	{
		try
		{
			return getCalendarioService().findByAnyo(anyo);
		} catch (CalendarioNotFoundException e)
		{
			return new ArrayList<Calendario>();
		}
	}

	@PostMapping("/generar")
	public Iterable<Calendario> generaCalendario(@RequestBody(required = true) AnyoRequestDto anyo)
	{
		try
		{
			return getCalendarioService().generaCalendarioAnyo(anyo.getAnyo());
		} catch (CalendarioAlreadyExistsException | EstadoNotFoundException e)
		{
			return new ArrayList<Calendario>();
		}
	}

	@PostMapping("/actualizar/{dia_id}")
	public Calendario actualizarDiaEnCalendario(@PathVariable("dia_id") int dia_id, @RequestBody Estado estado)
	{
		try
		{
			return getCalendarioService().updateCalendarioEstado(dia_id, estado);
		} catch (CalendarioNotFoundException e)
		{
			return null;
		}
	}

	@GetMapping("/{id}")
	public Calendario getCalendario(@PathVariable("id") Integer idCalendario)
	{
		return getCalendarioService().findById(idCalendario);
	}

}
