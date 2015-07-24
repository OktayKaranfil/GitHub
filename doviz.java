package ViewController;

import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.DocumentBuilder;
import org.w3c.dom.Document;
import org.w3c.dom.NodeList;
import org.w3c.dom.Node;
import org.w3c.dom.Element;
import java.net.URL;
import java.net.URLConnection;
import java.io.InputStream;
import java.io.BufferedInputStream;

public class doviz
{
    public static void main(String[] args) 
    {
        foodRecord[] arrayOfRecords = null;
        try
        {
            URL url = new URL("http://www.tcmb.gov.tr/kurlar/today.xml");
            URLConnection fxml = url.openConnection();
            InputStream inFile = new BufferedInputStream(fxml.getInputStream());
            DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
            Document doc = dBuilder.parse(inFile);
            
            doc.getDocumentElement().normalize();
            NodeList nList = doc.getElementsByTagName("Currency");
            foodRecord[] tempArray = new foodRecord[nList.getLength()];
            
            for(int temp = 0; temp < nList.getLength(); temp++)
            {
                Node nNode = nList.item(temp);
                
                if(nNode.getNodeType() == Node.ELEMENT_NODE)
                {
                    Element eElement = (Element) nNode;
                    foodRecord newRecord = new foodRecord();
                    
                    
                    
                    newRecord.name = eElement.getElementsByTagName("Isim").item(0).getTextContent();
                    newRecord.ForexBuying = eElement.getElementsByTagName("ForexBuying").item(0).getTextContent();
                    newRecord.ForexSelling = eElement.getElementsByTagName("ForexSelling").item(0).getTextContent();
                    newRecord.BanknoteSelling = eElement.getElementsByTagName("BanknoteSelling").item(0).getTextContent();
                    newRecord.BanknoteBuying = eElement.getElementsByTagName("BanknoteBuying").item(0).getTextContent();
                    tempArray[temp] = newRecord;
                }
            }
            
            arrayOfRecords = tempArray;
        }
        catch (Exception e) {e.printStackTrace();}
        
        for(int k = 0; k < arrayOfRecords.length; k++)
        {
            
            System.out.println(arrayOfRecords[k].name);
            System.out.println(arrayOfRecords[k].ForexBuying);
            System.out.println(arrayOfRecords[k].ForexSelling);
            System.out.println(arrayOfRecords[k].BanknoteSelling);
            System.out.println(arrayOfRecords[k].BanknoteBuying + "\n");
        }
    }
    
    static class foodRecord
    {
        String name;
        String ForexBuying;
        String ForexSelling;
        String BanknoteSelling;
        String BanknoteBuying;
    }
}