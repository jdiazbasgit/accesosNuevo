package cap.curso.accesos.servicios;

import java.util.List;

import org.springframework.stereotype.Service;

import cap.curso.accesos.entidades.Estado;

@Service
public interface EstadosServiceInterface
{
	public Estado findByDescripcion(String descripcion);

	public List<Estado> findAll();

	public Estado save(Estado estado);
}
