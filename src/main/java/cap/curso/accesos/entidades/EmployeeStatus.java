package cap.curso.accesos.entidades;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
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
	private Empleado empleado;
	
	@ManyToOne
	private Status estado;
	
	@ManyToOne
	private Calendario calendario;
	
	@ManyToOne
	private Jornada jornada;
 
	public int getId()
	{
		return id;
	}

	public void setId(int id)
	{
		this.id = id;
	}

	public Empleado getEmpleado()
	{
		return empleado;
	}

	public void setEmpleado(Empleado empleado)
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

	public Calendario getCalendario()
	{
		return calendario;
	}

	public void setCalendario(Calendario calendario)
	{
		this.calendario = calendario;
	}

	public Jornada getJornada()
	{
		return jornada;
	}

	public void setJornada(Jornada jornada)
	{
		this.jornada = jornada;
	}
	
	
	
}
