package com.recrale.accesos.repositorios;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.recrale.accesos.entidades.Calendario;

@Repository
public interface CalendarioRepository extends CrudRepository<Calendario, Integer>
{

	@Query("from Calendario c order by c.fecha")
	List<Calendario> getCalendarioOrdenado();

	
	
}
