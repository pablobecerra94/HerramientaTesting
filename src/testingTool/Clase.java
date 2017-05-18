package testingTool;

import java.util.ArrayList;
import java.util.List;

public class Clase {

	private String nombre;
	private ArrayList<String> lineasCodigoClase;
	private ArrayList<Metodo> metodos;
	private int cantidadLineas;

	public Clase(String nombre) {
		this.nombre = nombre;
		this.lineasCodigoClase = new ArrayList<String>();
		this.metodos = new ArrayList<Metodo>();
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	@Override
	public String toString() {
		return nombre;
	}

	public ArrayList<String> getLineasCodigo() {
		return lineasCodigoClase;
	}

	public void setLineasCodigo(ArrayList<String> lineasCodigo) {
		this.lineasCodigoClase = lineasCodigo;
	}

	public int getCantidadLineas() {
		return cantidadLineas;
	}

	public void setCantidadLineas(int cantidadLineas) {
		this.cantidadLineas = cantidadLineas;
	}

	public void encontrarMetodos() {
		String[] auxiliar = new String[10];

		String nombreMetodo;

		for (String linea : lineasCodigoClase) {

			if ((!linea.contains(";")) && tieneModificadores(linea)) {

				auxiliar = linea.split(" ");
				if (linea.contains("static")) {
					if (auxiliar[3].contains("("))
						nombreMetodo = auxiliar[3].substring(0, auxiliar[3].indexOf("("));
					else
						nombreMetodo = auxiliar[3];
				} else {

					if (!linea.contains(" " + this.getNombre() + "(")) {
						if (auxiliar[2].contains("("))
							nombreMetodo = auxiliar[2].substring(0, auxiliar[2].indexOf("("));
						else
							nombreMetodo = auxiliar[2];
					} else {
						if (auxiliar[1].contains("("))
							nombreMetodo = auxiliar[1].substring(0, auxiliar[1].indexOf("("));
						else
							nombreMetodo = auxiliar[1];
					}
				}
				Metodo metodo = new Metodo(nombreMetodo);
				metodos.add(metodo);
			}

		}

		this.agregarLineasCodigoMetodo();
		this.calcularFanInTotal();
		this.calcularFanOutTotal();
	}

	private boolean tieneModificadores(String linea) {
		return linea.contains("public") || linea.contains("protected") || linea.contains("private");
	}

	public void agregarLineasCodigoMetodo() {
		int a = 0;
		int contadorLlaves = 0;

		for (Metodo metodoActual : metodos) {
			metodoActual.limpiarLineas();
			a = 0;
			contadorLlaves = 0;
			while (a < lineasCodigoClase.size() && !lineasCodigoClase.get(a).contains(metodoActual.getNombre())
					|| lineasCodigoClase.get(a).contains(";") || !tieneModificadores(lineasCodigoClase.get(a)))
				a++;

			if (a < lineasCodigoClase.size() && lineasCodigoClase.get(a).contains("{")) {
				contadorLlaves++;
				a++;
			}

			if (a >= lineasCodigoClase.size()) {
				continue;
			}

			metodoActual.setFirma(lineasCodigoClase.get(--a));
			a++;//

			do {
				if (lineasCodigoClase.get(a).contains("{"))
					contadorLlaves++;
				if (lineasCodigoClase.get(a).contains("}"))
					contadorLlaves--;
				metodoActual.getLineasCodigo().add(lineasCodigoClase.get(a));
				a++;

			} while (contadorLlaves != 0);

		}

	}

	public List<Metodo> getMetodos() {
		return metodos;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((metodos == null) ? 0 : metodos.hashCode());
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
		Clase other = (Clase) obj;
		if (metodos == null) {
			if (other.metodos != null)
				return false;
		} else if (!metodos.equals(other.metodos))
			return false;
		if (nombre == null) {
			if (other.nombre != null)
				return false;
		} else if (!nombre.equals(other.nombre))
			return false;
		return true;
	}

	public void calcularFanInTotal() {
		int posicion = 0;
		char letra;
		String lineaActual;
		for (Metodo metodoActual : metodos) { // aguante la complejidad cubica
												// vieja
			for (Metodo metodoLlamador : metodos) {
				if (!metodoActual.equals(metodoLlamador)) {
					for (String lineaMetodo : metodoLlamador.getLineasCodigo()) {

						lineaActual = lineaMetodo.replace(" ", "");
						posicion = lineaActual.indexOf(metodoActual.getNombre() + "(");
						if (posicion != -1) {
							if (posicion == 0)
								metodoActual.setFanIn(metodoActual.getFanIn() + 1);

							else {
								posicion--;
								if (!Character.isLetter(lineaActual.charAt(posicion)))
									metodoActual.setFanIn(metodoActual.getFanIn() + 1);
							}

						}
					}
				}
			}
		}

	}

	public void calcularFanOutTotal() {
		String lineaActual;
		int posicion = 0;
		for (Metodo metodoActual : metodos) {
			for (Metodo metodoLlamado : metodos) {
				if (!metodoActual.equals(metodoLlamado)) {
					for (String lineaMetodo : metodoActual.getLineasCodigo()) {
						lineaActual = lineaMetodo.replace(" ", "");
						posicion = lineaActual.indexOf(metodoLlamado.getNombre() + "(");
						if (posicion != -1) {
							if (posicion == 0)
								metodoActual.setFanOut(metodoActual.getFanOut() + 1);

							else {
								posicion--;
								if (!Character.isLetter(lineaActual.charAt(posicion)))
									metodoActual.setFanOut(metodoActual.getFanOut() + 1);
							}

						}

					}
				}
			}
		}
	}

}
