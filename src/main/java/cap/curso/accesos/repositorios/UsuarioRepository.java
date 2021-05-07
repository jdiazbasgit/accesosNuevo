package cap.curso.accesos.repositorios;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import cap.curso.accesos.entidades.Usuario;


public interface UsuarioRepository extends CrudRepository<Usuario, Integer> {

	@Query("from Usuario u where u.user=:user")
	public Usuario findUserByUser(String user);

}
