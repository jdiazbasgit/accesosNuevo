package cap.curso.accesos.servicios;

import org.springframework.stereotype.Service;

import cap.curso.accesos.entidades.Jornada;

@Service
public interface JornadasServiceInterface
{
	
	public Jornada save(Jornada jornada);
	
	public Iterable<Jornada> findAll();
	
	public Jornada findById(Integer id);
}
