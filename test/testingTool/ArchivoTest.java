package testingTool;

import static org.junit.Assert.*;

import java.io.File;
import java.io.FileNotFoundException;

import org.junit.Test;

public class ArchivoTest {

	@Test
	public void test() throws FileNotFoundException {
		Archivo archivo= new Archivo(new File("src//testingTool//Archivo.java"));
		archivo.verLineas();
	}

}
