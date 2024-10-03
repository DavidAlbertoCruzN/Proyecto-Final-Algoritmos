package co.edu.uniquindio.bibliometria.model;

import java.util.HashMap;
import java.util.Map;

public class BibEntry {
    // Atributos comunes de una entrada BibTeX
    private String entryType;  // Tipo de entrada, como "article", "book", etc.
    private String citationKey; // Clave de citación única
    private Map<String, String> fields; // Campos adicionales, como author, title, etc.

    // Constructor
    public BibEntry(String entryType, String citationKey) {
        this.entryType = entryType;
        this.citationKey = citationKey;
        this.fields = new HashMap<>();
    }

    // Métodos para agregar y obtener campos
    public void addField(String key, String value) {
        fields.put(key, value);
    }

    public String getField(String key) {
        return fields.get(key);
    }

    public String getEntryType() {
        return entryType;
    }

    public String getCitationKey() {
        return citationKey;
    }

    @Override
	public String toString() {
		return "BibEntry [entryType=" + entryType + ", citationKey=" + citationKey + ",\n"+ " fields=" + fields + "]";
	}


}
