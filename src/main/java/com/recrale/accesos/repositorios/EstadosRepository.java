package com.recrale.accesos.repositorios;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.recrale.accesos.entidades.Status;

@Repository
public interface EstadosRepository extends CrudRepository<Status, Integer>
{


}
