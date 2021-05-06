package cap.curso.accesos.entidades;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name="empleados_estados")
public class EmpleadoEstado
{
 
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int id;
	
	@ManyToOne
	@JoinColumn(name="empleados_id")
	private Empleado empleado;
	
	@ManyToOne
	@JoinColumn(name="estados_id")
	private Estado estado;
	
	@ManyToOne
	@JoinColumn(name="calendarios_id")
	private Calendario calendario;
	
	@ManyToOne
	@JoinColumn(name="jornadas_id")
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

	public Estado getEstado()
	{
		return estado;
	}

	public void setEstado(Estado estado)
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
