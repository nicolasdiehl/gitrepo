import java.io.IOException;
import java.util.Iterator;
import java.util.List;

import org.jdom2.*;


/*import org.jdom2.Element;
import org.jdom2.output.Format;
import org.jdom2.output.XMLOutputter;*/

public class GenerateXMLDriver {

	  public static void main( String[] args )
	  {
		  System.out.println(GenerateXMLDriver.generateChannelXML());
	  }
	  
	  public static String generateChannelXML(){
		  Document document = new Document ();
		  Element channelElement = new Element ("channel");
		  return null;
	  }
	  public static void generateTopicXML(Element channelElement, String topicname, String[] topicTutorials){
		  
	  }
	}
