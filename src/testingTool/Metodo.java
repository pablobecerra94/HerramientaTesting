package testingTool;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;

public class Metodo {

	private String nombre;
	private ArrayList<String> lineasCodigoMetodo;
	private int cantidadLineas;
	private int cantidadLineasComentadas;
	private double porcentajeComentado;
	private int complejidadCiclomatica;
	private ArrayList<String> operadores;
	private HashSet<String> operandos;
	private int cantidadAparicionesOperadores;
	private int longitudHalstead;
	private double volumenHalstead;
	private int cantidadAparicionesOperandos;
	private int fanIn;
	private int fanOut;

	public Metodo(String nombre) {
		this.nombre = nombre;
		this.lineasCodigoMetodo = new ArrayList<String>();
		this.cantidadLineasComentadas = 0;
		complejidadCiclomatica = 0; // por defecto
		inicializarOperadores();
		operandos = new HashSet<>();
		cantidadAparicionesOperadores = 0;
		cantidadAparicionesOperandos = 0;
		fanIn=0;
		fanOut=0;
	}

	private void inicializarOperadores() {
		operadores = new ArrayList<>();
		operadores.add("for");
		operadores.add("if");
		operadores.add("while");
		operadores.add("switch");
		operadores.add("foreach");
		operadores.add("case");
		operadores.add("+");
		operadores.add("++");
		operadores.add("=+");
		operadores.add("--");
		operadores.add("=-");
		operadores.add("-");
		operadores.add(">");
		operadores.add(">=");
		operadores.add("<=");
		operadores.add("<");
		operadores.add("=*");
		operadores.add("=-");
		operadores.add("=/");
		operadores.add("==");
		operadores.add("=");
		operadores.add("%");
		operadores.add(":");
		operadores.add("&&");
		operadores.add("||");
		operadores.add("!");

	}
	
