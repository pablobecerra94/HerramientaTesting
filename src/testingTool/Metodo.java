package testingTool;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;

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

	public Metodo(String nombre) {
		this.nombre = nombre;
		this.lineasCodigoMetodo = new ArrayList<String>();
		this.cantidadLineasComentadas = 0;
		complejidadCiclomatica = 0; // por defecto
		inicializarOperadores();
		operandos = new HashSet<>();
		cantidadAparicionesOperadores = 0;
		cantidadAparicionesOperandos = 0;
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
		detectarOperandos();
		for (String lineaActual : lineasCodigoMetodo) {
			String operandos[] = lineaActual.split("^.*(if|else|case|default|for|while|catch|throw|\\+|-|\\*|\\/"
					+ "|={1}?|!=|={2}?|<=|>=|<{1}?|>{1}?|&&|\\|{2}?|and|or|equal to).*");
			for (int i = 0; i < operandos.length; i++) {
				this.cantidadAparicionesOperandos += 1;
				this.operandos.add(operandos[i]);
			}
		}
	}

	private void detectarOperandos() {
		

	}

	private void contarOperadores() {
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
}
