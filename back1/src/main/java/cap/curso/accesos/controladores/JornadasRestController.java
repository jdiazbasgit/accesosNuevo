package cap.curso.accesos.controladores;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
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
	@CrossOrigin(origins = "*")
	public List<Jornada> findAll()
	{
		return (List<Jornada>) getJornadasService().findAll();
	}

	@GetMapping("/{id}")
	@CrossOrigin(origins = "*")
	public Jornada findById(@PathVariable Integer id)
	{
		return getJornadasService().findById(id);
	}
	
	@PostMapping("/")
	@CrossOrigin(origins = "*")
	public Jornada nuevaJornada(@RequestBody Jornada jornada)
	{
		return getJornadasService().save(jornada);
	}

}
