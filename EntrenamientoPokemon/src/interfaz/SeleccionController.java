package interfaz;

import java.io.IOException;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.ListView;
import javafx.scene.control.TextArea;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import modelo.CampoEntrenamiento;
import modelo.Pokemon;

public class SeleccionController {
	
	private Main principal;
	private CampoEntrenamiento campo;
	private OpcionController opCont;
	@FXML private ListView<String> listaPokemon;
	@FXML private TextArea textArea;
	@FXML private ImageView pokemon;
	
	
	public SeleccionController() {
		
	}
	
	public void initialize() {
		textArea.setWrapText(true);
	}
	
	public void enlazar(Main main, CampoEntrenamiento camp, OpcionController opCon) {
		this.principal=main;
		this.campo=camp;
		this.opCont=opCon;
		listaPokemon.setItems(campo.getPokemonNames());
		listaPokemon.setStyle("-fx-font-size: 20; -fx-font-weight: bold;");
		
		listaPokemon.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<String>() {

			@Override
			public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
				Pokemon poke=campo.buscarPokemon(newValue);
				String image= "/pokemones/"+ newValue+".png";
				Image imagen= new Image(image);
				pokemon.setImage(imagen);
				textArea.setText(poke.getInfo());
				
			}
		
		});
		
		
	}
	
	
	public void jugar() {
		FXMLLoader loader=new FXMLLoader(getClass().getResource("Campo.fxml"));
		AnchorPane root;
		try {
			root = (AnchorPane)loader.load();
			CampoController controlador= loader.getController();
			controlador.enlazar(principal, campo, opCont,this);
			String image= "/image/fondoCampo.jpg";
			root.setStyle("-fx-background-image: url('" + image + "'); "
			           + "-fx-background-position: center center; "
			           + "-fx-background-repeat: stretch;");
			principal.getPrimaryStage().setScene(new Scene(root));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

}
