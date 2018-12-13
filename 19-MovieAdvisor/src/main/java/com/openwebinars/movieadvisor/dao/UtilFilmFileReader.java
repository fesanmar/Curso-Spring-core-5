package com.openwebinars.movieadvisor.dao;

import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.util.ResourceUtils;

import com.openwebinars.movieadvisor.model.Film;

/**
 * Clase de utilidad, que incluye un método estático para la lectura
 * y procesamiento del fichero CSV que incluye todos los datos.
 * 
 * PROPUESTA: ¿Serías capaz de cambiar el código necesario para
 * que fuera un bean?
 * 
 * 
 * 
 * @author OpenWebinars
 *
 */
public class UtilFilmFileReader {

	public static List<Film> readFile(final String path, final String separator, final String listSeparator) {
		List<Film> result = new ArrayList<>();


		try {
			// @formatter:off
			result = Files
						.lines(Paths.get(ResourceUtils.getFile(path).toURI()))
						.skip(1)
						.map(line -> {
							String[] values = line.split(separator);
							return new Film(Long.parseLong(values[0]), values[1], values[2],
											Arrays.asList(values[3].split(listSeparator)));
					}).collect(Collectors.toList());
 			// @formatter:on


		} catch (Exception e) {
			System.err.println("Error de lectura del fichero de datos: imdb_data");
			System.exit(-1);
		}

		return result;

	}

}
