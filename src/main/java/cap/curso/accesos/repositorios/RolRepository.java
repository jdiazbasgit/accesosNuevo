package cap.curso.accesos.repositorios;

import org.springframework.data.repository.CrudRepository;

import cap.curso.accesos.entidades.Role;


public interface RolRepository extends CrudRepository<Role, Integer> {

}
