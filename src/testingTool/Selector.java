package testingTool;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Selector {

    private List<File> files;

    private File selectedFile;

    private List<String> classes;

    private String error;

    public String getError() {
        return error;
    }

    public void searchFiles(String path){
        File directory = new File(path);
        if(!directory.exists()){
            error = "El directorio no existe";
            return;
        }
        if(!directory.isDirectory()){
            error = "El archivo no es un directorio";
            return;
        }
        files = new ArrayList<>();
        search(directory);
    }

    private void search(File file) {
        if(file.isDirectory()){
            for(int i = 0; i < file.listFiles().length; i++){
                search(file.listFiles()[i]);
            }
        }else{
            if(file.getName().endsWith(".java")){
                files.add(file);
            }
        }
    }

    public List<File> listFiles(String directory){
        searchFiles(directory);
        return files;
    }

    public void selectFile(int file){
        selectedFile = files.get(file);
    }

    public void readSelectedFile(){
        try (Stream<String> lines = Files.lines(Paths.get(selectedFile.toURI()))) {
            classes = lines.filter(line -> line.startsWith("public class")).map(line -> line.split(" ")[2]).collect(Collectors.toList());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public List<String> listClasses(File file){
        selectFile(files.indexOf(file));
        readSelectedFile();
        return classes;
    }
//
//    listMethods(){
//
//    }
//
//    selectMethod(){
//
//    }
}
