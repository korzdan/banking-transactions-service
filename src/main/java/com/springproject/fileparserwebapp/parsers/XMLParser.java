package com.springproject.fileparserwebapp.parsers;

import com.springproject.fileparserwebapp.model.Transaction;
import org.springframework.stereotype.Component;
import org.w3c.dom.*;
import org.xml.sax.SAXException;

import javax.xml.XMLConstants;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import java.io.File;
import java.io.IOException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.UUID;

@Component
public class XMLParser {

    private static final String FILE_PATH = "src/main/resources/files_to_parse/xml_example.xml";

    public ArrayList<Transaction> parseTransactionsFromXML() {
        DocumentBuilderFactory builderFactory = DocumentBuilderFactory.newInstance();

        ArrayList<Transaction> parsedTransactions = new ArrayList<>();

        try {
            builderFactory.setFeature(XMLConstants.FEATURE_SECURE_PROCESSING, true);

            DocumentBuilder documentBuilder = builderFactory.newDocumentBuilder();
            Document doc = documentBuilder.parse(new File(FILE_PATH));

            NodeList listOfTransactions = doc.getElementsByTagName("transaction");

            for (int i = 0; i < listOfTransactions.getLength(); i++) {
                // Getting all required Elements to parse needed properties
                Element transactionElement = (Element) listOfTransactions.item(i);

                NodeList user = transactionElement.getElementsByTagName("user");
                Element userElement = (Element) user.item(0);

                NodeList paymentDetails = transactionElement.getElementsByTagName("payment_details");
                Element paymentElement = (Element) paymentDetails.item(0);

                // Getting values to generate new Transaction
                UUID transactionID = UUID.fromString(getTagValue("id", transactionElement));
                UUID userID = UUID.fromString(getTagValue("id", userElement));
                Timestamp timestamp = Timestamp.valueOf(getTagValue("timestamp", transactionElement));
                long amount = removeSpacesInAmountProperty(getTagValue("amount", paymentElement));
                String currency = getTagValue("currency", paymentElement);
                String status = getTagValue("status", transactionElement);

                parsedTransactions.add(new Transaction(transactionID, userID, timestamp, amount, currency, status));
            }
            return parsedTransactions;
        } catch (ParserConfigurationException | SAXException | IOException e) {
            e.printStackTrace();
        }
        return parsedTransactions;
    }

    private static String getTagValue(String tag, Element element) {
        return element.getElementsByTagName(tag).item(0).getTextContent();
    }

    private static Long removeSpacesInAmountProperty(String amountString) {
        return Long.parseLong(amountString.replaceAll("\\s", ""));
    }

}
