package interfaz;

import javafx.fxml.FXML;
import javafx.scene.control.ListView;
import modelo.CampoEntrenamiento;

public class PuntajeController {

	private Main principal;
	private CampoEntrenamiento campo;
	private OpcionController opCont;
	private SeleccionController selecControl;
	private CampoController campoControl;
	@FXML private ListView puntajes;
	
	public PuntajeController() {
		
	}
	
	public void initialize() {
		
	}
	
	
	
	public void enlazar(Main main, CampoEntrenamiento camp, OpcionController opCon,SeleccionController selecControl, CampoController campo) {
		this.principal=main;
		this.campo=camp;
		this.opCont=opCon;
		this.selecControl=selecControl;
		this.campoControl= campo;
		puntajes.setItems(this.campo.pasarPuntajesALista());
		puntajes.setStyle("-fx-font-size: 20; -fx-font-weight: bold;-fx-text-fill: blue;");
	}

}
