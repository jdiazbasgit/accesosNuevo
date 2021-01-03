package com.recrale.accesos.repositorios;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.recrale.accesos.entidades.Calendario;
import com.recrale.accesos.entidades.Empleado;
import com.recrale.accesos.entidades.EmpleadoEstado;

@Repository
public interface EmpleadosEstadosRepository extends CrudRepository<EmpleadoEstado, Integer>
{
	
}
