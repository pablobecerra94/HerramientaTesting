package testingTool;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Scanner;

public class Archivo {

	private File file;
	private ArrayList<String> lineas;
	private ArrayList<Clase> clases;

	public Archivo(File file) throws FileNotFoundException {
		this.file = file;
		inicializar();
		leerArchivo();
		buscarClases();
	}

	private void inicializar() {
		lineas = new ArrayList<>();
		clases = new ArrayList<>();
	}

	public void buscarClases() {
		int indiceClase=-1;
		for(int i=0;i<lineas.size();i++){
			
			if(lineas.get(i).startsWith("class")||lineas.get(i).contains(" class ")){
					String [] lineaActual=lineas.get(i).split(" ");
					for(int j=0;j<lineaActual.length-1;j++){
						if(lineaActual[j].equals("class")){
							Clase clase = new Clase(lineaActual[j+1]);
							clases.add(clase);
							indiceClase++;
						}
					}
					
			}
			else{
				clases.get(indiceClase).getLineasCodigo().add(lineas.get(i));
			}
				
		}
		
		for(Clase clase : clases)
			clase.setCantidadLineas(clase.getLineasCodigo().size());
		
		
	/*	System.out.println("IMPRESION DE CODIGO DE LA PRIMERA CLASE ENCONTRADA:");
		for(int i=0;i<clases.get(0).getLineasCodigo().size();i++)
			System.out.println(clases.get(0).getLineasCodigo().get(i));
		
		System.out.println("\n\n\n\nIMPRESION DE CODIGO DE LA SEGUNDA CLASE ENCONTRADA:");
		for(int i=0;i<clases.get(1).getLineasCodigo().size();i++)
			System.out.println(clases.get(1).getLineasCodigo().get(i));*/
		clases.get(0).encontrarMetodos();
	}

	public void leerArchivo() throws FileNotFoundException {
		Scanner scanner = new Scanner(file);
		while (scanner.hasNextLine()) {
			String lineaActual = scanner.nextLine();
			lineaActual = limpiarLinea(lineaActual);
			if (!lineaActual.isEmpty() && !lineaActual.contains("import") && !lineaActual.contains("package")) {
				lineas.add(lineaActual);
			}
		}
		scanner.close();

		
		
	}

	private String limpiarLinea(String lineaActual) {
		return lineaActual.replaceAll("\r", "").replaceAll("\n", "").replaceAll("\t","");
	}

	public void verLineas() {
		for (String linea : lineas) {
			System.out.println(linea);
		}
	}

	public void verClases() {
		for (Clase clase : clases) {
			System.out.println(clase);
		}
	}
	
	public List<Clase> getClases(){
		return clases;
	}

}
