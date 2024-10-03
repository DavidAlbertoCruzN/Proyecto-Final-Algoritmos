package test;

import java.io.*;
import java.util.*;

public class Main {

	// Método para leer el archivo .bib y extraer las entradas
	public static List<Map<String, String>> parseBibFile(String filePath) {
		List<Map<String, String>> records = new ArrayList<>();
		try (BufferedReader br = new BufferedReader(new FileReader(filePath))) {
			String line;
			Map<String, String> currentEntry = new HashMap<>();
			while ((line = br.readLine()) != null) {
				if (line.startsWith("@")) {
					if (!currentEntry.isEmpty()) {
						records.add(currentEntry); // Añadir la entrada previa
					}
					currentEntry = new HashMap<>();
				} else if (line.contains("=")) {
					String[] parts = line.split("=", 2);
					String key = parts[0].trim();
					String value = parts[1].trim().replaceAll("[{},]", ""); // Limpiar
																			// símbolos
					currentEntry.put(key, value);
				}
			}
			if (!currentEntry.isEmpty()) {
				records.add(currentEntry); // Añadir la última entrada
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
		return records;
	}

	public static void main(String[] args) {
		// -----------------------------------------------------------------------------

		// IEEE
		String filePathIEEE = "D:/proyectoBib/combinedIEEE.bib";
		// SAGE
		String filePathSAGE = "D:/proyectoBib/combinedSAGE.bib";
		// ScienceDirect
		String filePathScienceDirect = "D:/proyectoBib/combinedScienceDirect.bib";
		// Scopus
		String filePathScopus = "D:/proyectoBib/combinedScopus.bib";
		// TaylorYFrancis
		String filePathTaylorYFrancis = "D:/proyectoBib/combinedTaylorYFrancis.bib";
		// Todo Bibliometria
		String filePathTodoBib = "D:/proyectoBib/combinedTodosBib.bib";

		// Todo Bibliometria de eliminar Archivos Repetidos
		String filePathEliminarArchivosRepetidosBib = "D:/proyectoBib/eliminarArchivosRepetidosBib.bib";

		System.out.println("------------------------------------------------------------------");
		List<Map<String, String>> recordsIEEE = parseBibFile(filePathIEEE);
		System.out.println("Total de entradas IEEE: " + recordsIEEE.size());

		List<Map<String, String>> recordsSAGE = parseBibFile(filePathSAGE);
		System.out.println("Total de entradas SAGE: " + recordsSAGE.size());

		List<Map<String, String>> recordsScienceDirect = parseBibFile(filePathScienceDirect);
		System.out.println("Total de entradas ScienceDirect: " + recordsScienceDirect.size());

		List<Map<String, String>> recordsScopus = parseBibFile(filePathScopus);
		System.out.println("Total de entradas Scopus: " + recordsScopus.size());

		List<Map<String, String>> recordsTaylorYFrancis = parseBibFile(filePathTaylorYFrancis);
		System.out.println("Total de entradas TaylorYFrancis: " + recordsTaylorYFrancis.size());

		System.out.println("------------------------------------------------------------------");
		List<Map<String, String>> recordsTodoBib = parseBibFile(filePathTodoBib);
		System.out.println("Total de entradas Todos Algoritmia y Bibliometria de .bib: " + recordsTodoBib.size());
		System.out.println("------------------------------------------------------------------");
		List<Map<String, String>> recordsEliminarArchivosRepetidosBib = parseBibFile(filePathEliminarArchivosRepetidosBib);
		System.out.println("Eliminar archivos repetidos todos Algoritmia y Bibliometria de .bib: " + recordsEliminarArchivosRepetidosBib.size());
		System.out.println("------------------------------------------------------------------");

		// Ejemplo de cómo acceder a la información
		//for (Map<String, String> entry : recordsTodoBib) {
		// System.out.println("Título: " + entry.get("title"));
		// System.out.println("Autor: " + entry.get("author"));
		// System.out.println("Año: " + entry.get("year"));
		// System.out.println("-----------------------------");
		//}

	}
}

