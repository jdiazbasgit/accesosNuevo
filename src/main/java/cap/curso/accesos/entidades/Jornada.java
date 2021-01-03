package cap.curso.accesos.entidades;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "jornadas")
public class Jornada
{

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int id;

	@Column
	private String lunes;

	@Column
	private String martes;

	@Column
	private String miercoles;

	@Column
	private String jueves;
	
	@Column
	private String viernes;

	@Column
	private String descripcion;

	@Column
	private int especial;

	


	public int getId()
	{
		return id;
	}

	public void setId(int id)
	{
		this.id = id;
	}

	public String getLunes()
	{
		return lunes;
	}

	public void setLunes(String lunes)
	{
		this.lunes = lunes;
	}

	public String getMartes()
	{
		return martes;
	}

	public void setMartes(String martes)
	{
		this.martes = martes;
	}

	public String getMiercoles()
	{
		return miercoles;
	}

	public void setMiercoles(String miercoles)
	{
		this.miercoles = miercoles;
	}

	public String getJueves()
	{
		return jueves;
	}

	public void setJueves(String jueves)
	{
		this.jueves = jueves;
	}
	
	public String getViernes()
	{
		return viernes;
	}

	public void setViernes(String viernes)
	{
		this.viernes = viernes;
	}

	public String getDescripcion()
	{
		return descripcion;
	}

	public void setDescripcion(String descripcion)
	{
		this.descripcion = descripcion;
	}

	public int getEspecial()
	{
		return especial;
	}

	public void setEspecial(int especial)
	{
		this.especial = especial;
	}

}
