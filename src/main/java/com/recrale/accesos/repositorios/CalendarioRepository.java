package com.recrale.accesos.repositorios;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.recrale.accesos.entidades.Calendario;

@Repository
public interface CalendarioRepository extends CrudRepository<Calendario, Integer>
{

	
}