	public void listarLineasCodigo(){
		System.out.println("CODIGO DEL METODO: "+this.getNombre());
		for(String lineaActual : lineasCodigoMetodo )
			System.out.println(lineaActual);
		System.out.println("---------------------");
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

	public int getCantidadLineasComentadas() {
		return cantidadLineasComentadas;
	}

	public double getPorcentajeComentado() {
		return porcentajeComentado;
	}

	public int getComplejidadCiclomatica() {
		return complejidadCiclomatica;
	}

	public void calcularLineas() {
		this.cantidadLineas = this.lineasCodigoMetodo.size();
	}

	public void calcularLineasComentadas() {
		/*
		 * for (String lineaActual : lineasCodigoMetodo) { if
		 * (lineaActual.startsWith("//")){ cantidadLineasComentadas++;
		 * 
		 * } }
		 */
		cantidadLineasComentadas=0;
		Boolean comentarioMultiLinea = false;
		Iterator<String> iterator = lineasCodigoMetodo.iterator();
		while (iterator.hasNext()) {
			String lineaActual = iterator.next();

			if (comentarioMultiLinea && !lineaActual.contains("*/")) {
				cantidadLineasComentadas++;
			}

			if (lineaActual.startsWith("//")) {
				cantidadLineasComentadas++;
			} else if (lineaActual.contains("/*") && !lineaActual.contains("*/")) {
				cantidadLineasComentadas++;
				comentarioMultiLinea = true;
			} else if (lineaActual.contains("*/")) {
				cantidadLineasComentadas++;
				comentarioMultiLinea = false;
			}
		}
	}

	public void calcularPorcentajeComentado() {
		porcentajeComentado = (double) cantidadLineasComentadas / (double) cantidadLineas;
	}

	public void calcularComplejidadCiclomatica() {
		complejidadCiclomatica=0;
		int cant = 0;
		for (String lineaActual : lineasCodigoMetodo) {
			cant = 0;
			lineaActual = lineaActual.replace(" ", "");
			if (lineaActual.contains("for("))
				complejidadCiclomatica++;

			if (lineaActual.contains("if(") || lineaActual.contains("while(")) {
				cant += contarOcurrencias(lineaActual, "||");
				cant += contarOcurrencias(lineaActual, "&&");
				complejidadCiclomatica += cant + 1;
				/*
				 * if (lineaActual.contains("||")) { String[] arrayOR =
				 * lineaActual.split("||");
				 * 
				 * for (String iteracionOR : arrayOR) { if
				 * (!iteracionOR.contains("&&")) complejidadCiclomatica++; else
				 * { String[] arrayAND = iteracionOR.split("&&");
				 * complejidadCiclomatica += arrayAND.length; }
				 * 
				 * }
				 * 
				 * } else if (lineaActual.contains("&&")) { String[] arrayAND =
				 * lineaActual.split("&&");
				 * 
				 * for (String iteracionAND : arrayAND) { if
				 * (!iteracionAND.contains("||")) complejidadCiclomatica++; else
				 * { String[] arrayOR = iteracionAND.split("||");
				 * complejidadCiclomatica += arrayOR.length; } } } else {
				 * complejidadCiclomatica++; }
				 */

			}
		}

		complejidadCiclomatica++;
	}

	public int contarOcurrencias(String sTexto, String sTextoBuscado) {
		int contador = 0;
		while (sTexto.indexOf(sTextoBuscado) > -1) {
			sTexto = sTexto.substring(sTexto.indexOf(sTextoBuscado) + sTextoBuscado.length(), sTexto.length());
			contador++;
		}
		return contador;
	}

	public int calcularLongitudHalstead() {
		contarOperadores();
		contarOperandos();
		longitudHalstead = cantidadAparicionesOperadores + cantidadAparicionesOperandos;
		return longitudHalstead;
	}

	private void contarOperandos() {
		
		cantidadAparicionesOperandos=0;
		for (String lineaActual : lineasCodigoMetodo) {
			String operandos[] = lineaActual.split("^.*(if|else|case|default|for|while|catch|throw|\\+|-|\\*|\\/"
					+ "|={1}?|!=|={2}?|<=|>=|<{1}?|>{1}?|&&|\\|{2}?|and|or|equal to).*");
			for (int i = 0; i < operandos.length; i++) {
				this.cantidadAparicionesOperandos += 1;
				this.operandos.add(operandos[i]);
			}
		}
	}

	

	private void contarOperadores() {
		cantidadAparicionesOperadores=0;
		for (String lineaActual : lineasCodigoMetodo) {
			for (String operador : operadores) {
				if (lineaActual.contains(operador)) {
					cantidadAparicionesOperadores++;
				}
			}
		}
	}

	public double calcularVolumenHalstead() {
		volumenHalstead=longitudHalstead*(Math.log(operadores.size()+operandos.size()) / Math.log(2));
		return volumenHalstead;
	}

	@Override
	public String toString() {
		return nombre;
	}

	public List<String> getOperadores() {
		return operadores;
	}

	public HashSet<String> getOperandos() {
		return operandos;
	}

	public int getFanIn() {
		return fanIn;
	}

	public void setFanIn(int fanIn) {
		this.fanIn = fanIn;
	}

	public int getFanOut() {
		return fanOut;
	}

	public void setFanOut(int fanOut) {
		this.fanOut = fanOut;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((lineasCodigoMetodo == null) ? 0 : lineasCodigoMetodo.hashCode());
		result = prime * result + ((nombre == null) ? 0 : nombre.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Metodo other = (Metodo) obj;
		if (lineasCodigoMetodo == null) {
			if (other.lineasCodigoMetodo != null)
				return false;
		} else if (!lineasCodigoMetodo.equals(other.lineasCodigoMetodo))
			return false;
		if (nombre == null) {
			if (other.nombre != null)
				return false;
		} else if (!nombre.equals(other.nombre))
			return false;
		return true;
	}

	public void limpiarLineas() {
		lineasCodigoMetodo.removeAll(lineasCodigoMetodo);
		
	}

	
	
}
