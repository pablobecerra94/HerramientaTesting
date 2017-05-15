package testingTool;

import java.util.List;

public class ClaseDamian {
    private List<Code> methods;
    private String name;

    public ClaseDamian(String name) {
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
