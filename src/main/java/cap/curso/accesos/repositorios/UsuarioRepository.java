package cap.curso.accesos.repositorios;

import org.springframework.data.repository.CrudRepository;

import cap.curso.accesos.entidades.Usuario;

public interface UsuarioRepository extends CrudRepository<Usuario, Integer> {

}
