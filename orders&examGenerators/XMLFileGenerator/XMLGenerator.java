import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Random;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import org.w3c.dom.Document;
import org.w3c.dom.Element;


public class XMLGenerator {

	/**
	 * @param args
	 * @throws IOException 
	 */
	@SuppressWarnings("deprecation")
	public static void main(String[] args) throws IOException {

		try {
			DocumentBuilderFactory docFactory = DocumentBuilderFactory.newInstance();
			DocumentBuilder docBuilder = docFactory.newDocumentBuilder();

			//root elements
			Document doc = docBuilder.newDocument();

			
			Element rootElement = doc.createElement("relatorio");
			doc.appendChild(rootElement);
			
			// set attribute of report
			rootElement.setAttribute("xmlns", "http://saude365.pt");
			rootElement.setAttribute("xmlns:xsi", "http://www.w3.org/2001/XMLSchema-instance");
			rootElement.setAttribute("xsi:schemaLocation", "http://saude365.pt patient-report.xsd ");
			
			
			
			//Data element
			SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
			Date dateOfReport = new Date();
			Element date = doc.createElement("dataHora");
			date.setTextContent(df.format(dateOfReport).replace(" ", "T"));
			rootElement.appendChild(date);
			
			//Doente element
			Element patient = doc.createElement("doente");
			rootElement.appendChild(patient);
			
			//Triagem element
			Element screening = doc.createElement("triagem");
			rootElement.appendChild(screening);
			
			
			//Doente SUB-ELEMENTS
			
			//Nome element
			RandomNameGenerator rng = 
					new RandomNameGenerator("names.txt", "surnames.txt", new Random());
			Element name = doc.createElement("nome");
			String patientName = rng.nextName();
			name.setTextContent(patientName);
			patient.appendChild(name);
			
			//DataNasc element
			GetRandomData grd = 
					new GetRandomData("birthdates.txt", new Random());
			Element birthdate = doc.createElement("dataNasc");
			birthdate.setTextContent(grd.nextName());
			patient.appendChild(birthdate);
			
			//ID element
			Date seed = new Date();
			Random random = new Random(seed.getTime());
			int idNumber = random.nextInt(900000) + 100000;
			Element id = doc.createElement("id");
			id.setTextContent(""+idNumber);
			patient.appendChild(id);

			//SeguroSaude element
			String insurance;
			int insuranceProb = random.nextInt(10);
			if (insuranceProb > 5)
				insurance = "Tem";
			else
				insurance = "Nao tem";
			
			Element healthInsurance = doc.createElement("seguroSaude");
			healthInsurance.setTextContent(insurance);
			patient.appendChild(healthInsurance);
			
			// Telemovel element
			GetRandomData randomMobile =
					new GetRandomData("mobiles.txt", 
										new Random());
			Element mobile = doc.createElement("Telemovel");
			String mobileNumber = randomMobile.nextName();
			mobile.setTextContent(mobileNumber);
			patient.appendChild(mobile);
			
			//Morada element
			Element address = doc.createElement("morada");
			patient.appendChild(address);
			
			//MORADA SUB-ELEMENTS
			AddressRandomGenerator arg = 
					new AddressRandomGenerator("address.txt", 
												new Random());
			String[] addressString = arg.nextAddress().split("/");
			
			
			//CodigoPostal element
			Element postalCode = doc.createElement("codigoPostal");
			postalCode.setTextContent(addressString[0]);
			address.appendChild(postalCode);
			
			//Rua element
			Element street = doc.createElement("rua");
			street.setTextContent(addressString[1]);
			address.appendChild(street);
			
			//Numero element
			Element number = doc.createElement("num");
			number.setTextContent(addressString[2]);
			address.appendChild(number);
			
			//Andar element
			Element floor = doc.createElement("andar");
			if(addressString.length >= 4)
				floor.setTextContent(addressString[3]);
			address.appendChild(floor);
			
			//Apartamento element
			Element appartment = doc.createElement("apartamento");
			if(addressString.length == 5)
				appartment.setTextContent(addressString[4]);
			address.appendChild(appartment);
			
			
			//Triagem SUB-ELEMENTS
			
			//Tipo element
			GetRandomData randomtypes =
					new GetRandomData("ScreeningType.txt", 
										new Random());
			Element type = doc.createElement("tipo");
			type.setTextContent(randomtypes.nextName());
			screening.appendChild(type);
			
			//Nivel element
			GetRandomData randomlevels =
					new GetRandomData("type.txt", 
										new Random());
			Element level = doc.createElement("nivel");
			level.setTextContent(randomlevels.nextName());
			screening.appendChild(level);
			
			//Medicamento element
			int numberOfDrugs = random.nextInt(4) + 1;
			GetRandomData randomDrugs = 
					new GetRandomData("substance.txt", 
							new Random());
			
			
			for (int i = 0; i < numberOfDrugs; i++) {
				Element medication = doc.createElement("medicamento");
				screening.appendChild(medication);
				String[] drugElements = randomDrugs.nextName().split("!");
				
				Element substance = doc.createElement("substancia");
				substance.setTextContent(drugElements[0]);
				medication.appendChild(substance);
				
				Element drugName = doc.createElement("nome");
				drugName.setTextContent(drugElements[1]);
				medication.appendChild(drugName);
				
				Element dose = doc.createElement("dose");
				dose.setTextContent(drugElements[2]);
				medication.appendChild(dose);
			}


			//write the content into xml file
			TransformerFactory transformerFactory = TransformerFactory.newInstance();
			Transformer transformer = transformerFactory.newTransformer();
			DOMSource source = new DOMSource(doc);
			
			StreamResult result =  new StreamResult(new File("/tmp/patients/" + patientName.replace(" ", "_") + ".xml"));
			transformer.transform(source, result);

			System.out.println("Report received: report-" + patientName.replace(" ", "_") + ".xml");

		}catch(ParserConfigurationException pce){
			pce.printStackTrace();
		}catch(TransformerException tfe){
			tfe.printStackTrace();
		}
	}

}


