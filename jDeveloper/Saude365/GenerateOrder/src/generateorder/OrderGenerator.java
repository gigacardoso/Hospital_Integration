package generateorder;

import java.io.File;

import java.math.BigDecimal;

import java.text.SimpleDateFormat;

import java.util.Date;
import java.util.List;
import java.util.Random;

import javax.jws.Oneway;
import javax.jws.WebMethod;
import javax.jws.WebService;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerConfigurationException;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import javax.xml.ws.BindingType;
import javax.xml.ws.soap.SOAPBinding;

import org.w3c.dom.Document;
import org.w3c.dom.Element;


@WebService(portName = "OrderGeneratorSoap12HttpPort")
@BindingType(SOAPBinding.SOAP12HTTP_BINDING)
public class OrderGenerator {

    private static int number = 1;
    @WebMethod
    @Oneway
    public void generateOrder(Integer orderNumber, List<String> drugs, List<Integer> quantities, List<BigDecimal> prices) {
        DocumentBuilderFactory docFactory = DocumentBuilderFactory.newInstance();
                        DocumentBuilder docBuilder;
                        try {
                                SimpleDateFormat dt1 = new SimpleDateFormat("yyyy-MM-dd");
                                BigDecimal finalAmount = new BigDecimal(0.0);   
                                Element lineExtAmount;
                                Element payableAmount;

                                docBuilder = docFactory.newDocumentBuilder();

                                //root element
                                Document doc = docBuilder.newDocument();
                                Element rootElement = doc.createElement("Order");
                                doc.appendChild(rootElement);

                                // set attributes of Order
                                rootElement.setAttribute("xmlns", "urn:oasis:names:specification:ubl:schema:xsd:Order-2");
                                rootElement.setAttribute("xmlns:cac", "urn:oasis:names:specification:ubl:schema:xsd:CommonAggregateComponents-2");
                                rootElement.setAttribute("xmlns:cbc", "urn:oasis:names:specification:ubl:schema:xsd:CommonBasicComponents-2");
                                rootElement.setAttribute("xmlns:ccts", "urn:oasis:names:specification:ubl:schema:xsd:CoreComponentParameters-2");
                                rootElement.setAttribute("xmlns:qdt", "urn:oasis:names:specification:ubl:schema:xsd:QualifiedDatatypes-2");                     
                                rootElement.setAttribute("xmlns:udt", "urn:un:unece:uncefact:data:draft:UnqualifiedDataTypesSchemaModule:2");                   


                                // cbc:UBLVersionID
                                Element versionID = doc.createElement("cbc:UBLVersionID");
                                versionID.setTextContent("2.0");
                                rootElement.appendChild(versionID);

                                // cbc:CustomizationID
                                Element customizationID = doc.createElement("cbc:CustomizationID");
                                customizationID.setTextContent("urn:oasis:names:specification:ubl:xpath:Order-2.0:sbs-1.0-draft");
                                rootElement.appendChild(customizationID);                       

                                // cbc:ProfileID
                                Element profileID = doc.createElement("cbc:ProfileID");
                                profileID.setTextContent("bpid:urn:oasis:names:draft:bpss:ubl-2-sbs-order-with-simple-response-draft");
                                rootElement.appendChild(profileID);

                                // cbc:ID
                                Element ID = doc.createElement("cbc:ID");
                                ID.setTextContent("AEG012345");
                                rootElement.appendChild(ID);                    

                                // cbc:SalesOrderID
                                Element salesOrderID = doc.createElement("cbc:SalesOrderID");
                                salesOrderID.setTextContent("CON0095678");
                                rootElement.appendChild(salesOrderID);                                  

                                // cbc:CopyIndicator
                                Element copyIndicator = doc.createElement("cbc:CopyIndicator");
                                copyIndicator.setTextContent("false");
                                rootElement.appendChild(copyIndicator);         

                                // cbc:UUID
                                Element UUID = doc.createElement("cbc:UUID");
                                UUID.setTextContent("6E09886B-DC6E-439F-82D1-7CCAC7F4E3B1");
                                rootElement.appendChild(UUID);          

                                // cbc:IssueDate
                                Element issueDate = doc.createElement("cbc:IssueDate");
                                Date date = new Date();
                                issueDate.setTextContent(dt1.format(date));
                                rootElement.appendChild(issueDate);                     

                                // cac:BuyerCustomerParty
                                Element buyerCustomerParty = doc.createElement("cac:BuyerCustomerParty");
                                rootElement.appendChild(buyerCustomerParty);

                                {
                                        // cbc:CustomerAssignedAccountID
                                        Element customerAssignedAccountID = doc.createElement("cbc:CustomerAssignedAccountID");
                                        customerAssignedAccountID.setTextContent("XFB01");
                                        buyerCustomerParty.appendChild(customerAssignedAccountID);

                                        // cbc:SupplierAssignedAccountID
                                        Element supplierAssignedAccountID = doc.createElement("cbc:SupplierAssignedAccountID");
                                        supplierAssignedAccountID.setTextContent("GT00978567");
                                        buyerCustomerParty.appendChild(supplierAssignedAccountID);

                                        // cac:Party
                                        Element party = doc.createElement("cac:Party");
                                        buyerCustomerParty.appendChild(party);
                                        {
                                                // cac:PartyName
                                                Element partyName = doc.createElement("cac:PartyName");
                                                party.appendChild(partyName);
                                                {
                                                        // cbc:Name
                                                        Element name = doc.createElement("cbc:Name");
                                                        name.setTextContent("Hospital Sao Nuno");
                                                        partyName.appendChild(name);
                                                }

                                                // cac:PostalAddress
                                                Element postalAddress = doc.createElement("cac:PostalAddress");
                                                party.appendChild(postalAddress);
                                                {
                                                        // cbc:StreetName
                                                        Element streetName = doc.createElement("cbc:StreetName");
                                                        streetName.setTextContent("Av. Rovisco Pais");
                                                        postalAddress.appendChild(streetName);

                                                        // cbc:BuildingNumber
                                                        Element buildingNumber = doc.createElement("cbc:BuildingNumber");
                                                        buildingNumber.setTextContent("1");
                                                        postalAddress.appendChild(buildingNumber);

                                                        // cbc:CityName
                                                        Element cityName = doc.createElement("cbc:CityName");
                                                        cityName.setTextContent("Lisboa");
                                                        postalAddress.appendChild(cityName);

                                                        // cbc:PostalZone
                                                        Element postalZone = doc.createElement("cbc:PostalZone");
                                                        postalZone.setTextContent("1049-001");
                                                        postalAddress.appendChild(postalZone);

                                                        // cac:Country
                                                        Element country = doc.createElement("cac:Country");
                                                        postalAddress.appendChild(country);
                                                        {
                                                                // cbc:IdentificationCode
                                                                Element idCode = doc.createElement("cbc:IdentificationCode");
                                                                idCode.setTextContent("PT");
                                                                country.appendChild(idCode);
                                                        }
                                                }

                                                // cac:PartyTaxScheme
                                                Element partyTaxScheme = doc.createElement("cac:PartyTaxScheme");
                                                party.appendChild(partyTaxScheme);
                                                {
                                                        // cbc:CompanyID
                                                        Element companyID = doc.createElement("cbc:CompanyID");
                                                        companyID.setTextContent("12356478");
                                                        partyTaxScheme.appendChild(companyID);

                                                        // cac:TaxScheme
                                                        Element taxScheme = doc.createElement("cac:TaxScheme");
                                                        partyTaxScheme.appendChild(taxScheme);
                                                        {
                                                                // cbc:ID
                                                                Element id = doc.createElement("cbc:ID");
                                                                id.setTextContent("PT VAT");
                                                                taxScheme.appendChild(id);

                                                                // cbc:TaxTypeCode
                                                                Element taxTypeCode = doc.createElement("cbc:TaxTypeCode");
                                                                taxTypeCode.setTextContent("VAT");
                                                                taxScheme.appendChild(taxTypeCode);
                                                        }

                                                }

                                                // cac:Contact
                                                Element contact = doc.createElement("cac:Contact");
                                                party.appendChild(contact);
                                                {
                                                        // cbc:Name
                                                        Element name = doc.createElement("cbc:Name");
                                                        name.setTextContent("Nuno Silva");
                                                        contact.appendChild(name);

                                                        // cbc:Telephone
                                                        Element telephone = doc.createElement("cbc:Telephone");
                                                        telephone.setTextContent("916367234");
                                                        contact.appendChild(telephone);

                                                        // cbc:ElectronicMail
                                                        Element mail = doc.createElement("cbc:ElectronicMail");
                                                        mail.setTextContent("nuno.silva@hsn.pt");
                                                        contact.appendChild(mail);
                                                }
                                        }
                                }

                                // cac:SellerSupplierParty
                                Element sellerSuplierParty = doc.createElement("cac:SellerSupplierParty");
                                rootElement.appendChild(sellerSuplierParty);

                                {
                                        // cbc:CustomerAssignedAccountID
                                        Element customerAssignedAccountID = doc.createElement("cbc:CustomerAssignedAccountID");
                                        customerAssignedAccountID.setTextContent("CO001");
                                        sellerSuplierParty.appendChild(customerAssignedAccountID);

                                        // cac:Party
                                        Element party = doc.createElement("cac:Party");
                                        buyerCustomerParty.appendChild(party);
                                        {
                                                // cac:PartyName
                                                Element partyName = doc.createElement("cac:PartyName");
                                                party.appendChild(partyName);
                                                {
                                                        // cbc:Name
                                                        Element name = doc.createElement("cbc:Name");
                                                        name.setTextContent("BIOSAUDE, PRODUTOS FARMACEUTICOS, LDA");
                                                        partyName.appendChild(name);
                                                }

                                                // cac:PostalAddress
                                                Element postalAddress = doc.createElement("cac:PostalAddress");
                                                party.appendChild(postalAddress);
                                                {
                                                        // cbc:StreetName
                                                        Element streetName = doc.createElement("cbc:StreetName");
                                                        streetName.setTextContent("Avenida Jose Malhoa");
                                                        postalAddress.appendChild(streetName);

                                                        // cbc:BuildingNumber
                                                        Element buildingNumber = doc.createElement("cbc:BuildingNumber");
                                                        buildingNumber.setTextContent("2");
                                                        postalAddress.appendChild(buildingNumber);

                                                        // cbc:CityName
                                                        Element cityName = doc.createElement("cbc:CityName");
                                                        cityName.setTextContent("Lisboa");
                                                        postalAddress.appendChild(cityName);

                                                        // cbc:PostalZone
                                                        Element postalZone = doc.createElement("cbc:PostalZone");
                                                        postalZone.setTextContent("1070-325");
                                                        postalAddress.appendChild(postalZone);

                                                        // cac:Country
                                                        Element country = doc.createElement("cac:Country");
                                                        postalAddress.appendChild(country);
                                                        {
                                                                // cbc:IdentificationCode
                                                                Element idCode = doc.createElement("cbc:IdentificationCode");
                                                                idCode.setTextContent("PT");
                                                                country.appendChild(idCode);
                                                        }
                                                }

                                                // cac:PartyTaxScheme
                                                Element partyTaxScheme = doc.createElement("cac:PartyTaxScheme");
                                                party.appendChild(partyTaxScheme);
                                                {
                                                        // cbc:CompanyID
                                                        Element companyID = doc.createElement("cbc:CompanyID");
                                                        companyID.setTextContent("175 269 2355");
                                                        partyTaxScheme.appendChild(companyID);

                                                        // cac:TaxScheme
                                                        Element taxScheme = doc.createElement("cac:TaxScheme");
                                                        partyTaxScheme.appendChild(taxScheme);
                                                        {
                                                                // cbc:ID
                                                                Element id = doc.createElement("cbc:ID");
                                                                id.setTextContent("VAT");
                                                                taxScheme.appendChild(id);

                                                                // cbc:TaxTypeCode
                                                                Element taxTypeCode = doc.createElement("cbc:TaxTypeCode");
                                                                taxTypeCode.setTextContent("VAT");
                                                                taxScheme.appendChild(taxTypeCode);
                                                        }

                                                }

                                                // cac:Contact
                                                Element contact = doc.createElement("cac:Contact");
                                                party.appendChild(contact);
                                                {
                                                        // cbc:Name
                                                        Element name = doc.createElement("cbc:Name");
                                                        name.setTextContent("Daniel Cardoso");
                                                        contact.appendChild(name);

                                                        // cbc:Telephone
                                                        Element telephone = doc.createElement("cbc:Telephone");
                                                        telephone.setTextContent("916437138");
                                                        contact.appendChild(telephone);

                                                        // cbc:ElectronicMail
                                                        Element mail = doc.createElement("cbc:ElectronicMail");
                                                        mail.setTextContent("daniel.cardoso@biosaude.pt");
                                                        contact.appendChild(mail);
                                                }
                                        }
                                }

                                // cac:Delivery
                                Element delivery = doc.createElement("cac:Delivery");
                                rootElement.appendChild(delivery);
                                {
                                        // cac:DeliveryAddress
                                        Element deliveryAddress = doc.createElement("cac:DeliveryAddress");
                                        delivery.appendChild(deliveryAddress);
                                        {
                                                // cbc:StreetName
                                                Element streetName = doc.createElement("cbc:StreetName");
                                                streetName.setTextContent("Av. Rovisco Pais");
                                                deliveryAddress.appendChild(streetName);

                                                // cbc:BuildingNumber
                                                Element buildingNumber = doc.createElement("cbc:BuildingNumber");
                                                buildingNumber.setTextContent("1");
                                                deliveryAddress.appendChild(buildingNumber);

                                                // cbc:CityName
                                                Element cityName = doc.createElement("cbc:CityName");
                                                cityName.setTextContent("Lisboa");
                                                deliveryAddress.appendChild(cityName);

                                                // cbc:PostalZone
                                                Element postalZone = doc.createElement("cbc:PostalZone");
                                                postalZone.setTextContent("1049-001");
                                                deliveryAddress.appendChild(postalZone);

                                                // cac:Country
                                                Element country = doc.createElement("cac:Country");
                                                deliveryAddress.appendChild(country);
                                                {
                                                        // cbc:IdentificationCode
                                                        Element idCode = doc.createElement("cbc:IdentificationCode");
                                                        idCode.setTextContent("PT");
                                                        country.appendChild(idCode);
                                                }
                                        }

                                        // cac:RequestedDeliveryPeriod
                                        Element requestedDeliveryPeriod = doc.createElement("cac:RequestedDeliveryPeriod");
                                        delivery.appendChild(requestedDeliveryPeriod);
                                        {
                                                // cbc:StartDate
                                                Element startDate = doc.createElement("cbc:StartDate");
                                                startDate.setTextContent(dt1.format(date));
                                                requestedDeliveryPeriod.appendChild(startDate);

                                                // cbc:StartTime
                                                Element startTime = doc.createElement("cbc:StartTime");
                                                Integer hours = date.getHours();
                                                Integer minutes = date.getMinutes();
                                                Integer seconds = date.getSeconds();
                                                startTime.setTextContent(hours + ":" + minutes + ":" + seconds + ".0Z");
                                                requestedDeliveryPeriod.appendChild(startTime);

                                                // cbc:EndDate
                                                Element endDate = doc.createElement("cbc:EndDate");
                                                date.setDate(date.getDay()+2);
                                                endDate.setTextContent(dt1.format(date));
                                                requestedDeliveryPeriod.appendChild(endDate);

                                                // cbc:EndTime
                                                Element endTime = doc.createElement("cbc:EndTime");
                                                endTime.setTextContent(hours + ":" + minutes + ":" + seconds + ".0Z");
                                                requestedDeliveryPeriod.appendChild(endTime);
                                        }
                                }

                                // cac:TransactionConditions
                                Element transactionConditions = doc.createElement("cac:TransactionConditions");
                                rootElement.appendChild(transactionConditions);
                                {
                                        // cbc:Description
                                        Element description = doc.createElement("cbc:Description");
                                        description.setTextContent("Self-Invoicing must be applied to the transaction");
                                        transactionConditions.appendChild(description);
                                }

                                // cac:AnticipatedMonetaryTotal
                                Element anticipatedMonetaryTotal = doc.createElement("cac:AnticipatedMonetaryTotal");
                                rootElement.appendChild(anticipatedMonetaryTotal);
                                {                       
                                        // cbc:LineExtensionAmount
                                        lineExtAmount = doc.createElement("cbc:LineExtensionAmount");
                                        lineExtAmount.setAttribute("currencyID", "EUR");
                                        anticipatedMonetaryTotal.appendChild(lineExtAmount);

                                        // cbc:PayableAmount
                                        payableAmount = doc.createElement("cbc:PayableAmount");
                                        payableAmount.setAttribute("currencyID", "EUR");
                                        anticipatedMonetaryTotal.appendChild(payableAmount);
                                }


                                BigDecimal lineExtTotalAmount = new BigDecimal(0.0);
                                Integer index = 0;
                                for (String drug : drugs) {
                                        Integer quantity = quantities.get(index);
                                        BigDecimal price = prices.get(index);
                                        index++;

                                        // cac:OrderLine
                                        Element orderLine = doc.createElement("cac:OrderLine");
                                        rootElement.appendChild(orderLine);                     

                                        {
                                                // cac:LineItem
                                                Element lineItem = doc.createElement("cac:LineItem");
                                                orderLine.appendChild(lineItem);

                                                {
                                                        // cbc:ID
                                                        Element lineItemID = doc.createElement("cbc:ID");
                                                        lineItemID.setTextContent(index.toString());
                                                        lineItem.appendChild(lineItemID);

                                                        // cbc:Quantity
                                                        Element itemQuantity = doc.createElement("cbc:Quantity");
                                                        itemQuantity.setAttribute("unitcode", "PKG");
                                                        itemQuantity.setTextContent(quantity.toString());
                                                        lineItem.appendChild(itemQuantity);

                                                        // cbc:LineExtensionAmount
                                                        Element lineExtensionAmount = doc.createElement("cbc:LineExtensionAmount");
                                                        lineExtensionAmount.setAttribute("currencyID", "EUR");
                                                        lineExtensionAmount.setTextContent(price.setScale(2, BigDecimal.ROUND_HALF_EVEN).toString());
                                                        lineItem.appendChild(lineExtensionAmount);
                                                        finalAmount = finalAmount.add(price.multiply(new BigDecimal(quantity)));

                                                        // cbc:TotalTaxAmount
                                                        Element totalTaxAmount = doc.createElement("cbc:TotalTaxAmount");
                                                        totalTaxAmount.setAttribute("currencyID", "EUR");
                                                        BigDecimal taxAmount = price.multiply(new BigDecimal(0.06)).multiply(new BigDecimal(quantity));
                                                        totalTaxAmount.setTextContent(taxAmount.setScale(2, BigDecimal.ROUND_HALF_EVEN).toString());
                                                        lineItem.appendChild(totalTaxAmount);
                                                        finalAmount= finalAmount.add(taxAmount);

                                                        // cac:Price
                                                        Element itemPrice = doc.createElement("cac:Price");
                                                        lineItem.appendChild(itemPrice);

                                                        {
                                                                // cbc:PriceAmount
                                                                Element itemPriceAmount = doc.createElement("cbc:PriceAmount");
                                                                itemPriceAmount.setAttribute("currencyID", "EUR");
                                                                itemPriceAmount.setTextContent(price.setScale(2, BigDecimal.ROUND_HALF_EVEN).toString());
                                                                itemPrice.appendChild(itemPriceAmount);

                                                                // cbc:BaseQuantity
                                                                Element itemBaseQuantity = doc.createElement("cbc:BaseQuantity");
                                                                itemBaseQuantity.setAttribute("unitCode", "PKG");
                                                                itemBaseQuantity.setTextContent("1");
                                                                itemPrice.appendChild(itemBaseQuantity);
                                                        }

                                                        // cac:Item
                                                        Element item = doc.createElement("cac:Item");
                                                        lineItem.appendChild(item);                                             

                                                        {
                                                                // cbc:Name
                                                                Element itemName = doc.createElement("cbc:Name");
                                                                itemName.setTextContent(drug);
                                                                item.appendChild(itemName);

                                                                // cac:BuyersItemIdentification
                                                                Element buyersItemID = doc.createElement("cac:BuyersItemIdentification");
                                                                item.appendChild(buyersItemID);

                                                                {
                                                                        // cbc:ID
                                                                        Element buyersItemIDNumber = doc.createElement("cbc:ID");
                                                                        buyersItemIDNumber.setTextContent("6578489");
                                                                        buyersItemID.appendChild(buyersItemIDNumber);
                                                                }

                                                                // cac:SellersItemIdentification
                                                                Element sellersItemID = doc.createElement("cac:SellersItemIdentification");
                                                                item.appendChild(sellersItemID);

                                                                {
                                                                        // cbc:ID
                                                                        Element sellersItemIDNumber = doc.createElement("cbc:ID");
                                                                        sellersItemIDNumber.setTextContent("17589683");
                                                                        sellersItemID.appendChild(sellersItemIDNumber);
                                                                }
                                                        }
                                                }
                                        }
                                }
                                lineExtAmount.setTextContent(finalAmount.setScale(2, BigDecimal.ROUND_HALF_EVEN).toString());
                                payableAmount.setTextContent(finalAmount.setScale(2, BigDecimal.ROUND_HALF_EVEN).toPlainString());


                                //write the content into xml file
                                TransformerFactory transformerFactory = TransformerFactory.newInstance();
                                Transformer transformer = transformerFactory.newTransformer();
                                DOMSource source = new DOMSource(doc);

                                StreamResult result =  new StreamResult(new File("/tmp/orders/Order"+ number +".xml"));
                                transformer.transform(source, result);

                                generateInvoiceReq(drugs, quantities, prices);

                                System.out.println("File Order.xml Created!");


                        } catch (ParserConfigurationException e) {
                                e.printStackTrace();
                        } catch (TransformerConfigurationException e) {
                                e.printStackTrace();
                        } catch (TransformerException e) {
                                e.printStackTrace();
                        }        
    }


