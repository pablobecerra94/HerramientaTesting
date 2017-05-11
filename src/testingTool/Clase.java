package testingTool;

import java.util.List;

public class Clase {
    private List<Code> methods;
    private String name;

    public Clase(String name) {
        this.name = name;
    }

    public List<Code> getMethods() {
        return methods;
    }

    public void setMethods(List<Code> methods) {
        this.methods = methods;
    }

    public String getName() {
        return name;
    }
}
