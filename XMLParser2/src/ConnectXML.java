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
			         int tempi=0;
			         int highest=0;
			         String temps="";
			         for (int temp = 0; temp < Liste.size(); temp++) { 
			            Element Vorgang = Liste.get(temp);

			            temps= Vorgang.getAttributeValue("ID");
			            tempi=Integer.parseInt(temps);
			            if (highest < tempi){
			            	highest= tempi;
			            }
			         }
			         
			         highest++;
			         temps= Integer.toString(highest);
		          return temps;
			      }catch(JDOMException e){
			         e.printStackTrace();
			      }
		      return "random test value";
	   }
}