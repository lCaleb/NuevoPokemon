package modelo;

public class Pokemon {
	
	private String nombre;
	private String ruta;
	private String info;
	

	private int posX;
	private int posY;
	
	public Pokemon(String nombre, String info) {
		this.nombre = nombre;
		this.info=info;
		setRuta(nombre);
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public int getPosX() {
		return posX;
	}

	public void setPosX(int speed) {
		this.posX = speed;
	}

	public String getRuta() {
		return ruta;
	}

	public void setRuta(String ruta) {
		this.ruta = "/pokemones/"+nombre+".gif";
	}

	public int getPosY() {
		return posY;
	}

	public void setPosY(int posY) {
		this.posY = posY;
	}
	
	public String getInfo() {
		return info;
	}

	public void setInfo(String info) {
		this.info = info;
	}

}
