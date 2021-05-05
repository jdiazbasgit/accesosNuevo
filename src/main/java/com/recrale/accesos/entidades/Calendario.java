package com.recrale.accesos.entidades;


import java.beans.Transient;
import java.util.GregorianCalendar;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.springframework.hateoas.ResourceSupport;
@Entity
@Table(name="calendarios")
//@Relation(collectionRelation = "estados")
public class Calendario extends ResourceSupport
{

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name="id")
	private int idCalendario;
	
	@Column
	private GregorianCalendar fecha;
	
	
	private int diaSemana;
	
	
	private int semanaMes;
	
	@ManyToOne
	//@JoinColumn(name="estado_id")
	private Status estado;
	
	
	


	

	public Status getEstado()
	{
		return estado;
	}

	public void setEstado(Status estado)
	{
		this.estado = estado;
	}
	@Transient
	public int getDiaSemana() {
		return diaSemana;
	}

	public void setDiaSemana(int diaSemana) {
		this.diaSemana = diaSemana;
	}
	@Transient
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

	public int getIdCalendario() {
		return idCalendario;
	}

	public void setIdCalendario(int idCalendario) {
		this.idCalendario = idCalendario;
	}
	
	
	
}
