package testingTool;

import com.sun.org.apache.bcel.internal.classfile.Code;

/**
 * Created by Shorshi on 03/05/2017.
 */
public class Analysis {
    private Code method;

    public Analysis(Code method) {
        this.method = method;
    }


    public int countCodelines() {
        return method.getLength();
    }
}
