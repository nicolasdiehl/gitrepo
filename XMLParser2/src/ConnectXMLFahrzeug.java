import java.io.File;
import java.io.IOException;
import java.util.List;

import org.jdom2.*;
import org.jdom2.Attribute;
import org.jdom2.Document;
import org.jdom2.Element;
import org.jdom2.input.SAXBuilder;
import org.jdom2.JDOMException;
import org.jdom2.output.Format;
import org.jdom2.output.XMLOutputter;

public class ConnectXMLFahrzeug extends ConnectXML{
//	   public static void main(String[] args) throws JDOMException{
//		 // einfügenFahrzeug();
//	   }
	   
	   public static void readFahrzeugListe(){
	      try {
	         File inputFile = new File("Fahrzeug.xml");
	         SAXBuilder saxBuilder = new SAXBuilder();
	         Document document = saxBuilder.build(inputFile);
	         System.out.println("Root element :" + document.getRootElement().getName());

	         Element classElement = document.getRootElement();

	         List<Element> fuhrparkList = classElement.getChildren();
	         System.out.println("----------------------------");

	         for (int temp = 0; temp < fuhrparkList.size(); temp++) {    
	            Element fuhrp = fuhrparkList.get(temp);
	            System.out.println("\n Current Element :" + fuhrp.getName());
	            Attribute attribute =  fuhrp.getAttribute("ID");
	            System.out.println("Fuhrpark roll no : " + attribute.getValue() );
	            System.out.println("Marke :      " + fuhrp.getChild("Marke").getText());
	            System.out.println("Modell:      "+ fuhrp.getChild("Modell").getText());
	            System.out.println("Ausstattung: "+ fuhrp.getChild("Ausstattung").getText());
	         }
	      }catch(JDOMException e){
	         e.printStackTrace();
	      }catch(IOException ioe){
	         ioe.printStackTrace();
	      }
	   }
	   
	   public static void einfügenFahrzeug(String marke, String modell, String ausstattung) throws JDOMException{
		   try {
		         File inputFile = new File("Fahrzeug.xml");
		         SAXBuilder saxBuilder = new SAXBuilder();
		         Document document = saxBuilder.build(inputFile);
		         String tempID= createUniqueID("Fahrzeug.xml");
		         Element neuesAuto= new Element ("Fahrzeug");
		         neuesAuto.setAttribute(new Attribute ("ID", tempID));
		         Element neuesAutoMarke= new Element ("Marke");
		         neuesAutoMarke.setText(marke);
		         Element neuesAutoModell= new Element ("Modell");
		         neuesAutoModell.setText(modell);
		         Element neuesAutoAusstattung= new Element ("Ausstattung");
		         neuesAutoAusstattung.setText(ausstattung);
		            
		         neuesAuto.addContent(neuesAutoMarke);
		         neuesAuto.addContent(neuesAutoModell);
		         neuesAuto.addContent(neuesAutoAusstattung);

		         document.getRootElement().addContent(neuesAuto);

		         XMLOutputter xmlOutput = new XMLOutputter();

		         // display ml
		         xmlOutput.setFormat(Format.getPrettyFormat());
		         xmlOutput.output(document, System.out); 
		      }catch(IOException e){
		         e.printStackTrace();
		      }	
		   }//schließt Methode	   
	   
//	   public static String createUniqueID() throws JDOMException, IOException{
//		      try {  
//			         File inputFile = new File("Fahrzeug.xml");
//			         SAXBuilder saxBuilder = new SAXBuilder();
//			         Document document = saxBuilder.build(inputFile);
//			         Element classElement = document.getRootElement();
//			         List<Element> Fahrzeugliste = classElement.getChildren();
//			         int tempi=0;
//			         int highest=0;
//			         String temps="";
//			         for (int temp = 0; temp < Fahrzeugliste.size(); temp++) { 
//			            Element Vorgang = Fahrzeugliste.get(temp);
//
//			            temps= Vorgang.getAttributeValue("ID");
//			            tempi=Integer.parseInt(temps);
//			            if (highest < tempi){
//			            	highest= tempi;
//			            }
//			         }
//			         
//			         highest++;
//			         temps= Integer.toString(highest);
//		          return temps;
//			      }catch(JDOMException e){
//			         e.printStackTrace();
//			      }
//		      return "random test value";
//	   }
}