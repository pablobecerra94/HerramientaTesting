package testingTool;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

public class Archivo {

	private File file;
	private ArrayList<String> lineas;

	public Archivo(File file) throws FileNotFoundException {
		this.file = file;
		lineas = new ArrayList<>();
		leerArchivo();
	}

	public void leerArchivo() throws FileNotFoundException {
		Scanner scanner = new Scanner(file);
		while (scanner.hasNextLine()) {
			String lineaActual = scanner.nextLine();
			lineaActual=lineaActual.replaceAll("\r", "").replaceAll("\n", "").replaceAll("\t","");
			if (!lineaActual.isEmpty() && !lineaActual.contains("import") && !lineaActual.contains("package")) {
				lineas.add(lineaActual);
			}
		}
		scanner.close();

		
		
	}

	public void verLineas() {
		for (String linea : lineas) {
			System.out.println(linea);
		}
	}

}
