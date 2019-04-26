/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package consumewebservices;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.StringReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.soap.MessageFactory;
import javax.xml.soap.MimeHeaders;
import javax.xml.soap.SOAPBody;
import javax.xml.soap.SOAPConnection;
import javax.xml.soap.SOAPConnectionFactory;
import javax.xml.soap.SOAPElement;
import javax.xml.soap.SOAPEnvelope;
import javax.xml.soap.SOAPException;
import javax.xml.soap.SOAPMessage;
import javax.xml.soap.SOAPPart;
import org.w3c.dom.*;
import org.xml.sax.InputSource;

/**
 *
 * @author pc
 */
public class ConsumeSoapService3 {
      public static void main(String args[]) {
        /*
            The example below requests from the Web Service at:
             https://www.w3schools.com/xml/tempconvert.asmx?op=CelsiusToFahrenheit


            To call other WS, change the parameters below, which are:
             - the SOAP Endpoint URL (that is, where the service is responding from)
             - the SOAP Action

            Also change the contents of the method createSoapEnvelope() in this class. It constructs
             the inner part of the SOAP envelope that is actually sent.
         */
        String soapEndpointUrl = "http://localhost:8080/CreateSoapService/Calculator?wsdl";
        String soapAction = "http://CreateSoapService/Calculator";

        callSoapWebService(soapEndpointUrl, soapAction);
    }

    private static void createSoapEnvelope(SOAPMessage soapMessage) throws SOAPException {
        SOAPPart soapPart = soapMessage.getSOAPPart();

        String myNamespace = "Calculator";
        String myNamespaceURI = "http://soap.java.com/";

        // SOAP Envelope
        SOAPEnvelope envelope = soapPart.getEnvelope();
        envelope.addNamespaceDeclaration(myNamespace, myNamespaceURI);

            /*
            Constructed SOAP Request Message:
            <SOAP-ENV:Envelope xmlns:SOAP-ENV="http://schemas.xmlsoap.org/soap/envelope/" xmlns:myNamespace="https://www.w3schools.com/xml/">
                <SOAP-ENV:Header/>
                <SOAP-ENV:Body>
                    <myNamespace:CelsiusToFahrenheit>
                        <myNamespace:Celsius>100</myNamespace:Celsius>
                    </myNamespace:CelsiusToFahrenheit>
                </SOAP-ENV:Body>
            </SOAP-ENV:Envelope>
            */

        // SOAP Body
        SOAPBody soapBody = envelope.getBody();
        SOAPElement soapBodyElem = soapBody.addChildElement("Add", myNamespace);
        SOAPElement soapBodyElem1 = soapBodyElem.addChildElement("arg0");
        SOAPElement soapBodyElem2 = soapBodyElem.addChildElement("arg1");
        soapBodyElem1.addTextNode("135");
        soapBodyElem2.addTextNode("265");
    }

    private static void callSoapWebService(String soapEndpointUrl, String soapAction) {
        try {
            // Create SOAP Connection
            SOAPConnectionFactory soapConnectionFactory = SOAPConnectionFactory.newInstance();
            SOAPConnection soapConnection = soapConnectionFactory.createConnection();

            // Send SOAP Message to SOAP Server
            SOAPMessage soapResponse = soapConnection.call(createSOAPRequest(soapAction), soapEndpointUrl);

            // Print the SOAP Response
        
            /*    System.out.println("Response SOAP Message:");        
            soapResponse.writeTo(System.out);
            System.out.println(); */
List<String> output = getFullNameFromXml(soapMessageToString(soapResponse), "return");
String[] strarray = new String[output.size()];
output.toArray(strarray);
System.out.print("Response Array is "+Arrays.toString(strarray));
            
            soapConnection.close();
        } catch (Exception e) {
            System.err.println("\nError occurred while sending SOAP Request to Server!\nMake sure you have the correct endpoint URL and SOAPAction!\n");
            e.printStackTrace();
        }
    }

    private static SOAPMessage createSOAPRequest(String soapAction) throws Exception {
        MessageFactory messageFactory = MessageFactory.newInstance();
        SOAPMessage soapMessage = messageFactory.createMessage();

        createSoapEnvelope(soapMessage);

        MimeHeaders headers = soapMessage.getMimeHeaders();
        headers.addHeader("SOAPAction", soapAction);

        soapMessage.saveChanges();

        /* Print the request message, just for debugging purposes */
        System.out.println("Request SOAP Message:");
        soapMessage.writeTo(System.out);
        System.out.println("\n");  

/*              QName bodyName = new QName("http://YourSOAPServer.com", "GetQuote", "d");
         SOAPBody sb = soapMessage.getSOAPBody();
      java.util.Iterator iterator = sb.getChildElements(bodyName);
      while (iterator.hasNext()) {
        SOAPBodyElement bodyElement = (SOAPBodyElement) iterator.next();
        String val = bodyElement.getValue();
        System.out.println("The Value is:" + val);
      }*/
        
        return soapMessage;
    }

   public static Document loadXMLString(String response) throws Exception
{
    DocumentBuilderFactory dbf =DocumentBuilderFactory.newInstance();
    DocumentBuilder db = dbf.newDocumentBuilder();
    InputSource is = new InputSource(new StringReader(response));

    return db.parse(is);
}

public static List<String> getFullNameFromXml(String response, String tagName) throws Exception {
    Document xmlDoc = loadXMLString(response);
    NodeList nodeList = xmlDoc.getElementsByTagName(tagName);
    List<String> ids = new ArrayList<String>(nodeList.getLength());
    for(int i=0;i<nodeList.getLength(); i++) {
        Node x = nodeList.item(i);
        ids.add(x.getFirstChild().getNodeValue());             
        System.out.println(nodeList.item(i).getFirstChild().getNodeValue());
    }
    return ids;
}

   public static String soapMessageToString(SOAPMessage message) 
    {
        String result = null;

        if (message != null) 
        {
            ByteArrayOutputStream baos = null;
            try 
            {
                baos = new ByteArrayOutputStream();
                message.writeTo(baos); 
                result = baos.toString();
            } 
            catch (Exception e) 
            {
            } 
            finally 
            {
                if (baos != null) 
                {
                    try 
                    {
                        baos.close();
                    } 
                    catch (IOException ioe) 
                    {
                    }
                }
            }
        }
        return result;
    } 
}
