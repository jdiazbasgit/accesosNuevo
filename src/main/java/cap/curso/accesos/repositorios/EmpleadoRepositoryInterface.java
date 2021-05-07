package cap.curso.accesos.repositorios;



import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import cap.curso.accesos.entidades.Empleado;


@Repository
public interface EmpleadoRepositoryInterface extends CrudRepository<Empleado, Integer>
{
	
}
