package com.recrale.accesos.repositorios;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.recrale.accesos.entidades.Jornada;

@Repository
public interface JornadaRepository extends CrudRepository<Jornada, Integer>
{

}
