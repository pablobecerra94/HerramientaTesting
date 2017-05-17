package testingTool;

import java.util.ArrayList;

public class Triangulo {
	ArrayList<Integer> lados=new ArrayList<>();

	
	
	public Triangulo(Object...objects) throws Exception{
		if(objects.length!=3){
			throw new Exception("Error, cantidad de valores incorrectos, debe ingresar 3 valores");
		}
		else{
			for(Object object : objects){
				if(object.getClass().equals(Integer.class)){
					lados.add((Integer) object);
				}
				else{
					if(object.getClass().equals(String.class)||object.getClass().equals(Character.class)){
						throw new Exception("Error no debe ingresar caracteres o cadenas de caracteres, debe ingresar numeros enteros positivos");
					}
						
				}
			}
			
		}
		
	}

	public int getLado1() {
		return lados.get(0);
	}

	public void setLado1(int lado1) {
		this.lados.set(0, lado1);
	}

	public int getLado2() {
		return lados.get(1);
	}

	public void setLado2(int lado2) {
		this.lados.set(1, lado2);
	}

	public int getLado3() {
		return lados.get(2);
	}

	public void setLado3(int lado3) {
		this.lados.set(2, lado3);
	}

	public String definir() {
		//linea1
		/*lienea2*/
		/*linea3
		 *4 
		 */
		/*linea1
		 * 
		 p*/		
		if (!validarLadosMayoresDistintosACero()) {
			return "Error, valor invalido, no debe ingresar un 0, solo numeros positivos mayores a 0";
		} else 	
		{
			if(!validarLadosNoNegativos()){
				return "Error, valor invalido, no debe ingresar numeros negativos, solo positivos mayores a 0";				
			}
			else {

				if (!esTriangulo()) {
					return "Error, no es un triangulo, no cumple la regla de triangularidad, la suma de los dos lados menores debe dar mayor al lado mayor";
				}

				else{


					if (lados.get(0) == lados.get(1)&& lados.get(1)== lados.get(2)) {
						return "El triangulo es  equilatero";
					} else {
						if (lados.get(0) ==  lados.get(1) ||  lados.get(0) ==  lados.get(2) ||  lados.get(1) ==  lados.get(2))
							return "El triangulo es isosceles";
						else {
							if (lados.get(0) != lados.get(1) || lados.get(1) != lados.get(2)|| lados.get(2) != lados.get(1))
								return "El triangulo es escaleno";
						}
					}
				}
			}
		}
		return null;
	}

	private boolean validarLadosNoNegativos() {
		return lados.get(0) > 0 && lados.get(1) > 0 && lados.get(2) > 0;
	}

	private boolean validarLadosMayoresDistintosACero() {
		return lados.get(0) != 0 && lados.get(1) != 0 && lados.get(2) != 0;
	}

	private boolean esTriangulo() {
		if (lados.get(0) > lados.get(1) && lados.get(0) > lados.get(2)) {
			return lados.get(0) < lados.get(1)+ lados.get(2);
		} else {
			if (lados.get(1) > lados.get(0)&& lados.get(1)> lados.get(2)) {
				return lados.get(1) < lados.get(0)+ lados.get(2);
			} else {
				if (lados.get(2)> lados.get(0)&& lados.get(2)> lados.get(1)) {
					return lados.get(2) < lados.get(1)+ lados.get(0);
				} else {
					return true;
				}
			}
		}
	}

	
}
