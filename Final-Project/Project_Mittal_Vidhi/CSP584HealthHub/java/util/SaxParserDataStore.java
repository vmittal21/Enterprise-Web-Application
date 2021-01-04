package util;


import org.xml.sax.InputSource;

import java.io.IOException;
import java.text.ParseException;
import java.util.*;
import java.io.StringReader;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;
import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;


import bean.Product;


////////////////////////////////////////////////////////////

/**************

SAX parser use callback function  to notify client object of the XML document structure. 
You should extend DefaultHandler and override the method when parsin the XML document

***************/

////////////////////////////////////////////////////////////

public class SaxParserDataStore extends DefaultHandler {
    
    public static HashMap<String, Product> products;
    String consoleXmlFileName;
    String elementValueRead;
	String currentElement="";
	Product product;
    public SaxParserDataStore()
	{
	}
	public SaxParserDataStore(String consoleXmlFileName) {
	    this.consoleXmlFileName = consoleXmlFileName;
		products = new HashMap<>();
		parseDocument();
    }

   //parse the xml using sax parser to get the data
    private void parseDocument() 
	{
        SAXParserFactory factory = SAXParserFactory.newInstance();
        try 
		{
	    SAXParser parser = factory.newSAXParser();
	    parser.parse(consoleXmlFileName, this);
        } catch (ParserConfigurationException e) {
            System.out.println("ParserConfig error");
        } catch (SAXException e) {
            System.out.println("SAXException : xml not well formed");
        } catch (IOException e) {
        	System.out.println(e.getMessage());
            System.out.println("IO error");
        }
	}

   

////////////////////////////////////////////////////////////

/*************

There are a number of methods to override in SAX handler  when parsing your XML document :

    Group 1. startDocument() and endDocument() :  Methods that are called at the start and end of an XML document. 
    Group 2. startElement() and endElement() :  Methods that are called  at the start and end of a document element.  
    Group 3. characters() : Method that is called with the text content in between the start and end tags of an XML document element.


There are few other methods that you could use for notification for different purposes, check the API at the following URL:

https://docs.oracle.com/javase/7/docs/api/org/xml/sax/helpers/DefaultHandler.html

***************/

////////////////////////////////////////////////////////////
	
	// when xml start element is parsed store the id into respective hashmap for console,games etc 
    @Override
    public void startElement(String str1, String str2, String elementName, Attributes attributes) throws SAXException {
    	
    	if(elementName.equals("vitamin") || elementName.equals("medicine") || elementName.equals("personalcare") || elementName.equals("homecare") || elementName.equals("nutrition")) {
    		product = new Product();
        	product.setCatagory(elementName);
        	product.setId(attributes.getValue("id"));
        	product.setInventory(100);
    	}
    	
    	
    }
	// when xml end element is parsed store the data into respective hashmap for console,games etc respectively
    @Override
    public void endElement(String str1, String str2, String element) throws SAXException {
    	if(element.equals("condition")) {
    		product.setCondition(elementValueRead);
    	}
    	if(element.equals("name")) {
    		product.setName(elementValueRead);
    	}
    	if(element.equals("discount")) {
    		product.setDiscount(Double.parseDouble(elementValueRead));
    	}
    	if(element.equals("manufacturer")) {
    		product.setManufacturer(elementValueRead);
    	}
    	                                   
    	if(element.equals("price")) {
    		product.setPrice(Double.parseDouble(elementValueRead));
    	}
    	if(element.equals("image")) {
    		product.setImage(elementValueRead);
    	}
    	products.put(product.getId(), product);
	}
	//get each element in xml tag
    @Override
    public void characters(char[] content, int begin, int end) throws SAXException {
        elementValueRead = new String(content, begin, end);
    }


    /////////////////////////////////////////
    // 	     Kick-Start SAX in main       //
    ////////////////////////////////////////
	
//call the constructor to parse the xml and get product details
 public static void addHashmap() {
	 	String TOMCAT_HOME = System.getProperty("catalina.base");
	 	
		new SaxParserDataStore(TOMCAT_HOME + "//webapps//CSP584HealthHub//data//ProductCatalog.xml");
//	 	new SaxParserDataStore("src/util/ProductCatalog.xml");
    } 
}
