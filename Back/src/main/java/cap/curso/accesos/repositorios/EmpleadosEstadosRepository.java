package cap.curso.accesos.repositorios;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import cap.curso.accesos.entidades.Calendario;
import cap.curso.accesos.entidades.Empleado;
import cap.curso.accesos.entidades.EmpleadoEstado;

@Repository
public interface EmpleadosEstadosRepository extends CrudRepository<EmpleadoEstado, Integer>
{
	@Query("from EmpleadoEstado ue where ue.empleado=:empleado and ue.calendario=:calendario")
	public EmpleadoEstado getUsuarioEstadoByEmpleado(Empleado empleado, Calendario calendario);
}
