import javax.xml.parsers.*;
import javax.xml.stream.XMLOutputFactory;
import javax.xml.stream.XMLStreamWriter;

import org.xml.sax.*;
import org.w3c.dom.*;
import java.io.*;
import java.util.*;
import  javax.xml.stream.*;


public class DVDReader {
    private HashMap <Integer, Integer> release_year = new HashMap();

    public void read(String filepath) {
        DocumentBuilderFactory dbf  = DocumentBuilderFactory.newInstance();
        dbf.setValidating(true);

        DocumentBuilder db = null;
        try {
            db =  dbf.newDocumentBuilder();
            db.setErrorHandler(new  ErrorHandler() {
                public void error(SAXParseException spe) {
                    System.err.println(spe);
                }
                public void fatalError(SAXParseException spe){
                    System.err.println(spe);
                }
                public void warning(SAXParseException spe) {
                    System.out.println(spe);
                }
            });
        } catch (ParserConfigurationException  pce) {
            System.err.println(pce);
            System.exit(1);
        }

        Document doc = null;
        try {
            doc = db.parse(new File(filepath));
        }  catch (SAXException  se) {
            System.err.println(se);
        }  catch (IOException  ioe) {
            System.err.println(ioe);
        }
        NodeList nodeList =  doc.getDocumentElement().getChildNodes();
        EchoNodes(nodeList);
        summary(this.release_year);
    }


    //check whether a string can be converted to a number
    public static boolean isNumeric(String str){
        return str.matches("-?\\d+");
    };

    //add year to the releaseyear hashmap
    public void addYear(int year){
        if (this.release_year.containsKey(year)) {
            Integer value = this.release_year.get(year);
            int newValue = value + 1;
            this.release_year.put(year, newValue);
        }else{
            this.release_year.put(year,1);
        }
    }


    public void  EchoNodes(NodeList nodeList) {
        if (nodeList == null)  return;
        for (int i = 0; i <  nodeList.getLength(); i++) {
            Node child_node = nodeList.item(i);
             if  (child_node.getNodeType() == Node.TEXT_NODE) {
                Text tn = (Text)child_node;
                String text = tn.getWholeText().trim();
                //int num = Integer.parseInt(text);
               if(text.length()>0) {
                   //check if it is integer to get year
                   if(isNumeric(text)){
                       int num = Integer.parseInt(text);
                       // check if it is year of number of disc, put year decade into arraylist.
                       if(num > 1000){
                           int year = (num/10)*10;
                           addYear(year);

                       }

                   }
               }
            }

            EchoNodes(child_node.getChildNodes());
        }

    }


    //print out the resualt
    public void summary(HashMap map){
        XMLStreamWriter xw = null;
        XMLOutputFactory xof = XMLOutputFactory.newInstance();

        try{
            xw = xof.createXMLStreamWriter(new FileWriter("summary.xml"));
            xw.writeStartDocument("1.0");
            xw.writeCharacters("\n");
            xw.writeStartElement("DVD");
            xw.writeCharacters("\n");
            xw.writeStartElement("Summary");
            xw.writeCharacters("\n");


            //summary info
            Set<Integer> keys = release_year.keySet();
            for(Integer i: keys) {
                xw.writeCharacters("    ");
                xw.writeStartElement("count decade = " + Integer.toString(i));
                xw.writeCharacters(Integer.toString(release_year.get(i)));
                xw.writeEndElement();
                xw.writeCharacters("\n");

            }

            xw.writeEndElement();
            xw.writeCharacters("\n");
            xw.writeEndElement();
            xw.writeEndDocument();

            xw.flush();
            xw.close();


        }catch  (XMLStreamException e) {
            e.printStackTrace();
        } catch (IOException e)  {
            e.printStackTrace();
        }

    }

    public static void  main(String[] args) {
        DVDReader domDVDReader  = new DVDReader();
        domDVDReader.read("dvd.xml");
    }
}