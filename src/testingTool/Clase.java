package testingTool;

import java.util.ArrayList;

public class Clase {
	
	private String nombre;
	private ArrayList<String> lineasCodigoClase;
	private ArrayList<Metodo> metodos;
	private int cantidadLineas;
	
	public Clase(String nombre) {
		this.nombre=nombre;
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
	
	public void encontrarMetodos(){
		String[] auxiliar = new String[10]; //perdon por esto, es un parche jaja
		String nombreMetodo;
	
		for(String linea: lineasCodigoClase){
			
			if((!linea.contains(";"))&&tieneModificadores(linea)){
				
				auxiliar = linea.split(" ");
				if(linea.contains("static")) {
					if(auxiliar[3].contains("("))
						nombreMetodo=	auxiliar[3].substring(0, auxiliar[3].indexOf("("));
					else
						nombreMetodo=	auxiliar[3];
				}
				else{
					//if(!linea.contains(this.getNombre())){
					if(!linea.contains(" "+this.getNombre()+"(")){
						if(auxiliar[2].contains("("))
							nombreMetodo=	auxiliar[2].substring(0, auxiliar[2].indexOf("("));
						else
							nombreMetodo=	auxiliar[2];
					}
					else{
						if(auxiliar[1].contains("("))
							nombreMetodo=	auxiliar[1].substring(0, auxiliar[1].indexOf("("));
						else
							nombreMetodo=	auxiliar[1];
					}
				}
				Metodo metodo = new Metodo(nombreMetodo);
				metodos.add(metodo);
			}
			
		}
	
			for(Metodo met : metodos)
			{
			System.out.println(met.getNombre());
			met.setCantidadLineas(met.getLineasCodigo().size());
			}
			
			this.agregarLineasCodigoMetodo();
			
	}

	private boolean tieneModificadores(String linea) {
		return linea.contains("public")||linea.contains("protected")||linea.contains("private");
	}
	
	
	
	public void agregarLineasCodigoMetodo(){
		int a=0;
		int contadorLlaves=0;
		System.out.println("-----------------------------");
		for(Metodo metodoActual : metodos){
			a=0;
			contadorLlaves=0;
			while(!lineasCodigoClase.get(a).contains(metodoActual.getNombre())|| lineasCodigoClase.get(a).contains(";")|| !tieneModificadores(lineasCodigoClase.get(a)))
				a++;
			
			if(lineasCodigoClase.get(a).contains("{"))
				contadorLlaves++;
			a++;
			do{
				if(lineasCodigoClase.get(a).contains("{"))
					contadorLlaves++;
				if(lineasCodigoClase.get(a).contains("}"))
					contadorLlaves--;
				metodoActual.getLineasCodigo().add(lineasCodigoClase.get(a));
				a++;
				
			}
				while(contadorLlaves!=0);
			
		}
		
		System.out.println("\n\n\nCODIGO DEL PRIMER METODO:");
		for(int i=0;i<metodos.get(10).getLineasCodigo().size();i++)
			System.out.println(metodos.get(10).getLineasCodigo().get(i));
		
		
		int k=7;
		metodos.get(k).calcularLineasComentadas();
		metodos.get(k).calcularLineas();
		metodos.get(k).calcularPorcentajeComentado();
		System.out.println(metodos.get(k).getCantidadLineas());
		System.out.println(metodos.get(k).getCantidadLineasComentadas());
		System.out.println(metodos.get(k).getPorcentajeComentado());
		
		
		System.out.println(metodos.get(k).getNombre());
		metodos.get(k).calcularComplejidadCiclomatica();
		System.out.println(metodos.get(k).getComplejidadCiclomatica());
	}

}
