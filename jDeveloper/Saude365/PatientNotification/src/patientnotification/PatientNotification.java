package patientnotification;


import com.nexmo.messaging.sdk.NexmoSmsClient;
import com.nexmo.messaging.sdk.SmsSubmissionResult;
import com.nexmo.messaging.sdk.messages.TextMessage;

import javax.jws.WebService;

import javax.xml.ws.BindingType;
import javax.xml.ws.soap.SOAPBinding;


@WebService(portName = "PatientNotificationSoap12HttpPort")
@BindingType(SOAPBinding.SOAP12HTTP_BINDING)
public class PatientNotification {

    public String notifyPatient(String name, String phone, String type) {
        
	    String API_KEY = "f9796faa";
	    String API_SECRET = "df29ee12";
	    String SMS_FROM = "HSN";
	    String SMS_TO = phone;
	    String SMS_TEXT = "";
		
		if (type.equals("checkup")) {
			SMS_TEXT = "Sr/Sra " + name + ", ja pode ser atendido/a.";
		} else if (type.equals("exams")) {
			SMS_TEXT = "Sr/Sra " + name + ", os seus exames medicos ja estao disponiveis.";
                } else {
                    SMS_TEXT = "Sr/Sra " + name + ", o medicamento \""+type+"\" ja esta disponivel na farmacia do Hospital.";
		}
		
		// Create a client for submitting to Nexmo

        NexmoSmsClient client = null;
        try {
            client = new NexmoSmsClient(API_KEY, API_SECRET);
        } catch (Exception e) {
            System.err.println("Failed to instanciate a Nexmo Client");
            e.printStackTrace();
            throw new RuntimeException("Failed to instanciate a Nexmo Client");
        }

        // Create a Text SMS Message request object ...

        TextMessage message = new TextMessage(SMS_FROM, SMS_TO, SMS_TEXT);

        // Use the Nexmo client to submit the Text Message ...

        SmsSubmissionResult[] results = null;
        try {
            results = client.submitMessage(message);
        } catch (Exception e) {
            System.err.println("Failed to communicate with the Nexmo Client");
            e.printStackTrace();
            throw new RuntimeException("Failed to communicate with the Nexmo Client");
        }

        // Evaluate the results of the submission attempt ...
        System.out.println("... Message submitted in [ " + results.length + " ] parts");
        for (int i=0;i<results.length;i++) {
            System.out.println("--------- part [ " + (i + 1) + " ] ------------");
            System.out.println("Status [ " + results[i].getStatus() + " ] ...");
            if (results[i].getStatus() == SmsSubmissionResult.STATUS_OK)
                System.out.println("SUCCESS");
            else if (results[i].getTemporaryError())
                System.out.println("TEMPORARY FAILURE - PLEASE RETRY");
            else
                System.out.println("SUBMISSION FAILED!");
            System.out.println("Message-Id [ " + results[i].getMessageId() + " ] ...");
            System.out.println("Error-Text [ " + results[i].getErrorText() + " ] ...");

            if (results[i].getMessagePrice() != null)
                System.out.println("Message-Price [ " + results[i].getMessagePrice() + " ] ...");
            if (results[i].getRemainingBalance() != null)
                System.out.println("Remaining-Balance [ " + results[i].getRemainingBalance() + " ] ...");
        }
	return "Message Sent";
    }
}

