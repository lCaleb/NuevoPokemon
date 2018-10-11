package modelo;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.ArrayList;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class CampoEntrenamiento implements Serializable{
	

	public static final int POS_BANDERA=120;
	public static final int POS_Y=450;
	private Pokemon[] pokemones;
	private Pokemon pokeDeTurno;
	private Jugador jugador;

	private boolean lanzar;
	private boolean atrapar;
	
	private ObservableList<String>  pokemonNames;

	public CampoEntrenamiento() {
		pokemonNames=FXCollections.observableArrayList();
		this.pokemones = new Pokemon[9];
		crearPokemones();
		
	}
	
	public void guardarPuntaje() {
		File file = new File ("Puntajes.txt");
		try {
			FileWriter fw = new FileWriter(file,true);
			BufferedWriter bw = new BufferedWriter(fw); 
			bw.write("\n"+jugador.getNombre()+"  "+pokeDeTurno.getNombre()+"  "+"Puntaje: "+jugador.getPuntaje());			
			bw.close();
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			System.out.println(e.getMessage());
		}
		
	}
	
	public ObservableList<String> pasarPuntajesALista() {
		ObservableList<String> puntajes=FXCollections.observableArrayList();
		
		File file = new File ("Puntajes.txt");
		
		try {
			FileReader reader = new FileReader(file); 
			BufferedReader br = new BufferedReader(reader);
			String linea = "";
			
			while((linea = br.readLine()) != null){ 
				puntajes.add(linea);
			}
			
			br.close(); 
			
		}catch (FileNotFoundException e) {
			e.printStackTrace();
		}catch(IOException e){
			
		}
		
		return puntajes;
	}
	
	public void aumentarPuntaje() {
		jugador.setPuntaje(680-pokeDeTurno.getPosX());
	}
	
	public Pokemon buscarPokemon(String nombre) {
		
		for (int i = 0; i < pokemones.length; i++) {
			
			Pokemon pokemon = pokemones[i];
			if (pokemon.getNombre().equals(nombre.toLowerCase())) {
				pokeDeTurno=pokemon;
				
			}
		}
		return pokeDeTurno;
	}
	
	public void comenzarEntrenamiento(int pos) {
		if (atrapar) {
			pokeDeTurno.setPosY(POS_Y);
			pokeDeTurno.setPosX(pos);
			correPokemon(3);
		} else if(lanzar) {
			pokeDeTurno.setPosY(POS_Y);
			pokeDeTurno.setPosX(pos);
			correPokemon(3);
		}
	}
	
	public ObservableList<String> getPokemonNames() {
		for (int i = 0; i < pokemones.length; i++) {
			String name= pokemones[i].getNombre();
			pokemonNames.add(name.toUpperCase());
		}
		return pokemonNames;
	}

	public void crearPokemones() {
		Pokemon pokemon1 = new Pokemon("pikachu","Cada vez que un Pikachu se encuentra con algo nuevo, le lanza una descarga eléctrica. Cuando se ve alguna baya chamuscada, es muy probable que sea obra de un Pikachu, ya que a veces no controlan la intensidad de la descarga.");
		pokemones[0] = pokemon1;
		Pokemon pokemon2 = new Pokemon("squirtle", "El caparazón de Squirtle no le sirve de protección únicamente. Su forma redondeada y las hendiduras que tiene le ayudan a deslizarse en el agua y le permiten nadar a gran velocidad.");
		pokemones[1] = pokemon2;
		Pokemon pokemon3 = new Pokemon("jolteon","Las células de Jolteon generan un nivel bajo de electricidad, cuya intensidad aumenta con la electricidad estática que acumula en un pelaje formado por agujas cargadas de electricidad. Esta característica le permite lanzar rayos.");
		pokemones[2] = pokemon3;
		Pokemon pokemon4 = new Pokemon("articuno","Articuno es un Pokémon pájaro legendario que puede controlar el hielo. El batir de sus alas congela el aire. Dicen que consigue hacer que nieve cuando vuela.");
		pokemones[3] = pokemon4;
		Pokemon pokemon5 = new Pokemon("bulbasaur", "A Bulbasaur es fácil verle echándose una siesta al sol. La semilla que tiene en el lomo va creciendo cada vez más a medida que absorbe los rayos del sol.");
		pokemones[4] = pokemon5;
		Pokemon pokemon6 = new Pokemon("umbreon","Umbreon evolucionó tras haber estado expuesto a ondas lunares. Suele esconderse en la oscuridad en silencio y esperar a que su presa se mueva. Cuando se lanza al ataque, le brillan los anillos del cuerpo.");
		pokemones[5] = pokemon6;
		Pokemon pokemon7 = new Pokemon("charizard","Charizard se dedica a volar por los cielos en busca de oponentes fuertes. Echa fuego por la boca y es capaz de derretir cualquier cosa. No obstante, si su rival es más débil que él, no usará este ataque.");
		pokemones[6] = pokemon7;
		Pokemon pokemon8 = new Pokemon("gyarados","Cuando Magikarp evoluciona y se convierte en Gyarados, sufre un cambio estructural en las células del cerebro. Dicen que esa transformación es la causa de la naturaleza violenta y salvaje de este Pokémon.");
		pokemones[7] = pokemon8;
		Pokemon pokemon9 = new Pokemon("surprise","El misterio, ni siquiera se sabe si es un pokemon.");
		pokemones[8] = pokemon9;

	}
	
	public void inicio(String name, boolean catchar, boolean throwar) {
		jugador= new Jugador(name);
		atrapar=catchar;
		lanzar=throwar;
	}
	
	public void mensajeDeBienvenida(int tipo) {
		
	}
	
	public void correPokemon(int speed) {
		if(pokeDeTurno.getPosX()>(0+speed)) {
			pokeDeTurno.setPosX(pokeDeTurno.getPosX()-speed);
		}else {
			pokeDeTurno.setPosX(700);
		}
	}
	public Pokemon getPokeDeTurno() {
		return pokeDeTurno;
	}

	
	public Jugador getJugador() {
		return jugador;
	}

	public void setJugador(Jugador jugador) {
		this.jugador = jugador;
	}
	public ArrayList<CampoEntrenamiento> leerCampos() {
		FileInputStream fileInStr = null;
        ObjectInputStream entrada = null;
        ArrayList<CampoEntrenamiento> campos=null;

        try {
        	fileInStr = new FileInputStream("Campos.dat");
            entrada = new ObjectInputStream(fileInStr);
            campos = (ArrayList<CampoEntrenamiento>) entrada.readObject();            
        } catch (FileNotFoundException e) {
            System.out.println(e.getMessage());
        } catch (ClassNotFoundException e) {
            System.out.println(e.getMessage());
        } catch (IOException e) {
            System.out.println(e.getMessage());
        } finally {
            try {
                if (fileInStr != null) {
                	fileInStr.close();
                }
                if (entrada != null) {
                    entrada.close();
                }
            } catch (IOException e) {
                System.out.println(e.getMessage());
            }
        }
		return campos;
	}
	
	public void serializarCampo() {
		FileOutputStream fileOutS = null;
		ObjectOutputStream salida = null;
		ArrayList<CampoEntrenamiento> campos=leerCampos();
		try
		{
			fileOutS = new FileOutputStream("Campos.dat");
			salida = new ObjectOutputStream(fileOutS);
			if (campos!=null) {
				campos.add(this);
			}else {
				campos= new ArrayList<CampoEntrenamiento>();
				campos.add(this);
			}		
			salida.writeObject(campos);

		}catch(FileNotFoundException e)
		{
			System.out.println(e.getMessage());
		}catch(IOException e)
		{
			System.out.println(e.getMessage());
		}finally
		{
			try {
				if (campos != null)
					fileOutS.close();
				if (salida != null)
					salida.close();
			} catch (IOException e) {
				System.out.println(e.getMessage());
			}
		}
	
	}

	public void setPokeDeTurno(Pokemon pokeDeTurno) {
		this.pokeDeTurno = pokeDeTurno;
	}

	public Pokemon[] getPokemones() {
		return pokemones;
	}

	public void setPokemones(Pokemon[] pokemones) {
		this.pokemones = pokemones;
	}

	public boolean isLanzar() {
		return lanzar;
	}

	public void setLanzar(boolean lanzar) {
		this.lanzar = lanzar;
	}

	public boolean isAtrapar() {
		return atrapar;
	}

	public void setAtrapar(boolean atrapar) {
		this.atrapar = atrapar;
	}


}
