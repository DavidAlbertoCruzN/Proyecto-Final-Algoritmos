package test;

import java.io.*;
import java.util.*;

public class BibCleaner {

    public static void main(String[] args) throws IOException {
        String inputFilePath = "D:/proyectoBib/combinedTodosBib.bib";
        String outputFilePath = "D:/proyectoBib/eliminarArchivosRepetidosBib.bib";

        // Mapa para almacenar las entradas bibtex sin duplicados
        Map<String, String> uniqueEntries = new LinkedHashMap<>();

        try (BufferedReader reader = new BufferedReader(new FileReader(inputFilePath))) {
            StringBuilder currentEntry = new StringBuilder();
            String line;
            String entryKey = null;

            while ((line = reader.readLine()) != null) {
                if (line.trim().isEmpty()) {
                    // Si se encuentra una línea vacía, esto indica el final de una entrada
                    if (entryKey != null && !uniqueEntries.containsKey(entryKey)) {
                        uniqueEntries.put(entryKey, currentEntry.toString());
                    }
                    currentEntry.setLength(0);
                    entryKey = null;
                } else {
                    // Captura la clave del registro (Ejemplo: 5673139)
                    if (line.startsWith("@") && line.contains("{")) {
                        entryKey = line.substring(line.indexOf("{") + 1, line.indexOf(","));
                    }
                    currentEntry.append(line).append("\n");
                }
            }

            // Asegurarse de agregar la última entrada si el archivo no termina con una línea vacía
            if (entryKey != null && !uniqueEntries.containsKey(entryKey)) {
                uniqueEntries.put(entryKey, currentEntry.toString());
            }
        }

        // Escribir las entradas únicas en un archivo nuevo
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(outputFilePath))) {
            for (String entry : uniqueEntries.values()) {
                writer.write(entry);
                writer.write("\n");
            }
        }

        System.out.println("Eliminar archivos repetidos Bibliometria en: " + outputFilePath);
    }
}
