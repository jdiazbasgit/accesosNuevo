package cap.curso.accesos.entidades;


import java.sql.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name="accesos")
public class Access
{

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int id;
	
	@Column
	private int year;
	
	@Column
	private int month;
	
	@Column
	private int day;
	
	@Column
	private int minuto;
	
	@Column
	private int hora;
	
	@Column
	private int tipo;
	
	@Column
	private Date fecha;
	
	@Column
	private int horaReal;
	
	@Column
	private int minutoReal;
	
	@ManyToOne
	@JoinColumn(name="empleados_id")
	private Empleado empleado;

	public int getId()
	{
		return id;
	}

	public void setId(int id)
	{
		this.id = id;
	}

	public int getYear()
	{
		return year;
	}

	public void setYear(int year)
	{
		this.year = year;
	}

	public int getMonth()
	{
		return month;
	}

	public void setMonth(int month)
	{
		this.month = month;
	}

	public int getDay()
	{
		return day;
	}

	public void setDay(int day)
	{
		this.day = day;
	}

	public int getMinuto()
	{
		return minuto;
	}

	public void setMinuto(int minuto)
	{
		this.minuto = minuto;
	}

	public int getHora()
	{
		return hora;
	}

	public void setHora(int hora)
	{
		this.hora = hora;
	}

	public int getTipo()
	{
		return tipo;
	}

	public void setTipo(int tipo)
	{
		this.tipo = tipo;
	}

	public Date getFecha()
	{
		return fecha;
	}

	public void setFecha(Date fecha)
	{
		this.fecha = fecha;
	}

	public int getHoraReal()
	{
		return horaReal;
	}

	public void setHoraReal(int horaReal)
	{
		this.horaReal = horaReal;
	}

	public int getMinutoReal()
	{
		return minutoReal;
	}

	public void setMinutoReal(int minutoReal)
	{
		this.minutoReal = minutoReal;
	}

	public Empleado getEmpleado()
	{
		return empleado;
	}

	public void setEmpleado(Empleado empleado)
	{
		this.empleado = empleado;
	}
	
	
	
}
