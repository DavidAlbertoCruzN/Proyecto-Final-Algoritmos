package co.edu.uniquindio.bibliometria.controllers;

import co.edu.uniquindio.bibliometria.Aplicacion;
import co.edu.uniquindio.bibliometria.exceptions.BibTeXMergerException;
import co.edu.uniquindio.bibliometria.persistencia.BibFileHandler;
import javafx.fxml.FXML;

import java.net.URL;
import java.util.ResourceBundle;

public class BibliometriaViewController {


    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

	Aplicacion aplicacion;

    @FXML
    void initialize() {

    }

	public void setAplicacion(Aplicacion aplicacion) {
		// TODO Auto-generated method stub
		this.aplicacion = aplicacion;
	}
}
