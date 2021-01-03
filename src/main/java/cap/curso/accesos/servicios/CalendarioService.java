package cap.curso.accesos.servicios;

import java.sql.Date;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.Iterator;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cap.curso.accesos.entidades.Calendario;
import cap.curso.accesos.entidades.Estado;
import cap.curso.accesos.exception.CalendarioAlreadyExistsException;
import cap.curso.accesos.exception.CalendarioNotFoundException;
import cap.curso.accesos.exception.EstadoNotFoundException;
import cap.curso.accesos.repositorios.CalendarioRepository;
import cap.curso.accesos.repositorios.EstadosRepository;

@Service
public class CalendarioService implements CalendarioServiceInterface
{

	@Autowired
	private CalendarioRepository calendarioRepository;

	@Autowired
	private EstadosRepository estadosRepository;

	public CalendarioRepository getCalendarioRepository()
	{
		return calendarioRepository;
	}

	public void setCalendarioRepository(CalendarioRepository calendarioRepository)
	{
		this.calendarioRepository = calendarioRepository;
	}

	public EstadosRepository getEstadosRepository()
	{
		return estadosRepository;
	}

	public void setEstadosRepository(EstadosRepository estadosRepository)
	{
		this.estadosRepository = estadosRepository;
	}

	public Iterable<Calendario> findAll()
	{
		return getCalendarioRepository().findAll();
	}
	
	public Calendario findById(Integer idCalendario) {
		return getCalendarioRepository().findById(idCalendario).orElse(null);
	}

	public Calendario save(Calendario calendario) throws CalendarioAlreadyExistsException
	{
		Calendario calendarioSearched = getCalendarioRepository().findById(calendario.getId()).orElse(null);

		if (calendarioSearched == null)
		{
			return getCalendarioRepository().save(calendario);
		} else
		{
			throw new CalendarioAlreadyExistsException("El calendario ya existe en la bd existe");
		}
	}

	@Override
	public Iterable<Calendario> generaCalendarioAnyo(Integer anyo)
			throws CalendarioAlreadyExistsException, EstadoNotFoundException
	{
		Iterable<Calendario> diasAnyo = getCalendarioRepository().findByAnyo(anyo.toString());

		// System.out.println("-----> HE BUSCADO POR AÑO");

		Iterator<Calendario> it = diasAnyo.iterator();
		if (it.hasNext()) // si la lista de dias no esta vacia, no se genera calendario
		{
			throw new CalendarioAlreadyExistsException("El calendario ya existe en la bd");
		}

		// System.out.println("-----> HE ENCONTRADO POR AÑO");

		Estado laborable = getEstadosRepository().findByDescripcion("Laborable");
		Estado festivo = getEstadosRepository().findByDescripcion("Festivo");

		// System.out.println("-----> HE BUSCADO LOS ESTADOS");

		if (laborable == null)
		{
			throw new EstadoNotFoundException("El estado Laborable no existe");
		} else if (festivo == null)
		{
			throw new EstadoNotFoundException("El estado Festivo no existe");
		}

		// System.out.println("-----> HE ENCONTRADO LOS ESTADOS");

		List<Calendario> listaDiasAnyo = new ArrayList<Calendario>();
		GregorianCalendar calendario = new GregorianCalendar(anyo, 0, 1); // instanciamos el año
		int daysInYear = calendario.getActualMaximum(Calendar.DAY_OF_YEAR);

		for (int i = 1; i <= daysInYear; ++i)
		{
			Calendario fila = new Calendario();
			Date fecha = new Date(calendario.getTime().getTime());
			fila.setFecha(fecha);

			int dayOfWeek = calendario.get(Calendar.DAY_OF_WEEK);
			if (dayOfWeek > 1 && dayOfWeek <= 6) // si es esntre semana
			{
				// System.out.println(calendario.getTime().toString() + " - DIARIO");
				fila.setEstado(laborable);

			} else // fin de semana
			{
				// System.out.println(calendario.getTime().toString() + " - FINDE");
				fila.setEstado(festivo);
			}

			Calendario insertado = getCalendarioRepository().save(fila);

			// System.out.println("-----> HE INSERTADO EL DIA");

			listaDiasAnyo.add(insertado);

			calendario.add(Calendar.DAY_OF_YEAR, 1); // avanzamos al dia siguiente
		}

		return listaDiasAnyo;
	}

	public Calendario updateCalendarioEstado(Integer idCalendario, Estado estado) throws CalendarioNotFoundException
	{
		// el metodo get() devuelve la excepcion NoSuchElementException y nosotros
		// queremos devolver null
		// Calendario calendarioSearched =
		// getCalendarioRepository().findById(idCalendario).get();
		Calendario calendarioSearched = getCalendarioRepository().findById(idCalendario).orElse(null);

		if (calendarioSearched != null)
		{
			calendarioSearched.setEstado(estado);
			return getCalendarioRepository().save(calendarioSearched);
		} else
		{
			throw new CalendarioNotFoundException("El calendario no existe");
		}
	}

	public Iterable<Calendario> findByAnyo(Integer anyo) throws CalendarioNotFoundException
	{
		Iterable<Calendario> diasAnyo = getCalendarioRepository().findByAnyo(anyo.toString());

		Iterator<Calendario> it = diasAnyo.iterator();
		if (!it.hasNext()) // si la lista de dias no esta vacia, no se genera calendario
		{
			throw new CalendarioNotFoundException("El calendario no existe en la bd");
		}

		return diasAnyo;
	}

}
