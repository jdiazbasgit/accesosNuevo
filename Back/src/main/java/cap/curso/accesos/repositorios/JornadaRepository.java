package cap.curso.accesos.repositorios;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import cap.curso.accesos.entidades.Jornada;

@Repository
public interface JornadaRepository extends CrudRepository<Jornada, Integer>{}