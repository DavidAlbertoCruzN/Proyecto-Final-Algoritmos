package co.edu.uniquindio.bibliometria;

import java.io.IOException;

import co.edu.uniquindio.bibliometria.controllers.BibParserController;
import co.edu.uniquindio.bibliometria.controllers.BibliometriaViewController;
import co.edu.uniquindio.bibliometria.exceptions.BibTeXMergerException;
import co.edu.uniquindio.bibliometria.model.BibEntry;
import co.edu.uniquindio.bibliometria.persistencia.BibFileHandler;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;


public class Aplicacion extends Application {

	private Stage primaryStage;
	private BorderPane rootLayout;

	@Override
	public void start(Stage primaryStage) {
		this.primaryStage = primaryStage;
		this.primaryStage.setTitle("Proyecto del bibliometria");
		mostrarVentanaPrincipal();
	}


	/** * Initializes the root layout. */
	public void mostrarVentanaPrincipal() {
		try {
			// Load root layout from fxml file.
			FXMLLoader loader = new FXMLLoader();
			loader.setLocation(Aplicacion.class.getResource("views/BibliometriaView.fxml"));
			AnchorPane rootLayout = (AnchorPane) loader.load();
			BibliometriaViewController bibliometriaViewController = loader.getController();

			bibliometriaViewController.setAplicacion(this);

			// Show the scene containing the root layout.
			Scene scene = new Scene(rootLayout);
			primaryStage.setScene(scene);
			primaryStage.show();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public static void main(String[] args) {

		BibParserController controller = new BibParserController();
        controller.run();

        BibEntry entry = new BibEntry("article", "Doe2024");
        entry.addField("author", "David");
        entry.addField("title", "Un estudio");
        entry.addField("journal", "Revista de estudios");
        entry.addField("year", "2024");

        System.out.println(entry.toString());

		launch(args);

	}
}
