package interfaz;

import java.io.IOException;

import javax.swing.JOptionPane;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.animation.Transition;
import javafx.animation.TranslateTransition;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Bounds;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.util.Duration;
import modelo.CampoEntrenamiento;

public class CampoController {
	
	private Main principal;
	private CampoEntrenamiento campo;
	private OpcionController opCont;
	private SeleccionController selecControl;
	private int posInicial;
	private boolean atrapado;
	
	@FXML private ImageView objeto;
	@FXML private TextField puntaje;
	@FXML private Button guardar;
	
	private Timeline timeLineAnimation;
	
	public CampoController() {
		
	}
	
	public void initialize() {
		
	}
	
	public void inicioJuego() {
		objeto.setLayoutY(campo.POS_Y);
		posInicial=((int)Math.random()*100 +600);
		objeto.setLayoutX(posInicial);
		
		if (campo.isLanzar()) {
			String imageUrl= "/image/Pokeball.png";
			Image imagena= new Image(imageUrl);
			objeto.setImage(imagena);
			objeto.setFitHeight(60);
			objeto.setFitWidth(60);
			
		}else if(campo.isAtrapar()) {
			String imageUrla= "/pokemones/"+ campo.getPokeDeTurno().getNombre()+".gif";
			Image imagenao= new Image(imageUrla);
			objeto.setImage(imagenao);
			objeto.setFitHeight(150);
			objeto.setFitWidth(150);
			correrPokemon();
		}
	}
	
	
	public void lanzarMensaje(int distancia) {
		timeLineAnimation.pause();
		Alert alert = new Alert(AlertType.INFORMATION);
		alert.setTitle("Recorrido");
		alert.setHeaderText("La Distancia Recorrida fue:");
		alert.setContentText(distancia+"PokePixeles");
		alert.initOwner(principal.getPrimaryStage());
		alert.show();
	}
	
	public void correrPokemon() {
		timeLineAnimation = new Timeline(new KeyFrame(Duration.millis(60), new EventHandler<ActionEvent>() {

			
			@Override
			public void handle(ActionEvent e) {
				// TODO Auto-generated method stub
				campo.correPokemon(3);
				objeto.setLayoutX(campo.getPokeDeTurno().getPosX());
				campo.aumentarPuntaje();
				puntaje.setText("Puntaje:    "+campo.getJugador().getPuntaje());
				if(campo.getPokeDeTurno().getPosX()<campo.POS_BANDERA){
					lanzarMensaje(posInicial-campo.POS_BANDERA);
					}
				}
			
		}));
		timeLineAnimation.setCycleCount(Timeline.INDEFINITE);
		timeLineAnimation.play();
	}
	
	public void entrenar() {
		if (campo.isLanzar()) {
			String image= "/pokemones/"+ campo.getPokeDeTurno().getNombre()+".gif";
			Image imagen= new Image(image);
			objeto.setImage(imagen);
			campo.comenzarEntrenamiento(posInicial);
			correrPokemon();
		}else if (campo.isAtrapar()) {
			timeLineAnimation.stop();
			String imageUrl= "/image/Pokeball.png";
			Image imagena= new Image(imageUrl);
			objeto.setImage(imagena);
			objeto.setFitHeight(60);
			objeto.setFitWidth(60);
			//campo.getPokeDeTurno().setPosX(700);
			
		}
		
	}
	public void enlazar(Main main, CampoEntrenamiento camp, OpcionController opCon,SeleccionController selecControl) {
		this.principal=main;
		this.campo=camp;
		this.opCont=opCon;
		this.selecControl=selecControl;
		if (campo.isLanzar()) {
			inicioJuego();
		} else {
			inicioJuego();
		}
		
	}
	
	public void guardaPuntajes() {
		campo.guardarPuntaje();
		campo.serializarCampo();
	}
	
	public void guardar() {
		guardaPuntajes();
		timeLineAnimation.stop();
		
		FXMLLoader loader=new FXMLLoader(getClass().getResource("Puntaje.fxml"));
		AnchorPane root;
		try {
			root = (AnchorPane)loader.load();
			PuntajeController controlador= loader.getController();
			controlador.enlazar(principal, campo, opCont,selecControl,this);
			String image= "/image/fondoPuntaje.jpg";
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
