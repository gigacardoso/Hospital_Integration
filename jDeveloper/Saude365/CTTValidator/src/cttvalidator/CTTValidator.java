package cttvalidator;

import java.io.*;
import java.net.*;

import javax.jws.WebMethod;
import javax.jws.WebService;

import javax.xml.ws.BindingType;
import javax.xml.ws.soap.SOAPBinding;

@WebService(portName = "CTTValidatorSoap12HttpPort")
@BindingType(SOAPBinding.SOAP12HTTP_BINDING)
public class CTTValidator {
    
    public CTTValidator(){
        super();
        }

    public String validate(String arg) throws Exception {
        
        final String address = "http://www.ctt.pt/pdcp/xml_pdcp?incodpos=" + arg;
        System.out.println("Posting to " + address);

        // Construct data
        //String data = URLEncoder.encode("name", "UTF-8") + "=" + URLEncoder.encode("friend", "UTF-8");
        //data += "&" + URLEncoder.encode("key2", "UTF-8") + "=" + URLEncoder.encode("value2", "UTF-8");

        // Send data
        URL url = new URL(address);
        URLConnection conn = url.openConnection();
        conn.setDoOutput(true);
        // No caching
        conn.setUseCaches (false);

        // Get the response
        int responseCode = ((HttpURLConnection)conn).getResponseCode();
        //System.out.println("Response code is " + responseCode);

        BufferedReader rd = new BufferedReader(new InputStreamReader(conn.getInputStream()));
        String line;
        //System.out.println("Response:");
        while ((line = rd.readLine()) != null) {
                if (!line.contains("?xml")) {
                        if(line.contains("OK")) {
                            rd.close();
                            return "VALIDO!";
                            //System.out.println(line);
                        } else {
                            rd.close();
                                return "INVALIDO!";
                                //System.out.println(line);
                        }
                }
        }
        rd.close();
        return "INVALIDO!";
    }

    @WebMethod(exclude = true)
    public String validate1(String args[]) throws Exception {
    	
        final String address = "http://www.ctt.pt/pdcp/xml_pdcp?incodpos=" + args[0];
        System.out.println("Posting to " + address);

        // Construct data
        //String data = URLEncoder.encode("name", "UTF-8") + "=" + URLEncoder.encode("friend", "UTF-8");
        //data += "&" + URLEncoder.encode("key2", "UTF-8") + "=" + URLEncoder.encode("value2", "UTF-8");

        // Send data
        URL url = new URL(address);
        URLConnection conn = url.openConnection();
        conn.setDoOutput(true);
        // No caching
        conn.setUseCaches (false);

        // Get the response
        int responseCode = ((HttpURLConnection)conn).getResponseCode();
        //System.out.println("Response code is " + responseCode);

        BufferedReader rd = new BufferedReader(new InputStreamReader(conn.getInputStream()));
        String line;
        //System.out.println("Response:");
        while ((line = rd.readLine()) != null) {
        	if (!line.contains("?xml")) {
        		if(line.contains("OK")) {
                            rd.close();
                            return "VALIDO!";
                            //System.out.println(line);
                        } else {
                            rd.close();
                                return "INVALIDO!";
                                //System.out.println(line);
                        }
        	}
            if(args.length > 1) {
                FileWriter fw = new FileWriter(responseCode + "-" + args[1], true);
                fw.write(line);
                fw.write(System.getProperty("line.separator"));
                fw.close();
            }
        }
        rd.close();
        return "INVALIDO!";
    }
}
