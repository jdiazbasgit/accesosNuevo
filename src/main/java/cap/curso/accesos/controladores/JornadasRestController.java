package cap.curso.accesos.controladores;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import cap.curso.accesos.entidades.Jornada;
import cap.curso.accesos.servicios.JornadasServiceInterface;

@RestController
@RequestMapping("/api/jornadas")
public class JornadasRestController
{

	@Autowired
	private JornadasServiceInterface jornadasService;

	public JornadasServiceInterface getJornadasService()
	{
		return jornadasService;
	}

	public void setJornadasService(JornadasServiceInterface jornadasService)
	{
		this.jornadasService = jornadasService;
	}
	
	@GetMapping("/")
	public Iterable<Jornada> findAll()
	{
		return getJornadasService().findAll();
	}

	@GetMapping("/{id}")
	public Jornada findById(@PathVariable Integer id)
	{
		return getJornadasService().findById(id);
	}
	
	@PostMapping("/nueva-jornada")
	public Jornada nuevaJornada(@RequestBody Jornada jornada)
	{
		return getJornadasService().save(jornada);
	}

}
