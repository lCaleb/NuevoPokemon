package interfaz;

import modelo.CampoEntrenamiento;

import java.io.IOException;

import interfaz.Main;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;

public class OpcionController {
	
	
	private CampoEntrenamiento campo;
	
	@FXML private TextField nombre;
	@FXML private RadioButton atrapar;
	@FXML private RadioButton lanzar;
	@FXML private Button continuar;
	
	private Main principal;
	
	
	public OpcionController() {
		
	}
	
	public void initialize() {
		campo= new CampoEntrenamiento();
		atrapar.setOnMouseClicked(new EventHandler<MouseEvent>() {
			@Override
			public void handle(MouseEvent e) {
				lanzar.setSelected(false);
			}
		});
		
		lanzar.setOnMouseClicked(new EventHandler<MouseEvent>() {
			@Override
			public void handle(MouseEvent e) {
				atrapar.setSelected(false);
			}
		});
	}
	
	public void continuar() {
		campo.inicio(nombre.getText(), atrapar.isSelected(), lanzar.isSelected());
		FXMLLoader loader=new FXMLLoader(getClass().getResource("Seleccion.fxml"));
		AnchorPane root;
		try {
			root = (AnchorPane)loader.load();
			SeleccionController controlador= loader.getController();
			controlador.enlazar(principal, campo, this);
			String image= "/image/fondoSeleccion.jpg";
			root.setStyle("-fx-background-image: url('" + image + "'); "
			           + "-fx-background-position: center center; "
			           + "-fx-background-repeat: stretch;");
			principal.getPrimaryStage().setScene(new Scene(root));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
	
	
	
	public void pasarMain(Main main) {
		principal=main;
		principal.getPrimaryStage().setHeight(700);
		principal.getPrimaryStage().setWidth(720);
		principal.getPrimaryStage().setTitle("EntrenamientoPokemon :: By Maykol S & CVH.");
		principal.getPrimaryStage().getIcons().add(new Image("/image/pokeball.png"));
		String image= "/image/fondoOpcion.jpg";
		principal.getScene().getRoot().setStyle("-fx-background-image: url('" + image + "'); "
		           + "-fx-background-position: center center; "
		           + "-fx-background-repeat: stretch;");
	}
	
	
	
}
