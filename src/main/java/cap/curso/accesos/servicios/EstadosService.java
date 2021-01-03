package cap.curso.accesos.servicios;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cap.curso.accesos.entidades.Estado;
import cap.curso.accesos.repositorios.EstadosRepository;

@Service
public class EstadosService implements EstadosServiceInterface
{

	@Autowired
	private EstadosRepository estadosRepository;

	public EstadosRepository getEstadosRepository()
	{
		return estadosRepository;
	}

	public void setEstadosRepository(EstadosRepository estadosRepository)
	{
		this.estadosRepository = estadosRepository;
	}

	public Estado findByDescripcion(String descripcion)
	{
		return getEstadosRepository().findByDescripcion(descripcion);
	}

	public List<Estado> findAll()
	{
		return (List<Estado>) getEstadosRepository().findAll();
	}

	public Estado save(Estado estado)
	{
		return getEstadosRepository().save(estado);
	}

}
