package testingTool;

import org.junit.Assert;
import org.junit.Test;


public class AnalysisTest {
    Analysis analysis;

    @Test
    public void mustReadOnlyCodelines(){
        Directory directory = new Directory("..\\herramientaTesting");
//        analysis = new Analysis(directory.getSelectedMethod());
        Assert.assertEquals(170, analysis.countCodelines());
    }
}