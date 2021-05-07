package cap.curso.accesos.repositorios;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import cap.curso.accesos.entidades.Status;


@Repository
public interface EstadosRepository extends CrudRepository<Status, Integer>
{


}
