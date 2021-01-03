package com.recrale.accesos.repositorios;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.recrale.accesos.entidades.Calendar;
import com.recrale.accesos.entidades.Employee;
import com.recrale.accesos.entidades.EmployeeStatus;

@Repository
public interface EmpleadosEstadosRepository extends CrudRepository<EmployeeStatus, Integer>
{
	
}
