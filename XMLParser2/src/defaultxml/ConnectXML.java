package defaultxml;
import java.io.File;
import java.util.Date;
import java.io.IOException;
import java.util.List;

import org.jdom2.Document;
import org.jdom2.Element;
import org.jdom2.JDOMException;
import org.jdom2.input.SAXBuilder;

public class ConnectXML {
	   public static String createUniqueID(String filename) throws JDOMException, IOException{
		      try {  
			         File inputFile = new File(filename);
			         SAXBuilder saxBuilder = new SAXBuilder();
			         Document document = saxBuilder.build(inputFile);
			         Element classElement = document.getRootElement();
			         List<Element> Liste = classElement.getChildren();
			         int idInt=0;
			         int highest=0;
			         String idString="";
			         for (int i = 0; i < Liste.size(); i++) { 
			            Element currentElement = Liste.get(i);

			            idString = currentElement.getAttributeValue("ID");
			            idInt=Integer.parseInt(idString);
			            if (highest < idInt){
			            	highest= idInt;
			            }
			         }
			         
			         highest++;
			         idString= Integer.toString(highest);
		          return idString;
			      }catch(JDOMException e){
			         e.printStackTrace();
			      }
		      return "error: creation of id failed somehow";
	   }
}