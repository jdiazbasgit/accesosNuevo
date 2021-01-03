package com.recrale.accesos.repositorios;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.recrale.accesos.entidades.Status;

@Repository
public interface EstadosRepository extends CrudRepository<Status, Integer>
{


}
