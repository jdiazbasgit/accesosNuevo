package com.recrale.accesos.repositorios;



import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.recrale.accesos.entidades.Employee;

@Repository
public interface EmpleadoRepositoryInterface extends CrudRepository<Employee, Integer>
{
	
}
