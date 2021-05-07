package cap.curso.accesos.repositorios;

import java.util.Calendar;
import java.util.List;
import java.util.stream.Stream;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import cap.curso.accesos.entidades.Calendario;


@Repository
public interface CalendarioRepository extends CrudRepository<Calendario, Integer> {

	 @Query("from Calendario c order by c.fecha")
	List<Calendario> getCalendarioOrdenado();

	// @Query("select c.fecha,e.descripcion from Calendario c,"
	// + "Status e where c.estado=e and c.estado.id=:tipo")

	// @Query(" from Calendario c, Status e")
	@Query(value = "SELECT * FROM CALENDARIOS C, ESTADOS E WHERE C.ESTADO_ID=E.ID",
			nativeQuery = true)
	Object[][] getFechaAndEstado(int tipo);

	default List<Calendario> getCalendarioByYear(int year) {
		List<Calendario> calendarios=getCalendarioOrdenado();
		for (Calendario calendario : calendarios) {
			calendario.setDiaSemana(calendario.getFecha().get(Calendar.DAY_OF_WEEK));
			calendario.setSemanaMes(calendario.getFecha().get(Calendar.WEEK_OF_MONTH));
		}

		return calendarios;
	}

	default Stream<Calendario> getCalendarioByMonth(int year, int mes) {

		return ((List<Calendario>) getCalendarioOrdenado()).stream()
				.filter((c) -> c.getFecha().get(Calendar.YEAR) == year).
				filter((c)->c.getFecha().get(Calendar.MONTH)==mes-1);
	}
	
	default Stream<Calendario> getCalendarioByMonthAndWeek(int year, int mes, int semana) {

		return ((List<Calendario>) getCalendarioOrdenado()).stream()
				.filter((c) -> c.getFecha().get(Calendar.YEAR) == year).
				filter((c)->c.getFecha().get(Calendar.MONTH)==mes-1).
				filter((c)->c.getFecha().get(Calendar.WEEK_OF_MONTH)==semana);
	}

}
