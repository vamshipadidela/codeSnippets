package ProjectX;

import ProjectX.X;
import ProjectX.ObjectZ;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

//import java.io.File;
import java.util.Map;

/**
*  Created by Vamshi Padidela 07/01/2016
*  Unit Testing
*  Reflections
*  Change private field
*
*/
public class changePrivateField{
    private XMLParser parser;
    private Map<String,ObjectX> testObjectXMap;
    private String dummyTestXMLPath = "/Users/vamshi/Documents/Workspace/ProjectX/src/conf/config.xml";
    private int expectedNumberOfObjects;
    private String expectedMode;
    
    //parsing the XML file of objects defined into a Map with all the objects
    @Before
    public void setUp() throws Exception {
        parser = new XMLParser();
        Field pathField = parser.getClass().getDeclaredField("FILE_PATH");
        pathField.setAccessible(true);
        pathField.set(parser,dummyTestXMLPath);
        expectedNumberOfObjects = 3;
        expectedMode = "sum";
    }
    
    //compares the expected number of Objects with the number of Objects created through parsing
    @Test
    public void parseXmlObjectCountTest() throws Exception {
        //File f; 
        // choice to create temp file with XML tags and parse on a fly and delete instead of hardcoding
        //f.deleteOnExit();
        parser.parseXmlFile();
        testObjectXMap = parser.getObjectXMap();
        Assert.assertEquals("ObjectX:ParseXMLFile:Checking count of objects returned by XML parsing",expectedNumberOfObjects, testObjectXMap.size());
    }
    
    // Extended example - Checking mode in different class of different object after parsing
    @Test
    public void objectModeParser() throws Exception {
        Field field  = sensor.getX().getClass().getDeclaredField("ObjectZ");
        field.setAccessible(true);
        ObjectZ objectZ = (ObjectZ) field.get(ObjectX.getInspector());
        Field modeField = objectZ.getClass().getDeclaredField("mode");
        modeField.setAccessible(true);
        String mode = (String) modeField.get(objectZ);
        modeField.setAccessible(false);
        field.setAccessible(false);
        Assert.assertEquals("ObjectX:ParseXMLFile: Checking ObjectZ class of different object after parsing",expectedMode,mode);
    }
}
