package testingTool;

import org.junit.Assert;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

public class DirectoryTest {
    private String path;
    private Directory directory;

    @Test
    public void mustReturnJustJavaFiles(){
        path = "..\\herramientaTesting";
        directory = new Directory(path);
        Assert.assertEquals(7, directory.getFiles().size());
    }

    @Test
    public void mustReturnErrorIfFileIsNotADirectory(){
        path = "test\\testingTool\\DirectoryTest.java";
        directory = new Directory(path);
        Assert.assertEquals("El archivo no es un directorio", directory.getError());
    }

    @Test
    public void mustReturnErrorIfFileNotExist(){
        path = ".\\nonexistentDirectory";
        directory = new Directory(path);
        Assert.assertEquals("El directorio no existe", directory.getError());
    }

    @Test
    public void mustListClasses(){
        path = "..\\herramientaTesting";
        directory = new Directory(path);
        List<String> classes = new ArrayList<>();
        classes.add("Analysis");
        Assert.assertEquals(classes.get(0), directory.listClasses(directory.getFiles().get(0)).get(0));
    }

    @Test
    public void mustListMethods() throws ClassNotFoundException {
        path = "..\\herramientaTesting";
        directory = new Directory(path);
        directory.selectClass(directory.listClasses(directory.getFiles().get(0)).get(0));
        directory.readClass();
        Code method = directory.listMethods().get(0);
        Assert.assertEquals(method.getName(), "countCodelines");
    }
}