package ProjectX;

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
    
    //parsing the XML file of objects defined into a Map with all the objects
    @Before
    public void setUp() throws Exception {
        parser = new XMLParser();
        Field pathField = parser.getClass().getDeclaredField("FILE_PATH");
        pathField.setAccessible(true);
        pathField.set(parser,dummyTestXMLPath);
        expectedNumberOfObjects = 3;
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
    
}
