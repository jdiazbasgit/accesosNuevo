package com.recrale.accesos.entidades;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name="empleados_estados")
public class EmployeeStatus
{
 
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int id;
	
	@ManyToOne
	@JoinColumn(name="empleados_id")
	private Employee empleado;
	
	@ManyToOne
	@JoinColumn(name="estados_id")
	private Status estado;
	
	@ManyToOne
	@JoinColumn(name="calendarios_id")
	private Calendar calendario;
	
	@ManyToOne
	@JoinColumn(name="jornadas_id")
	private WorkingDay jornada;
 
	public int getId()
	{
		return id;
	}

	public void setId(int id)
	{
		this.id = id;
	}

	public Employee getEmpleado()
	{
		return empleado;
	}

	public void setEmpleado(Employee empleado)
	{
		this.empleado = empleado;
	}

	public Status getEstado()
	{
		return estado;
	}

	public void setEstado(Status estado)
	{
		this.estado = estado;
	}

	public Calendar getCalendario()
	{
		return calendario;
	}

	public void setCalendario(Calendar calendario)
	{
		this.calendario = calendario;
	}

	public WorkingDay getJornada()
	{
		return jornada;
	}

	public void setJornada(WorkingDay jornada)
	{
		this.jornada = jornada;
	}
	
	
	
}