	// ----------------------------------------------------------------------------------
	// ----------------------------------------------------------------------------------

    @WebMethod(exclude = true)
    public void generateInvoiceReq(List<String> drugs, List<Integer> quantities, List<BigDecimal> prices) {

		DocumentBuilderFactory docFactory = DocumentBuilderFactory.newInstance();
		DocumentBuilder docBuilder;
		try {
			docBuilder = docFactory.newDocumentBuilder();

			//root element
			Document doc = docBuilder.newDocument();
			Element rootElement = doc.createElement("msg:message");
			doc.appendChild(rootElement);

			SimpleDateFormat dt1 = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
			SimpleDateFormat dt2 = new SimpleDateFormat("yyyy-MM-dd");			
			Date date = new Date();
			
			
			// set attributes of Invoice Req
			rootElement.setAttribute("xmlns:msg", "urn:netdocs:schemas:message");
			rootElement.setAttribute("xmlns:xsi", "http://www.w3.org/2001/XMLSchema-instance");
			rootElement.setAttribute("xsi:schemaLocation", "urn:netdocs:schemas:message http://www.netdocs.com.pt/schemas/netdocsxmlapi-1.0/xsd/maindoc/ND-Message-1.0.xsd");
			rootElement.setAttribute("id", "201303000028");
			rootElement.setAttribute("creationDateTime", dt1.format(date).replace(" ", "T"));

			// <sender>
			Element sender = doc.createElement("sender");
			rootElement.appendChild(sender);

			{
				//<id>
				Element senderID = doc.createElement("id");
				senderID.setAttribute("entityIdTypeCoded", "NETDOCSID");
				senderID.setTextContent("IST02");
				sender.appendChild(senderID);
				//<name>
				Element senderName = doc.createElement("name");
				senderName.setTextContent("BIOSAUDE, PRODUTOS FARMACEUTICOS, LDA");
				sender.appendChild(senderName);
				//<addressInformation>
				Element senderAddress = doc.createElement("addressInformation");
				sender.appendChild(senderAddress);
				{
					//<countryCode>
					Element senderCountryCode = doc.createElement("countryCode");
					senderCountryCode.setTextContent("PT");
					senderAddress.appendChild(senderCountryCode);
				}
			}

			// <receiver>
			Element receiver = doc.createElement("receiver");	
			rootElement.appendChild(receiver);

			{
				//<id>
				Element receiverID = doc.createElement("id");
				receiverID.setAttribute("entityIdTypeCoded", "VAT");
				receiverID.setTextContent("501507931");
				receiver.appendChild(receiverID);
				//<name>
				Element receiverName = doc.createElement("name");
				receiverName.setTextContent("HOSPITAL SÃO NUNO");
				receiver.appendChild(receiverName);
				//<addressInformation>
				Element receiverAddress = doc.createElement("addressInformation");
				receiver.appendChild(receiverAddress);
				{
					//<countryCode>
					Element receiverCountryCode = doc.createElement("countryCode");
					receiverCountryCode.setTextContent("PT");
					receiverAddress.appendChild(receiverCountryCode);
				}
			}


			// <doc:invoice>
			Element invoice = doc.createElement("doc:invoice");
			rootElement.appendChild(invoice);

			Random docNumGen = new Random();
			Integer docNum = docNumGen.nextInt(91);
			invoice.setAttribute("documentNumber", "6696464823"+ number++ +docNum.toString());
			invoice.setAttribute("documentDate", dt2.format(date));
			invoice.setAttribute("schemaVersion", "1.0");
			invoice.setAttribute("purpose", "ORIGINAL");
			invoice.setAttribute("xmlns:doc", "urn:netdocs:schemas:document");
			invoice.setAttribute("xsi:schemaLocation", "urn:netdocs:schemas:document http://www.netdocs.com.pt/schemas/netdocsxmlapi-1.0/xsd/maindoc/ND-Invoice-1.0.xsd");

			{
				//<seller>
				Element seller = doc.createElement("seller");
				invoice.appendChild(seller);
				{
					//<id>
					Element sellerID = doc.createElement("id");
					sellerID.setAttribute("entityIdTypeCoded", "NETDOCSID");
					sellerID.setTextContent("IST02");
					seller.appendChild(sellerID);
					//<name>
					Element sellerName = doc.createElement("name");
					sellerName.setTextContent("BIOSAUDE, PRODUTOS FARMACEUTICOS, LDA");
					seller.appendChild(sellerName);
					//<addressInformation>
					Element sellerAddress = doc.createElement("addressInformation");
					seller.appendChild(sellerAddress);

					{
						//<address>
						Element sellerAddressTitle = doc.createElement("address");
						sellerAddressTitle.setTextContent("Avenida Jos Malhoa, 2 ,2Œ - 2.2");
						sellerAddress.appendChild(sellerAddressTitle);
						//<city>
						Element sellerAddressCity = doc.createElement("city");
						sellerAddressCity.setTextContent("Lisboa");
						sellerAddress.appendChild(sellerAddressCity);
						//<postalCode>
						Element sellerAddressPostalCode = doc.createElement("postalCode");
						sellerAddress.appendChild(sellerAddressPostalCode);
						{
							//<zip>
							Element sellerAddressPostalCodeZip = doc.createElement("zip");
							sellerAddressPostalCodeZip.setTextContent("1070-325");
							sellerAddressPostalCode.appendChild(sellerAddressPostalCodeZip);
							//<area>
							Element sellerAddressPostalCodeArea = doc.createElement("area");
							sellerAddressPostalCodeArea.setTextContent("Lisboa");
							sellerAddressPostalCode.appendChild(sellerAddressPostalCodeArea);
						}
						//<countryCode>
						Element sellerAddressCountryCode = doc.createElement("countryCode");
						sellerAddressCountryCode.setTextContent("PT");
						sellerAddress.appendChild(sellerAddressCountryCode);						
					}
					
					//<vatNumber>
					Element sellerVatNum = doc.createElement("vatNumber");
					sellerVatNum.setTextContent("501643982");
					seller.appendChild(sellerVatNum);
					//<commercialRegistrationNumber>
					Element sellerComRegNum = doc.createElement("commercialRegistrationNumber");
					sellerComRegNum.setTextContent("234680");
					seller.appendChild(sellerComRegNum);
					//<commercialRegistrationLocation>
					Element sellerComRegLoc = doc.createElement("commercialRegistrationLocation");
					sellerComRegLoc.setTextContent("Avenida Jos Malhoa, 2 ,2Œ - 2.2");
					seller.appendChild(sellerComRegLoc);
					//<socialCapital>
					Element sellerSocialCap = doc.createElement("socialCapital");
					sellerSocialCap.setTextContent("550000");
					seller.appendChild(sellerSocialCap);
				}
				//<buyer>
				Element buyer = doc.createElement("buyer");
				invoice.appendChild(buyer);
				{
					//<id>
					Element buyerID = doc.createElement("id");
					buyerID.setAttribute("entityIdTypeCoded", "VAT");
					buyerID.setTextContent("501507931");
					buyer.appendChild(buyerID);
					//<name>
					Element buyerName = doc.createElement("name");
					buyerName.setTextContent("HOSPITAL SÃO NUNO");
					buyer.appendChild(buyerName);
					//<addressInformation>
					Element buyerAddress = doc.createElement("addressInformation");
					buyer.appendChild(buyerAddress);

					{
						//<address>
						Element buyerAddressTitle = doc.createElement("address");
						buyerAddressTitle.setTextContent("Av. Rovisco Pais, 1");
						buyerAddress.appendChild(buyerAddressTitle);
						//<city>
						Element buyerAddressCity = doc.createElement("city");
						buyerAddressCity.setTextContent("Lisboa");
						buyerAddress.appendChild(buyerAddressCity);
						//<postalCode>
						Element buyerAddressPostalCode = doc.createElement("postalCode");
						buyerAddress.appendChild(buyerAddressPostalCode);
						{
							//<zip>
							Element buyerAddressPostalCodeZip = doc.createElement("zip");
							buyerAddressPostalCodeZip.setTextContent("1049-001");
							buyerAddressPostalCode.appendChild(buyerAddressPostalCodeZip);
							//<area>
							Element buyerAddressPostalCodeArea = doc.createElement("area");
							buyerAddressPostalCodeArea.setTextContent("Lisboa");
							buyerAddressPostalCode.appendChild(buyerAddressPostalCodeArea);
						}
						//<countryCode>
						Element buyerAddressCountryCode = doc.createElement("countryCode");
						buyerAddressCountryCode.setTextContent("PT");
						buyerAddress.appendChild(buyerAddressCountryCode);						
					}

					//<vatNumber>
					Element buyerVatNumber = doc.createElement("vatNumber");
					buyerVatNumber.setTextContent("501507931");
					buyer.appendChild(buyerVatNumber);
				}
				//<billTo>
				Element billTo = doc.createElement("billTo");
				invoice.appendChild(billTo);
				{
					//<id>
					Element billToID = doc.createElement("id");
					billToID.setAttribute("entityIdTypeCoded", "VAT");
					billToID.setTextContent("501507931");
					billTo.appendChild(billToID);
					//<name>
					Element billToName = doc.createElement("name");
					billToName.setTextContent("HOSPITAL SÃO NUNO");
					billTo.appendChild(billToName);
					//<addressInformation>
					Element billToAddress = doc.createElement("addressInformation");
					billTo.appendChild(billToAddress);

					{
						//<address>
						Element billToAddressTitle = doc.createElement("address");
						billToAddressTitle.setTextContent("Av. Rovisco Pais, 1");
						billToAddress.appendChild(billToAddressTitle);
						//<city>
						Element billToAddressCity = doc.createElement("city");
						billToAddressCity.setTextContent("Lisboa");
						billToAddress.appendChild(billToAddressCity);
						//<postalCode>
						Element billToAddressPostalCode = doc.createElement("postalCode");
						billToAddress.appendChild(billToAddressPostalCode);
						{
							//<zip>
							Element billToAddressPostalCodeZip = doc.createElement("zip");
							billToAddressPostalCodeZip.setTextContent("1049-001");
							billToAddressPostalCode.appendChild(billToAddressPostalCodeZip);
							//<area>
							Element billToAddressPostalCodeArea = doc.createElement("area");
							billToAddressPostalCodeArea.setTextContent("Lisboa");
							billToAddressPostalCode.appendChild(billToAddressPostalCodeArea);
						}
						//<countryCode>
						Element billToAddressCountryCode = doc.createElement("countryCode");
						billToAddressCountryCode.setTextContent("PT");
						billToAddress.appendChild(billToAddressCountryCode);						
					}

					//<vatNumber>
					Element billToVatNum = doc.createElement("vatNumber");
					billToVatNum.setTextContent("501507931");
					billTo.appendChild(billToVatNum);
				}
				//<shipTo>
				Element shipTo = doc.createElement("shipTo");
				invoice.appendChild(shipTo);
				{
					//<id>
					Element shipToID = doc.createElement("id");
					shipToID.setAttribute("entityIdTypeCoded", "VAT");
					shipToID.setTextContent("501507931");
					shipTo.appendChild(shipToID);
					//<name>
					Element shipToName = doc.createElement("name");
					shipToName.setTextContent("HOSPITAL SÃO NUNO");
					shipTo.appendChild(shipToName);
					//<addressInformation>
					Element shipToAddress = doc.createElement("addressInformation");
					shipTo.appendChild(shipToAddress);

					{
						//<address>
						Element shipToAddressTitle = doc.createElement("address");
						shipToAddressTitle.setTextContent("Av. Rovisco Pais, 1");
						shipToAddress.appendChild(shipToAddressTitle);
						//<city>
						Element shipToAddressCity = doc.createElement("city");
						shipToAddressCity.setTextContent("Lisboa");
						shipToAddress.appendChild(shipToAddressCity);
						//<postalCode>
						Element shipToAddressPostalCode = doc.createElement("postalCode");
						shipToAddress.appendChild(shipToAddressPostalCode);
						{
							//<zip>
							Element shipToAddressPostalCodeZip = doc.createElement("zip");
							shipToAddressPostalCodeZip.setTextContent("1049-001");
							shipToAddressPostalCode.appendChild(shipToAddressPostalCodeZip);
							//<area>
							Element shipToAddressPostalCodeArea = doc.createElement("area");
							shipToAddressPostalCodeArea.setTextContent("Lisboa");
							shipToAddressPostalCode.appendChild(shipToAddressPostalCodeArea);
						}
						//<countryCode>
						Element shipToAddressCountryCode = doc.createElement("countryCode");
						shipToAddressCountryCode.setTextContent("PT");
						shipToAddress.appendChild(shipToAddressCountryCode);						
					}

				}
				//<additionalDate>
				Element additionalDate1 = doc.createElement("additionalDate");
				additionalDate1.setAttribute("type", "CUSTOMERAVAILABLE");
				additionalDate1.setTextContent(dt1.format(date).replace(" ", "T"));
				invoice.appendChild(additionalDate1);
				//<additionalDate>
				Element additionalDate2 = doc.createElement("additionalDate");
				additionalDate2.setAttribute("type", "DUE");
				additionalDate2.setTextContent(dt1.format(date).replace(" ", "T"));
				invoice.appendChild(additionalDate2);
				//<additionalDate>
				date.setDate(date.getDay()+2);
				Element additionalDate3 = doc.createElement("additionalDate");
				additionalDate3.setAttribute("type", "DEPARTURE");
				additionalDate3.setTextContent(dt1.format(date).replace(" ", "T"));
				invoice.appendChild(additionalDate3);
				//<reference>
				Element reference1 = doc.createElement("reference");
				reference1.setAttribute("type", "ORDER");
				Random orderRefGen = new Random();
				Integer orderRef = orderRefGen.nextInt(9);
				reference1.setTextContent("00012378055"+orderRef.toString());
				invoice.appendChild(reference1);
				//<reference>
				Element reference2 = doc.createElement("reference");
				reference2.setAttribute("type", "DESPATCHADVICE");					
				reference2.setTextContent("2013000361");
				invoice.appendChild(reference2);
				//<currencyCode>
				Element currencyCode = doc.createElement("currencyCode");
				currencyCode.setTextContent("EUR");
				invoice.appendChild(currencyCode);

				//PRODUCTS
				Integer number = 0;
				Integer index = 0;
				Integer drugQuantity;
				BigDecimal drugPrice;
				BigDecimal totalVatAmountN = new BigDecimal(0.0);
				BigDecimal totalTaxableAmountN = new BigDecimal(0.0);
				for(String drug : drugs) {
					drugQuantity = quantities.get(index);
					drugPrice = prices.get(index);
					number++;
					index++;
					//<lineItem>
					Element lineItem = doc.createElement("lineItem");
					lineItem.setAttribute("number", number.toString());
					invoice.appendChild(lineItem);
					{
						Integer sellerCode;
						Integer buyerCode;
						Random generator = new Random();						
						sellerCode = generator.nextInt(999999 - 100000) + 100000;
						buyerCode = generator.nextInt(999999 - 100000) + 100000;

						//<gtinCode>
						Element gtinCode = doc.createElement("gtinCode");
						gtinCode.setTextContent("5601312040866");
						lineItem.appendChild(gtinCode);
						//<sellerItemCode>
						Element sellerItemCode = doc.createElement("sellerItemCode");
						sellerItemCode.setTextContent(sellerCode.toString());
						lineItem.appendChild(sellerItemCode);
						//<buyerItemCode>
						Element buyerItemCode = doc.createElement("buyerItemCode");
						buyerItemCode.setTextContent(buyerCode.toString());
						lineItem.appendChild(buyerItemCode);
						//<description>
						Element description = doc.createElement("description");
						description.setTextContent(drug);
						lineItem.appendChild(description);
						//<quantity>
						Element quantity = doc.createElement("quantity");
						lineItem.appendChild(quantity);
						{
							//<value>
							Element value = doc.createElement("value");
							value.setTextContent(drugQuantity.toString());
							quantity.appendChild(value);
							//<unitOfMeasure>
							Element unitOfMeasure = doc.createElement("unitOfMeasure");
							unitOfMeasure.setTextContent("PKG");
							quantity.appendChild(unitOfMeasure);
						}
						//<netUnitPrice>
						Element netUnitPrice = doc.createElement("netUnitPrice");
						netUnitPrice.setTextContent(drugPrice.setScale(2, BigDecimal.ROUND_HALF_EVEN).toString());
						lineItem.appendChild(netUnitPrice);
						//<grossUnitPrice>
						Element grossUnitPrice = doc.createElement("grossUnitPrice");
						grossUnitPrice.setTextContent(drugPrice.setScale(2, BigDecimal.ROUND_HALF_EVEN).toString());
						lineItem.appendChild(grossUnitPrice);
						//<vatPercentage>
						Element vatPercentage = doc.createElement("vatPercentage");
						vatPercentage.setTextContent("6.00");
						lineItem.appendChild(vatPercentage);
						//<vatAmount>
						Element vatAmount = doc.createElement("vatAmount");
						BigDecimal vatAmountN = new BigDecimal(drugQuantity).multiply(drugPrice).multiply(new BigDecimal(0.06));
						totalVatAmountN = totalVatAmountN.add(vatAmountN);
						vatAmount.setTextContent(vatAmountN.setScale(2, BigDecimal.ROUND_HALF_EVEN).toString());
						lineItem.appendChild(vatAmount);
						//<taxableAmount>
						Element taxableAmount = doc.createElement("taxableAmount");
						BigDecimal taxableAmountN = new BigDecimal(drugQuantity).multiply(drugPrice);
						totalTaxableAmountN = totalTaxableAmountN.add(taxableAmountN);
						taxableAmount.setTextContent(taxableAmountN.setScale(2, BigDecimal.ROUND_HALF_EVEN).toString());
						lineItem.appendChild(taxableAmount);
						//<netAmount>
						Element netAmount = doc.createElement("netAmount");
						BigDecimal netAmountN = new BigDecimal(drugQuantity).multiply(drugPrice);
						netAmount.setTextContent(netAmountN.setScale(2, BigDecimal.ROUND_HALF_EVEN).toString());
						lineItem.appendChild(netAmount);
					}
				}
				//<vatSummary>
				Element vatSummary = doc.createElement("vatSummary");
				invoice.appendChild(vatSummary);
				{
					//<vatPercentage>
					Element vatPercentage = doc.createElement("vatPercentage");
					vatPercentage.setTextContent("6.00");
					vatSummary.appendChild(vatPercentage);
					//<vatAmount>
					Element vatAmount = doc.createElement("vatAmount");
					vatAmount.setTextContent(totalVatAmountN.setScale(2, BigDecimal.ROUND_HALF_EVEN).toString());
					vatSummary.appendChild(vatAmount);
					//<taxableAmount>
					Element taxableAmount = doc.createElement("taxableAmount");
					taxableAmount.setTextContent(totalTaxableAmountN.setScale(2, BigDecimal.ROUND_HALF_EVEN).toString());
					vatSummary.appendChild(taxableAmount);
				}

				//<totalVatAmount>
				Element totalVatAmount = doc.createElement("totalVatAmount");
				totalVatAmount.setTextContent(totalVatAmountN.setScale(2, BigDecimal.ROUND_HALF_EVEN).toString());
				invoice.appendChild(totalVatAmount);
				//<totalTaxableAmount>
				Element totalTaxableAmount = doc.createElement("totalTaxableAmount");
				totalTaxableAmount.setTextContent(totalTaxableAmountN.setScale(2, BigDecimal.ROUND_HALF_EVEN).toString());
				invoice.appendChild(totalTaxableAmount);
				//<totalNetAmount>
				Element totalNetAmount = doc.createElement("totalNetAmount");
				totalNetAmount.setTextContent(totalTaxableAmountN.setScale(2, BigDecimal.ROUND_HALF_EVEN).toString());
				invoice.appendChild(totalNetAmount);
				//<totalGrossAmount>
				Element totalGrossAmount = doc.createElement("totalGrossAmount");
				totalGrossAmount.setTextContent(totalTaxableAmountN.setScale(2, BigDecimal.ROUND_HALF_EVEN).toString());
				invoice.appendChild(totalGrossAmount);
				//<totalDiscountAmount>
				Element totalDiscountAmount = doc.createElement("totalDiscountAmount");
				totalDiscountAmount.setTextContent("0.00");
				invoice.appendChild(totalDiscountAmount);
				//<totalPayableAmount>
				Element totalPayableAmount = doc.createElement("totalPayableAmount");
				BigDecimal totalPayableAmountN = totalTaxableAmountN.add(totalVatAmountN);
				//totalPayableAmount.setAttribute("textAmount", "DUZENTOS E TRæS EUROS E CINQUENTA E DOIS CENTIMOS");
				totalPayableAmount.setTextContent(totalPayableAmountN.setScale(2, BigDecimal.ROUND_HALF_EVEN).toString());
				invoice.appendChild(totalPayableAmount);
			}




			//write the content into xml file
			TransformerFactory transformerFactory = TransformerFactory.newInstance();
			Transformer transformer = transformerFactory.newTransformer();
			DOMSource source = new DOMSource(doc);

			StreamResult result =  new StreamResult(new File("/tmp/invoiceReq/IST02.xml"));
                        StreamResult result2 =  new StreamResult(new File("/tmp/MQInvoice/IST02.xml"));// MUDAR NO FIM
			transformer.transform(source, result);
                        transformer.transform(source, result2);

			System.out.println("Invoice Request Created!");


		} catch (ParserConfigurationException e) {
			e.printStackTrace();
		} catch (TransformerConfigurationException e) {
			e.printStackTrace();
		} catch (TransformerException e) {
			e.printStackTrace();
		}
        }

}
