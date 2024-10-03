package co.edu.uniquindio.bibliometria.persistencia;

import co.edu.uniquindio.bibliometria.exceptions.BibTeXMergerException;

import java.io.*;
import java.nio.file.*;
import java.util.*;
import java.util.stream.Collectors;

public class BibFileHandler {

    public void combineBibFiles(String sourceDirectory, String outputFilePath) throws BibTeXMergerException {
        try {
            List<Path> bibFiles = listBibFiles(Paths.get(sourceDirectory));
            Set<String> combinedEntries = new LinkedHashSet<>();

            for (Path bibFile : bibFiles) {
                System.out.println("Procesando: " + bibFile.toString());
                List<String> entries = extractEntries(bibFile);
                combinedEntries.addAll(entries);
            }

            writeCombinedBib(combinedEntries, Paths.get(outputFilePath));
            System.out.println("Archivos .bib combinados exitosamente en: " + outputFilePath);
        } catch (IOException e) {
            throw new BibTeXMergerException("Error al combinar archivos .bib: " + e.getMessage());
        }
    }

    private List<Path> listBibFiles(Path directory) throws IOException {
        if (!Files.exists(directory)) {
            throw new FileNotFoundException("El directorio " + directory.toString() + " no existe.");
        }

        return Files.walk(directory)
                .filter(path -> !Files.isDirectory(path))
                .filter(path -> path.toString().toLowerCase().endsWith(".bib"))
                .collect(Collectors.toList());
    }

    private List<String> extractEntries(Path bibFile) throws IOException {
        List<String> entries = new ArrayList<>();
        StringBuilder entryBuilder = new StringBuilder();
        boolean insideEntry = false;

        try (BufferedReader reader = Files.newBufferedReader(bibFile)) {
            String line;
            while ((line = reader.readLine()) != null) {
                line = line.trim();
                if (line.startsWith("@")) {
                    if (insideEntry) {
                        entries.add(entryBuilder.toString());
                        entryBuilder.setLength(0);
                    }
                    insideEntry = true;
                }

                if (insideEntry) {
                    entryBuilder.append(line).append(System.lineSeparator());
                    if (line.endsWith("}")) {
                        entries.add(entryBuilder.toString());
                        entryBuilder.setLength(0);
                        insideEntry = false;
                    }
                }
            }

            if (entryBuilder.length() > 0) {
                entries.add(entryBuilder.toString());
            }
        }

        return entries;
    }

    private void writeCombinedBib(Set<String> entries, Path outputFile) throws IOException {
        try (BufferedWriter writer = Files.newBufferedWriter(outputFile)) {
            for (String entry : entries) {
                writer.write(entry);
                writer.write(System.lineSeparator());
                writer.write(System.lineSeparator());
            }
        }
    }

    //---------------------------------------------------------------------------------------
    // Buscar Lista todo bib
    //---------------------------------------------------------------------------------------
    // Método para leer el archivo .bib y extraer las entradas
    public List<Map<String, String>> parseBibFile(String filePath) {
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
                    String value = parts[1].trim().replaceAll("[{},]", ""); // Limpiar símbolos
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
}
