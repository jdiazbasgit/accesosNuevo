package com.recrale.accesos.entidades;


import java.sql.Timestamp;
import java.util.Date;
import java.util.GregorianCalendar;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Transient;

@Entity
@Table(name="calendarios")
public class Calendario
{

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int id;
	
	@Column
	private GregorianCalendar fecha;
	@Transient
	private int diaSemana;
	
	@Transient
	private int semanaMes;
	
	@ManyToOne
	//@JoinColumn(name="estado_id")
	private Status estado;

	public int getId()
	{
		return id;
	}

	public void setId(int id)
	{
		this.id = id;
	}

	

	public Status getEstado()
	{
		return estado;
	}

	public void setEstado(Status estado)
	{
		this.estado = estado;
	}

	public int getDiaSemana() {
		return diaSemana;
	}

	public void setDiaSemana(int diaSemana) {
		this.diaSemana = diaSemana;
	}

	public int getSemanaMes() {
		return semanaMes;
	}

	public void setSemanaMes(int semanaMes) {
		this.semanaMes = semanaMes;
	}

	public GregorianCalendar getFecha() {
		return fecha;
	}

	public void setFecha(GregorianCalendar fecha) {
		this.fecha = fecha;
	}
	
	
	
}
