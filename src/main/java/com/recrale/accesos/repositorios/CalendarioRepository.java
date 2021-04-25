package com.recrale.accesos.repositorios;

import java.time.LocalDate;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.List;
import java.util.stream.Stream;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.recrale.accesos.entidades.Calendario;

@Repository
public interface CalendarioRepository extends CrudRepository<Calendario, Integer> {

	@Query("from Calendario c order by c.fecha")
	List<Calendario> getCalendarioOrdenado();

	default Stream<Calendario> getCalendarioByYear(int year) {
		List<Calendario> calendario = (List<Calendario>) findAll();
		for (Calendario dia : calendario) {

			GregorianCalendar fecha = dia.getFecha();
			//fecha.setTimeInMillis(dia.getFecha().getTime());
			int diaSemana=0;
			int semanaMes =0;
			int mes=0;
			if (fecha.get(Calendar.YEAR) == year) {
				diaSemana = fecha.get(Calendar.DAY_OF_WEEK);
				if (diaSemana > 1) {
					diaSemana -= 1;
				} else {
					diaSemana = 7;
				}
				dia.setDiaSemana(diaSemana);

				semanaMes = fecha.get(Calendar.WEEK_OF_MONTH);

				if (semanaMes == 0) {
					mes = fecha.get(Calendar.MONTH);
				}

				if (mes == fecha.get(Calendar.MONTH))
					semanaMes += 1;

				// dia.setFecha(fecha.getGregorianChange());
				dia.setSemanaMes(semanaMes);
			}

		}
		//return calendario.stream().filter(p -> p.getFecha().getYear() + 1900 == year);
		return calendario.stream().filter(p->p.getFecha().get(Calendar.YEAR)==year);
	}

}
