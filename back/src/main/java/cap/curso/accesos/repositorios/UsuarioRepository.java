package cap.curso.accesos.repositorios;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import cap.curso.accesos.entidades.User;

public interface UsuarioRepository extends CrudRepository<User, Integer> {

	@Query("from User u where u.user=:username")
	public User findByUserName(String username);

}
