package testingTool;

import java.util.ArrayList;

public class Metodo {

	private String nombre;
	private ArrayList<String> lineasCodigoMetodo;
	private int cantidadLineas;
	
	public Metodo (String nombre){
		this.nombre=nombre;
		this.lineasCodigoMetodo = new ArrayList<String>();
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public ArrayList<String> getLineasCodigo() {
		return lineasCodigoMetodo;
	}

	public void setLineasCodigo(ArrayList<String> lineasCodigo) {
		this.lineasCodigoMetodo = lineasCodigo;
	}

	public int getCantidadLineas() {
		return cantidadLineas;
	}

	public void setCantidadLineas(int cantidadLineas) {
		this.cantidadLineas = cantidadLineas;
	}
	
	
	
}
