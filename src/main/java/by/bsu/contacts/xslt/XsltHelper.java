package by.bsu.contacts.xslt;

import by.bsu.contacts.controller.AppHelper;
import by.bsu.contacts.domain.Contact;
import org.w3c.dom.*;

import javax.xml.parsers.*;
import javax.xml.transform.*;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import javax.xml.transform.stream.StreamSource;
import java.io.PrintWriter;
import java.util.List;

public class XsltHelper {
    public static void transform(Document doc, String xsltFilename, PrintWriter output) {
        try {
            AppHelper appHelper = AppHelper.getInstance();
            Source xmlSource = new DOMSource(doc);
            Source xsltSource = new StreamSource(appHelper.getResourseAsStream(xsltFilename));
            Transformer transformer = TransformerFactory.newInstance().newTransformer(xsltSource);
            transformer.transform(xmlSource, new StreamResult(output));
        } catch (TransformerConfigurationException e) {
            e.printStackTrace();
        } catch (TransformerException e) {
            e.printStackTrace();
        }
    }

    public static Document buildContactsXml(List<Contact> contacts, String reqId) {
        Document doc = null;
        try {
            doc = DocumentBuilderFactory.newInstance().newDocumentBuilder().newDocument();

            Element rootElem = doc.createElement("data");
            doc.appendChild(rootElem);

            Element requestElem = doc.createElement("request");
            requestElem.setAttribute("reqId", reqId);
            rootElem.appendChild(requestElem);

            Element contactsList = doc.createElement("contacts");
            rootElem.appendChild(contactsList);

            for (Contact contact : contacts) {
                contactsList.appendChild(buildContactElement(doc, contact));
            }
        } catch (ParserConfigurationException pce) {
            pce.printStackTrace();
        }
        return doc;
    }

    private static Element buildContactElement(Document doc, Contact contact){
        Element contactElem = doc.createElement("contact");
        contactElem.setAttribute("id", Integer.toString(contact.getId()));
        contactElem.setAttribute("first_name", contact.getFirstName());
        contactElem.setAttribute("last_name", contact.getLastName());
        Element phoneElem = doc.createElement("phone");
        phoneElem.appendChild(doc.createTextNode(contact.getPhone()));
        contactElem.appendChild(phoneElem);
        return contactElem;
    }
}
