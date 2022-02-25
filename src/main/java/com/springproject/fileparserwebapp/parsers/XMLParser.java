package com.springproject.fileparserwebapp.parsers;

import com.springproject.fileparserwebapp.exception.FileParserException;
import com.springproject.fileparserwebapp.exception.InvalidFileException;
import com.springproject.fileparserwebapp.models.Transaction;
import com.springproject.fileparserwebapp.utils.Utils;
import org.springframework.stereotype.Component;
import org.w3c.dom.*;
import org.xml.sax.SAXException;

import javax.xml.XMLConstants;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import java.io.IOException;
import java.io.InputStream;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.UUID;

@Component
public class XMLParser implements Parser {
    @Override
    public ArrayList<Transaction> parse(InputStream inputStream) throws InvalidFileException, FileParserException {
        DocumentBuilderFactory builderFactory = DocumentBuilderFactory.newInstance();

        ArrayList<Transaction> parsedTransactions = new ArrayList<>();

        try {
            builderFactory.setFeature(XMLConstants.FEATURE_SECURE_PROCESSING, true);

            DocumentBuilder documentBuilder = builderFactory.newDocumentBuilder();
            Document doc = documentBuilder.parse(inputStream);

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
                if (transactionID.equals(userID)) {
                    throw new InvalidFileException(" has some null values: it cannot be parsed.");
                }
                Timestamp timestamp = Timestamp.valueOf(getTagValue("timestamp", transactionElement));
                long amount = removeSpacesInAmountProperty(getTagValue("amount", paymentElement));
                String currency = getTagValue("currency", paymentElement);
                String status = getTagValue("status", transactionElement);
                parsedTransactions.add(new Transaction(transactionID, Utils.getCurrentUser(), userID, timestamp, amount, currency, status));
            }
        } catch (ParserConfigurationException | SAXException | IOException e) {
            throw new FileParserException(" cannot be parsed: file is invalid.");
        }
        return parsedTransactions;
    }

    @Override
    public String getAppropriateExtension() {
        return "xml";
    }

    private static String getTagValue(String tag, Element element) {
        try {
            return element.getElementsByTagName(tag).item(0).getTextContent();
        } catch (NullPointerException e) {
            throw new InvalidFileException(" has some null values: it cannot be parsed.");
        }
    }

    private static Long removeSpacesInAmountProperty(String amountString) {
        return Long.parseLong(amountString.replaceAll("\\s", ""));
    }

}
