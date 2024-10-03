package co.edu.uniquindio.bibliometria.controllers;

import co.edu.uniquindio.bibliometria.persistencia.BibFileHandler;

import java.util.List;
import java.util.Map;

public class BibParserController {

	public void run() {
		// Rutas de archivos .bib
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

		BibFileHandler fileHandler = new BibFileHandler();

		System.out.println("------------------------------------------------------------------");
		System.out.println("--------- Proyecto del curso: Algoritmia y bibliometría ----------");
		System.out.println("------------------------------------------------------------------");
		List<Map<String, String>> recordsIEEE = fileHandler.parseBibFile(filePathIEEE);
		System.out.println("Total de entradas IEEE: " + recordsIEEE.size());
		List<Map<String, String>> recordsSAGE = fileHandler.parseBibFile(filePathSAGE);
		System.out.println("Total de entradas SAGE: " + recordsSAGE.size());
		List<Map<String, String>> recordsScienceDirect = fileHandler.parseBibFile(filePathScienceDirect);
		System.out.println("Total de entradas ScienceDirect: " + recordsScienceDirect.size());
		List<Map<String, String>> recordsScopus = fileHandler.parseBibFile(filePathScopus);
		System.out.println("Total de entradas Scopus: " + recordsScopus.size());
		List<Map<String, String>> recordsTaylorYFrancis = fileHandler.parseBibFile(filePathTaylorYFrancis);
		System.out.println("Total de entradas TaylorYFrancis: " + recordsTaylorYFrancis.size());

		System.out.println("------------------------------------------------------------------");
		List<Map<String, String>> recordsTodoBib = fileHandler.parseBibFile(filePathTodoBib);
		System.out.println("Total de entradas Todos Algoritmia y Bibliometria de .bib: " + recordsTodoBib.size());
		System.out.println("------------------------------------------------------------------");
		List<Map<String, String>> recordsEliminarArchivosRepetidosBib = fileHandler.parseBibFile(filePathEliminarArchivosRepetidosBib);
		System.out.println("Eliminar archivos repetidos con el mismo nombre todos bibliometria de .bib: " + recordsEliminarArchivosRepetidosBib.size());
		System.out.println("------------------------------------------------------------------");

	}
}
