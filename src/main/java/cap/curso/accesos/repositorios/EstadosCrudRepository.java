package cap.curso.accesos.repositorios;

import org.springframework.data.repository.CrudRepository;

import cap.curso.accesos.entidades.Status;


public interface EstadosCrudRepository extends CrudRepository<Status, Integer> {

}
