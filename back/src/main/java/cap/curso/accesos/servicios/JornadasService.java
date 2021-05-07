package cap.curso.accesos.servicios;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cap.curso.accesos.entidades.Jornada;
import cap.curso.accesos.repositorios.JornadaRepository;

@Service
public class JornadasService implements JornadasServiceInterface
{
	@Autowired
	JornadaRepository jornadaRepositoryInterface;

	public JornadaRepository getJornadaRepositoryInterface()
	{
		return jornadaRepositoryInterface;
	}

	public void setJornadaRepositoryInterface(JornadaRepository jornadaRepositoryInterface)
	{
		this.jornadaRepositoryInterface = jornadaRepositoryInterface;
	}

	public Jornada save(Jornada jornada)
	{
		return getJornadaRepositoryInterface().save(jornada);
	}

	public Iterable<Jornada> findAll()
	{
		return getJornadaRepositoryInterface().findAll();
	}

	public Jornada findById(Integer id)
	{
		return getJornadaRepositoryInterface().findById(id).orElse(null);
	}

}
