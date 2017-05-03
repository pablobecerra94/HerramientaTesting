package testingTool;

import org.junit.Assert;
import org.junit.Test;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class SelectorTest {
    private String path;
    private Selector selector = new Selector();

    @Test
    public void mustReturnJustJavaFiles(){
        path = "..\\herramientaTesting";
        Assert.assertEquals(4 ,selector.listFiles(path).size());
    }

    @Test
    public void mustReturnErrorIfFileIsNotADirectory(){
        path = "test\\testingTool\\SelectorTest.java";
        selector.listFiles(path);
        Assert.assertEquals("El archivo no es un directorio", selector.getError());
    }

    @Test
    public void mustReturnErrorIfFileNotExist(){
        path = ".\\nonexistentDirectory";
        selector.listFiles(path);
        Assert.assertEquals("El directorio no existe", selector.getError());
    }

    @Test
    public void mustListClasses(){
        path = "..\\herramientaTesting";
        List<File> files = selector.listFiles(path);
        List<String> classes = new ArrayList<>();
        classes.add("HerramientaTesting");
        Assert.assertEquals(classes.get(0) ,selector.listClasses(files.get(0)).get(0));
    }
}